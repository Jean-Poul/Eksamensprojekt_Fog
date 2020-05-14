package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;


/**
 * UpdateQuoteOrder will update the currently viewed order while updating the jsp site
 */
public class UpdateQuoteOrder extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException {
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


        // Getting parameter to be able to update on current ID
        String viewID = request.getParameter("quoteID");


        // Getting parameters to be able to populate select options
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int cWidth = Integer.parseInt(request.getParameter("carportWidth"));
        int cLength = Integer.parseInt(request.getParameter("carportLength"));
        String sWidth = request.getParameter("shedWidth");
        String sLength = request.getParameter("shedLength");
        String rFlat = request.getParameter("roofFlat");
        String rRaised = request.getParameter("roofRaised");
        String roofOptionDegrees = request.getParameter("roofOptionDegrees");


        // Roof option 0 or 1 for switch case
        int roofOption = Integer.parseInt(request.getParameter("roofOption"));


        // Initialize variables to be able to update an order
        String roofType = null;
        int pitch;
        int orderId;
        int roofDegrees = 0;
        int shedW = 0;
        int shedL = 0;


        // Check if roofOptionDegrees is not empty and parse to int
        if(!roofOptionDegrees.isEmpty()) {
            roofDegrees = Integer.parseInt(roofOptionDegrees);
        }


        // Check if sWidth is not empty and parse to int
        if(!sWidth.isEmpty()) {
            shedW = Integer.parseInt(sWidth);
        }


        // Check if sLength is not empty and parse to int
        if(!sLength.isEmpty()) {
            shedL = Integer.parseInt(sLength);
        }


        // Insert into order using switch case to choose between flat or raised roof (0 or 1 from roofOption)
        switch (roofOption) {
            case 0:
                pitch = 0;
                roofType = "fladt";
                LogicFacade.updateQuoteOrders(orderID, cWidth, cLength, shedW, shedL, roofType, rFlat, pitch);
                break;
            case 1:
                roofType = "rejst";
                LogicFacade.updateQuoteOrders(orderID, cWidth, cLength, shedW, shedL, roofType, rRaised, roofDegrees);;
                break;
        }


        // Singleton for initializing an instance of UserProposition
        // if List is empty
        if ( userProposition == null ) {
            userProposition = LogicFacade.getUserProposition(Integer.parseInt(viewID));
        } else {
            userProposition = (List<UserProposition>) session.getAttribute("userProposition");
        }


        // Singletons for initializing instances of CarportWidth, CarportLength, RoofFlat, RoofRaised, RoofDegree, ShedWidth, ShedLength
        // if List is empty
        if ( carportWidth == null ) {
            carportWidth = LogicFacade.getCarportWidth();
        } else {
            carportWidth = (List<CarportWidth>) session.getAttribute("carportWidth");
        }

        if ( carportLength == null ) {
            carportLength = LogicFacade.getCarportLength();
        } else {
            carportLength = (List<CarportLength>) session.getAttribute("carportLength");
        }


        if ( roofFlat == null ) {
            roofFlat = LogicFacade.getRoofFlat();
        } else {
            roofFlat = (List<RoofFlat>) session.getAttribute("roofFlat");
        }

        if ( roofRaised == null ) {
            roofRaised = LogicFacade.getRoofRaised();
        } else {
            roofRaised = (List<RoofRaised>) session.getAttribute("roofRaised");
        }

        if ( roofDegree == null ) {
            roofDegree = LogicFacade.getRoofDegree();
        } else {
            roofDegree = (List<RoofDegree>) session.getAttribute("roofDegree");
        }


        if ( shedWidth == null ) {
            shedWidth = LogicFacade.getShedWidth();
        } else {
            shedWidth = (List<ShedWidth>) session.getAttribute("shedWidth");
        }

        if ( shedLength == null ) {
            shedLength = LogicFacade.getShedLength();
        } else {
            shedLength = (List<ShedLength>) session.getAttribute("shedLength");
        }


        // Attributes to use on jsp site
        request.setAttribute("userproposition", userProposition);

        request.setAttribute("carportwidth", carportWidth);
        request.setAttribute("carportlength", carportLength);

        request.setAttribute("roofflat", roofFlat);
        request.setAttribute("roofraised", roofRaised);
        request.setAttribute("roofdegree", roofDegree);

        request.setAttribute("shedWidth", shedWidth);
        request.setAttribute("shedLength", shedLength);


        // Return value for FrontController
        return "quoteview";
    }
}
