package com.zikriZulfaAzhimJBusRS;

import com.zikriZulfaAzhimJBusRS.dbjson.Serializable;

import java.sql.Timestamp;

/**
 * Class representing an Invoice.
 * Manages the details of transactions between a buyer and a renter.
 * Extends Serializable for serialization purposes.
 */
public class Invoice extends Serializable {
    /** ID of the buyer associated with the invoice. */
    public int buyerId;

    /** ID of the renter associated with the invoice. */
    public int renterId;

    /** Timestamp representing the time of the invoice creation. */
    public Timestamp time;

    /** Enum representing the rating of the bus in the invoice. */
    public BusRating rating;

    /** Enum representing the status of the payment in the invoice. */
    public PaymentStatus status;

    /**
     * Enum for BusRating indicating the rating of the bus.
     * Can be NONE, NEUTRAL, GOOD, or BAD.
     */
    public enum BusRating {
        NONE, NEUTRAL, GOOD, BAD;
    }

    /**
     * Enum for PaymentStatus indicating the status of the payment.
     * Can be FAILED, WAITING, or SUCCESS.
     */
    public enum PaymentStatus {
        FAILED, WAITING, SUCCESS;
    }

    /**
     * Constructor to create an Invoice with buyerId and renterId.
     * @param buyerId ID of the buyer.
     * @param renterId ID of the renter.
     */
    protected Invoice(int buyerId, int renterId) {
        super();
        this.time = new Timestamp(System.currentTimeMillis());
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    /**
     * Constructor to create an Invoice using Account and Renter objects.
     * @param buyer Account of the buyer.
     * @param renter Renter associated with the invoice.
     */
    public Invoice(Account buyer, Renter renter) {
        super();
        this.time = new Timestamp(System.currentTimeMillis());
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    /**
     * Returns the string representation of the Invoice object.
     * @return String representation of the Invoice.
     */
    public String toString(){
        return "Invoice Id: " + super.id +
                ", Buyer Id: " + buyerId +
                ", Renter Id: " + renterId +
                ", Rating: " + rating +
                ", Status: " + status;
    }
}
