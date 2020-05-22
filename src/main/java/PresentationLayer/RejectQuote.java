package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Tables.UserProposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * RejectQuote will reject and delete a customer quote using a user proposition id
 */
public class RejectQuote extends Command {
    // Initialize variable to be able to parse a String to an int
    private int qID;

    /**
     *
     * @return adminpage
     * @throws LoginSampleException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing List with UserProposition object
        List<UserProposition> userProposition = (List<UserProposition>) session.getAttribute("userProposition");


        // Getting parameter of current user proposition id
        String quoteID = request.getParameter("quoteID");


        // Check if quoteID is not empty and parse it to an int
        if(!quoteID.isEmpty()) {
            qID = Integer.parseInt(quoteID);
        }


        // Deleting a quote with quoteID
        LogicFacade.deleteQuote(qID);


        // Singleton for initializing an instance of UserProposition
        // if List is empty
        if ( userProposition == null ) {
            userProposition = LogicFacade.getAllUserPropositions();
        } else {
            userProposition = (List<UserProposition>) session.getAttribute("userProposition");
        }


        // Attribute to use on jsp site
        request.setAttribute("userProposition", userProposition);


        // Return value for FrontController
        return "adminpage";
    }
}
