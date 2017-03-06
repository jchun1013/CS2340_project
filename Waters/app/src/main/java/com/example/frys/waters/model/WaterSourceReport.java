package com.example.frys.waters.model;


/**
 * Created by joon1 on 2017-03-05.
 */

public class WaterSourceReport {
    private String dateTime;
    private int reportNumber;
    private String nameOfReporter;
    private String location;
    private String condition;
    private String waterType;

    public WaterSourceReport(String dateTime, int reportNumber,
                             String nameOfReporter, String location, String condition, String waterType) {
        this.dateTime = dateTime;
        this.reportNumber = reportNumber;
        this.nameOfReporter = nameOfReporter;
        this.location = location;
        this.condition = condition;
        this.waterType = waterType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getReportNumber() {
        return reportNumber;
    }

    public String getNameOfReporter() {
        return nameOfReporter;
    }

    public String getLocation() {
        return location;
    }

    public String getCondition() {
        return condition;
    }
}
