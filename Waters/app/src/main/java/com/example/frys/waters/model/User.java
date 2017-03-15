package com.example.frys.waters.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Frys on 3/5/2017.
 */

public class User {

    private String name;
    private String username;
    private String password;
    private String emailAddress;
    private String homeAddress;
    private String title;
    private UserType usertype;
    private boolean isReporting = false;

    public static List<UserType> typeList = Arrays.asList(UserType.values());

    /**
     * Creates user
     * @param username
     * @param name
     * @param email
     * @param password
     * @param address
     */
    public User(String username, String name, String email, String password, String address) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.emailAddress = email;
        this.homeAddress = address;
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
     * @param name
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
     * setter for username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getter for password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setter for password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getter for emailAddress
     * @return emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * setter for emailAddress
     * @param email
     */
    public void setEmailAddress(String email) {
        this.emailAddress = email;
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
     * @param address
     */
    public void setHomeAddress(String address) {
        this.homeAddress = address;
    }

    /**
     * getter for title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter for title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getter for userType
     * @return userType
     */
    public UserType getUsertype() {
        return usertype;
    }

    /**
     * setter for userType
     * @param type
     */
    public void setUsertype(UserType type) {
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
     * @param isReporting
     */
    public void setIsReporting(boolean isReporting) {
        this.isReporting = isReporting;
    }

    // MUST EDIT!!!!!!!
    public void submitWaterAvailability() {

    }

    public WaterSourceReport viewWaterSource() {
        return null;
    }

}