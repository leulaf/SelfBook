
public class Seat {
	
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
	
	public static final char[] ROW_HASH = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
	
	public static final int OPEN = 0;
	public static final int ON_HOLD = 1;
	public static final int TAKEN = 2; 

	public Seat(int row, int col) {
		this.row = ROW_HASH[row];
		this.col = col;
		this.status = OPEN;
	}
		
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String toString() {
		String s = "Seat Number: " + this.row + this.col + "\n";
		return s;
		
	}

}
