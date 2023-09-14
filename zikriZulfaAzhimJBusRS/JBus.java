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
      int hargaSetelahDiskon;
      if (discountPercentage >= 100){
          return 0;
      }
      else {
          hargaSetelahDiskon = price - ((int) discountPercentage * price / 100);
          return hargaSetelahDiskon;
      }
  }
  
  public static int getOriginalPrice(int discountedPrice, float discountPercentage){
      float hargaAwal1;
      hargaAwal1 = discountedPrice * 100 / (100 - (discountPercentage));
      int hargaAwal2 = (int) hargaAwal1;
      return hargaAwal2;
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
  
    public static void main(String[] args) {
    
  }
    
}

