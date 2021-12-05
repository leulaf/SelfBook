import java.util.HashMap;
import java.util.Map.Entry;
import java.util.*;

public class Movie {
    //a Movie object has a name, price and tickets (num tickets)
    String name;
    double price;
    int tickets;
    //mapping the time and location of the movies in theater now
    HashMap<Time,Theatre> timeLocation = new HashMap<Time,Theatre>();
    
    public Movie (String name, double price){
        this.name = name;
        this.price = price;
        this.tickets = Theatre.TOTAL_NUM_SEATS;
    }
    //check if the give movie's name is the same as this instance of movie
    public boolean checkName(Movie otherMovie) {
        return (this.name.replaceAll(" ", "").toLowerCase().equals(otherMovie.getName().replaceAll(" ", "").toLowerCase()));
    }
    //accessor returning the name of the movie
    public String getName(){
        return this.name;
    }
    //prints the name of the movie
    public void printName(){
        System.out.println(this.name);
    }
    //prints showtime for the movies in the hash map (movies in theater now)
    public void printTimes(){
        System.out.println("Show times for " + this.name + ":");
        
        for (Map.Entry<Time,Theatre> entry : timeLocation.entrySet()) {
            System.out.println("- " + entry.getKey());
        }
    }
    //prints locations of the movies in the hash map (movies in theater now)
    public void printLocations(){
        for (Map.Entry<Time,Theatre> entry : timeLocation.entrySet()) {
            System.out.println("- " + entry.getValue());
        }
    }
    //prints time and locations of the movies in the hash map (movies in theater now)
    public String[] printTimeAndLocation(){
        String[] allTimes = new String[timeLocation.size()];
        int track = 0;
    	System.out.println("Show times for " + this.name + ":");
        for (Map.Entry<Time,Theatre> entry : timeLocation.entrySet()) {
            System.out.println((track+1) + " - At " + entry.getKey() + " in " + entry.getValue()) ;
            track++;
        }
        return allTimes;
    }
    // find the showtime and theatre corresponding to the users choice
    public Map.Entry<Time,Theatre> getShowTimeFromIndex(int i) {
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

    //prints the price of this instance of movie
	public void printPrice(){
	    System.out.println("$" + this.price);
	}
	//accessor method that returns the price of this movie
    public double getPrice(){
        return this.price;
    }
    // returns all the show times in a given theatre 
    public ArrayList<Time> getTimesInTheatre(Theatre theatre) {
        ArrayList<Time> allTimes = new ArrayList<Time>();
        for (Map.Entry<Time,Theatre> entry : timeLocation.entrySet()) {
            if (entry.getValue().equals(theatre)) 
                allTimes.add(entry.getKey());
        }
        return allTimes;
    }
    //we only need the name of the movie when printing the movie
    public String toString() {
        return this.getName();
    }

}
