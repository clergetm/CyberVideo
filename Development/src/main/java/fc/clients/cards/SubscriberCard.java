package fc.clients.cards;

import java.util.ArrayList;

import fc.Rental;
import fc.films.AgeRestriction;
import fc.films.Categories;

/**
 * Class that represents the subscriber cards
 * @author Mathys
 * @author Clarisse
 *
 */
public class SubscriberCard extends Card{
	private double balance;
	private int limitWeek;
	private AgeRestriction restriction;
	private ArrayList<Categories> forbiddenCategories;
	private ArrayList<Rental> rentalHistoric;

	/**
	 * the constructor 
	 * @param balance the current balance on the card
	 * @param limitWeek the maximum number of rentals possible per week
	 */
	public SubscriberCard(int numCard, double balance, int limitWeek){
	    super(numCard);
	    this.balance = balance;
	    this.limitWeek = limitWeek;
	    forbiddenCategories = new ArrayList<Categories>();
	    rentalHistoric = new ArrayList<Rental>();
	}

	/**
	 * @return limitWeek
	 */
	public int getLimitWeek(){
		return  limitWeek;
	}

	/**
	 * change the maximum number of rentals per week
	 * @param value the number per week
	 */
	public void setLimitWeek(int value){
		limitWeek = value;
	}

	/**
	 * add or change age restrictions on the card
	 * @param newRestriction
	 */
	public void setAgeRestriction(AgeRestriction newRestriction){
		this.restriction = newRestriction;
	}

	/**
	 * add prohibited category to the card
	 * @param cat the category
	 */
	public void addForbiddenCategorie(Categories cat){
		forbiddenCategories.add(cat);
	}

	/**
	 * remove prohibited category from the card
	 * @param cat
	 */
	public void removeForbiddenCategorie(Categories cat){
		forbiddenCategories.remove(cat);
	}

	/**
	 * remove all prohibited categories from the card
	 */
	public void removeAllForbiddenCategories(){
		forbiddenCategories.clear();
	}

	/**
	 * @return the historic of all the rentals
	 */
	public Rental[] getHistoric(){
		if(rentalHistoric == null) return null;

		int size = rentalHistoric.size();
		Rental[] rentals = new Rental[size];
		for(int i = 0; i < size; i++){
			rentals[i] = rentalHistoric.get(i);
		}
		return rentals;
	}

	/**
	 * add a new rental to the historic
	 * @param rental
	 */
	public void addRentalToHistoric(Rental rental){
		rentalHistoric.add(rental);
	}
}
