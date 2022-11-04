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
      File file = new File("createTable.sql");
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
      System.out.println("Création de base de données...");

      executeSqlScript(conn,file);

      //------------------------INSERTING VALUES AND DISPLAY TABLE TEST WITH THE DATABASE CREATED------------------------//
      stmt.executeUpdate("INSERT INTO Films Values(1,'Les dents de la mer','Histoire de requins...','Steven','Spielberg','M16')");

      displayTable(stmt, "SELECT * FROM Films");
      //-----------------------------------------------END TEST----------------------------------------------------------//

      System.out.println("Base de données crée avec succés...");

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
