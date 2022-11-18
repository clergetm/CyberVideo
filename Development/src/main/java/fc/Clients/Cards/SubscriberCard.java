package fc.Clients.Cards;

import java.util.ArrayList;

import fc.Rental;
import fc.Films.AgeRestriction;
import fc.Films.Categories;

public class SubscriberCard extends Card{
    private double balance;
    private int limitWeek;
    private AgeRestriction restriction;
    private ArrayList<Categories> forbiddenCategories;
    private ArrayList<Rental> rentalHistoric;

    public SubscriberCard(double balance, int limitWeek){
        this.balance = balance;
        this.limitWeek = limitWeek;
        forbiddenCategories = new ArrayList<Categories>();
        rentalHistoric = new ArrayList<Rental>();
    }

    public int getLimitWeek(){
        return  limitWeek;
    }

    public void setLimitWeek(int value){
        limitWeek = value;
    }

    public void setAgeRestriction(AgeRestriction newRestriction){
        restriction = newRestriction;
    }
    
    public AgeRestriction getAgeRestriction(){
    	return restriction;
    }

    public void addForbiddenCategorie(Categories cat){
        forbiddenCategories.add(cat);
    }

    public void removeForbiddenCategorie(Categories cat){
        forbiddenCategories.remove(cat);
    }

    public void removeAllForbiddenCategories(){
        forbiddenCategories.clear();
    }

    public Rental[] getHistoric(){
        if(rentalHistoric == null) return null;

        int size = rentalHistoric.size();
        Rental[] rentals = new Rental[size];
        for(int i = 0; i < size; i++){
            rentals[i] = rentalHistoric.get(i);
        }

        return rentals;
    }

    public void addRentalToHistoric(Rental rental){
        rentalHistoric.add(rental);
    }
}
