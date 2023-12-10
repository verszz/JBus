package com.zikriZulfaAzhimJBusRS;
import java.util.ArrayList;


/**
 * The Validate class contains methods for filtering Price objects based on price values.
 */
public class Validate
{public Validate(){
}
    /**
     * Filters an array of Price objects based on the specified value and condition.
     * @param list An array of Price objects to be filtered.
     * @param value The value used as a filter condition.
     * @param less A boolean flag indicating whether to filter values less than or greater than the specified value.
     * @return An ArrayList of Double containing filtered price values.
     */
    public static ArrayList filter (Price[] list, int value, boolean less){
        ArrayList<Double> filteredList = new ArrayList<Double>();
        for(Price price : list){
            if(less){
                if(price.price <= value){
                    filteredList.add(price.price);
                }
            }
            else{
                if(price.price > value){
                    filteredList.add(price.price);
                }
            }
        }
        return filteredList;
    }
}
