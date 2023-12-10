package com.zikriZulfaAzhimJBusRS;

/**
 * Represents a rating system for calculating average ratings.
 */
 public class Rating{
    private long count;
    private long total;

    /**
     * Constructor to initialize count and total sum of ratings.
     */
    public Rating(){
            this.count = 0;
            this.total = 0;
    }

    /**
     * Inserts a rating into the system and updates the total and count.
     * @param rating The rating to be inserted.
     */
    public void insert(int rating){
            total += rating;
            count++;
        }

    /**
     * Calculates and returns the average rating.
     * @return The average rating.
     */
    public double getAverage(){
            long rataRataTemp;
            if(this.count == 0){
                return 0;
            }
            else{
                rataRataTemp = this.total / this.count;
                double rataRataFinal = (double) rataRataTemp;
                return rataRataFinal;
            }
    }

    /**
     * Retrieves the count of ratings.
     * @return The count of ratings.
     */
    public long getCount(){
            return this.count;
        }

    /**
     * Retrieves the total sum of ratings.
     * @return The total sum of ratings.
     */
        public long getTotal(){
            return this.total;
        }

    /**
     * Provides the string representation of count and total.
     * @return The string representation of count and total.
     */
    public String toString(){
        return "Count: "+this.count+"\nTotal: "+this.total;
    }
}
