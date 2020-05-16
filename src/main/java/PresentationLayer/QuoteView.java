package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.List;

/**
 * QuoteView is used to populate info fields with user quote information on quoteview.jsp
 */
public class QuoteView extends Command {
    // Initialize variable to be able to parse a String to an int
    private int vID = 0;
    private int oID = 0;


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
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


        // Getting parameter for calculating price on an order id
        String orderID = request.getParameter("orderID");


        // Check if viewID is not empty and parse it to an int
        if (!viewID.isEmpty()) {
            vID = Integer.parseInt(viewID);
        }


        // Check if orderID is not empty and parse it to an int
        if (!orderID.isEmpty()) {
            oID = Integer.parseInt(orderID);
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


//**************************** SKAL KØRES PÅ oID
        String totalPrice = request.getParameter("price");
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        if (totalPrice == null) {
            double price = 0;
            try {
                CarportCalculation cp = new CarportCalculation(vID);
                price = new PriceCalculator(cp).getTotalCarportPriceCoverage();
            } catch (LoginSampleException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            totalPrice = String.valueOf(decimalFormat.format(price));
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

        request.setAttribute("totalPrice", totalPrice);


        // Return value for FrontController
        return "quoteview";
    }
}
