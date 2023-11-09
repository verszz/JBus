package com.zikriZulfaAzhimJBusRS;


 public class Rating{
    private long count;
    private long total;
 
    public Rating(){
            this.count = 0;
            this.total = 0;
    }
        
    public void insert(int rating){
            this.total += rating;
            this.count++;
        }
        
    public double getAverage(){
            long rataRataTemp;
            if(this.count == 0){
                return 0;
            }
            else{
                rataRataTemp = this.total / this.count;
                double rataRataFinal = (double) rataRataTemp;
                return rataRataFinal;
            }
    }
        
        public long getCount(){
            return this.count;
        }
        
        public long getTotal(){
            return this.total;
        }
    
    public String toString(){
        return "Count: "+this.count+"\nTotal: "+this.total;
    }
}
