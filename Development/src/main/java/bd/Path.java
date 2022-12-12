package bd;

/**
 * Enumerations of Path used in database related classes.
 * @author MathysC
 *
 */
public enum Path {
   RSC("databases/"),
   SCRIPTS(RSC+"scripts/"),
   TRIGGER_TESTS(SCRIPTS+"trigger_tests/"),
   CREDENTIALS(RSC+"credentials/");

    private String pth;
    
    /**
     * Default Constructor of {@code Path}
     * @author MathysC
     *
     * @param string the path.
     */
    Path(String string) {
	this.pth = string;
    }

    /**
     * Get the path of the given fileName.
     * @author MathysC
     *
     * @param fileName The file to found.
     * @return The path to the file. null if not found.
     */
    public String getPath(String fileName) {
	return Path.class.getResource(this.pth+fileName).getFile();
    }
}
