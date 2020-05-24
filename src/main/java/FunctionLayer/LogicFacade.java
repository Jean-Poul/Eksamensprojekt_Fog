package FunctionLayer;

import DBAccess.DataMapper;
import FunctionLayer.Calculation.BeamDimensionHeavy;
import FunctionLayer.Calculation.BeamDimensionLight;
import FunctionLayer.Calculation.Item;
import FunctionLayer.Calculation.StandardDimensions;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Measurements.*;
import FunctionLayer.Tables.ItemList;
import FunctionLayer.Measurements.RoofRaised;
import FunctionLayer.Tables.UserProposition;
import FunctionLayer.Users.User;

import java.util.List;
import java.util.Map;

/**
 * The purpose of LogicFacade is to call Mapper classes safely without doing it directly from other methods in other classes
 */
public class LogicFacade {

    //##############
    // User calls ##
    //##############

    /**
     * @param email
     * @param password
     * @return UserMapper.login(email, password)
     * @throws LoginSampleException
     */
    public static User login(String email, String password) throws LoginSampleException {
        return DataMapper.login(email, password);
    }

    /**
     * @param email
     * @param password
     * @return user
     * @throws LoginSampleException
     */
    public static User createUser(String email, String password) throws LoginSampleException {
        User user = new User(email, password, "customer");
        DataMapper.createUser(user);
        return user;
    }

    //####################
    // User quote calls ##
    //####################

    /**
     * @param name
     * @param address
     * @param zipcodeCity
     * @param phone
     * @param email
     * @param comments
     * @return userId
     * @throws LoginSampleException
     */
    public static int createUserQuote(String name, String address, String zipcodeCity, int phone, String email, String comments) throws LoginSampleException {
        return DataMapper.createUserQuote(name, address, zipcodeCity, phone, email, comments);
    }

    /**
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
    public static int createQuoteOrder(int user_proposition_id, int oc_width, int oc_length, int ots_width, int ots_length, String roof_type, String roof_material, int pitch) throws LoginSampleException {
        return DataMapper.createQuoteOrder(user_proposition_id, oc_width, oc_length, ots_width, ots_length, roof_type, roof_material, pitch);
    }

    /**
     * @param orders_id
     * @param item_list_id
     * @param quantity
     * @param total_price
     * @throws LoginSampleException
     */
    public static void createQuoteOrderline(int orders_id, int item_list_id, double quantity, double total_price) throws LoginSampleException {
        DataMapper.createQuoteOrderline(orders_id, item_list_id, quantity, total_price);
    }

    /**
     * @param quoteID
     * @throws LoginSampleException
     */
    public static void deleteQuote(int quoteID) throws LoginSampleException {
        DataMapper.deleteQuote(quoteID);
    }

    /**
     * @param userId
     * @return DataMapper.getUserPropositions(userId)
     * @throws LoginSampleException
     */
    public static List<UserProposition> getUserProposition(int userId) throws LoginSampleException {
        return DataMapper.getUserProposition(userId);
    }

    /**
     * @return DataMapper.getAllUserPropositions()
     * @throws LoginSampleException
     */
    public static List<UserProposition> getAllUserPropositions() throws LoginSampleException {
        return DataMapper.getAllUserPropositions();
    }

    /**
     * @param orderID
     * @return List of calculated items for order
     * @throws LoginSampleException
     */
    public static List<ItemList> getAllItemList(int orderID) throws LoginSampleException {
        return DataMapper.getAllItemList(orderID);
    }

    /**
     * @param orderID
     * @param status
     * @throws LoginSampleException
     */
    public static void updateStatus(int orderID, String status) throws LoginSampleException {
        DataMapper.updateStatus(orderID, status);
    }

    /**
     * @param userID
     * @param name
     * @param address
     * @param zipcodeCity
     * @param phone
     * @param email
     * @param comments
     * @throws LoginSampleException
     */
    public static void updateQuoteUserProposition(int userID, String name, String address, String zipcodeCity, int phone, String email, String comments) throws LoginSampleException {
        DataMapper.updateQuoteUserProposition(userID, name, address, zipcodeCity, phone, email, comments);
    }

    /**
     * @param orderID
     * @param oc_width
     * @param oc_length
     * @param ots_width
     * @param ots_length
     * @param roof_type
     * @param roof_material
     * @param pitch
     * @throws LoginSampleException
     */
    public static void updateQuoteOrders(int orderID, int oc_width, int oc_length, int ots_width, int ots_length, String roof_type, String roof_material, int pitch) throws LoginSampleException {
        DataMapper.updateQuoteOrders(orderID, oc_width, oc_length, ots_width, ots_length, roof_type, roof_material, pitch);
    }

    /**
     * Update quantity in orderline
     *
     * @param orderlineID
     * @param quantity
     * @throws LoginSampleException
     */
    public static void updateQuantityOrderline(int orderlineID, double quantity) throws LoginSampleException {
        DataMapper.updateQuantityOrderline(orderlineID, quantity);
    }

    /**
     * Returns the coverage from database
     * @param orderID
     * @return
     * @throws LoginSampleException
     */
    public static int getOrderCoverage(int orderID) throws LoginSampleException {
        return DataMapper.getOrderCoverage(orderID);
    }

    public static void updateOrderCoverage(int coverage, int orderID) throws LoginSampleException {
        DataMapper.updateOrderCoverage(coverage, orderID);
    }

    /**
     * Inserts the total carport price w. tax and w/o coverage to order in DB
     *
     * @param totalCarportPriceWithTax Total price for carport
     * @param orderID specific order ID
     * @throws LoginSampleException
     */
    public static void insertTotalCarportPrice(double totalCarportPriceWithTax, int orderID) throws LoginSampleException {
        DataMapper.insertTotalPrice(totalCarportPriceWithTax, orderID);
    }

    /**
     * Returns total carport (Cost) price from database
     * @param orderID
     * @return
     * @throws LoginSampleException
     */
    public static double getTotalCarportPrice(int orderID) throws LoginSampleException {
     return DataMapper.getOrderTotalPrice(orderID);
    }

    //######################
    // Select option calls #
    //######################

    /**
     * @return DimensionMapper.getCarportWidth()
     * @throws LoginSampleException
     */
    public static List<CarportWidth> getCarportWidth() throws LoginSampleException {
        return DataMapper.getCarportWidth();
    }

    /**
     * @return DimensionMapper.getCarportLength()
     * @throws LoginSampleException
     */
    public static List<CarportLength> getCarportLength() throws LoginSampleException {
        return DataMapper.getCarportLength();
    }

    /**
     * @return DimensionMapper.getRoofFlat()
     * @throws LoginSampleException
     */
    public static List<RoofFlat> getRoofFlat() throws LoginSampleException {
        return DataMapper.getRoofFlat();
    }

    /**
     * @return DimensionMapper.getRoofRaised()
     * @throws LoginSampleException
     */
    public static List<RoofRaised> getRoofRaised() throws LoginSampleException {
        return DataMapper.getRoofRaised();
    }

    /**
     * @return DimensionMapper.getRoofDegree()
     * @throws LoginSampleException
     */
    public static List<RoofDegree> getRoofDegree() throws LoginSampleException {
        return DataMapper.getRoofDegree();
    }

    /**
     * @return DimensionMapper.getShedWidth()
     * @throws LoginSampleException
     */
    public static List<ShedWidth> getShedWidth() throws LoginSampleException {
        return DataMapper.getShedWidth();
    }

    /**
     * @return DimensionMapper.getShedLength()
     * @throws LoginSampleException
     */
    public static List<ShedLength> getShedLength() throws LoginSampleException {
        return DataMapper.getShedLength();
    }


    //######################
    // Carport calculation #
    //######################

    /**
     * @param rafterLength
     * @return beam-dimension and beam-spacing for light roof
     * @throws LoginSampleException
     */
    public static List<BeamDimensionLight> getBeamDimensionLight(double rafterLength) throws LoginSampleException {
        return DataMapper.getBeamDimensionLight(rafterLength);
    }

    /**
     * @param rafterLength
     * @return beam-dimension and beam-spacing for heavy roof
     * @throws LoginSampleException
     */
    public static List<BeamDimensionHeavy> getBeamDimensionHeavy(double rafterLength) throws LoginSampleException {
        return DataMapper.getBeamDimensionHeavy(rafterLength);
    }

    /**
     * @return HashMap of pitch and factor
     * @throws LoginSampleException
     */
    public static Map<Integer, Double> getPitchFactor() throws LoginSampleException {
        return DataMapper.getPitchFactor();
    }

    /**
     * @return List of standard dimensions
     * @throws LoginSampleException
     */
    public static List<StandardDimensions> getStandardDimensions() throws LoginSampleException {
        return DataMapper.getStandardDimensions();
    }

    /**
     * @return Arraylist of all items in DB
     * @throws LoginSampleException
     */
    public static List<Item> getItemList() throws LoginSampleException {
        return DataMapper.getItemList();
    }

    //########################################
    // All Admin queries for CRUD
    //########################################

    //----------------------------------------
    // CRUD for Measurement Units
    //----------------------------------------

    /**
     *
     * @return
     * @throws LoginSampleException
     */
    public static List<MeasurementUnits> getMeasurementUnits() throws LoginSampleException {
        return DataMapper.getMeasurementUnits();
    }

    /**
     *
     * @param units
     * @param c_width
     * @param c_length
     * @param ts_width
     * @param ts_length
     * @throws LoginSampleException
     */
    public static void createMeasurementUnits(int units, int c_width, int c_length, int ts_width, int ts_length) throws LoginSampleException {
        DataMapper.createMeasurementUnits(units,c_width,c_length,ts_width,ts_length);
    }

    /**
     *
     * @param measurement_units_id
     * @param units
     * @param c_width
     * @param c_length
     * @param ts_width
     * @param ts_length
     * @throws LoginSampleException
     */
    public static void updateMeasurementUnits(int measurement_units_id, int units, int c_width, int c_length, int ts_width, int ts_length) throws LoginSampleException {
        DataMapper.updateMeasurementUnits(measurement_units_id,units,c_width,c_length,ts_width,ts_length);
    }

    /**
     *
     * @param measurement_units_id
     * @throws LoginSampleException
     */
    public static void deleteMeasurementUnits(int measurement_units_id) throws LoginSampleException {
        DataMapper.deleteMeasurementUnits(measurement_units_id);
    }

    //----------------------------------------
    // CRUD for Item List
    //----------------------------------------

    /**
     *
     * @return
     * @throws LoginSampleException
     */
    public static List<ItemList> getItemListAdmin() throws LoginSampleException {
        return DataMapper.getItemListAdmin();
    }

    /**
     *
     * @param material_type
     * @param material
     * @param description
     * @param amounts
     * @param unit
     * @param price_per_unit
     * @throws LoginSampleException
     */
    public static void createItemList(String material_type, String material, String description, int amounts, String unit, double price_per_unit) throws LoginSampleException {
        DataMapper.createItemList(material_type,material,description,amounts,unit,price_per_unit);
    }

    /**
     *
     * @param item_list_id
     * @param material_type
     * @param material
     * @param description
     * @param amounts
     * @param unit
     * @param price_per_unit
     * @throws LoginSampleException
     */
    public static void updateItemList(int item_list_id, String material_type, String material, String description, int amounts, String unit, double price_per_unit) throws LoginSampleException {
        DataMapper.updateItemList(item_list_id,material_type,material,description,amounts,unit,price_per_unit);
    }

    /**
     *
     * @param item_list_id
     * @throws LoginSampleException
     */
    public static void deleteItemList(int item_list_id) throws LoginSampleException {
        DataMapper.deleteItemList(item_list_id);
    }

    //----------------------------------------
    // CRUD for Rafter spacing
    //----------------------------------------

    /**
     *
     * @return
     * @throws LoginSampleException
     */
    public static List<RafterSpacing> getRafterSpacing() throws LoginSampleException {
        return DataMapper.getRafterSpacing();
    }

    /**
     *
     * @param category
     * @param beam_dimension
     * @param beam_spacing
     * @param rafter_length
     * @throws LoginSampleException
     */
    public static void createRafterSpacing(String category, String beam_dimension, double beam_spacing, double rafter_length) throws LoginSampleException {
        DataMapper.createRafterSpacing(category,beam_dimension,beam_spacing,rafter_length);
    }

    /**
     *
     * @param rafter_spacing_id
     * @param category
     * @param beam_dimension
     * @param beam_spacing
     * @param rafter_length
     * @throws LoginSampleException
     */
    public static void updateRafterSpacing(int rafter_spacing_id, String category, String beam_dimension, double beam_spacing, double rafter_length) throws LoginSampleException {
        DataMapper.updateRafterSpacing(rafter_spacing_id,category,beam_dimension,beam_spacing,rafter_length);
    }

    /**
     *
     * @param rafter_spacing_id
     * @throws LoginSampleException
     */
    public static void deleteRafterSpacing(int rafter_spacing_id) throws LoginSampleException {
        DataMapper.deleteRafterSpacing(rafter_spacing_id);
    }

    //----------------------------------------
    // CRUD for Roof
    //----------------------------------------

    /**
     *
     * @return
     * @throws LoginSampleException
     */
    public static List<Roof> getRoof() throws LoginSampleException {
        return DataMapper.getRoof();
    }

    /**
     *
     * @param roof_type
     * @param roof_category
     * @param roof_material
     * @throws LoginSampleException
     */
    public static void createRoof(String roof_type, String roof_category, String roof_material) throws LoginSampleException {
        DataMapper.createRoof(roof_type,roof_category,roof_material);
    }

    /**
     *
     * @param roof_id
     * @param roof_type
     * @param roof_category
     * @param roof_material
     * @throws LoginSampleException
     */
    public static void updateRoof(int roof_id, String roof_type, String roof_category, String roof_material) throws LoginSampleException {
        DataMapper.updateRoof(roof_id,roof_type,roof_category,roof_material);
    }

    /**
     *
     * @param roof_id
     * @throws LoginSampleException
     */
    public static void deleteRoof(int roof_id) throws LoginSampleException {
        DataMapper.deleteRoof(roof_id);
    }

    //----------------------------------------
    // CRUD for Roof pitch
    //----------------------------------------

    /**
     *
     * @return
     * @throws LoginSampleException
     */
    public static List<RoofPitch> getRoofPitch() throws LoginSampleException {
        return DataMapper.getRoofPitch();
    }

    /**
     *
     * @param pitch
     * @param factor
     * @throws LoginSampleException
     */
    public static void createRoofPitch(int pitch, double factor) throws LoginSampleException {
        DataMapper.createRoofPitch(pitch,factor);
    }

    /**
     *
     * @param roof_pitch_id
     * @param pitch
     * @param factor
     * @throws LoginSampleException
     */
    public static void updateRoofPitch(int roof_pitch_id, int pitch, double factor) throws LoginSampleException {
        DataMapper.updateRoofPitch(roof_pitch_id,pitch,factor);
    }

    /**
     *
     * @param roof_pitch_id
     * @throws LoginSampleException
     */
    public static void deleteRoofPitch(int roof_pitch_id) throws LoginSampleException {
        DataMapper.deleteRoofPitch(roof_pitch_id);
    }

    //----------------------------------------
    // CRUD for Standard dimensions
    //----------------------------------------

    /**
     *
     * @return
     * @throws LoginSampleException
     */
    public static List<StandardDimensions> getStandardDimensionsAdmin() throws LoginSampleException {
        return DataMapper.getStandardDimensionsAdmin();
    }

    /**
     *
     * @param standard_dimensions_id
     * @param bottom_lathspan
     * @param bottom_laths
     * @param top_lath_gap
     * @param avg_lath_span
     * @param roof_tile_length
     * @param roof_tile_width
     * @param roof_trapez_length
     * @param roof_trapez_width
     * @param shed_claddeing_board_dim
     * @param beam_dimension_heavy
     * @param beam_dimension_light
     * @throws LoginSampleException
     */
    public static void updateStandardDimensions(int standard_dimensions_id,int bottom_lathspan,int bottom_laths,double top_lath_gap,double avg_lath_span,double roof_tile_length,double roof_tile_width,double roof_trapez_length, double roof_trapez_width, String shed_claddeing_board_dim, String beam_dimension_heavy, String beam_dimension_light) throws LoginSampleException {
        DataMapper.updateStandardDimensions(standard_dimensions_id,bottom_lathspan,bottom_laths,top_lath_gap,avg_lath_span,roof_tile_length,roof_tile_width,roof_trapez_length,roof_trapez_width,shed_claddeing_board_dim,beam_dimension_heavy,beam_dimension_light);
    }

}
