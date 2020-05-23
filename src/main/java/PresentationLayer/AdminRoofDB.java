package PresentationLayer;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Measurements.Roof;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminRoofDB extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Get parameters from adminRoof.jsp
        int queryChoice = Integer.parseInt(request.getParameter("queryChoice"));
        String roofId = request.getParameter("roofId");
        String roof_type = request.getParameter("roof_type");
        String roof_category = request.getParameter("roof_category");
        String roof_material = request.getParameter("roof_material");

        int rId = 0;

        if(roofId != null) {
            rId = Integer.parseInt(roofId);
        }

        switch (queryChoice) {
            case 1: // insert
                //System.out.println("insert");
                //System.out.println("rt:"+roof_type+" rc:"+roof_category+" rm:"+roof_material);
                LogicFacade.createRoof(roof_type,roof_category,roof_material);
                break;
            case 2: // update
                //System.out.println("update");
                //System.out.println("id:"+rId+" rt:"+roof_type+" rc:"+roof_category+" rm:"+roof_material);
                LogicFacade.updateRoof(rId,roof_type,roof_category,roof_material);
                break;
            case 3: // delete
                //System.out.println("delete");
                //System.out.println("id:"+rId);
                LogicFacade.deleteRoof(rId);
                break;
            default:

        }


        // Initializing List with Roof object
        List<Roof> roofs = (List<Roof>) session.getAttribute("roof");


        // Singleton to initialize an instance of Roof
        // if List is empty
        if (roofs == null) {
            roofs = LogicFacade.getRoof();
        } else {
            roofs = (List<Roof>) session.getAttribute("roof");
        }


        // Attribute to use on jsp site
        request.setAttribute("roofs", roofs);


        return "adminRoof";
    }
}
