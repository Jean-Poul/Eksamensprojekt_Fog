package PresentationLayer;

import FunctionLayer.Exceptions.LoginSampleException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Customized exception handling for errors
 */
public class UnknownCommand extends Command {
    /**
     *
     * @return msg
     * @throws LoginSampleException Exception for login
     */
    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LoginSampleException {
        String msg = "Unknown command. Contact IT";
        throw new LoginSampleException( msg );
    }

}
