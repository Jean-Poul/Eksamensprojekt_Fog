package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Svg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Drawing extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        Svg svg = new Svg(600, 800, "0,0,600,800",0,0);

        //Mål på hele carport
        svg.addRect(0,0,600,780);

        //Remme
        svg.addRect(0,35,4.5,780);
        svg.addRect(0,565,4.5,780);

        //Spær
        svg.addRect(0,565,4.5,780);

        request.setAttribute("svgdrawing", svg.toString());
        return "drawing";
    }
}
