package PresentationLayer;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Measurements.Roof;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminRoof extends Command {
    /**
     *
     * @return adminRoof
     * @throws LoginSampleException
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
