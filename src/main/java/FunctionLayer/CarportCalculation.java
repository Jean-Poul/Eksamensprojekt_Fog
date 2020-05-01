package FunctionLayer;

import java.text.DecimalFormat;
import java.util.*;


// ######################################
// #           TO BE DONE               #
// ######################################

/**
 * KLASSEN HAR BRUG FOR FØLGENDE INFORMATIONER FRA DATABASEN.
 * De skal altså hentes med datamapper igennem logicfacade og instantieres som objekter i denne klasse.
 * int          - carport længde
 * int          - carport bredde
 * boolean      - carport tag fladt/rejst
 * int          - kundens valgte hældning
 * int          - skur bredde
 * int          - skur længde
 * HashMap      - anglesAndFactor (format: [int 15], double 1.0])
 * ArrayList    - RaftDistancesLightRoof (format: ["let"],["44 x 120"],["0.4"],["10.26])
 * ArrayList    - RaftDistancesHeavyRoof - as above
 */

//TO-DO Systemet skal vælge det rigtige spær!

//[FIX] Shed dimensions should be validated to fit inside carport. TBD on .jsp maybe?
//[FIX] Calculate brackets, bolts, screws and washers - perhaps make a complete solution (Assumption)
//[FIX] Door (Cladding + handle, hinges and beams for "Z") - perhaps make a complete solution (Assumption)

/**
 * Contains methods for calculating every dimension of the total carport solution.
 * The calculations are used to generate drawings, bill-of-material and the final quote.
 *
 * @author group
 */
public class CarportCalculation {

    //##########################################################
    // FINALS below are assumptions of standard dimensions.
    // Should probably be in DB if FOG needs to change these later.
    //##########################################################
    private static final int BOTTOM_LATHSPAN = 35;
    private static final int BOTTOM_LATHS = 2;
    private static final double TOP_LATH = 3;
    private static final double AVG_LATH_SPAN = 30;
    private static final double ROOF_TILE_LENGTH = 25;
    private static final double ROOF_TILE_WIDTH = 20;
    private static final double ROOF_TRAPEZ_LENGTH = 240;
    private static final double ROOF_TRAPEZ_WIDTH = 109;
    private static final String SHED_CLADDING_BOARD_DIM = "19x100";
    private static final String BEAM_DIMENSION_HEAVY = "125 x 125";
    private static final String BEAM_DIMENSION_LIGHT = "100 x 100";

    //##########################################################
    // Class variables used for calculations
    //##########################################################
    private boolean raisedRoof = true;
    boolean roofHeavy;
    private int carportLength;
    private int carportWidth;
    private int customerRoofAngle;
    private double shedLength;
    private double shedWidth;
    private String roofCladdingType;
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
    private String beamDimension;
    private int noOfLaths;
    private double lathSpan;
    private int noOfBeams;

    //Formats decimal numbers to two decimals.
    DecimalFormat df = new DecimalFormat("#.##");

    //[FIX] Contains the roof slant angle and the corresponding factor to multiply with - Should be retrieved and populated from DB.
    HashMap<Integer, Double> angleAndFactor = new HashMap<Integer, Double>();

    //[FIX] Contains the raft distances to be selected from depending on raft length and roof type (Light/heavy)
    ArrayList<ArrayList<String>> raftDistancesLight = new ArrayList<>();
    ArrayList<ArrayList<String>> raftDistancesHeavy = new ArrayList<>();

    //##########################################################
    // CONSTRUCTOR (Probably easier to make a new one when DB connection is running)
    //##########################################################

    //##########################################################
    // TEST CONSTRUCTOR - PAY ATTENTION TO METHOD EXECUTION ORDER
    //##########################################################
    public CarportCalculation() {
    /*
    ########################
    ###   TEST EXAMPLE!  ###
    ########################
     */

        carportLength = 720;
        carportWidth = 650;
        customerRoofAngle = 30;
        shedLength = 690;
        shedWidth = 330;
        roofHeavy = false; //Should be determined - maybe depending on flat/raised roof?


        populateAngleAndFactor();
        calcRoofAngle(customerRoofAngle);
        populateRaftDistancesLight();
        populateRaftDistancesHeavy();
        //[FIX] These functions should be generated with data from the database!
        //Bear in mind, that some functions should be calculated before others.

        System.out.println("Lad os lave et test eksempel:");
        System.out.println("Kunden vælger en carport på: " + carportWidth + " x " + carportLength + " m med vinkel " + customerRoofAngle);

        calcRoofHeight(customerRoofAngle, carportWidth);
        calcRaftLength(carportWidth, customerRoofAngle, calcAngle);
        selectRaftDimAndSpacing(roofHeavy, calcRaftLength);
        noOfRafts(carportLength, raftDistance);
        calcRoofLaths(calcRaftLength);
        calculateShedWallLaths();
        calcShedCladding(shedLength, shedWidth, SHED_CLADDING_BOARD_DIM);
        calcRoofCladdingArea(carportLength, calcRaftLength, ROOF_TILE_LENGTH, ROOF_TILE_WIDTH, ROOF_TRAPEZ_LENGTH, ROOF_TRAPEZ_WIDTH, customerRoofAngle);
        calcNoOfBeamsAndDim(shedLength);

        System.out.println("Tungt tag?: " + roofHeavy);
        System.out.println("Systemet udregner antal spær: " + df.format(noOfRafts) + " stk");
        System.out.println("Systemet udregner spærdimension " + raftDimension + " mm");
        System.out.println("Systemet udregner spærlængde: " + df.format(calcRaftLength) + " cm");
        System.out.println("Systemet udregner spærafstand: " + (raftDistance * 100) + " cm");
        System.out.println("Systemet udregner tagets højde: " + df.format(calcRoofHeight) + " cm");
        System.out.println("Systemet udregner antal lægter " + noOfLaths + " stk");
        System.out.println("Systemet udregner lægteafstand: " + df.format(lathSpan) + " cm");
        System.out.println("Systemet udregner lægtelængde: " + carportLength + " cm");
        System.out.println("Der skal bruges " + totalNumberOfRoofTiles + " " + roofCladdingType);
        System.out.println("Systemet udregner antal beklædningsbræt til skur: " + noOfCladdingBoards + " stk");
        System.out.println("Systemet udregner antal løsholter: " + this.shedWallLaths + " stk");
        System.out.println("Der skal bruges " + noOfBeams + " søjler i størrelsen " + beamDimension);
    }

    //[FIX] Populates raftDistance lists for light roof types. This is a practical example - Should be retrieved and populatedfrom DB
    private void populateRaftDistancesLight() {
        ArrayList<String> lRaftDistance1 = new ArrayList<String>();
        ArrayList<String> lRaftDistance2 = new ArrayList<String>();
        ArrayList<String> lRaftDistance3 = new ArrayList<String>();
        ArrayList<String> lRaftDistance4 = new ArrayList<String>();

        String dbRaftDistance1 = "let,44 x 120,0.4,10.26"; //This should come fromt the DB
        String dbRaftDistance2 = "let,50 x 140,0.6,6.48"; //This should come fromt the DB
        String dbRaftDistance3 = "let,45 x 120,0.8,2.26"; //This should come fromt the DB
        String dbRaftDistance4 = "let,45 x 195,0.4,4.52"; //This should come fromt the DB

        String[] dbRaftDistanceArr1 = dbRaftDistance1.split(",");
        String[] dbRaftDistanceArr2 = dbRaftDistance2.split(",");
        String[] dbRaftDistanceArr3 = dbRaftDistance3.split(",");
        String[] dbRaftDistanceArr4 = dbRaftDistance4.split(",");

        for (int i = 0; i < dbRaftDistanceArr1.length; i++) {
            lRaftDistance1.add(dbRaftDistanceArr1[i]);
        }

        for (int i = 0; i < dbRaftDistanceArr2.length; i++) {
            lRaftDistance2.add(dbRaftDistanceArr2[i]);
        }

        for (int i = 0; i < dbRaftDistanceArr3.length; i++) {
            lRaftDistance3.add(dbRaftDistanceArr3[i]);
        }

        for (int i = 0; i < dbRaftDistanceArr4.length; i++) {
            lRaftDistance3.add(dbRaftDistanceArr4[i]);
        }

        raftDistancesLight.add(lRaftDistance1);
        raftDistancesLight.add(lRaftDistance2);
        raftDistancesLight.add(lRaftDistance3);
        raftDistancesLight.add(lRaftDistance4);

//        Collections.sort(raftDistancesLight, new Comparator<ArrayList<String>>() {
//            @Override
//            public int compare(ArrayList<String> a, ArrayList<String> b) {
//                return a.get(3).compareTo(b.get(3));
//            }
//        });
    }

    //[FIX] Populates raftDistance lists for heavy roof types. This is a practical example - Should be retrieved and populated from DB
    private void populateRaftDistancesHeavy() {
        ArrayList<String> hRaftDistance1 = new ArrayList<String>();
        ArrayList<String> hRaftDistance2 = new ArrayList<String>();
        ArrayList<String> hRaftDistance3 = new ArrayList<String>();
        ArrayList<String> hRaftDistance4 = new ArrayList<String>();

        String dbRaftDistance1 = "tung,45 x 120,0.4,2.43"; //This should come fromt the DB
        String dbRaftDistance2 = "tung,45 x 120,0.6,2.13"; //This should come fromt the DB
        String dbRaftDistance3 = "tung,45 x 120,0.8,1.93"; //This should come fromt the DB
        String dbRaftDistance4 = "tung,45 x 195,0.4,3.94"; //This should come fromt the DB

        String[] dbRaftDistanceArr1 = dbRaftDistance1.split(",");
        String[] dbRaftDistanceArr2 = dbRaftDistance2.split(",");
        String[] dbRaftDistanceArr3 = dbRaftDistance3.split(",");
        String[] dbRaftDistanceArr4 = dbRaftDistance4.split(",");

        for (int i = 0; i < dbRaftDistanceArr1.length; i++) {
            hRaftDistance1.add(dbRaftDistanceArr1[i]);
        }

        for (int i = 0; i < dbRaftDistanceArr2.length; i++) {
            hRaftDistance2.add(dbRaftDistanceArr2[i]);
        }

        for (int i = 0; i < dbRaftDistanceArr3.length; i++) {
            hRaftDistance3.add(dbRaftDistanceArr3[i]);
        }

        for (int i = 0; i < dbRaftDistanceArr4.length; i++) {
            hRaftDistance4.add(dbRaftDistanceArr4[i]);
        }

        raftDistancesHeavy.add(hRaftDistance1);
        raftDistancesHeavy.add(hRaftDistance2);
        raftDistancesHeavy.add(hRaftDistance3);
        raftDistancesHeavy.add(hRaftDistance4);

//        Collections.sort(raftDistancesHeavy, new Comparator<ArrayList<String>>() {
//            @Override
//            public int compare(ArrayList<String> a, ArrayList<String> b) {
//                return a.get(3).compareTo(b.get(3));
//            }
//        });
    }

    //[FIX] Set roof slant multiplication factor according to documentation - Should be retrieved and populated from DB
    private void populateAngleAndFactor() {
        angleAndFactor.put(15, 1.0);
        angleAndFactor.put(20, 0.97);
        angleAndFactor.put(25, 0.94);
        angleAndFactor.put(30, 0.89);
        angleAndFactor.put(35, 0.84);
        angleAndFactor.put(40, 0.79);
        angleAndFactor.put(45, 0.72);
    }



    //[FIX]
    /**
     * Selects the correct raft spacing and dimension using arraylists with data from the database.
     *
     * @param roofHeavy      Determines if the roof type is heavy or light (Used to select from correct arraylist)
     * @param calcRaftLength Used to select from correct arraylist
     */
    private void selectRaftDimAndSpacing(boolean roofHeavy, double calcRaftLength) {
        double raftLengthM = calcRaftLength / 100; //Convert calcRaftLength to meters
        if (!roofHeavy) {
            if (raftLengthM <= Double.parseDouble(String.valueOf(raftDistancesLight.get(0).get(3)))) {
                this.raftDistance = Double.parseDouble(String.valueOf(raftDistancesLight.get(0).get(2))); //Get the raft spacing
                this.raftDimension = (String) raftDistancesLight.get(0).get(1); //Get the raft dimensions
            } else if (raftLengthM >= Double.parseDouble(String.valueOf(raftDistancesLight.get(raftDistancesLight.size() - 1).get(3)))) {
                this.raftDistance = Double.parseDouble(String.valueOf(raftDistancesLight.get(raftDistancesLight.size() - 1).get(2))); //Get the raft spacing
                this.raftDimension = (String) raftDistancesLight.get(raftDistancesLight.size() - 1).get(1); //Get the raft dimensions
            } else {
                for (int i = 1; i < raftDistancesLight.size(); i++) {
                    if (raftLengthM > Double.parseDouble(String.valueOf(raftDistancesLight.get(i).get(3)))
                            &&
                            raftLengthM < Double.parseDouble(String.valueOf(raftDistancesLight.get(0 + i).get(3)))) {
                        this.raftDistance = Double.parseDouble(String.valueOf(raftDistancesLight.get(i + 1).get(2))); //Get the raft spacing
                        this.raftDimension = (String) raftDistancesLight.get(i).get(1); //Get the raft dimensions
                    }
                }
            }
        } else if (roofHeavy) {
            if (raftLengthM < Double.parseDouble(String.valueOf(raftDistancesHeavy.get(0).get(3)))) {
                this.raftDistance = Double.parseDouble(String.valueOf(raftDistancesHeavy.get(0).get(2))); //Get the raft spacing
                this.raftDimension = (String) raftDistancesHeavy.get(0).get(1); //Get the raft dimensions
            } else {
                for (int i = 0; i < raftDistancesHeavy.size(); i++) {

                    if (raftLengthM > Double.parseDouble(String.valueOf(raftDistancesHeavy.get(i).get(3)))
                            &&
                            raftLengthM < Double.parseDouble(String.valueOf(raftDistancesHeavy.get(0 + i).get(3)))) {
                        this.raftDistance = Double.parseDouble(String.valueOf(raftDistancesHeavy.get(i + 1).get(2))); //Get the raft spacing
                        this.raftDimension = (String) raftDistancesHeavy.get(i).get(1); //Get the raft dimensions
                    }
                }
            }

        } else {
            //If the roof is neither light or heavy, something went wrong bigtime.
            //Need an exception here.
        }
    }




    /**
     * Calculates the required amount of vertical boards for cladding the shed
     *
     * @param shedWidth               The customer selected shed width
     * @param shedLength              The customer selected shed length
     * @param SHED_CLADDING_BOARD_DIM the assumed cladding board dimensions.
     */
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
     * Determines if roof is flat/raised and calculates the amount of roof tiles OR trapez plates depending on the roof type
     *
     * @param carportLength      The customer selected carport length
     * @param calcRaftLength     The calculated raft length
     * @param ROOF_TILE_LENGTH   Length of a roof tile (Assumed value from FOG website)
     * @param ROOF_TILE_WIDTH    Width of a roof tile (Assumed value from FOG website)
     * @param ROOF_TRAPEZ_LENGTH Length of a roof trapez plate (Assumed value from FOG website)
     * @param ROOF_TRAPEZ_WIDTH  Width of a roof trapez plate (Assumed value from FOG website)
     */
    private void calcRoofCladdingArea(int carportLength, double calcRaftLength, double ROOF_TILE_LENGTH, double ROOF_TILE_WIDTH, double ROOF_TRAPEZ_LENGTH, double ROOF_TRAPEZ_WIDTH, int customerRoofAngle) {

        if (customerRoofAngle > 0) {
            raisedRoof = true;
        }

        if (raisedRoof) {
            int columnsOfTiles = (int) (carportLength / ROOF_TILE_WIDTH);
            int rowsOfTiles = (int) ((calcRaftLength) / ROOF_TILE_LENGTH);
            int totalNumberOfRoofTiles = rowsOfTiles * columnsOfTiles * 2; //Multiply by 2 to get both sides of the roof
            this.totalNumberOfRoofTiles = totalNumberOfRoofTiles;
            this.roofCladdingType = "tagsten";
        } else if (!raisedRoof) {
            double trapezPlateSquareArea = ROOF_TRAPEZ_LENGTH * ROOF_TILE_WIDTH;
            int noOfTrapezPlates = (int) ((carportLength * carportWidth) / trapezPlateSquareArea);
            this.totalNumberOfRoofTrapezPlates = noOfTrapezPlates;
            this.roofCladdingType = "trapezplader";
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
     * Calculates the required no. of beams and their dimension. (Will always be 4 unless there's a shed - then there's 8)
     *
     * @param shedLength The user selected shed length
     */
    private void calcNoOfBeamsAndDim(double shedLength) {
        if (shedLength > 0) {
            this.noOfBeams = 8;
        } else {
            this.noOfBeams = 4;
        }
        if (roofHeavy) {
            beamDimension = BEAM_DIMENSION_HEAVY;
        } else if (!roofHeavy) {
            beamDimension = BEAM_DIMENSION_LIGHT;
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

    /**
     * Calculates the total length of stern boards
     *
     * @param raftLength the calculated raftlength which matches the length of the roof stern.
     */
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

    /**
     * Calculates the required amount of rafts
     *
     * @param carportLength The customer selected carport length
     * @param raftDistance  The calculated raft distance
     */
    private void noOfRafts(double carportLength, double raftDistance) {
        double noOfRafts = Math.round(carportLength / (raftDistance * 100)); //Convert raftdistance to cm
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
        double calcLathSpan = (calcRaftLength - bottomLathSpan - topLathSpan) / calcLaths;
        int actualLaths = (int) ((calcLaths + bottomLaths) * 2);

        this.noOfLaths = actualLaths;
        this.lathSpan = calcLathSpan;
    }
}