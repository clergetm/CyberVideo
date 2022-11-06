package ui.Colors;


/**
 * ColorTheme is an interface that implements two methods {@code setLight} and {@code setDark} 
 * used to change the theme the UI.
 * @author MathysC
 * @see Colors.MD
 */
public interface ColorTheme {
	
	/**
	 * Set the Light Theme.
	 * @author MathysC
	 *
	 */
	void setLight();
	
	/**
	 * Set the Dark Theme.
	 * @author MathysC
	 *
	 */
	void setDark();
}
