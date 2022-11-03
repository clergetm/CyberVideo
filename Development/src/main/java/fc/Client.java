import java.util.ArrayList;

public abstract class Client {
    protected CreditCard credCard;
    protected ArrayList<Film> filmCart;

    public Client(){
        filmCart = new ArrayList<>();
    }

    public CreditCard getCard(){
        return credCard;
    }
    public void setCard(CreditCard card){
        credCard = card;
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
}