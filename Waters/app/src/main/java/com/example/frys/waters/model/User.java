package com.example.frys.waters.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Frys on 3/5/2017.
 */

public class User {

    private String name;
    private int id;
    private String username;
    private String password;
    private String emailAddress;
    private String homeAddress;
    private String title;
    private UserType usertype;

    public static List<UserType> typeList = Arrays.asList(UserType.values());

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
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


}
