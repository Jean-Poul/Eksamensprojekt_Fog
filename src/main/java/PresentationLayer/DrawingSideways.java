package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.SvgSideways;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DrawingSideways extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        SvgSideways svgSideways = new SvgSideways(0.0, 0.0, 1000.0,1000.0,"0,0,1000,1000");

        //Ramme
        svgSideways.addRect(90.0,-20.0, 380.0,850.0);

        //Mål på hele carport
        svgSideways.addRect(75,20,600.0,780.0);

        //Spær
        svgSideways.addRect(35.0,2.5,87.5,4.5);
        svgSideways.addRect(124.0,2.5,87.5,4.5);
        svgSideways.addRect(213.0,2.5,87.5,4.5);
        svgSideways.addRect(302.0,2.5,87.5,4.5);
        svgSideways.addRect(391.0,2.5,87.5,4.5);
        svgSideways.addRect(480.0,2.5,87.5,4.5);
        svgSideways.addRect(590.0,2.5,87.5,4.5);
        svgSideways.addRect(700.0,2.5,87.5,4.5);

        //Tag
        svgSideways.addRect(0.0,2.5,4.5,730.0);
        svgSideways.addRect(0.0,25.0,4.5,730.0);
        svgSideways.addRect(0.0,45.0,4.5,730.0);
        svgSideways.addRect(0.0,65.0,4.5,730.0);
        svgSideways.addRect(0.0,80.0,4.5,730.0);

        //Tagryg
        svgSideways.addRect(0.0,2.5,10.0,730.0);

        //vindskede
        svgSideways.addRect(-5.0,0.0,85.0,10.0);
        svgSideways.addRect(725.0,0.0,85.0,10.0);

        //Vandbraet på vindskede
        svgSideways.addRect(-5.0,70.0,2.5,10.0);
        svgSideways.addRect(725.0,70.0,2.5,10.0);

        //Stolper
        svgSideways.addRect(80.0,95.0,210.0,10.0);
        svgSideways.addRect(355.0,95.0,210.0,10.0);
        svgSideways.addRect(475.0,95.0,210.0,10.0);
        svgSideways.addRect(700.0,95.0,210.0,10.0);

        //rem skur
        svgSideways.addRect(480.0,90.0,19.5,230.0);

        //Sternbraet
        svgSideways.addRect(0.0,80.0,15.0,730.0);

        //Bottom of roof
        svgSideways.addLine(00.0, 85.0,730.0,85.0);

        //Skur
        svgSideways.addRect(475.0,105.0,200.0,10.0);
        svgSideways.addRect(490.0,105.0,200.0,10.0);
        svgSideways.addRect(505.0,105.0,200.0,10.0);
        svgSideways.addRect(520.0,105.0,200.0,10.0);
        svgSideways.addRect(535.0,105.0,200.0,10.0);
        svgSideways.addRect(550.0,105.0,200.0,10.0);
        svgSideways.addRect(565.0,105.0,200.0,10.0);
        svgSideways.addRect(580.0,105.0,200.0,10.0);
        svgSideways.addRect(595.0,105.0,200.0,10.0);
        svgSideways.addRect(610.0,105.0,200.0,10.0);
        svgSideways.addRect(625.0,105.0,200.0,10.0);
        svgSideways.addRect(645.0,105.0,200.0,10.0);
        svgSideways.addRect(660.0,105.0,200.0,10.0);
        svgSideways.addRect(675.0,105.0,200.0,10.0);
        svgSideways.addRect(690.0,105.0,200.0,10.0);
        svgSideways.addRect(705.0,105.0,200.0,10.0);

        //Diagonal Arrows
        svgSideways.addLine(00.0, 325.0,00.0,109.5);
        svgSideways.addLine(730.0, 325.0,730.0,109.5);

        //Horizontal arrow
        svgSideways.addLine(-15.0, 305.0,750.0,305.0);

        //Rooftiles
        svgSideways.addLine(200.0, 250.0,200.0,270.4);
        svgSideways.addLine(223.6, 250.0,223.6,270.4);
        //MANGLER TAGSTEN
        //svgSideways.addPath

        //arrow measurements
        svgSideways.addLine(30, 10.0,30.0,315.0);
        svgSideways.addLine(60, 105.0,60.0,315.0);
        svgSideways.addLine(95, 350.0,175.0,350.0);
        svgSideways.addLine(175, 350.0,450.0,350.0);
        svgSideways.addLine(450, 350.0,570.0,350.0);
        svgSideways.addLine(570, 350.0,795.0,350.0);
        svgSideways.addLine(795, 350.0,825.0,350.0);

        //text measurements
        svgSideways.addUpperText(25,200, 305);
        svgSideways.addUpperText(50,200, 210);

        svgSideways.addLowerText(135,340, 80);
        svgSideways.addLowerText(300,340, 275);

        svgSideways.addLowerText(510,340, 120);
        svgSideways.addLowerText(670,340, 225);
        svgSideways.addLowerText(805,340, 30);


        request.setAttribute("svgdrawingSideways", svgSideways.toString());
        return "drawingSideways";
    }
}
