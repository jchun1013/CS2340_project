package Model;

/**
 * Created by joon1 on 2017-04-22.
 */
import java.util.Arrays;
import java.util.List;

public class User {

    private String name;
    private String username;
    private String password;
    private String emailAddress;
    private String homeAddress;
    private String usertype;
    private boolean isReporting = false;

    public static final List<UserType> typeList = Arrays.asList(UserType.values());

    /**
     * Creates user
     * @param username username
     * @param name name
     * @param email email
     * @param password password
     * @param address address
     */
    public User(String username, String name, String email, String password, String address, String type) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.emailAddress = email;
        this.homeAddress = address;
        this.usertype = type;
    }

    public User() {

    }
    /**
     * getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * getter for password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * getter for emailAddress
     * @return emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * getter for homeAddress
     * @return hoemAddress
     */
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     * setter for homeAddress
     * @param address address
     */
    public void setHomeAddress(String address) {
        this.homeAddress = address;
    }

    /**
     * getter for userType
     * @return userType
     */
    public String getUsertype() {
        return usertype;
    }

    /**
     * setter for userType
     * @param type type
     */
    public void setUsertype(String type) {
        this.usertype = type;
    }

    /**
     * whether or not isReporting
     * @return whether or not isReporting
     */
    public boolean getIsReporting() {
        return isReporting;
    }

    /**
     * setter for isReporting
     * @param isReporting isReporting
     */
    public void setIsReporting(boolean isReporting) {
        this.isReporting = isReporting;
    }
}
