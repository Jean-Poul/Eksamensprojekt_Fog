package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.UserProposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public class AdminRejectQuote extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();

        String quoteID = request.getParameter("quoteID");

        LogicFacade.deleteQuote(Integer.parseInt(quoteID));

        List<UserProposition> userProposition = LogicFacade.getAllUserPropositions();
        request.setAttribute("userpropositions", userProposition);

        return "adminpage";
    }
}
