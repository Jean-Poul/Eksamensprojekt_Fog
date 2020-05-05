package FunctionLayer;

/**
 * Price calculator calculates the total price for the entire carport.
 * Takes a majority of calculated data from the CarportCalculation class.
 * Takes a majority of price information from the database.
 */

public class PriceCalculator {

    //Init ny beregning = "Opfrisk" prisList


    //#### Roof rafts (Spær)
    private double totalRaftLength;
    private double raftPricePrM;
    private double totalRaftPrice;

    //#### Shed laths (Løsholter)
    private double totalShedWallLathsLength;
    private double shedLathPriceprM;
    private double shedWallTotalPrice;

    //#### Shed cladding (Beklædning skur)
    private double shedCladdingBoardPricePrM;
    private double totalShedCladdingBoardLength;
    private double totalPriceCladdingBoards;

    //#### Roof laths (Taglægter)
    private double totalRoofLathLengthM;
    private double roofLathPricePrM;
    private double roofLathTotalPrice;

    //#### support strap (Rem)
    private double totalSupportStrapLength;
    private double supportStrapPricePrM;
    private double totalPriceStrap;

    //#### Roof cladding
    private double roofCladdingPricePrPc;
    private int totalNumberOfRoofTiles;
    private double totalPriceRoofCladding;

    //#### Stern boards (Stern brædder)
    private double totalSternBoardLengthM;
    private double sternBoardPricePrM;
    private double totalPriceSternBoards;

    //#### Beams (Stolper)
    private double beamPricePrM;
    private int totalNoOfBeams;
    private double totalPriceBeams;

    public PriceCalculator() {

    }


}
