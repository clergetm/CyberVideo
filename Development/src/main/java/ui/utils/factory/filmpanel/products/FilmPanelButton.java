package ui.utils.factory.filmpanel.products;

import java.awt.Font;

import javax.swing.JButton;

import fc.films.Film;
import ui.utils.Decorations;
import ui.utils.observer.colortheme.ColorThemes;
import ui.utils.observer.colortheme.IColorThemeObserver;
import ui.utils.observer.colortheme.palettes.Dark;
import ui.utils.observer.colortheme.palettes.Light;

/**
 * Common Interface for Film Panel Buttons.
 * @author MathysC
 */
@SuppressWarnings("serial")
public abstract class FilmPanelButton extends JButton implements IColorThemeObserver {
    private Film film;
    private String supportType;
    
//    FIXME Refactor in FONT ENUM
    private Font buttonFont = Decorations.FONT_BASIC.getFont(Font.BOLD, 12);
    
    protected FilmPanelButton(Film film, String supportType) {
	this.film = film;
	this.supportType = supportType;
	this.setText(supportType);
	this.setName(supportType);
	this.setFont(buttonFont);
	this.setEnabled(false); // Disabled by default.

    }
    /**
     * @return the film
     */
    public Film getFilm() {
	return film;
    }

    /**
     * @return the supportType
     */
    public String getSupportType() {
	return supportType;
    }
   
    //  FIXME TOO
    public Font getButtonFont() {
	return this.buttonFont;
    }
  
    @Override
    public void setColorTheme(ColorThemes colorTheme) {
	switch(colorTheme) {
	case LIGHT_THEME:
	    this.setBackground(Light.BLUE.getColor());
	    this.setForeground(Light.WHITE.getColor());
	    break;
	case DARK_THEME:
	    this.setBackground(Dark.BLUE.getColor());
	    this.setForeground(Dark.FOREGROUND.getColor());
	    break;
	default:
	    break;
	}
	
    }
}
