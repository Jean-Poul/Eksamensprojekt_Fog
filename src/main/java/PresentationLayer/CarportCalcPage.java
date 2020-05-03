package PresentationLayer;

import FunctionLayer.CarportCalculation;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarportCalcPage extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        CarportCalculation c = new CarportCalculation();

        /**
         * 1. Hent alle user-input fra carportcustomerize.jsp til variabler
         * 2. Lav if/else eller switch-case og brug tag-type til at finde users valg
         * 3. Indsæt først user info til createUserQuote igennem logicFacade til DataMapper
         *    som returnere users_id
         * 4. Indsæt derefter alle de valgte mål samt user_id fra user til createQuoteOrder
         *    igennem logicFacede til DataMapper som returnere order_id
         * 5. ?????? lav beregning og indsæt de enkelte linier sammen med order_id til
         *    createQuoteOrderline ??????
         * 6. return til 'Tak for ordreforspøgelse' jsp-side
         */

        return "index";
    }
}
