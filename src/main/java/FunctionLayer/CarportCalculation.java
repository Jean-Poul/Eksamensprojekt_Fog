package FunctionLayer;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Contains methods for calculating every dimension of the total carport solution.
 * The calculations are used to generate drawings, bill-of-material and the final quote.
 *
 * @author group
 */
public class CarportCalculation {

    //##########################################################
    // FINALS below are assumptions of standard dimensions.
    // SHOULD BE RETRIEVED FROM DATABASE (And should probably not be FINAL)
    //##########################################################
    private static final int BOTTOM_LATHSPAN = 35;
    private static final int BOTTOM_LATHS = 2;
    private static final double TOP_LATH_GAP = 3;
    private static final double AVG_LATH_SPAN = 30;

    private static final double ROOF_TILE_LENGTH = 25;
    private static final double ROOF_TILE_WIDTH = 20;
    private static final double ROOF_TRAPEZ_LENGTH = 240;
    private static final double ROOF_TRAPEZ_WIDTH = 109;

    private static final String SHED_CLADDING_BOARD_DIM = "19x100";

    private static final String BEAM_DIMENSION_HEAVY = "125 x 125";
    private static final String BEAM_DIMENSION_LIGHT = "100 x 100";

    // ###########################################
    //  CUSTOMER SELECTED CRITERIA [cm]
    // ###########################################
    private boolean raisedRoof; //If true, then roofHeavy is true as well (Is set in constructor)
    private int carportLength;
    private int carportWidth;
    private int customerRoofAngle;
    private double shedLength;
    private double shedWidth;

    // ###########################################
    //  ITEM TYPES GETTING ASSIGNED WITH CORRESPONDING ARTICLE NO. FROM DB
    // ###########################################
    private int raftType;
    private int shedWallLathType;
    private int shedCladdingBoardType;
    private int roofLathType;
    private int supportStrapType;
    private int roofCladType;
    private int sternBoardType;
    private int beamType;


    // ###########################################
    //  RAFT CALCULATIONS (Qty & Length [cm])
    // ###########################################
    private double raftLength;
    private int noOfRafts;
    private double raftDistance;
    private double avgRaftDistance;
    private String raftDimension;
    private double horizontalRaftLength;
    private double verticalRaftLength;
    private int noOfHorizontalRafts;
    private int noOfVerticalRafts;

    // ###########################################
    //  SHED CALCULATIONS [cm]
    // ###########################################
    private int shedWallLaths;
    private int noOfCladdingBoards;

    // ###########################################
    //  ROOF CALCULATIONS [cm]
    // ###########################################
    private double calcRoofHeight;
    private int noOfLaths;
    private double lathSpan;
    private double supportingStrapLength;
    private int totalNumberOfRoofTiles;
    private int totalNumberOfRoofTrapezPlates;
    private String roofCladdingType;
    private int calcAngle;

    // ###########################################
    //  ASSORTED STRUCTURAL & ACCESSORIES
    // ###########################################
    private boolean roofHeavy;
    private int sternBoardLength;
    private String beamDimension;
    private int noOfBeams;

    // ###########################################
    //  ENGINEERING TABLES FROM DATABASE
    // ###########################################
    private List<BeamDimensionHeavy> raftStringHeavy;
    private List<BeamDimensionLight> raftStringLight;
    ArrayList<ArrayList<String>> raftDistancesLight = new ArrayList<>(); //[FIX] Contains the raft distances to be selected from depending on raft length and roof type (Light/heavy)
    ArrayList<ArrayList<String>> raftDistancesHeavy = new ArrayList<>();
    Map<Integer, Double> angleAndFactor = new HashMap<Integer, Double>(); //[FIX] Contains the roof slant angle and the corresponding factor to multiply with - Should be retrieved and populated from DB.

    //Formats decimal numbers to two decimals.
    DecimalFormat df = new DecimalFormat("#.##");

    //##########################################################
    // CONSTRUCTOR (Probably easier to make a new one when DB connection is running)
    //##########################################################

//    //The "Real" constructor
//    public CarportCalculation() throws SQLException {
//
//        LogicFacade logFac = new LogicFacade();
//        this.angleAndFactor = logFac.getPitchFactor(); //Get pitch-factor table
//
//        /*
//         * Methods below should be created in LogicFacade to get relevant customer queries.
//         */
//
//        //this.carportWidth = logFac.getCusCarportWidth();
//        //this.carportLength = logFac.getCusCarportLength();
//
//        //this.carportShedLength = logFac.getCusShedLength();
//        //this.carportShedWidth = logFac.getCusShedWith();
//
//        //this.customerRoofAngle = logFac.getCusPitch()
//        calcRoofAngle(customerRoofAngle);
//
//
////        If roof is raised, then it's heavy!
////        if(logFac.getCusPitch() > 0){
////            this.raisedRoof = true;
////            this.roofHeavy = true;
////        } else {
////            this.raisedRoof = false;
////            this.roofHeavy = false;
////        }
//
//        //If roof is raised calculate raft spacing and get dimensions from DB.
//        if (raisedRoof) {
//            calcRaftLength(carportWidth, customerRoofAngle, calcAngle);
//            if (roofHeavy) {
//                this.raftStringHeavy = logFac.getBeamDimensionHeavy(raftLength);
//                raftDistance = Double.parseDouble(String.valueOf(raftStringHeavy.get(1)));
//            } else {
//                this.raftStringLight = logFac.getBeamDimensionLight(raftLength);
//                raftDistance = Double.parseDouble(String.valueOf(raftStringLight.get(1)));
//            }
//        }
//    }

//    ##########################################################
//     TEST CONSTRUCTOR - PAY ATTENTION TO METHOD EXECUTION ORDER
//    ##########################################################
    public CarportCalculation() {
    /*
    ########################
    ###   TEST EXAMPLE!  ###
    ########################
     */

        carportLength = 850;
        carportWidth = 650;
        customerRoofAngle = 30;
        shedLength = 290;
        shedWidth = 330;
        roofHeavy = false; //Should be determined - maybe depending on flat/raised roof?
        raisedRoof = true;

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
        selectRaftDimAndSpacing(roofHeavy, raftLength);
        noOfRafts(carportLength, raftDistance);
        calcRoofLaths(raftLength);
        calculateShedWallLaths();
        calcShedCladding(shedLength, shedWidth, SHED_CLADDING_BOARD_DIM);
        calcRoofCladdingArea(carportLength, raftLength, ROOF_TILE_LENGTH, ROOF_TILE_WIDTH, ROOF_TRAPEZ_LENGTH, ROOF_TRAPEZ_WIDTH, customerRoofAngle);
        calcNoOfBeamsAndDim(shedLength);
        calculateSupportingStrap(carportWidth, carportLength);

        System.out.println("Tungt tag?: " + roofHeavy);
        System.out.println("Systemet udregner antal spær: " + df.format(noOfRafts) + " stk");
        System.out.println("Systemet udregner spærdimension " + raftDimension + " mm");
        System.out.println("Systemet udregner spærlængde: " + df.format(raftLength) + " cm");
        System.out.println("Systemet udregner spærafstand: " + (avgRaftDistance) + " cm");
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

    /**
     * Calculates the raft length
     *
     * @param carportWidth        Customer selected carport width.
     * @param customerRoofAngle   Customer selected roof slant angle.
     * @param calculatedRoofAngle The calculated upper roof angle (Comes from calcRoofAngle().
     */
    private void calcRaftLength(double carportWidth, int customerRoofAngle, int calculatedRoofAngle) {

        //Determine sloped raft length
        double custRoofAngleRadian = Math.toRadians(customerRoofAngle);
        double calcRoofAngleRadian = Math.toRadians(calculatedRoofAngle);
        double calcRaftLength = (carportWidth * Math.sin(custRoofAngleRadian)) / (Math.sin(calcRoofAngleRadian));
        calcRaftLength = calcRaftLength * angleAndFactor.get(customerRoofAngle);
        raftLength = calcRaftLength;

        //Determine length of horizontal rafts
        horizontalRaftLength = this.carportWidth;

        //Determine height of vertical rafts
        verticalRaftLength = getCalcRoofHeight();
    }

    //[FIX]

    /**
     * Selects the correct raft spacing and dimension using arraylists with data from the database.
     *
     * @param roofHeavy      Determines if the roof type is heavy or light (Used to select from correct arraylist)
     * @param calcRaftLength Used to select from correct arraylist
     * @author Mick
     */
    private void selectRaftDimAndSpacing(boolean roofHeavy, double calcRaftLength) {
        double raftLengthM = calcRaftLength / 100; //Convert calcRaftLength to meters
        if (!roofHeavy) {
            raftDistance = 0.8;
            raftDimension = "45 x 120";
            raftType = 20;
        } else if (roofHeavy) {
            raftDistance = 0.6;
            raftDimension = "95 x 120";
            raftType = 21;

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
        shedWallLaths = 12;
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
            roofCladdingType = "tagsten";
        } else if (!raisedRoof) {
            double trapezPlateSquareArea = ROOF_TRAPEZ_LENGTH * ROOF_TILE_WIDTH;
            int noOfTrapezPlates = (int) ((carportLength * carportWidth) / trapezPlateSquareArea);
            totalNumberOfRoofTrapezPlates = noOfTrapezPlates;
            roofCladdingType = "trapezplader";
        }
    }

//    /**
//     * Calculates the required amount of wall-laths for the shed
//     *
//     * @param shedWidth  The customer selected shed width
//     * @param shedLength The customer selected shed length
//     */
//    private void calcWallLaths(double shedWidth, double shedLength) {
//        double wallLathQty = (shedLength + shedWidth) * 2;
//        this.wallLath = wallLathQty;
//    }

    /**
     * Calculates the required no. of beams and their dimension. (Will always be 4 unless there's a shed - then there's 8)
     *
     * @param shedLength The user selected shed length
     */
    private void calcNoOfBeamsAndDim(double shedLength) {
        if (shedLength > 0) {
            noOfBeams = 8;
        } else {
            noOfBeams = 4;
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

        double totalSupportStrap = (carportLength * 2) + carportWidth;
        supportingStrapLength = totalSupportStrap;
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
        double sternBoardsLength = this.carportLength * 2;
        this.sternBoardLength = sternBoardLength;
    }

    /**
     * Calculates the required amount of rafts
     *
     * @param carportLength The customer selected carport length
     * @param raftDistance  The calculated raft distance
     */
    private void noOfRafts(double carportLength, double raftDistance) {

        //Determine no. of sloped rafts
        int noOfRafts = (int) Math.ceil(carportLength / (raftDistance * 100)); //Convert raftdistance to cm
        double avgRaftDistance = this.carportLength / noOfRafts;
        this.avgRaftDistance = avgRaftDistance;
        this.noOfRafts = noOfRafts;

        //Determine no. of horizontal and vertical rafts
        noOfHorizontalRafts = this.noOfRafts;
        noOfVerticalRafts = this.noOfRafts;
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
        double topLathSpan = TOP_LATH_GAP;
        double avgLathSpan = AVG_LATH_SPAN;
        double calcLaths = Math.round((calcRaftLength - bottomLathSpan - topLathSpan) / avgLathSpan);
        double calcLathSpan = (calcRaftLength - bottomLathSpan - topLathSpan) / calcLaths;
        int actualLaths = (int) ((calcLaths + bottomLaths) * 2);

        noOfLaths = actualLaths;
        lathSpan = calcLathSpan;
    }

// ################################
// #      GETTERS & SETTERS       #
// ################################

    public void setRaftType(int raftType) {
        this.raftType = raftType;
    }

    public void setShedWallLathType(int shedWallLathType) {
        this.shedWallLathType = shedWallLathType;
    }

    public void setShedCladdingBoardType(int shedCladdingBoardType) {
        this.shedCladdingBoardType = shedCladdingBoardType;
    }

    public void setRoofLathType(int roofLathType) {
        this.roofLathType = roofLathType;
    }

    public void setSupportStrapType(int supportStrapType) {
        this.supportStrapType = supportStrapType;
    }

    public void setRoofCladType(int roofCladType) {
        this.roofCladType = roofCladType;
    }

    public void setSternBoardType(int sternBoardType) {
        this.sternBoardType = sternBoardType;
    }

    public void setBeamType(int beamType) {
        this.beamType = beamType;
    }

    public boolean isRaisedRoof() {
        return raisedRoof;
    }

    public boolean isRoofHeavy() {
        return roofHeavy;
    }

    public int getCarportLength() {
        return carportLength;
    }

    public int getCarportWidth() {
        return carportWidth;
    }

    public int getCustomerRoofAngle() {
        return customerRoofAngle;
    }

    public double getShedLength() {
        return shedLength;
    }

    public double getShedWidth() {
        return shedWidth;
    }

    public String getRoofCladdingType() {
        return roofCladdingType;
    }

    public int getCalcAngle() {
        return calcAngle;
    }

    public double getRaftLength() {
        return raftLength;
    }

    public double getNoOfRafts() {
        return noOfRafts;
    }

    public double getAvgRaftDistance() {
        return avgRaftDistance;
    }

    public String getRaftDimension() {
        return raftDimension;
    }

    public double getCalcRoofHeight() {
        return calcRoofHeight;
    }

    public double getSupportingStrapLength() {
        return supportingStrapLength;
    }

    public int getSternBoardLength() {
        return sternBoardLength;
    }

    public int getTotalNumberOfRoofTiles() {
        return totalNumberOfRoofTiles;
    }

    public int getTotalNumberOfRoofTrapezPlates() {
        return totalNumberOfRoofTrapezPlates;
    }

    public int getShedWallLaths() {
        return shedWallLaths;
    }

    public int getNoOfCladdingBoards() {
        return noOfCladdingBoards;
    }

    public String getBeamDimension() {
        return beamDimension;
    }

    public int getNoOfBeams() {
        return noOfBeams;
    }

    public int getNoOfLaths() {
        return noOfLaths;
    }

    public double getLathSpan() {
        return lathSpan;
    }

    public int getNoOfHorizontalRafts() {
        return noOfHorizontalRafts;
    }

    public double getHorizontalRaftLength() {
        return horizontalRaftLength;
    }

    public int getNoOfVerticalRafts() {
        return noOfVerticalRafts;
    }

    public double getVerticalRaftLength() {
        return verticalRaftLength;
    }

    public int getRaftType() {
        return raftType;
    }

    public int getShedWallLathType() {
        return shedWallLathType;
    }

    public int getShedCladdingBoardType() {
        return shedCladdingBoardType;
    }

    public int getRoofLathType() {
        return roofLathType;
    }

    public int getSupportStrapType() {
        return supportStrapType;
    }

    public int getRoofCladType() {
        return roofCladType;
    }

    public int getSternBoardType() {
        return sternBoardType;
    }

}