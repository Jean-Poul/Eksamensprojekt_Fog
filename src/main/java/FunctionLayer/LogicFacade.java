package FunctionLayer;

import DBAccess.DataMapper;
import FunctionLayer.Calculation.BeamDimensionHeavy;
import FunctionLayer.Calculation.BeamDimensionLight;
import FunctionLayer.Calculation.Item;
import FunctionLayer.Calculation.StandardDimensions;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Measurements.*;
import FunctionLayer.Tables.ItemList;
import FunctionLayer.Tables.UserProposition;
import FunctionLayer.Users.User;

import java.util.List;
import java.util.Map;

/**
 * The purpose of LogicFacade is to call Mapper classes safely<br>
 * without doing it directly from other methods in other classes
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
public class LogicFacade {

    //##################
    // 1. User queries #
    //##################

    /**
     * User login
     *
     * @param email e-mail
     * @param password password
     * @return DataMapper.login(email, password)
     * @throws LoginSampleException LoginSampleException
     */
    public static User login(String email, String password) throws LoginSampleException {
        return DataMapper.login(email, password);
    }


    //###############################
    // 2. Create user quote queries #
    //###############################

    /**
     * Create user quote
     *
     * @param name name
     * @param address address
     * @param zipcodeCity zipcode and city
     * @param phone phone
     * @param email e-mail
     * @param comments comments
     * @return userId
     * @throws LoginSampleException LoginSampleException
     */
    public static int createUserQuote(String name, String address, String zipcodeCity, int phone, String email, String comments) throws LoginSampleException {
        return DataMapper.createUserQuote(name, address, zipcodeCity, phone, email, comments);
    }

    /**
     * Create quote order
     *
     * @param user_proposition_id user proposition id
     * @param oc_width carport width
     * @param oc_length carport length
     * @param ots_width shed width
     * @param ots_length shed length
     * @param roof_type roof type
     * @param roof_material roof material
     * @param pitch roof pitch
     * @return orderId
     * @throws LoginSampleException LoginSampleException
     */
    public static int createQuoteOrder(int user_proposition_id, int oc_width, int oc_length, int ots_width, int ots_length, String roof_type, String roof_material, int pitch) throws LoginSampleException {
        return DataMapper.createQuoteOrder(user_proposition_id, oc_width, oc_length, ots_width, ots_length, roof_type, roof_material, pitch);
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
        DataMapper.createQuoteOrderline(orders_id, item_list_id, quantity, total_price);
    }


    //###############################################
    // 3. Queries for dropdown in user proposition  #
    //###############################################

    /**
     * Get carport width measurement units for dropdown in customer quote
     *
     * @return DimensionMapper.getCarportWidth()
     * @throws LoginSampleException LoginSampleException
     */
    public static List<CarportWidth> getCarportWidth() throws LoginSampleException {
        return DataMapper.getCarportWidth();
    }

    /**
     * Get carport length measurement units for dropdown in customer quote
     *
     * @return DimensionMapper.getCarportLength()
     * @throws LoginSampleException LoginSampleException
     */
    public static List<CarportLength> getCarportLength() throws LoginSampleException {
        return DataMapper.getCarportLength();
    }

    /**
     * Get flat roof material for dropdown in customer quote
     *
     * @return DimensionMapper.getRoofFlat()
     * @throws LoginSampleException LoginSampleException
     */
    public static List<RoofFlat> getRoofFlat() throws LoginSampleException {
        return DataMapper.getRoofFlat();
    }

    /**
     * Get raised roof material for dropdown in customer quote
     * @return DimensionMapper.getRoofRaised()
     * @throws LoginSampleException LoginSampleException
     */
    public static List<RoofRaised> getRoofRaised() throws LoginSampleException {
        return DataMapper.getRoofRaised();
    }

    /**
     * Get roof pitch for dropdown in customer quote
     *
     * @return DimensionMapper.getRoofDegree()
     * @throws LoginSampleException LoginSampleException
     */
    public static List<RoofDegree> getRoofDegree() throws LoginSampleException {
        return DataMapper.getRoofDegree();
    }

    /**
     * Get shed width measurement units for dropdown in customer quote
     *
     * @return DimensionMapper.getShedWidth()
     * @throws LoginSampleException LoginSampleException
     */
    public static List<ShedWidth> getShedWidth() throws LoginSampleException {
        return DataMapper.getShedWidth();
    }

    /**
     * Get shed length measurement units for dropdown in customer quote
     *
     * @return DimensionMapper.getShedLength()
     * @throws LoginSampleException LoginSampleException
     */
    public static List<ShedLength> getShedLength() throws LoginSampleException {
        return DataMapper.getShedLength();
    }

    //######################################################
    // 4. All measurement queries for Carport Calculation  #
    //######################################################

    /**
     * Get list of calculated items for order
     *
     * @param orderID order id
     * @return List of calculated items for order
     * @throws LoginSampleException LoginSampleException
     */
    public static List<ItemList> getAllItemList(int orderID) throws LoginSampleException {
        return DataMapper.getAllItemList(orderID);
    }

    /**
     * Get beam-dimension and beam-spacing for light roof
     *
     * @param rafterLength rafter length
     * @return beam-dimension and beam-spacing for light roof
     * @throws LoginSampleException LoginSampleException
     */
    public static List<BeamDimensionLight> getBeamDimensionLight(double rafterLength) throws LoginSampleException {
        return DataMapper.getBeamDimensionLight(rafterLength);
    }

    /**
     * Get beam-dimension and beam-spacing for heavy roof
     *
     * @param rafterLength rafter length
     * @return beam-dimension and beam-spacing for heavy roof
     * @throws LoginSampleException LoginSampleException
     */
    public static List<BeamDimensionHeavy> getBeamDimensionHeavy(double rafterLength) throws LoginSampleException {
        return DataMapper.getBeamDimensionHeavy(rafterLength);
    }

    /**
     * Get HashMap of pitch and factor
     *
     * @return HashMap of pitch and factor
     * @throws LoginSampleException LoginSampleException
     */
    public static Map<Integer, Double> getPitchFactor() throws LoginSampleException {
        return DataMapper.getPitchFactor();
    }

    /**
     * Get list of standard dimensions
     *
     * @return List of standard dimensions
     * @throws LoginSampleException LoginSampleException
     */
    public static List<StandardDimensions> getStandardDimensions() throws LoginSampleException {
        return DataMapper.getStandardDimensions();
    }

    /**
     * Gets all items from DB and maps them to an array.
     *
     * @return Arraylist of all items in DB
     * @throws LoginSampleException LoginSampleException
     */
    public static List<Item> getItemList() throws LoginSampleException {
        return DataMapper.getItemList();
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
        return DataMapper.getAllUserPropositions();
    }

    /**
     * Get one user proposition from DB and maps it to an array.
     *
     * @param userId user id
     * @return Arraylist of one userProposition
     * @throws LoginSampleException LoginSampleException
     */
    public static List<UserProposition> getUserProposition(int userId) throws LoginSampleException {
        return DataMapper.getUserProposition(userId);
    }

    /**
     * Delete quote from DB
     *
     * @param quoteID quote id
     * @throws LoginSampleException LoginSampleException
     */
    public static void deleteQuote(int quoteID) throws LoginSampleException {
        DataMapper.deleteQuote(quoteID);
    }

    /**
     * Update status in orders
     *
     * @param orderID order id
     * @param status status
     * @throws LoginSampleException LoginSampleException
     */
    public static void updateStatus(int orderID, String status) throws LoginSampleException {
        DataMapper.updateStatus(orderID, status);
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
        DataMapper.updateQuoteUserProposition(userID, name, address, zipcodeCity, phone, email, comments);
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
        DataMapper.updateQuoteOrders(orderID, oc_width, oc_length, ots_width, ots_length, roof_type, roof_material, pitch);
    }

    /**
     * Update orderline total price in DB
     *
     * @param orderlineID orderline id
     * @param quantity quantity
     * @param price price
     * @throws LoginSampleException LoginSampleException
     */
    public static void updateQuantityOrderline(int orderlineID, double quantity, double price) throws LoginSampleException {
        DataMapper.updateQuantityOrderline(orderlineID, quantity, price);
    }

    //-----------------------------------------------
    // Queries for recalculation price with coverage
    //-----------------------------------------------

    /**
     * Returns the item no. by looking at specific orderLine in customers order
     *
     * @param orderID order id
     * @param orderLineID orderline id
     * @return DataMapper.getOrderIDFromLineID(orderID, orderLineID)
     * @throws LoginSampleException LoginSampleException
     */
    public static int getOrderIDFromLineID(int orderID, int orderLineID) throws LoginSampleException {
        return DataMapper.getOrderIDFromLineID(orderID, orderLineID);
    }

    /**
     * Gets current item price from orderline using lineID
     *
     * @param orderID order id
     * @param orderLineID orderline id
     * @return DataMapper.getOrderLinePriceFromLineID(orderID, orderLineID)
     * @throws LoginSampleException LoginSampleException
     */
    public static double getOrderLinePriceFromLineID(int orderID, int orderLineID) throws LoginSampleException {
        return DataMapper.getOrderLinePriceFromLineID(orderID, orderLineID);
    }

    /**
     * Returns the coverage from database
     *
     * @param orderID order id
     * @return DataMapper.getOrderCoverage(orderID)
     * @throws LoginSampleException LoginSampleException
     */
    public static int getOrderCoverage(int orderID) throws LoginSampleException {
        return DataMapper.getOrderCoverage(orderID);
    }

    /**
     * Update coverage from a specific order
     *
     * @param coverage coverage
     * @param orderID order id
     * @throws LoginSampleException LoginSampleException
     */
    public static void updateOrderCoverage(int coverage, int orderID) throws LoginSampleException {
        DataMapper.updateOrderCoverage(coverage, orderID);
    }

    /**
     * Inserts the total carport price w. tax and w/o coverage to order in DB
     *
     * @param totalCarportPriceWithTax Total price for carport
     * @param orderID specific order ID
     * @throws LoginSampleException LoginSampleException
     */
    public static void insertTotalCarportPrice(double totalCarportPriceWithTax, int orderID) throws LoginSampleException {
        DataMapper.insertTotalPrice(totalCarportPriceWithTax, orderID);
    }

    /**
     * Returns total carport (Cost) price from database
     *
     * @param orderID order id
     * @return DataMapper.getOrderTotalPrice(orderID)
     * @throws LoginSampleException LoginSampleException
     */
    public static double getTotalCarportPrice(int orderID) throws LoginSampleException {
     return DataMapper.getOrderTotalPrice(orderID);
    }

    /**
     * Update price with coverage by order id
     *
     * @param totalPrice total price
     * @param orderID order id
     * @throws LoginSampleException LoginSampleException
     */
    public static void setPriceWithCoverage(double totalPrice, int orderID) throws LoginSampleException {
        DataMapper.setPriceWithCoverage(totalPrice, orderID);
    }


    //########################################
    // 6. All Admin queries for CRUD         #
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
        return DataMapper.getMeasurementUnits();
    }

    /**
     * Create new measurement units for admin management
     *
     * @param units units
     * @param c_width carport width
     * @param c_length carport length
     * @param ts_width shed width
     * @param ts_length shed width
     * @throws LoginSampleException LoginSampleException
     */
    public static void createMeasurementUnits(int units, int c_width, int c_length, int ts_width, int ts_length) throws LoginSampleException {
        DataMapper.createMeasurementUnits(units,c_width,c_length,ts_width,ts_length);
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
        DataMapper.updateMeasurementUnits(measurement_units_id,units,c_width,c_length,ts_width,ts_length);
    }

    /**
     * Delete from measurement units for admin management
     *
     * @param measurement_units_id measurement units id
     * @throws LoginSampleException LoginSampleException
     */
    public static void deleteMeasurementUnits(int measurement_units_id) throws LoginSampleException {
        DataMapper.deleteMeasurementUnits(measurement_units_id);
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
        return DataMapper.getItemListAdmin();
    }

    /**
     * Create new item in item list for admin management
     *
     * @param material_type material type
     * @param material material
     * @param description description
     * @param amounts amounts
     * @param unit unit
     * @param price_per_unit price per unit
     * @throws LoginSampleException LoginSampleException
     */
    public static void createItemList(String material_type, String material, String description, int amounts, String unit, double price_per_unit) throws LoginSampleException {
        DataMapper.createItemList(material_type,material,description,amounts,unit,price_per_unit);
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
        DataMapper.updateItemList(item_list_id,material_type,material,description,amounts,unit,price_per_unit);
    }

    /**
     * Delete from item list for admin management
     *
     * @param item_list_id item list id
     * @throws LoginSampleException LoginSampleException
     */
    public static void deleteItemList(int item_list_id) throws LoginSampleException {
        DataMapper.deleteItemList(item_list_id);
    }

    //----------------------------------------
    // CRUD for Rafter spacing
    //----------------------------------------

    /**
     * Get all rafter spacing for admin management
     *
     * @return rafterSpacings object
     * @throws LoginSampleException LoginSampleException
     */
    public static List<RafterSpacing> getRafterSpacing() throws LoginSampleException {
        return DataMapper.getRafterSpacing();
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
        DataMapper.createRafterSpacing(category,beam_dimension,beam_spacing,rafter_length);
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
        DataMapper.updateRafterSpacing(rafter_spacing_id,category,beam_dimension,beam_spacing,rafter_length);
    }

    /**
     * Delete from rafter spacing for admin management
     * @param rafter_spacing_id rafter spacing id
     * @throws LoginSampleException LoginSampleException
     */
    public static void deleteRafterSpacing(int rafter_spacing_id) throws LoginSampleException {
        DataMapper.deleteRafterSpacing(rafter_spacing_id);
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
        return DataMapper.getRoof();
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
        DataMapper.createRoof(roof_type,roof_category,roof_material);
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
        DataMapper.updateRoof(roof_id,roof_type,roof_category,roof_material);
    }

    /**
     * Delete from roof for admin management
     *
     * @param roof_id roof id
     * @throws LoginSampleException LoginSampleException
     */
    public static void deleteRoof(int roof_id) throws LoginSampleException {
        DataMapper.deleteRoof(roof_id);
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
        return DataMapper.getRoofPitch();
    }

    /**
     * Create new roof pitch and factor for admin management
     *
     * @param pitch pitch
     * @param factor factor
     * @throws LoginSampleException LoginSampleException
     */
    public static void createRoofPitch(int pitch, double factor) throws LoginSampleException {
        DataMapper.createRoofPitch(pitch,factor);
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
        DataMapper.updateRoofPitch(roof_pitch_id,pitch,factor);
    }

    /**
     * Delete from roof pitch
     *
     * @param roof_pitch_id roof pitch id
     * @throws LoginSampleException LoginSampleException
     */
    public static void deleteRoofPitch(int roof_pitch_id) throws LoginSampleException {
        DataMapper.deleteRoofPitch(roof_pitch_id);
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
        return DataMapper.getStandardDimensionsAdmin();
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
        DataMapper.updateStandardDimensions(standard_dimensions_id,bottom_lathspan,bottom_laths,top_lath_gap,avg_lath_span,roof_tile_length,roof_tile_width,roof_trapez_length,roof_trapez_width,shed_claddeing_board_dim,beam_dimension_heavy,beam_dimension_light);
    }

}
