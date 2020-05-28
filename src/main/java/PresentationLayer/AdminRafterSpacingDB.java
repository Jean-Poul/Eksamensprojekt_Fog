package PresentationLayer;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Measurements.RafterSpacing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Class that takes requests from adminRafterSpacing.jsp and choose witch query through LogicFacade to<br>
 * populate, and thereafter return updated rafterSpacing list to jsp page.
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class AdminRafterSpacingDB extends Command {

    /**
     * Get request from jsp page and populate data to DB and return new rafterSpacing list
     *
     * @param request request for Http Servlet
     * @param response response for Http Servlet
     * @return adminRafterSpacing
     * @throws LoginSampleException LoginSampleException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Get parameters from adminRafterSpacing.jsp
        int queryChoice = Integer.parseInt(request.getParameter("queryChoice"));
        String rafterSpacingId = request.getParameter("rafterSpacingId");
        String category = request.getParameter("category");
        String beam_dimension = request.getParameter("beam_dimension");
        String beam_spacing = request.getParameter("beam_spacing");
        String rafter_length = request.getParameter("rafter_length");

        int raftSpaceId = 0;
        double beamSpacing = 0;
        double rafterLength = 0;

        if(rafterSpacingId != null) {
            raftSpaceId = Integer.parseInt(rafterSpacingId);
        }

        if(beam_spacing != null) {
            beamSpacing = Double.parseDouble(beam_spacing);
        }

        if(rafter_length != null) {
            rafterLength = Double.parseDouble(rafter_length);
        }

        switch (queryChoice) {
            case 1: // insert
                //System.out.println("insert");
                //System.out.println("c:"+category+" bd:"+beam_dimension+" bs:"+beamSpacing+" rl:"+rafterLength);
                LogicFacade.createRafterSpacing(category,beam_dimension,beamSpacing,rafterLength);
                break;
            case 2: // update
                //System.out.println("update");
                //System.out.println("id:"+raftSpaceId+" c:"+category+" bd:"+beam_dimension+" bs:"+beamSpacing+" rl:"+rafterLength);
                LogicFacade.updateRafterSpacing(raftSpaceId,category,beam_dimension,beamSpacing,rafterLength);
                break;
            case 3: // delete
                //System.out.println("delete");
                //System.out.println("id:"+raftSpaceId);
                LogicFacade.deleteRafterSpacing(raftSpaceId);
                break;
            default:

        }


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
