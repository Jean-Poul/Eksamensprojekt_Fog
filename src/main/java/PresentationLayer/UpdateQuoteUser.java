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
 * UpdateQuoteUser will update the currently viewed user info while updating the jsp site
 */
public class UpdateQuoteUser extends Command {
    // Initialize variables to be able to update a proposition and calculate price
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");
    private double price = 0;
    private int coverage = 0;
    private int vID = 0;
    private int qID = 0;
    private int oID = 0;
    private int phn = 0;
    private String address;
    private String comments;
    private String email;
    private String name;
    private String status;
    private String totalPrice;
    private String zipcode;


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing List with UserProposition object
        List<UserProposition> userProposition = (List<UserProposition>) session.getAttribute("userProposition");


        // Getting parameter to be able to update on current ID
        String viewID = request.getParameter("quoteID");


        // Getting parameter and initializing variable for showing total price
        totalPrice = request.getParameter("totalPrice");


        // Getting parameters from input fields
        String quoteID = request.getParameter("quoteID");
        name = request.getParameter("name");
        address = request.getParameter("address");
        zipcode = request.getParameter("zipcode");
        String phone = request.getParameter("phone");
        email = request.getParameter("email");
        comments = request.getParameter("comments");

        String orderID = request.getParameter("orderID");
        status = request.getParameter("status");


        // Check if viewID is not empty and parse it to an int
        if (!viewID.isEmpty()) {
            vID = Integer.parseInt(viewID);
        }


        // Check if quoteID is not empty and parse it to an int
        if (!quoteID.isEmpty()) {
            qID = Integer.parseInt(quoteID);
        }


        // Check if phone is not empty and parse it to an int
        if (!phone.isEmpty()) {
            phn = Integer.parseInt(phone);
        }


        // Check if orderID is not empty and parse it to an int
        if (!orderID.isEmpty()) {
            oID = Integer.parseInt(orderID);
        }


        // Updating a user quote with parameters from jsp values
        LogicFacade.updateQuoteUserProposition(qID, name, address, zipcode, phn, email, comments);
        LogicFacade.updateStatus(oID, status);


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
            double coverageCalc = (coverage / 100) + 1;
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
