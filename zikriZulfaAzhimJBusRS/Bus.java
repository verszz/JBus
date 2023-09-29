package zikriZulfaAzhimJBusRS;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;


/**
 * Write a description of class Bus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bus extends Serializable implements FileParser
{   
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    public BusType busType;
    public City city;
    public Station departure;
    public Station arrival;
    public List<Schedule> schedules;
    
    public Bus(int id, String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival){
        super(id);
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
    
    public String toString(){
        return "Name: "+this.name+"\nFacility: "+this.facility+"\nPrice: "+this.price+"\nCapacity: "
        +this.capacity+"\nBus Type: "+this.busType+"\nCity: "+this.city+"\nDeparture: "+this.departure+"\nArrival: "+this.arrival;
    }
    
    public void addSchedule(Calendar calendar){
        Schedule schedule = new Schedule(calendar, this.capacity);
        this.schedules.add(schedule);
    }
    
    /*public void printSchedule(Schedule schedule) {
        System.out.println("Departure date: " + schedule.departureSchedule);
        System.out.println("Seat availability table:");
        for(int i = 0; i <= schedule.seatAvailability.length; i++){
            System.out.println(Schedule.seatAvailability[i]);
        }
        
    }*/
    
    @Override
    public boolean read(String content) {
        return false;
    }

    @Override
    public Object write() {
        return null;
    }
}