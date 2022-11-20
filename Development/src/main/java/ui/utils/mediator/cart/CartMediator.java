package ui.utils.mediator.cart;

import fc.clients.Client;
import fc.films.Film;
import ui.pages.actions.CartPanel;

/**
 * Mediator Interface to encapsulate all cart related objects.
 * @author MathysC
 * @see ui.utils.mediator.cart.components.CartComponent
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
    void removeFromCart(int index);
    
    /**
     * Remove all films from the cart.
     * @author MathysC
     */
    void clearCart();
    
    /**
     * @author MathysC
     *
     * @param client the client to register. 
     */
    void registerClient(Client client);
    
    /**
     * @author MathysC
     *
     * @param cartPanel the CartPanel to register.
     */
    void registerCartPanel(CartPanel cartPanel);
}
