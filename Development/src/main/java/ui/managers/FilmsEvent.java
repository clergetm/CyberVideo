package ui.managers;

/**
 * The enumeration of events related to the cart.
 * @author MathysC
 *
 */
public enum FilmsEvent {
    ADDTOCART,		// Add a Film to the cart.
    REMOVEFROMCART,	// Remove a Film from the cart.
    RENT;		// Rent a Film.
    
    public static FilmsEvent getFromName(String name) {
	for(FilmsEvent event : FilmsEvent.values()) {
	    if(event.toString().equals(name)) {
		return event;
	    }
	}
	return null;
    }
}
