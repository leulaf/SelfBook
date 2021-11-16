import java.util.*;

public class Main {

	static ArrayList<Movie> movieList = new ArrayList<Movie>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Admin.main();
			
			int choice1;
			Scanner input = new Scanner(System.in);
			
			System.out.println("Movies in Theater Now:");
			System.out.println();
			
			for(int i = 0; i < Admin.movies.size(); i++) {
				System.out.println(
						"////////////////////////////////////// \n//\t" +
				(i+1) + " - " + Admin.movies.get(i).getName());
			}
			
			System.out.println("\nPick a movie by entering the corresponding number below:\n"
						+ "For Admin options, enter passcode instead:");
			
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
					Admin.movies.get(choice1 - 1).printTimeAndLocation();
					break;
				}else {
					System.out.println("Input was incorrect, please try again");
					continue;
				}
			}
			
		input.close();
		
	}

}
