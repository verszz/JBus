package zikriZulfaAzhimJBusRS;
import java.util.Calendar;

public class JBus{
  public static int getBusId() {
    return 0;
  }  
  
  public static String getBusName(){
      return "Bus";
  }
  
  public static boolean isDiscount(){
     return true;
    }
    
  public static float getDiscountPercentage(int beforeDiscount, int afterDiscount){
      float diskon;
      if (beforeDiscount < afterDiscount){
          return 0.0f;
      }
      else if(beforeDiscount == afterDiscount){
          return 0.0f;
      }
      else {
          diskon = ((float) beforeDiscount - (float) afterDiscount) / ((float) beforeDiscount / 100);
          return diskon;
      }
  }
  
  public static int getDiscountedPrice(int price,float discountPercentage){
      float hargaSetelahDiskonTemp;
      if (discountPercentage >= 100){
          return 0;
      }
      else {
          hargaSetelahDiskonTemp = price - (discountPercentage * price / 100);
          int hargaSetelahDiskonFinal = (int) hargaSetelahDiskonTemp;
          return hargaSetelahDiskonFinal;
      }
  }
  
  public static int getOriginalPrice(int discountedPrice, float discountPercentage){
      float hargaAwalTemp;
      hargaAwalTemp = discountedPrice * 100 / (100 - (discountPercentage));
      int hargaAwalFinal = (int) hargaAwalTemp;
      return hargaAwalFinal;
  }
  
  public static float getAdminFeePercentage(){
      return 0.05f;  
  }
  
  public static int getAdminFee(int price){
      float biayaAdmin;
      biayaAdmin = getAdminFeePercentage() * (float) price;
      int biayaAdmin2 = (int) biayaAdmin;
      return biayaAdmin2;
  }
  
  public static int getTotalPrice(int price,int numberOfSeat){
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
    
    Price[] unfilteredArray = new Price[5];
    for(int i = 0; i < unfilteredArray.length; i++){
        int j = 5000;
        unfilteredArray[i] = new Price((i+1)*j);
    }
    
    System.out.println("Price List");
    for(Price price : unfilteredArray){
        System.out.println(price.price);    
    }
    System.out.println("Below 12000.0");
    System.out.println(Validate.filter(unfilteredArray, 12000, true));
    System.out.println("Above 10000.0");
    System.out.println(Validate.filter(unfilteredArray, 10000, false));
    
    Bus testBus = createBus();
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
//for(Schedule s: testBus.schedules){
//testBus.printSchedule(s);
//}
  }
    
}

