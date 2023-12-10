package com.zikriZulfaAzhimJBusRS;

import java.sql.Timestamp;
public class BookingThread extends Thread{
    private final Bus bus;
    private final Timestamp timestamp ;
    public BookingThread(String name, Bus bus, Timestamp timestamp){
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start();
    }

    public void run(){
        System.out.println(Thread.currentThread().getName() + " ID : " + Thread.currentThread().getId() + " is Running");
        synchronized (Payment.class) {
            // Make booking for the first seat on the bus
            boolean bookingSuccessful = Payment.makeBooking(timestamp, "RD01", bus);

            // Check if booking was successful
            if (bookingSuccessful) {
                System.out.println("Booking successful!");
            } else {
                System.out.println("Booking failed!");
            }
        }
    }
}
