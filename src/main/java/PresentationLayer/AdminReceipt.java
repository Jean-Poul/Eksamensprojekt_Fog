package PresentationLayer;

import FunctionLayer.*;
import FunctionLayer.Drawings.SvgSideways;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Measurements.*;
import FunctionLayer.Measurements.RoofRaised;
import FunctionLayer.Tables.UserProposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * AdminReceipt will change status on a quote to "Godkend" while printing a receipt for the customer
 */
public class AdminReceipt extends Command {
    // Initialize variable to be able to parse a String to an int and calculate price
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");
    private double coverage = 0;
    private double price = 0;
    private int oID = 0;
    private int vID = 0;
    private final String GODKEND = "Godkend";
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


        // Getting parameter for showing drawing on an order id and change status
        String orderID = request.getParameter("orderID");


        // Getting parameter for viewing a customers proposition
        String viewID = request.getParameter("viewID");


        // Check if orderID is not empty and parse it to an int
        if (!orderID.isEmpty()) {
            oID = Integer.parseInt(orderID);
        }


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


        // Updating status to "Godkend" on the currently viewed order
        LogicFacade.updateStatus(oID, GODKEND);


        // Create date for proposition receipt
        Locale dk = new Locale("da", "DK");
        LocalDateTime receiptDate = LocalDateTime.now();
        DateTimeFormatter receiptFormatDate = DateTimeFormatter.ofPattern("d. MMM yyyy", dk);
        String formattedDate = receiptDate.format(receiptFormatDate);


        // Gets the order coverage and price by passing oID to database.
        // Adds the coverage to the price, to be displayed on the site
        try {
            if (totalPrice == null) {
                coverage = LogicFacade.getOrderCoverage(oID);
                double coverageCalc = ((coverage / 100) + 1);
                price = (LogicFacade.getTotalCarportPrice(oID) * coverageCalc);
                totalPrice = String.valueOf(decimalFormat.format(price));
            } else {
                totalPrice = decimalFormat.format(session.getAttribute("totalPrice"));
            }
        } catch (IllegalArgumentException ex) {
            ex.getMessage();
        }


        // Initializing an instance of SvgSideways to be able to show order visually
        SvgSideways svgSideways = new SvgSideways(oID);


        // Calling methods to draw sideways view in colors
        svgSideways.addCarport();
        svgSideways.addRoof();
        svgSideways.addLines();
        svgSideways.addRooftiles();


        // Attributes to use on jsp site
        request.setAttribute("userProposition", userProposition);

        request.setAttribute("carportWidth", carportWidth);
        request.setAttribute("carportLength", carportLength);

        request.setAttribute("roofFlat", roofFlat);
        request.setAttribute("roofRaised", roofRaised);
        request.setAttribute("roofDegree", roofDegree);

        request.setAttribute("shedWidth", shedWidth);
        request.setAttribute("shedLength", shedLength);

        request.setAttribute("date", formattedDate);

        request.setAttribute("totalPrice", totalPrice);

        request.setAttribute("svgDrawingSideways", svgSideways.toString());


        // Return value for FrontController
        return "adminReceipt";
    }
}
