package com.zikriZulfaAzhimJBusRS;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.List;

/**
 * Represents a Payment extending Invoice class.
 * Manages the payment details for a bus booking transaction.
 */
public class Payment extends Invoice
{
    /** The ID of the bus associated with the payment. */
    public final int busId;

    /** The departure date for the payment. */
    public Timestamp departureDate;

    /** List of bus seats associated with the payment. */
    public List<String> busSeat;
    /**
     * Constructor for creating a Payment object.
     * @param buyerId ID of the buyer.
     * @param renterId ID of the renter.
     * @param busId ID of the bus.
     * @param busSeat List of bus seats.
     * @param departureDate Timestamp representing the departure date.
     */
    public Payment(int buyerId, int renterId, int busId, List<String> busSeat, Timestamp departureDate){
        super(buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = departureDate;
        //this.departureDate.add(Calendar.DAY_OF_MONTH, 2);
    }
    /**
     * Constructor for creating a Payment object using Account and Renter objects.
     * @param buyer Account of the buyer.
     * @param renter Renter associated with the payment.
     * @param busId ID of the bus.
     * @param busSeat List of bus seats.
     * @param departureDate Timestamp representing the departure date.
     */
    public Payment(Account buyer, Renter renter, int busId, List<String> busSeat, Timestamp departureDate){
        super(buyer, renter);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }

    /**
     * Checks for the availability of a schedule based on the departure date, seats, and bus.
     * @param departureDate Timestamp representing the departure date.
     * @param seats List of seats to check availability.
     * @param bus The Bus object.
     * @return Schedule object if available, else returns null.
     */
    public static Schedule availableSchedule(Timestamp departureDate, List<String> seats, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(departureDate)) {
                boolean allSeatsAvailable = true;
                for (String seat : seats) {
                    if (!schedule.isSeatAvailable(seat)) {
                        allSeatsAvailable = false;
                        break;
                    }
                }
                if (allSeatsAvailable) {
                    return schedule;
                }
            }
        }
        return null;
    }

    /**
     * Checks for the availability of a schedule based on the departure date, seat, and bus.
     * @param departureDate Timestamp representing the departure date.
     * @param seat Seat to check availability.
     * @param bus The Bus object.
     * @return Schedule object if available, else returns null.
     */
    public static Schedule availableSchedule(Timestamp departureDate, String seat, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(departureDate) && schedule.isSeatAvailable(seat)) {
                return schedule;
            }
        }
        return null;
    }

    /**
     * Books a seat for a specific schedule.
     * @param departureDate Timestamp representing the departure date.
     * @param seat Seat to book.
     * @param bus The Bus object.
     * @return True if booking is successful, else false.
     */
    public static boolean makeBooking(Timestamp departureDate, String seat, Bus bus) {
        Schedule schedule = availableSchedule(departureDate, seat, bus);
        if (schedule != null) {
            schedule.bookSeat(seat);
            return true;
        }
        return false;
    }

    /**
     * Books multiple seats for a specific schedule.
     * @param departureDate Timestamp representing the departure date.
     * @param seats List of seats to book.
     * @param bus The Bus object.
     * @return True if booking is successful, else false.
     */
    public static boolean makeBooking(Timestamp departureDate, List<String> seats, Bus bus) {
        boolean allSeatsAvailable = true;
        Schedule schedule = availableSchedule(departureDate, seats, bus);

        if (schedule != null) {
            for (String seat : seats) {
                if (!schedule.isSeatAvailable(seat)) {
                    allSeatsAvailable = false;
                    break;
                }
            }
            if (allSeatsAvailable) {
                for (String seat : seats) {
                    schedule.bookSeat(seat);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the bus ID associated with the payment.
     * @return The bus ID.
     */
    public int getBusId(){
        return this.busId;
    }
    /**
     * Retrieves departure information for the payment.
     * @return Details regarding the departure.
     */
    public String getDepartureInfo(){
        SimpleDateFormat date = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        return "Bus ID: " + busId +
                "\nBuyer ID: " + this.buyerId +
                "\nRenter ID: " + this.renterId +
                "\nPayment ID: " + super.id +
                "\nDeparture Date: " + date.format(departureDate.getTime()) +
                "\nBus Seat: " + busSeat;
    }
    /**
     * Retrieves the formatted time for the departure date.
     * @return Formatted date and time.
     */
    public String getTime(){
        SimpleDateFormat SDFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        String curr_date = SDFormat.format(this.departureDate.getTime());
        return curr_date;
    }
}