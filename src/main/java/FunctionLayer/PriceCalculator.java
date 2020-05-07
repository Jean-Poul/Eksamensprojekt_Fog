package FunctionLayer;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Price calculator calculates the total price for the entire carport.
 * Takes a majority of calculated data from the CarportCalculation class.
 * Takes a majority of price information from the database.
 */

public class PriceCalculator {

    LogicFacade log = new LogicFacade();

    //Used if item is not found in DB
    Item item = new Item();

    //Formats decimal numbers to two decimals.
    DecimalFormat df = new DecimalFormat("#,##0.00");

    //  RAFT CALCULATIONS (Qty & Length [cm])
    private double totalRaftLength; //Remember vertical and horizontal rafts!
    private double totalRaftPrice;

    //  SHED CALCULATIONS [cm]
    private double totalShedWallLathLength;
    private double totalShedCladdingBoardLength;
    private double totalShedCladdingBoardPrice;
    private double totalShedWallLathPrice;

    //  ROOF CALCULATIONS [cm]
    private double totalLathsLength;
    private double totalSupportingStrapLength;
    private int totalNumberOfRoofTiles;
    private int totalNumberOfRoofTrapezPlates;
    private double totalRoofLathPrice;
    private double totalSupportingStrapPrice;
    private double totalRoofCladPrice;

    //  ASSORTED STRUCTURAL & ACCESSORIES
    private double totalSternBoardLength;
    private double totalNoOfBeamLength;
    private double totalSternBoardPrice;
    private double totalBeamPrice;

    //Total price
    private double totalCarportPrice;

    public PriceCalculator(CarportCalculation cpCalc) throws SQLException {

        //Rafters
        this.totalRaftLength = ((cpCalc.getRaftLength() + cpCalc.getHorizontalRaftLength() + cpCalc.getVerticalRaftLength()) / 100) * cpCalc.getNoOfRafts();
        this.totalRaftPrice = itemSearch(cpCalc.getRaftType()).getPricePrUnit() * totalRaftLength;
        System.out.println("Samlet kostpris for tagspær: " + df.format(totalRaftPrice) + " kr");

        //Shed - NOTE THAT DOOR IS NOT SUBTRACTED FROM SHED CLADDING
        this.totalShedWallLathLength = cpCalc.getShedWallLathsTotalLength();
        this.totalShedWallLathPrice = itemSearch(cpCalc.getShedWallLathType()).getPricePrUnit() * (totalShedWallLathLength / 100);
        System.out.println("Samlet kostpris for løsholter: " + df.format(totalShedWallLathPrice) + " kr");

        this.totalShedCladdingBoardLength = cpCalc.getNoOfCladdingBoardsTotal() * 2.5; //2.5m is carport standard height
        this.totalShedCladdingBoardPrice = itemSearch(cpCalc.getShedCladdingBoardType()).getPricePrUnit() * totalShedCladdingBoardLength;
        System.out.println("Samlet kostpris for skur beklædningsbræt: " + df.format(totalShedCladdingBoardPrice) + " kr");

        //Roof
        this.totalLathsLength = (cpCalc.getNoOfLaths() * cpCalc.getCarportLength() / 100);
        this.totalRoofLathPrice = itemSearch(cpCalc.getRoofLathType()).getPricePrUnit() * totalLathsLength;
        System.out.println("Samlet kostpris for taglægter: " + df.format(totalRoofLathPrice) + " kr");

        this.totalSupportingStrapLength = cpCalc.getSupportingStrapLength() / 100;
        this.totalSupportingStrapPrice = itemSearch(cpCalc.getSupportStrapType()).getPricePrUnit() * totalSupportingStrapLength;
        System.out.println("Samlet kostpris for rem: " + df.format(totalSupportingStrapPrice) + " kr");

        if (cpCalc.getRoofCladdingType().equalsIgnoreCase("tagsten")) {
            this.totalNumberOfRoofTiles = cpCalc.getTotalNumberOfRoofTiles();
            this.totalRoofCladPrice = itemSearch(cpCalc.getRoofCladType()).getPricePrUnit() * totalNumberOfRoofTiles;
            System.out.println("Samlet kostpris for tagsten: " + df.format(totalRoofCladPrice) + " kr");
        } else {
            this.totalNumberOfRoofTrapezPlates = cpCalc.getTotalNumberOfRoofTrapezPlates();
            this.totalRoofCladPrice = itemSearch(cpCalc.getRoofCladType()).getPricePrUnit() * totalNumberOfRoofTrapezPlates;
            System.out.println("Samlet kostpris for trapezplader: " + df.format(totalRoofCladPrice) + " kr");
        }

        //Assorted
        this.totalSternBoardLength = cpCalc.getSternBoardLength() / 100;
        this.totalSternBoardPrice = itemSearch((int) cpCalc.getSternBoardType()).getPricePrUnit() * totalSternBoardLength;
        System.out.println("Samlet kostpris for sternbrædder: " + df.format(totalSternBoardPrice));

        this.totalNoOfBeamLength = cpCalc.getNoOfBeams() * 2.5; //STD LENGTH IS 250 cm - NEEDS TO MULTIPLY BY THIS - PROBABLY FROM DB
        this.totalBeamPrice = itemSearch(cpCalc.getBeamType()).getPricePrUnit() * totalNoOfBeamLength;
        System.out.println("Samlet kostpris for søjler: " + df.format(totalBeamPrice) + " kr");

        //REMEMBER A PACKAGE FOR SHED DOOR + DIVERSE BRACKETS, SCREWS, BOLTS, ETC

        calculateCarportTotalPrice(totalRaftPrice, totalShedCladdingBoardPrice, totalShedWallLathPrice,
                totalRoofLathPrice, totalSupportingStrapPrice, totalRoofCladPrice,
                totalSternBoardPrice, totalBeamPrice);

        System.out.println("\n========================================");
        System.out.println("Den samlede kostpris er: " + df.format(totalCarportPrice) + " kr");
        System.out.println("========================================");

    }

    private void calculateCarportTotalPrice(double raft, double shedCladBoard, double shedLath,
                                            double roofLath, double supportStrap, double roofClad,
                                            double sternBoard, double beam) {

        totalCarportPrice = raft + shedCladBoard + shedLath + roofLath + supportStrap + roofClad + sternBoard + beam;

    }


    private Item itemSearch(int itemID) throws SQLException {

        for (int i = 0; i < log.getItemList().size(); i++) {

            if (itemID == log.getItemList().get(i).getItemListID()) {
                this.item = log.getItemList().get(i);
            }
        }
        return item;
    }
}
