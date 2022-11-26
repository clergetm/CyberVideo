package bd.DAO_Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import fc.films.Film;

public class RentalDAO<Rental> extends DAO<Rental> {

    public RentalDAO(Connection conn) {
        super(conn);
    }

    @Override
    public Rental read(int id) {

        try{
            Rental rental;

            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM Rentals NATURAL JOIN BluRays NATURAL JOIN SupportFilms WHERE rentalsID ="+id);
            
            if(result.first()){

                int tarif = result.getInt("price");
                FilmsDAO<Film> filmDAO = new FilmsDAO<Film>(connect);
                Film film = filmDAO.read(result.getInt("filmID"));

                rental = new Rental(tarif,film);
            }

            return rental;
            
        }
        catch (SQLException e) { e.printStackTrace(); }
        
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
