package fc.clients;

import java.util.Date;

/**
 * Class that allows to represent subscriber customers 
 * @author Clarisse
 * @author Mathys
 */
public class Subscriber extends Client {
	private String firstName;
	private String lastName;
	private Date birthDate;
	private Login login;

	/**
	 * the constructor of the class
	 * @param firstName the first name of the subscriber
	 * @param lastName the last name of the subscriber
	 * @param birthDate the date of birth of the subscriber
	 */
	public Subscriber(String firstName, String lastName, Date birthDate){
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	@Override
	public int getSizeCart() {
		return 3;
	}

	public void askCard() {}

	/**
	 * change the subscriber's information
	 * @param fn the first name
	 * @param ln the last name
	 * @param bd the birth date
	 */
	public void modifyInfo(String fn, String ln, Date bd){
		setFirstName(fn);
		setLastName(ln);
		setBirthDate(bd);
	}

	/**
	 * @return the first name of the subscriber
	 */
	public String getFirstName(){
		return  firstName;
	}

	/**
	 * change the first name of the subscriber
	 * @param firstName
	 */
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	/**
	 * @return the last name of the subscriber
	 */
	public String getLastName(){
		return lastName;
	}

	/**
	 * change the last name of the subscriber
	 * @param lastName
	 */
	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	/**
	 * @return the birth date name of the subscriber
	 */
	public Date getBirthDate(){
		return birthDate;
	}

	/**
	 * change the birth date of the subsciber
	 * @param birthDate
	 */
	public void setBirthDate(Date birthDate){
		this.birthDate = birthDate;
	}

	// TODO
	/**
	 * @return the identifier of the subscriber
	 */
	public Login getLogin(){ return null;}
}
