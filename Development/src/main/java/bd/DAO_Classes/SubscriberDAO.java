package bd.DAO_Classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import fc.clients.Subscriber;
import ui.utils.observer.colortheme.palettes.Dark;

public class SubscriberDAO<Subscriber> extends DAO {

    @Override
    public Object read(int id) {

        

        try{
            Subscriber subscriber;

            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM Subscribers WHERE subID ="+id);
            
            if(result.first()){

                String firstName = result.getString("subFirstName");
                String lastName = result.getString("subLastName");
                Date birthDate = result.getDate("birthDate");

                subscriber = new Subscriber(firstName,lastName,birthDate);
            }

            return subscriber;
            
        }
        catch (SQLException e) { e.printStackTrace(); }
        
    }

    @Override
    public boolean create(Object obj) {

        return false;
    }

    @Override
    public boolean update(Object obj) {

        return false;
    }

    @Override
    public boolean delete(Object obj) {

        return false;
    }
    
}
