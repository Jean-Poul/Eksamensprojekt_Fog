package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;


/**
 * The purpose of EditItemList is to be able to update the quantity of items from the item list
 */
public class EditItemList extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing Lists with ItemList object
        List<ItemList> itemList = (List<ItemList>) session.getAttribute("itemList");


        // Getting parameter for viewing a customer item list on a specific id
        String orderID = request.getParameter("orderID");


        // Getting parameter orderline id to be able to update item list quantity
        String orderLineID = request.getParameter("orderLineID");


        // Getting parameter for quantity of units from the specific item list
        String qty = request.getParameter("quantity");


        // Initialize variables to be able to parse a String to an int
        int oID = 0;
        int oLineID = 0;
        int quantity = 0;


        // Check if orderID is not empty and parse to int
        if(!orderID.isEmpty()) {
            oID = Integer.parseInt(orderID);
        }


        // Check if orderLineID is not empty and parse to int
        if(!orderLineID.isEmpty()) {
            oLineID = Integer.parseInt(orderLineID);
        }

        // Check if qty is not empty and parse to int
        if(!qty.isEmpty()) {
            quantity = Integer.parseInt(qty);
        }


        // Updating an item list with parameters from jsp values
        LogicFacade.updateQuantityOrderline(oLineID, quantity);


        // Singleton for initializing an instance of ItemList
        // if List is empty
        if ( itemList == null ) {
            itemList = LogicFacade.getAllItemList(oID);
        } else {
            itemList = (List<ItemList>) session.getAttribute("itemList");
        }


        // Attributes to use on jsp site
        request.setAttribute("itemlist", itemList);


        // Return value for FrontController
        return "drawing";
    }
}
