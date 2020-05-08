package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Svg;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Drawing extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        Svg svg = new Svg();

        svg.addCarport();

        request.setAttribute("svgDrawing", svg.toString());

        return "drawing";
    }
}
