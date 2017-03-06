package com.example.frys.waters.model;

/**
 * Created by joon1 on 2017-03-05.
 */

public class WaterPurityReport {
    //LocalDateAndTime?
    private String dateTime;
    private int reportNumber;
    private String nameOfWorker;
    private String location;
    //enum condition?
    private String condition;
    //PPM in double?
    private String virusPPM;
    private String contaminantPPM;

    public WaterPurityReport(String dateTime, int reportNumber, String nameOfWorker
            , String location, String condition, String virusPPM, String contaminantPPM) {
        this.dateTime = dateTime;
        this.reportNumber = reportNumber;
        this.nameOfWorker = nameOfWorker;
        this.location = location;
        this.condition = condition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getReportNumber() {
        return reportNumber;
    }

    public String getNameOfWorker() {
        return nameOfWorker;
    }

    public String getLocation() {
        return location;
    }

    public String getCondition() {
        return condition;
    }

    public String getVirusPPM() {
        return virusPPM;
    }

    public String getContaminantPPM() {
        return contaminantPPM;
    }
}
