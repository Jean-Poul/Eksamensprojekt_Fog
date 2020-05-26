package DBAccess;

import FunctionLayer.Calculation.BeamDimensionHeavy;
import FunctionLayer.Calculation.BeamDimensionLight;
import FunctionLayer.Calculation.Item;
import FunctionLayer.Calculation.StandardDimensions;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Measurements.*;
import FunctionLayer.Tables.ItemList;
import FunctionLayer.Tables.UserProposition;
import FunctionLayer.Users.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The purpose of DataMapper is to be able to make database queries
 * <p>
 * 1. User queries<br>
 * 2. Create user quotes<br>
 * 3. Queries for dropdown in user proposition<br>
 * 4. All measurement queries for Carport Calculation<br>
 * 5. All user queries for admin page<br>
 * 6. All Admin queries for CRUD
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */

public class DataMapper {


    //##################
    // 1. User queries #
    //##################

    /**
     * Create user
     *
     * @param user User object
     * @throws LoginSampleException LoginSampleException
     */
    public static void createUser(User user) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO Users (email, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            user.setId(id);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * User login
     *
     * @param email e-mail
     * @param password password
     * @return user
     * @throws LoginSampleException LoginSampleException
     */
    public static User login(String email, String password) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT users_id, role FROM users "
                    + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                int id = rs.getInt("users_id");
                User user = new User(email, password, role);
                user.setId(id);
                return user;
            } else {
                throw new LoginSampleException("Could not validate user");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    //########################
    // 2. Create user quotes #
    //########################

    /**
     * Create user quote
     *
     * @param name name
     * @param address address
     * @param zipcodeCity zipcode and city
     * @param phone phonenumber
     * @param email e-mail
     * @param comments comments
     * @return userId
     * @throws LoginSampleException LoginSampleException
     */
    public static int createUserQuote(String name, String address, String zipcodeCity, int phone, String email, String comments) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO user_proposition (name,address,zipcodeCity,phone,email,comments) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, zipcodeCity);
            ps.setInt(4, phone);
            ps.setString(5, email);
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
     * Create quote order
     *
     * @param user_proposition_id user propposition id
     * @param oc_width carport width
     * @param oc_length carport length
     * @param ots_width shed width
     * @param ots_length shed length
     * @param roof_type roof tyoe
     * @param roof_material roof material
     * @param pitch roof pitch
     * @return orderId
     * @throws LoginSampleException LoginSampleException
     */
    public static int createQuoteOrder(int user_proposition_id, int oc_width, int oc_length, int ots_width, int ots_length, String roof_type, String roof_material, int pitch) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO orders (user_proposition_id,oc_width,oc_length,ots_width,ots_length,roof_type,roof_material,pitch) VALUES (?,?,?,?,?,?,?,?)";
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
     * Create quote orderlines
     *
     * @param orders_id order id
     * @param item_list_id item list id
     * @param quantity quantity
     * @param total_price total price
     * @throws LoginSampleException LoginSampleException
     */
    public static void createQuoteOrderline(int orders_id, int item_list_id, double quantity, double total_price) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO orderline (orders_id,item_list_id,quantity,total_price) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orders_id);
            ps.setInt(2, item_list_id);
            ps.setDouble(3, quantity);
            ps.setDouble(4, total_price);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    //###############################################
    // 3. Queries for dropdown in user proposition  #
    //###############################################

    /**
     * Get carport width measurement units for dropdown in customer quote
     *
     * @return carportWidth object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<CarportWidth> getCarportWidth() throws LoginSampleException {
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
            throw new LoginSampleException(ex.getMessage());
        }

        return carportWidth;
    }


    /**
     * Get carport length measurement units for dropdown in customer quote
     *
     * @return carportLength object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<CarportLength> getCarportLength() throws LoginSampleException {
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
            throw new LoginSampleException(ex.getMessage());
        }

        return carportLength;
    }


    /**
     * Get flat roof material for dropdown in customer quote
     *
     * @return roofFlat object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<RoofFlat> getRoofFlat() throws LoginSampleException {
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
            throw new LoginSampleException(ex.getMessage());
        }

        return roofFlat;
    }


    /**
     * Get raised roof material for dropdown in customer quote
     *
     * @return roofRaised object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<RoofRaised> getRoofRaised() throws LoginSampleException {
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
            throw new LoginSampleException(ex.getMessage());
        }

        return roofRaised;
    }


    /**
     * Get roof pitch for dropdown in customer quote
     *
     * @return roofDegree object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<RoofDegree> getRoofDegree() throws LoginSampleException {
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
            throw new LoginSampleException(ex.getMessage());
        }

        return roofDegree;
    }


    /**
     * Get shed width measurement units for dropdown in customer quote
     *
     * @return shedWidth object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<ShedWidth> getShedWidth() throws LoginSampleException {
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
            throw new LoginSampleException(ex.getMessage());
        }

        return shedWidth;
    }


    /**
     * Get shed length measurement units for dropdown in customer quote
     *
     * @return shedLength object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<ShedLength> getShedLength() throws LoginSampleException {
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
            throw new LoginSampleException(ex.getMessage());
        }

        return shedLength;
    }


    //######################################################
    // 4. All measurement queries for Carport Calculation  #
    //######################################################

    /**
     * Get list of calculated items for order
     *
     * @param orderID order id
     * @return orderItemList object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<ItemList> getAllItemList(int orderID) throws LoginSampleException {
        List<ItemList> orderItemList = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT ol.orderline_id,o.orders_id,material_type,quantity,unit,description, ol.total_price FROM user_proposition u\n" +
                    "INNER JOIN orders o on u.user_proposition_id = o.user_proposition_id\n" +
                    "INNER JOIN orderline ol on o.orders_id = ol.orders_id\n" +
                    "INNER JOIN item_list il on ol.item_list_id = il.item_list_id\n" +
                    "WHERE ol.orders_id = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderline_id = rs.getInt("orderline_id");
                int orders_id = rs.getInt("orders_id");
                String material_type = rs.getString("material_type");
                double quantity = rs.getDouble("quantity");
                String unit = rs.getString("unit");
                String description = rs.getString("description");
                double total_price = rs.getDouble("total_price");
                ItemList itemList = new ItemList(orderline_id, orders_id, material_type, quantity, unit, description, total_price);
                orderItemList.add(itemList);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return orderItemList;
    }


    /**
     * Get beam-dimension and beam-spacing for light roof
     *
     * @param rafterLength rafter length
     * @return beamDimensionLight object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<BeamDimensionLight> getBeamDimensionLight(double rafterLength) throws LoginSampleException {
        List<BeamDimensionLight> beamDimensionLight = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT beam_dimension,beam_spacing FROM rafter_spacing WHERE category = 'let' and rafter_length >= ?" +
                    "ORDER BY rafter_length ASC LIMIT 1";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setDouble(1, rafterLength);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String beamDimension = rs.getString("beam_dimension");
                double beamSpacing = rs.getDouble("beam_spacing");
                BeamDimensionLight bd = new BeamDimensionLight(beamDimension, beamSpacing);
                beamDimensionLight.add(bd);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }

        return beamDimensionLight;
    }


    /**
     * Get beam-dimension and beam-spacing for heavy roof
     *
     * @param rafterLength rafter length
     * @return beamDimensionHeavy object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<BeamDimensionHeavy> getBeamDimensionHeavy(double rafterLength) throws LoginSampleException {
        List<BeamDimensionHeavy> beamDimensionHeavy = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT beam_dimension,beam_spacing FROM rafter_spacing WHERE category = 'tung' and rafter_length >= ?" +
                    "ORDER BY rafter_length ASC LIMIT 1";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setDouble(1, rafterLength);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String beamDimension = rs.getString("beam_dimension");
                double beamSpacing = rs.getDouble("beam_spacing");
                BeamDimensionHeavy bd = new BeamDimensionHeavy(beamDimension, beamSpacing);
                beamDimensionHeavy.add(bd);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }

        return beamDimensionHeavy;
    }


    /**
     * Get HashMap of pitch and factor
     *
     * @return HashMap pitchFactor
     * @throws LoginSampleException LoginSampleException
     */
    public static Map<Integer, Double> getPitchFactor() throws LoginSampleException {
        Map<Integer, Double> pitchFactor = new HashMap<>();

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT pitch,factor FROM roof_pitch";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int pitch = rs.getInt("pitch");
                double factor = rs.getDouble("factor");
                pitchFactor.put(pitch, factor);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return pitchFactor;
    }


    /**
     * Get list of standard dimensions
     *
     * @return standardDimensions object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<StandardDimensions> getStandardDimensions() throws LoginSampleException {
        List<StandardDimensions> standardDimensions = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM standard_dimensions";
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
                StandardDimensions sd = new StandardDimensions(bottom_lathspan, bottom_laths, top_lath_gap, avg_lath_span, roof_tile_length, roof_tile_width, roof_trapez_length, roof_trapez_width, shed_claddeing_board_dim, beam_dimension_heavy, beam_dimension_light);
                standardDimensions.add(sd);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }

        return standardDimensions;
    }


    /**
     * Gets all items from DB and maps them to an array.
     *
     * @return ArrayList of all items from DB
     * @throws LoginSampleException LoginSampleException
     */
    public static List<Item> getItemList() throws LoginSampleException {
        List<Item> itemList = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM item_list";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int item_list_id = rs.getInt("item_list_id");
                String material_type = rs.getString("material_type");
                String material = rs.getString("material");
                String description = rs.getString("description");
                int amounts = rs.getInt("amounts");
                String unit = rs.getString("unit");
                double price_per_unit = rs.getDouble("price_per_unit");
                Item item = new Item(item_list_id, material_type, material, description, amounts, unit, price_per_unit);
                itemList.add(item);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }

        return itemList;
    }


    //######################################
    // 5. All user queries for admin page  #
    //######################################

    /**
     * Gets all user proposition from DB and maps them to an array.
     *
     * @return Arraylist of all userProposition
     * @throws LoginSampleException LoginSampleException
     */
    public static List<UserProposition> getAllUserPropositions() throws LoginSampleException {
        List<UserProposition> userProposition = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM user_proposition u\n" +
                    "INNER JOIN orders o on u.user_proposition_id = o.user_proposition_id";
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
                int coverage = rs.getInt("coverage");
                double offer_price = rs.getDouble("offer_price");
                double total_price = rs.getDouble("total_price");
                UserProposition up = new UserProposition(user_proposition_id, name, address, zipcodeCity, phone, email, comments, orders_id, order_date, status, carport_width, carport_length, shed_width, shed_length, roof_type, roof_material, pitch, coverage, offer_price, total_price);
                userProposition.add(up);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }

        return userProposition;
    }


    /**
     * Get one user proposition from DB and maps it to an array.
     *
     * @param userId User proposition ID
     * @return Arraylist of one userProposition
     * @throws LoginSampleException LoginSampleException
     */
    public static List<UserProposition> getUserProposition(int userId) throws LoginSampleException {
        List<UserProposition> userProposition = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM user_proposition u\n" +
                    "INNER JOIN orders o on u.user_proposition_id = o.user_proposition_id\n" +
                    "WHERE u.user_proposition_id = ?";
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
                int coverage = rs.getInt("coverage");
                double offer_price = rs.getDouble("offer_price");
                double total_price = rs.getDouble("total_price");
                UserProposition up = new UserProposition(user_proposition_id, name, address, zipcodeCity, phone, email, comments, orders_id, order_date, status, carport_width, carport_length, shed_width, shed_length, roof_type, roof_material, pitch, coverage, offer_price, total_price);
                userProposition.add(up);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }

        return userProposition;
    }


    /**
     * Delete quote from DB
     *
     * @param quoteID User proposition ID
     * @throws LoginSampleException LoginSampleException
     */
    public static void deleteQuote(int quoteID) throws LoginSampleException {

        try {
            Connection con = Connector.connection();
            String SQL = "DELETE from user_proposition WHERE user_proposition_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, quoteID);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * Update status in orders
     *
     * @param orderID order id
     * @param status status
     * @throws LoginSampleException LoginSampleException
     */
    public static void updateStatus(int orderID, String status) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE orders SET status = ? WHERE orders_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, status);
            ps.setInt(2, orderID);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * Update users info from admin-page
     *
     * @param userID user id
     * @param name name
     * @param address address
     * @param zipcodeCity zipcode and city
     * @param phone phone
     * @param email e-mail
     * @param comments comments
     * @throws LoginSampleException LoginSampleException
     */
    public static void updateQuoteUserProposition(int userID, String name, String address, String zipcodeCity, int phone, String email, String comments) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE user_proposition SET name = ?, address = ?, zipcodeCity = ?, phone = ?, email = ?, comments = ? WHERE user_proposition_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, zipcodeCity);
            ps.setInt(4, phone);
            ps.setString(5, email);
            ps.setString(6, comments);
            ps.setInt(7, userID);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * Update users order-info from admin-page
     *
     * @param orderID order id
     * @param oc_width carport width
     * @param oc_length carport length
     * @param ots_width shed width
     * @param ots_length shed length
     * @param roof_type roof type
     * @param roof_material roof material
     * @param pitch roof pitch
     * @throws LoginSampleException LoginSampleException
     */
    public static void updateQuoteOrders(int orderID, int oc_width, int oc_length, int ots_width, int ots_length, String roof_type, String roof_material, int pitch) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE orders SET oc_width = ?, oc_length = ?, ots_width = ?, ots_length = ?, roof_type = ?, roof_material = ?, pitch = ? WHERE orders_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, oc_width);
            ps.setInt(2, oc_length);
            ps.setInt(3, ots_width);
            ps.setInt(4, ots_length);
            ps.setString(5, roof_type);
            ps.setString(6, roof_material);
            ps.setInt(7, pitch);
            ps.setInt(8, orderID);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * Update quantity and price in orderline
     *
     * @param orderlineID orderline id
     * @param quantity quantity
     * @param price total price
     * @throws LoginSampleException LoginSampleException
     */
    public static void updateQuantityOrderline(int orderlineID, double quantity, double price) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE `orderline` SET `quantity` = ?, `total_price` = ? WHERE `orderline_id` = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setDouble(1, quantity);
            ps.setDouble(2, price);
            ps.setInt(3, orderlineID);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    //-----------------------------------------------
    // Queries for recalculation price with coverage
    //-----------------------------------------------

    /**
     * Returns the unique item no. by looking at order ID and the items orderLineID in the DB
     *
     * @param orderID order id
     * @param orderLineID orderline id
     * @return item number
     * @throws LoginSampleException LoginSampleException
     */
    public static int getOrderIDFromLineID(int orderID, int orderLineID) throws LoginSampleException {
        int itemNo = 0;
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM orderline WHERE orders_id = ? AND orderline_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderID);
            ps.setInt(2, orderLineID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                itemNo = rs.getInt("item_list_id");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return itemNo;
    }


    /**
     * Find a specific item price from orderline by order id and item list id
     * @param orderID order id
     * @param orderLineID orderline id
     * @return item price
     * @throws LoginSampleException LoginSampleException
     */
    public static double getOrderLinePriceFromLineID(int orderID, int orderLineID) throws LoginSampleException {
        double itemPrice = 0;
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM orderline WHERE orders_id = ? AND item_list_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderID);
            ps.setInt(2, orderLineID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                itemPrice = rs.getDouble("total_price");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return itemPrice;
    }


    /**
     * Get coverage from order id and return it
     *
     * @param orderID order id
     * @return coverage
     * @throws LoginSampleException LoginSampleException
     */
    public static int getOrderCoverage(int orderID) throws LoginSampleException {
        int coverage = 0;
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT coverage FROM orders WHERE orders_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                coverage = rs.getInt("coverage");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return coverage;
    }


    /**
     * Update coverage from a specific order
     *
     * @param coverage coverage
     * @param orderID order id
     * @throws LoginSampleException LoginSampleException
     */
    public static void updateOrderCoverage(int coverage, int orderID) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE orders SET coverage = ? WHERE orders_id =?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, coverage);
            ps.setInt(2, orderID);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * Inserts the total carport price w.and w/o tax coverage to order in DB
     *
     * @param carportTotalPriceWithTax Total price for carport
     * @param propositionID            specific order ID
     * @throws LoginSampleException    LoginSampleException
     */
    public static void insertTotalPrice(double carportTotalPriceWithTax, int propositionID) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE orders SET offer_price = ? WHERE orders_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setDouble(1, carportTotalPriceWithTax);
            ps.setInt(2, propositionID);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * Find total carport price by order id and return it
     *
     * @param orderID order id
     * @return totalCarportPrice
     * @throws LoginSampleException LoginSampleException
     */
    public static double getOrderTotalPrice(int orderID) throws LoginSampleException {
        double totalCarportPrice = 0;
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT offer_price FROM orders WHERE orders_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalCarportPrice = rs.getDouble("offer_price");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return totalCarportPrice;
    }


    /**
     * Update price with coverage by order id
     *
     * @param totalPrice total price
     * @param orderID order id
     * @throws LoginSampleException LoginSampleException
     */
    public static void setPriceWithCoverage(double totalPrice, int orderID) throws LoginSampleException {

        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE `fogdb`.`orders` SET `total_price` = ? WHERE (`orders_id` = ?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setDouble(1, totalPrice);
            ps.setInt(2, orderID);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    //########################################
    // 6. All Admin queries for CRUD
    //########################################

    //----------------------------------------
    // CRUD for Measurement Units
    //----------------------------------------

    /**
     * Get all measurement units for admin management
     *
     * @return measurementUnits object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<MeasurementUnits> getMeasurementUnits() throws LoginSampleException {
        List<MeasurementUnits> measurementUnits = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM measurement_units ORDER BY units ASC";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int measurement_units_id = rs.getInt("measurement_units_id");
                int units = rs.getInt("units");
                int c_width = rs.getInt("c_width");
                int c_length = rs.getInt("c_length");
                int ts_width = rs.getInt("ts_width");
                int ts_length = rs.getInt("ts_length");
                MeasurementUnits mu = new MeasurementUnits(measurement_units_id, units, c_width, c_length, ts_width, ts_length);
                measurementUnits.add(mu);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return measurementUnits;
    }


    /**
     * Create new measurement units for admin management
     *
     * @param units units
     * @param c_width carport width
     * @param c_length carport length
     * @param ts_width shed width
     * @param ts_length shed length
     * @throws LoginSampleException LoginSampleException
     */
    public static void createMeasurementUnits(int units, int c_width, int c_length, int ts_width, int ts_length) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO measurement_units (units,c_width,c_length,ts_width,ts_length) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, units);
            ps.setInt(2, c_width);
            ps.setInt(3, c_length);
            ps.setInt(4, ts_width);
            ps.setInt(5, ts_length);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * Update measurement units for admin management
     *
     * @param measurement_units_id measurement units id
     * @param units units
     * @param c_width carport width
     * @param c_length carport length
     * @param ts_width shed width
     * @param ts_length shed length
     * @throws LoginSampleException LoginSampleException
     */
    public static void updateMeasurementUnits(int measurement_units_id, int units, int c_width, int c_length, int ts_width, int ts_length) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE measurement_units SET units = ?, c_width = ?, c_length = ?, ts_width = ?, ts_length = ? WHERE measurement_units_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, units);
            ps.setInt(2, c_width);
            ps.setInt(3, c_length);
            ps.setInt(4, ts_width);
            ps.setInt(5, ts_length);
            ps.setInt(6, measurement_units_id);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * Delete from measurement units for admin management
     *
     * @param measurement_units_id measurement units id
     * @throws LoginSampleException LoginSampleException
     */
    public static void deleteMeasurementUnits(int measurement_units_id) throws LoginSampleException {

        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM measurement_units WHERE measurement_units_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, measurement_units_id);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    //----------------------------------------
    // CRUD for Item List
    //----------------------------------------


    /**
     * Get all items from item list for admin management
     *
     * @return itemLists object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<ItemList> getItemListAdmin() throws LoginSampleException {
        List<ItemList> itemLists = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM item_list ORDER BY material_type ASC;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int item_list_id = rs.getInt("item_list_id");
                String material_type = rs.getString("material_type");
                String material = rs.getString("material");
                String description = rs.getString("description");
                int amounts = rs.getInt("amounts");
                String unit = rs.getString("unit");
                double price_per_unit = rs.getDouble("price_per_unit");
                ItemList il = new ItemList(item_list_id,material_type,material,description,amounts,unit,price_per_unit);
                itemLists.add(il);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return itemLists;
    }


    /**
     * Create new item in item list for admin management
     *
     * @param material_type material type
     * @param material material
     * @param description description
     * @param amounts amount
     * @param unit unit
     * @param price_per_unit price per unit
     * @throws LoginSampleException LoginSampleException
     */
    public static void createItemList(String material_type, String material, String description, int amounts, String unit, double price_per_unit) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO item_list (material_type,material,description,amounts,unit,price_per_unit) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, material_type);
            ps.setString(2, material);
            ps.setString(3, description);
            ps.setInt(4, amounts);
            ps.setString(5, unit);
            ps.setDouble(6, price_per_unit);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * Update item in item list for admin management
     *
     * @param item_list_id item list id
     * @param material_type material type
     * @param material material
     * @param description description
     * @param amounts amounts
     * @param unit unit
     * @param price_per_unit price per unit
     * @throws LoginSampleException LoginSampleException
     */
    public static void updateItemList(int item_list_id, String material_type, String material, String description, int amounts, String unit, double price_per_unit) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE item_list SET material_type = ?, material = ?, description = ?, amounts = ?, unit = ?, price_per_unit = ? WHERE item_list_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, material_type);
            ps.setString(2, material);
            ps.setString(3, description);
            ps.setInt(4, amounts);
            ps.setString(5, unit);
            ps.setDouble(6, price_per_unit);
            ps.setInt(7, item_list_id);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * Delete from item list for admin management
     *
     * @param item_list_id item list id
     * @throws LoginSampleException LoginSampleException
     */
    public static void deleteItemList(int item_list_id) throws LoginSampleException {

        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM item_list WHERE item_list_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, item_list_id);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    //----------------------------------------
    // CRUD for Rafter Spacing
    //----------------------------------------


    /**
     * Get all rafter spacing for admin management
     *
     * @return rafterSpacings object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<RafterSpacing> getRafterSpacing() throws LoginSampleException {
        List<RafterSpacing> rafterSpacings = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM rafter_spacing ORDER BY category ASC, beam_dimension ASC";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int rafter_spacing_id = rs.getInt("rafter_spacing_id");
                String category = rs.getString("category");
                String beam_dimension = rs.getString("beam_dimension");
                double beam_spacing = rs.getDouble("beam_spacing");
                double rafter_length = rs.getDouble("rafter_length");
                RafterSpacing rsp = new RafterSpacing(rafter_spacing_id,category,beam_dimension,beam_spacing,rafter_length);
                rafterSpacings.add(rsp);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return rafterSpacings;
    }


    /**
     * Create new item in rafter spacing for admin management
     *
     * @param category category
     * @param beam_dimension beam dimension
     * @param beam_spacing beam spacing
     * @param rafter_length rafter length
     * @throws LoginSampleException LoginSampleException
     */
    public static void createRafterSpacing(String category, String beam_dimension, double beam_spacing, double rafter_length) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO rafter_spacing (category,beam_dimension,beam_spacing,rafter_length) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, category);
            ps.setString(2, beam_dimension);
            ps.setDouble(3, beam_spacing);
            ps.setDouble(4, rafter_length);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * Update item in rafter spacing for admin management
     *
     * @param rafter_spacing_id rafter spacing id
     * @param category category
     * @param beam_dimension beam dimension
     * @param beam_spacing beam spacing
     * @param rafter_length rafter length
     * @throws LoginSampleException LoginSampleException
     */
    public static void updateRafterSpacing(int rafter_spacing_id, String category, String beam_dimension, double beam_spacing, double rafter_length) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE rafter_spacing SET category = ?, beam_dimension = ?, beam_spacing = ?, rafter_length = ? WHERE rafter_spacing_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, category);
            ps.setString(2, beam_dimension);
            ps.setDouble(3, beam_spacing);
            ps.setDouble(4, rafter_length);
            ps.setInt(5, rafter_spacing_id);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * Delete from rafter spacing for admin management
     *
     * @param rafter_spacing_id rafter spacing id
     * @throws LoginSampleException LoginSampleException
     */
    public static void deleteRafterSpacing(int rafter_spacing_id) throws LoginSampleException {

        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM rafter_spacing WHERE rafter_spacing_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, rafter_spacing_id);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    //----------------------------------------
    // CRUD for Roof
    //----------------------------------------


    /**
     * Get all roofs for admin management
     *
     * @return roofs object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<Roof> getRoof() throws LoginSampleException {
        List<Roof> roofs = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM roof ORDER BY roof_type ASC, roof_category ASC, roof_material ASC";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int roof_id = rs.getInt("roof_id");
                String roof_type = rs.getString("roof_type");
                String roof_category = rs.getString("roof_category");
                String roof_material = rs.getString("roof_material");
                Roof r = new Roof(roof_id,roof_type,roof_category,roof_material);
                roofs.add(r);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return roofs;
    }


    /**
     * Create new roof for admin management
     *
     * @param roof_type roof type
     * @param roof_category roof category
     * @param roof_material roof material
     * @throws LoginSampleException LoginSampleException
     */
    public static void createRoof(String roof_type, String roof_category, String roof_material) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO roof (roof_type,roof_category,roof_material) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, roof_type);
            ps.setString(2, roof_category);
            ps.setString(3, roof_material);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * Update roof for admin management
     *
     * @param roof_id roof id
     * @param roof_type roof type
     * @param roof_category roof category
     * @param roof_material roof material
     * @throws LoginSampleException LoginSampleException
     */
    public static void updateRoof(int roof_id, String roof_type, String roof_category, String roof_material) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE roof SET roof_type = ?, roof_category = ?, roof_material = ? WHERE roof_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, roof_type);
            ps.setString(2, roof_category);
            ps.setString(3, roof_material);
            ps.setInt(4, roof_id);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * Delete from roof for admin management
     *
     * @param roof_id roof id
     * @throws LoginSampleException LoginSampleException
     */
    public static void deleteRoof(int roof_id) throws LoginSampleException {

        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM roof WHERE roof_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, roof_id);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    //----------------------------------------
    // CRUD for Roof pitch
    //----------------------------------------


    /**
     * Get all roof pitch and factor for admin management
     *
     * @return roofPitches object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<RoofPitch> getRoofPitch() throws LoginSampleException {
        List<RoofPitch> roofPitches = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM roof_pitch ORDER BY pitch ASC";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int roof_pitch_id = rs.getInt("roof_pitch_id");
                int pitch = rs.getInt("pitch");
                double factor = rs.getDouble("factor");
                RoofPitch rp = new RoofPitch(roof_pitch_id,pitch,factor);
                roofPitches.add(rp);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return roofPitches;
    }


    /**
     * Create new roof pitch and factor for admin management
     *
     * @param pitch pitch
     * @param factor factor
     * @throws LoginSampleException LoginSampleException
     */
    public static void createRoofPitch(int pitch, double factor) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO roof_pitch (pitch,factor) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, pitch);
            ps.setDouble(2, factor);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * Update roof pitch and factor for admin management
     *
     * @param roof_pitch_id roof pitch id
     * @param pitch pitch
     * @param factor factor
     * @throws LoginSampleException LoginSampleException
     */
    public static void updateRoofPitch(int roof_pitch_id, int pitch, double factor) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE roof_pitch SET pitch = ?, factor = ? WHERE roof_pitch_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, pitch);
            ps.setDouble(2, factor);
            ps.setInt(3, roof_pitch_id);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    /**
     * Delete from roof pitch
     *
     * @param roof_pitch_id roof pitch id
     * @throws LoginSampleException LoginSampleException
     */
    public static void deleteRoofPitch(int roof_pitch_id) throws LoginSampleException {

        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM roof_pitch WHERE roof_pitch_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, roof_pitch_id);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    //----------------------------------------
    // CRUD for Standard dimensions
    //----------------------------------------


    /**
     * Get all standard dimension for admin management
     *
     * @return standardDimensions object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<StandardDimensions> getStandardDimensionsAdmin() throws LoginSampleException {
        List<StandardDimensions> standardDimensions = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM standard_dimensions";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int standard_dimensions_id = rs.getInt("standard_dimensions_id");
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
                StandardDimensions sd = new StandardDimensions(standard_dimensions_id,bottom_lathspan,bottom_laths,top_lath_gap,avg_lath_span,roof_tile_length,roof_tile_width,roof_trapez_length,roof_trapez_width,shed_claddeing_board_dim,beam_dimension_heavy,beam_dimension_light);
                standardDimensions.add(sd);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return standardDimensions;
    }


    /**
     * Update standard dimension for admin management
     *
     * @param standard_dimensions_id standard dimensions id
     * @param bottom_lathspan bottom lath span
     * @param bottom_laths bottom laths
     * @param top_lath_gap top lath gap
     * @param avg_lath_span average lath span
     * @param roof_tile_length roof tile length
     * @param roof_tile_width roof tile width
     * @param roof_trapez_length roof trapeze length
     * @param roof_trapez_width roof trapeze width
     * @param shed_claddeing_board_dim shed cladding board dimension
     * @param beam_dimension_heavy beam dimension heavy
     * @param beam_dimension_light beam dimension light
     * @throws LoginSampleException LoginSampleException
     */
    public static void updateStandardDimensions(int standard_dimensions_id,int bottom_lathspan,int bottom_laths,double top_lath_gap,double avg_lath_span,double roof_tile_length,double roof_tile_width,double roof_trapez_length, double roof_trapez_width, String shed_claddeing_board_dim, String beam_dimension_heavy, String beam_dimension_light) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE standard_dimensions SET bottom_lathspan = ?, bottom_laths = ?, top_lath_gap = ?, avg_lath_span = ?, roof_tile_length = ?, roof_tile_width = ?, roof_trapez_length = ?, roof_trapez_width = ?, shed_claddeing_board_dim = ?, beam_dimension_heavy = ?, beam_dimension_light = ? WHERE standard_dimensions_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, bottom_lathspan);
            ps.setInt(2, bottom_laths);
            ps.setDouble(3, top_lath_gap);
            ps.setDouble(4, avg_lath_span);
            ps.setDouble(5, roof_tile_length);
            ps.setDouble(6, roof_tile_width);
            ps.setDouble(7, roof_trapez_length);
            ps.setDouble(8, roof_trapez_width);
            ps.setString(9, shed_claddeing_board_dim);
            ps.setString(10, beam_dimension_heavy);
            ps.setString(11, beam_dimension_light);
            ps.setInt(12, standard_dimensions_id);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }
}
