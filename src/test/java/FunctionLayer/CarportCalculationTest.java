package FunctionLayer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

public class CarportCalculationTest {

    //Simulate customer order
    private int carportLength;
    private int carportWidth;
    private int shedLength;
    private int shedWidth;
    private int roofAngle;
    private int noOfBeams;

    //Set some standard dimensions
    private int shedCladdingBoardDim;
    int noOfShedWallLaths = 12;
    int roofTileWidth = 20;
    int roofTileLength = 25;
    int roofTrapezWidth = 109;
    int roofTrapezLength = 240;
    int bottomLathSpan = 35;
    int bottomLaths = 2;
    double topLathSpan = 3;
    double avgLathSpan = 30;

    @Before
    public void beforeEachTestMethod() {
        carportLength = 610;
        carportWidth = 420;
        shedLength = 290;
        shedWidth = 390;
        roofAngle = 25;
        shedCladdingBoardDim = 10;
    }

    @Test
    public void calcRaftLength() {

        double expected = 2.31;

        int calculatedTopAngle = 180 - (roofAngle * 2);
        double roofAngleRad = Math.toRadians(roofAngle);
        double calcAngle = Math.toRadians(calculatedTopAngle);
        double calcRaftLength = ((carportWidth * Math.sin(roofAngleRad) / (Math.sin(calcAngle))) / 100);

        Assert.assertEquals(expected, calcRaftLength, 0.1);
    }

    @Test
    public void calcShedCladding() {

        int expected = 136;

        int claddingBoardsShedWidth = (int) (shedWidth / shedCladdingBoardDim);
        int claddingBoardsShedLength = (int) (shedLength / shedCladdingBoardDim); //As above
        int noOfCladdingBoardsTotal = (claddingBoardsShedLength + claddingBoardsShedWidth) * 2;

        Assert.assertEquals(expected, noOfCladdingBoardsTotal);
    }

    @Test
    public void calculateShedWallLaths() {

        int expected = 163;

        int totalShedWallLathLength = (((shedLength + shedWidth) * 2) * noOfShedWallLaths) / 100;

        Assert.assertEquals(expected, totalShedWallLathLength);
    }

    @Test
    public void calcRoofCladdingArea() {

        int expected = 540;
        int totalNumberOfRoofTiles = 0;

        int columnsOfTiles = carportLength / roofTileWidth; //30,5
        int rowsOfTiles = (int) (231 / roofTileLength); //9,24
        totalNumberOfRoofTiles = rowsOfTiles * columnsOfTiles * 2; //Multiply by 2 to get both sides of the roof

        Assert.assertEquals(expected, totalNumberOfRoofTiles);
    }

    @Test
    public void testcalcRoofCladdingArea() {

        double expected = 9.79;

        double trapezPlateSquareArea = (double) (roofTrapezLength * roofTrapezWidth) / 10000;
        double noOfTrapezPlates = (double) (((carportLength * carportWidth) / 10000) / trapezPlateSquareArea);

        Assert.assertEquals(expected, noOfTrapezPlates, 0.5);
    }

    @Test
    public void calcNoOfBeamsAndDim() {

        boolean withShed;

        int expected = 8;
        if (shedLength > 0) {
            noOfBeams = 8;
        } else {
            noOfBeams = 4;
        }

        Assert.assertEquals(expected, noOfBeams);
    }

    @Test
    public void calculateSupportingStrap() {

        int expected = 1640;

        int totalSupportingStrap = (carportLength * 2) + carportWidth;

        Assert.assertEquals(expected, totalSupportingStrap);
    }

    @Test
    public void calcRoofAngle() {

        int calcAngle = 180 - (roofAngle * 2);

        Assert.assertTrue(calcAngle <= 150 && calcAngle >= 90);
    }

    @Test
    public void calcSternBoardLength() {

        int expected = 1220;

        int totalSternBoardLength = carportLength * 2;

        Assert.assertEquals(expected, totalSternBoardLength);

    }

    @Test
    public void calcRoofHeight() {

        double expected = 97.92;

        double custRoofAngleRadian = Math.toRadians(roofAngle);
        double calcRoofHeight = (Math.tan(custRoofAngleRadian) * (carportWidth / 2));

        Assert.assertEquals(expected, calcRoofHeight, 0.1);
    }

    @Test
    public void calcRoofLaths() {

        int expected = 16;
        int calcRaftLength = 231;

        double calcLaths = Math.round((calcRaftLength - bottomLathSpan - topLathSpan) / avgLathSpan);
        int noOfLaths = (int) ((calcLaths + bottomLaths) * 2);

        Assert.assertEquals(expected, noOfLaths);
    }
}