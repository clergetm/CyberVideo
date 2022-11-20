package ui.utils.mediator.cart;

import fc.films.Film;
import ui.utils.mediator.cart.components.Component;

/**
 * Mediator Interface to encapsulate all cart related objects.
 * @author MathysC
 * @see ui.utils.mediator.cart.components.Component
 */
public interface CartMediator {
    
    /**
     * Add a film to the cart.
     * @author MathysC
     *
     * @param film the wanted film.
     * @param supportType how the film is rented.
     * @return True if the film was added to the cart, else false.
     */
    boolean addToCart(Film film, String supportType);
    
    /**
     * Remove a film from the cart.
     * @author MathysC
     *
     * @param from the Component used.
     */
    void removeFromCart(Component from);
    
    /**
     * Remove all films from the cart.
     * @author MathysC
     *
     */
    void clearCart();
}
