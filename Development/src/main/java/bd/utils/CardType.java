package bd.utils;

/**
 * Enumeration of card types used in the database.
 * @author MathysC
 */
public enum CardType {
    CREDIT("CC"),	
    SUBSCRIBER("SC");
    
    private String cardType;
    
    /**
     * Default constructor of {@code CardType}.
     * @author MathysC
     *
     * @param t The type.
     */
    CardType(String t){
	this.cardType = t;
    }
    
    @Override
    public String toString() {
	return this.cardType;
    }
}
