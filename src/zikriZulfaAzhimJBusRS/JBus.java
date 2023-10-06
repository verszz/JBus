package zikriZulfaAzhimJBusRS;

import java.util.Arrays;
import java.util.Calendar;
import java.sql.Timestamp;
import java.util.List;

public class JBus {
    public static int getBusId() {
        return 0;
    }

    public static String getBusName() {
        return "Bus";
    }

    public static boolean isDiscount() {
        return true;
    }

    public static float getDiscountPercentage(int beforeDiscount, int afterDiscount) {
        float diskon;
        if (beforeDiscount < afterDiscount) {
            return 0.0f;
        } else if (beforeDiscount == afterDiscount) {
            return 0.0f;
        } else {
            diskon = ((float) beforeDiscount - (float) afterDiscount) / ((float) beforeDiscount / 100);
            return diskon;
        }
    }

    public static int getDiscountedPrice(int price, float discountPercentage) {
        float hargaSetelahDiskonTemp;
        if (discountPercentage >= 100) {
            return 0;
        } else {
            hargaSetelahDiskonTemp = price - (discountPercentage * price / 100);
            int hargaSetelahDiskonFinal = (int) hargaSetelahDiskonTemp;
            return hargaSetelahDiskonFinal;
        }
    }

    public static int getOriginalPrice(int discountedPrice, float discountPercentage) {
        float hargaAwalTemp;
        hargaAwalTemp = discountedPrice * 100 / (100 - (discountPercentage));
        int hargaAwalFinal = (int) hargaAwalTemp;
        return hargaAwalFinal;
    }

    public static float getAdminFeePercentage() {
        return 0.05f;
    }

    public static int getAdminFee(int price) {
        float biayaAdmin;
        biayaAdmin = getAdminFeePercentage() * (float) price;
        int biayaAdmin2 = (int) biayaAdmin;
        return biayaAdmin2;
    }

    public static int getTotalPrice(int price, int numberOfSeat) {
        int hargaTotal;
        hargaTotal = getAdminFee(price * numberOfSeat) + (price * numberOfSeat);
        return hargaTotal;

    }

    //public static Bus createBus(){
    //    Price price = new Price(750000, 5);
    //    Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25);
    //    return bus;
    //}
    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }

    public static void main(String[] args) {
        //Bus testBus = createBus();
        //System.out.println(testBus.id);
        //System.out.println(testBus.name);
        //System.out.println(testBus.facility);
        //System.out.println(testBus.price.price);
        //System.out.println(testBus.capacity);

        //Payment testPayment = new Payment(1, 1, 1, "A", 1, "A", "A");
        //Invoice testInvoice = new Invoice(2,2,2, "B");
        //Station testStation = new Station(3, "C", City.DEPOK);
        //System.out.println(testPayment.print());
        //System.out.println(testInvoice.print());
        //System.out.println(testStation.print());

        //Review testReview = new Review(1, "23 August 2023", "Bad Quality");
        //Price testPrice = new Price (100000, 20000);
        //Station testDeparture = new Station (2, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya");
        //Station testArrival = new Station (3, "Halte UI", City.JAKARTA, "Universitas Indonesia");
        //Bus testBus = new Bus (1, "Busway", Facility.AC, testPrice, 50, BusType.REGULER, City.DEPOK, testDeparture, testArrival); Account testAccount = new Account(1, "Bob", "bob@gmail.com", "bob");
        //Rating testRating = new Rating();
        //System.out.println(testReview+"\n");
        //System.out.println(testBus+"\n");
        //System.out.println(testAccount+"\n");
        //System.out.println(testPrice+"\n");
        //System.out.println(testRating+"\n");
    
    /*Price[] unfilteredArray = new Price[5];
    for(int i = 0; i < unfilteredArray.length; i++){
        int j = 5000;
        unfilteredArray[i] = new Price((i+1)*j);
    }*/

        //System.out.println("Price List");
        //for(Price price : unfilteredArray){
        //    System.out.println(price.price);
        //}
        //System.out.println("Below 12000.0");
        //System.out.println(Validate.filter(unfilteredArray, 12000, true));
        //System.out.println("Above 10000.0");
        //System.out.println(Validate.filter(unfilteredArray, 10000, false));
    
    /*Bus testBus = createBus();
// Payment
    Payment testPayment = new Payment(1, 1, 1, testBus.id, "S1");
    System.out.println(testPayment.getDepartureInfo());
    System.out.println(testPayment.getTime());
// Tes Schedule
    Calendar schedule1 = Calendar.getInstance();
    testBus.addSchedule(schedule1);
    Calendar schedule2 = Calendar.getInstance();
    schedule2.add(Calendar.DAY_OF_MONTH, 3);
    testBus.addSchedule(schedule2);
        for(Schedule s: testBus.schedules){
        testBus.printSchedule(s);
        }
  }*/
//    Bus b = createBus();
//    Timestamp schedule1 = Timestamp.valueOf("2023-7-18 15:00:00");
//    Timestamp schedule2 = Timestamp.valueOf("2023-7-20 12:00:00");
//    b.addSchedule(schedule1, 12);
//    b.addSchedule(schedule2, 12);
//    b.schedules.forEach(Schedule::printSchedule);
//    // Invalid date
//    Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
//    System.out.println("Make booking at July 19, 2023 15:00:00 Seat RS12");
//    System.out.println(Payment.makeBooking(t1, "RS12", b));
//    // Valid date, invalid seat
//    Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
//    System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat RS20");
//    System.out.println(Payment.makeBooking(t2, "RS20", b));
//    // Valid date, valid seat
//    System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat RS07");
//    System.out.println(Payment.makeBooking(t2, "RS07", b));
//    Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
//    System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat RS01");
//
//    System.out.println(Payment.makeBooking(t3, "RS01", b));
//    System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat RS01 again");
//    System.out.println(Payment.makeBooking(t3, "RS01", b));
//        // Check if the data changed
//    System.out.println("\nUpdated Schedule\n");
//    b.schedules.forEach(Schedule::printSchedule);
//        Integer[] numbers = {10, 20, 30, 40, 30};
//        int valueToCheck = 30;
//        boolean isExists = Algorithm.exists(numbers, valueToCheck);
//        if (isExists) {
//            System.out.println(valueToCheck + " terdapat dalam array");
//        } else {
//            System.out.println(valueToCheck + " tidak ada dalam array");
//        }
//        int valueToCheck2 = 30;
//        int Count = Algorithm.count(numbers, valueToCheck2);
//            System.out.println(Count);
//        int valueToCheck3 = 30;
//        int Collect = Algorithm.collect(numbers, numbers <= 30);
//        System.out.println(Collect);
        Integer[] numbers = {18, 10, 22, 43, 18, 67, 12, 11, 88, 22, 18};
        System.out.println("Number "+ Arrays.toString(numbers));

        // Tes Algorithm
        System.out.print("1. ");
        testCount(numbers);
        System.out.print("2. ");
        testFind(numbers);
        System.out.print("3. ");
        testExist(numbers);
        System.out.println("4. Filtering");
        testCollect(numbers);
    }
    private static void testExist(Integer[] t) {
        int valueToCheck = 67;
        boolean result3 = Algorithm.exists(t, valueToCheck);
        if (result3) {
            System.out.println(valueToCheck + " exist in the array.");
        } else {
            System.out.println(valueToCheck + " doesn't exists in the array.");
        }
    }
    public static void testCount(Integer[] t) {
        int valueToCount = 18;
        int result = Algorithm.count(t, valueToCount);
        System.out.println("Number " + valueToCount + " appears " + result + " times");
    }
    public static void testFind(Integer[] t) {
        Integer valueToFind = 18;
        Integer result2 = Algorithm.find(t, valueToFind);
        System.out.print("Finding " + valueToFind + " inside the array : ");
        if (result2 != null) {
            System.out.println("Found! " + result2);
        } else {
            System.out.println("Not Found");
        }
    }
    private static void testCollect(Integer[] t) {
        Predicate<Integer> below = (val)->val<=22;
        Predicate<Integer> above = (val)->val>43;

        List<Integer> integerBelow = Algorithm.collect(t, below);
        List<Integer> integerAbove = Algorithm.collect(t, above);

        System.out.println("Below 22");
        System.out.println(integerBelow);
        System.out.println("Above 43");
        System.out.println(integerAbove);
    }
}

