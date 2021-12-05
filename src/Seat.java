
public class Seat {
	//a seat has a row, column and status(open, on-hold, taken)
	private char row;
	private int col; 
	private int status; 
		
	public static final int A = 0;
	public static final int B = 1; 
	public static final int C = 2; 
	public static final int D = 3; 
	public static final int E = 4; 
	public static final int F = 5; 
	public static final int G = 6; 
	public static final int H = 7; 
	public static final int I = 8; 
	//getting char rows.
	public static final char[] ROW_HASH = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
	
	public static final int OPEN = 0;
	public static final int ON_HOLD = 1;
	public static final int TAKEN = 2; 

	//creates a new open seat in the given row and column
	public Seat(int row, int col) {
		this.row = ROW_HASH[row];
		this.col = col;
		this.status = OPEN;
	}
	//accessor method returning the row of the seat
	public int getRow() {
		return this.row;
	}
	
	//accessor method returning the column of the seat
	public int getCol() {
		return this.col;
	}

	//accessor method returning the status of the seat
	public int getStatus() {
		return this.status;
	}

	//mutator method setting the status of the seat
	public void setStatus(int status) {
		this.status = status;
	}
	
	//printing an easy to read seat
	public String toString() {
		String s = String.valueOf(this.row) + this.col + "\n";
		return s;
	}

}
