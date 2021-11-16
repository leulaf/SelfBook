
public class User {

	boolean isAdmin;
	String firstName;
	String lastName; 
	String email; 
	
	public User(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName; 
		this.email = email; 
		this.isAdmin = false;
	}
}
