package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * CarportCustomize is used to get values from the database and populate select option on carportcustomize.jsp
 */
public class CarportCustomize extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //Initializing session variable with current session
        HttpSession session = request.getSession();

        //Initializing Lists with measurement objects
        List<CarportWidth> carportWidth = (List<CarportWidth>) session.getAttribute("carportWidth");
        List<CarportLength> carportLength = (List<CarportLength>) session.getAttribute("carportLength");

        List<RoofFlat> roofFlat = (List<RoofFlat>) session.getAttribute("roofFlat");
        List<RoofRaised> roofRaised = (List<RoofRaised>) session.getAttribute("roofRaised");
        List<RoofDegree> roofDegree = (List<RoofDegree>) session.getAttribute("roofDegree");

        List<ShedWidth> shedWidth = (List<ShedWidth>) session.getAttribute("shedWidth");
        List<ShedLength> shedLength = (List<ShedLength>) session.getAttribute("shedLength");

        //Singletons for initializing instances of CarportWidth, CarportLength, RoofFlat, RoofRaised, RoofDegree, ShedWidth, ShedLength
        //if List is empty
        if ( carportWidth == null ) {
            carportWidth = LogicFacade.getCarportWidth();
        } else {
            carportWidth = (List<CarportWidth>) session.getAttribute("carportWidth");
        }

        if ( carportLength == null ) {
            carportLength = LogicFacade.getCarportLength();
        } else {
            carportLength = (List<CarportLength>) session.getAttribute("carportLength");
        }


        if ( roofFlat == null ) {
            roofFlat = LogicFacade.getRoofFlat();
        } else {
            roofFlat = (List<RoofFlat>) session.getAttribute("roofFlat");
        }

        if ( roofRaised == null ) {
            roofRaised = LogicFacade.getRoofRaised();
        } else {
            roofRaised = (List<RoofRaised>) session.getAttribute("roofRaised");
        }

        if ( roofDegree == null ) {
            roofDegree = LogicFacade.getRoofDegree();
        } else {
            roofDegree = (List<RoofDegree>) session.getAttribute("roofDegree");
        }


        if ( shedWidth == null ) {
            shedWidth = LogicFacade.getShedWidth();
        } else {
            shedWidth = (List<ShedWidth>) session.getAttribute("shedWidth");
        }

        if ( shedLength == null ) {
            shedLength = LogicFacade.getShedLength();
        } else {
            shedLength = (List<ShedLength>) session.getAttribute("shedLength");
        }


        //Attributes to use on jsp site
        request.setAttribute("carportwidth", carportWidth);
        request.setAttribute("carportlength", carportLength);

        request.setAttribute("roofflat", roofFlat);
        request.setAttribute("roofraised", roofRaised);
        request.setAttribute("roofdegree", roofDegree);

        request.setAttribute("shedWidth", shedWidth);
        request.setAttribute("shedLength", shedLength);

        //Return value for FrontController
        return "carportcustomize";
    }
}
