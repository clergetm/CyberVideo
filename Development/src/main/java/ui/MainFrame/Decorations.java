package ui.MainFrame;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * Enumerations of all parameters used.
 * 
 * TODO: Find a better way to represent this ugly code.
 * 
 * @author MathysC
 * 
 */
enum Decorations {

	// Dimensions
	DIM_BUTTON(new Dimension(100,100)),
	DIM_LOGO(new Dimension(200,150)),
	
	// Icons
	ICON_LOGO("Test_Logo"),					// TODO
	ICON_BUTTON("Test_Button"),				// TODO
	ICON_BANNER("Test_Banner"),				// TODO
	
	// Borders
	BORDER_DEFAULT(new EmptyBorder(10, 10, 10, 10));
	
	private Object decoration;
	private static String PREFIXPATH = "Resources/";
	private static String FORMATIMAGE = ".png";
	
	
	Decorations(Object o) {
		this.decoration = o; 
	}
	
	/**
	 * Get the enumerated Dimension
	 * @author MathysC
	 *
	 * @return Dimension if the enumerated element name’s start with "DIM_";
	 */
	public Dimension getDimension() {
		if(this.name().startsWith("DIM_"))
				return (Dimension) this.decoration;
		else
			return null;
	}
	
	/**
	 * Get the enumerated Icon
	 * @author MathysC
	 *
	 * @return ImageIcon if the enumerated element name’s start with "ICON_";
	 */
	public ImageIcon getIcon() {
		if(this.name().startsWith("ICON_"))
			return new ImageIcon(PREFIXPATH + (String)this.decoration + FORMATIMAGE);
		else
			return null;
	}
	
	/**
	 * Get the enumerated Border
	 * @author MathysC
	 *
	 * @return Border if the enumerated element name’s start with "BORDER_";
	 */
	public Border getBorder() {
		if(this.name().startsWith("BORDER_"))
			return (Border) this.decoration;
		else
			return null;
	}
}
