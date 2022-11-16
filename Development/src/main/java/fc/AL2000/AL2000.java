package fc.AL2000;

import java.util.ArrayList;

import fc.Clients.Client;
import fc.Clients.Cards.Card;
import fc.Films.AgeRestriction;
import fc.Films.BluRay;
import fc.Films.Categories;
import fc.Films.Film;
import fc.Films.StatesBluRay;

public class AL2000 {

	private final EPT ept;
	private final Distributor ditributor;
	private final Bank bank;
	private final Printer printer;
	private Client client;
	
	/**
	 * Constructor of {@code AL2000}
	 * @author MathysC
	 *
	 */
	public AL2000(Client client) {
		this.client=client;
	}
	
	public Card getCard() {
		return client.getCreditCard();
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
}
