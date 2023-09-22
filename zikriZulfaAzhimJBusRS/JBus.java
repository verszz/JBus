package zikriZulfaAzhimJBusRS;

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
  
  public static Bus createBus(){
      Price price = new Price(750000, 5);
      Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25);
      return bus;
  }
  
    public static void main(String[] args) {
    Bus testBus = createBus();
    System.out.println(testBus.id);
    System.out.println(testBus.name);
    System.out.println(testBus.facility);
    System.out.println(testBus.price.price);
    System.out.println(testBus.capacity);
  }
    
}

