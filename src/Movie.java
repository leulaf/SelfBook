import java.util.HashMap;
import java.util.*;

public class Movie {
    String name;
    double price;
    HashMap<String, String> timeLocation = new HashMap<String, String>();
    
    public Movie (String name, int tickets, double price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public void printName(){
        System.out.println(this.name);
    }


    public void printTimes(){
        System.out.println("Show times for " + this.name + ":");
        
        for (Map.Entry<String, String> entry : timeLocation.entrySet()) {
            System.out.println("- " + entry.getKey());
        }
    }

    public void printLocations(){
        for (Map.Entry<String, String> entry : timeLocation.entrySet()) {
            System.out.println("- " + entry.getValue());
        }
    }
    
    public String[] printTimeAndLocation(){
        String[] allTimes = new String[timeLocation.size()];
        int track = 0;
    	System.out.println("Show times for " + this.name + ":");
        
        for (Map.Entry<String, String> entry : timeLocation.entrySet()) {
            System.out.println((track+1) + " - At " + entry.getKey() + " in " + entry.getValue()) ;
            allTimes[track] = entry.getKey();
            track++;
        }
        
        return allTimes;
    }
   
    
	public void printPrice(){
	        System.out.println("$" + this.price);
	}
	
    public double getPrice(){
        return this.price;
    }

    public static void main(String[] args){

        
    }

}
