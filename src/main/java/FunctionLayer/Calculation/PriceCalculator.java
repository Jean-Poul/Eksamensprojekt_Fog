package FunctionLayer.Calculation;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.LogicFacade;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Price calculator calculates the total price for the entire carport.<br>
 * Takes a majority of calculated data from the CarportCalculation class.<br>
 * Takes a majority of price information from the database.
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */

public class PriceCalculator {

    LogicFacade log = new LogicFacade();

    //Used if item is not found in DB
    Item item = new Item();

    //Used to store items before iterating over them to store them in DB
    ArrayList<Item> itemList = new ArrayList();

    //Formats decimal numbers to two decimals.
    DecimalFormat df = new DecimalFormat("#,##0.00");

    //Used to update orderLine price
    double orderLinePriceOld;
    double orderLinePriceNew;

    int orderID;

    //Danish tax
    double addTax = 1.25;
    double subtractTax = 0.8;
    double orderCoverage;

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
    private int doorKitQty = 1;
    private int screwKitGenericRoofBracketsQty = 1;
    private int screwKitGenericRoofLathsQty = 1;
    private int bracketKitQty = 1;
    private int screwKitShedDoorCladdingInsideQty = 1;
    private int screwKitShedDoorCladdingOutsideQty = 1;
    private int screwKitRoofTilesQty = 1;
    private int screwKitTrapezQty = 1;
    private double doorKitPrice = 0;
    private double screwKitGenericRoofBracketsPrice;
    private double screwKitGenericRoofLathsPrice;
    private double bracketKitPrice;
    private double screwKitShedDoorCladdingInsidePrice = 0;
    private double screwKitShedDoorCladdingOutsidePrice = 0;
    private double screwKitRoofTilesPrice = 0;
    private double screwKitTrapezPrice = 0;

    //Total price
    private double totalCarportCostNoTax;
    private double totalCarportPriceCostWithTax;
    private double totalCarportPriceCoverage;

    /**
     * Constructor for PriceCalculator. Takes a carport object to perform all necessary price calculations
     * @param cpCalc CarportCalculation Object
     * @throws LoginSampleException LoginSampleException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    public PriceCalculator(CarportCalculation cpCalc) throws LoginSampleException, ClassNotFoundException {

        this.orderID = cpCalc.getOrderID();

        this.orderCoverage = LogicFacade.getOrderCoverage(cpCalc.orderID);

        //Accessories

        this.screwKitGenericRoofBracketsPrice = itemSearch(cpCalc.getScrewKitGenericRoofBrackets()).getPricePrUnit() * screwKitGenericRoofBracketsQty;
        this.screwKitGenericRoofLathsPrice = itemSearch(cpCalc.getScrewKitGenericRoofLaths()).getPricePrUnit() * screwKitGenericRoofLathsQty;
        this.bracketKitPrice = itemSearch(cpCalc.getBracketKit()).getPricePrUnit() * bracketKitQty;

        //Rafters
        this.totalRaftLength = ((cpCalc.getRaftLength() + cpCalc.getHorizontalRaftLength() + cpCalc.getVerticalRaftLength()) / 100) * cpCalc.getNoOfRafts();
        this.totalRaftPrice = itemSearch(cpCalc.getRaftType()).getPricePrUnit() * totalRaftLength;

        //Roof
        this.totalLathsLength = (cpCalc.getNoOfLaths() * cpCalc.getCarportLength() / 100);
        this.totalRoofLathPrice = itemSearch(cpCalc.getRoofLathType()).getPricePrUnit() * totalLathsLength;

        this.totalSupportingStrapLength = cpCalc.getSupportingStrapLength() / 100;
        this.totalSupportingStrapPrice = itemSearch(cpCalc.getSupportStrapType()).getPricePrUnit() * totalSupportingStrapLength;

        if (cpCalc.getRoofCladdingType().equalsIgnoreCase("tagsten")) {
            this.totalNumberOfRoofTiles = cpCalc.getTotalNumberOfRoofTiles();
            this.totalRoofCladPrice = itemSearch(cpCalc.getRoofCladType()).getPricePrUnit() * totalNumberOfRoofTiles;
            this.screwKitRoofTilesPrice = itemSearch(cpCalc.getScrewKitRoofRaised()).getPricePrUnit() * screwKitRoofTilesQty;
        } else {
            this.totalNumberOfRoofTrapezPlates = cpCalc.getTotalNumberOfRoofTrapezPlates();
            this.totalRoofCladPrice = itemSearch(cpCalc.getRoofCladType()).getPricePrUnit() * totalNumberOfRoofTrapezPlates;
            this.screwKitTrapezPrice = itemSearch(cpCalc.getScrewKitTrapez()).getPricePrUnit() * screwKitTrapezQty;
        }

        //Assorted
        this.totalSternBoardLength = cpCalc.getSternBoardLength() / 100;
        this.totalSternBoardPrice = itemSearch((int) cpCalc.getSternBoardType()).getPricePrUnit() * totalSternBoardLength;
        this.totalNoOfBeamLength = cpCalc.getNoOfBeams() * 2.5; //STD LENGTH IS 250 cm - NEEDS TO MULTIPLY BY THIS - PROBABLY FROM DB
        this.totalBeamPrice = itemSearch(cpCalc.getBeamType()).getPricePrUnit() * totalNoOfBeamLength;

        //Making items to store in orderline in DB
        Item raftItem = new Item(orderID, cpCalc.getRaftType(), totalRaftLength, totalRaftPrice);
        Item lathItem = new Item(orderID, cpCalc.getRoofLathType(), totalLathsLength, totalRoofLathPrice);
        Item supportStrapItem = new Item(orderID, cpCalc.getSupportStrapType(), totalSupportingStrapLength, totalSupportingStrapPrice);
        Item sternBoardItem = new Item(orderID, cpCalc.getSternBoardType(), totalSternBoardLength, totalSternBoardPrice);
        Item beamItem = new Item(orderID, cpCalc.getBeamType(), totalNoOfBeamLength, totalBeamPrice);
        Item screwKitGenericRoofBracketsItem = new Item(orderID, cpCalc.getScrewKitGenericRoofBrackets(), screwKitGenericRoofBracketsQty, screwKitGenericRoofBracketsPrice);
        Item screwKitGenericRoofLathsItem = new Item(orderID, cpCalc.getScrewKitGenericRoofLaths(), screwKitGenericRoofLathsQty, screwKitGenericRoofLathsPrice);
        Item bracketKitItem = new Item(orderID, cpCalc.getBracketKit(), bracketKitQty, bracketKitPrice);
        if (cpCalc.isRaisedRoof()) {
            Item roofTileItem = new Item(orderID, cpCalc.getRoofCladType(), totalNumberOfRoofTiles, totalRoofCladPrice);
            Item screwKitRoofTilesItem = new Item(orderID, cpCalc.getRoofCladType(), screwKitRoofTilesQty, screwKitRoofTilesPrice);
            itemList.add(roofTileItem);
            itemList.add(screwKitRoofTilesItem);
        } else {
            Item roofTrapezItem = new Item(orderID, cpCalc.getRoofCladType(), totalNumberOfRoofTrapezPlates, totalRoofCladPrice);
            Item screwKitTrapezItem = new Item(orderID, cpCalc.getScrewKitTrapez(), screwKitTrapezQty, screwKitTrapezPrice);
            itemList.add(roofTrapezItem);
            itemList.add(screwKitTrapezItem);
        }
        if (cpCalc.getShedLength() > 0) {
            //Shed - NOTE THAT DOOR IS NOT SUBTRACTED FROM SHED CLADDING
            this.totalShedWallLathLength = cpCalc.getShedWallLathsTotalLength();
            this.totalShedWallLathPrice = itemSearch(cpCalc.getShedWallLathType()).getPricePrUnit() * (totalShedWallLathLength / 100);
            this.totalShedCladdingBoardLength = cpCalc.getNoOfCladdingBoardsTotal() * 2.5; //2.5m is carport standard height
            this.totalShedCladdingBoardPrice = itemSearch(cpCalc.getShedCladdingBoardType()).getPricePrUnit() * totalShedCladdingBoardLength;
            this.doorKitPrice = itemSearch(cpCalc.getDoorKit()).getPricePrUnit() * doorKitQty;
            this.screwKitShedDoorCladdingInsidePrice = itemSearch(cpCalc.getScrewKitShedDoorCladdingInside()).getPricePrUnit() * screwKitShedDoorCladdingInsideQty;
            this.screwKitShedDoorCladdingOutsidePrice = itemSearch(cpCalc.getScrewKitShedDoorCladdingOutside()).getPricePrUnit() * screwKitShedDoorCladdingOutsideQty;
            Item shedWallLathItem = new Item(orderID, cpCalc.getShedWallLathType(), totalShedWallLathLength, totalShedWallLathPrice);
            Item shedCladdingBoardItem = new Item(orderID, cpCalc.getShedCladdingBoardType(), totalShedCladdingBoardLength, totalShedCladdingBoardPrice);
            Item screwKitShedDoorCladdingInsideItem = new Item(orderID, cpCalc.getScrewKitShedDoorCladdingInside(), screwKitShedDoorCladdingInsideQty, screwKitShedDoorCladdingInsidePrice);
            Item screwKitShedDoorCladdingOutsideItem = new Item(orderID, cpCalc.getScrewKitShedDoorCladdingOutside(), screwKitShedDoorCladdingOutsideQty, screwKitShedDoorCladdingOutsidePrice);
            Item doorKitItem = new Item(orderID, cpCalc.getDoorKit(), doorKitQty, doorKitPrice);
            itemList.add(shedWallLathItem);
            itemList.add(shedCladdingBoardItem);
            itemList.add(screwKitShedDoorCladdingInsideItem);
            itemList.add(screwKitShedDoorCladdingOutsideItem);
            itemList.add(doorKitItem);
        }

        itemList.add(raftItem);
        itemList.add(lathItem);
        itemList.add(supportStrapItem);
        itemList.add(sternBoardItem);
        itemList.add(beamItem);
        itemList.add(screwKitGenericRoofBracketsItem);
        itemList.add(screwKitGenericRoofLathsItem);
        itemList.add(bracketKitItem);

        //Iterate over itemList and put each item on orderline in DB
            for (Item i : itemList) {
                orderLineToDB(orderID, i);
            }
            itemList.clear();



        //Calculate the total carport cost price.
        totalCarportCostNoTax = totalRaftPrice+ totalShedCladdingBoardPrice+ totalShedWallLathPrice+
                totalRoofLathPrice+ totalSupportingStrapPrice+ totalRoofCladPrice+
                totalSternBoardPrice+ totalBeamPrice+ screwKitRoofTilesPrice+ screwKitTrapezPrice+ screwKitShedDoorCladdingInsidePrice+
                screwKitShedDoorCladdingOutsidePrice+ doorKitPrice+ screwKitGenericRoofBracketsPrice+ screwKitGenericRoofLathsPrice+ bracketKitPrice;

        calculateCarportTotalPrice(totalCarportCostNoTax);

        costPriceToDB(totalCarportPriceCostWithTax, orderID);

    }

    /**
     * Constructor for updating single orderlines
     * @param oLineID order line id
     * @param qty quantity
     * @param orderID order id
     * @throws LoginSampleException LoginSampleException
     */
    public PriceCalculator(int oLineID, double qty, int orderID) throws LoginSampleException {

        //Get the current order line price (To be replaced)
        this.orderLinePriceOld = LogicFacade.getOrderLinePriceFromLineID(orderID, oLineID);

        //Update the new order line price
        this.orderLinePriceNew = updatePrice(oLineID, qty);

        //Get the current total carport price (To be replaced)
        double currentTotalPrice = LogicFacade.getTotalCarportPrice(orderID);

        //Withdraw tax and current order line price from total carport price
        double newTotalPrice = ((currentTotalPrice * subtractTax) - orderLinePriceOld);

        //Add new item line price and tax
        newTotalPrice = (newTotalPrice + this.orderLinePriceNew) * addTax;

        //Update the total cost
        costPriceToDB(newTotalPrice, orderID);
    }

    /**
     * Gets the updated price for an order line
     * @param itemID item id
     * @param qty quantity
     * @return newOrderLinePrice
     * @throws LoginSampleException LoginSampleException
     */
    public double updatePrice(int itemID, double qty) throws LoginSampleException {
        Item i = itemSearch(itemID);
        double newOrderLinePrice = i.getPricePrUnit() * qty;
        return newOrderLinePrice;
    }

    /**
     * Iterates over an item list and stores each item as an orderline in DB
     *
     * @param orderID Current order ID
     * @param item    Item type containing item type (no.), total calculated length and total price (qty x price per unit)
     * @throws LoginSampleException LoginSampleException
     */
    public void orderLineToDB(int orderID, Item item) throws LoginSampleException {
        int oID = orderID;
        int itemType = item.getItemListID();
        double totalLength = item.getQuantity();
        double totalItemPrice = item.getTotalPrice();

        LogicFacade.createQuoteOrderline(oID, itemType, totalLength, totalItemPrice);
    }

    /**
     * Calculates total carport price with tax
     * @param totalCarportCostNoTax total carport price without tax
     */
    public void calculateCarportTotalPrice(double totalCarportCostNoTax) {

        totalCarportPriceCostWithTax = totalCarportCostNoTax * addTax;
        totalCarportPriceCoverage = totalCarportPriceCostWithTax * ((this.orderCoverage / 100) + 1);

    }

    /**
     * Inserts the total carport price w. tax and w/o coverage to order in DB
     *
     * @param totalCarportPriceCostWithTax Total cost price for carport with tax and without coverage
     * @param orderID                      specific order ID
     * @throws LoginSampleException        LoginSampleException
     */
    public void costPriceToDB(double totalCarportPriceCostWithTax, int orderID) throws LoginSampleException {
        LogicFacade.insertTotalCarportPrice(totalCarportPriceCostWithTax, orderID);
    }

    /**
     * Takes item type from carport calculation and searches for equivilant in DB
     * @param itemID item id
     * @return item
     * @throws LoginSampleException LoginSampleException
     */
    public Item itemSearch(int itemID) throws LoginSampleException {

        for (int i = 0; i < log.getItemList().size(); i++) {

            if (itemID == log.getItemList().get(i).getItemListID()) {
                this.item = log.getItemList().get(i);
            }
        }
        return item;
    }

    public double getTotalRaftLength() {
        return totalRaftLength;
    }

    public double getTotalRaftPrice() {
        return totalRaftPrice;
    }

    public double getTotalShedWallLathLength() {
        return totalShedWallLathLength;
    }

    public double getTotalShedCladdingBoardLength() {
        return totalShedCladdingBoardLength;
    }

    public double getTotalShedCladdingBoardPrice() {
        return totalShedCladdingBoardPrice;
    }

    public double getTotalShedWallLathPrice() {
        return totalShedWallLathPrice;
    }

    public double getTotalLathsLength() {
        return totalLathsLength;
    }

    public double getTotalSupportingStrapLength() {
        return totalSupportingStrapLength;
    }

    public int getTotalNumberOfRoofTiles() {
        return totalNumberOfRoofTiles;
    }

    public int getTotalNumberOfRoofTrapezPlates() {
        return totalNumberOfRoofTrapezPlates;
    }

    public double getTotalRoofLathPrice() {
        return totalRoofLathPrice;
    }

    public double getTotalSupportingStrapPrice() {
        return totalSupportingStrapPrice;
    }

    public double getTotalRoofCladPrice() {
        return totalRoofCladPrice;
    }

    public double getTotalSternBoardLength() {
        return totalSternBoardLength;
    }

    public double getTotalNoOfBeamLength() {
        return totalNoOfBeamLength;
    }

    public double getTotalSternBoardPrice() {
        return totalSternBoardPrice;
    }

    public double getTotalBeamPrice() {
        return totalBeamPrice;
    }

    public double getTotalCarportCostNoTax() {
        return totalCarportCostNoTax;
    }

    public double getTotalCarportPriceCostWithTax() {
        return totalCarportPriceCostWithTax;
    }

    public double getTotalCarportPriceCoverage() {
        return totalCarportPriceCoverage;
    }
}
