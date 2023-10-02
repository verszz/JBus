package zikriZulfaAzhimJBusRS;
import java.util.ArrayList;


/**
 * Write a description of class Validate here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Validate
{
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
