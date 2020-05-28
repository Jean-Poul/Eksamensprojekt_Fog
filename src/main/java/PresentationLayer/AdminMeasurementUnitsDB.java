package PresentationLayer;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Measurements.MeasurementUnits;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Class that takes requests from adminMeasurementUnits.jsp and choose witch query through LogicFacade to<br>
 * populate, and thereafter return updated measurement list to jsp page.
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class AdminMeasurementUnitsDB extends Command {

    /**
     * Get request from jsp page and populate data to DB and return new measurementUnit list
     *
     * @param request request for Http Servlet
     * @param response response for Http Servlet
     * @return adminMeasurementUnits
     * @throws LoginSampleException LoginSampleException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Get parameters from adminMeasurementUnit.jsp
        int queryChoice = Integer.parseInt(request.getParameter("queryChoice"));
        String measurementUnitId = request.getParameter("measurementUnitId");
        String units = request.getParameter("units");
        String c_width = request.getParameter("c_width");
        String c_length = request.getParameter("c_length");
        String ts_width = request.getParameter("ts_width");
        String ts_length = request.getParameter("ts_length");

        int mUnitId = 0;
        int mUnits = 0;
        int cWidth = 0;
        int cLength = 0;
        int sWidth = 0;
        int sLength = 0;

        if(measurementUnitId != null) {
            mUnitId = Integer.parseInt(measurementUnitId);
        }

        // check if units is not empty and parse to int
        if(units != null) {
            mUnits = Integer.parseInt(units);
        }

        // check if c_width is not null and parce to int
        if(c_width != null) {
            cWidth = Integer.parseInt(c_width);
        }

        if(c_length != null) {
            cLength = Integer.parseInt(c_length);
        }

        if(ts_width != null) {
            sWidth = Integer.parseInt(ts_width);
        }

        if(ts_length != null) {
            sLength = Integer.parseInt(ts_length);
        }

        switch (queryChoice) {
            case 1: // insert
                //System.out.println("insert");
                //System.out.println("u:"+mUnits+" cw:"+cWidth+" cl:"+cLength+" sw:"+sWidth+" sl:"+sLength);
                LogicFacade.createMeasurementUnits(mUnits,cWidth,cLength,sWidth,sLength);
                break;
            case 2: // update
                //System.out.println("update");
                //System.out.println("id:"+measurementUnitId+" cw:"+cWidth+" cl:"+cLength+" sw:"+sWidth+" sl:"+sLength);
                LogicFacade.updateMeasurementUnits(mUnitId,mUnits,cWidth,cLength,sWidth,sLength);
                break;
            case 3: // delete
                //System.out.println("delete");
                //System.out.println("id:"+measurementUnitId);
                LogicFacade.deleteMeasurementUnits(mUnitId);
                break;
            default:

        }


        // Initializing List with MeasurementUnits object
        List<MeasurementUnits> measurementUnits = (List<MeasurementUnits>) session.getAttribute("measurementUnits");


        // Singleton to initialize an instance of UserProposition
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
