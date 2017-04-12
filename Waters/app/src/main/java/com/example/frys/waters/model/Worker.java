package com.example.frys.waters.model;

public class Worker extends User {
    /**
     * creates worker
     * @param username username
     * @param name name
     * @param email email
     * @param password password
     * @param address address
     */
    public Worker(String username, String name, String email, String password, String address) {
        super(username, name, email, password, address);
        super.setUsertype(UserType.WORKER);
    }
}
