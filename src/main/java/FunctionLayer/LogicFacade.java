package FunctionLayer;

import DBAccess.DimensionMapper;
import DBAccess.UserMapper;

import java.sql.SQLException;
import java.util.List;

/**
 * The purpose of LogicFacade is to call Mapper classes safely without doing it directly from other methods in other classes
 *
 */
public class LogicFacade {

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

    /**
     *
     * @return DimensionMapper.getCarport()
     * @throws SQLException
     */
    public static List<CarportWidth> getCarport() throws SQLException {
        return DimensionMapper.getCarportWidth();
    }

    /**
     *
     * @return DimensionMapper.getRoof()
     * @throws SQLException
     */
    public static List<RoofMeasurements> getRoof() throws SQLException {
        return DimensionMapper.getRoof();
    }

    /**
     *
     * @return DimensionMapper.getShed()
     * @throws SQLException
     */
    public static List<ShedMeasurements> getShed() throws SQLException {
        return DimensionMapper.getShed();
    }

}
