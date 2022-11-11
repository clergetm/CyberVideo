package fc.Films;

/**
 * BluRay is the class that allow the bluray
 * @author Clarisse
 *
 */
public class BluRay extends Film {

	private double price;
	private StatesBluRay state;
	/**
	 * Constructor of the class BluRay
	 * @param price is the purchase price of the BluRay
	 * @param state is the current state of the BluRay
	 * @param other param are in Film class
	 */
	public BluRay(double price, StatesBluRay state, String title, 
			String synopsis, String[] actors, String FNDirector, String LNDirector,
			AgeRestriction restriction, Categories[] categories) {
		super(title, synopsis, actors, FNDirector, LNDirector, restriction, categories);

		this.price=price;
		this.state=state;
	}
	
	@Override
	public String getType() {
		return "BluRay";
	}

	/**
	 * 
	 * @return the price of the bluray
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * change the purchase price of the bluray
	 * @param price the new price of the bluray
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * 
	 * @return the state of the bluray
	 */
	public StatesBluRay getState() {
		return state;
	}
	
	/**
	 * change the current sate of the bluray
	 * @param state
	 */
	public void setState(StatesBluRay state) {
		this.state=state;
		
	}
	
	/**
	 * @author MathysC
	 *
	 * @return true if the BluRay is stated AVAILABLE, else false.
	 */
	@Override
	public boolean isBRAvailable() {
		return this.state == StatesBluRay.AVAILABLE? true: false;
	}

}
