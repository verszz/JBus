package com.zikriZulfaAzhimJBusRS;


import com.zikriZulfaAzhimJBusRS.dbjson.Serializable;

/**
 * Write a description of class Station here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Station extends Serializable {
    public City city;
    public String stationName;
    public String address;

    public Station(String stationName, City city, String address) {
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }

    public String toString() {
        return "\nStation ID: "+id+"\nCity: " + this.city + "\nStation Name: " + this.stationName + "\nAddress: " + this.address;
    }
}
