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

    //User data (In order to reference what user ID the calculation belongs to
    int orderID;

    //Standard dimensions - retrieved from DB (Assumptions)
    int bottomLathSpan;
    int bottomLaths;
    double topLathGap;
    double avgLathSpan;
    double roofTileLength;
    double roofTileWidth;
    double roofTrapezLength;
    double roofTrapezWidth;
    String shedCladdingBoardDim;
    String beamDimensionHeavy;
    String beamDimensionLight;

    //  ITEM TYPES GETTING ASSIGNED WITH CORRESPONDING ARTICLE NO. FROM DB (ASSIGMENT SHOULD BE DONE IN EACH METHOD)
    private int raftType;
    private int shedWallLathType;
    private int shedCladdingBoardType;
    private int roofLathType;
    private int supportStrapType;
    private int roofCladType;
    private int sternBoardType;
    private int beamType;
    private int doorKit;
    private int bracketKit;
    private int screwKitRoofRaised;
    private int screwKitTrapez;
    private int screwKitGenericRoofBrackets;
    private int screwKitGenericRoofLaths;
    private int screwKitShedDoorCladdingInside;
    private int screwKitShedDoorCladdingOutside;

    //  CUSTOMER SELECTED CRITERIA [cm]
    private boolean raisedRoof; //If true, then roofHeavy is true as well (Is set in constructor)
    private int carportLength;
    private int carportWidth;
    private int customerRoofAngle;
    private int shedLength;
    private int shedWidth;

    //  RAFT CALCULATIONS (Qty & Length [cm])
    private double raftLength;
    private int noOfRafts;
    private double raftDistance;
    private double avgRaftDistance;
    private String raftDimension;
    private double horizontalRaftLength;
    private double verticalRaftLength;
    private int noOfHorizontalRafts;
    private int noOfVerticalRafts;

    //  SHED CALCULATIONS [cm]
    private int noOfShedWallLaths = 12; //Assumption
    private double shedWallLathsTotalLength;
    private int noOfCladdingBoardsTotal;
    private double claddingBoardOverlap = 2.5;
    private int noOfCladdingBoardsWidth;
    private int noOfCladdingBoardsLength;

    //  ROOF CALCULATIONS [cm]
    private double calcRoofHeight;
    private int noOfLaths;
    private double lathSpan;
    private double supportingStrapLength;
    private int totalNumberOfRoofTiles;
    private int totalNumberOfRoofTrapezPlates;
    private String roofCladdingType;
    private int calcAngle;

    //  ASSORTED STRUCTURAL & ACCESSORIES
    private boolean roofHeavy;
    private double sternBoardLength;
    private String beamDimension;
    private int noOfBeams;

    //  ENGINEERING TABLES FROM DATABASE
    private List<BeamDimensionHeavy> raftStringHeavy;
    private List<BeamDimensionLight> raftStringLight;

    ArrayList<ArrayList<String>> raftDistancesLight = new ArrayList<>(); //[FIX] Contains the raft distances to be selected from depending on raft length and roof type (Light/heavy)
    ArrayList<ArrayList<String>> raftDistancesHeavy = new ArrayList<>();


    //Formats decimal numbers to two decimals.
    DecimalFormat df = new DecimalFormat("#.##");

    //private LogicFacade log = new LogicFacade();

    //Fetch standard dimensions from db (Assumptions)
    //private List<StandardDimensions> standardDimensions = log.getStandardDimensions();
    private List<StandardDimensions> standardDimensions = LogicFacade.getStandardDimensions();

    //Contains the roof slant angle and the corresponding factor to multiply with
    //private Map<Integer, Double> angleAndFactor = log.getPitchFactor();
    private Map<Integer, Double> angleAndFactor = LogicFacade.getPitchFactor();

    public CarportCalculation(int orderID) throws LoginSampleException {

        this.orderID = orderID;
        //this.userPropositionID = user_proposition_Id; //How do we get this (Remember to add to parameter)?

        //Set standard dimensions from database (Assumptions)
        this.bottomLathSpan = standardDimensions.get(0).getBottom_lathspan();
        this.bottomLaths = standardDimensions.get(0).getBottom_laths();
        this.topLathGap = standardDimensions.get(0).getTop_lath_gap();
        this.avgLathSpan = standardDimensions.get(0).getAvg_lath_span();
        this.roofTileLength = standardDimensions.get(0).getRoof_tile_length();
        this.roofTileWidth = standardDimensions.get(0).getRoof_tile_width();
        this.roofTrapezLength = standardDimensions.get(0).getRoof_trapez_length();
        this.roofTrapezWidth = standardDimensions.get(0).getRoof_trapez_width();
        this.shedCladdingBoardDim = standardDimensions.get(0).getShed_claddeing_board_dim();
        this.beamDimensionHeavy = standardDimensions.get(0).getBeam_dimension_heavy();
        this.beamDimensionLight = standardDimensions.get(0).getBeam_dimension_light();

//        this.carportLength = log.getUserProposition(orderID).get(0).getCarport_length();
//        this.carportWidth = log.getUserProposition(orderID).get(0).getCarport_width();
//        this.customerRoofAngle = log.getUserProposition(orderID).get(0).getPitch();
//        this.shedLength = log.getUserProposition(orderID).get(0).getShed_length();
//        this.shedWidth = log.getUserProposition(orderID).get(0).getShed_width();

        this.carportLength = LogicFacade.getUserProposition(orderID).get(0).getCarport_length();
        this.carportWidth = LogicFacade.getUserProposition(orderID).get(0).getCarport_width();
        this.customerRoofAngle = LogicFacade.getUserProposition(orderID).get(0).getPitch();
        this.shedLength = LogicFacade.getUserProposition(orderID).get(0).getShed_length();
        this.shedWidth = LogicFacade.getUserProposition(orderID).get(0).getShed_width();

        //Asosign accessories that follow all types of carprts
        this.screwKitGenericRoofBrackets = 9;
        this.screwKitGenericRoofLaths = 10;
        this.bracketKit = 34;

        //Determine if there's a shed and assign according item packs
        if (this.shedLength > 0){
            this.screwKitShedDoorCladdingInside = 12;
            this.screwKitShedDoorCladdingOutside = 11;
            this.doorKit = 35;
        } else {
            this.shedLength = 0;
            this.shedWidth = 0;
        }



        //Determine if roof is raised or flat and assign according screw packs
        if (this.customerRoofAngle > 0) {
            this.raisedRoof = true;
            this.roofHeavy = true;
            this.screwKitRoofRaised = 8;
            calcRoofAngle(customerRoofAngle);
            calcRaftLength(carportWidth, customerRoofAngle, calcAngle);
        } else {
            this.raftLength = carportWidth;
            this.raisedRoof = false;
            this.roofHeavy = false;
            this.screwKitTrapez = 13;
            this.customerRoofAngle = 0;
        }

        //[FIX] TEMPORARY FIX UNTIL RAFTDISTANCES WORKS
        if (roofHeavy) {
            //this.raftDistance = log.getBeamDimensionHeavy(raftLength).get(0).getBeamSpacingHeavy(); //Out of bounds!?
            //this.raftDimension = log.getBeamDimensionHeavy(raftLength).get(0).getBeamDimensionHeavy();
//            LogicFacade.getBeamDimensionHeavy(raftLength).get(0).getBeamDimensionHeavy();
//            LogicFacade.getBeamDimensionHeavy(raftLength).get(0).getBeamSpacingHeavy();
            raftDistance = 1.0;
            raftDimension = "45 x 195";
        } else {
            //this.raftDistance = log.getBeamDimensionLight(raftLength).get(0).getBeamSpacingLight();
            //this.raftDimension = log.getBeamDimensionLight(raftLength).get(0).getBeamDimensionLight();
            raftDistance = 1.0;
            raftDimension = "45 x 120";
        }

        //Set rafttype depending on raftdistance table in database
        if(raftDimension.equalsIgnoreCase("45 x 120")){
            raftType = 20;
        } else if (raftDimension.equalsIgnoreCase("45 x 195")){
            raftType = 21;
        }

        //Begin calculations
        calcRoofHeight(customerRoofAngle, carportWidth);

        noOfRafts(carportLength, raftDistance);
        calcRoofLaths(raftLength);
        calculateShedWallLaths(shedLength, shedWidth);
        calcShedCladding(shedLength, shedWidth, shedCladdingBoardDim);
        calcRoofCladdingArea(carportLength, raftLength, roofTileLength, roofTileWidth, roofTrapezLength, roofTrapezWidth, customerRoofAngle);
        calcNoOfBeamsAndDim(shedLength);
        calculateSupportingStrap(carportWidth, carportLength);
        calcSternBoardLength(carportLength);

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
        this.raftLength = calcRaftLength;

        //Determine length of horizontal rafts
        horizontalRaftLength = this.carportWidth;

        //Determine height of vertical rafts
        verticalRaftLength = getCalcRoofHeight();
    }

    /**
     * Calculates the required amount of vertical boards for cladding the shed
     *
     * @param shedWidth               The customer selected shed width
     * @param shedLength              The customer selected shed length
     * @param SHED_CLADDING_BOARD_DIM the assumed cladding board dimensions.
     */
    private void calcShedCladding(double shedWidth, double shedLength, String SHED_CLADDING_BOARD_DIM) {
        shedCladdingBoardType = 31; //Set cladding item no.

        String sCladBoardWidth = SHED_CLADDING_BOARD_DIM.substring(SHED_CLADDING_BOARD_DIM.length() - 3, SHED_CLADDING_BOARD_DIM.length());
        int cladBoardWidth = Integer.parseInt(sCladBoardWidth) / 10; //Convert to cm

        int claddingBoardsShedWidth = (int) (shedWidth / cladBoardWidth); //No of cladding boards shed length w/o overlap
        noOfCladdingBoardsWidth = (int) Math.ceil((claddingBoardsShedWidth + (((claddingBoardsShedWidth / claddingBoardOverlap) / cladBoardWidth)))); //No. of cladding boards, with overlap in mind

        int claddingBoardsShedLength = (int) (shedLength / cladBoardWidth); //As above
        noOfCladdingBoardsLength = (int) Math.ceil((claddingBoardsShedLength + (((claddingBoardsShedLength / claddingBoardOverlap) / cladBoardWidth))));

        noOfCladdingBoardsTotal = (noOfCladdingBoardsWidth + noOfCladdingBoardsLength) * 2; //Calculate all four sides of the shed
    }

    /**
     * Outputs number of laths required for shed. Currently set to a fixed number of 12
     */
    private void calculateShedWallLaths(int shedLength, int shedWidth) {
        shedWallLathType = 29;
        this.shedWallLathsTotalLength = ((shedLength + shedWidth) * 2) * noOfShedWallLaths;
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
            roofCladType = 32;
        } else if (!raisedRoof) {
            double trapezPlateSquareArea = ROOF_TRAPEZ_LENGTH * ROOF_TILE_WIDTH;
            int noOfTrapezPlates = (int) ((carportLength * carportWidth) / trapezPlateSquareArea);
            totalNumberOfRoofTrapezPlates = noOfTrapezPlates;
            roofCladdingType = "trapezplader";
            roofCladType = 33;
        }
    }

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
            beamDimension = beamDimensionHeavy;
            beamType = 23;
        } else if (!roofHeavy) {
            beamDimension = beamDimensionLight;
            beamType = 22;
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
        supportStrapType = 25;
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
     * @param carportLength the calculated raftlength which matches the length of the roof stern.
     */
    private void calcSternBoardLength(int carportLength) {
        sternBoardType = 30;
        double sternBoardsLength = carportLength * 2;
        this.sternBoardLength = sternBoardsLength;
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
        roofLathType = 27;

        int bottomLathSpan = this.bottomLathSpan;
        int bottomLaths = this.bottomLaths;
        double topLathSpan = topLathGap;
        double avgLathSpan = this.avgLathSpan;
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

    public double getSternBoardLength() {
        return sternBoardLength;
    }

    public int getTotalNumberOfRoofTiles() {
        return totalNumberOfRoofTiles;
    }

    public int getTotalNumberOfRoofTrapezPlates() {
        return totalNumberOfRoofTrapezPlates;
    }

    public int getNoOfShedWallLaths() {
        return noOfShedWallLaths;
    }

    public int getNoOfCladdingBoardsTotal() {
        return noOfCladdingBoardsTotal;
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

    public int getNoOfCladdingBoardsWidth() {
        return noOfCladdingBoardsWidth;
    }

    public int getNoOfCladdingBoardsLength() {
        return noOfCladdingBoardsLength;
    }

    public int getBeamType() {
        return beamType;
    }

    public double getShedWallLathsTotalLength() {
        return shedWallLathsTotalLength;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getDoorKit() {
        return doorKit;
    }

    public void setDoorKit(int doorKit) {
        this.doorKit = doorKit;
    }

    public int getBracketKit() {
        return bracketKit;
    }

    public void setBracketKit(int bracketKit) {
        this.bracketKit = bracketKit;
    }

    public int getScrewKitRoofRaised() {
        return screwKitRoofRaised;
    }

    public void setScrewKitRoofRaised(int screwKitRoofRaised) {
        this.screwKitRoofRaised = screwKitRoofRaised;
    }

    public int getScrewKitTrapez() {
        return screwKitTrapez;
    }

    public void setScrewKitTrapez(int screwKitTrapez) {
        this.screwKitTrapez = screwKitTrapez;
    }

    public int getScrewKitGenericRoofBrackets() {
        return screwKitGenericRoofBrackets;
    }

    public void setScrewKitGenericRoofBrackets(int screwKitGenericRoofBrackets) {
        this.screwKitGenericRoofBrackets = screwKitGenericRoofBrackets;
    }

    public int getScrewKitGenericRoofLaths() {
        return screwKitGenericRoofLaths;
    }

    public void setScrewKitGenericRoofLaths(int screwKitGenericRoofLaths) {
        this.screwKitGenericRoofLaths = screwKitGenericRoofLaths;
    }

    public int getScrewKitShedDoorCladdingInside() {
        return screwKitShedDoorCladdingInside;
    }

    public void setScrewKitShedDoorCladdingInside(int screwKitShedDoorCladdingInside) {
        this.screwKitShedDoorCladdingInside = screwKitShedDoorCladdingInside;
    }

    public int getScrewKitShedDoorCladdingOutside() {
        return screwKitShedDoorCladdingOutside;
    }

    public void setScrewKitShedDoorCladdingOutside(int screwKitShedDoorCladdingOutside) {
        this.screwKitShedDoorCladdingOutside = screwKitShedDoorCladdingOutside;
    }
}