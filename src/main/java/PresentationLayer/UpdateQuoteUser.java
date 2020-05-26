package PresentationLayer;

import FunctionLayer.*;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Measurements.*;
import FunctionLayer.Tables.UserProposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * UpdateQuoteUser will update the currently viewed user info while updating the jsp site
 *
 */
public class UpdateQuoteUser extends Command {
    // Initialize variables to be able to update a proposition and calculate price
    private int vID = 0;
    private int qID = 0;
    private int oID = 0;
    private int phn = 0;
    private String address;
    private String comments;
    private String email;
    private String name;
    private String status;
    private String zipcode;

    /**
     *
     * @param request
     * @param response
     * @return adminQuoteView
     * @throws LoginSampleException
     * @throws ClassNotFoundException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing List with UserProposition object
        List<UserProposition> userProposition = (List<UserProposition>) session.getAttribute("userProposition");


        // Initializing Lists with measurement objects
        List<CarportWidth> carportWidth = (List<CarportWidth>) session.getAttribute("carportWidth");
        List<CarportLength> carportLength = (List<CarportLength>) session.getAttribute("carportLength");

        List<RoofFlat> roofFlat = (List<RoofFlat>) session.getAttribute("roofFlat");
        List<RoofRaised> roofRaised = (List<RoofRaised>) session.getAttribute("roofRaised");
        List<RoofDegree> roofDegree = (List<RoofDegree>) session.getAttribute("roofDegree");

        List<ShedWidth> shedWidth = (List<ShedWidth>) session.getAttribute("shedWidth");
        List<ShedLength> shedLength = (List<ShedLength>) session.getAttribute("shedLength");


        // Getting parameter to be able to update on current ID
        String viewID = request.getParameter("quoteID");


        // Getting parameters from input fields
        String quoteID = request.getParameter("quoteID");
        name = request.getParameter("name");
        address = request.getParameter("address");
        zipcode = request.getParameter("zipcode");
        String phone = request.getParameter("phone");
        email = request.getParameter("email");
        comments = request.getParameter("comments");

        String orderID = request.getParameter("orderID");
        status = request.getParameter("status");


        // Check if viewID is not empty and parse it to an int
        if (!viewID.isEmpty()) {
            vID = Integer.parseInt(viewID);
        }


        // Check if quoteID is not empty and parse it to an int
        if (!quoteID.isEmpty()) {
            qID = Integer.parseInt(quoteID);
        }


        // Check if phone is not empty and parse it to an int
        if (!phone.isEmpty()) {
            phn = Integer.parseInt(phone);
        }


        // Check if orderID is not empty and parse it to an int
        if (!orderID.isEmpty()) {
            oID = Integer.parseInt(orderID);
        }


        // Updating a user quote with parameters from jsp values
        LogicFacade.updateQuoteUserProposition(qID, name, address, zipcode, phn, email, comments);
        LogicFacade.updateStatus(oID, status);


        // Singleton for initializing an instance of UserProposition
        // if List is empty
        if (userProposition == null) {
            userProposition = LogicFacade.getUserProposition(vID);
        } else {
            userProposition = (List<UserProposition>) session.getAttribute("userProposition");
        }


        // Singletons for initializing instances of CarportWidth, CarportLength, RoofFlat, RoofRaised, RoofDegree, ShedWidth, ShedLength
        // if List is empty
        if (carportWidth == null) {
            carportWidth = LogicFacade.getCarportWidth();
        } else {
            carportWidth = (List<CarportWidth>) session.getAttribute("carportWidth");
        }

        if (carportLength == null) {
            carportLength = LogicFacade.getCarportLength();
        } else {
            carportLength = (List<CarportLength>) session.getAttribute("carportLength");
        }


        if (roofFlat == null) {
            roofFlat = LogicFacade.getRoofFlat();
        } else {
            roofFlat = (List<RoofFlat>) session.getAttribute("roofFlat");
        }

        if (roofRaised == null) {
            roofRaised = LogicFacade.getRoofRaised();
        } else {
            roofRaised = (List<RoofRaised>) session.getAttribute("roofRaised");
        }

        if (roofDegree == null) {
            roofDegree = LogicFacade.getRoofDegree();
        } else {
            roofDegree = (List<RoofDegree>) session.getAttribute("roofDegree");
        }


        if (shedWidth == null) {
            shedWidth = LogicFacade.getShedWidth();
        } else {
            shedWidth = (List<ShedWidth>) session.getAttribute("shedWidth");
        }

        if (shedLength == null) {
            shedLength = LogicFacade.getShedLength();
        } else {
            shedLength = (List<ShedLength>) session.getAttribute("shedLength");
        }


        // Attributes to use on jsp site
        request.setAttribute("userProposition", userProposition);

        request.setAttribute("carportWidth", carportWidth);
        request.setAttribute("carportLength", carportLength);

        request.setAttribute("roofFlat", roofFlat);
        request.setAttribute("roofRaised", roofRaised);
        request.setAttribute("roofDegree", roofDegree);

        request.setAttribute("shedWidth", shedWidth);
        request.setAttribute("shedLength", shedLength);


        // Return value for FrontController
        return "adminQuoteView";
    }
}
