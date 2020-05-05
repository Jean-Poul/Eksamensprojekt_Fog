package PresentationLayer;

import FunctionLayer.CustomerQuote;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 The purpose of Login is to...

 @author kasper
 */
public class Login extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LoginSampleException, SQLException {
        String email = request.getParameter( "email" );
        String password = request.getParameter( "password" );
        User user = LogicFacade.login( email, password );

        HttpSession session = request.getSession();

        session.setAttribute( "user", user );
        session.setAttribute( "role", user.getRole() );
        session.setAttribute("email", email);  // ellers skal man skrive  user.email på jsp siderne og det er sgu lidt mærkeligt at man har adgang til private felter. Men måske er det meget fedt , jeg ved det ikke


        List<CustomerQuote> customerQuoteList = LogicFacade.getCustomerQouteList();
        int userSum = LogicFacade.getUserSum();
        int qouteSum = LogicFacade.getQouteSum();

        List<CustomerQuote> customerQuote = LogicFacade.CustomerQuote();

        request.setAttribute("customerQuote", customerQuote);

        request.setAttribute("userqoutelist", customerQuoteList);
        request.setAttribute("qoutesum", qouteSum);
        request.setAttribute("usersum", userSum);

        return user.getRole() + "page";
    }

}
