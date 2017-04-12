package com.example.frys.waters.model;

/**
 * Created by joon1 on 2017-03-11.
 */

public class Location {
    private double _latitude;
    private double _longitude;
    private String fullAddress;

    public Location() {

    }

    /**
     * creates Location with latitude and longitude
     * @param lat latitude
     * @param longit longitude
     */
    public Location(double lat, double longit) {
        _latitude = lat;
        _longitude = longit;
    }

    /**
     * getter for latitude
     * @return latitude
     */
    public double getLatitude() { return _latitude; }
    /**
     * getter for longitude
     * @return longitude
     */
    public double getLongitude() { return _longitude; }

    /**
     * setter for latitude
     * @param lat latitude
     */
    public void set_latitude(double lat) {
        this._latitude = lat;
    }

    /**
     * setter for longitude
     * @param longi longitude
     */
    public void set_longitude(double longi) {
        this._longitude = longi;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String address) {
        this.fullAddress = address;
    }

    /**
     * toString method
     * @return String form of latitude and longitude
     */
    public String toString() {
        return "" + getLatitude() + "," + getLongitude();
    }

}
