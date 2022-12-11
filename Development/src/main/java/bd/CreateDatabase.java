package bd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

//Run with this command : java -classpath projectpath\CyberVideo\Development\src\main\java\bd\ojdbc11.jar CreateDatabase.java

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
      File createTable = new File("createTable.sql");
      File max5CardsTrigger = new File("testTrigger/test_trigger_max_5_cards.sql");
      File creditAfter20RentalsTrigger = new File("testTrigger/test_trigger_credit_after_20_rentals.sql");
      File max1YearHistoric = new File("testTrigger/test_trigger_max_1_year_historic.sql");
      File max1YearRentals = new File("testTrigger/test_trigger_max_1_year_rentals.sql");
      File stolenAfter30Days = new File("testTrigger/test_trigger_stolen.sql");
      File deleteTablesTrigger1 = new File("testTrigger/deleteTablesTrigger1.sql");
      File deleteTablesTrigger2 = new File("testTrigger/deleteTablesTrigger2.sql");
      File deleteTablesTrigger3 = new File("testTrigger/deleteTablesTrigger3.sql");
      File deleteTablesTrigger4 = new File("testTrigger/deleteTablesTrigger4.sql");
      File deleteTablesTrigger5 = new File("testTrigger/deleteTablesTrigger5.sql");

      //Loading the JDBC Driver class
      Class.forName("oracle.jdbc.driver.OracleDriver");

      //Importing connection logs from oracleLogs.properties file
      Properties dbProps = new Properties();
      dbProps.load(new FileInputStream("oracleLogs.properties"));
      
      String url = dbProps.getProperty("URL");
      String user = dbProps.getProperty("User");
      String password = dbProps.getProperty("Password");

      //Creating the connection object and the statement
      Connection conn = DriverManager.getConnection(url,user,password); 
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

      //------------------------INSERTING VALUES AND DISPLAY TABLE TEST WITH THE DATABASE CREATED------------------------//
      System.out.println("\t\tINSERTING VALUES AND DISPLAY TABLE TEST WITH THE DATABASE CREATED\n");
      stmt.executeUpdate("INSERT INTO Films Values(10,'Les dents de la mer',1975,'Histoire de requins...','Steven','Spielberg','M16')");
      displayTable(stmt, "SELECT * FROM Films");
      //-----------------------------------------------END TEST----------------------------------------------------------//

      //--------------------------------TRIGGERS TESTING---------------------------//
      System.out.println("\tTRIGGERS TESTING\n");
      System.out.println("\t\tMax 5 cards Trigger TEST\n");
      displayTable(stmt, "SELECT count(*) FROM OwnedCards WHERE subID = 1");
      executeSqlScript(conn,max5CardsTrigger);
      displayTable(stmt, "SELECT count(*) FROM OwnedCards WHERE subID = 1");
      executeSqlScript(conn,deleteTablesTrigger1);

      System.out.println("\t\tCredit After 20 Rentals Trigger TEST\n");
      executeSqlScript(conn,creditAfter20RentalsTrigger);
      displayTable(stmt, "SELECT balance FROM SubscriberCards");
      executeSqlScript(conn,deleteTablesTrigger2);

      System.out.println("\t\tMax 1 year historic of credit cards Trigger TEST\n");
      executeSqlScript(conn,max1YearHistoric);
      displayTable(stmt, "SELECT * FROM HistoricCreditCards");
      executeSqlScript(conn,deleteTablesTrigger3);

      System.out.println("\t\tMax 1 year rentals Trigger TEST\n");
      executeSqlScript(conn,max1YearRentals);
      displayTable(stmt, "SELECT * FROM Rentals");
      executeSqlScript(conn,deleteTablesTrigger4);

      System.out.println("\t\tStolen after 30 days TEST\n");
      executeSqlScript(conn,stolenAfter30Days);
      displayTable(stmt, "SELECT * FROM Rentals NATURAL JOIN Blurays");
      executeSqlScript(conn,deleteTablesTrigger5);
      //-------------------------------END TRIGGERS TEST----------------------------//

      //------------------------INSERTING Films from IMDB API-----------------------//
      executeSqlScript(conn,createTable);
      ImdbAPI imdb = new ImdbAPI(conn);
      imdb.getData();
      displayTable(stmt, "SELECT * FROM Films");
      displayTable(stmt, "SELECT * FROM Actors");
      //---------------------------------------------------------------------------//

      System.out.println("Database successfully created...");

      //-------------------------------DAO Initialisation----------------------------//
      
      //MainDAO<Films> daoFilms = new FilmsDAO(conn);

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
