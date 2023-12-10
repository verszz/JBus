package com.zikriZulfaAzhimJBusRS;


import com.zikriZulfaAzhimJBusRS.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Renter in the system who provides services for bus rental.
 */
public class Renter extends Serializable {
    public String address;
    public String companyName;
    public String phoneNumber;
    private static final String REGEX_PHONE = "\\d{9,12}" ;
    private static final String REGEX_NAME = "^[A-Z][A-Z0-9_]{3,19}$";

    /**
     * Validates the company name and phone number of the renter.
     * @return True if the company name and phone number are valid, otherwise false.
     */
    public boolean validate() {
        Pattern name = Pattern.compile(this.REGEX_NAME);
        Pattern phone_num = Pattern.compile(this.REGEX_PHONE);
        Matcher phone_match = phone_num.matcher(this.phoneNumber);
        Matcher name_match = name.matcher(this.companyName);
        boolean name_found = name_match.find();
        boolean phone_found = phone_match.find();

        return name_found && phone_found;

    }

    /**
     * Constructs a renter with a company name.
     * @param companyName The name of the company.
     */
    public Renter(String companyName) {
        super();
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = "";
    }

    /**
     * Constructs a renter with a phone number and company name.
     * @param phoneNumber The phone number of the renter.
     * @param companyName The name of the company.
     */
    public Renter(String phoneNumber, String companyName) {
        super();
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = phoneNumber;
    }

    /**
     * Constructs a renter with a company name, phone number, and address.
     * @param companyName The name of the company.
     * @param phoneNumber The phone number of the renter.
     * @param address The address of the renter.
     */
    public Renter(String companyName, String phoneNumber, String address) {
        super();
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


}
