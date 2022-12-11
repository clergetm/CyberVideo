package fc.clients;

/**
 * class that allows to manage the connection 
 * of subscribers and administrators
 * @author Clarisse
 *
 */
public class Login {
	private String login;
	private String hpassword;
	
	/**
	 * Constructor of the class
	 * @param login the identifier for the connection
	 * @param hpassword the password for the connection
	 */
	public Login(String login, String hpassword) {
		this.login=login;
		this.hpassword=hpassword;
	}
	
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * enables changing the password
	 * @param password
	 */
	public void setHpassword(String password) {
		hpassword=hashPassword(password);
	}
	
	/**
	 * method that encrypts a string
	 * @param password the string to encrypting
	 * @return the encrypted password
	 */
	private String hashPassword(String password) {
		// TODO 
		return null;
	}
	
	/**
	 * check that the password is correct
	 * @param password
	 * @return
	 */
	public boolean verifyPassword(String password) {
		return this.hpassword==password;
	}
}
