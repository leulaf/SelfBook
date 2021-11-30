import java.util.*;

public class Main {

	// static ArrayList<Movie> movieList = new ArrayList<Movie>();
	static Scanner input = new Scanner(System.in);

	// all the theatres in the movie theatre 
	public static final Theatre[] ALL_THEATRES = { new Theatre(1), new Theatre(2), new Theatre(3), new Theatre(4), new Theatre(5), new Theatre(6), new Theatre(7), new Theatre(8), new Theatre(9), new Theatre(10) };
	
	//remove later
	static boolean ranOnce = false;

	static void showAllTheatres() {
		// shows admin all theatre movie + time combinations currently showing 
		System.out.println("Here are all the current showtimes!");
		for (int i = 0; i < ALL_THEATRES.length; i++) {
			System.out.println("----------------");
			System.out.println("   Theatre " + ALL_THEATRES[i].getNumber());
			System.out.println("   ---------    ");
			// now iterate through the movies to print their times
			// each movie's times and theatres can be found in their hashmap attribute 
			for (int j = 0; j < Admin.movies.size(); j++) {
				Movie currMovie = Admin.movies.get(j);
				ArrayList<Time> showTimes = currMovie.getTimesInTheatre(ALL_THEATRES[i]);
				if (showTimes.size() > 0) {
					System.out.println(currMovie);
					for (int k = 0; k < showTimes.size(); k++) {
						System.out.println("   " + showTimes.get(k));
					}
				}
			}
			System.out.println("----------------");
		}
	}

	//remove Admin.movies.clear(); statement later
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (!ranOnce) {
			Admin.main();
			ranOnce = true;
		}
			
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
					Admin.movies.remove(Admin.Ex1);Admin.movies.remove(Admin.Ex2);Admin.movies.remove(Admin.Ex3);
					
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
						Admin.movies.remove(Admin.Ex1);Admin.movies.remove(Admin.Ex2);Admin.movies.remove(Admin.Ex3);
						main(null);
					}else if(adminChoice == 1) {
						// int numTickets;
						double price;
						System.out.println("Enter the name of the Movie you want to add:");
						String movieName = input.nextLine();
						
						// Commenting out for now - num seats is set by the theatre class 
						// while(true) {
						// 	System.out.println("Enter the number of tickets available for " + movieName);
						// 	try {
						// 		numTickets = Integer.parseInt(input.nextLine());
						// 	} catch (NumberFormatException e) {
						// 		System.out.println("Input was incorrect, please try again");
						// 		continue;
						// 	}
						// 	break;
						// }
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
						Movie added = Admin.addMovie(movieName, price);
						boolean addOther = true;
						showAllTheatres();

						while(addOther) {
							int movieLocation;
							System.out.println("Enter when the showing time of " + movieName + " will be (EX: 12:00PM-02:00PM)");
							String movieTime = input.nextLine();
							System.out.println("Enter where the location the showing for " + movieName + " will be (Choose: 1-10)");
							try {
								movieLocation = Integer.parseInt(input.nextLine());
								if (movieLocation < 1 || movieLocation > 10) 
									System.out.println("Please enter a valid theatre number.");
							} catch (NumberFormatException e) {
								System.out.println("Input was incorrect, please try again");
								continue;
							}
							Theatre theatreChoice = ALL_THEATRES[movieLocation-1];	
							System.out.println("Calling add Time Location");						
							// make sure that we can add the movie 
							if (!Admin.addTimeLocation(added, movieTime, theatreChoice)) {
								// we failed so let's allow them to try again.
								continue;
							}

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
						System.out.println("Here is the updated list of movies with their corresponding theatre and show time");		
						Main.showAllTheatres();
						main(null);
					}else if(adminChoice == 2) {
						int removed;
						while(true){
							for(int i = 0; i < Admin.movies.size(); i++) {
								System.out.println(
										"////////////////////////////////////// \n//\t" +
								(i+1) + " - " + Admin.movies.get(i).getName());
							}
							System.out.println("Enter the corresponding number for the movie you want to remove:");
							try {
								removed = Integer.parseInt(input.nextLine());
							} catch (NumberFormatException e) {
								System.out.println("Input was incorrect, please try again");
								continue;
							}
							if(removed > Admin.movies.size() || removed <= 0) {
								System.out.println("Input was incorrect, please try again");
								continue;
							}
							break;
						}
						Movie wantRemoved = Admin.movies.get(removed-1);
						String showtimeSelected = "";
						int showtimeSelectedInt = 0;
						boolean clearAllTimes = false;
						while(true){
							wantRemoved.printTimeAndLocation();
							System.out.println("Please select the show time you want removed, or enter A to clear all showtimes.");
							try {
								// parse their input
								showtimeSelected = input.nextLine();
								showtimeSelectedInt = Integer.parseInt(showtimeSelected);
								if (wantRemoved.timeLocation.size()-1 <= showtimeSelectedInt-1) {
									System.out.println("That showtime doesn't exist, please try again!");
									continue;
								}
								// check for a quit 
								if (showtimeSelectedInt == 0) {
									main(null);
									break;
								}
								break;
							} catch (NumberFormatException e) {
								// check if they want to clear all showtimes
								if (showtimeSelected.toLowerCase().charAt(0) == 'a') {
									clearAllTimes = true;
									break;
								}
							}
						}
						// now let's remove the appropriate showtime 
						if (clearAllTimes) {
							System.out.println("The movie " + Admin.movies.get(removed-1).getName() + " has been removed");
							Admin.removeMovie(Admin.movies.get(removed-1));
						} else {
							Movie movieSelected = Admin.movies.get(removed-1);
							Map.Entry<Time, Theatre> correspondingShowTime = movieSelected.getShowTimeFromIndex(showtimeSelectedInt-1); 
							if (correspondingShowTime.getKey() == null) {
								System.out.println("We couldn't find that showtime! Sending you back to the main menu.");
								main(null);
							}
							Admin.removeShowtimeForMovie(movieSelected, correspondingShowTime.getKey(), correspondingShowTime.getValue());
							System.out.println("The movie " + movieSelected + " at " + correspondingShowTime.getKey() + " in " + correspondingShowTime.getValue() + " has been removed.");
						}
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
						System.out.println("-- Number of tickets sold: " + Admin.getNumTicketsSold());
						System.out.println("----------------------------------------------");
						System.out.println("-- Amount of money collected: " + Admin.getTotalRevenue());
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
					Admin.movies.remove(Admin.Ex1);Admin.movies.remove(Admin.Ex2);Admin.movies.remove(Admin.Ex3);
					main(null);
				}else if(timeChoice > 0 && (timeChoice-1 <= Admin.movies.get(movieChoice - 1).timeLocation.size() - 1)) {
					// timeChoise now corresponds to a time and a theatre -> let's get that entry in our hashmap 
					Map.Entry<Time, Theatre> showTimeChosen = Admin.movies.get(movieChoice - 1).getShowTimeFromIndex(timeChoice-1);
					showTimeChosen.getValue().runCheckout(showTimeChosen.getKey());
					break;
				}else {
					System.out.println("Input was incorrect, please try again");
					continue;
				}
			}
			
	}

}
