package fc;

import java.util.Calendar;
import java.util.Date;

import fc.clients.cards.Card;
import fc.films.Film;
import fc.films.Support;

public class Rental {
    private Date startingDate;
    private Date endingDate;
    private Film film;
    private Card card;
    private Support support;

    public Rental(Film film, Card card, Support support){
        this.startingDate = new Date();
        //endRental calculates (startingDate + 24h) and affects it to endingDate
        endRental();
        this.film = film;
        this.card = card;
        this.support = support;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public Date getEndingDate(){
        return endingDate;
    }

    public Card getCard(){
        return card;
    }

    public Film getFilm(){
        return film;
    }
    
    public Support getSupport() {
    	return support;
    }

    public void endRental(){
        Calendar c = Calendar.getInstance();
        c.setTime(getStartingDate());
        c.add(Calendar.DATE, 2);
        this.endingDate = c.getTime();
    }
    
    public String getReceipt() {
    	// TODO 
    	return null;
    }
    
    public double getDeposit() {
    	// TODO
    	return 0.0;
    }
}
