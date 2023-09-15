package zikriZulfaAzhimJBusRS;


public class Price{
	public double rebate;
	public double price; 
	public int discount;
	
	public Price(double price, double rebate){
		this.price = price;
		this.rebate = rebate;
		this.discount = 0;
	}
	
	public Price(double price, int discount){
		this.price = price;
		this.rebate = 0;
		this.discount = discount;
	}

	public Price(double price){
		this.price = price;
		this.rebate = 0;
		this.discount = 0;
	}
	
	private double getDiscountedPrice(){
            	
                if(this.discount >= 100.0){
            	    return 0;
                }
                else{
                    this.price = this.price - (this.discount * this.price / 100);
                    return this.price;
                }
            }
            
        private double getRebatedPrice(){

                     if(this.rebate > this.price){
                         return 0;
                     }
                     else
                         return (this.price - this.rebate);
                     
                }
        }

