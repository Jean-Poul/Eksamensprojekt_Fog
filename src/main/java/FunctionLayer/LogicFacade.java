package FunctionLayer;

import DBAccess.DataMapper;

import java.sql.SQLException;
import java.util.List;

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
     * @param adress
     * @param zipcodeCity
     * @param phone
     * @param email
     * @param comments
     * @throws LoginSampleException
     */
    public static void createUserQuote(String name,String adress,String zipcodeCity, int phone, String email,String comments) throws LoginSampleException {
        DataMapper.createUserQuote(name,adress,zipcodeCity,phone,email,comments);
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
     * @throws LoginSampleException
     */
    public static void createQuoteOrder(int user_proposition_id,int oc_width,int oc_length,int ots_width,int ots_length,String roof_type,String roof_material,int pitch) throws LoginSampleException {
        DataMapper.createQuoteOrder(user_proposition_id, oc_width, oc_length, ots_width, ots_length, roof_type, roof_material, pitch);
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

}
