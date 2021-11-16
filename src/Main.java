import java.util.*;

public class Admin extends User {

	public Admin(String firstName, String lastName, String email) {
		super(firstName, lastName, email);
		super.isAdmin = true;
	}
	
	static int adminCode = 12345;
	
    static ArrayList<Movie> movies = new ArrayList<Movie>();
    static HashMap<String, Integer> ticketsLeft = new HashMap<String, Integer>();

    static int ticketsSold = 0;
    static double moneyCollected = 0.0;
    
    public void setAdminCode(int code) {
		adminCode = code;
	}
    
	public static void addMovie(String name, int numTickets, double price) {
		movies.add(new Movie(name, numTickets, price));
	}
	
	public static void removeMovie(Movie movie) {
		movies.remove(movie);
	}
	
	public static void addTimeLocation(Movie movie, String time, String location) {
		movie.timeLocation.put(time, location);
		ticketsLeft.put(movie.getName()+time+location, 99);
	}
	
	public void updatePrice(Movie movie, int newPrice){
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
