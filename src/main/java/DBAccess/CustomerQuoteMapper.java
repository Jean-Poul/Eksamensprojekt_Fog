package DBAccess;

import FunctionLayer.LoginSampleException;

import java.sql.*;

/**
 * Set and get user proposition from database
 */
public class CustomerQuoteMapper {

    public static int createUserQuote(String name,String adress,String zipcodeCity,int phone, String email,String comments) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO user_proposition (name,adress,zipcodeCity,phone,email,comments) VALUES (?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, adress);
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

    public static void createQuoteOrderline(int orders_id, String material_type, String material, String description, int length, int quantity, String unit, double total_price) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO orderline (orders_id,material_type,material,description,length,quantity,unit,total_price) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orders_id);
            ps.setString(2, material_type);
            ps.setString(3, material);
            ps.setString(4, description);
            ps.setInt(5, length);
            ps.setInt(6, quantity);
            ps.setString(7, unit);
            ps.setDouble(8, total_price);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }
}
