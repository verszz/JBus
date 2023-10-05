package zikriZulfaAzhimJBusRS;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
/**
 * Write a description of class Payment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Payment extends Invoice
{
    private int busId;
    public Timestamp departureDate;
    public String busSeat;
    
    public Payment(int id, int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate){
        super(id, buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = departureDate;
        //this.departureDate.add(Calendar.DAY_OF_MONTH, 2);
    }
    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat, Timestamp departureDate){
        super(id, buyer, renter);
        this.busId = busId;
        this.departureDate = departureDate;
        //this.departureDate.add(Calendar.DAY_OF_MONTH, 2);
        this.busSeat = busSeat;
    }
    public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(departureSchedule) && schedule.isSeatAvailable(seat)) {
                return true;
            }
        }
        return false;
    }
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus) {
        if (isAvailable(departureSchedule, seat, bus)) {
            for (Schedule schedule : bus.schedules) {
                if (schedule.departureSchedule.equals(departureSchedule) && schedule.isSeatAvailable(seat)) {
                    schedule.bookSeat(seat);
                    return true;
                }
            }
        }
        return false;
    }
    public int getBusId(){
        return this.busId;
    }
    public String getDepartureInfo(){
        return "Bus ID: " + this.busId + 
        "\nBuyer ID: " + super.buyerId + 
        "\nRenter ID: " + super.renterId + 
        "\nPayment ID: " + super.id + 
        "\nDeparture Date: " + getTime() + 
        "\nBus Seat: " + this.busSeat;
    }
    public String getTime(){
        SimpleDateFormat SDFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        String curr_date = SDFormat.format(this.departureDate.getTime());
        return curr_date;
    }
}
