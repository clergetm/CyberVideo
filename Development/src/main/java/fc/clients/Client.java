package fc.clients;

import java.util.ArrayList;

import fc.clients.cards.CreditCard;
import fc.films.BluRay;
import fc.films.Film;

/**
 * Class which allows to represent the clients
 * @author Clarisse
 *
 */
public abstract class Client {
	/**
	 * creditCard  : the credit card of the client
	 * filmCart : the list film in cart
	 */
    protected CreditCard creditCard;
    protected ArrayList<Film> filmCart;

    /**
     * the constructor of the Client class
     */
    protected Client(){
    	creditCard = null;
        filmCart = new ArrayList<>();
    }
    
    /**
     * @return the maximum number of films that
     * the customer can add to his cart according 
     * to his privileges
     */
    public abstract int getSizeCart();

    /**
     * @return the credit card of the client
     */
    public CreditCard getCreditCard(){
        return creditCard;
    }
    
    /**
     * change the card of the client
     * @param card
     */
    public void setCreditCard(CreditCard card){
        creditCard = card;
    }
 
    /**
     * add film to the client's cart
     * @param film
     */
    public void addToCart(Film film){
        if(filmCart.size() >= 3) return;
        filmCart.add(film);
    }

    /**
     * remove film from cart
     * @param film
     */
    public void removeFromCart(Film film){
        filmCart.remove(film);
    }

    /**
     * remove film from cart by its index
     * @param index
     */
    public void removeFromCart(int index){
        if(index > 0 && index < filmCart.size())
            filmCart.remove(index);
    }
    
    /**
     * empty the cart
     * deletes all the films in the cart
     */
    public void emptyCart() {
    	//TODO
    }
    
    /**
     * method that represents the action of the client retrieving a bluray br
     * @param br
     */
    public void takeBluRay(BluRay br){

    }

    /**
     * method that represents the action of the client returning a bluray br
     * @param br
     */
    public void returnBluRay(BluRay br){

    }
}
