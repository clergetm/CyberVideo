package bd.DAO_Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fc.Rental;
import fc.clients.cards.Card;
import fc.films.BluRay;
import fc.films.Film;
import fc.films.QRCode;
import fc.films.StatesBluRay;
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
                Card card = Card.valueOf(result.getInt("supportCardID"));

                Support support = null;
                ResultSet resultSuppID = this.connect.createStatement().executeQuery("SELECT price, state FROM BluRays NATURAL JOIN WHERE supportFilmID="+result.getString("supportFilmID"));
                ResultSet resultSuppIDbis = this.connect.createStatement().executeQuery("SELECT link FROM QRCodes NATURAL JOIN WHERE supportFilmID="+result.getString("supportFilmID"));
                if(resultSuppID.first())
                    support = new BluRay(resultSuppID.getInt("price"),StatesBluRay.valueOf(resultSuppID.getString("state")));
                else if(resultSuppIDbis.first())
                    support = new QRCode(resultSuppIDbis.getString("link"));
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
