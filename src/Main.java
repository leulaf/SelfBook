import java.util.*;

public class Main {

	static ArrayList<Movie> movieList = new ArrayList<Movie>();
	static Scanner input = new Scanner(System.in);
	
	//remove later
	static boolean ranOnce = false;

	//remove Admin.movies.clear(); statement later
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Admin.main();
			
			int movieChoice;
			int timeChoice;
			String[] times;
			
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
					movieChoice = Integer.parseInt(input.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Input was incorrect, please try again");
					continue;
				}
				
				System.out.println();
				if(movieChoice == 0){
					Admin.movies.clear();
					
					main(null);
				} else if (Admin.adminCode == movieChoice) {
					int adminChoice;
					System.out.println("-----------------Admin Options-----------------");
					System.out.println("1. Add Movies");
					System.out.println("2. Remove Movies");
					System.out.println("3. Change price");
					System.out.println("4. View total Tickets sold and money collected");
					System.out.println("5. Set or change Admin passcode\n");
					System.out.println("Pick one of the above options by entering the corresponding number:");
					while(true) {
						try {
							adminChoice = Integer.parseInt(input.nextLine());
						} catch (NumberFormatException e) {
							System.out.println("Input was incorrect, please try again");
							continue;
						}
						if(!(adminChoice <= 5 && adminChoice > 0)) {
							continue;
						}
						break;
					}
					if(adminChoice == 0) {
						Admin.movies.clear();
						main(null);
					}else if(adminChoice == 1) {
						int numTickets;
						double price;
						System.out.println("Enter the name of the Movie you want to add:");
						String movieName = input.nextLine();
						
						while(true) {
							System.out.println("Enter the number of tickets available for " + movieName);
							try {
								numTickets = Integer.parseInt(input.nextLine());
							} catch (NumberFormatException e) {
								System.out.println("Input was incorrect, please try again");
								continue;
							}
							break;
						}
						while(true) {	
							System.out.println("Enter How much the tickets for " + movieName + " should cost");
							try {
								price = Double.parseDouble(input.nextLine());
							} catch (NumberFormatException e) {
								System.out.println("Input was incorrect, please try again");
								continue;
							}
							break;
						}
						Movie added = Admin.addMovie(movieName, numTickets, price);
						boolean addOther = true;
						
						
						
						while(addOther) {
							System.out.println("Enter the when showing time of " + movieName + "will be (EX: 12PM-2PM)");
							String movieTime = input.nextLine();
							System.out.println("Enter where the location the showing for " + movieName + " will be");
							String movieLocation = input.nextLine();
							Admin.addTimeLocation(added, movieTime, movieLocation);
							System.out.println("Enter 'yes' if you want to add another time and location otherwise enter 'no'");
							String anotherTime = input.nextLine();
							while(true) {
								if(anotherTime.toLowerCase().equals("yes")) {
									addOther = true;
								}else if(anotherTime.toLowerCase().equals("no")){
									addOther = false;
								}else {
									System.out.println("Input was incorrect, please try again");
									continue;
								}
								break;
							}
							
							if(!addOther) {
								break;
							}else {
								continue;
							}
						}
						System.out.println("The movie " + movieName + " was sucessfully added along with it's price, number of tickets, times, and locations");
						main(null);
					}else if(adminChoice == 2) {
						int removed;
						for(int i = 0; i < Admin.movies.size(); i++) {
							System.out.println(
									"////////////////////////////////////// \n//\t" +
							(i+1) + " - " + Admin.movies.get(i).getName());
						}
						
						while(true){
							System.out.println("Enter the corresponding number for the movie you want to remove:");
							try {
								removed = Integer.parseInt(input.nextLine());
							} catch (NumberFormatException e) {
								System.out.println("Input was incorrect, please try again");
								continue;
							}
							break;
						}
						System.out.println("The movie " + Admin.movies.get(removed-1).getName() + " has been removed");
						Admin.removeMovie(Admin.movies.get(removed-1));
						main(null);
						
					}else if(adminChoice == 3) {
						int changed;
						Double newPrice;
						for(int i = 0; i < Admin.movies.size(); i++) {
							System.out.println(
									"////////////////////////////////////// \n//\t" +
							(i+1) + " - " + Admin.movies.get(i).getName());
						}
						
						while(true) {	
							System.out.println("Enter the corresponding number for the movie you want to change the price of:");
							try {
								changed = Integer.parseInt(input.nextLine());
							} catch (NumberFormatException e) {
								System.out.println("Input was incorrect, please try again");
								continue;
							}
							break;
						}
						
						while(true) {	
							System.out.println("Enter the new ticket price for " + Admin.movies.get(changed-1).getName() + ":");
							try {
								newPrice = Double.parseDouble(input.nextLine());
							} catch (NumberFormatException e) {
								System.out.println("Input was incorrect, please try again");
								continue;
							}
							break;
						}
						
						Admin.updatePrice(Admin.movies.get(changed-1), newPrice);
						
						System.out.println("The new ticket price for " + Admin.movies.get(changed-1).getName() + " has been changed to " + Admin.movies.get(changed-1).getPrice());
						main(null);
					}else if(adminChoice == 4) {
						System.out.println("----------------------------------------------");
						System.out.println("-- Number of tickets sold: " + Admin.ticketsSold);
						System.out.println("----------------------------------------------");
						System.out.println("-- Amount of money collected: " + Admin.moneyCollected);
						System.out.println("----------------------------------------------");
						main(null);
					}else if(adminChoice == 5) {
						int changed;
						while(true) {	
							System.out.println("Enter the new Admin passcode: (passcode be consist five-eight digit number)");
							try {
								changed = Integer.parseInt(input.nextLine());
							} catch (NumberFormatException e) {
								System.out.println("Input was incorrect, please try again");
								continue;
							}
							break;
						}
						
						Admin.setAdminCode(changed);
						
						System.out.println("The Admin passcode has been changed successfully");
						main(null);
					}
					
				}else if(movieChoice > 0 && movieChoice < Admin.movies.size()+1) {
					times = Admin.movies.get(movieChoice - 1).printTimeAndLocation();
					break;
				}else {
					System.out.println("Input was incorrect, please try again");
					continue;
				}
			}
			
			
			
			System.out.println("\nPick the time and location for " + Admin.movies.get(movieChoice - 1).getName() + ":");
			

			while(true) {
				
				try {
					timeChoice = Integer.parseInt(input.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Input was incorrect, please try again");
					continue;
				}
				
				System.out.println();
				
				if(timeChoice == 0){
					Admin.movies.clear();
					main(null);
				}else if(timeChoice > 0 && timeChoice < times.length+1) {
					System.out.println("Seats available for " + Admin.movies.get(movieChoice - 1).getName() + " at " + 
					times[timeChoice-1] + " in " + Admin.movies.get(movieChoice - 1).timeLocation.get(times[timeChoice-1]));
					//just an example implemented later
					Theatre.main(null);;
					break;
				}else {
					System.out.println("Input was incorrect, please try again");
					continue;
				}
			}
			
	}

}
