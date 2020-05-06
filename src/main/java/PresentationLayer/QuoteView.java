package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Quoteview is used to initialize variables which are used on quoteview.jsp
 */
public class QuoteView extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //Initializing session variable with current session
        HttpSession session = request.getSession();

        //Initializing Lists with user proposition object
        List<UserProposition> userProposition = (List<UserProposition>) session.getAttribute("userProposition");

        //Singletons to initialize an instance of UserProposition
        //if List is empty
        if ( userProposition == null ) {
            String viewID = request.getParameter("viewID");
            userProposition = LogicFacade.getUserProposition(Integer.parseInt(viewID));
        } else {
            userProposition = (List<UserProposition>) session.getAttribute("userProposition");
        }

        //Attributes to grab on jsp site
        request.setAttribute("userproposition", userProposition);

        return "quoteview";
    }
}
