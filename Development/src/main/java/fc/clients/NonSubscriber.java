package fc.clients;

/**
 * 
 * @author Clarisse
 *
 * Class that allows to represent non-subscriber customers 
 */
public class NonSubscriber extends Client {

	@Override
	public int getSizeCart() {
		return 1;
	}
}
