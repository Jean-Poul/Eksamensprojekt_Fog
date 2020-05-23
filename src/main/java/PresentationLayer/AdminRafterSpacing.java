package PresentationLayer;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Measurements.RafterSpacing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminRafterSpacing extends Command {
    /**
     *
     * @return adminRafterSpacing
     * @throws LoginSampleException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing List with RafterSpacing object
        List<RafterSpacing> rafterSpacing = (List<RafterSpacing>) session.getAttribute("rafterSpacing");


        // Singleton to initialize an instance of RafterSpacing
        // if List is empty
        if (rafterSpacing == null) {
            rafterSpacing = LogicFacade.getRafterSpacing();
        } else {
            rafterSpacing = (List<RafterSpacing>) session.getAttribute("rafterSpacing");
        }


        // Attribute to use on jsp site
        request.setAttribute("rafterSpacing", rafterSpacing);


        return "adminRafterSpacing";
    }
}
