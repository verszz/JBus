package zikriZulfaAzhimJBusRS;

import java.sql.Timestamp;
public class BookingThread extends Thread{
    private Bus bus;
    private Timestamp timestamp ;
    public BookingThread(String name, Bus bus, Timestamp timestamp){
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start();
    }
    public void run(){
        System.out.println(this.getName() + " ID : " + this.getId() + " is Running");
        synchronized (bus){
            boolean BookRes = Payment.makeBooking(timestamp,"RS01", bus);
            if (BookRes) {
                System.out.println("Thread "+this.getId() + " Berhasil melakukan Booking");
            } else {
                System.out.println("Thread "+this.getId() + " Gagal melakukan Booking");
            }
        }
    }
}
