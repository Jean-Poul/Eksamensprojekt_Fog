package PresentationLayer;

import FunctionLayer.CustomerQuote;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * Quoteview is used to initialize variables which are used on quoteview.jsp
 */
public class QuoteView extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException {
        List<CustomerQuote> rejectCustomerQuotes = LogicFacade.getCustomerQuoteID();

        request.setAttribute("rejectcustomerquotes", rejectCustomerQuotes);

        return "quoteview";
    }
}
