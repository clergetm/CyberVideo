package bd.DAO_Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import fc.Rental;
import fc.clients.cards.Card;
import fc.films.Film;
import fc.films.Support;

public class RentalDAO extends DAO<Rental> {

    public RentalDAO(Connection conn) {
        super(conn);
    }

    @Override
    public Rental read(int id) {

        Rental rental = null;

        try{
            
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM Rentals NATURAL JOIN BluRays NATURAL JOIN SupportFilms WHERE rentalsID ="+id);
            
            if(result.first()){
        	//FIXME
                FilmsDAO filmDAO = new FilmsDAO(connect);
                Film film = filmDAO.read(result.getInt("filmID"));
                Card card = null;
                Support support = null;
                rental = new Rental(film, card, support);
            }

        }
        catch (SQLException e) { e.printStackTrace(); }

        return rental;
        
    }

    @Override
    public boolean create(Rental obj) {

        return false;
    }

    @Override
    public boolean update(Rental obj) {

        return false;
    }

    @Override
    public boolean delete(Rental obj) {

        return false;
    }
    
}
