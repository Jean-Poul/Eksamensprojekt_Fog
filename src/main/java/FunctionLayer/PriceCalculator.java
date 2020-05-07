package FunctionLayer;

import sun.rmi.runtime.Log;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Price calculator calculates the total price for the entire carport.
 * Takes a majority of calculated data from the CarportCalculation class.
 * Takes a majority of price information from the database.
 */

public class PriceCalculator {

    //Formats decimal numbers to two decimals.
    DecimalFormat df = new DecimalFormat("#.##");

    LogicFacade log = new LogicFacade();
    Item item = new Item();

    //  RAFT CALCULATIONS (Qty & Length [cm])
    private double totalRaftLength; //Remember vertical and horizontal rafts!
    private double totalRaftPrice;

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

    public PriceCalculator(CarportCalculation cpCalc) throws SQLException {

        //Rafters
        this.totalRaftLength = (cpCalc.getRaftLength()/100) * cpCalc.getNoOfRafts();
        this.totalRaftPrice = itemSearch(cpCalc.getRaftType()).getPricePrUnit() * totalRaftLength;
        System.out.println("Samlet pris for tagspær: " + df.format(totalRaftPrice));

        //Shed - NOTE THAT DOOR IS NOT SUBTRACTED FROM SHED CLADDING
        this.totalShedWallLathLength = ((cpCalc.getShedLength() + cpCalc.getShedWidth() * 2) * cpCalc.getShedWallLaths());
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

    public Item itemSearch(int itemID) throws SQLException {

        for(int i = 0; i < log.getItemList().size(); i++){

            if(itemID == log.getItemList().get(i).getItemListID()){
                this.item = log.getItemList().get(i);
            }
        }
        return item;
    }


}
