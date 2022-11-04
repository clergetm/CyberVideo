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

    // Dimensions
    DIM_BUTTON(new Dimension(100, 100)),
    DIM_LOGO(new Dimension(200, 150)),

    // Icons
    IMG_LOGO("Light_Logo"),
    IMG_BUTTON("Test_Button"),
    IMG_CURRENTFR("CurrentFR"),
    IMG_CURRENTEN("CurrentEN"),
    IMG_CURRENTLIGHT("CurrentLight"),
    IMG_CURRENTDARK("CurrentDark"),
    IMG_BANNERIT("BannerIT"),
    IMG_BANNERPOLICE("BannerPOLICE"),
    IMG_BANNERDESERT("BannerDESERT"),
    IMG_FILM("Test_Film"), // TODO
    ICO_KEYBOARD("edit"),
    ICO_CYBERVIDEO("Logo_ico"),
    // Borders
    BORDER_DEFAULT(new EmptyBorder(10, 10, 10, 10)),

	// Fonts
	FONT_BASIC("Helvetica"),
	FONT_PLACEHOLDER("Monospaced");
    private Object decoration;
    private static String PREFIXPATH = "resources/",
    		PREFIXIMG = PREFIXPATH + "/images/",
    		PREFIXICO = PREFIXPATH + "/icons/",
    		
    		FORMATIMAGE = ".png",
    		FORMATICON = ".ico";
    private static int ICOH = 16;
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
     * Get the enumerated Icon
     * @author MathysC
     *
     * @return ImageIcon if the enumerated element name’s start with "ICO_";
     */
    public Image getIco() {
    	if (this.name().startsWith("ICO_")) {
    		try {
    	    	BufferedImage image = new BufferedImage(ICOH, ICOH, BufferedImage.TYPE_INT_RGB);
    	    	File file = new File(PREFIXICO + (String) this.decoration + FORMATICON);
    	    	
				image = ImageIO.read(file);
				return image;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
		}   
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