import java.util.*;

public class Admin extends User {

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
    
	public static Movie addMovie(String name, int numTickets, double price) {
		Movie movie = new Movie(name, numTickets, price);
		movies.add(movie);
		return movie;
	}
	
	public static void removeMovie(Movie movie) {
		movies.remove(movie);
	}
	
	public static void addTimeLocation(Movie movie, String time, String location) {
		movie.timeLocation.put(time, location);
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
    
	public static void main() {
		// TODO Auto-generated method stub
		addMovie("Dune",100,15);
		addMovie("Martian",100,15);
		addMovie("Avengers",1000,15);
		
		addTimeLocation(movies.get(0),"11AM-12PM","AMC43");
		addTimeLocation(movies.get(0),"12PM-2PM","AMC44");
		
		addTimeLocation(movies.get(1),"1AM-2PM","AMC46");
		addTimeLocation(movies.get(1),"2PM-4PM","AMC42");
		
		addTimeLocation(movies.get(2),"12AM-2aM","AMC48");
		addTimeLocation(movies.get(2),"2PM-4PM","AMC43");
	}
}
