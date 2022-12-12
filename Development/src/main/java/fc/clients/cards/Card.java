package fc.clients.cards;

/**
 * Class that represents the cards
 * @author Clarisse
 *
 */
public abstract class Card {
    protected int number;
    
    /**
     * the constructor
     * @param numCard the unique number that represents the card
     */
    protected Card(int numCard) {
	this.number = numCard;
    }
    
    /**
     * @return number
     */
    public int getNumber() {
    	return number;
    }
}
