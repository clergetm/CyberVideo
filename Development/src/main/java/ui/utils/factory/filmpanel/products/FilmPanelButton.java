package ui.utils.factory.filmpanel.products;

import java.awt.Font;

import javax.swing.JButton;

import fc.films.Film;
import ui.utils.Decorations;
import ui.utils.colors.ColorTheme;
import ui.utils.colors.Dark;
import ui.utils.colors.Light;

/**
 * Common Interface for Film Panel Buttons.
 * @author MathysC
 */
@SuppressWarnings("serial")
public abstract class FilmPanelButton extends JButton implements ColorTheme {
    private Film film;
    private String supportType;
    
//    FIXME Refactor in FONT ENUM
    private Font buttonFont = Decorations.FONT_BASIC.getFont(Font.BOLD, 12);
//    FIXME TOO
    public Font getButtonFont() {
	return this.buttonFont;
    }
    
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
