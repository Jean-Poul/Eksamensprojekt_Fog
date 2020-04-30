package FunctionLayer;

import DBAccess.DimensionMapper;
import DBAccess.CustomerQuoteMapper;
import DBAccess.UserMapper;

import java.sql.SQLException;
import java.util.List;

/**
 * The purpose of LogicFacade is to call Mapper classes safely without doing it directly from other methods in other classes
 *
 */
public class LogicFacade {

    //##################
    //User related calls
    //##################

    /**
     *
     * @param email
     * @param password
     * @return UserMapper.login( email, password )
     * @throws LoginSampleException
     */
    public static User login( String email, String password ) throws LoginSampleException {
        return UserMapper.login( email, password );
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
        UserMapper.createUser( user );
        return user;
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
        return DimensionMapper.getCarportWidth();
    }

    /**
     *
     * @return DimensionMapper.getCarportLength()
     * @throws SQLException
     */
    public static List<CarportLength> getCarportLength() throws SQLException {
        return DimensionMapper.getCarportLength();
    }

    /**
     *
     * @return DimensionMapper.getRoofFlat()
     * @throws SQLException
     */
    public static List<RoofFlat> getRoofFlat() throws SQLException {
        return DimensionMapper.getRoofFlat();
    }

    /**
     *
     * @return DimensionMapper.getRoofRaised()
     * @throws SQLException
     */
    public static List<RoofRaised> getRoofRaised() throws SQLException {
        return DimensionMapper.getRoofRaised();
    }

    /**
     *
     * @return DimensionMapper.getRoofDegree()
     * @throws SQLException
     */
    public static List<RoofDegree> getRoofDegree() throws SQLException {
        return DimensionMapper.getRoofDegree();
    }

    /**
     *
     * @return DimensionMapper.getShedWidth()
     * @throws SQLException
     */
    public static List<ShedWidth> getShedWidth() throws SQLException {
        return DimensionMapper.getShedWidth();
    }

    /**
     *
     * @return DimensionMapper.getShedLength()
     * @throws SQLException
     */
    public static List<ShedLength> getShedLength() throws SQLException {
        return DimensionMapper.getShedLength();
    }


    public static int createUserQuote(String name,String adress,String zipcodeCity, int phone, String email,String comments) throws LoginSampleException {
        return CustomerQuoteMapper.createUserQuote(name,adress,zipcodeCity,phone,email,comments);
    }

    public static int createQuoteOrder(int user_proposition_id,int oc_width,int oc_length,int ots_width,int ots_length,String roof_type,String roof_material,int pitch) throws LoginSampleException {
        return CustomerQuoteMapper.createQuoteOrder(user_proposition_id,oc_width,oc_length,ots_width,ots_length,roof_type,roof_material,pitch);
    }

    public static void createQuoteOrderline(int orders_id,String material_type,String material,String description,int length,int quantity,String unit,double total_price) throws LoginSampleException {
        CustomerQuoteMapper.createQuoteOrderline(orders_id,material_type,material,description,length,quantity,unit,total_price);
    }

}
