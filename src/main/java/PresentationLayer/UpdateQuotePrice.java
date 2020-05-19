package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateQuotePrice extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {

        HttpSession session = request.getSession();

        int oID = 0;
        int qCov = 0;

        //Get the order ID
        String orderID = request.getParameter("orderID");

        // Check if orderID is not empty and parse it to an int
        if (!orderID.isEmpty()) {
            oID = Integer.parseInt(orderID);
        }

        //Get the changed quote coverage
        String quoteCoverage = request.getParameter("quoteCoverage");

        // Check if quote coverage is not empty and parse it to an int
        if(!quoteCoverage.isEmpty()){
            qCov = Integer.parseInt(quoteCoverage);
        }

        //Set the changed quote coverage
        LogicFacade.updateOrderCoverage(qCov, oID);

        return "quoteview";
    }
}
