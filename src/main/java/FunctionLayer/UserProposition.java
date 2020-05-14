package FunctionLayer;

/**
 * Class for instantiating instances of a user proposition with quote information
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

    public UserProposition(int user_proposition_id, String name, String address, String zipcodeCity, int phone, String email, String comments, int orders_id, String order_date, String status, int carport_width, int carport_length, int shed_width, int shed_length, String roof_type, String roof_material, int pitch) {
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
    }

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
}
