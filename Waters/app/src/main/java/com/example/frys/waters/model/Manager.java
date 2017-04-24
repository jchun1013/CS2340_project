package com.example.frys.waters.model;

public class Manager extends Worker {
    /**
     * Creates Manager
     * @param username username
     * @param name name
     * @param email email
     * @param password password
     * @param address address
     */
    public Manager(String username, String name, String email, String password, String address, boolean banned) {
        super(username, name, email, password, address, banned);
        super.setUsertype(UserType.MANAGER);
    }
}
