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
        //svgSideways.addRect(90.0,-20.0, 380.0,850.0);

        //Mål på hele carport
        //svgSideways.addRect(0,0,600.0,780.0);

        //Spær
        svgSideways.addRect(85.0,2.5,87.5,4.5);
        svgSideways.addRect(174.0,2.5,87.5,4.5);
        svgSideways.addRect(263.0,2.5,87.5,4.5);
        svgSideways.addRect(352.0,2.5,87.5,4.5);
        svgSideways.addRect(441.0,2.5,87.5,4.5);
        svgSideways.addRect(520.0,2.5,87.5,4.5);
        svgSideways.addRect(640.0,2.5,87.5,4.5);
        svgSideways.addRect(750.0,2.5,87.5,4.5);

        //Tag
        svgSideways.addRect(50.0,2.5,4.5,730.0);
        svgSideways.addRect(50.0,25.0,4.5,730.0);
        svgSideways.addRect(50.0,45.0,4.5,730.0);
        svgSideways.addRect(50.0,65.0,4.5,730.0);
        svgSideways.addRect(50.0,80.0,4.5,730.0);

        //Tagryg
        svgSideways.addRect(50.0,2.5,10.0,730.0);

        //vindskede
        svgSideways.addRect(45.0,0.0,85.0,10.0);
        svgSideways.addRect(775.0,0.0,85.0,10.0);

        //Vandbraet på vindskede
        svgSideways.addRect(45.0,70.0,2.5,10.0);
        svgSideways.addRect(775.0,70.0,2.5,10.0);

        //Stolper
        svgSideways.addRect(130.0,95.0,210.0,10.0);
        svgSideways.addRect(405.0,95.0,210.0,10.0);
        svgSideways.addRect(525.0,95.0,210.0,10.0);
        svgSideways.addRect(750.0,95.0,210.0,10.0);

        //rem
        svgSideways.addRect(80.0,90.0,19.5,450.0);
        //rem skur
        svgSideways.addRect(530.0,90.0,19.5,230.0);


        //Sternbraet
        svgSideways.addRect(50.0,80.0,15.0,730.0);

        //Bottom of roof
        svgSideways.addLineNoArrow(50.0, 85.0,780.0,85.0);

        //Skur
        svgSideways.addRect(525.0,105.0,200.0,10.0);
        svgSideways.addRect(540.0,105.0,200.0,10.0);
        svgSideways.addRect(555.0,105.0,200.0,10.0);
        svgSideways.addRect(570.0,105.0,200.0,10.0);
        svgSideways.addRect(585.0,105.0,200.0,10.0);
        svgSideways.addRect(600.0,105.0,200.0,10.0);
        svgSideways.addRect(615.0,105.0,200.0,10.0);
        svgSideways.addRect(630.0,105.0,200.0,10.0);
        svgSideways.addRect(645.0,105.0,200.0,10.0);
        svgSideways.addRect(660.0,105.0,200.0,10.0);
        svgSideways.addRect(675.0,105.0,200.0,10.0);
        svgSideways.addRect(695.0,105.0,200.0,10.0);
        svgSideways.addRect(710.0,105.0,200.0,10.0);
        svgSideways.addRect(725.0,105.0,200.0,10.0);
        svgSideways.addRect(740.0,105.0,200.0,10.0);
        svgSideways.addRect(755.0,105.0,200.0,10.0);

        //Diagonal Arrows
        svgSideways.addLineNoArrow(55, 325.0,55,109.5);
        svgSideways.addLineNoArrow(780.0, 325.0,780.0,109.5);

        //Horizontal arrow
        svgSideways.addLineNoArrow(35.0, 305.0,800.0,305.0);

        //Rooftiles
        svgSideways.addLineNoArrow(250.0, 250.0,250.0,270);
        svgSideways.addLineNoArrow(275, 250.0,275,270);
        //Roof tile
        svgSideways.addRoofTile1();
        svgSideways.addRoofTile2();

        //arrow measurements
        svgSideways.addLine(0.0, 0.0,0.0,305.0);
        svgSideways.addLine(25.0, 95.0,25.0,305.0);
        svgSideways.addLine(50.0, 350.0,130.0,350.0);
        svgSideways.addLine(130.0, 350.0,405.0,350.0);
        svgSideways.addLine(405.0, 350.0,525.0,350.0);
        svgSideways.addLine(525.0, 350.0,750.0,350.0);
        svgSideways.addLine(750.0, 350.0,780.0,350.0);

        //text measurements
        svgSideways.addUpperText(90,250, 305);
        svgSideways.addUpperText(120,290, 210);

        svgSideways.addLowerText(80,340, 80);
        svgSideways.addLowerText(275,340, 275);

        svgSideways.addLowerText(470,340, 120);
        svgSideways.addLowerText(660,340, 225);
        svgSideways.addLowerText(770,340, 30);

        request.setAttribute("svgdrawingSideways", svgSideways.toString());
        return "drawingSideways";
    }
}
