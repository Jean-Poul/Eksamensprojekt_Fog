package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.CustomerQuote;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class RejectQuote extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException {
        HttpSession session = request.getSession();

        List<CustomerQuote> rejectCustomerQuotes = LogicFacade.CustomerQuote();

        request.setAttribute("rejectcustomercuotes", rejectCustomerQuotes);

        System.out.println(rejectCustomerQuotes.get(0));

        String qouteID = request.getParameter("quoteId");

        System.out.println(qouteID);

        LogicFacade.deleteQuote(Integer.parseInt(qouteID));


        return "quoteview";
    }
}
