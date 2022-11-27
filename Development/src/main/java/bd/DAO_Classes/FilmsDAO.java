package bd.DAO_Classes;

import fc.films.AgeRestriction;
import fc.films.Categories;
import fc.films.Film;
import fc.films.Support;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmsDAO<Film> extends DAO<Film>{
    
    public FilmsDAO(Connection conn)
    {
        super(conn);
    }

    @Override
    public Film read(int id) {
        
        try{
            Film film;

            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM Films JOIN FilmCategories WHERE filmID ="+id);
            ResultSet resultActorName = this.connect.createStatement().executeQuery("SELECT CONCAT(actorFirstName,' ',actorLastName) AS actorName FROM FilmsActors NATURAL JOIN Actors WHERE filmID ="+id);
            ResultSet resultCatName = this.connect.createStatement().executeQuery("SELECT catName FROM FilmsCategories NATURAL JOIN Categories WHERE filmID ="+id);
            ResultSet resultSuppID = this.connect.createStatement().executeQuery("SELECT supportFilmID FROM SupportFilms WHERE filmID="+id);

            if(result.first() && resultActorName.first() && resultCatName.first() && resultSuppID.first()){
                String title = result.getString("title");

                String synopsis = result.getString("synopsis");

                ArrayList<String> actors = new ArrayList<String>();
                while(resultActorName.next()){
                    actors.add(resultActorName.getString("actorName"));
                }

                int year = result.getInt("year");

                String FNDirector = result.getString("directorFirstName");

			    String LNDirector = result.getString("directorLastName");

                AgeRestriction restriction = AgeRestriction.valueOf(result.getString("restrictedAge"));

                ArrayList<Categories> categories = new ArrayList<Categories>();
                while(resultActorName.next()){
                    categories.add(Categories.valueOf(resultCatName.getString("catName")));
                }

                /*ArrayList<Integer> supports = new ArrayList<Integer>();
                while(resultActorName.next()){
                    supports.add(resultSuppID.getInt("supportFilmID"));
                }*/


                film = new Film(title,synopsis,actors,year,FNDirector,LNDirector,restriction,categories,supports);
            }

            return film;
            
        }
        catch (SQLException e) { e.printStackTrace(); }
        
    }

    @Override
    public boolean create(Film obj) {
        
        return false;
    }

    @Override
    public boolean update(Film obj) {
        
        return false;
    }

    @Override
    public boolean delete(Film obj) {
        
        return false;
    }
}