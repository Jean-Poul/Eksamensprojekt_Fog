package DBAccess;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Users.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class DataMapperTest {


    private static Connection testConnection;
    private static String USER = "root";
    private static String USERPW = "password";
    private static String DBNAME = "fogdb_test?serverTimezone=CET&useSSL=false";
    private static String HOST = "localhost";

    @BeforeClass
    public static void setUp() {
        try {
            // awoid making a new connection for each test
            if ( testConnection == null ) {
                String url = String.format( "jdbc:mysql://%s:3306/%s", HOST, DBNAME );
                Class.forName( "com.mysql.cj.jdbc.Driver" );

                testConnection = DriverManager.getConnection( url, USER, USERPW );
                // Make mappers use test 
                Connector.setConnection( testConnection );
            }

        } catch ( ClassNotFoundException | SQLException ex ) {
            testConnection = null;
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }
    }

    @Before
    public void beforeEachTest() {
        try( Statement stmt = testConnection.createStatement()){
            stmt.execute("drop table if exists users");
            stmt.execute("CREATE TABLE `users` LIKE fogdb.users");
            stmt.execute("INSERT INTO `users` VALUES " +
                    "(1, 'admin@fog.dk', '1234', 'admin')," +
                    "(2, 'palle@fog.dk', '1111', 'admin')," +
                    "(3, 'kurt@fog.dk', '3333', 'admin');");
        } catch (SQLException ex) {
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @Test
    public void testSetUpOK() {
        // Just check that we have a connection.
        assertNotNull( testConnection );
    }

    @Test
    public void testLogin01() throws LoginSampleException {
        // Can we log in
        User user = DataMapper.login( "admin@fog.dk", "1234" );
        assertTrue( user != null );
    }

    @Test( expected = LoginSampleException.class )
    public void testLogin02() throws LoginSampleException {
        // We should get an exception if we use the wrong password
        User user = DataMapper.login( "admin@fog.dk", "4321" );
    }

    @Test
    public void testLogin03() throws LoginSampleException {
        // Admin is supposed to be a admin
        User user = DataMapper.login( "admin@fog.dk", "1234" );
        assertEquals( "admin", user.getRole() );
    }

    @Test
    public void testLogin4() throws LoginSampleException {
        // Admin is supposed to be a admin
        User user = DataMapper.login("admin@fog.dk","1234");
        assertNotEquals("customer", user.getRole());
    }

    @Test
    public void testCreateUser01() throws LoginSampleException {
        // Can we create a new user - Notice, if login fails, this will fail
        // but so would login01, so this is OK
        User original = new User( "svend@fog.dk", "9999", "admin" );
        DataMapper.createUser( original );
        User retrieved = DataMapper.login( "svend@fog.dk", "9999" );
        assertEquals( "admin", retrieved.getRole() );
    }
}
