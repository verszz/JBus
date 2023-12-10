package com.zikriZulfaAzhimJBusRS;


/**
 * Represents the pricing details for a bus.
 */
public class Price {
    /** The rebate amount for the bus price. */
    public double rebate;

    /** The original price of the bus. */
    public double price;

    /**
     * Constructor for creating a Price object with both price and rebate.
     * @param price The original price of the bus.
     * @param rebate The rebate amount for the bus price.
     */
    public Price(double price, double rebate){
        this.price = price;
        this.rebate = rebate;
    }

    /**
     * Constructor for creating a Price object with only the price.
     * @param price The original price of the bus.
     */
    public Price(double price){
        this.price = price;
    }

    /**
     * Provides the string representation of the price.
     * @return The string representation of the price.
     */
    public String toString(){
        return ""+this.price;
    }
}

