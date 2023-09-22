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
    
    public Station(int id, String stationName, City city){
        super(id);
        this.stationName = stationName;
        this.city = city;
    }
    public String print(){
        return "City:" + this.city + "\nStationName: " +this.stationName;
    }
}
