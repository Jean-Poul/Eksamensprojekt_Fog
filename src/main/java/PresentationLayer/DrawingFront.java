package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.SvgFront;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DrawingFront extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException {

        SvgFront svgFront = new SvgFront();

        svgFront.addCarportFront();

        request.setAttribute("svgDrawingFront", svgFront.toString());

        return "drawingFront";
    }
}
