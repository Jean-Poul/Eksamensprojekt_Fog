package PresentationLayer;

import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Redirect helps with managing the navigation of the site while not having to make a java class and form for each link
 */
public class Redirect extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        // Getting parameter from header href
        String destination = request.getParameter("destination");


        // Switch case for navigation
        switch (destination) {
            case "customerpage":
                request.setAttribute("message", "Kunde side");
                break;
            case "carportstandard":
                request.setAttribute("message", "Standard byg");
                break;
            case "login":
                request.setAttribute("message", "Log ind side");
                break;
            case "index":
                request.setAttribute("message", "Index side");
                // Terminate current session
                request.getSession().invalidate();
                break;
            default:
                request.setAttribute("message", "Denne side findes ikke");
                break;
        }


        // Return value for FrontController
        return destination;
    }
}
