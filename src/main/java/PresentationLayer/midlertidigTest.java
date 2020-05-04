package PresentationLayer;

import FunctionLayer.CarportCalculation;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class midlertidigTest extends Command{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException {

        CarportCalculation c = new CarportCalculation();

        return "index";
    }
}
