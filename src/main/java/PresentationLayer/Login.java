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
 * @author kasper
 */
public class Login extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException {
        //Initializing session variable with current session
        HttpSession session = request.getSession();


        //Initializing User & Lists with attributes from jsp
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = LogicFacade.login(email, password);

        List<UserProposition> userProposition = (List<UserProposition>) session.getAttribute("userProposition");


        //Singleton calls
        if (userProposition == null) {
            userProposition = LogicFacade.getAllUserPropositions();
        } else {
            userProposition = (List<UserProposition>) session.getAttribute("userProposition");
        }


        //Attributes to grab on jsp site
        session.setAttribute("user", user);
        session.setAttribute("role", user.getRole());
        session.setAttribute("email", email);

        request.setAttribute("userpropositions", userProposition);

        //Return value for FrontController
        return user.getRole() + "page";
    }

}
