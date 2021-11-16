import java.util.*;

public class Main {

	static ArrayList<Movie> movieList = new ArrayList<Movie>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Admin.main();
			
			int choice1;
			int choice2;
			String[] times;
			Scanner input = new Scanner(System.in);
			
			System.out.println("Movies in Theater Now:");
			System.out.println();
			
			for(int i = 0; i < Admin.movies.size(); i++) {
				System.out.println(
						"////////////////////////////////////// \n//\t" +
				(i+1) + " - " + Admin.movies.get(i).getName());
			}
			
			System.out.println("\nPick a movie by entering the corresponding number.\nEnter 0 at any point to quit:\n"
						+ "------------------------------------- PassCode - Admin Options");
			
			while(true) {
				
				try {
					choice1 = Integer.parseInt(input.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Input was incorrect, please try again");
					continue;
				}
				
				System.out.println();
				
				if (Admin.adminCode == choice1) {
					System.out.println("-----------------Admin Options-----------------");
					System.out.println("1. Add Movies");
					System.out.println("2. Remove Movies");
					System.out.println("3. Change price");
					System.out.println("4. Change Time/Location");
				}else if(choice1 < Admin.movies.size()+1) {
					times = Admin.movies.get(choice1 - 1).printTimeAndLocation();
					break;
				}else {
					System.out.println("Input was incorrect, please try again");
					continue;
				}
			}
			
			System.out.println("\nPick the time and location for " + Admin.movies.get(choice1 - 1).getName() + ":");
			
			while(true) {
				
				try {
					choice2 = Integer.parseInt(input.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Input was incorrect, please try again");
					continue;
				}
				
				System.out.println();
				
				if(choice2 < times.length+1) {
					System.out.println("Seats available for " + Admin.movies.get(choice1 - 1).getName() + " at " + 
					times[choice2-1] + " in " + Admin.movies.get(choice1 - 1).timeLocation.get(times[choice2-1]));
					//just an example implemented later
					Theatre.main(null);;
					break;
				}else {
					System.out.println("Input was incorrect, please try again");
					continue;
				}
			}
		input.close();
		
	}

}
