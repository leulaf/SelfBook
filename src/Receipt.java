import java.util.ArrayList;

public class Receipt {

    private int numSeats;
    private double totalPaid;
    ArrayList<Seat> seats;
    private int theaterNumber;
    private String movieName;

    //creating a new reciept. called when chekout is successfull. 
    public Receipt(Movie movie, ArrayList<Seat> s, int numSeats, int theaterNum){
        this.totalPaid = movie.getPrice() * numSeats;
        this.movieName = movie.getName();
        this.numSeats = numSeats;
        this.theaterNumber = theaterNum;
        seats = new ArrayList<Seat>(s);
        
    }
    //printing an easy to read receipt
    public String toString(){
        
        String s = " Please print this page for future reference. \n";
        s += "----------------\n" ;
        s += "   Receipt   \n";
        s += "Date: " + java.time.LocalDate.now() + "\n"; 
        s += "Movie Name: " + this.movieName + "\n"; 
        s += "Theater Number: " + this.theaterNumber + "\n";
        s += "Number of Seats Purchased: " + this.numSeats + "\n";
        s += "Seat Numbers: \n" ;
        for (Seat seat : this.seats)
            s+= seat;
        s += "Total Paid "+ this.totalPaid;
        s += "\n----------------";

        return s;        
    }


    

    
}
