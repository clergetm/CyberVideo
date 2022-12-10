package ui.utils.observer.colortheme.palettes;

import java.awt.Color;

/**
 * Light color palette.
 * 
 * @author MathysC
 *
 */
public enum Light {
    BG(new Color(238, 239, 240)),
    REVERSE_FG(new Color(47, 83, 170)), // Foreground Blue used on white buttons.
    REVERSE_BG(new Color(255, 255, 255)), // Background White used on white buttons.
    BLACK(Color.BLACK),
    BLUE(new Color(47, 63, 170)),
    WHITE(new Color(245, 246, 243));
    
    private Color color;
    
    Light(Color c){ 
	this.color = c;
    }
	
    /**
     * @author MathysC
     * @return Color the color to get.
     */
    public Color getColor() {
	return this.color;
    }
}
