package fc.clients;

/**
 * 
 * @author Clarisse
 *
 */
public class Login {
	
	//read only
	private String login;
	private String hpassword;
	
	public Login(String login, String hpassword) {
		this.login=login;
		this.hpassword=hpassword;
	}
	
	public String getIdentifier() {return null;}
	
	public void setLogin(String login) {
		this.login=login;
	}
	
	public void setHpassword(String password) {
		hpassword=hashPassword(password);
	}
	
	private String hashPassword(String password) {
		// TODO 
		return null;
	}
	
	public boolean verifyPassword(String password) {
		return this.hpassword==password;
	}
}
