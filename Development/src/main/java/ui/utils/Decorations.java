package ui.utils;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Enumeration of the screen size
 * @author Clarisse
 */
enum SCREEN {
    HEIGHT(864),
    WIDTH(1536),
    CURRENT_HEIGHT((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()),
    CURRENT_WIDTH((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth());
	
    private int length;

    SCREEN(int lgt){
	this.length = lgt;
    }

    public int getLength() {
	return this.length;
    }
}


/**
 * Enumerations of all parameters used.
 * 
 * @author MathysC
 * 
 */
public enum Decorations {
  
    /* Fonts */
    FONT_BASIC("Helvetica"),
    FONT_PLACEHOLDER("Monospaced");

    private String decoration;

    Decorations(String dcrt) {
        this.decoration = dcrt;
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
     * Convert dimensions with the size of screen, based on a fixed size screen
     * @author Clarisse
     * 
     * @param dim the dimension of an object to be resize
     * @return the dimension based on the current screen size
     */
    public static Dimension sizeConverter(Dimension dim) {
	return new Dimension((dim.width*SCREEN.CURRENT_WIDTH.getLength())/SCREEN.WIDTH.getLength(),
		(dim.height*SCREEN.CURRENT_HEIGHT.getLength())/SCREEN.HEIGHT.getLength());
    }
    
    /**
     * Set the placeholder of a JTextField.
     * @author MathysC
     *
     * @param tf The JTextField component to set.
     * @param prompt The text to put as a placeholder.
     */
    public static void setDefaultPlaceholder(JTextField tf, String prompt) {
        tf.setFont(Decorations.FONT_PLACEHOLDER.getFont(Font.PLAIN, 16));
        tf.setText(prompt);
    }
    
    /**
     * Remove the placeholder of a JTextField.
     * @author MathysC
     *
     * @param tf The JTextField to unset.
     */
    public static void resetDefaultPlaceholder(JTextField tf) {
        tf.setFont(Decorations.FONT_BASIC.getFont(Font.PLAIN, 16));
        tf.setText("");
    }
}