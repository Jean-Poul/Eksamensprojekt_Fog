package FunctionLayer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Indholder diverse metoder til beregning af carport størrelse, stykliste og priser
 * Contains methods for calculating carport size and bill-of-material
 *
 * @author Alle
 */

// ###################
// #   TO BE DONE    #
// ###################

//Shed dimensions should be validated to fit inside carport. TBD on .jsp maybe?

//Calculate roof cladding ("6 rækker á 24 sten")
//Calculate brackets, bolts, screws and washers
//Rooftop brackets = antallet af sten i én række
//Tiles - (L x W / Roof area m2)
//tile hooks and binders (2 packages)

//Calculate cladding (Only on sheds - Sides 1 and 2 (L x W) - cladding piece height and width
//Door (Cladding + handle, hinges and beams for "Z")

public class CarportCalculation {

    //FINALS below are assumptions of standard dimensions - Should probably be in DB
    private static final int BOTTOM_LATHSPAN = 35;
    private static final int BOTTOM_LATHS = 2;
    private static final double TOP_LATH = 3;
    private static final double AVG_LATH_SPAN = 30;

    DecimalFormat df = new DecimalFormat("#.##");

    private double carportLength;
    private double carportWidth;
    private int customerRoofAngle;
    private double shedLength;
    private double shedWidth;

    private int calcAngle;
    private double calcRaftLength;
    private double noOfRafts;
    private double raftDistance;
    private String raftDimension;
    private double calcRoofHeight;
    private double supportingStrap;
    private int sternBoardLength;
    private double wallLath;

    boolean withShed = false;

    private int noOfLaths;
    private double lathSpan;
    private int noOfBeams;

    //To be returned from DB - Below should be deleted when queries work
    private String raftDimAndDist = "45 x 120 1.2";

//    public CarportCalculation(double carportLength, double carportWidth, double customerRoofAngle, double shedLength, double shedWidth, String roofCladdingType){
//        calcNoOfBeams(shedLength);
//        //#######################################
//        //DET HER BLIVER DEN RIGTIGE CONSTRUCTOR
//        //#######################################
//    }

    /*
    Hashmap "angleAndFactor", constructor and "populateValidAngles" should be aquired from DB.
    */

    //Contains the roof slant angle and the corresponding factor to multiply with - Should be retrieved from DB.
    HashMap<Integer, Double> angleAndFactor = new HashMap<Integer, Double>();

    //double shedLength
    public CarportCalculation() {

        //If the customer has selected a shed, the number of beams doubles.
        calcNoOfBeams(shedLength);

        //To be deleted when the hashmap is populated from DB
        populateAngleAndFactor();
    /*
    ########################
    ###   TEST EXAMPLE!  ###
    ########################
     */

        carportLength = 720;
        carportWidth = 360;
        customerRoofAngle = 30;

        System.out.println("Lad os lave et test eksempel:");
        System.out.println("Kunden vælger en carport på: 3,6 x 7,3 m med vinkel 30");
        System.out.println("Vi forventer 6 stk. spær á 45x120 på 184,98 cm, Taghøjde på 103,92 cm");

        calcRoofAngle(customerRoofAngle);
        calcRaftLength(carportWidth, customerRoofAngle, calcAngle);
        calcRoofHeight(customerRoofAngle, carportWidth);
        getRaftDimension(raftDimAndDist);
        getRaftDistance(raftDimAndDist);
        noOfRafts(carportLength, raftDistance);
        calcRoofLaths(calcRaftLength);

        System.out.println("Systemet udregner antal spær: " + noOfRafts + " stk");
        System.out.println("Systemet udregner spærdimension " + raftDimension + " mm");
        System.out.println("Systemet udregner spærlængde: " + df.format(calcRaftLength) + " cm");
        System.out.println("Systemet udregner spærafstand: " + raftDistance + " cm");
        System.out.println("Systemet udregner højde: " + df.format(calcRoofHeight) + " cm");
        System.out.println("Systemet udregner antal lægter " + noOfLaths);
        System.out.println("Systemet udregner lægteafstand: " + lathSpan);
        System.out.println("Systemet udregner lægtelængde: " + carportLength);
    }

    /**
     * Set roof slant multiplication factor according to documentation (To be retrieved from DB)
     */
    private void populateAngleAndFactor() {
        angleAndFactor.put(15, 1.0);
        angleAndFactor.put(20, 0.97);
        angleAndFactor.put(25, 0.94);
        angleAndFactor.put(30, 0.89);
        angleAndFactor.put(35, 0.84);
        angleAndFactor.put(40, 0.79);
        angleAndFactor.put(45, 0.72);
    }

    /**
     * Calculates the required amount of wall-laths for the shed
     *
     * @param shedWidth The customer selected shed width
     * @param shedLength The customer selected shed length
     */
    private void calcWallLaths(double shedWidth, double shedLength){
        double wallLathQty = (shedLength + shedWidth) * 2;
        this.wallLath = wallLathQty;
    }

    /**
     * Calculates the required no. of beams. (Will always be 4 unless there's a shed - then there's 8)
     *
     * @param shedLength
     */
    private void calcNoOfBeams(double shedLength) {
        if (shedLength > 0) {
            this.noOfBeams = 8;
        } else {
            this.noOfBeams = 4;
        }
    }

    /**
     * Calculates the amount of supporting strap needed (Tagets rem)
     *
     * @param carportWidth  The customer selected carport width
     * @param carportLength The customer selected carport length
     */
    private void calculateSupportingStrap(double carportWidth, double carportLength) {

        double totalSupportStrap = (carportLength + carportWidth) * 2;
        this.supportingStrap = totalSupportStrap;
    }

    /**
     * Calculates the upper roof angle. Is necessary to determine roof height.
     *
     * @param customerRoofAngle The customer selected roof slant angle
     */
    private void calcRoofAngle(int customerRoofAngle) {
        int triangleAngleSum = 180;
        int calcAngle = triangleAngleSum - (customerRoofAngle * 2);
        this.calcAngle = calcAngle;
    }

    private void calcSternBoardLength(double raftLength) {
        double sternBoardsLength = this.calcRaftLength * 4;
        this.sternBoardLength = sternBoardLength;
    }

    /**
     * Calculates the raft length
     *
     * @param carportWidth        Customer selected carport width.
     * @param customerRoofAngle   Customer selected roof slant angle.
     * @param calculatedRoofAngle The calculated upper roof angle (Comes from calcRoofAngle().
     */
    private void calcRaftLength(double carportWidth, int customerRoofAngle, int calculatedRoofAngle) {

        double custRoofAngleRadian = Math.toRadians(customerRoofAngle);
        double calcRoofAngleRadian = Math.toRadians(calculatedRoofAngle);
        double calcRaftLength = (carportWidth * Math.sin(custRoofAngleRadian)) / (Math.sin(calcRoofAngleRadian));
        calcRaftLength = calcRaftLength * angleAndFactor.get(customerRoofAngle);
        this.calcRaftLength = calcRaftLength;
    }

    private void getRaftDistance(String s) {

        String raftDistance = s.substring(s.length() - 3);
        double raftDist = Double.parseDouble(raftDistance) * 100;
        this.raftDistance = raftDist;
    }

    private void getRaftDimension(String s) {
        String raftDim = s.substring(0, 8);
        this.raftDimension = raftDim;
    }

    private void noOfRafts(double carportLength, double raftDistance) {
        double noOfRafts = Math.round(carportLength / raftDistance);
        this.noOfRafts = noOfRafts;
    }

    /**
     * Calculates the roofs total height.
     *
     * @param customerRoofAngle The customer selected roof slant angle.
     * @param carportWidth      The customer selected carport width.
     */
    private void calcRoofHeight(int customerRoofAngle, double carportWidth) {
        double custRoofAngleRadian = Math.toRadians(customerRoofAngle);
        double calcRoofHeight = (Math.tan(custRoofAngleRadian) * (carportWidth / 2));
        this.calcRoofHeight = calcRoofHeight;
    }

    private void calcRoofLaths(double calcRaftLength) {
        int bottomLathSpan = BOTTOM_LATHSPAN;
        int bottomLaths = BOTTOM_LATHS;
        double topLathSpan = TOP_LATH;
        double avgLathSpan = AVG_LATH_SPAN;
        double calcLaths = Math.round((calcRaftLength - bottomLathSpan - topLathSpan) / avgLathSpan);
        System.out.println(calcLaths);
        double calcLathSpan = (calcRaftLength - bottomLathSpan - topLathSpan) / calcLaths;
        int actualLaths = (int) ((calcLaths + bottomLaths) * 2);

        this.noOfLaths = actualLaths;
        this.lathSpan = calcLathSpan;
    }
}