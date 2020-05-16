package PresentationLayer;

import FunctionLayer.CarportCalculation;
import FunctionLayer.LoginSampleException;
import FunctionLayer.PriceCalculator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * TRIGGERS THE TEST SHOWN IN CONSOLE VIA THE INDEX PAGE
 */
public class midlertidigTest extends Command{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        //Constructor SHOULD receive specific orderID instead of userID
        CarportCalculation c = new CarportCalculation(1);

        try {
            PriceCalculator priceCalc = new PriceCalculator(c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return "index";
    }
}
