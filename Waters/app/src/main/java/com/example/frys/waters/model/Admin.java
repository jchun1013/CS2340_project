package com.example.frys.waters.model;

/**
 * Created by joon1 on 2017-03-05.
 */

public class Admin extends Manager {
    /**
     * Creates Admin
     * @param username username
     * @param name name
     * @param email email
     * @param password password
     * @param address address
     */
    public Admin(String username, String name, String email, String password, String address) {
        super(username, name, email, password, address);
        super.setUsertype(UserType.ADMIN);
    }
}

