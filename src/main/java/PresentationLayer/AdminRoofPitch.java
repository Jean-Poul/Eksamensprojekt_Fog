package PresentationLayer;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Measurements.RoofPitch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminRoofPitch extends Command {
    /**
     *
     * @return adminRoofPitch
     * @throws LoginSampleException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing List with Roof Pitch object
        List<RoofPitch> roofPitches = (List<RoofPitch>) session.getAttribute("roofPitch");


        // Singleton to initialize an instance of Roof Pitch
        // if List is empty
        if (roofPitches == null) {
            roofPitches = LogicFacade.getRoofPitch();
        } else {
            roofPitches = (List<RoofPitch>) session.getAttribute("roofPitch");
        }


        // Attribute to use on jsp site
        request.setAttribute("roofPitches", roofPitches);

        return "adminRoofPitch";
    }
}
