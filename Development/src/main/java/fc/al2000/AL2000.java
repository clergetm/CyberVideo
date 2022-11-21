package fc.al2000;

import java.util.ArrayList;

import fc.clients.Client;
import fc.clients.cards.Card;
import fc.films.AgeRestriction;
import fc.films.BluRay;
import fc.films.Categories;
import fc.films.Film;
import fc.films.StatesBluRay;

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
	
	public Card getCard() {
		// TODO
		//return client.getCreditCard();
		return null;
	}
	
	public Printer getPrinter() {
		return printer;
	}
	
	public Bank getBank() {
		return bank;
	}
	
	public EPT getEPT() {
		return ept;
	}
	
	public Distributor getDistributor() {
		return ditributor;
	}
	
	public Client getConnectedClient() {
		return connectedClient;
	}

	public void setConnectedClient(Client connectedClient) {
		this.connectedClient = connectedClient;
	}
}
