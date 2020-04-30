package FunctionLayer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

//Select correct raft spacing (Almost done)
//Select correct raft dimension (Easily done when above is finished)

//Select correct beams for light/heavy roof

//Calculate brackets, bolts, screws and washers
//tile hooks and binders (2 packages)
//Door (Cladding + handle, hinges and beams for "Z") - assumption?

public class CarportCalculation {

    //FINALS below are assumptions of standard dimensions - Should probably be in DB
    private static final int BOTTOM_LATHSPAN = 35;
    private static final int BOTTOM_LATHS = 2;
    private static final double TOP_LATH = 3;
    private static final double AVG_LATH_SPAN = 30;
    private static final double ROOF_TILE_LENGTH = 23.6;
    private static final double ROOF_TILE_WIDTH = 20.4;
    private static final double ROOF_TRAPEZ_LENGTH = 240;
    private static final double ROOF_TRAPEZ_WIDTH = 109;
    private static final String SHED_CLADDING_BOARD_DIM = "19x100";
    private String RAFT_DIM_AND_DIST = "45 x 120 1.2";

    boolean withShed = false;
    boolean raisedRoof;
    boolean roofHeavy;

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
    private int totalNumberOfRoofTiles;
    private int totalNumberOfRoofTrapezPlates;
    private int shedWallLaths;
    private int noOfCladdingBoards;

    private int noOfLaths;
    private double lathSpan;
    private int noOfBeams;

    DecimalFormat df = new DecimalFormat("#.##");

    /*
    Hashmap "angleAndFactor", constructor and "populateValidAngles" should be aquired from DB.
    */
    //Contains the roof slant angle and the corresponding factor to multiply with - Should be retrieved from DB.
    HashMap<Integer, Double> angleAndFactor = new HashMap<Integer, Double>();

    //Contains the raft distances to be selected from depending on raft length and roof type (Light/heavy) - should be retrieved from DB
    ArrayList<ArrayList> raftDistancesLight = new ArrayList<ArrayList>();
    ArrayList<ArrayList> raftDistancesHeavy = new ArrayList<ArrayList>();

//    public CarportCalculation(double carportLength, double carportWidth, double customerRoofAngle, double shedLength, double shedWidth, String roofCladdingType){
//        //TJEK OM DER ER SKUR
//        //TJEK OM DET ER REJST TAG
//        //#######################################
//        //DET HER BLIVER DEN RIGTIGE CONSTRUCTOR
//        //#######################################
//    }


    public CarportCalculation() {

        //If the customer has selected a shed, the number of beams doubles.
        //calcNoOfBeams(shedLength);

        //To be deleted when the hashmap is populated from DB
        populateAngleAndFactor();
    /*
    ########################
    ###   TEST EXAMPLE!  ###
    ########################
     */

        carportLength = 720;
        carportWidth = 450;
        customerRoofAngle = 30;
        shedLength = 690;
        shedWidth = 330;

        System.out.println("Lad os lave et test eksempel:");
        System.out.println("Kunden vælger en carport på: 3,6 x 7,3 m med vinkel 30");
        System.out.println("Vi forventer 6 stk. spær á 45x120 på 184,98 cm, Taghøjde på 103,92 cm");

        populateRaftDistancesLight();
        calcRoofAngle(customerRoofAngle);
        calcRaftLength(carportWidth, customerRoofAngle, calcAngle);
        calcRoofHeight(customerRoofAngle, carportWidth);
        getRaftDimension(RAFT_DIM_AND_DIST);
        selectRaftDimAndSpacing(roofHeavy, calcRaftLength);
        noOfRafts(carportLength, raftDistance);
        calcRoofLaths(calcRaftLength);
        calculateShedWallLaths();
        calcShedCladding(shedLength, shedWidth, SHED_CLADDING_BOARD_DIM);

        roofHeavy = false;

        System.out.println("Systemet udregner antal spær: " + noOfRafts + " stk");
        System.out.println("Systemet udregner spærdimension " + raftDimension + " mm");
        System.out.println("Systemet udregner spærlængde: " + df.format(calcRaftLength) + " cm");
        System.out.println("Systemet udregner spærafstand: " + (raftDistance * 100) + " cm");
        System.out.println("Systemet udregner højde: " + df.format(calcRoofHeight) + " cm");
        System.out.println("Systemet udregner antal lægter " + noOfLaths);
        System.out.println("Systemet udregner lægteafstand: " + lathSpan);
        System.out.println("Systemet udregner lægtelængde: " + carportLength);
        System.out.println("Systemet udregner antal beklædningsbræt til skur: " + noOfCladdingBoards);
        System.out.println("Systemet udregner antal løsholter: " + this.shedWallLaths);


    }

    private void selectRaftDimAndSpacing(boolean roofHeavy, double calcRaftLength) {

        double raftLengthM = calcRaftLength / 100; //Convert calcRaftLength to meters

        if (!roofHeavy) {
            if (raftLengthM < Double.parseDouble(String.valueOf(raftDistancesLight.get(0).get(3)))) {
                this.raftDistance = Double.parseDouble(String.valueOf(raftDistancesLight.get(0).get(3)));
                System.out.println("hello");
            } else {

                for (int i = 0; i < raftDistancesLight.size(); i++) {

                    if (raftLengthM > Double.parseDouble(String.valueOf(raftDistancesLight.get(i).get(3)))
                            &&
                            raftLengthM < Double.parseDouble(String.valueOf(raftDistancesLight.get(i + 1).get(3)))) {
                        this.raftDistance = Double.parseDouble(String.valueOf(raftDistancesLight.get(i + 1).get(3)));
                    }
                }
            }

        } else if (roofHeavy) {

        } else {
            //If the roof is neither light or heavy, something went wrong bigtime.
            //Need an exception here.
        }
    }

    //Populates raftDistance lists light. This is a practical example - Should be retrieved from DB
    private void populateRaftDistancesLight() {
        ArrayList<String> lRaftDistance1 = new ArrayList<String>();
        ArrayList<String> lRaftDistance2 = new ArrayList<String>();
        ArrayList<String> lRaftDistance3 = new ArrayList<String>();

        String dbRaftDistance1 = "let,45 x 120,0.4,2.81"; //This should come fromt the DB
        String dbRaftDistance2 = "let,45 x 120,0.6,6.48"; //This should come fromt the DB
        String dbRaftDistance3 = "let,45 x 120,0.8,10.26"; //This should come fromt the DB

        String[] dbRaftDistanceArr1 = dbRaftDistance1.split(",");
        String[] dbRaftDistanceArr2 = dbRaftDistance2.split(",");
        String[] dbRaftDistanceArr3 = dbRaftDistance3.split(",");

        for (int i = 0; i < dbRaftDistanceArr1.length; i++) {
            lRaftDistance1.add(dbRaftDistanceArr1[i]);
        }

        for (int i = 0; i < dbRaftDistanceArr2.length; i++) {
            lRaftDistance2.add(dbRaftDistanceArr2[i]);
        }

        for (int i = 0; i < dbRaftDistanceArr3.length; i++) {
            lRaftDistance3.add(dbRaftDistanceArr3[i]);
        }

        raftDistancesLight.add(lRaftDistance1);
        raftDistancesLight.add(lRaftDistance2);
        raftDistancesLight.add(lRaftDistance3);
    }

    //Populates raftDistance lists Heavy. This is a practical example - Should be retrieved from DB
    private void populateRaftDistancesHeavy() {
        ArrayList<String> hRaftDistance1 = new ArrayList<String>();
        ArrayList<String> hRaftDistance2 = new ArrayList<String>();
        ArrayList<String> hRaftDistance3 = new ArrayList<String>();

        String dbRaftDistance1 = "tung,45 x 120,0.4,2.43"; //This should come fromt the DB
        String dbRaftDistance2 = "tung,45 x 120,0.6,2.13"; //This should come fromt the DB
        String dbRaftDistance3 = "tung,45 x 120,0.8,1.93"; //This should come fromt the DB

        String[] dbRaftDistanceArr1 = dbRaftDistance1.split(",");
        String[] dbRaftDistanceArr2 = dbRaftDistance2.split(",");
        ;
        String[] dbRaftDistanceArr3 = dbRaftDistance3.split(",");
        ;

        for (int i = 0; i < dbRaftDistanceArr1.length; i++) {
            hRaftDistance1.add(dbRaftDistanceArr1[i]);
        }

        for (int i = 0; i < dbRaftDistanceArr2.length; i++) {
            hRaftDistance2.add(dbRaftDistanceArr2[i]);
        }

        for (int i = 0; i < dbRaftDistanceArr3.length; i++) {
            hRaftDistance3.add(dbRaftDistanceArr3[i]);
        }

        raftDistancesHeavy.add(hRaftDistance1);
        raftDistancesHeavy.add(hRaftDistance2);
        raftDistancesHeavy.add(hRaftDistance3);
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

    private void calcShedCladding(double shedWidth, double shedLength, String SHED_CLADDING_BOARD_DIM) {
        String sCladBoardWidth = SHED_CLADDING_BOARD_DIM.substring(SHED_CLADDING_BOARD_DIM.length() - 3, SHED_CLADDING_BOARD_DIM.length());
        int cladBoardWidth = Integer.parseInt(sCladBoardWidth) / 10; //Convert to cm
        int claddingBoardsShedWidth = (int) (shedWidth / cladBoardWidth);
        int claddingBoardsShedLength = (int) (shedLength / cladBoardWidth);
        int noOfCladdingBoards = (claddingBoardsShedLength + claddingBoardsShedWidth) * 2; //Calculate all four sides of the shed
        this.noOfCladdingBoards = noOfCladdingBoards;
    }

    /**
     * Outputs number of laths required for shed. Currently set to a fixed number of 12
     */
    private void calculateShedWallLaths() {
        this.shedWallLaths = 12;
    }

    /**
     * Calculates the amount of roof tiles OR trapez plates depending on the roof type
     *
     * @param carportLength      The customer selected carport length
     * @param calcRaftLength     The calculated raft length
     * @param ROOF_TILE_LENGTH   Length of a roof tile (Assumed value from FOG website)
     * @param ROOF_TILE_WIDTH    Width of a roof tile (Assumed value from FOG website)
     * @param ROOF_TRAPEZ_LENGTH Length of a roof trapez plate (Assumed value from FOG website)
     * @param ROOF_TRAPEZ_WIDTH  Width of a roof trapez plate (Assumed value from FOG website)
     */
    private void calculateRoofCladdingArea(int carportLength, double calcRaftLength, double ROOF_TILE_LENGTH, double ROOF_TILE_WIDTH, double ROOF_TRAPEZ_LENGTH, double ROOF_TRAPEZ_WIDTH) {

        if (raisedRoof) {
            int columnsOfTiles = (int) (carportLength / ROOF_TILE_WIDTH);
            int rowsOfTiles = (int) ((calcRaftLength * 100) / ROOF_TILE_LENGTH); //Converting raft length to cm
            int totalNumberOfRoofTiles = rowsOfTiles * columnsOfTiles * 2; //Multiply by 2 to get both sides of the roof
            this.totalNumberOfRoofTiles = totalNumberOfRoofTiles;
        } else if (!raisedRoof) {
            double trapezPlateSquareArea = ROOF_TRAPEZ_LENGTH * ROOF_TILE_WIDTH;
            int noOfTrapezPlates = (int) ((carportLength * carportWidth) / trapezPlateSquareArea);
            this.totalNumberOfRoofTrapezPlates = noOfTrapezPlates;
        }
    }

    /**
     * Calculates the required amount of wall-laths for the shed
     *
     * @param shedWidth  The customer selected shed width
     * @param shedLength The customer selected shed length
     */
    private void calcWallLaths(double shedWidth, double shedLength) {
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

    //TO-DO - Henter spærets dimensioner. Dobbelttjek at substring metoden altid vil virke. Det er ikke den bedste løsning...
    private void getRaftDimension(String s) {
        String raftDim = s.substring(0, 8);
        this.raftDimension = raftDim;
    }

    /**
     * Calculates the required amount of rafts
     *
     * @param carportLength The customer selected carport length
     * @param raftDistance  The calculated raft distance
     */
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

    /**
     * Calculates the required amount of roof laths and the necessary distance between them
     *
     * @param calcRaftLength
     */
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