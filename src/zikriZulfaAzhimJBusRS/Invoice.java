package zikriZulfaAzhimJBusRS;
import java.util.Calendar;
import java.sql.Timestamp;


public class Invoice extends Serializable
{
    public int buyerId;
    public int renterId;
    public Timestamp time;
    public BusRating rating;
    public PaymentStatus status;
    
    public enum BusRating{
        NONE, NEUTRAL, GOOD,BAD;
    }
    public enum PaymentStatus{
        FAILED, WAITING, SUCCESS;
    }
    protected Invoice(int id, int buyerId, int renterId){
        super(id);
        this.time = new Timestamp(System.currentTimeMillis());
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    public Invoice(int id, Account buyer, Renter renter){
        super(id);
        this.time = new Timestamp(System.currentTimeMillis());
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    /*public String toString(){
        return "BuyerId: " + this.buyerId + "\nRenterId: " + this.renterId + "\nTime: " + this.time;
    }*/
    
}
