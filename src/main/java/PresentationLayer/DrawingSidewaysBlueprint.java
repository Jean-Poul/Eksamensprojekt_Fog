package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.SvgSidewaysBlueprint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DrawingSidewaysBlueprint extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        SvgSidewaysBlueprint svgSidewaysBlueprint = new SvgSidewaysBlueprint();


        //ROOF


        svgSidewaysBlueprint.addCarport();
        svgSidewaysBlueprint.addRoof();
        svgSidewaysBlueprint.addLines();

        request.setAttribute("svgdrawingSidewaysBlueprint", svgSidewaysBlueprint.toString());
        return "drawingSidewaysBlueprint";
    }
}
