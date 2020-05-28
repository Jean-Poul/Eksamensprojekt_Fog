package PresentationLayer;

import FunctionLayer.Exceptions.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Register will add a new customer to the database, using email and password
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class Register extends Command {

    /**
     * NOT IN USE<br>
     *     Add new customer to database
     *
     * @param request request for Http Servlet
     * @param response response for Http Servlet
     * @return user.getRole() + "page"
     * @throws LoginSampleException LoginSampleException
     */
    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LoginSampleException {
//        String email = request.getParameter( "email" );
//        String password1 = request.getParameter( "password1" );
//        String password2 = request.getParameter( "password2" );
//       if ( password1.equals( password2 ) ) {
//            User user = LogicFacade.createUser( email, password1 );
//            HttpSession session = request.getSession();
//
//            session.setAttribute("email",email);
//            session.setAttribute( "user", user );
//            session.setAttribute( "role", user.getRole() );
//            return user.getRole() + "page";
//        } else {
//            throw new LoginSampleException( "the two passwords did not match" );
//        }
        return "index";
    }


}
