package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class Carportcustomize extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException {
        HttpSession session = request.getSession();

        List<CarportWidth> carportWidth = (List<CarportWidth>) session.getAttribute("carportWidth");
        List<RoofMeasurements> roofMeasurements = (List<RoofMeasurements>) session.getAttribute("roofMeasurements");
        List<ShedMeasurements> shedMeasurements = (List<ShedMeasurements>) session.getAttribute("shedMeasurements");


        if ( carportMeasurements == null ) {
            carportMeasurements = LogicFacade.getCarportWidth();
        } else {
            carportMeasurements = (List<CarportMeasurements>) session.getAttribute("carportMeasurements");
        }

   /*     if ( roofMeasurements == null ) {
            roofMeasurements = LogicFacade.getRoof();
        } else {
            roofMeasurements = (List<RoofMeasurements>) session.getAttribute("roofMeasurements");
        }

        if ( shedMeasurements == null ) {
            shedMeasurements = LogicFacade.getShed();
        } else {
            shedMeasurements = (List<ShedMeasurements>) session.getAttribute("shedMeasurements");
        }*/

        request.setAttribute("carport", carportMeasurements);
        request.setAttribute("roof", roofMeasurements);
        request.setAttribute("shed", shedMeasurements);
        System.out.println(carportMeasurements);

        return "carportcustomize";
    }
}
