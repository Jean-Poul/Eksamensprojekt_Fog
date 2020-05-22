package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Measurements.MeasurementUnits;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminMeasurementUnits extends Command {
    /**
     *
     * @return adminMeasurementUnits
     * @throws LoginSampleException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing List with MeasurementUnits object
        List<MeasurementUnits> measurementUnits = (List<MeasurementUnits>) session.getAttribute("measurementUnits");


        // Singleton to initialize an instance of MeasurementUnits
        // if List is empty
        if (measurementUnits == null) {
            measurementUnits = LogicFacade.getMeasurementUnits();
        } else {
            measurementUnits = (List<MeasurementUnits>) session.getAttribute("measurementUnits");
        }


        // Attribute to use on jsp site
        request.setAttribute("measurementUnits", measurementUnits);



        return "adminMeasurementUnits";
    }
}
