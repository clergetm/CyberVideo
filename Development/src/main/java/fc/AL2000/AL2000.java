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

	private final EPT ept = new EPT();
	private final Distributor ditributor = new Distributor();
	private final Bank bank = new Bank();
	private final Printer printer = new Printer();
	private Client client;
	
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
}
