package PresentationLayer;

import FunctionLayer.LoginSampleException;

import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put( "login", new Login() );
        commands.put( "register", new Register() );
        commands.put( "carportCalcPage", new CarportCalcPage() );
        commands.put( "redirect", new Redirect() );
        commands.put( "drawing", new Drawing() );
        commands.put( "carportCustomize", new CarportCustomize() );
        commands.put( "drawingSideways", new DrawingSideways() );
        commands.put("midlertidigTest", new midlertidigTest());
        commands.put( "rejectQuote", new RejectQuote() );
        commands.put( "quoteview", new QuoteView() );
    }

    static Command from( HttpServletRequest request ) {
        String targetName = request.getParameter( "target" );
        if ( commands == null ) {
            initCommands();
        }
        return commands.getOrDefault(targetName, new UnknownCommand() );   // unknowncommand er default.
    }

    abstract String execute( HttpServletRequest request, HttpServletResponse response ) 
            throws LoginSampleException, SQLException;

}
