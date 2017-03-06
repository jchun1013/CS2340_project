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

    public static List<UserType> typeList = Arrays.asList(UserType.values());

    public User(String username, String name, String email, String password, String address) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.emailAddress = email;
        this.homeAddress = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String email) {
        this.emailAddress = email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String address) {
        this.homeAddress = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType type) {
        this.usertype = type;
    }

    public void submitWaterAvailability() {

    }

    public WaterSourceReport viewWaterSource() {
        return null;
    }

}
