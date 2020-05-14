package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of Login is to have a user role before you can access the admin page
 *
 */
public class Login extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        //Initializing session variable with current session
        HttpSession session = request.getSession();

        //Getting parameters from jsp to be able to login
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Initializing User to be able to login
        User user = LogicFacade.login(email, password);

        //Initializing
        List<UserProposition> userProposition = (List<UserProposition>) session.getAttribute("userProposition");

        //Singletons to initialize an instance of UserProposition
        //if List is empty
        if (userProposition == null) {
            userProposition = LogicFacade.getAllUserPropositions();
        } else {
            userProposition = (List<UserProposition>) session.getAttribute("userProposition");
        }

        //Attributes to use on jsp site
        session.setAttribute("user", user);
        session.setAttribute("role", user.getRole());
        session.setAttribute("email", email);

        request.setAttribute("userpropositions", userProposition);

        //Return value for FrontController
        return user.getRole() + "page";
    }

}
