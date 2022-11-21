package bd.DAO_Classes;

import fc.Films.BluRay;
import fc.Films.Film;
import fc.Films.StatesBluRay;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BluRayDAO<BluRay> extends DAO<BluRay>{
    
    public BluRayDAO(Connection conn)
    {
        super(conn);
    }

    @Override
    public BluRay read(int id) {
        
        try{

            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM Films NATURAL JOIN BluRays WHERE filmID ="+id);
            
            if(result.first()){

                double price = result.getDouble("price");
                StatesBluRay state = StatesBluRay.valueOf(result.getString("state"));

                //We use the filmDAO class to instanciate a Film linked with the BluRay
                FilmsDAO<Film> filmDAO= new FilmsDAO<Film>(this.connect);
                Film film = (Film) filmDAO.read(id);

                BluRay bluray = new BluRay(price,state,film.getTitle(),film.getSynopsis(),film.getActors(),film.getFNDirector(),film.getLNDirector(),film.getRestriction(),film.getCategories());
            }
            
        }
        catch (SQLException e) { e.printStackTrace(); }
        
        return bluray;
    }

    @Override
    public boolean create(BluRay obj) {
        
        return false;
    }

    @Override
    public boolean update(BluRay obj) {
        
        return false;
    }

    @Override
    public boolean delete(BluRay obj) {
        
        return false;
    }
}

