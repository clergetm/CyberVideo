package bd;

/**
 * Singleton of connection to the Database.
 * @author MathysC
 */
public class DBConnection {

    private static DBConnection instance = null;
    
    /**
     * Private default constructor of {@code DBConnection}
     * @author MathysC
     *
     */
    private DBConnection() {
	
    }
    
    /**
     * Getter of the one and only instance of {@code DBConnection}.
     * @author MathysC
     *
     * @return The Database Connection.
     */
    public static DBConnection getInstance() {
	if(instance == null) {
	    instance = new DBConnection();
	}
	return instance;
    }
}
