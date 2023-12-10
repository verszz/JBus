package com.zikriZulfaAzhimJBusRS;

import com.zikriZulfaAzhimJBusRS.dbjson.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

/**
 * Write a description of class Bus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bus extends Serializable /*implements FileParser*/ {
    /**
     * Seat capacity of the bus
     */
    public int capacity;

    /**
     * Facility of the bus, calling another enum which is the Facility enum
     *
     * @see Facility
     */
    public List<Facility> facilities;

    /**
     * Name of the bus
     */
    public String name;

    /**
     * Price details of the Bus
     */
    public Price price;

    /**
     * The type of bus according to the BusType enum @see BusType
     */
    public BusType busType;

    /**
     * The Station the bus is going to according to the Station class @see Station
     */
    public Station arrival;

    /**
     * The Station the bus is departing from according to the Station class @see Station
     */
    public Station departure;

    public List<Schedule> schedules;
    public int accountId;

    /**
     * Create a new Bus based on the specified details
     *
     * @param accountId        The ID number of the bus, inherited from Serializable class
     * @param name      The name of the Bus
     * @param facilities  The facility of the Bus
     * @param price     The price details of the Bus
     * @param capacity  The seating capacity of the Bus
     * @param busType   The type of Bus
     * @param arrival   The station the bus is going to
     * @param departure The station the bus is departing from
     */
    public Bus(int accountId, String name, List<Facility> facilities, Price price, int capacity, BusType busType, Station departure, Station arrival) {
        super();
        this.accountId = accountId;
        this.capacity = capacity;
        this.facilities = facilities;
        this.name = name;
        this.price = price;
        this.busType = busType;
        this.arrival = arrival;
        this.departure = departure;
        this.schedules = new ArrayList<>();
    }

    /**
     * Returns a string that shows all the Bus details
     *
     * @return A string containing the Bus details
     */
    public String toString() {
        return "\nBus ID: " + super.id +
                ", Bus Name: " + name +
                ", Facility: " + facilities +
                ", Price: " + price.price +
                ", Capacity: " + capacity +
                ", Bus Type: " + busType +
                "Departure: " + "\n" + departure + "\n" +
                "Arrival: " + "\n" + arrival;
    }

    /**
     * Writes the object's data to a file
     *
     * @return An object representation of the written data
     */
    public Object write() {
        return null;
    }

    /**
     * Reads data from a specified file and updates the object's state accordingly
     *
     * @param filename The name or path of the file to read from
     * @return true if the read operation was successful, otherwise false
     */
    public boolean read(String filename) {
        return false;
    }

    /**
     *
     * @param calendar
     */
    public void addSchedule(Timestamp calendar) {
        // Check for duplicate schedules
        for (Schedule existingSchedule : schedules) {
            if (existingSchedule.departureSchedule.equals(calendar)) {
                // If a duplicate schedule is found, throw an exception
                throw new IllegalArgumentException("Duplicate schedule with the same timestamp exists.");
            }
        }

        // If no duplicate schedule found, add the new schedule
        Schedule schedule = new Schedule(calendar, this.capacity);
        schedules.add(schedule);
    }

    /**
     *
     * @param departureSchedule
     * @return
     */
    public boolean removeSchedule(Timestamp departureSchedule) {
        try {
            Schedule foundSchedule = null;
            for (Schedule existingSchedule : schedules) {
                if (existingSchedule.departureSchedule.equals(departureSchedule)) {
                    foundSchedule = existingSchedule;
                    break;
                }
            }

            if (foundSchedule != null) {
                schedules.remove(foundSchedule);
                return true;
            } else {
                return false; // Tidak ditemukan jadwal dengan Timestamp yang diberikan
            }
        } catch (Exception e) {
            System.err.println("Gagal menghapus jadwal: " + e.getMessage());
            return false; // Gagal menghapus jadwal
        }
    }


}