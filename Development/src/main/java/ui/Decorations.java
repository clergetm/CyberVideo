package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
    // Icons
    IMG_BUTTON("Test_Button"),
    IMG_FILM("Test_Film"), // TODO  
    
	// Fonts
	FONT_BASIC("Helvetica"),
	FONT_PLACEHOLDER("Monospaced");

	private static String PREFIXPATH = "resources/",
    		PREFIXIMG = PREFIXPATH + "/images/",
    		PREFIXICO = PREFIXPATH + "/icons/",
    		
    		FORMATIMAGE = ".png",
    		FORMATICON = ".ico";
    private static int ICOH = 16;
    
    private Object decoration;

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
     * Get the enumerated Image
     * @author MathysC
     *
     * @return ImageIcon if the enumerated element name’s start with "IMG_";
     */
    public ImageIcon getImg() {
        if (this.name().startsWith("IMG_"))
            return new ImageIcon(PREFIXIMG + (String) this.decoration + FORMATIMAGE);
        else
            return null;
    }

    /**
     * Get named Image
     * @author MathysC
     *
     * @return ImageIcon Icon from named image.
     */
    public static ImageIcon getImg(String name) {
        return new ImageIcon(PREFIXIMG + name + FORMATIMAGE);

    }

    /**
     * Get named Icon.
     * @author MathysC
     *
     * @return ImageIcon Image from named Icon.
     */
    public static Image getIco(String name) {
		try {
	    	BufferedImage image = new BufferedImage(ICOH, ICOH, BufferedImage.TYPE_INT_RGB);
	    	File file = new File(PREFIXICO + name + FORMATICON);
	    	
			image = ImageIO.read(file);
			return image;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * Get the default Border
     * @author MathysC
     *
     * @return EmptyBorder a border used as a margin.
     */
    public static EmptyBorder getDefaultBorder() {
            return new EmptyBorder(10, 10, 10, 10);
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