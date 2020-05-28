package DBAccess;

import FunctionLayer.Calculation.Item;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Tables.UserProposition;
import FunctionLayer.Users.User;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Test class for DataMapper
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
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

            stmt.execute("drop table if exists user_proposition");
            stmt.execute("CREATE TABLE `user_proposition` LIKE fogdb.user_proposition");
            stmt.execute("INSERT INTO `user_proposition` VALUES " +
                    "(1, 'Pop corn', 'Majsgade 99', '9898 Corncity', '98989898', 'pop@corn.com', 'POPpop pop pop POPPOP')," +
                    "(2, 'Pip Fugl', 'Fuglevej 23', '2323 Fugleby', '23232323', 'pip@fugl.dk', 'PIPPIP pipPIP pip pip')," +
                    "(3, 'Pap Kasse', 'Papirgade 55', '5555 Kasseby', '55555555', 'pap@kasse.dk', 'Knitre knitre')");

            stmt.execute("drop table if exists orders");
            stmt.execute("CREATE TABLE `orders` LIKE fogdb.orders");
            stmt.execute("INSERT INTO `orders` VALUE " +
                    "(1, 1, '2020-05-05 18:26:00', 'Forespørgsel', '450', '510', '420', '300', 'rejst', 'Eternittag B6 - Teglrød','25','40','16500.95','23101.33')," +
                    "(2, 2, '2020-05-17 17:53:06', 'Forespørgsel', '360', '630', '330', '210', 'rejst','Betontagsten – B&C protector: Skifer', '25', '40', '13500.45','18900.63')," +
                    "(3, 3, '2020-05-18 13:33:37', 'Forespørgsel', '360', '570', '0', '0', 'fladt', 'bølgepap', '0', '40', '11200.35','15680.49')");

            stmt.execute("drop  table if exists item_list");
            stmt.execute("CREATE TABLE `item_list` LIKE fogdb.item_list");
            stmt.execute("INSERT INTO `item_list` VALUE " +
                    "(1, 'Tilbehør til spær:', 'universal 190 mm højre', 'Til montering af spær på rem', '1', '', '23.00')," +
                    "(2, 'Skruepakke som fast følger med:', '4,5 x 60 mm. Skruer 200 stk.', 'Til montering af Stern, vindskeder, vindkryds & vand bræt', '1', '200', '38.50')," +
                    "(3, 'Tilbehør til stolper:', 'bræddebolt 10 x 120 mm.', 'Til montering af rem på stolper (stk. pr stolpe, 4 i samling)', '2', 'stk', '6.60')");
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

    @Test
    public void testAllUserProposition01() throws LoginSampleException {
        List<UserProposition> userPropositions = DataMapper.getAllUserPropositions();
        // Check that the user_proposition has size
        assertThat(userPropositions, hasSize(3));
        // Check that one of the users email is 'pip@fugl'
        assertThat(userPropositions, hasItem(hasProperty("email", equalTo("pip@fugl.dk"))));

    }

    @Test
    public void testAllUserProposition02() throws LoginSampleException {
        List<UserProposition> userPropositions = DataMapper.getAllUserPropositions();
        // Check that one of the users roof type choice is 'fladt'
        assertThat(userPropositions, hasItem(hasProperty("roof_type", equalTo("fladt"))));

    }

    @Test
    public void testAllUserProposition03() throws LoginSampleException {
        List<UserProposition> userPropositions = DataMapper.getAllUserPropositions();
        // Check that one or more of the users shed_width choice is '330'
        assertThat(userPropositions, hasItem(hasProperty("shed_width", equalTo(330))));

    }

    @Test
    public void testAllUserProposition04() throws LoginSampleException {
        List<UserProposition> userPropositions = DataMapper.getAllUserPropositions();
        // Check that one of the roof type is 'rejst'
        assertThat("rejst", isOneOf(userPropositions.get(0).getRoof_type()));

    }

    @Test
    public void testAllUserProposition05() throws LoginSampleException {
        List<UserProposition> userPropositions = DataMapper.getAllUserPropositions();
        // Check that multiple items is in userproposition
        assertThat(userPropositions, Matchers.hasItems(
                hasProperty("email", equalTo("pop@corn.com")),
                hasProperty("name", equalTo("Pop corn")),
                hasProperty("roof_material", equalTo("Eternittag B6 - Teglrød"))));

    }

    @Test
    public void testItemList01() throws LoginSampleException {
        List<Item> items = DataMapper.getItemList();
        // Check that the item_list has size 3
        assertThat(items, hasSize(3));
        // Check that 'material' has '4,5 x 60 mm. Skruer 200 stk.'
        assertThat(items, hasItem(hasProperty("material", equalTo("4,5 x 60 mm. Skruer 200 stk."))));

    }

    @Test
    public void testItemList02() throws LoginSampleException {
        List<Item> items = DataMapper.getItemList();
        // Check that 'unit' has 'stk'
        assertThat(items, hasItem(hasProperty("unit", equalTo("stk"))));

    }

    @Test
    public void testItemList03() throws LoginSampleException {
        List<Item> items = DataMapper.getItemList();
        // Check that one of the material type is 'Tilbehør til spær:'
        assertThat("Tilbehør til spær:", isOneOf(items.get(0).getMaterialType()));

    }

    @Test
    public void testItemList04() throws LoginSampleException {
        List<Item> items = DataMapper.getItemList();
        // Check that multiple items is in item list
        assertThat(items, Matchers.hasItems(
                hasProperty("material", equalTo("universal 190 mm højre")),
                hasProperty("description", equalTo("Til montering af spær på rem")),
                hasProperty("pricePrUnit", equalTo(23.00))));

    }
}
