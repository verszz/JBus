package com.zikriZulfaAzhimJBusRS;


import com.zikriZulfaAzhimJBusRS.dbjson.Serializable;

/**
 * Represents a review in the system.
 */
public class Review extends Serializable {
    public String date;
    public String desc;


    /**
     * Constructs a review with an ID, date, and description.
     * @param id The ID of the review.
     * @param date The date of the review.
     * @param desc The description of the review.
     */
    public Review(int id, String date, String desc) {
        super();
        this.date = date;
        this.desc = desc;
    }


    /**
     * Returns a string representation of the review.
     * @return A string containing the date and description of the review.
     */
    public String toString() {
        return "Date: " + this.date + "\nDescription: " + this.desc;
    }
}
