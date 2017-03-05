package com.example.frys.waters.model;

/**
 * Created by joon1 on 2017-03-05.
 */

public class Worker extends User {
    /**
     * creates worker
     * @param username
     * @param name
     * @param email
     * @param password
     * @param address
     */
    Worker(String username, String name, String email, String password, String address) {
        super(username, name, email, password, address);
    }
    /**
     * report on water purity level
     */
    public void reportOnWaterPurityLevel() {

    }
}
