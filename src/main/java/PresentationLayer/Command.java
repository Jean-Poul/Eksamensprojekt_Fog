package PresentationLayer;

import FunctionLayer.LoginSampleException;

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
        commands.put( "rejectQuote", new RejectQuote() );
        commands.put( "quoteView", new QuoteView() );
        commands.put( "updateQuoteUser", new UpdateQuoteUser() );
        commands.put( "updateQuoteOrder", new UpdateQuoteOrder() );
        commands.put( "updateQuotePrice", new UpdateQuotePrice() );
        commands.put( "adminRejectQuote", new AdminRejectQuote() );
        commands.put( "returnAdmin", new ReturnAdmin() );
        commands.put( "editItemList", new EditItemList() );
        commands.put( "adminMeasurementUnits", new AdminMeasurementUnits() );
        commands.put( "adminItemList", new AdminItemList() );
        commands.put( "adminRafterSpacing", new AdminRafterSpacing() );
        commands.put( "adminRoof", new AdminRoof() );
        commands.put( "adminRoofPitch", new AdminRoofPitch() );
        commands.put( "adminStandartDimensions", new AdminStandartDimensions() );
    }

    static Command from( HttpServletRequest request ) {
        String targetName = request.getParameter( "target" );
        if ( commands == null ) {
            initCommands();
        }
        return commands.getOrDefault(targetName, new UnknownCommand() );   // unknowncommand er default.
    }

    abstract String execute( HttpServletRequest request, HttpServletResponse response )
            throws LoginSampleException, ClassNotFoundException;

}
