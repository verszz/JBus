package zikriZulfaAzhimJBusRS;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static final String REGEX_PHONE = "\\d{9,12}" ;
    private static final String REGEX_NAME = "^[A-Z][A-Z0-9_]{3,19}$";

    public boolean validate() {
        Pattern name = Pattern.compile(this.REGEX_NAME);
        Pattern phone_num = Pattern.compile(this.REGEX_PHONE);
        String phone_to_string = Integer.toString(this.phoneNumber);
        Matcher phone_match = phone_num.matcher(phone_to_string);
        Matcher name_match = name.matcher(this.companyName);
        boolean name_found = name_match.find();
        boolean phone_found = phone_match.find();

        return name_found && phone_found;

    }

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
