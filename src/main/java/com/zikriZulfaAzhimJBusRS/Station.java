package com.zikriZulfaAzhimJBusRS;


import com.zikriZulfaAzhimJBusRS.dbjson.Serializable;

/**
 * Represents a bus station with its location details.
 */
public class Station extends Serializable {
    public City city;
    public String stationName;
    public String address;

    /**
     * Constructs a Station object with the provided details.
     * @param stationName The name of the station.
     * @param city The city where the station is located.
     * @param address The address of the station.
     */
    public Station(String stationName, City city, String address) {
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }

    /**
     * Generates a string representation of the Station object.
     * @return A string representation including Station ID, city, station name, and address.
     */
    @Override
    public String toString() {
        return "\nStation ID: "+id+"\nCity: " + city + "\nStation Name: " + stationName + "\nAddress: " + address;
    }
}
