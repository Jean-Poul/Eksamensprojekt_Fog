package FunctionLayer.Tables;

/**
 * Class for instantiating instances of a user proposition with quote information
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */

public class UserProposition {

    private int user_proposition_id;
    private String name;
    private String address;
    private String zipcodeCity;
    private int phone;
    private String email;
    private String comments;
    private int orders_id;
    private String order_date;
    private String status;
    private int carport_width;
    private int carport_length;
    private int shed_width;
    private int shed_length;
    private String roof_type;
    private String roof_material;
    private int pitch;
    private int coverage;
    private double offer_price;
    private double total_price;


    /**
     * Constructor for user proposition with quote info
     *
     * @param user_proposition_id user proposition id
     * @param name name
     * @param address address
     * @param zipcodeCity zipcode and city
     * @param phone phone
     * @param email e-mail
     * @param comments comments
     * @param orders_id order id
     * @param order_date order date
     * @param status status
     * @param carport_width carport width
     * @param carport_length carport length
     * @param shed_width shed width
     * @param shed_length shed length
     * @param roof_type roof type
     * @param roof_material roof material
     * @param pitch pitch
     * @param coverage coverage
     * @param offer_price offer price
     * @param total_price tatal price
     */
    public UserProposition(int user_proposition_id, String name, String address, String zipcodeCity, int phone, String email, String comments, int orders_id, String order_date, String status, int carport_width, int carport_length, int shed_width, int shed_length, String roof_type, String roof_material, int pitch, int coverage, double offer_price, double total_price) {
        this.user_proposition_id = user_proposition_id;
        this.name = name;
        this.address = address;
        this.zipcodeCity = zipcodeCity;
        this.phone = phone;
        this.email = email;
        this.comments = comments;
        this.orders_id = orders_id;
        this.order_date = order_date;
        this.status = status;
        this.carport_width = carport_width;
        this.carport_length = carport_length;
        this.shed_width = shed_width;
        this.shed_length = shed_length;
        this.roof_type = roof_type;
        this.roof_material = roof_material;
        this.pitch = pitch;
        this.coverage = coverage;
        this.offer_price = offer_price;
        this.total_price = total_price;
    }

    // Getters and setters
    public int getUser_proposition_id() {
        return user_proposition_id;
    }

    public void setUser_proposition_id(int user_proposition_id) {
        this.user_proposition_id = user_proposition_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcodeCity() {
        return zipcodeCity;
    }

    public void setZipcodeCity(String zipcodeCity) {
        this.zipcodeCity = zipcodeCity;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(int orders_id) {
        this.orders_id = orders_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCarport_width() {
        return carport_width;
    }

    public void setCarport_width(int carport_width) {
        this.carport_width = carport_width;
    }

    public int getCarport_length() {
        return carport_length;
    }

    public void setCarport_length(int carport_length) {
        this.carport_length = carport_length;
    }

    public int getShed_width() {
        return shed_width;
    }

    public void setShed_width(int shed_width) {
        this.shed_width = shed_width;
    }

    public int getShed_length() {
        return shed_length;
    }

    public void setShed_length(int shed_length) {
        this.shed_length = shed_length;
    }

    public String getRoof_type() {
        return roof_type;
    }

    public void setRoof_type(String roof_type) {
        this.roof_type = roof_type;
    }

    public String getRoof_material() {
        return roof_material;
    }

    public void setRoof_material(String roof_material) {
        this.roof_material = roof_material;
    }

    public int getPitch() {
        return pitch;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    public int getCoverage() { return coverage; }

    public void setCoverage(int coverage) { this.coverage = coverage; }

    public double getOffer_price() { return offer_price; }

    public void setOffer_price(double offer_price) { this.offer_price = offer_price; }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}
