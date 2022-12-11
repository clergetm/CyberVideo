package fc.clients;

import java.util.ArrayList;

import fc.clients.cards.CreditCard;
import fc.films.BluRay;
import fc.films.Film;

public abstract class Client {
    protected CreditCard creditCard;
    protected ArrayList<Film> filmCart;

    public abstract int getSizeCart();
    
    public Client(){
    	creditCard = new CreditCard();
        filmCart = new ArrayList<>();
    }

    public CreditCard getCreditCard(){
        return creditCard;
    }
    
    public void setCreditCard(CreditCard card){
        creditCard = card;
    }
 
    public void addToCart(Film film){
        if(filmCart.size() >= 3) return;
        filmCart.add(film);
    }

    public void takeBluRay(BluRay br){

    }

    public void returnBluRay(BluRay br){

    }

    public void removeFromCart(Film film){
        filmCart.remove(film);
    }

    public void removeFromCart(int index){
        if(index > 0 && index < filmCart.size())
            filmCart.remove(index);
    }
    
    public void emptyCart() {
    	
    }
}
