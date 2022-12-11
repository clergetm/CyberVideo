package fc.al2000;

import fc.clients.Client;
import fc.clients.cards.Card;

/**
 * @author Clarisse
 * Class that allows to represent the AL2000 machine
 */

public class AL2000 {

	private final EPT ept = new EPT();
	private final Distributor ditributor = new Distributor();
	private final Bank bank = new Bank();
	private final Printer printer = new Printer();
	private Client connectedClient;

	/**
	 * Constructor of {@code AL2000}
	 * @author MathysC
	 *
	 */
	public AL2000() {
	}
	
	/**
	 * @return the card of the client who is connected
	 */
	public Card getCard() {
		return connectedClient.getCreditCard();
	}
	
	/**
	 * @return the printer of AL2000
	 */
	public Printer getPrinter() {
		return printer;
	}
	
	/**
	 * @return the bank
	 */
	public Bank getBank() {
		return bank;
	}
	
	/**
	 * @return the electronic payment terminal
	 */
	public EPT getEPT() {
		return ept;
	}
	
	/**
	 * @return the distributor
	 */
	public Distributor getDistributor() {
		return ditributor;
	}
	
	/**
	 * @return current client who is connected
	 */
	public Client getConnectedClient() {
		return connectedClient;
	}

	/**
	 * change the current client
	 * @param connectedClient the client who is conecting
	 */
	public void setConnectedClient(Client connectedClient) {
		this.connectedClient = connectedClient;
	}
}
