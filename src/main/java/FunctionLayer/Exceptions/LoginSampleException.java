package FunctionLayer.Exceptions;

/**
 * The purpose of LoginSampleException is to be able to throw an exception with a message<br>
 * LoginSampleException inherits from the object Exception which is the top level of exception<br>
 * therefor all exceptions are covered
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class LoginSampleException extends Exception {

    public LoginSampleException(String msg) {
        super(msg);
    }


}
