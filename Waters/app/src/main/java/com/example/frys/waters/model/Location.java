package com.example.frys.waters.model;

/**
 * Created by joon1 on 2017-03-11.
 */

public class Location {
    private double _latitude;
    private double _longitude;

    public Location(double lat, double longit) {
        _latitude = lat;
        _longitude = longit;
    }

    public double getLatitude() { return _latitude; }
    public double get_longitude() { return _longitude; }

    public void set_latitude(double lat) {
        this._latitude = lat;
    }

    public void set_longitude(double longi) {
        this._longitude = longi;
    }

    public String toString() {
        return " ~ ";
    }
}
