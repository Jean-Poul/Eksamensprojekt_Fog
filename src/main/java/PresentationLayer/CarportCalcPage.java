package PresentationLayer;

import FunctionLayer.CarportCalculation;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarportCalcPage extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        CarportCalculation c = new CarportCalculation();

        return "index";
    }
}
