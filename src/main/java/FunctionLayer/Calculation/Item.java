package FunctionLayer.Calculation;

/**
 * Contains information about items from the Database. Used to retrive and update items in DB
 */
public class Item {

    private int itemListID;
    private String materialType;
    private String material;
    private String description;
    double quantity;
    private String unit;
    private double pricePrUnit;
    private int ordersID;
    private double totalPrice;

    public Item(){

    }

    public Item(int itemListID, String materialType, String material, String description, int quantity, String unit, double pricePrUnit){

        this.itemListID = itemListID;
        this.materialType = materialType;
        this.material = material;
        this.description = description;
        this.quantity = quantity;
        this.unit = unit;
        this.pricePrUnit = pricePrUnit;
    }

    public Item(int ordersID, int itemListID, double quantity, double totalPrice){
        this.ordersID = ordersID;
        this.itemListID = itemListID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;

    }

    public int getItemListID() {
        return itemListID;
    }

    public void setItemListID(int itemListID) {
        this.itemListID = itemListID;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPricePrUnit() {
        return pricePrUnit;
    }

    public void setPricePrUnit(double pricePrUnit) {
        this.pricePrUnit = pricePrUnit;
    }

    public int getOrdersID() {
        return ordersID;
    }

    public void setOrdersID(int ordersID) {
        this.ordersID = ordersID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
