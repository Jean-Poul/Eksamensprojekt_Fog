package FunctionLayer.Calculation;

import DBAccess.Connector;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.LogicFacade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.rmi.runtime.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PriceCalculatorTest {

    private static Connection testConnection;
    private static String USER = "root";
    private static String USERPW = "password";
    private static String DBNAME = "fogdb_test?serverTimezone=CET&useSSL=false";
    private static String HOST = "localhost";

    @BeforeClass
    public static void setUp() {
        try {
            // awoid making a new connection for each test
            if (testConnection == null) {
                String url = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
                Class.forName("com.mysql.cj.jdbc.Driver");

                testConnection = DriverManager.getConnection(url, USER, USERPW);
                // Make mappers use test
                Connector.setConnection(testConnection);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    PriceCalculator pc = new PriceCalculator();

    @Test
    public void orderInfoFromDB() throws LoginSampleException {

        int expected = 40;
        int coverage = LogicFacade.getOrderCoverage(1);

        Assert.assertEquals(expected, coverage);
    }

    @Test
    public void getOfferPrice() throws LoginSampleException {

        double expected = 16500.95;

        double totalPrice = LogicFacade.getTotalCarportPrice(1);

        Assert.assertEquals(expected, totalPrice, 0.01);
    }

    @Test
    public void getTotalPrice() throws LoginSampleException {

        double expected = 23101.33;

        double offerPrice = LogicFacade.getTotalCarportPrice(1);
        double coverage = LogicFacade.getOrderCoverage(1);
        double result = offerPrice * ((coverage / 100) + 1);

        Assert.assertEquals(expected, result, 0.01);
    }
}
