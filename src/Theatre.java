import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Theatre {
	
	private Seat allSeats[][];
	private int theatreNumber;
	private int numSeatsAvailable;
	private double moviePrice;

	private HashMap<Time,Theatre> seatMap = new HashMap<Time,Theatre>();

	static Scanner inputObj = new Scanner(System.in);

	public static final int ROWS = 9;
	public static final int SEATS_PER_ROW = 11;
	public static final int TOTAL_NUM_SEATS = ROWS*SEATS_PER_ROW;

	public Theatre(int theatreNumber) {
		this.numSeatsAvailable = TOTAL_NUM_SEATS;
		this.theatreNumber = theatreNumber;
		this.allSeats = new Seat[ROWS][SEATS_PER_ROW];
		
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < SEATS_PER_ROW; j++) {
				this.allSeats[i][j] = new Seat(i, j);
			}
		}
		
	}

	public static Theatre nullTheatre() {
		return new Theatre(-1);
	}

	public boolean equals(Theatre theatre) {
		return this.getNumber() == theatre.getNumber();
	}

	private int indexOf(int row) {
		for (int i = 0; i < ROWS; i++) {
			if (Seat.ROW_HASH[i] == row) {
				return i;
			}
		}
		return -1;
	}

	private void setMoviePrice(double price) {
		this.moviePrice = price;
	}
	
	private double getMoviePrice() {
		return this.moviePrice;
	}

	public String toString() {
		String s = "Theatre " + this.theatreNumber + "\n";
//		s += "Movie: " + this.movie + "\n";
		// s += "Seats:\n";
		return s;
	}

	public int getNumber() {
		return this.theatreNumber;
	} 

	public int getNumberSeatsAvailable() {
		return this.numSeatsAvailable;
	}
	
	public void displaySeats() {
		System.out.println("     -- SCREEN --\n");
		
		String seats = "   ";

		
		for (int i = 0; i < ROWS; i++) {
			seats +=  i + " ";
		}
		seats += "\n";
		
		for (int i = 0; i < ROWS; i++) {
			seats += Seat.ROW_HASH[i] + ": ";
			for (int j = 0; j < SEATS_PER_ROW; j++) {
				if (this.allSeats[i][j].getStatus() == Seat.OPEN) 
					seats += "  ";
				else if (this.allSeats[i][j].getStatus() == Seat.ON_HOLD)
					seats += "- ";
				else 
					seats += "X ";
			}
			seats += "\n";
		}
		System.out.println(seats);
	}

	private int[] selectSeat() {
		char row;
		int col;
		int index;
		int[] seatNum = new int[2];

		System.out.println("Attention: to quit you must select a row and column first - then, press 0 at any time");
		while (true) {
			// get the row desired
			System.out.println("Please select a row: ");
			row = inputObj.next().charAt(0);
			index = this.indexOf(row);
			if (index != -1)
				break;
			System.out.println("Please enter a valid row.");
		}

		while (true) {
			// get the col desired
			try {
				System.out.println("Please select a column: ");
				inputObj.nextLine();
				col = Integer.parseInt(inputObj.next());
				if (col >= 0 && col <=9)
					break;
				System.out.println("Please enter a valid column.");
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid integer!");
			}
		}

		seatNum[0] = index;
		seatNum[1] = col;
		return seatNum;
	}

	public void addShowTime(Time newTime, double price) {
		int mapSize = this.seatMap.size() + 10;
		Theatre t = new Theatre(mapSize);
		// set the movie price for admin purposes
		t.setMoviePrice(price);
		this.seatMap.put(newTime, t);
	}

	public Theatre findShowTime(Time time) {
		for (Map.Entry<Time,Theatre> entry : seatMap.entrySet()) {
			if (time.equals(entry.getKey())) 
				return entry.getValue();
		}
		return Theatre.nullTheatre();
	}

	private ArrayList<Seat> validateSeat() {
		
		int[] rowCol;
		int row;
		int col;
		Seat seatSelected;
		ArrayList<Seat> seatsSelected = new ArrayList<Seat>();
		int numberOfSeats;
		String input = "";

		// show them the seats available
		System.out.println("Here are the seats available: ");
		this.displaySeats();
		// allow them to choose the number of seats they want
		while (true) {
			try {
				System.out.println("Please enter the number of seats you would like: ");
				input = inputObj.next();
				numberOfSeats = Integer.parseInt(input);

				// check for a quit 
				if (this.checkInputForQuit(input)) {
					Main.main(null);
				}	
				if (numberOfSeats > this.numSeatsAvailable) {
					System.out.println("Not enough seats available. Please enter another number.");
					continue;
				} else if (numberOfSeats < 0) {
					System.out.println("Invalid number of seats. Please try again!");
					continue;
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number.");
			}
		}
		
		// now loop through the number of seats selected to choose a seat for each instance
		for (int i = 0; i < numberOfSeats; i++) {
			do {
				this.displaySeats();
				rowCol = this.selectSeat();
				row = rowCol[0];
				col = rowCol[1];
				seatSelected = this.allSeats[row][col];
				// we need to print an error message if they select a taken seat
				if (seatSelected.getStatus() != Seat.OPEN) 
					System.out.println("This seat is taken! Please try again!");

			} while (seatSelected.getStatus() != Seat.OPEN);
			seatSelected.setStatus(Seat.ON_HOLD);
			this.numSeatsAvailable -= 1;
			seatsSelected.add(seatSelected);
		}
		// show them that their seat is being held 
		this.displaySeats();

		return seatsSelected;
	}

	private boolean checkInputForQuit(String input) {
		// System.out.println("the token: " + input);
		if (input.equals("0")) 
			return true; 
		return false;
	}

	private ArrayList<Object> checkout() {
		Long number; 
		int month;
		int year;
		String cvv;
		System.out.println("Welcome to checkout!");
		while (true) {
			System.out.print("Enter 16 digit credit card number. Use format (xxxx xxxx xxxx xxxx): ");
			inputObj.nextLine();
			String cardNumber = inputObj.nextLine();

			if (this.checkInputForQuit(cardNumber))
				return new ArrayList<Object>(Arrays.asList(false, 0, 0, 0, 0));

			cardNumber = cardNumber.replaceAll(" ", "");
			if (cardNumber.length() != 16) {
				System.out.println("Please enter a valid credit card number. The length was incorrect.");
				continue;
			} 
		
			try {
				number = Long.valueOf(cardNumber);
			} catch (Exception e) {
				System.out.println("Please enter a valid credit card number. Invalid characters.");
				continue;
			}
			break;
		}
		while (true) {
			System.out.println("Enter month. Use format (xx): ");
			String res;
			try {
				res = inputObj.next();
				if (this.checkInputForQuit(res))
					return new ArrayList<Object>(Arrays.asList(false, 0, 0, 0, 0));
				
				month = Integer.valueOf(res);
				if (!(month > 0 && month <= 12)) {
					System.out.println("Invalid month! Please try again.");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("Please enter a valid month, or 0 if you want to quit");

			}
		}
		while (true) {
			System.out.println("Enter year. Use format (xxxx): ");
			String res;
			try {
				res = inputObj.next();
				if (this.checkInputForQuit(res))
					return new ArrayList<Object>(Arrays.asList(false, 0, 0, 0, 0));
				
				year = Integer.valueOf(res);
				int currYear = java.time.LocalDateTime.now().getYear();
				int currMonth = java.time.LocalDateTime.now().getMonthValue();

				if (!(year > currYear || (year == currYear && month >= currMonth))) {
					System.out.println("Invalid year! Please try again.");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("Please enter a valid year, or 0 if you want to quit");
			}
		}
		while (true) {
			System.out.println("Enter cvv. Use format (xxx): ");
			cvv = inputObj.next();
			if (this.checkInputForQuit(cvv))
				return new ArrayList<Object>(Arrays.asList(false, 0, 0, 0, 0));
				
			if (cvv.length() != 3) {
				System.out.println("Invalid cvv! Please try again.");
				continue;
			}
			try {
				int check = Integer.valueOf(cvv);
			} catch (Exception e) {
				System.out.println("Please enter a valid cvv");
				continue;
			}
			break;
		}
		return new ArrayList<Object>(Arrays.asList(true, number, month, year, cvv));
	}

	private void runTheatreUI(Movie movieChosen) {
		// initialize an array of seats selected
		ArrayList<Seat> seatsSelected = new ArrayList<Seat>();
		// validate that these seats are not currently held
		seatsSelected = this.validateSeat();
		this.processCheckout(movieChosen, seatsSelected);
	}
	
	// private void expiredCheckout() {
	// 	Scanner inputObj = new Scanner(System.in);
	// 	while (true) {
	// 		System.out.println("Your session has expired!");
	// 		System.out.println("If you want to start over, press 1"); 
	// 		System.out.println("If you want to cancel, press q");
	// 		String input = inputObj.next();
	// 		System.out.println("made it here");
	// 		if (input.equals("q"))
	// 			return;
	// 		else if (input.equals("1")) {
	// 			this.runTheatreUI();
	// 			break;
	// 		} else {
	// 			System.out.println("Please enter valid choice!");
	// 		}
	// 	}
	// }

	private void processCheckout(Movie movieChosen, ArrayList<Seat> seatsSelected) {

		ArrayList<Object> checkoutInfo = new ArrayList<Object>();
		// check if they opted to select a seat 
		if (seatsSelected.size() > 0)
			checkoutInfo = this.checkout();
		else 
			return;

		if (checkoutInfo.get(0).equals(true)) {
			// set all seats selected to taken 
			for (int i = 0; i < seatsSelected.size(); i++) {
				seatsSelected.get(i).setStatus(Seat.TAKEN);
				this.numSeatsAvailable -= 1;
			}
			// now increment the number of tickets sold and the total revenue 
			Admin.incrementNumTickets(seatsSelected.size());
			Admin.incrementTotalRevenue(seatsSelected.size()*this.getMoviePrice());
		
			// now give them a receipt
			Receipt r = new Receipt(movieChosen, seatsSelected, seatsSelected.size(), this.getNumber());
			System.out.println("Here is your receipt!");
			System.out.println(r);
		} else {
			// the user quit - set all seats selected to open
			for (int i = 0; i < seatsSelected.size(); i++) {
				seatsSelected.get(i).setStatus(Seat.OPEN);
				this.numSeatsAvailable += 1;
			}
		}

		return;	
	}

	public void runCheckout(Time showTime, Movie movieChosen) {		
		// get the proper sub theatre 
		Theatre t = this.seatMap.get(showTime);

		// run the theatre UI to select seats and checkout 
		t.runTheatreUI(movieChosen);
		

		// send them back to main 
		Main.main(null);
	}  
}
