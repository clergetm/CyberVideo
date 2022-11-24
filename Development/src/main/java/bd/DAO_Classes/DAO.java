package bd.DAO_Classes;

import java.sql.Connection;

/**
 * @author EvanP
 * Class that initializes the DAO to link database and functional core.
 */
public abstract class DAO<T> {
    
    protected Connection connect = null;

    public DAO(Connection conn)
    { 
        this.connect = conn; 
    } 

    /**
     * Read an object with his ID
     * @param id
     * @return
     */
    public abstract T read(int id);
    
    /**
     * Create entry in database
     * @param obj
     */
    public abstract boolean create(T obj);
    
    /**
     * Update entry in database
     * @param obj
     */
    public abstract boolean update(T obj);
    
    /**
     * Delete entry in database
     * @param obj
     */
    public abstract boolean delete(T obj);
}
