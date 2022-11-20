package ui.utils.mediator.cart;

import java.util.LinkedHashMap;

import fc.clients.Client;
import fc.films.Film;
import ui.pages.actions.CartItemPanel;
import ui.pages.actions.CartPanel;


public class ConcreteCartMediator implements CartMediator {
    private LinkedHashMap<Film, CartItemPanel> cart = new LinkedHashMap<>();
//    private Client client; TODO Implement client
    private CartPanel cartPanel;
    
    @Override
    public boolean addToCart(Film film, String supportType) {
	// TODO Check Client cart
//	if (this.cart.size() >= this.client.getSizeCart()) return false;
	//TODO Add to client cart
//	this.client.addToCart(film);
	// Add to GUI cart
	
//	FIXME the CartItemPanel created doesn’t have setLight/setDark and Language 
	CartItemPanel item = new CartItemPanel(film, supportType);
	item.getRemoveButton().setMediator(this);
	item.getRemoveButton().setIndex(cart.size());
	this.cartPanel.addToCart(item);
	
	// Add to Mediator cart
	this.cart.put(film, item);
	
	return true; // TODO with check client cart
    }

    /**
     * {@inheritDoc}
     * 
     * This implementation of this method uses only {@code RemoveButton}.
     * So we don’t need to check for the right Component using {@code getComponentName()}
     * @author MathysC
     * @see io.utils.mediator.cart.components.RemoveButton
     */
    @Override
    public void removeFromCart(int index) {
	// Get key and value
	Film film = (Film) cart.keySet().toArray()[index];
	CartItemPanel panel = cart.get(film);

	// Change indexes
	int newIndex = index;
	/**
	 * Decrement the CartItemPanel's RemoveButton indexes after the current INDEX (thus: index+1)
	 *
	 * And we modify these indexes before actually deleting the Entry<>.
	 * HashMap indexes and those used in RemoveButtons are not linked.
	 *
	 * If we delete the entry and THEN we modify the index. We are going to face an issue.
	 */
	for(int idx = index+1; idx<cart.size(); idx++ ) {
	    cart.get(cart.keySet().toArray()[idx]).getRemoveButton().setIndex(newIndex);
	    newIndex++;
	}
	
//	 TODO: Remove from client cart
//	 this.client.removeFromCart(film);
	// Remove from GUI cart
	this.cartPanel.removeFromCart(panel);
	// Remove from Mediator cart
	cart.remove(film);
    }

    @Override
    public void clearCart() {
	// TODO Clear client cart
	
	// Clear GUI cart
	this.cartPanel.clearCart();
	// Clear Mediator cart
	this.cart.clear();
    }

    @Override
    public void registerClient(Client client) {
//	this.client = client;
	// TODO
    }

    @Override
    public void registerCartPanel(CartPanel cartPanel) {
	this.cartPanel = cartPanel;
    }
}
