package FunctionLayer.Tables;

/**
 * Class for instantiating instances of request item list
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class ItemList {

    private int item_list_id;
    private String material;
    private int amounts;
    private double price_per_unit;
    private int orderline_id;
    private int orders_id;
    private String material_type;
    private double quantity;
    private String unit;
    private String description;
    private double total_price;



    /**
     * Constructor for adding items to orderline
     *
     * @param orderline_id orderline id
     * @param orders_id order id
     * @param material_type material type
     * @param quantity quantity
     * @param unit unit
     * @param description description
     * @param total_price total price
     */
    public ItemList(int orderline_id, int orders_id, String material_type, double quantity, String unit, String description, double total_price) {
        this.orderline_id = orderline_id;
        this.orders_id = orders_id;
        this.material_type = material_type;
        this.quantity = quantity;
        this.unit = unit;
        this.description = description;
        this.total_price = total_price;
    }

    /**
     * Constructor for admin management
     *
     * @param item_list_id item list id
     * @param material_type material type
     * @param material material
     * @param description description
     * @param amounts amounts
     * @param unit unit
     * @param price_per_unit price per unit
     */
    public ItemList(int item_list_id,String material_type,String material,String description,int amounts,String unit,double price_per_unit) {
        this.item_list_id = item_list_id;
        this.material_type = material_type;
        this.material = material;
        this.description = description;
        this.amounts = amounts;
        this.unit = unit;
        this.price_per_unit = price_per_unit;
    }

    // Getters and setters
    public int getOrderline_id() {
        return orderline_id;
    }

    public void setOrderline_id(int orderline_id) {
        this.orderline_id = orderline_id;
    }

    public int getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(int orders_id) {
        this.orders_id = orders_id;
    }

    public String getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(String material_type) {
        this.material_type = material_type;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getItem_list_id() { return item_list_id; }

    public void setItem_list_id(int item_list_id) { this.item_list_id = item_list_id; }

    public String getMaterial() { return material; }

    public void setMaterial(String material) { this.material = material; }

    public int getAmounts() { return amounts; }

    public void setAmounts(int amounts) { this.amounts = amounts; }

    public double getPrice_per_unit() { return price_per_unit; }

    public void setPrice_per_unit(double price_per_unit) { this.price_per_unit = price_per_unit; }
}
