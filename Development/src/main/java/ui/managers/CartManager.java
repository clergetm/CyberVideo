package ui.managers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import fc.films.Film;
import ui.utils.factory.filmpanel.products.CartButton;
import ui.utils.observe.cart.ICartObservable;
import ui.utils.observe.cart.ICartObserver;

/**
 * Concrete implementation of Cart Observer.
 * @author MathysC
 * @see ui.utils.observe.cart.ICartObservable
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
//	    TODO TOCART
//	    TODO récupérer le film et le supportType
	    CartButton source = (CartButton)e.getSource();
	    this.notifyObservers(FilmEvents.ADDTOCART, source.getFilm(), source.getSupportType());
	    break;
	case REMOVEFROMCART:
//	    TODO REMOVE
	    break;
	case RENT:
//	    TODO RENT
//	    TODO récupérer le film et le supportType
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
