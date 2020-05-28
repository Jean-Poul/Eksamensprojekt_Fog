package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Tables.UserProposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The ReturnAdmin class makes sure the proposition list is updated when returning to adminpage.jsp from adminQuoteView.jsp
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class ReturnAdmin extends Command {

    /**
     * Set userProposition object for view on adminpage.jsp
     *
     * @param request request for Http Servlet
     * @param response response for Http Servlet
     * @return adminpage
     * @throws LoginSampleException LoginSampleException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing List with UserProposition object
        List<UserProposition> userProposition = (List<UserProposition>) session.getAttribute("userProposition");


        // Singleton to initialize an instance of UserProposition
        // if List is empty
        if (userProposition == null) {
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
