package DBAccess;

import FunctionLayer.CarportWidth;
import FunctionLayer.RoofMeasurements;
import FunctionLayer.ShedMeasurements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will get measurements from the database to populate the select options in carportcustomize.jsp
 */
public class DimensionMapper {

    /**
     *
     * @return carportMeasurements
     * @throws SQLException
     */
    public static List<CarportWidth> getCarportWidth() throws SQLException {
        List<CarportWidth> carportMeasurements = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT units FROM measurement_units WHERE c_width = '1'";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int width = rs.getInt("units");
                CarportWidth cw = new CarportWidth(width);
                carportMeasurements.add(cw);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }

        return carportMeasurements;
    }

    /**
     *
     * @return roofMeasurements
     * @throws SQLException
     */
    public static List<RoofMeasurements> getRoof() throws SQLException {
        List<RoofMeasurements> roofMeasurements = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM ORDER BY ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("roofId");
                String roofType = rs.getString("roofType");
                String roofFlatType = rs.getString("roofFlatType");
                String raisedRoofType = rs.getString("raisedRoofType");
                int roofDegree = rs.getInt("roofDegree");
                RoofMeasurements rm = new RoofMeasurements(id, roofType, roofFlatType, raisedRoofType, roofDegree);
                roofMeasurements.add(rm);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }

        return roofMeasurements;
    }

    /**
     *
     * @return shedMeasurements
     * @throws SQLException
     */
    public static List<ShedMeasurements> getShed() throws SQLException {
        List<ShedMeasurements> shedMeasurements = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM ORDER BY ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("shedId");
                int shedWidth = rs.getInt("shedWidth");
                int shedLength = rs.getInt("shedLength");
                ShedMeasurements sm = new ShedMeasurements(id, shedWidth, shedLength);
                shedMeasurements.add(sm);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }

        return shedMeasurements;
    }

}
