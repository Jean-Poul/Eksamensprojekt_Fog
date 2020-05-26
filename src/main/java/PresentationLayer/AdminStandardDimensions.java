package PresentationLayer;

import FunctionLayer.Calculation.StandardDimensions;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminStandardDimensions extends Command {
    /**
     *
     * @return "adminStandardDimensions"
     * @throws LoginSampleException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing List with Standard Dimensions object
        List<StandardDimensions> standardDimensions = (List<StandardDimensions>) session.getAttribute("standardDimensions");


        // Singleton to initialize an instance of Standard Dimension
        // if List is empty
        if (standardDimensions == null) {
            standardDimensions = LogicFacade.getStandardDimensionsAdmin();
        } else {
            standardDimensions = (List<StandardDimensions>) session.getAttribute("standardDimensions");
        }


        // Attribute to use on jsp site
        request.setAttribute("standardDimensions", standardDimensions);

        return "adminStandardDimensions";
    }
}
