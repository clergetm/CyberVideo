package bd.DAO_Classes;

import fc.films.BluRay;
import fc.films.StatesBluRay;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;;

public class BluRayDAO extends DAO<BluRay>{
    
    public BluRayDAO(Connection conn)
    {
        super(conn);
    }

    @Override
    public BluRay read(int id) {
        
        BluRay bluray = null;
        try{
            

            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM Films NATURAL JOIN BluRays WHERE filmID ="+id);
            
            if(result.first()){

                double price = result.getDouble("price");
                StatesBluRay state = StatesBluRay.valueOf(result.getString("state"));

                bluray = new BluRay(price,state);
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

