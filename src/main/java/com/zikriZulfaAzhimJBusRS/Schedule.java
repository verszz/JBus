package com.zikriZulfaAzhimJBusRS;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Represents the schedule for a bus departure including seat availability.
 */
public class Schedule
{   
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;

    /**
     * Constructs a schedule for a bus departure with initial seat availability.
     * @param departureSchedule The timestamp for the departure schedule.
     * @param numberOfSeats The number of seats available for the schedule.
     */

    public Schedule(Timestamp departureSchedule, int numberOfSeats)
    {
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }

    /**
     * Books a single seat for the schedule.
     * @param seat The seat to be booked.
     */
    public void bookSeat(String seat) {
        if (isSeatAvailable(seat)) {
            seatAvailability.put(seat, false);
        }
    }

    /**
     * Books multiple seats for the schedule.
     * @param seats The list of seats to be booked.
     */
    public void bookSeat(List<String> seats) {
        for(String seat : seats){
            if(seatAvailability.containsKey(seat)){
                seatAvailability.put(seat, false);
            }
        }
    }

    /**
     * Prints the schedule including the departure date and seat availability.
     */
    public void printSchedule() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        // Print tanggal keberangkatan
        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);
        // Print daftar kursi dan ketersediaan kursinya
        System.out.println("Daftar kursi dan ketersediaan kursinya: ");
        // Create a list of seats and sort them numerically
        int maxSeatsPerRow = 4; // Assuming there are 4 seats per row
        int currentSeat = 1;
        for (String seat : this.seatAvailability.keySet()) {
            String symbol = this.seatAvailability.get(seat)? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat ++;
        }
        System.out.println("\n");
    }

    /**
     * Initializes seat availability based on the number of seats.
     * @param numberOfSeats The number of seats to initialize.
     */
    private void initializeSeatAvailability(int numberOfSeats){
        seatAvailability = new LinkedHashMap<>();
        for(int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++){
            String sn = seatNumber < 10 ? "0"+seatNumber : ""+seatNumber;
            seatAvailability.put("RS" + sn, true);
        }
    }

    /**
     * Checks if a seat is available.
     * @param seat The seat to be checked.
     * @return True if the seat is available, otherwise false.
     */
    public boolean isSeatAvailable(String seat) {
        return seatAvailability.get(seat) != null && seatAvailability.get(seat);
    }

    /**
     * Checks if multiple seats are available.
     * @param seats The list of seats to be checked.
     * @return True if all seats are available, otherwise false.
     */
    public boolean isSeatAvailable(List<String> seats) {
        for (String seat : seats) {
            if (!isSeatAvailable(seat)) {
                return false; // Jika salah satu kursi tidak tersedia, return false
            }
        }
        return true; // Semua kursi tersedia
    }

    /**
     * Provides a string representation of the schedule.
     * @return A string representation of the schedule.
     */
    @Override
    public String toString() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        String formattedDepartureSchedule = date.format(this.departureSchedule.getTime());

        int totalSeats = seatAvailability.size();
        int occupiedSeats = (int) seatAvailability.values().stream().filter(available -> !available).count();

        return "Schedule: " + formattedDepartureSchedule +
                "\nOccupied: " + occupiedSeats + "/" + totalSeats;
    }
}
