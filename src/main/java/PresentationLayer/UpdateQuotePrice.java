package PresentationLayer;

import FunctionLayer.*;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Tables.UserProposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 */
public class UpdateQuotePrice extends Command {
    // Initialize variables to be able to update a proposition and calculate price
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");
    private double coverage = 0;
    private double price = 0;
    private int oID = 0;
    private int vID = 0;
    private int qCov = 0;
    private String totalPrice;


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing List with UserProposition object
        List<UserProposition> userProposition = (List<UserProposition>) session.getAttribute("userProposition");


        // Getting parameter to be able to update on current ID
        String viewID = request.getParameter("quoteID");


        // Getting parameter to be able to update price on current quote
        String orderID = request.getParameter("orderID");


        // Getting parameter to be able to calculate new price
        String quoteCoverage = request.getParameter("quoteCoverage");


        // Getting parameter and initializing variable for showing total price
        totalPrice = request.getParameter("totalPrice");


        // Check if orderID is not empty and parse it to an int
        if (!orderID.isEmpty()) {
            oID = Integer.parseInt(orderID);
        }


        // Check if viewID is not empty and parse it to an int
        if (!viewID.isEmpty()) {
            vID = Integer.parseInt(viewID);
        }


        // Check if quote coverage is not empty and parse it to an int
        if(!quoteCoverage.isEmpty()){
            qCov = Integer.parseInt(quoteCoverage);
        }


        // Set the changed quote coverage
        LogicFacade.updateOrderCoverage(qCov, oID);


        // Singleton for initializing an instance of UserProposition
        // if List is empty
        if (userProposition == null) {
            userProposition = LogicFacade.getUserProposition(vID);
        } else {
            userProposition = (List<UserProposition>) session.getAttribute("userProposition");
        }


        // Gets the order coverage and price by passing oID to database.
        // Adds the coverage to the price, to be displayed on the site
        if (totalPrice == null) {
            coverage = LogicFacade.getOrderCoverage(oID);
            double coverageCalc = ((coverage / 100) + 1);
            price = (LogicFacade.getTotalCarportPrice(oID) * coverageCalc);
            totalPrice = String.valueOf(decimalFormat.format(price));
        } else {
            totalPrice = decimalFormat.format(session.getAttribute("totalPrice"));
        }


        // Attributes to use on jsp site
        request.setAttribute("userProposition", userProposition);

        request.setAttribute("quoteCoverage", coverage);
        request.setAttribute("totalPrice", totalPrice);


        // Return value for FrontController
        return "adminQuoteView";
    }
}
