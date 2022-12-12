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
     * @param number the unique number that represents the card
     */
    public Card() {
    }
    
    /**
     * @return number
     */
    public int getNumber() {
    	return number;
    }
}
