package FunctionLayer.Tables;

/**
 * Class for instantiating instances of a customer quote with user information
 */
public class CustomerQuote {

    private int customerId;
    private String name;
    private String address;
    private String zipcodeCity;
    private int phone;
    private String email;
    private String comment;

    /**
     * Constructor
     *
     * @param customerId
     */
    public CustomerQuote(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Constructor
     *
     * @param customerId
     * @param name
     * @param address
     * @param zipcodeCity
     * @param phone
     * @param email
     * @param comment
     */
    public CustomerQuote(int customerId, String name, String address, String zipcodeCity, int phone, String email, String comment) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.zipcodeCity = zipcodeCity;
        this.phone = phone;
        this.email = email;
        this.comment = comment;
    }

    // Getters and setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public void setZipcode(int zipcode) {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
