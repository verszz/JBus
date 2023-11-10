package com.zikriZulfaAzhimJBusRS;


import com.zikriZulfaAzhimJBusRS.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Serializable /*implements FileParser*/ {
    public String email;
    public String name;
    public String password;
    public Renter company;
    public double balance;
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-z]{2,}(\\.[a-zA-Z]+)*$";


    public boolean validate() {
        Pattern password = Pattern.compile(this.REGEX_PASSWORD);
        Pattern email = Pattern.compile(this.REGEX_EMAIL);
        Matcher password_match = password.matcher(this.password);
        Matcher email_match = email.matcher(this.email);
        boolean password_found = password_match.find();
        boolean email_found = email_match.find();

        return password_found && email_found;

    }
    public Account(String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0;
        this.company = null;
    }

    public String toString() {
        return "Name: " + this.name + "\nEmail: " + this.email + "\nPassword: " + this.password;
    }

    public boolean topUp(double amount){
        if(amount < 0)
            return false;
        balance += amount;
        return true;
    }

    /*@Override
    public boolean read(String content) {
        return false;
    }

    @Override
    public Object write() {
        return null;
    }*/
}
