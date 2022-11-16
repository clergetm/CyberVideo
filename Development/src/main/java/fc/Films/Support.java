package fc.Films;

/**
 * 
 * @author Clarisse
 * class that allows to manage the support of a film
 */
public abstract class Support {
	
	/**
	 * 
	 * @return the type of the film : QRCode or BluRay
	 */
	public abstract String getType();
	
	/**
	 * 
	 * @return the price of the rental
	 */
	public abstract double getTariff();
	
	/**
	 * 
	 * @return true id the support is available
	 */
	public abstract boolean isAvailable();
}
