package bd;

import java.sql.*;

public class CreateDatabase
{
  public static void main(String args[])
  {
    try
    {
      //Loading the JDBC Driver class
      Class.forName("oracle.jdbc.driver.OracleDriver");

      //Creating the connection object and the statement
      Connection conn = DriverManager.getConnection(
      "jdbc:oracle:thin:@localhost:1521:xe","system","oracle"); 
      Statement stmt = conn.createStatement();

      //Executing the DB creation request
      System.out.println("Création de base de données...");
      stmt.executeUpdate("CREATE DATABASE cybervideo");
      System.out.println("Base de données crée avec succés...");

      //Closing the connection object
      conn.close();
    }
    catch(Exception e){ 
      System.out.println(e);
    }
  }
}
