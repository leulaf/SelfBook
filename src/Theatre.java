import java.util.Scanner;

public class Theatre {
	
//	private Movie movie;
	private Seat allSeats[][];
	private int theatreNumber; 
	public static final int ROWS = 9;
	public static final int SEATS_PER_ROW = 11;
	public static final int TOTAL_NUM_SEATS = ROWS*SEATS_PER_ROW;
	
	public Theatre(int theatreNumber) {
//		this.movie = movie;
		this.theatreNumber = theatreNumber;
		this.allSeats = new Seat[ROWS][SEATS_PER_ROW];
		
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < SEATS_PER_ROW; j++) {
				this.allSeats[i][j] = new Seat(i, j);
			}
		}
		
	}

	private int indexOf(int row) {
		for (int i = 0; i < ROWS; i++) {
			if (Seat.ROW_HASH[i] == row) {
				return i;
			}
		}
		return -1;
	}

	public String toString() {
		String s = "Theatre: " + this.theatreNumber + "\n";
//		s += "Movie: " + this.movie + "\n";
		s += "Seats:\n";
		return s;
	}
	
	public void displaySeats() {
		System.out.println("     -- SCREEN --\n");
		
		String seats = "  ";

		
		for (int i = 0; i < ROWS; i++) {
			seats +=  i + " ";
		}
		seats += "\n";
		
		for (int i = 0; i < ROWS; i++) {
			seats += Seat.ROW_HASH[i] + ": ";
			for (int j = 0; j < SEATS_PER_ROW; j++) {
				if (this.allSeats[i][j].getStatus() == Seat.OPEN) 
					seats += "  ";
				else 
					seats += "X ";
			}
			seats += "\n";
		}
		System.out.println(seats);
	}
	
	public static void main(String[] args) {
		// TODO: validate the inputs
		Scanner inputObj = new Scanner(System.in);
		Theatre t = new Theatre(1);
		t.displaySeats();
		
		while (true) {
			// get the row desired
			System.out.println("Please select a row: ");
			char row = inputObj.next().charAt(0);
			
			// get the col desired
			System.out.println("Please select a column: ");
			int col = inputObj.nextInt();
		}
		int index = t.indexOf(row);
		if (index != -1) {
			Seat seatSelected = t.allSeats[index][col];
			int status = seatSelected.getStatus();
			if (status != Seat.OPEN) {
				System.out.println("This seat is taken, please select another one!");
			}
		}
		
		inputObj.close();
	}

}
