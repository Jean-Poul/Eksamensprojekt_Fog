package PresentationLayer;

import FunctionLayer.Calculation.StandardDimensions;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Class that gets a List of all standard dimensions from database and sets attribute for<br>
 * view on adminStandardDimensions.jsp
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class AdminStandardDimensions extends Command {

    /**
     * Get standard dimensions from database and set attribute for jsp page
     *
     * @param request request for Http Servlet
     * @param response response for Http Servlet
     * @return "adminStandardDimensions"
     * @throws LoginSampleException LoginSampleException
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
