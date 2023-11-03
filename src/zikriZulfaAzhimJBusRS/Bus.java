package zikriZulfaAzhimJBusRS;

import java.io.IOException;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

/**
 * Write a description of class Bus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bus extends Serializable /*implements FileParser*/ {
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    public BusType busType;
    public City city;
    public Station departure;
    public Station arrival;
    public List<Schedule> schedules;

    public Bus(int id, String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival) {
        super();
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.city = city;
        this.departure = departure;
        this.arrival = arrival;
        this.schedules = new ArrayList<Schedule>();
    }

    public String toString() {
        return "Bus ID: "+this.id+"\nName: " + this.name + "\nFacility: " + this.facility + "\nPrice: " + this.price + "\nCapacity: "
                + this.capacity + "\nBus Type: " + this.busType + "\nCity: " + this.city + "\nDeparture: " + this.departure + "\nArrival: " + this.arrival;
    }

    public void addSchedule(Timestamp calendar){
        try{
            for(Schedule sch: this.schedules){
                if(sch.departureSchedule.equals(calendar)){
                    System.out.println("Schedule already exist");
                }else{
                    Schedule schedule = new Schedule(calendar, this.capacity);
                schedules.add(schedule);
                    System.out.println("Schedule has been added");
            }}
    } catch(Exception e) {
            System.out.println("Unexcepted Exception");
            e.printStackTrace();
        }}
    
    /*public void printSchedule(Schedule schedule){
        SimpleDateFormat SDFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        String curr_date = SDFormat.format(schedule.departureSchedule.getTime());
        System.out.println("Departure date: " + curr_date);
        System.out.println("Seat availability table:");
        for(String seat: schedule.seatAvailability.keySet()){
            System.out.println(seat + ":" + schedule.seatAvailability.get(seat)+"\n");
        }
    }*/

    /*@Override
    public boolean read(String content) {
        return false;
    }

    @Override
    public Object write() {
        return null;
    }*/
}