package ui.utils.observe.cart;

import fc.films.Film;

/**
 * Common interface for an Observer, cart related, object.
 * @author MathysC
 */
public interface IObserver {

    /**
     * 
     * @author MathysC
     *
     * @param film The Film to add to the cart.
     * @param supportType The supportType related to the Film.
     */
    void addToCart(Film film, String supportType);
    
    /**
     * 
     * @author MathysC
     *
     * @param film The Film to remove fromthe cart.
     * @param supportType The supportType related to the Film.
     */
    void removeFromCart(Film film, String supportType);
}
