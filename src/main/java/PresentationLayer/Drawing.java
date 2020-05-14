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
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing Lists with UserProposition object
        List<UserProposition> userProposition = (List<UserProposition>) session.getAttribute("userProposition");


        // Initializing Lists with ItemList object
        List<ItemList> itemList = (List<ItemList>) session.getAttribute("itemList");


        // Getting parameter for viewing a customer quote on a specific id
        String viewID = request.getParameter("viewID");


        // Getting parameter for viewing a customer item list on a specific id
        String orderID = request.getParameter("orderID");


        // Initialize variable to be able to parse a String to an int
        int oID = 0;


        // Check if orderID is not empty and parse to int
        if(!orderID.isEmpty()) {
            oID = Integer.parseInt(orderID);
        }


        // Singleton for initializing an instance of UserProposition
        // if List is empty
        if ( userProposition == null ) {
            userProposition = LogicFacade.getUserProposition(Integer.parseInt(viewID));
        } else {
            userProposition = (List<UserProposition>) session.getAttribute("userProposition");
        }


        // Singleton for initializing an instance of ItemList
        // if List is empty
        if ( itemList == null ) {
            itemList = LogicFacade.getAllItemList(oID);
        } else {
            itemList = (List<ItemList>) session.getAttribute("itemList");
        }


        // Attributes to use on jsp site
        request.setAttribute("itemlist", itemList);
        request.setAttribute("userproposition", userProposition);


        //MANGLER COMMENTS FRA DE ANDRE*********************************************************

        try {
        Svg svg = new Svg();

        svg.addCarport();

        request.setAttribute("svgDrawing", svg.toString());


        SvgSideways svgSideways = new SvgSideways();



            SvgFront svgFront = new SvgFront();

            svgFront.addCarportFront();

            request.setAttribute("svgDrawingFront", svgFront.toString());


        //ROOF

        svgSideways.addCarport();
        svgSideways.addRoof();
        svgSideways.addLines();
        svgSideways.addRooftiles();

        request.setAttribute("svgdrawingSideways", svgSideways.toString());


        SvgSidewaysBlueprint svgSidewaysBlueprint = new SvgSidewaysBlueprint();


        //ROOF


        svgSidewaysBlueprint.addCarport();
        svgSidewaysBlueprint.addRoof();
        svgSidewaysBlueprint.addLines();


        request.setAttribute("svgdrawingSidewaysBlueprint", svgSidewaysBlueprint.toString());
        }

        catch (NullPointerException ex) {
            ex.getMessage();
        }

        // Return value for FrontController
        return "drawing";
    }
}
