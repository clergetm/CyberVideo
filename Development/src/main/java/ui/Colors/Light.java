package ui.Colors;

import java.awt.Color;

/**
 * Light color palette.
 * 
 * @author MathysC
 *
 */
public enum Light {
	BG(238, 239, 240),
	FG_BLUE(47, 83, 170),	// Foreground Blue.
	WHITE(255, 255, 255),	// Used as Foreground too.
	BLUE(47, 63, 170);
	
	
	
	private int[] rgb;
	
	Light(int r, int g, int b) {
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
