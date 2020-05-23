package PresentationLayer;

import FunctionLayer.Exceptions.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Command Pattern design template - called by FrontController
 */
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
        commands.put( "adminReceipt", new AdminReceipt() );
        commands.put( "editItemList", new EditItemList() );
        commands.put( "adminMeasurementUnits", new AdminMeasurementUnits() );
        commands.put( "adminItemList", new AdminItemList() );
        commands.put( "adminRafterSpacing", new AdminRafterSpacing() );
        commands.put( "adminRoof", new AdminRoof() );
        commands.put( "adminRoofPitch", new AdminRoofPitch() );
        commands.put( "adminStandartDimensions", new AdminStandartDimensions() );
        commands.put( "adminMeasurementUnitsDB", new AdminMeasurementUnitsDB() );
        commands.put( "adminItemListDB", new AdminItemListDB() );
        commands.put( "adminRafterSpacingDB", new AdminRafterSpacingDB() );
        commands.put( "adminRoofDB", new AdminRoofDB() );
        commands.put( "adminRoofPitchDB", new AdminRoofPitchDB() );
    }

    /**
     * @param request receives a hidden value from a hidden inputfield with the name "target" from a form on a .jsp page
     * @return Returns the value from inputfield "target"
     */

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
