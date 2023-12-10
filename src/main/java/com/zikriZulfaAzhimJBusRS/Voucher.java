package com.zikriZulfaAzhimJBusRS;


import com.zikriZulfaAzhimJBusRS.dbjson.Serializable;

public class Voucher extends Serializable  {
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;

    /**
     * Constructs a Voucher object with the specified attributes.
     * @param id The identifier of the voucher.
     * @param name The name of the voucher.
     * @param code The code of the voucher.
     * @param type The type of the voucher (either DISCOUNT or REBATE).
     * @param minimum The minimum price required for applying the voucher.
     * @param cut The value of the discount or rebate.
     */
    public Voucher(int id, String name, int code, Type type, double minimum, double cut) {
        //super();
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }

    /**
     * Checks if the voucher has been used.
     * @return True if the voucher has been used, otherwise false.
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * Checks if the voucher can be applied based on the price.
     * @param price The price object to which the voucher is applied.
     * @return True if the voucher can be applied, otherwise false.
     */
    public boolean canApply(Price price) {
        return !used && price.price > minimum;
    }

    /**
     * Applies the voucher to the given price.
     * @param price The price object to which the voucher is applied.
     * @return The discounted price after applying the voucher.
     */
    public double apply(Price price) {
        if(canApply(price)){
        this.used = true;
        if (type == Type.DISCOUNT) {
            if (this.cut >= 100.0) {
                return 0.0d;
            } else {
                return price.price - (this.cut * price.price / 100);
            }
        } else if (type == Type.REBATE) {
            if (this.cut >= price.price) {
                return 0.0d;
            } else {
                return price.price - this.cut;
            }
            }
        } else {
            return price.price;
        }
        return price.price;}


    /**
     * Reads voucher information from a file.
     * @param fileName The name of the file to read.
     * @return True if reading is successful, otherwise false.
     */
    public boolean read(String fileName) {
        return false;
    }

    /**
     * Writes voucher information to a file.
     * @return The object written to the file.
     */
    public Object write() {
        return null;
    }
}
