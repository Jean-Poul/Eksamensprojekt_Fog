package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.UserProposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * RejectQuote will reject and delete a customer quote using a proposition id
 */
public class RejectQuote extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing Lists with UserProposition object
        List<UserProposition> userProposition = (List<UserProposition>) session.getAttribute("userProposition");


        // Getting parameter of current user proposition id
        String quoteID = request.getParameter("quoteID");


        // Deleting a quote with parameter quoteID
        LogicFacade.deleteQuote(Integer.parseInt(quoteID));


        // Singleton for initializing an instance of UserProposition
        // if List is empty
        if ( userProposition == null ) {
            userProposition = LogicFacade.getAllUserPropositions();
        } else {
            userProposition = (List<UserProposition>) session.getAttribute("userProposition");
        }


        // Attributes to use on jsp site
        request.setAttribute("userpropositions", userProposition);

        // Return value for FrontController
        return "adminpage";
    }
}
