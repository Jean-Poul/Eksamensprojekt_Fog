package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * UpdateQuoteUser will update the currently viewed user info while updating the jsp site
 */
public class UpdateQuoteUser extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, LoginSampleException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing List with UserProposition object
        List<UserProposition> userProposition = (List<UserProposition>) session.getAttribute("userProposition");


        // Getting parameter to be able to update on current ID
        String viewID = request.getParameter("quoteID");


        // Getting parameters from input fields
        int quoteID = Integer.parseInt(request.getParameter("quoteID"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String zipcode = request.getParameter("zipcode");
        int phone = Integer.parseInt(request.getParameter("phone"));
        String email = request.getParameter("email");
        String comments = request.getParameter("comments");

        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String status = request.getParameter("status");


        // Updating a user quote with parameters from jsp values
        LogicFacade.updateQuoteUserProposition(quoteID, name, address, zipcode, phone, email, comments);
        LogicFacade.updateStatus(orderID, status);


        // Singleton for initializing an instance of UserProposition
        // if List is empty
        if ( userProposition == null ) {
            userProposition = LogicFacade.getUserProposition(Integer.parseInt(viewID));
        } else {
            userProposition = (List<UserProposition>) session.getAttribute("userProposition");
        }


        // Attributes to use on jsp site
        request.setAttribute("userproposition", userProposition);


        // Return value for FrontController
        return "quoteview";
    }
}
