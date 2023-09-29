package zikriZulfaAzhimJBusRS;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Payment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Payment extends Invoice
{
    private int busId;
    public Calendar departureDate;
    public String busSeat;
    
    public Payment(int id, int buyerId, int renterId, int busId, String busSeat){
        super(id, buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = Calendar.getInstance();
        this.departureDate.add(Calendar.DAY_OF_MONTH, 2);
    }
    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat){
        super(id, buyer, renter);
        this.busId = busId;
        this.departureDate = Calendar.getInstance();
        this.departureDate.add(Calendar.DAY_OF_MONTH, 2);
        this.busSeat = busSeat;
    }
    public String toString(){
        return "BusId: " + this.busId + "\nDepartureDate: " + this.departureDate + "\nBusSeat: " + this.busSeat;
    }
    public int getBusId(){
        return this.busId;
    }
    public String getDepartureInfo(){
        return "BusId: " + this.busId + "\nDeparture Date: " + this.getTime() + "\nBus Seat: " + this.busSeat;
    }
    public String getTime(){
        SimpleDateFormat SDFormat = new SimpleDateFormat("'MMMM dd, yyyy HH:mm:ss");
        String curr_date = SDFormat.format(departureDate.getTime());
        return curr_date;
    }
}
