package DBAccess;

import FunctionLayer.*;

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
     * @return carportWidth
     * @throws SQLException
     */
    public static List<CarportWidth> getCarportWidth() throws SQLException {
        List<CarportWidth> carportWidth = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT units FROM measurement_units WHERE c_width = '1'";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int width = rs.getInt("units");
                CarportWidth cw = new CarportWidth(width);
                carportWidth.add(cw);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }

        return carportWidth;
    }

    /**
     *
     * @return carportLength
     * @throws SQLException
     */
    public static List<CarportLength> getCarportLength() throws SQLException {
        List<CarportLength> carportLength = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT units FROM measurement_units WHERE c_length = '1'";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int length = rs.getInt("units");
                CarportLength cl = new CarportLength(length);
                carportLength.add(cl);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }

        return carportLength;
    }

    /**
     *
     * @return roofFlat
     * @throws SQLException
     */
    public static List<RoofFlat> getRoofFlat() throws SQLException {
        List<RoofFlat> roofFlat = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT roof_material FROM roof WHERE roof_type = 'fladt'";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String roofFlatOption = rs.getString("roof_material");
                RoofFlat rf = new RoofFlat(roofFlatOption);
                roofFlat.add(rf);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }

        return roofFlat;
    }

    /**
     *
     * @return roofRaised
     * @throws SQLException
     */
    public static List<RoofRaised> getRoofRaised() throws SQLException {
        List<RoofRaised> roofRaised = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT roof_material FROM roof WHERE roof_type = 'rejst'";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String roofRaisedOptions = rs.getString("roof_material");
                RoofRaised rfo = new RoofRaised(roofRaisedOptions);
                roofRaised.add(rfo);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }

        return roofRaised;
    }

    /**
     *
     * @return roofDegree
     * @throws SQLException
     */
    public static List<RoofDegree> getRoofDegree() throws SQLException {
        List<RoofDegree> roofDegree = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT pitch FROM roof_pitch";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int roofDegreeOptions = rs.getInt("pitch");
                RoofDegree rd = new RoofDegree(roofDegreeOptions);
                roofDegree.add(rd);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }

        return roofDegree;
    }

    /**
     *
     * @return shedWidth
     * @throws SQLException
     */
    public static List<ShedWidth> getShedWidth() throws SQLException {
        List<ShedWidth> shedWidth = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT units FROM measurement_units WHERE ts_width = '1'";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int shedWidthOptions = rs.getInt("units");
                ShedWidth sw = new ShedWidth(shedWidthOptions);
                shedWidth.add(sw);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }

        return shedWidth;
    }

    /**
     *
     * @return shedLength
     * @throws SQLException
     */
    public static List<ShedLength> getShedLength() throws SQLException {
        List<ShedLength> shedLength = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT units FROM measurement_units WHERE ts_length = '1'";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int shedLengthOptions = rs.getInt("units");
                ShedLength sl = new ShedLength(shedLengthOptions);
                shedLength.add(sl);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }

        return shedLength;
    }

}
