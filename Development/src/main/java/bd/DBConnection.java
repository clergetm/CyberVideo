package bd;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import bd.utils.Path;

/**
 * Singleton of connection to the Database.
 * @author MathysC
 */
public class DBConnection {

    private static Connection instance = null;
    
    /**
     * Private default constructor of {@code DBConnection}
     * @author MathysC
     *
     */
    private DBConnection() {
	try {
	    Properties dbProps = new Properties();
	    FileInputStream fis = new FileInputStream(Path.CREDENTIALS.getPath("oracleLogs.properties")); 
	    dbProps.load(fis);
	    connect(dbProps.getProperty("URL"),
		    dbProps.getProperty("User"),
		    dbProps.getProperty("Password")); 

	} catch (IOException | SQLException e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * Connect the instance.
     * @author MathysC
     *
     * @param URL The URL of the connection
     * @param user The user.
     * @param pwd The password of the user.
     * @throws SQLException
     */
    private static void connect(String URL, String user, String pwd) throws SQLException {
	instance = DriverManager.getConnection(URL, user, pwd);
    }
    /**
     * Getter of the one and only instance of {@code DBConnection}.
     * @author MathysC
     *
     * @return The Database Connection.
     */
    public static Connection getInstance() {
	if(instance == null) {
	    new DBConnection();
	}
	return instance;
    }
}
