package com.example.frys.waters.model;

/**
 * Created by joon1 on 2017-03-05.
 */

public class Manager extends Worker {
    /**
     * Creates Manager
     * @param username username
     * @param name name
     * @param email email
     * @param password password
     * @param address address
     */
    public Manager(String username, String name, String email, String password, String address) {
        super(username, name, email, password, address);
        super.setUsertype(UserType.MANAGER);
    }
}
