package ui.utils.factory.filmpanel.products;

import javax.swing.JButton;

import ui.utils.colors.ColorTheme;
import ui.utils.colors.Dark;
import ui.utils.colors.Light;

/**
 * Common Interface for Film Panel Buttons.
 * @author MathysC
 */
@SuppressWarnings("serial")
public abstract class FilmPanelButton extends JButton implements ColorTheme {
    
    @Override
    public void setLight() {
	this.setBackground(Light.BLUE.getColor());
	this.setForeground(Light.WHITE.getColor());
    }

    @Override
    public void setDark() {
	this.setBackground(Dark.BLUE.getColor());
	this.setForeground(Dark.FOREGROUND.getColor());
    }
    
}
