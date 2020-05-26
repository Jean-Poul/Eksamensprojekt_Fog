package FunctionLayer.Exceptions;

/**
 * The purpose of LoginSampleException is to be able to throw an exception with a message
 * LoginSampleException inherits from the object Exception which is the top level of exception
 * therefor all exceptions are covered
 */
public class LoginSampleException extends Exception {

    public LoginSampleException(String msg) {
        super(msg);
    }


}
