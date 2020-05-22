package PresentationLayer;

import FunctionLayer.*;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Tables.UserProposition;
import FunctionLayer.Users.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The purpose of Login is to have a user role before you can access the admin page
 *
 */
public class Login extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Getting parameters from jsp to be able to login
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        // Initializing User object to be able to login
        User user = LogicFacade.login(email, password);


        // Initializing List with UserProposition object
        List<UserProposition> userProposition = (List<UserProposition>) session.getAttribute("userProposition");


        // Singleton to initialize an instance of UserProposition
        // if List is empty
        if (userProposition == null) {
            userProposition = LogicFacade.getAllUserPropositions();
        } else {
            userProposition = (List<UserProposition>) session.getAttribute("userProposition");
        }


        // Attributes to use on jsp site
        session.setAttribute("user", user);
        session.setAttribute("role", user.getRole());
        session.setAttribute("email", email);

        request.setAttribute("userProposition", userProposition);


        // Return value for FrontController
        return user.getRole() + "page";
    }

}
