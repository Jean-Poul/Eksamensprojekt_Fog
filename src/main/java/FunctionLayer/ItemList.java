package FunctionLayer;

public class ItemList {

    private int orders_id;
    private String material_type;
    private double quantity;
    private String unit;
    private String description;
    private double total_price;

    public ItemList(int orders_id, String material_type, double quantity, String unit, String description, double total_price) {
        this.orders_id = orders_id;
        this.material_type = material_type;
        this.quantity = quantity;
        this.unit = unit;
        this.description = description;
        this.total_price = total_price;
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
}
