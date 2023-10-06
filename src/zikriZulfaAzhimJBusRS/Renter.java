package zikriZulfaAzhimJBusRS;


/**
 * Write a description of class Renter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Renter extends Serializable {
    public String address;
    public String companyName;
    public int phoneNumber;

    public Renter(int id, String companyName) {
        super();
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = 0;
    }

    public Renter(int id, String companyName, String address) {
        super();
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = 0;
    }

    public Renter(int id, String companyName, int phoneNumber) {
        super();
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = phoneNumber;
    }

    public Renter(int id, String companyName, int phoneNumber, String address) {
        super();
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


}
