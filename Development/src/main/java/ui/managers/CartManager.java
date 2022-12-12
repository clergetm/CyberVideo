package ui.managers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import fc.films.Film;
import ui.utils.factory.filmpanel.products.CartButton;
import ui.utils.factory.filmpanel.products.RemoveFromCartButton;
import ui.utils.observer.cart.ICartObservable;
import ui.utils.observer.cart.ICartObserver;

/**
 * Concrete implementation of Cart Observer.
 * @author MathysC
 * @see ui.utils.observer.cart.ICartObservable
 * @see ui.managers.FilmEvents
 */
public class CartManager implements ICartObservable, ActionListener{
    public static final int NORMAL_CART_SIZE = 1;
    public static final int SUBSCRIBER_CART_SIZE = 3;

    private ArrayList<ICartObserver> cartObservers;
    private int maxSize;
    private int currentSize;
    /**
     * Default constructor of {@code CartManager}.
     * @author MathysC
     */
    public CartManager() {
	this.cartObservers = new ArrayList<>();
	this.maxSize = NORMAL_CART_SIZE;
	this.currentSize = 0;
	
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	FilmEvents event = FilmEvents.getFromName(e.getActionCommand());
	switch (event){
	case ADDTOCART:
	    if(currentSize < maxSize ) {
//	    	TODO #29 récupérer le film et le supportType
		CartButton source = (CartButton)e.getSource();
		this.notifyCartObservers(FilmEvents.ADDTOCART, source.getFilm(), source.getSupportType());
		this.currentSize++;
	    }
	    break;
	case REMOVEFROMCART:
	    RemoveFromCartButton source = (RemoveFromCartButton)e.getSource();
	    this.notifyCartObservers(FilmEvents.REMOVEFROMCART, source.getFilm(), source.getSupportType());
	    this.currentSize--;
	    break;
	case RENT:
//	    TODO #29 RENT récupérer le film et le supportType
//	    TODO #29 maybe dissociate RENT actions and CART actions
//	    this.notifyCartObservers(null, null, null);
	    break;
	default:
	    throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
	}
	
    }

    @Override
    public void registerCart(ICartObserver cartObserver) {
	this.cartObservers.add(cartObserver);
	
    }

    @Override
    public void unregisterCart(ICartObserver cartObserver) {
	this.cartObservers.remove(cartObserver);
	
    }

    @Override
    public void notifyCartObservers(FilmEvents event, Film film, String supportType) {
	this.cartObservers.forEach(o -> o.update(event, film, supportType));
	
    }

}
