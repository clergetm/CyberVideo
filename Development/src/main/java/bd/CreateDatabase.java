package bd;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

import bd.DAO_Classes.DAO;
import bd.DAO_Classes.FilmsDAO;
import bd.utils.Path;
import fc.films.Film;

/**
 * @author EvanP
 * Class that initializes the Cybervideo database.
 */
public class CreateDatabase
{

  /**
   * Create the connection with JDBC and initialize the whole database.
   * @author EvanP
   * 
   * @param args
   */
  public static void main(String args[])
  {
    try
    { 
      File createTable = new File(Path.SCRIPTS.getPath("createTable.sql"));
      
      //Loading the JDBC Driver class
      Class.forName("oracle.jdbc.driver.OracleDriver");


      //Creating the connection object and the statement
      Connection conn = DBConnection.getInstance(); 
      Statement stmt = conn.createStatement();

      //Executing the DB creation request
      System.out.println("Creation of the database\n");

      //Executing createTable.sql file
      System.out.println("\tCreation of tables\n");
      executeSqlScript(conn,createTable);
      System.out.println("\tTables created\n");

      //Executing Trigger
      System.out.println("\tCreation of triggers\n");
      stmt.executeUpdate("CREATE OR REPLACE TRIGGER max_5_cards_subscriber BEFORE INSERT ON OwnedCards FOR EACH ROW DECLARE nbCards INTEGER; BEGIN SELECT COUNT(*)INTO nbCards FROM OwnedCards WHERE subID = :new.subID; IF (nbCards >= 5) THEN RAISE_APPLICATION_ERROR(-20001,'A subscriber cannot have more than 5 subscriber cards'); END IF; END;");
      stmt.executeUpdate("CREATE OR REPLACE TRIGGER credit_after_20_rentals BEFORE INSERT ON Rentals FOR EACH ROW DECLARE nbRented INTEGER; BEGIN SELECT COUNT(*) INTO nbRented FROM Rentals WHERE supportCardID = :new.supportCardID; IF(nbRented >= 19) THEN UPDATE SubscriberCards SET balance = balance + 10 WHERE supportCardID = :new.supportCardID; END IF; END;");
      stmt.executeUpdate("CREATE OR REPLACE TRIGGER max_1_year_historic AFTER UPDATE ON HistoricCreditCards BEGIN DELETE FROM HistoricCreditCards WHERE (SYSDATE-actionDate) > 365; END;");
      stmt.executeUpdate("CREATE OR REPLACE TRIGGER max_1_year_location AFTER UPDATE ON Rentals BEGIN DELETE FROM Rentals WHERE (SYSDATE - beginDate) > 365; END;");
      stmt.executeUpdate("CREATE OR REPLACE TRIGGER stolen_after_30_days AFTER UPDATE ON Rentals BEGIN UPDATE Blurays SET state = 'STOLEN' WHERE blurayID IN (SELECT blurayID FROM Blurays NATURAL JOIN Rentals WHERE (SYSDATE - beginDate) > 30); END;");
      System.out.println("\tTriggers created\n");


      //------------------------INSERTING Films from IMDB API-----------------------//
      stmt.executeUpdate("INSERT INTO Categories Values(1,'HORROR')");
      stmt.executeUpdate("INSERT INTO Categories Values(2,'COMEDY')");
      stmt.executeUpdate("INSERT INTO Categories Values(3,'ROMANTIC')");
      stmt.executeUpdate("INSERT INTO Categories Values(4,'ACTION')");

      displayTable(stmt, "SELECT * FROM Categories");
      ImdbAPI imdb = new ImdbAPI(conn);
      imdb.getData();
      System.out.println("\tFILMS\n");
      displayTable(stmt, "SELECT * FROM Films");
      System.out.println("\tActors\n");
      displayTable(stmt, "SELECT * FROM Actors");
      System.out.println("\tFilmActors\n");
      displayTable(stmt, "SELECT * FROM FilmsActors");
      System.out.println("\tCategories\n");
      displayTable(stmt, "SELECT * FROM Categories");
      System.out.println("\tFILMSCategories\n");
      displayTable(stmt, "SELECT * FROM FilmsCategories");
      System.out.println("\tSupportFilms\n");
      displayTable(stmt, "SELECT * FROM SupportFilms");
      //---------------------------------------------------------------------------//

      System.out.println("Database successfully created...");

      //-------------------------------DAO Initialisation----------------------------//
      
      DAO<Film> daoFilms = new FilmsDAO(conn);
      Film film = daoFilms.read(1);
      System.out.println(film.getTitle());
      System.out.println(film.getYear());
      System.out.println(film.getFNDirector());
      System.out.println(film.getLNDirector());
      System.out.println(film.getSynopsis());

      //Closing the connection object
      conn.close();
    }
    catch(Exception e){ 
      System.out.println(e);
    }
  }

  /**
   * Display the chosen table.
   * @author EvanP
   * 
   * @param stmt the JDBC connection Statement
   * @param query the SQL query to execute
   */
  public static void displayTable(Statement stmt, String query) throws SQLException{
    ResultSet res = stmt.executeQuery(query);
    ResultSetMetaData rsmd = res.getMetaData();
    int nbCols = rsmd.getColumnCount();
    while (res.next()) {
      for (int i = 1; i <= nbCols; i++)
        System.out.print(res.getString(i) + " ");
      System.out.println();
    }
    System.out.println();
  }

  /**
   * Execute the whole SQL File specified.
   * @author EvanP
   * 
   * @param conn the JDBC connection object
   * @param inputFile the executed SQL File.
   */
  public static void executeSqlScript(Connection conn, File inputFile) { 
    // Delimiter 
    String delimiter = ";"; 
    // Create scanner 
    Scanner scanner; 
    try { 
      scanner = new Scanner(inputFile).useDelimiter(delimiter); 
    } catch (FileNotFoundException e1) { 
      e1.printStackTrace(); 
      return; 
    } 
    // Loop through the SQL file statements 
    Statement currentStatement = null; 
    while(scanner.hasNext()) { 
      // Get statement 
      String rawStatement = scanner.next();
      try { 
        // Execute statement 
        currentStatement = conn.createStatement(); 
        currentStatement.execute(rawStatement); 
      } catch (SQLException e) { 
        e.printStackTrace(); 
      } finally { 
        // Release resources 
        if (currentStatement != null) { 
          try { 
            currentStatement.close(); 
          } catch (SQLException e) { 
            e.printStackTrace(); 
          } 
        } 
        currentStatement = null; 
      } 
    } 
    scanner.close();
  }
  
}
