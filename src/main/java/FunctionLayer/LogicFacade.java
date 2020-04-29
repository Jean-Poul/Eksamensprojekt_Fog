package FunctionLayer;

import DBAccess.CustomerQuoteMapper;
import DBAccess.UserMapper;

/**
 * The purpose of LogicFacade is to...
 * @author kasper
 */
public class LogicFacade {

    public static User login( String email, String password ) throws LoginSampleException {
        return UserMapper.login( email, password );
    } 

    public static User createUser( String email, String password ) throws LoginSampleException {
        User user = new User(email, password, "customer");
        UserMapper.createUser( user );
        return user;
    }

    public static void createUserQuote(String name,String adress,String zipcodeCity, int phone, String email,String comments) throws LoginSampleException {
        CustomerQuoteMapper.createUserQuote(name,adress,zipcodeCity,phone,email,comments);
    }

    public static void createQuoteOrder(int user_proposition_id,int oc_width,int oc_length,int ots_width,int ots_length,String roof_type,String roof_material,int pitch) throws LoginSampleException {
        CustomerQuoteMapper.createQuoteOrder(user_proposition_id,oc_width,oc_length,ots_width,ots_length,roof_type,roof_material,pitch);
    }
}
