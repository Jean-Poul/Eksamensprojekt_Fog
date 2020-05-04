package PresentationLayer;

import FunctionLayer.CarportCalculation;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarportCalcPage extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {



        /**
         * 1. Hent alle user-input fra carportcustomerize.jsp til variabler
         * 2. Lav if/else eller switch-case og brug tag-type til at finde users valg
         * 3. Indsæt først user info til createUserQuote igennem logicFacade til DataMapper
         *    som returnere users_id
         * 4. Indsæt derefter alle de valgte mål samt user_id fra user til createQuoteOrder
         *    igennem logicFacede til DataMapper som returnere order_id
         * 5. ?????? lav beregning og indsæt de enkelte linier sammen med order_id til
         *    createQuoteOrderline ??????
         * 6. return til 'Tak for ordreforspørgelse' jsp-side
         */

        // User info
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String zipcodeCity = request.getParameter("zipcodeCity");
        int telephone = Integer.parseInt(request.getParameter("telephone"));
        String email = request.getParameter("email");
        String comments = request.getParameter("comments");

        // Carport measurements
        int carportWidth = Integer.parseInt(request.getParameter("carportWidth"));
        int carportLength = Integer.parseInt(request.getParameter("carportLength"));
        String roofFlat = request.getParameter("roofFlat");
        String roofRaised = request.getParameter("roofRaised");
        int roofOptionDegrees = Integer.parseInt(request.getParameter("roofOptionDegrees"));
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));

        // Roof option
        int roofOption = Integer.parseInt(request.getParameter("roofOption"));

        // insert user proposition and return userId
        int userId = LogicFacade.createUserQuote(name,address,zipcodeCity,telephone,email,comments);

        // initialize variables
        String roofType;
        int pitch;
        int orderId;

        // insert into order using switch case to choose between flat or raised roof
        switch (roofOption) {
            case 0:
                pitch = 0;
                roofType = "fladt";
                orderId = LogicFacade.createQuoteOrder(userId,carportWidth,carportLength,shedWidth,shedLength,roofType,roofFlat,pitch);
                break;
            case 1:
                roofType = "rejst";
                orderId = LogicFacade.createQuoteOrder(userId,carportWidth,carportLength,shedWidth,shedLength,roofType,roofRaised,roofOptionDegrees);
                break;
        }


        return "index";
    }
}
