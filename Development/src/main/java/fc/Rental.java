import java.util.Calendar;
import java.util.Date;

public class Rental {
    private Date startingDate;
    private int tarif;
    private Date endingDate;
    private Film film;
    private Card card;

    public Rental(int tarif, Film film){
        this.startingDate = new Date();
        //endRental calculates (startingDate + 24h) and affects it to endingDate
        endRental();
        this.tarif = tarif;
        this.film = film;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public int getTarif(){
        return tarif;
    }

    public Date getEndingDate(){
        return endingDate;
    }

    public Card getCard(){
        return card;
    }

    public void setCard(Card card){
        this.card = card;
    }

    public Film getFilm(){
        return film;
    }

    public void endRental(){
        Calendar c = Calendar.getInstance();
        c.setTime(getStartingDate());
        c.add(Calendar.DATE, 2);
        this.endingDate = c.getTime();
    }
}