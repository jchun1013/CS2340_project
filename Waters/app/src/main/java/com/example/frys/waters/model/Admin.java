package com.example.frys.waters.model;

/**
 * Created by joon1 on 2017-03-05.
 */

public class Admin extends Manager {
    /**
     * Creates Admin
     * @param username
     * @param name
     * @param email
     * @param password
     * @param address
     */
    public Admin(String username, String name, String email, String password, String address) {
        super(username, name, email, password, address);
    }

    /**
     * Deletes Account
     */
    public void deleteAccount() {

    }
    /**
     * Bans User
     */
    public void banUser() {

    }

    /**
     * Unblocks Account
     */
    public void unblockAccount() {

    }
    /**
     * View Security Log
     */
    public void viewSecuriryLog() {

    }
}

