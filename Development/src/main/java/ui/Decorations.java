package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;


/**
 * Enumeration of resources’ paths.
 * @author MathysC
 *
 */
enum PATH {
	RESOURCES_PATH("./Development/src/main/resources/"),
	IMG(RESOURCES_PATH+"images/"),
	ICO(RESOURCES_PATH +"icons/"),
	SND(RESOURCES_PATH + "sounds/");
	
	private String path;
	
	PATH(String path) {
		this.path = path;
	}
	
    @Override
    public String toString() {
    	return path;
    }
}

/**
 * Enumeration of used formats.
 * @author MathysC
 *
 */
enum FORMAT {
	IMG(".png"),
	ICO(".ico"),
	SND(".wav");
	
	private String format;
	
	FORMAT(String fmt){
		this.format = fmt;
	}
	
	static int sizeICO() {
		return 16;
	}
	
    @Override
    public String toString() {
    	return format;
    }
    
}
/**
 * Enumerations of all parameters used.
 * 
 * @author MathysC
 * 
 */
public enum Decorations {
	
    /* Images */
	IMG_BUTTON("Test_Button"),
	IMG_FILM("Test_Film"),
  
	/* Fonts */
	FONT_BASIC("Helvetica"),
	FONT_PLACEHOLDER("Monospaced"),

    /* Sounds */
    SND_DING(PATH.SND + "Ding" + FORMAT.SND),
    SND_BELL(PATH.SND + "Bell" + FORMAT.SND),
    SND_STORERING(PATH.SND + "StoreRing" + FORMAT.SND);

   private String decoration;

    Decorations(String o) {
        this.decoration = o;
    }
    
    @Override
    public String toString() {
    	return decoration;
    }
    
    /**
     * Get named Image
     * @author MathysC
     *
     * @return ImageIcon Icon from named image.
     */
    public static ImageIcon getImg(String name) {
        return new ImageIcon(PATH.IMG + name + FORMAT.IMG);
    }

    /**
     * Get named Icon.
     * @author MathysC
     *
     * @return ImageIcon Image from named Icon.
     */
    public static Image getIco(String name) {
		try {
	    	BufferedImage image = new BufferedImage(FORMAT.sizeICO(), FORMAT.sizeICO(), BufferedImage.TYPE_INT_RGB);
	    	File file = new File(PATH.ICO + name + FORMAT.ICO);
	    	System.out.println(System.getProperty("user.dir"));
			image = ImageIO.read(file);
			return image;
		} catch (IOException e) {
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
     * @author MathysC
     *
     * @return Dimension the default Button dimension.
     */
    public static Dimension getDefaultButtonDimension() {
    	return new Dimension(100, 100);
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
            return new Font(this.decoration,style,size);
        else
            return null;
    }
}