package PresentationLayer;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Measurements.Roof;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Class that gets a List of all roof materials from database and sets attribute for view on adminRoof.jsp
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class AdminRoof extends Command {
    /**
     * Get roof materials from database and set attribute for jsp page
     *
     * @param request request for Http Servlet
     * @param response response for Http Servlet
     * @return adminRoof
     * @throws LoginSampleException LoginSampleException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing List with Roof object
        List<Roof> roofs = (List<Roof>) session.getAttribute("roof");


        // Singleton to initialize an instance of Roof
        // if List is empty
        if (roofs == null) {
            roofs = LogicFacade.getRoof();
        } else {
            roofs = (List<Roof>) session.getAttribute("roof");
        }


        // Attribute to use on jsp site
        request.setAttribute("roofs", roofs);

        return "adminRoof";
    }
}
