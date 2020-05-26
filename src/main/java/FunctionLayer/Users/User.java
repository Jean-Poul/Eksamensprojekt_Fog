package FunctionLayer.Users;

/**
 * The purpose of User is to create a user with a specific role
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class User {

    /**
     * Constructor for user
     *
     * @param email e-mail
     * @param password password
     * @param role role
     */
    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Constructor for user with retrieval of autogen keys in DataMapper
     *
     * @param id id
     * @param email e-mail
     * @param password password
     * @param role role
     */
    public User(int id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    private int id;
    private String email;
    private String password; // Should be hashed and secured
    private String role;


    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
