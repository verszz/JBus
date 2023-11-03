package zikriZulfaAzhimJBusRS;


public class Voucher extends Serializable /*implements FileParser*/ {
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;

    public Voucher(int id, String name, int code, Type type, double minimum, double cut) {
        //super();
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }

    public boolean isUsed() {
        return this.used;
    }

    public boolean canApply(Price price) {

        if (price.price > this.minimum && !this.used) {
            return true;
        } else {
            return false;
        }
    }

    public double apply(Price price) {
        this.used = true;
        if (this.type == Type.DISCOUNT) {
            if (this.cut >= 100.0) {
                return 0.0d;
            } else {
                return price.price - (this.cut * price.price / 100);
            }
        } else if (this.type == Type.REBATE) {
            if (this.cut >= price.price) {
                return 0.0d;
            } else {
                return price.price - this.cut;
            }
        } else {
            return price.price;
        }
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
