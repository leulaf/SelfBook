import java.util.*;

public class Main {
	
	static Scanner input = new Scanner(System.in);

	// all the theatres in the movie theatre 
	public static final Theatre[] ALL_THEATRES = { new Theatre(1), new Theatre(2), new Theatre(3), new Theatre(4), new Theatre(5), new Theatre(6), new Theatre(7), new Theatre(8), new Theatre(9), new Theatre(10) };
	
	
	static boolean ranOnce = false; 

	//prints all the theaters, the movies played in the theatre and the show time
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

	public static void main(String[] args) {
		
		if (!ranOnce) {
			Admin.main();
			ranOnce = true;
		}
			
			int movieChoice;
			int timeChoice;
			
			String[] times;
			
			//print the movies in theater now
			System.out.println("Movies in Theater Now:");
			System.out.println();

			//iterate through the movies in movies array list
			for(int i = 0; i < Admin.movies.size(); i++) {
				System.out.println(
						"////////////////////////////////////// \n//\t" +
				(i+1) + " - " + Admin.movies.get(i).getName());
			}
			
			System.out.println("\nPick a movie by entering the corresponding number.\nEnter 0 at any point to quit:\n"
						+ "------------------------------------- PassCode - Admin Options");
			
			while(true) {
				//check if the input is correct
				try {
					movieChoice = Integer.parseInt(input.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Input was incorrect, please try again");
					continue;
				}
				//if entered 0 quit the program
				if(movieChoice == 0){					
					main(null);
				} 
				//if entered admincode, print admin options
				else if (Admin.adminCode == movieChoice) {
					int adminChoice;
					System.out.println("-----------------Admin Options-----------------");
					System.out.println("1. Add Movies");
					System.out.println("2. Remove Movies");
					System.out.println("3. Change price");
					System.out.println("4. View total Tickets sold and money collected");
					System.out.println("5. Set or change Admin passcode\n");
					System.out.println("Pick one of the above options by entering the corresponding number or press 0 to quit at anytime:");
					while(true) {
						//check if the input is correct
						try {
							adminChoice = Integer.parseInt(input.nextLine());
						} catch (NumberFormatException e) {
							System.out.println("Input was incorrect, please try again");
							continue;
						}
						if(!(adminChoice <= 5 && adminChoice >= 0)) {
							System.out.println("Input was incorrect, please try again");
							continue;
						}
						break;
					}
					//quitting program
					if(adminChoice == 0) {
						main(null);
					}
					//add movie instructions printed
					else if(adminChoice == 1) {
						// int numTickets;
						double price;
						System.out.println("Enter the name of the Movie you want to add:");
						String movieName = input.nextLine();

						// check for quit 
						if (movieName.equals("0"))
							main(null);
						
						while(true) {	
							System.out.println("Enter How much the tickets for " + movieName + " should cost");
							//check if the input is correct
							try {
								price = Double.parseDouble(input.nextLine());
								// check for a quit
								if (price == 0)
									main(null);

								// check for a negative input 
								if (price < 0) {
									System.out.println("Price cannot be negative! Please try again");
									continue;
								}
								
							} catch (NumberFormatException e) {
								System.out.println("Input was incorrect, please try again");
								continue;
							}
							break;
						}
						//add movie to the movies array list
						Movie added = Admin.addMovie(movieName, price);
						boolean addOther = true;
						showAllTheatres();

						while(addOther) {
							int movieLocation;
							//ask for show time
							System.out.println("Enter when the showing time of " + movieName + " will be (EX: 12:00PM-02:00PM)");
							String movieTime = input.nextLine();
							// check for quit
							if (movieTime.equals("0")) {
								// need to remove the movie 
								Admin.removeMovie(added);
								main(null);
							}

							System.out.println("Enter where the location the showing for " + movieName + " will be (Choose: 1-10)");
							//check if valid input
							try {
								movieLocation = Integer.parseInt(input.nextLine());
								// check for quit 
								if (movieLocation == 0) {
									// need to remove the movie 
									Admin.removeMovie(added);
									main(null);
								}

								if (movieLocation < 1 || movieLocation > 10) {
									System.out.println("Invalid theatre! Sending you back to choose a new time and theatre.");
									continue;
								}
							} catch (NumberFormatException e) {
								System.out.println("Input was incorrect, please try again");
								continue;
							}
							Theatre theatreChoice = ALL_THEATRES[movieLocation-1];	
							// make sure that we can add the movie 
							if (!Admin.addTimeLocation(added, movieTime, theatreChoice)) {
								// we failed so let's allow them to try again.
								continue;
							}

							System.out.println("Enter 'yes' if you want to add another time and location otherwise enter 'no'");
							String anotherTime = input.nextLine();

							// check for quit 
							if (anotherTime.equals("0")) 
								main(null);

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
							//if not adding more movies, quit
							if(!addOther) {
								break;
							}else {
								continue;
							}
						}
						//if here successfully added the movie
						System.out.println("The movie " + movieName + " was sucessfully added along with it's price, number of tickets, times, and locations");
						System.out.println("Here is the updated list of movies with their corresponding theatre and show time");		
						//print updated list of the theaters, movies, show times
						Main.showAllTheatres();
						main(null);
					}
					// remove movie intructions printed
					else if(adminChoice == 2) {
						int removed;
						//print the curent movies
						while(true){
							for(int i = 0; i < Admin.movies.size(); i++) {
								System.out.println(
										"////////////////////////////////////// \n//\t" +
								(i+1) + " - " + Admin.movies.get(i).getName());
							}
							System.out.println("Enter the corresponding number for the movie you want to remove:");
							//check if valid input
							try {
								removed = Integer.parseInt(input.nextLine());

								// check for quit
								if (removed == 0) {
									main(null);
								}
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
						//ask which show time to remove
						while(true){
							wantRemoved.printTimeAndLocation();
							System.out.println("Please select the show time you want removed, or enter A to clear all showtimes.");
							//check if valid input
							try {
								// parse their input
								showtimeSelected = input.nextLine();

								showtimeSelectedInt = Integer.parseInt(showtimeSelected);

								if (wantRemoved.timeLocation.size()-1 < showtimeSelectedInt-1) {
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
							//if show time does not exist
							if (correspondingShowTime.getKey() == null) {
								System.out.println("We couldn't find that showtime! Sending you back to the main menu.");
								main(null);
							}
							//remove show time
							Admin.removeShowtimeForMovie(movieSelected, correspondingShowTime.getKey(), correspondingShowTime.getValue());
							System.out.println("The movie " + movieSelected + " at " + correspondingShowTime.getKey() + " in " + correspondingShowTime.getValue() + " has been removed.");
						}
						main(null);
						
					}
					//change price instructions printed
					else if(adminChoice == 3) {
						int changed;
						Double newPrice;
						//print all movies
						for(int i = 0; i < Admin.movies.size(); i++) {
							System.out.println(
									"////////////////////////////////////// \n//\t" +
							(i+1) + " - " + Admin.movies.get(i).getName());
						}
						
						while(true) {	
							System.out.println("Enter the corresponding number for the movie you want to change the price of:");
							//check if valid input
							try {
								changed = Integer.parseInt(input.nextLine());

								// check for quit
								if (changed == 0) 
									main(null);

							} catch (NumberFormatException e) {
								System.out.println("Input was incorrect, please try again");
								continue;
							}
							break;
						}
						//ask for the new ticket price
						while(true) {	
							System.out.println("Enter the new ticket price for " + Admin.movies.get(changed-1).getName() + ":");
							//check for valid input
							try {
								newPrice = Double.parseDouble(input.nextLine());

								// check for quit 
								if (newPrice == 0) 
									main(null);

							} catch (NumberFormatException e) {
								System.out.println("Input was incorrect, please try again");
								continue;
							}
							break;
						}
						//update price
						Admin.updatePrice(Admin.movies.get(changed-1), newPrice);
						//print the new ticket price
						System.out.println("The new ticket price for " + Admin.movies.get(changed-1).getName() + " has been changed to " + Admin.movies.get(changed-1).getPrice());
						main(null);
					}
					//print num tickets sold, money collected
					else if(adminChoice == 4) {
						System.out.println("----------------------------------------------");
						System.out.println("-- Number of tickets sold: " + Admin.getNumTicketsSold());
						System.out.println("----------------------------------------------");
						System.out.println("-- Amount of money collected: " + Admin.getTotalRevenue());
						System.out.println("----------------------------------------------");
						main(null);

					}
					//change passcode instructions printed
					else if(adminChoice == 5) {
						int changed;
						while(true) {	
							System.out.println("Enter the new Admin passcode: (passcode be consist five-eight digit number)");
							//check if valid input
							try {
								changed = Integer.parseInt(input.nextLine());

								// check for quit 
								if (changed == 0) 
									main(null);

							} catch (NumberFormatException e) {
								System.out.println("Input was incorrect, please try again");
								continue;
							}
							break;
						}
						
						Admin.setAdminCode(changed);
						//print successfull message
						System.out.println("The Admin passcode has been changed successfully");
						main(null);
					}
					
				}
				//check if valid input, print showtimes for movie selected
				else if(movieChoice > 0 && movieChoice < Admin.movies.size()+1) {
					times = Admin.movies.get(movieChoice - 1).printTimeAndLocation();
					break;
				}else {
					System.out.println("Input was incorrect, please try again");
					continue;
				}
			}
			
			System.out.println("\nPick the time and location for " + Admin.movies.get(movieChoice - 1).getName() + ":");
			

			while(true) {
				//check if valid input
				try {
					timeChoice = Integer.parseInt(input.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Input was incorrect, please try again");
					continue;
				}
				
				System.out.println();
				//check for quit
				if(timeChoice == 0){
					main(null);
				}else if(timeChoice > 0 && (timeChoice-1 <= Admin.movies.get(movieChoice - 1).timeLocation.size() - 1)) {
					// timeChoise now corresponds to a time and a theatre -> let's get that entry in our hashmap 
					Map.Entry<Time, Theatre> showTimeChosen = Admin.movies.get(movieChoice - 1).getShowTimeFromIndex(timeChoice-1);
					//go to checkout
					showTimeChosen.getValue().runCheckout(showTimeChosen.getKey(), Admin.movies.get(movieChoice - 1));
					break;
				}else {
					System.out.println("Input was incorrect, please try again");
					continue;
				}
			}
			
	}

}
