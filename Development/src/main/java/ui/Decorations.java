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
 * Enumeration of the screen size
 * @author Clarisse
 */
enum SCREEN {
	HEIGHT(864),
	WIDTH(1536),
	CURENT_HEIGHT((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()),
	CURENT_WIDTH((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth());
	
	private int length;

	SCREEN(int l){
		this.length = l;
	}

	public int getLength() {
		return this.length;
	}
}

/**
 * Enumeration of resources’ paths.
 * @author MathysC
 *
 */
enum PATH {
	IMG("/images/"),
	ICO("/icons/"),
	SND("/sounds/");
	
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
    FONT_PLACEHOLDER("Monospaced");

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
        return new ImageIcon(getImgPath(name));
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
	    	File file = new File(getIcoPath(name));
	    	
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
    
    /**
     * 
     * @author MathysC
     *
     * @param name the name of the file
     * @return the path of an icon. 
     */
    public static String getIcoPath(String name) {
	return Decorations.class.getResource(PATH.ICO + name + FORMAT.ICO).getPath();
    }
    
    /**
     * 
     * @author MathysC
     *
     * @param name the name of the file
     * @return the path of an image. 
     */
    public static String getImgPath(String name) {
	return Decorations.class.getResource(PATH.IMG + name + FORMAT.IMG).getPath();
    }
    
    /**
     * 
     * @author MathysC
     *
     * @param name the name of the file
     * @return the path of a sound file. 
     */
    public static String getSndPath(String name) {
	return Decorations.class.getResource(PATH.SND + name + FORMAT.SND).getPath();
    } 

		/**
	 * Convert dimensions with the size of screen, based on a fixed size screen
	 * @author Clarisse
	 * 
	 * @param dim the dimension of an object to be resize
	 * @return the dimension based on the current screen size
	 */
	public static Dimension sizeConverter(Dimension dim) {
		return new Dimension((dim.width*SCREEN.CURENT_WIDTH.getLength())/SCREEN.WIDTH.getLength(),
				(dim.height*SCREEN.CURENT_HEIGHT.getLength())/SCREEN.HEIGHT.getLength());
	}
}