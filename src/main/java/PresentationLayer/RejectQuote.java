package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 *  RejectQuote will reject and delete a given customer quote
 */
public class RejectQuote extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException {
        HttpSession session = request.getSession();

        String quoteID = request.getParameter("quoteId");

        LogicFacade.deleteQuote(Integer.parseInt(quoteID));

        return "quoteview";
    }
}
