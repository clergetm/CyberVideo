package bd.DAO_Classes;

import fc.films.QRCode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QRCodeDAO extends DAO<QRCode>{
    
    public QRCodeDAO(Connection conn)
    {
        super(conn);
    }

    @Override
    public QRCode read(int id) {
        
        QRCode qrcode = null;
        try{
            

            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM QRCodes NATURAL JOIN QRCodes WHERE filmID ="+id);
            
            if(result.first()){

                String link = result.getString("link");

                qrcode = new QRCode(link);
            }
            
        }
        catch (SQLException e) { e.printStackTrace(); }
        return qrcode;
        
    }

    @Override
    public boolean create(QRCode obj) {
        
        return false;
    }

    @Override
    public boolean update(QRCode obj) {
        
        return false;
    }

    @Override
    public boolean delete(QRCode obj) {
        
        return false;
    }
}
