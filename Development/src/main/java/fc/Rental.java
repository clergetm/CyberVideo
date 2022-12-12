package fc;

import java.util.Calendar;
import java.util.Date;

import fc.clients.cards.Card;
import fc.films.Film;
import fc.films.Support;

/**
 * @author Mathys
 * @author Clarisse
 * Class that allows to represent a location
 */
public class Rental {
    private Date startingDate;
    private Date endingDate;
    private Film film;
    private Card card;
    private Support support;

    /**
     * Constructor of the class that initializes the instance's attributes
     * @param film the film that will be rented
     * @param card the card with which the film will be rented
     * @param support the film's support (QRCode or BluRay)
     */
    public Rental(Film film, Card card, Support support){
        this.startingDate = new Date();
        //endRental calculates (startingDate + 24h) and affects it to endingDate
        endRental();
        this.film = film;
        this.card = card;
        this.support = support;
    }

    /**
     * @return the start date of the rental period
     */
    public Date getStartingDate() {
        return startingDate;
    }

    /**
     * @return the end date of the rental period
     */
    public Date getEndingDate(){
        return endingDate;
    }

    /**
     * @return the card with which the film will be rented
     */
    public Card getCard(){
        return card;
    }

    /**
     * 
     * @return the film that will be rented
     */
    public Film getFilm(){
        return film;
    }
    
    /**
     * @return the film's support
     */
    public Support getSupport() {
    	return support;
    }

    /**
     * calculate the date of the end of the rental period
     */
    public void endRental(){
        Calendar c = Calendar.getInstance();
        c.setTime(getStartingDate());
        c.add(Calendar.DATE, 2);
        this.endingDate = c.getTime();
    }
    
    /**
     * create the ticket of the rent
     * @return a string with the content of the ticket
     */
    public String getReceipt() {
    	// TODO 
    	return null;
    }
    
    /**
     * method for paying
     * @return the total amount of the rental
     */
    public double getDeposit() {
    	// TODO
    	return 0.0;
    }
}
