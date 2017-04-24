package Model;

/**
 * Created by joon1 on 2017-04-24.
 */
public class WaterSourceReport {
    private String dateTime;
    private int reportNumber;
    private String nameOfReporter;
    private Location location;
    private String condition;
    private String waterType;

    public WaterSourceReport() {

    }

    public WaterSourceReport(String dateTime, int reportNumber,
                             String nameOfReporter, Location location, String condition, String waterType) {
        this.dateTime = dateTime;
        this.reportNumber = reportNumber;
        this.nameOfReporter = nameOfReporter;
        this.location = location;
        this.condition = condition;
        this.waterType = waterType;
    }

    /**
     * Get the date and time of the historical report submitted
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Get historical reports' report number
     */
    public int getReportNumber() {
        return reportNumber;
    }

    /**
     * returns the name of reporter
     */
    public String getNameOfReporter() {
        return nameOfReporter;
    }

    /**
     * returns location of the historical report
     */
    public Location getLocation() {
        return location;
    }

    /**
     * the water condition recorded in the historical report
     */
    public String getCondition() {
        return condition;
    }

    /**
     * returns the type of water recorded in the historical report
     */
    public String getTypeOfWater() { return waterType; }

    public void setTypeOfWater(String waterType) {
        this.waterType = waterType;
    }

    public void setLocation(double lat, double log) {
        this.location = new Location(lat, log);
    }

    /**
     * returns string representation of the historical report
     */
    public String toString() {
//        return "Date and Time: " + getDateTime() + "\n"
//                + "Report Number: " + getReportNumber() + "\n"
//                + "Name of Reporter: " + getNameOfReporter() + "\n"
//                + "Location: " + getLocation() + "\n"
//                + "Condition: " + getCondition();
        return "one";
    }
}
