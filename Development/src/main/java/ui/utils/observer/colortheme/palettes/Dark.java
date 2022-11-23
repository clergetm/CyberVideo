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

	BG(40,42,44),
	FOREGROUND(248, 248, 242),
	PURPLE(86, 71, 90),
	BLUE(98, 114, 164),
	PINK(189, 147, 249);
	
	
	private int[] rgb;
	
	Dark(int r, int g, int b) {
		this.rgb = new int[]{r, g, b};
	}
	
	/**
	 * Get the color.
	 * @author MathysC
	 *
	 * @return Color from rgb.
	 */
	public Color getColor() {
		return new Color(this.r(), this.g(), this.b());
		
	}
	
	/**
	 * Get red value
	 * @author MathysC
	 *
	 * @return int red
	 */
	private int r() {
		return this.rgb[0];
	}
	
	/**
	 * Get green value
	 * @author MathysC
	 *
	 * @return int green
	 */
	private int g() {
		return this.rgb[1];
	}
	
	/**
	 * Get blue value
	 * @author MathysC
	 *
	 * @return int blue
	 */
	private int b() {
		return this.rgb[2];
	}
}