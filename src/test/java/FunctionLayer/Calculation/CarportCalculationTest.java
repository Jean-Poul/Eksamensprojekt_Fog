package FunctionLayer.Calculation;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.LogicFacade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Test class for CarportCalculation
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class CarportCalculationTest {

    CarportCalculation cp;
    {
        try {
            cp = new CarportCalculation(1);
        } catch (LoginSampleException e) {
            e.printStackTrace();
        }
    }

    private Map<Integer, Double> angleAndFactor;
    {
        try {
            angleAndFactor = LogicFacade.getPitchFactor();
        } catch (LoginSampleException e) {
            e.printStackTrace();
        }
    }

    //Simulate customer order
    private int carportLength;
    private int carportWidth;
    private double shedLength;
    private double shedWidth;
    private int roofAngle = cp.getCustomerRoofAngle();
    private int noOfBeams = cp.getNoOfBeams();

    //Set some standard dimensions
    private int shedCladdingBoardDim;
    int roofTileWidth = 20;
    int roofTileLength = 25;
    int bottomLathSpan = 35;
    int bottomLaths = 2;
    double topLathSpan = 3;
    double avgLathSpan = 30;
    double claddingBoardOverlap = 2.5;

    @Before
    public void beforeEachTestMethod() {
        carportLength = cp.getCarportLength();
        carportWidth = cp.getCarportWidth();
        shedLength = cp.getShedLength();
        shedWidth = cp.getShedWidth();
        roofAngle = cp.getCustomerRoofAngle();
        shedCladdingBoardDim = 10;
    }

    @Test
    public void calcRaftLength() {

        double expected = cp.getRaftLength() / 100;

        int calculatedTopAngle = 180 - (roofAngle * 2);
        double roofAngleRad = Math.toRadians(roofAngle);
        double calcAngle = Math.toRadians(calculatedTopAngle);
        double calcRaftLength = ((carportWidth * Math.sin(roofAngleRad) / (Math.sin(calcAngle))) / 100);
        calcRaftLength = calcRaftLength * angleAndFactor.get(cp.getCustomerRoofAngle());

        Assert.assertEquals(expected, calcRaftLength, 0.1);
    }

    @Test
    public void calcShedCladding() {

        int expected = cp.getNoOfCladdingBoardsTotal();

        int claddingBoardsShedWidth = (int) (shedWidth / shedCladdingBoardDim); //No of cladding boards shed length w/o overlap
        int noOfCladdingBoardsWidth = (int) Math.ceil((claddingBoardsShedWidth + (((claddingBoardsShedWidth / claddingBoardOverlap) / shedCladdingBoardDim)))); //No. of cladding boards, with overlap in mind
        int claddingBoardsShedLength = (int) (shedLength / shedCladdingBoardDim); //As above
        int noOfCladdingBoardsLength = (int) Math.ceil((claddingBoardsShedLength + (((claddingBoardsShedLength / claddingBoardOverlap) / shedCladdingBoardDim))));
        int noOfCladdingBoardsTotal = (noOfCladdingBoardsWidth + noOfCladdingBoardsLength) * 2; //Calculate all four sides of the shed

        Assert.assertEquals(expected, noOfCladdingBoardsTotal);
    }

    @Test
    public void calculateShedWallLaths() {

        double expected = cp.getShedWallLathsTotalLength();

        double shedWallLathsTotalLength = ((shedLength + shedWidth) * 2) * 12;

        Assert.assertEquals(expected, shedWallLathsTotalLength, 0.01);
    }

    @Test
    public void calcRoofCladdingArea() {

        int expected = cp.getTotalNumberOfRoofTiles();

        int totalNumberOfRoofTiles = 0;
        int columnsOfTiles = carportLength / roofTileWidth; //30,5
        int rowsOfTiles = (int) (231 / roofTileLength); //9,24
        totalNumberOfRoofTiles = rowsOfTiles * columnsOfTiles * 2; //Multiply by 2 to get both sides of the roof

        Assert.assertEquals(expected, totalNumberOfRoofTiles);
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

        double expected = cp.getSupportingStrapLength();

        double totalSupportingStrap = (carportLength * 2) + carportWidth;

        Assert.assertEquals(expected, totalSupportingStrap, 0.01);
    }

    @Test
    public void calcRoofAngle() {

        int calcAngle = 180 - (roofAngle * 2);

        Assert.assertTrue(calcAngle <= 150 && calcAngle >= 90);
    }

    @Test
    public void calcSternBoardLength() {

        double expected = cp.getSternBoardLength();

        double totalSternBoardLength = carportLength * 2;

        Assert.assertEquals(expected, totalSternBoardLength, 0.01);

    }

    @Test
    public void calcRoofHeight() {

        double expected = cp.getCalcRoofHeight();

        double custRoofAngleRadian = Math.toRadians(roofAngle);
        double calcRoofHeight = (Math.tan(custRoofAngleRadian) * (carportWidth / 2));

        Assert.assertEquals(expected, calcRoofHeight, 0.1);
    }

    @Test
    public void calcRoofLaths() {

        int expected = cp.getNoOfLaths();

        double calcRaftLength = cp.getRaftLength();
        double calcLaths = Math.round((calcRaftLength - bottomLathSpan - topLathSpan) / avgLathSpan);
        int noOfLaths = (int) ((calcLaths + bottomLaths) * 2);

        Assert.assertEquals(expected, noOfLaths);
    }
}