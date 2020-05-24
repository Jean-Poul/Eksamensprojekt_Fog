package PresentationLayer;

import FunctionLayer.*;

import FunctionLayer.Calculation.CarportCalculation;
import FunctionLayer.Calculation.PriceCalculator;
import FunctionLayer.Drawings.Svg;
import FunctionLayer.Drawings.SvgFront;
import FunctionLayer.Drawings.SvgSideways;
import FunctionLayer.Drawings.SvgSidewaysBlueprint;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Tables.ItemList;
import FunctionLayer.Tables.UserProposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * The purpose of EditItemList is to be able to update the quantity of items from the item list
 */
public class EditItemList extends Command {

    // Initialize variable to be able to parse a String to an int
    private int oID = 0;
    private int oLineID = 0;
    private int qty = 0;
    private int vID = 0;


    /**
     *
     * @return adminDrawing
     * @throws LoginSampleException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing List with UserProposition object
        List<UserProposition> userProposition = (List<UserProposition>) session.getAttribute("userProposition");


        // Initializing List with ItemList object
        List<ItemList> itemList = (List<ItemList>) session.getAttribute("itemList");


        // Getting parameter for viewing a customer quote on a specific id
        String viewID = request.getParameter("viewID");


        // Getting parameter for viewing a customer item list on a specific id
        String orderID = request.getParameter("orderID");


        // Getting parameter orderline id to be able to update the quantity of item list
        String orderLineID = request.getParameter("orderLineID");


        // Getting parameter for quantity of units from the specific item list
        String quantity = request.getParameter("quantity");


        // Check if viewID is not empty and parse to int
        if (!viewID.isEmpty()) {
            vID = Integer.parseInt(viewID);
        }


        // Check if orderID is not empty and parse to int
        if (!orderID.isEmpty()) {
            oID = Integer.parseInt(orderID);
        }


        // Check if orderLineID is not empty and parse to int
        if (!orderLineID.isEmpty()) {
            oLineID = Integer.parseInt(orderLineID);
        }


        // Check if quantity is not empty and parse to int
        try {
            if (!quantity.isEmpty()) {
                qty = Integer.parseInt(quantity);
            }
        } catch (NumberFormatException ex) {
            ex.getMessage();
        }


        // Updating an item list with parameters from jsp values
        LogicFacade.updateQuantityOrderline(oLineID, qty);


        // Singleton for initializing an instance of ItemList with an order id
        // if List is empty
        if (itemList == null) {
            itemList = LogicFacade.getAllItemList(oID);
        } else {
            itemList = (List<ItemList>) session.getAttribute("itemList");
        }


        // Singleton for initializing an instance of UserProposition with a user proposition id
        // if List is empty
        if (userProposition == null) {
            userProposition = LogicFacade.getUserProposition(vID);
        } else {
            userProposition = (List<UserProposition>) session.getAttribute("userProposition");
        }


        // Initializing instances of SVG classes to be able to show drawings
        Svg svg = new Svg(oID);
        SvgFront svgFront = new SvgFront(oID);
        SvgSidewaysBlueprint svgSidewaysBlueprint = new SvgSidewaysBlueprint(oID);
        SvgSideways svgSideways = new SvgSideways(oID);


        // Calling Method: addCarport(); to draw eagle view
        svg.addCarport();


        // Calling Method: addCarportFront(); to draw front view
        svgFront.addCarportFront();


        // Calling methods to draw sideways view in blueprint form
        svgSidewaysBlueprint.addCarport();
        svgSidewaysBlueprint.addRoof();
        svgSidewaysBlueprint.addLines();


        // Calling methods to draw sideways view in colors
        svgSideways.addCarport();
        svgSideways.addRoof();
        svgSideways.addLines();
        svgSideways.addRooftiles();


        // Attributes to use on jsp site
        request.setAttribute("itemList", itemList);
        request.setAttribute("userProposition", userProposition);

        request.setAttribute("svgDrawing", svg.toString());
        request.setAttribute("svgDrawingFront", svgFront.toString());
        request.setAttribute("svgDrawingSideways", svgSideways.toString());
        request.setAttribute("svgDrawingSidewaysBlueprint", svgSidewaysBlueprint.toString());


        // Return value for FrontController
        return "adminDrawing";
    }
}
