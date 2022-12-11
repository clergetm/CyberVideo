package fc.clients;

/**
 * Class that allows to represent non-subscriber customers 
 * @author Clarisse
 */
public class NonSubscriber extends Client {

	@Override
	public int getSizeCart() {
		return 1;
	}
}
