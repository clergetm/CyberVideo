package ui.managers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import fc.films.Film;
import ui.utils.factory.filmpanel.products.CartButton;
import ui.utils.observe.cart.ICartObservable;
import ui.utils.observe.cart.IObserver;

public class CartManager implements ICartObservable, ActionListener{
 
    private ArrayList<IObserver> observers;
    
    /**
     * Default constructor of {@code CartManager}.
     * @author MathysC
     */
    public CartManager() {
	this.observers = new ArrayList<>();
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
    public void register(IObserver observer) {
	this.observers.add(observer);
	
    }

    @Override
    public void unregister(IObserver observer) {
	this.observers.remove(observer);
	
    }

    @Override
    public void notifyObservers(FilmEvents event, Film film, String supportType) {
	switch (event) {
	case ADDTOCART:
	    this.observers.forEach(o -> o.addToCart(film, supportType));
	    break;
	case REMOVEFROMCART:
	    this.observers.forEach(o -> o.removeFromCart(film, supportType));
	    break;
	default:
	    throw new IllegalArgumentException("Unexpected value: " + event);
	}
	
    }

}
