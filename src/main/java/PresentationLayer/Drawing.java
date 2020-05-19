package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Drawing class will show a table of a item list on the current order and show the blueprints of carport + shed
 */
public class Drawing extends Command {
    // Initialize variable to be able to parse a String to an int
    private int vID = 0;
    private int oID = 0;


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


        // Check if viewID is not empty and parse to int
        if (!viewID.isEmpty()) {
            vID = Integer.parseInt(viewID);
        }


        // Check if orderID is not empty and parse to int
        if (!orderID.isEmpty()) {
            oID = Integer.parseInt(orderID);
        }


        // Singleton for initializing an instance of UserProposition with a user proposition id
        // if List is empty
        if (userProposition == null) {
            userProposition = LogicFacade.getUserProposition(vID);
        } else {
            userProposition = (List<UserProposition>) session.getAttribute("userProposition");
        }


        // Singleton for initializing an instance of ItemList with an order id
        // if List is empty
        if (itemList == null) {
            itemList = LogicFacade.getAllItemList(oID);
        } else {
            itemList = (List<ItemList>) session.getAttribute("itemList");
        }


        // Initializing instances of SVG classes to be able to show drawings
        Svg svg = new Svg(oID);
        SvgFront svgFront = new SvgFront();
        SvgSidewaysBlueprint svgSidewaysBlueprint = new SvgSidewaysBlueprint();
        SvgSideways svgSideways = new SvgSideways();


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
        return "drawing";
    }
}
