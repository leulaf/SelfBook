import java.util.*;

public class Admin {
	
	public Admin() {
		// admin is a bunch of static attributes and methods, so we don't really need to initialize 
	}
	//counts the number of tickets sold
	static int numberOfTicketsSold;
	//counts the total revenue
	static double totalRevenue;
	//default admin passcode
	static int adminCode = 12345;
	//hold all the movies played in the theater now
	static ArrayList<Movie> movies = new ArrayList<Movie>();
    
	//admin can change the password when this method is called
	public static void setAdminCode(int code) {
		adminCode = code;
	}
	//counts the number of ti
	public static void incrementNumTickets(int amount) {
		numberOfTicketsSold += amount;
	}

	public static void incrementTotalRevenue(double amount) {
		totalRevenue += amount;
	}
	//accessor method returning the number of tickets sold
	public static int getNumTicketsSold() {
		return numberOfTicketsSold;
	}
	//accessor method returning the total revenue
	public static double getTotalRevenue() {
		return totalRevenue;
	}
	//creates and adds a new movie to the theater and returns the movie added.
	public static Movie addMovie(String name, double price) {
		Movie movie = new Movie(name, price);
		// let's check if the movie already exists 
		for (int i = 0; i < movies.size(); i++) {
			Movie currMovie = movies.get(i);
			if (movie.checkName(currMovie)) {
				//prices must be the same for same movies, if not let the admin know
				if (movie.getPrice() != currMovie.getPrice())
					System.out.println("This movie already exists, and is priced at " + currMovie.getPrice() + ". We have kept the original price. If you want to change this, please use option 3 in the admin menu.");
				return currMovie;
			}
		}
		//adds the created movie to the theater by putting it in the movies arraylist. 
		movies.add(movie);
		return movie;
	}
	//removes the given movie from the theater
	public static void removeMovie(Movie movie) {
		movies.remove(movie);
	}
	// removes a specific movie showtime from the theatre 
	public static void removeShowtimeForMovie(Movie movieSelected, Time timeSelected, Theatre theatreSelected) {
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
		// now let's check if that was the only show time 
		if (movieSelected.timeLocation.size() == 0)
			movies.remove(movieSelected);
	}

	// adds a movie to a theatre at the specific time
	// returns true if successfully added. false if there is a time conflict or an invalid time was given  
	public static boolean addTimeLocation(Movie movie, String time, Theatre location) {
		// let's check that the time is valid 
		Time timeGiven = new Time();
		boolean timeValid = timeGiven.parseTime(time);
		if (!timeValid) {
			// fail so return false
			return false;
		}

		// now we need to go through the movies in that location to make sure there is no overlap
		for (int i = 0; i < movies.size(); i++) {
			Movie currMovie = movies.get(i);

			// check for overlaps 
			for (Map.Entry<Time, Theatre> entry : currMovie.timeLocation.entrySet()) {
				Time currTime = entry.getKey();
				Theatre currTheatre = entry.getValue();
				// check that the movie is equal
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
	//updates the price of an existing movie
	public static void updatePrice(Movie movie, Double newPrice){
		movie.price = Math.round(newPrice*100.0)/100.0;;
	}
    
    
	static boolean ranOnce = false;

	public static void main() {
		// lets add some movies to kickstart the program
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
