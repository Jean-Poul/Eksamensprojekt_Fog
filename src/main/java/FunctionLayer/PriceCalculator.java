package FunctionLayer;

/**
 * Price calculator calculates the total price for the entire carport.
 * Takes a majority of calculated data from the CarportCalculation class.
 * Takes a majority of price information from the database.
 */

public class PriceCalculator {

    //Item information should be retrieved from DB every time the query runs (Put the item array in the constructor)

    //  RAFT CALCULATIONS (Qty & Length [cm])
    private double totalRaftLength; //Remember vertical and horizontal rafts!

    //  SHED CALCULATIONS [cm]
    private double totalShedWallLathLength;
    private int totalShedCladdingBoardLength;

    //  ROOF CALCULATIONS [cm]
    private double totalLathsLength;
    private double totalSupportingStrapLength;
    private int totalNumberOfRoofTiles;
    private int totalNumberOfRoofTrapezPlates;

    //  ASSORTED STRUCTURAL & ACCESSORIES
    private double totalSternBoardLength;
    private int totalNoOfBeamLength;

    public PriceCalculator(CarportCalculation cpCalc) {

    //Rafters
    this.totalRaftLength = cpCalc.getRaftLength() * cpCalc.getNoOfRafts();

    //Shed - NOTE THAT DOOR IS NOT SUBTRACTED FROM SHED CLADDING
    this.totalShedWallLathLength = ((cpCalc.getShedLength() + cpCalc.getShedWidth() * 2 ) * cpCalc.getShedWallLaths());
    this.totalShedCladdingBoardLength = cpCalc.getNoOfCladdingBoardsTotal();

    //Roof
    this.totalLathsLength = cpCalc.getNoOfLaths() * cpCalc.getCarportLength();
    this.totalSupportingStrapLength = cpCalc.getSupportingStrapLength();
    this.totalNumberOfRoofTiles = cpCalc.getTotalNumberOfRoofTiles();
    this.totalNumberOfRoofTrapezPlates = cpCalc.getTotalNumberOfRoofTrapezPlates();

    //Assorted
    this.totalSternBoardLength = cpCalc.getSternBoardLength();
    this.totalNoOfBeamLength = cpCalc.getNoOfBeams(); //STD LENGTH IS 250 cm - NEEDS TO MULTIPLY BY THIS - PROBABLY FROM DB

    //REMEMBER A PACKAGE FOR SHED DOOR + DIVERSE BRACKETS, SCREWS, BOLTS, ETC

    }


}
