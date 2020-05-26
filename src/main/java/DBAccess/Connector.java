package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* Creates a connection to the database
* @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class Connector {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    private static Connection singleton;

    /**
     * Set connection string
     * @param con connection
     */
    public static void setConnection( Connection con ) {
        singleton = con;
    }

    /**
     * Create connection
     * @return singleton
     * @throws ClassNotFoundException Thrown when an application tries to load in a class, but no definition for the class with the specified name could be found.
     * @throws SQLException An exception that provides information on a database access error or other errors
     */
    public static Connection connection() throws ClassNotFoundException, SQLException {
        if ((singleton == null) || singleton.isClosed()) {
            setDBCredentials();
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            singleton = DriverManager.getConnection( URL, USERNAME, PASSWORD );
        }
        return singleton;
    }

    /**
     * Set DB Credentials
     */
    public static void setDBCredentials() {
        String deployed = System.getenv("DEPLOYED");
        if (deployed != null){
            // Prod: hent variabler fra setenv.sh i Tomcats bin folder
            URL = System.getenv("JDBC_CONNECTION_STRING");
            USERNAME = System.getenv("JDBC_USER");
            PASSWORD = System.getenv("JDBC_PASSWORD");
        } else {
            // Localhost
            URL = "jdbc:mysql://localhost:3306/fogdb?serverTimezone=CET"; //&useSSL=false
            USERNAME = "root";
            PASSWORD = "password";
        }
    }

}
