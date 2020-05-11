package DBAccess;

import FunctionLayer.*;
import com.mysql.cj.protocol.Resultset;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The purpose of DataMapper is to be able to make database queries
 *
 *  1. User
 *  2. Create user quotes
 *  3. Queries for Quote view
 *  4. Queries for Quote update
 *  5. Select option
 */

public class DataMapper {

    //##################
    //1. User queries
    //##################

    public static void createUser( User user ) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO Users (email, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            ps.setString( 1, user.getEmail() );
            ps.setString( 2, user.getPassword() );
            ps.setString( 3, user.getRole() );
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt( 1 );
            user.setId( id );
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }

    public static User login( String email, String password ) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT users_id, role FROM users "
                    + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setString( 1, email );
            ps.setString( 2, password );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                String role = rs.getString( "role" );
                int id = rs.getInt( "users_id" );
                User user = new User( email, password, role );
                user.setId( id );
                return user;
            } else {
                throw new LoginSampleException( "Could not validate user" );
            }
        } catch ( ClassNotFoundException | SQLException ex ) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    //##############################
    //2. Create user quote queries
    //##############################

    /**
     *
     * @param name
     * @param address
     * @param zipcodeCity
     * @param phone
     * @param email
     * @param comments
     * @return userId
     * @throws LoginSampleException
     */
    public static int createUserQuote(String name,String address,String zipcodeCity,int phone, String email,String comments) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO user_proposition (name,address,zipcodeCity,phone,email,comments) VALUES (?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3,zipcodeCity);
            ps.setInt(4,phone);
            ps.setString(5,email);
            ps.setString(6, comments);
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int userId = ids.getInt(1);
            return userId;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    /**
     *
     * @param user_proposition_id
     * @param oc_width
     * @param oc_length
     * @param ots_width
     * @param ots_length
     * @param roof_type
     * @param roof_material
     * @param pitch
     * @return orderId
     * @throws LoginSampleException
     */
    public static int createQuoteOrder(int user_proposition_id,int oc_width,int oc_length,int ots_width, int ots_length,String roof_type,String roof_material,int pitch) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO orders (user_proposition_id,oc_width,oc_length,ots_width,ots_length,roof_type,roof_material,pitch) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, user_proposition_id);
            ps.setInt(2, oc_width);
            ps.setInt(3, oc_length);
            ps.setInt(4, ots_width);
            ps.setInt(5, ots_length);
            ps.setString(6, roof_type);
            ps.setString(7, roof_material);
            ps.setInt(8, pitch);
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int orderId = ids.getInt(1);
            return orderId;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    /**
     *
     * @param orders_id
     * @param item_list_id
     * @param quantity
     * @param total_price
     * @throws LoginSampleException
     */
    public static void createQuoteOrderline(int orders_id, int item_list_id, int quantity, double total_price) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO orderline (orders_id,item_list_id,quantity,total_price) VALUES (?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orders_id);
            ps.setInt(2, item_list_id);
            ps.setInt(3, quantity);
            ps.setDouble(4, total_price);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    //##############################
    //3. Queries for Quote view
    //##############################

    /**
     *
     * @param quoteID
     * @throws SQLException
     */
    public static void deleteQuote(int quoteID) throws SQLException {

        try{
            Connection con = Connector.connection();
            String SQL = "DELETE from user_proposition WHERE user_proposition_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, quoteID);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex ) {
            throw new SQLException( ex.getMessage() );
        }
    }

    /**
     * Gets all items from DB and maps them to an array.
     * @return ArrayList of all items from DB
     * @throws SQLException
     */
    public static List<Item> getItemList() throws SQLException {
        List<Item> itemList = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            con = Connector.connection();
            String SQL = "SELECT * FROM item_list";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int item_list_id = rs.getInt("item_list_id");
                String material_type = rs.getString("material_type");
                String material = rs.getString("material");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                String unit = rs.getString("unit");
                double price_per_unit = rs.getDouble("price_per_unit");
                Item item = new Item(item_list_id, material_type, material, description, quantity, unit, price_per_unit);
                itemList.add(item);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    /**
     *
     * @param userId
     * @return userProposition
     * @throws SQLException
     */
    public static List<UserProposition> getUserProposition(int userId) throws SQLException {
        List<UserProposition> userProposition = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM user_proposition u\n" +
                    "INNER JOIN orders o on u.user_proposition_id = o.user_proposition_id\n" +
                    "WHERE u.user_proposition_id = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int user_proposition_id = rs.getInt("user_proposition_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String zipcodeCity = rs.getString("zipcodeCity");
                int phone = rs.getInt("phone");
                String email = rs.getString("email");
                String comments = rs.getString("comments");
                int orders_id = rs.getInt("orders_id");
                String order_date = rs.getString("order_date");
                String status = rs.getString("status");
                int carport_width = rs.getInt("oc_width");
                int carport_length = rs.getInt("oc_length");
                int shed_width = rs.getInt("ots_width");
                int shed_length = rs.getInt("ots_length");
                String roof_type = rs.getString("roof_type");
                String roof_material = rs.getString("roof_material");
                int pitch = rs.getInt("pitch");
                UserProposition up = new UserProposition(user_proposition_id,name,address,zipcodeCity,phone,email,comments,orders_id,order_date,status,carport_width,carport_length,shed_width,shed_length,roof_type,roof_material,pitch);
                userProposition.add(up);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }

        return userProposition;
    }

    /**
     *
     * @return userProposition
     * @throws SQLException
     */
    public static List<UserProposition> getAllUserPropositions() throws SQLException {
        List<UserProposition> userProposition = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM user_proposition u\n" +
                    "INNER JOIN orders o on u.user_proposition_id = o.user_proposition_id;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int user_proposition_id = rs.getInt("user_proposition_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String zipcodeCity = rs.getString("zipcodeCity");
                int phone = rs.getInt("phone");
                String email = rs.getString("email");
                String comments = rs.getString("comments");
                int orders_id = rs.getInt("orders_id");
                String order_date = rs.getString("order_date");
                String status = rs.getString("status");
                int carport_width = rs.getInt("oc_width");
                int carport_length = rs.getInt("oc_length");
                int shed_width = rs.getInt("ots_width");
                int shed_length = rs.getInt("ots_length");
                String roof_type = rs.getString("roof_type");
                String roof_material = rs.getString("roof_material");
                int pitch = rs.getInt("pitch");
                UserProposition up = new UserProposition(user_proposition_id,name,address,zipcodeCity,phone,email,comments,orders_id,order_date,status,carport_width,carport_length,shed_width,shed_length,roof_type,roof_material,pitch);
                userProposition.add(up);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }

        return userProposition;
    }

    //#############################
    //4. Queries for Quote update
    //#############################

    public static void updateStatus(int orderID, String status) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE orders SET status = ? WHERE orders_id = ?;";
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setString(1, status);
            ps.setInt(2, orderID);
            ps.executeUpdate();

        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }

    public static void updateQuoteUserProposition(int userID, String name, String address, String zipcodeCity, int phone, String email, String comments) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE user_proposition SET name = ?, address = ?, zipcodeCity = ?, phone = ?, email = ?, comments = ? WHERE user_proposition_id = ?;";
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, zipcodeCity);
            ps.setInt(4, phone);
            ps.setString(5, email);
            ps.setString(6, comments);
            ps.setInt(7, userID);
            ps.executeUpdate();

        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }

    public static void updateQuoteOrders(int orderID, int oc_width, int oc_length, int ots_width, int ots_length, String roof_type, String roof_material, int pitch) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE orders SET oc_width = ?, oc_length = ?, ots_width = ?, ots_length = ?, roof_type = ?, roof_material = ?, pitch = ? WHERE orders_id = ?;";
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setInt(1, oc_width);
            ps.setInt(2, oc_length);
            ps.setInt(3, ots_width);
            ps.setInt(4, ots_length);
            ps.setString(5, roof_type);
            ps.setString(6, roof_material);
            ps.setInt(7, pitch);
            ps.setInt(8, orderID);
            ps.executeUpdate();

        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }


    //#############################
    //5. Select option queries
    //#############################

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



    /**
     *
     * @param rafterLength
     * @return beam-dimension and beam-spacing for light roof
     * @throws SQLException
     */
    public static List<BeamDimensionLight> getBeamDimensionLight(double rafterLength) throws SQLException {
        List<BeamDimensionLight> beamDimensionLight = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT beam_dimension,beam_spacing FROM rafter_spacing WHERE category = 'let' and rafter_length >= ?" +
                    "ORDER BY rafter_length ASC LIMIT 1;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setDouble(1, rafterLength);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String beamDimension = rs.getString("beam_dimension");
                double beamSpacing = rs.getDouble("beam_spacing");
                BeamDimensionLight bd = new BeamDimensionLight(beamDimension,beamSpacing);
                beamDimensionLight.add(bd);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }

        return beamDimensionLight;
    }

    /**
     *
     * @param rafterLength
     * @return beam-dimension and beam-spacing for heavy roof
     * @throws SQLException
     */
    public static List<BeamDimensionHeavy> getBeamDimensionHeavy(double rafterLength) throws SQLException {
        List<BeamDimensionHeavy> beamDimensionHeavy = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT beam_dimension,beam_spacing FROM rafter_spacing WHERE category = 'tung' and rafter_length >= ?" +
                    "ORDER BY rafter_length ASC LIMIT 1;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setDouble(1, rafterLength);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String beamDimension = rs.getString("beam_dimension");
                double beamSpacing = rs.getDouble("beam_spacing");
                BeamDimensionHeavy bd = new BeamDimensionHeavy(beamDimension,beamSpacing);
                beamDimensionHeavy.add(bd);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }

        return beamDimensionHeavy;
    }

    /**
     *
     * @return HashMap of pitch and factor
     * @throws SQLException
     */
    public static Map<Integer, Double> getPitchFactor() throws SQLException {
        Map<Integer, Double> pitchFactor = new HashMap<>();

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT pitch,factor FROM roof_pitch;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int pitch = rs.getInt("pitch");
                double factor = rs.getDouble("factor");
                pitchFactor.put(pitch,factor);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
        return pitchFactor;
    }

    /**
     *
     * @return List of standard dimensions
     * @throws SQLException
     */
    public static List<StandardDimensions> getStandardDimensions() throws SQLException {
        List<StandardDimensions> standardDimensions = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM standard_dimensions;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bottom_lathspan = rs.getInt("bottom_lathspan");
                int bottom_laths = rs.getInt("bottom_laths");
                double top_lath_gap = rs.getDouble("top_lath_gap");
                double avg_lath_span = rs.getDouble("avg_lath_span");
                double roof_tile_length = rs.getDouble("roof_tile_length");
                double roof_tile_width = rs.getDouble("roof_tile_width");
                double roof_trapez_length = rs.getDouble("roof_trapez_length");
                double roof_trapez_width = rs.getDouble("roof_trapez_width");
                String shed_claddeing_board_dim = rs.getString("shed_claddeing_board_dim");
                String beam_dimension_heavy = rs.getString("beam_dimension_heavy");
                String beam_dimension_light = rs.getString("beam_dimension_light");
                StandardDimensions sd = new StandardDimensions(bottom_lathspan,bottom_laths,top_lath_gap,avg_lath_span,roof_tile_length,roof_tile_width,roof_trapez_length,roof_trapez_width,shed_claddeing_board_dim,beam_dimension_heavy,beam_dimension_light);
                standardDimensions.add(sd);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException(ex.getMessage());
        }

        return standardDimensions;
    }

}
