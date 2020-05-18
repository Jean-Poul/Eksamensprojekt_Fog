package FunctionLayer;

import DBAccess.DataMapper;

import java.sql.SQLException;
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

    public static int getOrderCoverage(int orderID) throws LoginSampleException {
        return DataMapper.getOrderCoverage(orderID);
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

    //############################
    // All Admin queries for CRUD
    //############################

    public static List<MeasurementUnits> getMeasurementUnits() throws LoginSampleException {
        return DataMapper.getMeasurementUnits();
    }

}
