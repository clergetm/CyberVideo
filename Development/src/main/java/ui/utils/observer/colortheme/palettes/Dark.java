package ui.utils.observer.colortheme.palettes;

import java.awt.Color;

/**
 * Dark Palette of color inspired by Dracula
 * 
 * @author MathysC
 *
 * @see https://draculatheme.com/contribute
 */
public enum Dark {
    BG(new Color(40,42,44)),
    FOREGROUND(new Color(248, 248, 242)),
    PURPLE(new Color(86, 71, 90)),
    BLUE(new Color(98, 114, 164)),
    PINK(new Color(189, 147, 249));
	
	
    private Color color;
    
    Dark(Color c) {
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
