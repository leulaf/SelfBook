
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
		Theatre t = new Theatre(1);
		t.displaySeats();
	}

}
