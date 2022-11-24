package ui.managers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import fc.films.Film;
import ui.utils.factory.filmpanel.products.CartButton;
import ui.utils.observer.cart.ICartObservable;
import ui.utils.observer.cart.ICartObserver;

/**
 * Concrete implementation of Cart Observer.
 * @author MathysC
 * @see ui.utils.observer.cart.ICartObservable
 * @see ui.managers.FilmEvents
 */
public class CartManager implements ICartObservable, ActionListener{
 
    private ArrayList<ICartObserver> cartObservers;
    
    /**
     * Default constructor of {@code CartManager}.
     * @author MathysC
     */
    public CartManager() {
	this.cartObservers = new ArrayList<>();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	FilmEvents event = FilmEvents.getFromName(e.getActionCommand());
	switch (event){
	case ADDTOCART:
//	    TODO #29 récupérer le film et le supportType
	    CartButton source = (CartButton)e.getSource();
	    this.notifyObservers(FilmEvents.ADDTOCART, source.getFilm(), source.getSupportType());
	    break;
	case REMOVEFROMCART:
//	    TODO #29 REMOVE
	    break;
	case RENT:
//	    TODO #29 RENT récupérer le film et le supportType
//	    TODO #29 maybe dissociate RENT actions and CART actions
	    this.notifyObservers(null, null, null);
	    break;
	default:
	    throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
	}
	
    }

    @Override
    public void register(ICartObserver cartObserver) {
	this.cartObservers.add(cartObserver);
	
    }

    @Override
    public void unregister(ICartObserver cartObserver) {
	this.cartObservers.remove(cartObserver);
	
    }

    @Override
    public void notifyObservers(FilmEvents event, Film film, String supportType) {
	this.cartObservers.forEach(o -> o.update(event, film, supportType));
	
    }

}
