package com.example.frys.waters.model;


/**
 * Created by joon1 on 2017-03-05.
 */

public class WaterSourceReport {
    private String dateTime;
    private int reportNumber;
    private String nameOfReporter;
    private Location location;
    private String condition;
    private String waterType;


    public WaterSourceReport(String dateTime, int reportNumber,
                             String nameOfReporter, Location location, String condition, String waterType) {
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

    public Location getLocation() {
        return location;
    }

    public String getCondition() {
        return condition;
    }

    public String getTypeOfWater() { return waterType; }

    public String toString() {
        return "Date and Time: " + getDateTime() + "\n"
                + "Report Number: " + getReportNumber() + "\n"
                + "Name of Reporter: " + getNameOfReporter() + "\n"
                + "Location: " + getLocation() + "\n"
                + "Condition: " + getCondition();
    }
}
