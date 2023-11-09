package com.zikriZulfaAzhimJBusRS;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Schedule here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Schedule
{   
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;
    public Schedule(Timestamp departureSchedule, int numberOfSeats)
    {
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }
    
    private void initializeSeatAvailability(int numberOfSeats){
        seatAvailability = new LinkedHashMap<>();
        for(int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++){
            String sn = seatNumber < 10 ? "0"+seatNumber : ""+seatNumber;
            seatAvailability.put("RS" + sn, true);
        }
    }
    
    public boolean isSeatAvailable(String seat) {
        return seatAvailability.get(seat) != null && seatAvailability.get(seat);
    }

    public boolean isSeatAvailable(List<String> seats) {
        for(String seat : seats){
            if(seatAvailability.get(seat)!=null && seatAvailability.get(seat)){
                return true;
            }
        }
        return false;
    }
    public void bookSeat(String seat) {
        seatAvailability.put(seat, false);
    }

    public void bookSeat(List<String> seats) {
        for(String seat : seats){
            if(seatAvailability.containsKey(seat)){
                seatAvailability.put(seat, false);
            }
        }
    }
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
