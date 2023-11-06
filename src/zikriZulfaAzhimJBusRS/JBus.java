package zikriZulfaAzhimJBusRS;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.sql.Timestamp;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

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
//    public static Bus createBus() {
//        Price price = new Price(750000, 5);
//        Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
//        return bus;
//    }

    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25,
                BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK,
                "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
                Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");
        bus.addSchedule(timestamp);
        return bus;
    }


    public static List<Bus> filterByDeparture( List<Bus> buses, City departure, int page, int pageSize){
        Predicate<Bus> filterDep = (bus) -> bus.city == departure;
        return Algorithm.paginate(buses, page, pageSize, filterDep);
    }

    public static List<Bus> filterByPrice( List<Bus> buses, int min, int max){
        Predicate<Bus> range = (bus) -> bus.price.price >= min && bus.price.price <= max;
        return Algorithm.collect(buses, range);
    }

    public static Bus filterBusId(List<Bus> buses, int id) {
        Predicate<Bus> findId = (bus) -> bus.id == id;
        return Algorithm.find(buses, findId);
    }

    public static List<Bus> filterByDepartureAndArrival( List<Bus> buses, City departure, City arrival, int page, int pageSize){
        Predicate<Bus> filterDepArr = (bus) -> bus.departure.city == departure && bus.arrival.city == arrival;
        return Algorithm.paginate(buses, page, pageSize, filterDepArr);
    }

    public static void main(String[] args) throws InterruptedException {
        Bus bus = createBus();
        bus.schedules.forEach(Schedule::printSchedule);
        for(int i =0; i < 10; i++){
            BookingThread thread = new BookingThread("Thread " + i,bus, Timestamp.valueOf("2023-07-27 19:00:00"));
        }
        Thread.sleep(1000);
        bus.schedules.forEach(Schedule::printSchedule);
//        try {
//
//            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, "C:\\Users\\ASUS\\Documents\\Tugas Zikri\\Semester 3\\OOP\\Praktikum\\JBus\\data\\accountDatabase.json");
//
//
//            Account account1 = new Account(0,"zikri","zikri@gmail.com", "Password123");
//            Account account2 = new Account(1,"zulfa","zulfa@example.com", "Password456");
//
//            tableAccount.add(account1);
//            tableAccount.add(account2);
//
//
//            tableAccount.writeJson();
//
//            System.out.println("Data telah berhasil disimpan ke accountDatabase.json");
//        System.out.println(tableAccount);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
    }
        // TP Modul 6
//        String filepath = "C:\\Users\\ASUS\\Documents\\Tugas Zikri\\Semester 3\\OOP\\Praktikum\\JBus\\data\\station.json";
//        Gson gson = new Gson();
//        try {
//            BufferedReader buffer = new BufferedReader(new FileReader (filepath));
//            List<Station> stationjson = gson.fromJson(buffer, new TypeToken<List<Station>>() {}.getType());
//            stationjson.forEach(e -> System.out.println(e.toString()));
//            System.out.println();
//            buffer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            String filepath =
//                    "C:\\Users\\ASUS\\Documents\\Tugas Zikri\\Semester 3\\OOP\\Praktikum\\JBus\\data\\buses.json";
//            JsonTable<Bus> busList = new JsonTable<>(Bus.class,filepath);
//            List<Bus> filteredBus =
//                    filterByDeparture(busList,City.JAKARTA,1,10);
//            filteredBus.forEach(bus -> System.out.println("\n"+bus.toString()));
//        }
//        catch (Throwable t) {
//            t.printStackTrace();
//        }
//        try {
//            String filepath =
//                    "C:\\Users\\ASUS\\Documents\\Tugas Zikri\\Semester 3\\OOP\\Praktikum\\JBus\\data\\buses_CS.json";
//
////            JsonTable<Bus> busList = new JsonTable<>(Bus.class,filepath);
////            List<Bus> filteredBus = filterByDeparture(busList,City.JAKARTA,0,3); //
////            filteredBus.forEach(bus -> System.out.println("\n"+bus.toString()));
//
////            JsonTable<Bus> busList = new JsonTable<>(Bus.class,filepath);
////            List<Bus> filteredBus = filterByDepartureAndArrival(busList,City.JAKARTA,City.SURABAYA,0,3);
////            filteredBus.forEach(bus -> System.out.println("\n"+bus.toString()));
//
////            JsonTable<Bus> busList = new JsonTable<>(Bus.class,filepath);
////            List<Bus> filteredBus = filterByPrice(busList,100000, 500000);
////            filteredBus.forEach(bus -> System.out.println("\n"+bus.toString()));
//
//            JsonTable<Bus> busList = new JsonTable<>(Bus.class, filepath);
//            List<Bus> filteredBus = new ArrayList<>();
//            filteredBus.add(filterBusId(busList, 155));
//            filteredBus.forEach(bus -> System.out.println(bus.toString()));
//        }
//        catch (Throwable t) {
//            t.printStackTrace();
//        }
//    }
//        Bus b = createBus();
//        List<Timestamp> listOfSchedules = new ArrayList<>();
//        listOfSchedules.add(Timestamp.valueOf("2023-7-18 15:00:00"));
//        listOfSchedules.add(Timestamp.valueOf("2023-7-20 12:00:00"));
//        listOfSchedules.add(Timestamp.valueOf("2023-7-22 10:00:00"));
//        listOfSchedules.add(Timestamp.valueOf("2023-7-26 12:00:00"));
//
//        listOfSchedules.forEach(b::addSchedule);
//        System.out.println("Page 1");
//        Algorithm.paginate(b.schedules, 0, 3, t -> true).forEach(System.out::println);
//        System.out.println("=====================================================");
//        System.out.println("Page 2");
//        Algorithm.paginate(b.schedules, 1, 3, t -> true).forEach(System.out::println);
//        System.out.println("=====================================================");
//
//        // Tes Booking
//        String msgSuccess = "Booking Success!";
//        String msgFailed = "Booking Failed";
//        // valid date, invalid seat = Booking Failed
//        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
//        System.out.println("\nMake booking at July 19, 2023 15:00:00 Seats: RS17 RS18");
//        System.out.println(Payment.makeBooking(t1, List.of("RS17", "RS18"), b)? msgSuccess : msgFailed);
//        // valid date, invalid seat = Booking Failed
//        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
//        System.out.println("Make booking at July 18, 2023 15:00:00 Seat RS26");
//        System.out.println(Payment.makeBooking(t2, "RS26", b)? msgSuccess : msgFailed);
//        // valid date, valid seat = Booking Success
//        System.out.println("Make booking at July 18, 2023 15:00:00 Seats: RS7 RS8");
//        System.out.println(Payment.makeBooking(t2, List.of("RS7", "RS8"), b)? msgSuccess : msgFailed);
//        // valid date, valid seat = Booking Success
//        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
//        System.out.println("Make booking at July 20, 2023 12:00:00 Seats: RS1 RS2");
//        System.out.println(Payment.makeBooking(t3, List.of("RS1", "RS2"), b)? msgSuccess : msgFailed);
//        // valid date, book the same seat = Booking Failed
//        System.out.println("Make booking at July 20, 2023 12:00:00 Seat RS1");
//        System.out.println(Payment.makeBooking(t3, "RS1", b)? msgSuccess : msgFailed);
//        // check if the data changed
//        System.out.println("\nUpdated Schedule");
//        Algorithm.paginate(b.schedules, 0, 4, t-> true).forEach(System.out::println);
//    }
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
//        Integer[] numbers = {18, 10, 22, 43, 18, 67, 12, 11, 88, 22, 18};
//        System.out.println("Number "+ Arrays.toString(numbers));
//
//        // Tes Algorithm
//        System.out.print("1. ");
//        testCount(numbers);
//        System.out.print("2. ");
//        testFind(numbers);
//        System.out.print("3. ");
//        testExist(numbers);
//        System.out.println("4. Filtering");
//        testCollect(numbers);
//    }
//    private static void testExist(Integer[] t) {
//        int valueToCheck = 67;
//        boolean result3 = Algorithm.exists(t, valueToCheck);
//        if (result3) {
//            System.out.println(valueToCheck + " exist in the array.");
//        } else {
//            System.out.println(valueToCheck + " doesn't exists in the array.");
//        }
//    }
//    public static void testCount(Integer[] t) {
//        int valueToCount = 18;
//        int result = Algorithm.count(t, valueToCount);
//        System.out.println("Number " + valueToCount + " appears " + result + " times");
//    }
//    public static void testFind(Integer[] t) {
//        Integer valueToFind = 18;
//        Integer result2 = Algorithm.find(t, valueToFind);
//        System.out.print("Finding " + valueToFind + " inside the array : ");
//        if (result2 != null) {
//            System.out.println("Found! " + result2);
//        } else {
//            System.out.println("Not Found");
//        }
//    }
//    private static void testCollect(Integer[] t) {
//        Predicate<Integer> below = (val)->val<=22;
//        Predicate<Integer> above = (val)->val>43;
//
//        List<Integer> integerBelow = Algorithm.collect(t, below);
//        List<Integer> integerAbove = Algorithm.collect(t, above);
//
//        System.out.println("Below 22");
//        System.out.println(integerBelow);
//        System.out.println("Above 43");
//        System.out.println(integerAbove);
//    }
//}

