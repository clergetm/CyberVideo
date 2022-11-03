package ui;

import java.awt.Dimension;
import java.awt.Font;

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
public enum Decorations {

    // Dimensions
    DIM_BUTTON(new Dimension(100, 100)),
    DIM_LOGO(new Dimension(200, 150)),

    // Icons
    ICON_LOGO("Light_Logo"),
    ICON_BUTTON("Test_Button"),
    ICON_CURRENTFR("CurrentFR"),
    ICON_CURRENTEN("CurrentEN"),
    ICON_CURRENTLIGHT("CurrentLight"),
    ICON_CURRENTDARK("CurrentDark"),
    ICON_BANNER("Test_Banner"), // TODO
    ICON_FILM("Test_Film"), // TODO

    // Borders
    BORDER_DEFAULT(new EmptyBorder(10, 10, 10, 10)),

	// Fonts
	FONT_BASIC("Helvetica"),
	FONT_PLACEHOLDER("Monospaced");
    private Object decoration;
    private static String PREFIXPATH = "resources/";
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
        if (this.name().startsWith("DIM_"))
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
        if (this.name().startsWith("ICON_"))
            return new ImageIcon(PREFIXPATH + (String) this.decoration + FORMATIMAGE);
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
        if (this.name().startsWith("BORDER_"))
            return (Border) this.decoration;
        else
            return null;
    }
    
    
    /**
     * Get the enumerated Font
     * @author MathysC
     *
     * @param style The style of the font.
     * @param size	The size of the font.
     * @return Font if the enumerated element name’s start with "FONT_";
     */
    public Font getFont(int style, int size) {
        if (this.name().startsWith("FONT_"))
            return new Font(this.decoration.toString(),style,size);
        else
            return null;
    }
}