import java.util.HashMap;
import java.util.*;

public class Movie {
    String name;
    double price;
    int tickets;
    HashMap<Time,Theatre> timeLocation = new HashMap<Time,Theatre>();
    
    public Movie (String name, double price){
        this.name = name;
        this.price = price;
        this.tickets = Theatre.TOTAL_NUM_SEATS;
    }

    public String getName(){
        return this.name;
    }

    public void printName(){
        System.out.println(this.name);
    }

    public void printTimes(){
        System.out.println("Show times for " + this.name + ":");
        
        for (Map.Entry<Time,Theatre> entry : timeLocation.entrySet()) {
            System.out.println("- " + entry.getKey());
        }
    }

    public void printLocations(){
        for (Map.Entry<Time,Theatre> entry : timeLocation.entrySet()) {
            System.out.println("- " + entry.getValue());
        }
    }
    
    public String[] printTimeAndLocation(){
        String[] allTimes = new String[timeLocation.size()];
        int track = 0;
    	System.out.println("Show times for " + this.name + ":");
        for (Map.Entry<Time,Theatre> entry : timeLocation.entrySet()) {
            System.out.println((track+1) + " - At " + entry.getKey() + " in " + entry.getValue()) ;
            // allTimes[track] = entry.getKey();
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

    public ArrayList<Time> getTimesInTheatre(Theatre theatre) {
        // returns all the show times in a given theatre 
        ArrayList<Time> allTimes = new ArrayList<Time>();
        for (Map.Entry<Time,Theatre> entry : timeLocation.entrySet()) {
            if (entry.getValue().equals(theatre)) 
                allTimes.add(entry.getKey());
        }
        return allTimes;
    }

    public String toString() {
        return this.getName();
    }

}
