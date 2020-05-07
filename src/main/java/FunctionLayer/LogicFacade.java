package FunctionLayer;

import DBAccess.DataMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * The purpose of LogicFacade is to call Mapper classes safely without doing it directly from other methods in other classes
 *
 */
public class LogicFacade {

    //##################
    //User calls
    //##################

    /**
     *
     * @param email
     * @param password
     * @return UserMapper.login( email, password )
     * @throws LoginSampleException
     */
    public static User login( String email, String password ) throws LoginSampleException {
        return DataMapper.login( email, password );
    }

    /**
     *
     * @param email
     * @param password
     * @return user
     * @throws LoginSampleException
     */
    public static User createUser( String email, String password ) throws LoginSampleException {
        User user = new User(email, password, "customer");
        DataMapper.createUser( user );
        return user;
    }

    //##################
    //User quote calls
    //##################

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
    public static int createUserQuote(String name,String address,String zipcodeCity, int phone, String email,String comments) throws LoginSampleException {
        return DataMapper.createUserQuote(name,address,zipcodeCity,phone,email,comments);
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
    public static int createQuoteOrder(int user_proposition_id,int oc_width,int oc_length,int ots_width,int ots_length,String roof_type,String roof_material,int pitch) throws LoginSampleException {
        return DataMapper.createQuoteOrder(user_proposition_id, oc_width, oc_length, ots_width, ots_length, roof_type, roof_material, pitch);
    }

    /**
     *
     * @param orders_id
     * @param material_type
     * @param material
     * @param description
     * @param length
     * @param quantity
     * @param unit
     * @param total_price
     * @throws LoginSampleException
     */
    public static void createQuoteOrderline(int orders_id,String material_type,String material,String description,int length,int quantity,String unit,double total_price) throws LoginSampleException {
        DataMapper.createQuoteOrderline(orders_id,material_type,material,description,length,quantity,unit,total_price);
    }

    /**
     *
     * @param quoteID
     * @throws SQLException
     */
    public static void deleteQuote(int quoteID) throws SQLException {
        DataMapper.deleteQuote(quoteID);
    }

    /**
     *
     * @param userId
     * @return DataMapper.getUserPropositions(userId)
     * @throws SQLException
     */
    public static List<UserProposition> getUserProposition(int userId) throws SQLException {
        return DataMapper.getUserProposition(userId);
    }

    /**
     *
     * @return DataMapper.getAllUserPropositions()
     * @throws SQLException
     */
    public static List<UserProposition> getAllUserPropositions() throws SQLException {
        return DataMapper.getAllUserPropositions();
    }

    //##################
    //Select option calls
    //##################

    /**
     *
     * @return DimensionMapper.getCarportWidth()
     * @throws SQLException
     */
    public static List<CarportWidth> getCarportWidth() throws SQLException {
        return DataMapper.getCarportWidth();
    }

    /**
     *
     * @return DimensionMapper.getCarportLength()
     * @throws SQLException
     */
    public static List<CarportLength> getCarportLength() throws SQLException {
        return DataMapper.getCarportLength();
    }

    /**
     *
     * @return DimensionMapper.getRoofFlat()
     * @throws SQLException
     */
    public static List<RoofFlat> getRoofFlat() throws SQLException {
        return DataMapper.getRoofFlat();
    }

    /**
     *
     * @return DimensionMapper.getRoofRaised()
     * @throws SQLException
     */
    public static List<RoofRaised> getRoofRaised() throws SQLException {
        return DataMapper.getRoofRaised();
    }

    /**
     *
     * @return DimensionMapper.getRoofDegree()
     * @throws SQLException
     */
    public static List<RoofDegree> getRoofDegree() throws SQLException {
        return DataMapper.getRoofDegree();
    }

    /**
     *
     * @return DimensionMapper.getShedWidth()
     * @throws SQLException
     */
    public static List<ShedWidth> getShedWidth() throws SQLException {
        return DataMapper.getShedWidth();
    }

    /**
     *
     * @return DimensionMapper.getShedLength()
     * @throws SQLException
     */
    public static List<ShedLength> getShedLength() throws SQLException {
        return DataMapper.getShedLength();
    }


    //##################
    // Carport calculation
    //##################

    /**
     *
     * @param rafterLength
     * @return beam-dimension and beam-spacing for light roof
     * @throws SQLException
     */
    public static List<BeamDimensionLight> getBeamDimensionLight(double rafterLength) throws SQLException {
        return DataMapper.getBeamDimensionLight(rafterLength);
    }

    /**
     *
     * @param rafterLength
     * @return beam-dimension and beam-spacing for heavy roof
     * @throws SQLException
     */
    public static List<BeamDimensionHeavy> getBeamDimensionHeavy(double rafterLength) throws SQLException {
        return DataMapper.getBeamDimensionHeavy(rafterLength);
    }

    /**
     *
     * @return HashMap of pitch and factor
     * @throws SQLException
     */
    public static Map<Integer,Double> getPitchFactor() throws SQLException {
        return DataMapper.getPitchFactor();
    }

    /**
     *
     * @return List of standard dimensions
     * @throws SQLException
     */
    public static List<StandardDimensions> getStandardDimensions() throws SQLException {
        return DataMapper.getStandardDimensions();
    }

    /**
     *
     * @return Arraylist of all items in DB
     * @throws SQLException
     */
    public static List<Item> getItemList() throws SQLException {
        return DataMapper.getItemList();
    }

}
