package PresentationLayer;

import FunctionLayer.CarportWidth;
import FunctionLayer.LogicFacade;
import FunctionLayer.UserProposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * AdminRejectQuote will delete a customer quote using a proposition id
 */
public class AdminRejectQuote extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //Initializing session variable with current session
        HttpSession session = request.getSession();

        //Initializing Lists with user proposition object & getting the parameter quoteID
        List<UserProposition> userProposition = (List<UserProposition>) session.getAttribute("userProposition");

        //Getting parameter for deleting a quote on a specific id
        String quoteID = request.getParameter("quoteID");

        //Deleting a quote with quoteID
        LogicFacade.deleteQuote(Integer.parseInt(quoteID));

        //Singleton for initializing an instance of UserProposition
        //if List is empty
        if ( userProposition == null ) {
            userProposition = LogicFacade.getAllUserPropositions();
        } else {
            userProposition = (List<UserProposition>) session.getAttribute("userProposition");
        }

        //Attributes to use on jsp site
        request.setAttribute("userpropositions", userProposition);

        //Return value for FrontController
        return "adminpage";
    }
}
