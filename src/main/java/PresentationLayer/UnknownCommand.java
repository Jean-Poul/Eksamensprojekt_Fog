package PresentationLayer;

import FunctionLayer.Exceptions.LoginSampleException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Customized exception handling for errors
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class UnknownCommand extends Command {

    /**
     * Exception handling for errors
     *
     * @param request request for Http Servlet
     * @param response response for Http Servlet
     * @return msg
     * @throws LoginSampleException Exception for login
     */
    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LoginSampleException {
        String msg = "Unknown command. Contact IT";
        throw new LoginSampleException( msg );
    }

}
