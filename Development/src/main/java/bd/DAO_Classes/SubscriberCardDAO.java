package bd.DAO_Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriberCardDAO<SubscriberCard> extends DAO<SubscriberCard> {

    public SubscriberCardDAO(Connection conn) {
        super(conn);
    }

    @Override
    public SubscriberCard read(int id) {

        try{
            SubscriberCard subscriberCard;

            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM SubscriberCards WHERE subCardID ="+id);
            
            if(result.first()){

                double balance = result.getDouble("balance");
                int limitweek = result.getInt("limitWeek");

                subscriberCard = new SubscriberCard(balance,limitweek);
            }

            return subscriberCard;
            
        }
        catch (SQLException e) { e.printStackTrace(); }
        
    }

    @Override
    public boolean create(SubscriberCard obj) {

        return false;
    }

    @Override
    public boolean update(SubscriberCard obj) {

        return false;
    }

    @Override
    public boolean delete(SubscriberCard obj) {

        return false;
    }
    
}
