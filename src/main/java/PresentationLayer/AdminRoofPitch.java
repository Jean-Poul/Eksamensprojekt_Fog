package PresentationLayer;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Measurements.RoofPitch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Class that gets a List of all roof pitch and factor from database and sets attribute for<br>
 * view on adminRoofPitch.jsp
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class AdminRoofPitch extends Command {

    /**
     * Get roof pitch and factor from database and set attribute for jsp page
     *
     * @param request request for Http Servlet
     * @param response response for Http Servlet
     * @return adminRoofPitch
     * @throws LoginSampleException LoginSampleException
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
