package PresentationLayer;

import FunctionLayer.*;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Measurements.*;
import FunctionLayer.Measurements.RoofRaised;
import FunctionLayer.Tables.UserProposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.List;


/**
 * UpdateQuoteOrder will update the current order while updating adminQuoteView.jsp
 */
public class UpdateQuoteOrder extends Command {
    // Initialize variables to be able to update an order
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");
    private double price = 0;
    private int carpWidth = 0;
    private int carpLength = 0;
    private int coverage = 0;
    private int oID = 0;
    private int pitch;
    private int roofDegrees = 0;
    private int shedW = 0;
    private int shedL = 0;
    private int vID = 0;
    private String rFlat;
    private String rRaised;
    private String roofType;
    private String totalPrice;


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


        // Getting parameter to be able to update on current ID
        String viewID = request.getParameter("quoteID");


        // Getting parameters to be able to populate select options
        String orderID = request.getParameter("orderID");
        String cWidth = request.getParameter("carportWidth");
        String cLength = request.getParameter("carportLength");
        String sWidth = request.getParameter("shedWidth");
        String sLength = request.getParameter("shedLength");
        rFlat = request.getParameter("roofFlat");
        rRaised = request.getParameter("roofRaised");
        String roofOptionDegrees = request.getParameter("roofOptionDegrees");


        // Roof option 0 or 1 for switch case
        String roofOption = request.getParameter("roofOption");

        // Getting parameter and initializing variable for showing total price
        totalPrice = request.getParameter("totalPrice");


        // Check if viewID is not empty and parse it to an int
        if (!viewID.isEmpty()) {
            vID = Integer.parseInt(viewID);
        }


        // Check if orderID is not empty and parse it to an int
        if (!orderID.isEmpty()) {
            oID = Integer.parseInt(orderID);
        }


        // Check if cWidth is not empty and parse it to an int
        if (!cWidth.isEmpty()) {
            carpWidth = Integer.parseInt(cWidth);
        }


        // Check if cLength is not empty and parse it to an int
        if (!cLength.isEmpty()) {
            carpLength = Integer.parseInt(cLength);
        }


        // Check if roofOptionDegrees is not empty and parse it to an int
            if (!roofOptionDegrees.isEmpty()) {
                roofDegrees = Integer.parseInt(roofOptionDegrees);
            }


        // Check if sWidth is not empty and parse it to an int
        if (!sWidth.isEmpty()) {
            shedW = Integer.parseInt(sWidth);
        }


        // Check if sLength is not empty and parse it to an int
        if (!sLength.isEmpty()) {
            shedL = Integer.parseInt(sLength);
        }


        // Switch case to choose between flat or raised roof (0 or 1 from roofOption)
        switch (roofOption) {
            case "0":
                pitch = 0;
                roofType = "fladt";
                LogicFacade.updateQuoteOrders(oID, carpWidth, carpLength, shedW, shedL, roofType, rFlat, pitch);
                break;
            case "1":
                roofType = "rejst";
                LogicFacade.updateQuoteOrders(oID, carpWidth, carpLength, shedW, shedL, roofType, rRaised, roofDegrees);
                break;
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


        // Gets the order coverage and price by passing oID to database.
        // Adds the coverage to the price, to be displayed on the site
        if (totalPrice == null) {
            coverage = LogicFacade.getOrderCoverage(oID);
            double coverageCalc = (coverage / 100) + 1;
            price = (LogicFacade.getTotalCarportPrice(oID) * coverageCalc);
            totalPrice = String.valueOf(decimalFormat.format(price));
        } else {
            totalPrice = decimalFormat.format(session.getAttribute("totalPrice"));
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

        request.setAttribute("quoteCoverage", coverage);
        request.setAttribute("totalPrice", totalPrice);


        // Return value for FrontController
        return "adminQuoteView";
    }
}
