package PresentationLayer;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Measurements.RoofPitch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminRoofPitchDB extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Get parameters from adminRoof.jsp
        int queryChoice = Integer.parseInt(request.getParameter("queryChoice"));
        String roofPitchId = request.getParameter("roofPitchId");
        String pitch = request.getParameter("pitch");
        String factor = request.getParameter("factor");


        int rpId = 0;
        int ptch = 0;
        double fctr = 0;

        if(roofPitchId != null) {
            rpId = Integer.parseInt(roofPitchId);
        }

        if(pitch != null) {
            ptch = Integer.parseInt(pitch);
        }

        if(factor != null) {
            fctr = Double.parseDouble(factor);
        }

        switch (queryChoice) {
            case 1: // insert
                //System.out.println("insert");
                //System.out.println("p:"+ptch+" f:"+fctr);
                LogicFacade.createRoofPitch(ptch,fctr);
                break;
            case 2: // update
                //System.out.println("update");
                //System.out.println("id:"+rpId+" rt:"+" p:"+ptch+" f:"+fctr);
                LogicFacade.updateRoofPitch(rpId,ptch,fctr);
                break;
            case 3: // delete
                //System.out.println("delete");
                //System.out.println("id:"+rpId);
                LogicFacade.deleteRoofPitch(rpId);
                break;
            default:

        }


        // Initializing List with Roof Pitch object
        List<RoofPitch> roofPitches = (List<RoofPitch>) session.getAttribute("roofPitch");


        // Singleton to initialize an instance of Roof Pitch
        // if List is empty
        if (roofPitches == null) {
            roofPitches = LogicFacade.getRoofPitch();
        } else {
            roofPitches = (List<RoofPitch>) session.getAttribute("roofPitch");
        }


        // Attribute to use on jsp site
        request.setAttribute("roofPitches", roofPitches);
        return "adminRoofPitch";
    }
}
