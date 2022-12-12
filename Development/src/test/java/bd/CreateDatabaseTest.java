package bd;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import bd.DAO_Classes.DAO;
import bd.utils.Path;
import fc.films.Film;
import utils.LifecycleLoggerTest;

class CreateDatabaseTest implements LifecycleLoggerTest {
    File max5CardsTrigger = new File(Path.TRIGGER_TESTS.getPath("test_trigger_max_5_cards.sql"));
    File creditAfter20RentalsTrigger = new File(Path.TRIGGER_TESTS.getPath("test_trigger_credit_after_20_rentals.sql"));
    File max1YearHistoric = new File(Path.TRIGGER_TESTS.getPath("test_trigger_max_1_year_historic.sql"));
    File max1YearRentals = new File(Path.TRIGGER_TESTS.getPath("test_trigger_max_1_year_rentals.sql"));
    File stolenAfter30Days = new File(Path.TRIGGER_TESTS.getPath("test_trigger_stolen.sql"));
    File deleteTablesTrigger1 = new File(Path.TRIGGER_TESTS.getPath("deleteTablesTrigger1.sql"));
    File deleteTablesTrigger2 = new File(Path.TRIGGER_TESTS.getPath("deleteTablesTrigger2.sql"));
    File deleteTablesTrigger3 = new File(Path.TRIGGER_TESTS.getPath("deleteTablesTrigger3.sql"));
    File deleteTablesTrigger4 = new File(Path.TRIGGER_TESTS.getPath("deleteTablesTrigger4.sql"));
    File deleteTablesTrigger5 = new File(Path.TRIGGER_TESTS.getPath("deleteTablesTrigger5.sql"));
    Connection conn;
    Statement stmt;

    
    @BeforeAll
    public void setUp(TestInfo testInfo) {
        try {
            conn = DBConnection.getInstance();
	    stmt = conn.createStatement();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    @Test
    void InsertTest() {
	try {
	    //------------------------INSERTING VALUES AND DISPLAY TABLE TEST WITH THE DATABASE CREATED------------------------//
	    System.out.println("\t\tINSERTING VALUES AND DISPLAY TABLE TEST WITH THE DATABASE CREATED\n");
	    stmt.executeUpdate("INSERT INTO Films Values(10,'Les dents de la mer',1975,'Histoire de requins...','Steven','Spielberg','M16')");
	    CreateDatabase.displayTable(stmt, "SELECT * FROM Films");
	    //-----------------------------------------------END TEST----------------------------------------------------------//
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    @Test
    void LimitSubCardTriggerTest() {
	try {
	    System.out.println("\t\tMax 5 cards Trigger TEST\n");
	    CreateDatabase.displayTable(stmt, "SELECT count(*) FROM OwnedCards WHERE subID = 1");
	    CreateDatabase.executeSqlScript(conn,max5CardsTrigger);
	    CreateDatabase.displayTable(stmt, "SELECT count(*) FROM OwnedCards WHERE subID = 1");
		CreateDatabase.executeSqlScript(conn,deleteTablesTrigger1);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    @Test
    void CreditAfter20RentalsTriggerTest() {
	try {
	    System.out.println("\t\tCredit After 20 Rentals Trigger TEST\n");
	    CreateDatabase.executeSqlScript(conn,creditAfter20RentalsTrigger);
	    CreateDatabase.displayTable(stmt, "SELECT balance FROM SubscriberCards");
	    CreateDatabase.executeSqlScript(conn,deleteTablesTrigger2);	
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    @Test
    void CreditHistoricTriggerTest() {
	try {
	System.out.println("\t\tMax 1 year historic of credit cards Trigger TEST\n");
	CreateDatabase.executeSqlScript(conn,max1YearHistoric);
	CreateDatabase.displayTable(stmt, "SELECT * FROM HistoricCreditCards");
	CreateDatabase.executeSqlScript(conn,deleteTablesTrigger3);
    } catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    @Test
    void YearRentalTriggerTest() {
	try {
	System.out.println("\t\tMax 1 year rentals Trigger TEST\n");
	CreateDatabase.executeSqlScript(conn,max1YearRentals);
	CreateDatabase.displayTable(stmt, "SELECT * FROM Rentals");
	CreateDatabase.executeSqlScript(conn,deleteTablesTrigger4);
    } catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    @Test
    void StolenTriggerTest() {
	try {
	    System.out.println("\t\tStolen after 30 days TEST\n");
	    CreateDatabase.executeSqlScript(conn,stolenAfter30Days);
	    CreateDatabase.displayTable(stmt, "SELECT * FROM Rentals NATURAL JOIN Blurays");
	    CreateDatabase.executeSqlScript(conn,deleteTablesTrigger5);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

	@Test
    void DAOTest() {
		DAO<Film> daoFilms = DAOFactory.getFilmsDAO();
      	for(int i=1;i<10;i++){
        	Film film = daoFilms.read(1);
        	System.out.println("-TITLE:"+film.getTitle()+" -YEAR:"+film.getYear()+" -DIRECTOR:"+film.getFNDirector()+film.getLNDirector()+" -SYNOPSIS:"+film.getSynopsis());
		}
	}

}
