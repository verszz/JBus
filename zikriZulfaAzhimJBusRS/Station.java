package zikriZulfaAzhimJBusRS;


/**
 * Write a description of class Station here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Station extends Serializable
{
    public City city;
    public String stationName;
    public String address;
    
    public Station(int id, String stationName, City city, String address){
        super(id);
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }
    public String toString(){
        return "\nCity: " + this.city + "\nStationName: " +this.stationName+"\nAddress: "+this.address;
    }
}
