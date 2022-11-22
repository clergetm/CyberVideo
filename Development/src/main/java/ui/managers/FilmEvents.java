package ui.managers;

/**
 * The enumeration of events related to the cart.
 * @author MathysC
 *
 */
public enum FilmEvents {
    ADDTOCART,		// Add a Film to the cart.
    REMOVEFROMCART,	// Remove a Film from the cart.
    RENT;		// Rent a Film.
    
    /**
     * Get the FilmEvents corresponding of the name.
     * @author MathysC
     *
     * @param name the name of a Film event.
     * @return FilmEvents if found. Else null.
     */
    public static FilmEvents getFromName(String name) {
	for(FilmEvents event : FilmEvents.values()) {
	    if(event.toString().equals(name)) {
		return event;
	    }
	}
	return null;
    }
}
