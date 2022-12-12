package bd;

import java.sql.Connection;

import bd.DAO_Classes.BluRayDAO;
import bd.DAO_Classes.CreditCardDAO;
import bd.DAO_Classes.DAO;
import bd.DAO_Classes.FilmsDAO;
import bd.DAO_Classes.QRCodeDAO;
import bd.DAO_Classes.RentalDAO;
import bd.DAO_Classes.SubscriberCardDAO;
import bd.DAO_Classes.SubscriberDAO;
import fc.Rental;
import fc.clients.Subscriber;
import fc.clients.cards.CreditCard;
import fc.clients.cards.SubscriberCard;
import fc.films.BluRay;
import fc.films.Film;
import fc.films.QRCode;

public class DAOFactory {

    protected static final Connection conn = DBConnection.getInstance();
    public static DAO<BluRay> getBluRayDAO(){ return new BluRayDAO(conn); }
    public static DAO<QRCode> getQRCodeDAO(){ return new QRCodeDAO(conn); }
    public static DAO<CreditCard> getCreditCardDAO(){ return new CreditCardDAO(conn); }
    public static DAO<Rental> getRentalDAO(){ return new RentalDAO(conn); }
    public static DAO<Film> getFilmsDAO(){ return new FilmsDAO(conn); }
    public static DAO<Subscriber> getSubscriberDAO(){ return new SubscriberDAO(conn); }
    public static DAO<SubscriberCard> getSubscriberCardDAO(){ return new SubscriberCardDAO(conn); }
    
}
