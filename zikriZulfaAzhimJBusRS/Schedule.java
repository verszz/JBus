package zikriZulfaAzhimJBusRS;
import java.util.Calendar;
import java.util.Map;
import java.util.LinkedHashMap;


/**
 * Write a description of class Schedule here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Schedule
{   
    public Calendar departureSchedule;
    public Map<String, Boolean> seatAvailability;
    public Schedule(Calendar departureSchedule, int numberOfSeats)
    {
        this.departureSchedule = departureSchedule;
    }
    
    private void initializeSeatAvailability(int numberOfSeats){
        Map<String, Boolean> seatAvailability = new LinkedHashMap<String, Boolean>();
        for(int i = 0; i <= numberOfSeats; i++){
            seatAvailability.put("RS" + i, true);
        }
    }
    
    
}
