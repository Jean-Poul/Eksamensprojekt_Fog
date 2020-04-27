package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Svg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Drawing extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        Svg svg = new Svg(75, 10, 1000,1000,"0,0,780,600");

        //Mål på hele carport
        svg.addRect(0,0,600.0,780.0);

        //Skur
        svg.addRect(524,1,400,250);

        //Skur stolper
        svg.addRect(524,1,9.7,10.0);
        svg.addRect(765,1,9.7,10.0);
        svg.addRect(524,390,9.7,10.0);
        svg.addRect(765,390,9.7,10.0);

        //Remme
        svg.addRect(0,35,4.5,780.0);
        svg.addRect(0,565,4.5,780.0);

        //Spær
        svg.addRect(0,0,600.0,4.5);
        svg.addRect(55,0,600.0,4.5);
        svg.addRect(110,0,600.0,4.5);
        svg.addRect(165,0,600.0,4.5);
        svg.addRect(220,0,600.0,4.5);
        svg.addRect(275,0,600.0,4.5);
        svg.addRect(330,0,600.0,4.5);
        svg.addRect(275,0,600.0,4.5);
        svg.addRect(330,0,600.0,4.5);
        svg.addRect(385,0,600.0,4.5);
        svg.addRect(440,0,600.0,4.5);
        svg.addRect(495,0,600.0,4.5);
        svg.addRect(550,0,600.0,4.5);
        svg.addRect(605,0,600.0,4.5);
        svg.addRect(660,0,600.0,4.5);
        svg.addRect(715,0,600.0,4.5);
        svg.addRect(775,0,600.0,4.5);

        //Stolper
        svg.addRect(110,32,9.7,10.0);
        svg.addRect(420,32,9.7,10.0);
        svg.addRect(730,32,9.7,10.0);
        svg.addRect(110,562,9.7,10.0);
        svg.addRect(420,562,9.7,10.0);
        svg.addRect(730,562,9.7,10.0);

        //Kryds
        svg.addDotLine(55,35,600,570);
        svg.addDotLine(55,570,600,35);

        //Pile
        svg.addLine(40,10,40,610);
        svg.addLine(75,650,885,650);

        request.setAttribute("svgdrawing", svg.toString());
        return "drawing";
    }
}
