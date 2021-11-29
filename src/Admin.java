import java.util.*;

public class Admin extends User {
	static Movie Ex1;
	static Movie Ex2;
	static Movie Ex3;
	public Admin(String firstName, String lastName, String email) {
		super(firstName, lastName, email);
		super.isAdmin = true;
	}
	
	static int adminCode = 12345;
	
    static ArrayList<Movie> movies = new ArrayList<Movie>();

    static int ticketsSold = 0;
    static double moneyCollected = 0.0;
    
    public static void setAdminCode(int code) {
		adminCode = code;
	}
    
	public static Movie addMovie(String name, double price) {
		Movie movie = new Movie(name, price);
		movies.add(movie);
		return movie;
	}
	
	public static void removeMovie(Movie movie) {
		movies.remove(movie);
	}
	
	public static void addTimeLocation(Movie movie, String time, Theatre location) {
		// let's check that the time is valid 
		Time timeGiven = new Time();
		timeGiven.parseTime(time);
		for (Map.Entry<Time, Theatre> entry : movie.timeLocation.entrySet()) {
			Time currTime = entry.getKey();
			Theatre currTheatre = entry.getValue();
			if (currTheatre.equals(location)) {
				// now we need to check for time overlap 
				boolean hasOverlap = timeGiven.checkOverlap(currTime);
			} 
		}
		movie.timeLocation.put(timeGiven, location);
	}
	
	public static void updatePrice(Movie movie, Double newPrice){
		movie.price = Math.round(newPrice*100.0)/100.0;;
    }
    
    public void discountPrice(Movie movie, int percentOff) {
    	movie.price *= 1 - (percentOff/100.);
    	
    	movie.price = Math.round(movie.price*100.0)/100.0;
    }
    

    public static void printTotals() {
    	System.out.println("Tickets sold: " + ticketsSold);
    	System.out.println("Money collected: " + moneyCollected);
    }
	static boolean ranOnce = false;

	public static void main() {
		// TODO Auto-generated method stub
		addMovie("Dune",15);
		addMovie("Martian",15);
		addMovie("Avengers",15);
		
		addTimeLocation(movies.get(0),"11AM-12PM",Main.ALL_THEATRES[0]);
		addTimeLocation(movies.get(0),"12PM-2PM",Main.ALL_THEATRES[1]);
		
		addTimeLocation(movies.get(1),"1AM-2PM",Main.ALL_THEATRES[2]);
		addTimeLocation(movies.get(1),"2PM-4PM",Main.ALL_THEATRES[3]);
		
		addTimeLocation(movies.get(2),"12AM-2aM",Main.ALL_THEATRES[4]);
		addTimeLocation(movies.get(2),"2PM-4PM",Main.ALL_THEATRES[5]);
	}
}
