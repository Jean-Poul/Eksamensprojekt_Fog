package PresentationLayer;

import FunctionLayer.CarportCalculation;
import FunctionLayer.LoginSampleException;
import FunctionLayer.PriceCalculator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class midlertidigTest extends Command{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException {

        CarportCalculation carportCalc = new CarportCalculation();
        PriceCalculator priceCalc = new PriceCalculator();

        return "index";
    }
}
