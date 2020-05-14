package FunctionLayer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class CarportCalculationTest {

    @Parameterized.Parameter(value = 0)
    public int carportLength;
    @Parameterized.Parameter(value = 1)
    public int carportWidth;
    @Parameterized.Parameter(value = 2)
    public int shedLength;
    @Parameterized.Parameter(value = 3)
    public int shedWidth;
    public int roofAngle;
    public int carportShedDistance = 30;

    public void initTests(int carportLength, int carportWidth, int shedLength, int shedWith, int roofAngle) throws SQLException {
        this.carportLength = carportLength;
        this.carportWidth = carportWidth;
        this.shedLength = shedLength;
        this.shedWidth = shedWith;
        this.roofAngle = roofAngle;
    }

    @Parameterized.Parameters(name = "{index}: testValidCarportAndShedDimensions - {0}, {1}, {2}, {3}")
    public static Collection<Object[]> shedAndCarportDimensions() {
        return Arrays.asList(new Object[][]{
                {510, 450, 480, 410},
                {780, 750, 720, 690},
                {570, 570, 540, 540},
                {510, 330, 300, 300}
        });
    }


    @Test
    public void validShedDimensionWidth() {
        Assert.assertTrue(carportLength - (shedLength + carportShedDistance) >= 0);
        Assert.assertTrue(carportLength - (shedLength + carportShedDistance) >= 0);
    }



}