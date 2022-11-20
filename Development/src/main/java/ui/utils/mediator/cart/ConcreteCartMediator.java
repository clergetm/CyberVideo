package ui.utils.mediator.cart;

import java.util.HashMap;
import java.util.Map.Entry;

import fc.clients.Client;
import fc.films.Film;
import ui.pages.actions.CartItemPanel;
import ui.pages.actions.CartPanel;
import ui.utils.mediator.cart.components.CartComponent;
import ui.utils.mediator.cart.components.RemoveButton;

public class ConcreteCartMediator implements CartMediator {
    private HashMap<Film, CartItemPanel> cart = new HashMap<>();
//    private Client client; TODO Implement client
    private CartPanel cartPanel;
    
    @Override
    public boolean addToCart(Film film, String supportType) {
	// TODO Check Client cart
//	if (this.cart.size() >= this.client.getSizeCart()) return false;
	//TODO Add to client cart
//	this.client.addToCart(film);
	
	// Add to GUI cart
	CartItemPanel item = new CartItemPanel(film, supportType);
	item.getRemoveButton().setMediator(this);
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
    public void removeFromCart(CartComponent from) {
	
	RemoveButton button = (RemoveButton) from;
	for(Entry<Film, CartItemPanel> entry : cart.entrySet()) {
	    if((entry.getValue().getRemoveButton()).equals(button)) {
		// TODO: Remove from client cart
		// this.client.removeFromCart(entry.getKey());
		// Remove from GUI cart
		this.cartPanel.removeFromCart(entry.getValue());
		// Remove from Mediator cart
		cart.remove(entry.getKey());
		return;
	    }
	}	
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
