import java.util.*;

public class Admin extends User {
	static Movie Ex1;
	static Movie Ex2;
	static Movie Ex3;
	public Admin(String firstName, String lastName, String email) {
		super(firstName, lastName, email);
		super.isAdmin = true;
	}

	static int numberOfTicketsSold;
	static double totalRevenue;
	
	static int adminCode = 12345;
	
    static ArrayList<Movie> movies = new ArrayList<Movie>();
    
    public static void setAdminCode(int code) {
		adminCode = code;
	}
    
	public static void incrementNumTickets(int amount) {
		numberOfTicketsSold += amount;
	}

	public static void incrementTotalRevenue(double amount) {
		totalRevenue += amount;
	}

	public static int getNumTicketsSold() {
		return numberOfTicketsSold;
	}

	public static double getTotalRevenue() {
		return totalRevenue;
	}

	public static Movie addMovie(String name, double price) {
		Movie movie = new Movie(name, price);
		movies.add(movie);
		return movie;
	}
	
	public static void removeMovie(Movie movie) {
		movies.remove(movie);
	}

	public static void removeShowtimeForMovie(Movie movieSelected, Time timeSelected, Theatre theatreSelected) {
		// removes a specific movie showtime from the theatre 
		
		// iterate through all the showtimes for this movie 
		for (Map.Entry<Time, Theatre> entry : movieSelected.timeLocation.entrySet()) {
			Time currTime = entry.getKey();
			Theatre currTheatre = entry.getValue();
			// if the showtime and theatre chosen are the same, remove it
			if (timeSelected.equals(currTime) && currTheatre.equals(theatreSelected)) {
				movieSelected.timeLocation.remove(currTime);
				break;
			}
		}
	}
	
	public static boolean addTimeLocation(Movie movie, String time, Theatre location) {
		// adds a movie to a theatre at the specific time
		// returns true if successfully added. false if there is a time conflict or an invalid time was given  

		// let's check that the time is valid 
		Time timeGiven = new Time();
		boolean timeValid = timeGiven.parseTime(time);
		if (!timeValid) {
			System.out.println("The time range entered was not valid. Please try again!");
			return false;
		}
		
		// now we need to go through the movies in that location to make sure there is no overlap
		for (int i = 0; i < movies.size(); i++) {
			Movie currMovie = movies.get(i);
			for (Map.Entry<Time, Theatre> entry : currMovie.timeLocation.entrySet()) {
				Time currTime = entry.getKey();
				Theatre currTheatre = entry.getValue();
				if (currTheatre.equals(location)) {
					// now we need to check for time overlap
					boolean hasOverlap = timeGiven.checkOverlap(currTime);
					if (hasOverlap) {
						System.out.println("Time conflict in " + currTheatre + "Please choose a different time!");
						return false;
					} 
				} 
			}
		}
		// if we reach here, we have succeeded
		System.out.println("Successfully added " + movie + " in Theatre " + location.getNumber() + " in time slot " + time);
		location.addShowTime(timeGiven, movie.getPrice());
		movie.timeLocation.put(timeGiven, location);
		return true;
	}
	
	public static void updatePrice(Movie movie, Double newPrice){
		movie.price = Math.round(newPrice*100.0)/100.0;;
    }
    
    public void discountPrice(Movie movie, int percentOff) {
    	movie.price *= 1 - (percentOff/100.);
    	
    	movie.price = Math.round(movie.price*100.0)/100.0;
    }
    
	static boolean ranOnce = false;

	public static void main() {
		// let's add some movies to kickstart the program
		addMovie("Dune",15);
		addMovie("Martian",15);
		addMovie("Avengers",15);
		
		addTimeLocation(movies.get(0),"11:00AM-12:00PM",Main.ALL_THEATRES[0]);
		addTimeLocation(movies.get(0),"12:00PM-02:00PM",Main.ALL_THEATRES[1]);
		
		addTimeLocation(movies.get(1),"01:00AM-02:00PM",Main.ALL_THEATRES[2]);
		addTimeLocation(movies.get(1),"02:00PM-04:00PM",Main.ALL_THEATRES[3]);
		
		addTimeLocation(movies.get(2),"12:00AM-02:00aM",Main.ALL_THEATRES[4]);
		addTimeLocation(movies.get(2),"02:00PM-04:00PM",Main.ALL_THEATRES[5]);
	}
}
