package com.zikriZulfaAzhimJBusRS;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.List;

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
    public List<String> busSeat;

    public Payment(int id, int buyerId, int renterId, List<String> busSeat, Timestamp departureDate){
        super(id, buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = departureDate;
        //this.departureDate.add(Calendar.DAY_OF_MONTH, 2);
    }
    public Payment(int id, Account buyer, Renter renter, int busId, List<String> busSeat, Timestamp departureDate){
        super(id, buyer, renter);
        this.busId = busId;
        this.departureDate = departureDate;
        //this.departureDate.add(Calendar.DAY_OF_MONTH, 2);
        this.busSeat = busSeat;
    }
//    public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus) {
//        for (Schedule schedule : bus.schedules) {
//            if (schedule.departureSchedule.equals(departureSchedule) && schedule.isSeatAvailable(seat)) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public static Schedule availableSchedule(Timestamp departureSchedule, String seat, Bus bus) {
//        for (Schedule schedule : bus.schedules) {
//            if (schedule.departureSchedule.equals(departureSchedule) && Algorithm.exists(schedule.seatAvailability.keySet(), seat)) {
//                return schedule;
//            }
//        }
//        return null;
//    }
//    public static List<Schedule> availableSchedule(Timestamp departureSchedule, List<String> seats, Bus bus) {
//        List<Schedule> availableSchedules = new ArrayList<>();
//        for (Schedule s : bus.schedules) {
//            if (s.departureSchedule.equals(departureSchedule)) {
//                boolean allSeatsAvailable = true;
//                for (String seat : seats) {
//                    if (!s.isSeatAvailable(seat)) {
//                        allSeatsAvailable = false;
//                        break;
//                    }
//                }
//                if (allSeatsAvailable) {
//                    availableSchedules.add(s);
//                }
//            }
//        }
//        return availableSchedules;
//    }
//
//    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus){
//        Schedule schedule = availableSchedule(departureSchedule, seat, bus);
//        if (schedule != null) {
//            schedule.bookSeat(seat);
//            return true;
//        }
//        return false;
//    }
//
//    public static boolean makeBooking(Timestamp departureSchedule, List<String> seats, Bus bus) {
//        List<Schedule> availableSchedules = availableSchedule(departureSchedule, seats, bus);
//        if (!availableSchedules.isEmpty()) {
//            for (Schedule schedule : availableSchedules) {
//                for (String seat : seats) {
//                    schedule.bookSeat(seat);
//                }
//            }
//            return true;
//        }
//        return false;
//    }

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

    public static Schedule availableSchedule(Timestamp departureDate, String seat, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(departureDate) && schedule.isSeatAvailable(seat)) {
                return schedule;
            }
        }
        return null;
    }

    public static boolean makeBooking(Timestamp departureDate, String seat, Bus bus) {
        Schedule schedule = availableSchedule(departureDate, seat, bus);
        if (schedule != null) {
            schedule.bookSeat(seat);
            return true;
        }
        return false;
    }
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