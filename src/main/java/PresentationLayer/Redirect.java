package PresentationLayer;

import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redirect extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String destination = request.getParameter("destination");

        switch (destination) {
            case "customerpage": request.setAttribute( "message", "Kunde side"); break;
            case "login": request.setAttribute( "message", "Log ind side"); break;
            default: request.setAttribute("message", "Denne side findes ikke"); break;
        }
        return destination;
    }
}