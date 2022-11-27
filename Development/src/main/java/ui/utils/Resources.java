package ui.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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

    PATH(String pth) {
	this.path = pth;
    }

    @Override
    public String toString() {
	return path;
    }
}

/**
 * Enumeration of resource formats.
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

    @Override
    public String toString() {
	return format;
    }

}

/**
 * Enumeration of all resources.
 * @author MathysC
 */
public enum Resources {
    
    /* Images */
    IMG_BUTTON("Test_Button"),
    IMG_FILM("Test_Film");
    
    @SuppressWarnings("unused")
    private String resource;
    
    Resources(String rsc){
	this.resource = rsc;
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
	    BufferedImage image;
	    File file = new File(getIcoPath(name));
	    image = ImageIO.read(file);
	    return image;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
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
}
