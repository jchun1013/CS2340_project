package com.example.frys.waters.model;

/**
 * Created by joon1 on 2017-03-05.
 */

public class WaterPurityReport {
    //LocalDateAndTime?
    private String dateTime;
    private int reportNumber;
    private String nameOfWorker;
    private Location location;
    //enum condition?
    private String condition;
    //PPM in double?
    private double virusPPM;
    private double contaminantPPM;

    public WaterPurityReport() {

    }

    public WaterPurityReport(String dateTime, int reportNumber, String nameOfWorker
            , Location location, String condition, double virusPPM, double contaminantPPM) {
        this.dateTime = dateTime;
        this.reportNumber = reportNumber;
        this.nameOfWorker = nameOfWorker;
        this.location = location;
        this.condition = condition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
    }

    /**
     * Get date and time of the report
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * get report number of the water purity report
     */
    public int getReportNumber() {
        return reportNumber;
    }

    /**
     * get the name of the worker who submitted the report
     */
    public String getNameOfWorker() {
        return nameOfWorker;
    }

    /**
     * get the location of the report
     */
    public Location getLocation() {
        return location;
    }

    /**
     * get the water condition from the report submitted
     */
    public String getCondition() {
        return condition;
    }

    /**
     * get the virus in ppm of the report submitted
     */
    public double getVirusPPM() {
        return virusPPM;
    }

    /**
     * Get the contaminant in ppm of the report submitted
     */
    public double getContaminantPPM() {
        return contaminantPPM;
    }
}
