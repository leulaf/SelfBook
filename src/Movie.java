import java.util.HashMap;
import java.util.Map.Entry;
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
   
    public Map.Entry<Time,Theatre> getShowTimeFromIndex(int i) {
        // find the showtime and theatre corresponding to the users choice
        // int i is the users choice, iterate until we find it
        int counter = 0;
        for (Map.Entry<Time,Theatre> entry : timeLocation.entrySet()) {
            if (counter == i) {
                return entry;
            }
            counter = counter + 1;
        }
        // if we get here, there's no movie so lets give them a null object 
        Entry<Time, Theatre> entry = Map.entry(null, null);
        return entry;
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
