package PresentationLayer;

import FunctionLayer.*;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Measurements.*;
import FunctionLayer.Measurements.RoofRaised;
import FunctionLayer.Tables.UserProposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * QuoteView is used to populate info fields with user quote information on adminQuoteView.jsp
 *
 */
public class QuoteView extends Command {
    // Initialize variable to be able to parse a String to an int and calculate price
    private int vID = 0;


    /**
     *
     * @return adminQuoteView
     * @throws LoginSampleException
     * @throws ClassNotFoundException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing List with UserProposition object
        List<UserProposition> userProposition = (List<UserProposition>) session.getAttribute("userProposition");


        // Initializing Lists with measurement objects
        List<CarportWidth> carportWidth = (List<CarportWidth>) session.getAttribute("carportWidth");
        List<CarportLength> carportLength = (List<CarportLength>) session.getAttribute("carportLength");

        List<RoofFlat> roofFlat = (List<RoofFlat>) session.getAttribute("roofFlat");
        List<RoofRaised> roofRaised = (List<RoofRaised>) session.getAttribute("roofRaised");
        List<RoofDegree> roofDegree = (List<RoofDegree>) session.getAttribute("roofDegree");

        List<ShedWidth> shedWidth = (List<ShedWidth>) session.getAttribute("shedWidth");
        List<ShedLength> shedLength = (List<ShedLength>) session.getAttribute("shedLength");


        // Getting parameter for viewing a customer quote on a user proposition id
        String viewID = request.getParameter("viewID");


        // Check if viewID is not empty and parse it to an int
        if (!viewID.isEmpty()) {
            vID = Integer.parseInt(viewID);
        }


        // Singleton for initializing an instance of UserProposition
        // if List is empty
        if (userProposition == null) {
            userProposition = LogicFacade.getUserProposition(vID);
        } else {
            userProposition = (List<UserProposition>) session.getAttribute("userProposition");
        }


        // Singletons for initializing instances of CarportWidth, CarportLength, RoofFlat, RoofRaised, RoofDegree, ShedWidth, ShedLength
        // if List is empty
        if (carportWidth == null) {
            carportWidth = LogicFacade.getCarportWidth();
        } else {
            carportWidth = (List<CarportWidth>) session.getAttribute("carportWidth");
        }

        if (carportLength == null) {
            carportLength = LogicFacade.getCarportLength();
        } else {
            carportLength = (List<CarportLength>) session.getAttribute("carportLength");
        }


        if (roofFlat == null) {
            roofFlat = LogicFacade.getRoofFlat();
        } else {
            roofFlat = (List<RoofFlat>) session.getAttribute("roofFlat");
        }

        if (roofRaised == null) {
            roofRaised = LogicFacade.getRoofRaised();
        } else {
            roofRaised = (List<RoofRaised>) session.getAttribute("roofRaised");
        }

        if (roofDegree == null) {
            roofDegree = LogicFacade.getRoofDegree();
        } else {
            roofDegree = (List<RoofDegree>) session.getAttribute("roofDegree");
        }


        if (shedWidth == null) {
            shedWidth = LogicFacade.getShedWidth();
        } else {
            shedWidth = (List<ShedWidth>) session.getAttribute("shedWidth");
        }

        if (shedLength == null) {
            shedLength = LogicFacade.getShedLength();
        } else {
            shedLength = (List<ShedLength>) session.getAttribute("shedLength");
        }


        // Attributes to use on jsp site
        request.setAttribute("userProposition", userProposition);

        request.setAttribute("carportWidth", carportWidth);
        request.setAttribute("carportLength", carportLength);

        request.setAttribute("roofFlat", roofFlat);
        request.setAttribute("roofRaised", roofRaised);
        request.setAttribute("roofDegree", roofDegree);

        request.setAttribute("shedWidth", shedWidth);
        request.setAttribute("shedLength", shedLength);


        // Return value for FrontController
        return "adminQuoteView";
    }
}
