package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Svg;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Drawing extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        Svg svg = new Svg(0, 0, 1000,1000,"0,0,1000,1000");

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
        svg.addRect(385,0,600.0,4.5);
        svg.addRect(440,0,600.0,4.5);
        svg.addRect(495,0,600.0,4.5);
        svg.addRect(550,0,600.0,4.5);
        svg.addRect(605,0,600.0,4.5);
        svg.addRect(660,0,600.0,4.5);
        svg.addRect(715,0,600.0,4.5);
        svg.addRect(775,0,600.0,4.5);

        //Lægter
        svg.addRect(0,0,4.5,780.0);
        svg.addRect(0,35,4.5,780.0);
        svg.addRect(0,61,4.5,780.0);
        svg.addRect(0,87,4.5,780.0);
        svg.addRect(0,113,4.5,780.0);
        svg.addRect(0,139,4.5,780.0);
        svg.addRect(0,165,4.5,780.0);
        svg.addRect(0,191,4.5,780.0);
        svg.addRect(0,217,4.5,780.0);
        svg.addRect(0,243,4.5,780.0);
        svg.addRect(0,269,4.5,780.0);
        // ----------------------------------------------------- //
        svg.addRect(0,305,4.5,780.0);
        svg.addRect(0,331,4.5,780.0);
        svg.addRect(0,357,4.5,780.0);
        svg.addRect(0,383,4.5,780.0);
        svg.addRect(0,409,4.5,780.0);
        svg.addRect(0,435,4.5,780.0);
        svg.addRect(0,461,4.5,780.0);
        svg.addRect(0,487,4.5,780.0);
        svg.addRect(0,512,4.5,780.0);
        svg.addRect(0,539,4.5,780.0);
        svg.addRect(0,565,4.5,780.0);
        svg.addRect(0,600,4.5,780.0);

        //Stolper
        svg.addRect(110,32,9.7,10.0);
        svg.addRect(420,32,9.7,10.0);
        svg.addRect(730,32,9.7,10.0);
        svg.addRect(110,562,9.7,10.0);
        svg.addRect(420,562,9.7,10.0);
        svg.addRect(730,562,9.7,10.0);

        //Kryds
        svg.addDotLine(55,0,550,600);
        svg.addDotLine(55,600,550,0);

        //Pile
        svg.addLine(-30,0,-30,605);
        svg.addLine(0,630,780,630);

        //Pile Skur
        svg.addLine(810,0,810,400);
        svg.addLine(530,-30,780,-30);

        //Skur text
        svg.addUpperText(940,300, 400);
        svg.addLowerText(655,-50, 250);

        //Text
        svg.addLowerText(390,650, 780);
        svg.addUpperText(50,390, 600);

        request.setAttribute("svgDrawing", svg.toString());

        return "drawing";
    }
}
