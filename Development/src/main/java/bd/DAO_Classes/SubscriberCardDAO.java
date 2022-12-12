package bd.DAO_Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import fc.clients.cards.SubscriberCard;

public class SubscriberCardDAO extends DAO<SubscriberCard> {

    public SubscriberCardDAO(Connection conn) {
        super(conn);
    }

    @Override
    public SubscriberCard read(int id) {
        SubscriberCard subscriberCard = null;
        try{
            

            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM SubscriberCards WHERE subCardID ="+id);
            
            if(result.first()){

                double balance = result.getDouble("balance");
                int limitweek = result.getInt("limitWeek");
                int supportCardID = result.getInt("supportCardID");

                subscriberCard = new SubscriberCard(supportCardID,balance,limitweek);
            }
            
        }
        catch (SQLException e) { e.printStackTrace(); }
        return subscriberCard;
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
