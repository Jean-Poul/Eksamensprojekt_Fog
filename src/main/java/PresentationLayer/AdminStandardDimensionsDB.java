package PresentationLayer;

import FunctionLayer.Calculation.StandardDimensions;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Class that takes requests from adminStandardDimensions.jsp and update through LogicFacade<br>
 * and thereafter return updated standard dimension list to jsp page.
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class AdminStandardDimensionsDB extends Command {

    /**
     *
     * @param request request for Http Servlet
     * @param response response for Http Servlet
     * @return adminStandardDimensions
     * @throws LoginSampleException LoginSampleException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Get parameters from adminStandardDimensions.jsp
        int standardDimensionsId = Integer.parseInt(request.getParameter("standardDimensionsId"));
        int bottom_lathspan = Integer.parseInt(request.getParameter("bottom_lathspan"));
        int bottom_laths = Integer.parseInt(request.getParameter("bottom_laths"));
        double top_lath_gap = Double.parseDouble(request.getParameter("top_lath_gap"));
        double avg_lath_span = Double.parseDouble(request.getParameter("avg_lath_span"));
        double roof_tile_length = Double.parseDouble(request.getParameter("roof_tile_length"));
        double roof_tile_width = Double.parseDouble(request.getParameter("roof_tile_width"));
        double roof_trapez_length = Double.parseDouble(request.getParameter("roof_trapez_length"));
        double roof_trapez_width = Double.parseDouble(request.getParameter("roof_trapez_width"));
        String shed_claddeing_board_dim = request.getParameter("shed_claddeing_board_dim");
        String beam_dimension_heavy = request.getParameter("beam_dimension_heavy");
        String beam_dimension_light = request.getParameter("beam_dimension_light");

        //System.out.println("update");
        //System.out.println("id:"+standardDimensionsId+" bls:"+bottom_lathspan+" bl:"+bottom_laths+" tlg:"+top_lath_gap+" als:"+avg_lath_span+" rtl:"+roof_tile_length+" rtw:"+roof_tile_width+" rtzl:"+roof_trapez_length+" rtzw:"+roof_trapez_width+" scdb:"+shed_claddeing_board_dim+" bdh:"+beam_dimension_heavy+" bdl:"+beam_dimension_light);
        LogicFacade.updateStandardDimensions(standardDimensionsId,bottom_lathspan,bottom_laths,top_lath_gap,avg_lath_span,roof_tile_length,roof_tile_width,roof_trapez_length,roof_trapez_width,shed_claddeing_board_dim,beam_dimension_heavy,beam_dimension_light);



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
