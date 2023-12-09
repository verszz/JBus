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
    public int capacity;
    public List<Facility> facility;
    public String name;
    public Price price;
    public BusType busType;
    public Station departure;
    public Station arrival;
    public List<Schedule> schedules;
    public int accountId;


    public boolean read(String filename) {
        return false;
    }


    public Object write() {
        return null;
    }
    public Bus(int accountId, String name, List<Facility> facility, Price price, int capacity, BusType busType, Station departure, Station arrival) {
        this.accountId = accountId;
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.departure = departure;
        this.arrival = arrival;
        this.schedules = new ArrayList<Schedule>();
    }

    public String toString() {
        return "Bus ID: "+this.id+"\nName: " + this.name + "\nFacility: " + this.facility + "\nPrice: " + this.price + "\nCapacity: "
                + this.capacity + "\nBus Type: " + this.busType + "\nDeparture: " + this.departure + "\nArrival: " + this.arrival;
    }

    public void addSchedule(Timestamp departureSchedule) {
        try {
            for (Schedule Exist : schedules) {
                if (Exist.departureSchedule.equals(departureSchedule)) {
                    System.out.println(("Schedule already exist"));
                    return;
                }
            }
            schedules.add(new Schedule(departureSchedule, this.capacity));
        }catch(Exception e){
            System.err.println("Cannot adding schedule" + e.getMessage());
            e.printStackTrace();
        }
    }

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