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


    public boolean read(String Content) {
        return false;
    }


    public Object write() {
        return null;
    }
    public Bus(String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival) {
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