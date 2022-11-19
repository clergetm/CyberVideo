package fc.films;

/**
 * BluRay is the class that allow the bluray
 * @author Clarisse
 *
 */
public class BluRay extends Support {

	private double purchasePrice;
	private StatesBluRay state;
	
	/**
	 * Constructor of the class BluRay
	 * @param purchasePrice is the purchase price of the BluRay
	 * @param state is the current state of the BluRay
	 */
	public BluRay(double price, StatesBluRay state) {
		this.purchasePrice=price;
		this.state=state;
	}
	
	@Override
	public String getType() {
		return "BluRay";
	}
	
	@Override
	public double getTariff() {
		return 5;
	}

	@Override
	public boolean isAvailable() {
		return state==StatesBluRay.AVAILABLE ;
	}

	/**
	 * 
	 * @return the price of the bluray
	 */
	public double getPurchasePrice() {
		return purchasePrice;
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
}
