package com.zikriZulfaAzhimJBusRS;


public class Price{
    public double rebate;
    public double price; 
    
    
    public Price(double price, double rebate){
        this.price = price;
        this.rebate = rebate;
        
    }
    

    public Price(double price){
        this.price = price;
        this.rebate = 0;
        
    }
    
    public String toString(){
        return ""+this.price;
    }
    }

