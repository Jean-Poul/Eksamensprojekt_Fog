package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.SvgSideways;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DrawingSideways extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        SvgSideways svgSideways = new SvgSideways();


        //ROOF


        svgSideways.addCarport();
        svgSideways.addRoof();
        svgSideways.addLines();
        svgSideways.addRooftiles();

        request.setAttribute("svgdrawingSideways", svgSideways.toString());
        return "drawingSideways";
    }
}
