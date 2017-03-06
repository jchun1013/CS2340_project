package com.example.frys.waters.model;

/**
 * Created by joon1 on 2017-03-05.
 */

public class Manager extends Worker {
    /**
     * Creates Manager
     * @param username
     * @param name
     * @param email
     * @param password
     * @param address
     */
    public Manager(String username, String name, String email, String password, String address) {
        super(username, name, email, password, address);
    }

    /**
     * View Historical Report
     */
    public void viewHistoricalReport() {

    }

    /**
     * View Trend of Water Purity
     */
    public void viewTrendOfWaterPurity() {

    }

    /**
     * Delete Report
     */
    public void deleteReport() {

    }
}
