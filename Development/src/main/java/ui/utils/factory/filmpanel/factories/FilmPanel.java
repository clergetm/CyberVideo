package ui.utils.factory.filmpanel.factories;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fc.films.Film;
import fc.films.Support;
import ui.utils.Decorations;
import ui.utils.colors.ColorTheme;
import ui.utils.factory.filmpanel.products.FilmPanelButton;

/**
 * Abstract Factory to create Filmpanel.
 * Graphic Implementation of a Film.
 * A Film Panel is composed of:
 *  - A poster.
 *  - Buttons related to film supports.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public abstract class FilmPanel extends JPanel implements ColorTheme {
    protected Film film;

    private JLabel poster;
    private ImageIcon posterImage;

    protected JPanel buttonPanel;
    protected HashMap<String, FilmPanelButton> buttonMap = new HashMap<>();

    private Dimension dimButton = Decorations.sizeConverter(new Dimension(85, 25));
    private Dimension dimPoster = Decorations.sizeConverter(new Dimension(100, 150));
    protected Font font = Decorations.FONT_BASIC.getFont(Font.BOLD, 12);
    
    /**
     * Constructor of FilmPanel
     * @author MathysC
     *
     * @param film Film to display graphically.
     */
    protected FilmPanel(Film film) {
	this.film = film;
	// TODO #8
	posterImage = Decorations.getImg(Decorations.IMG_FILM.toString()); 
	this.createGUI();
	this.setScale(100);
    }
    
    /**
     * Create the GUI of the FilmPanel.
     * @author MathysC
     *
     */
    private void createGUI() {
	this.setLayout(new BorderLayout());
	this.setOpaque(false);
	
	poster = new JLabel();
	poster.setOpaque(false);
	this.add(poster, BorderLayout.CENTER);
	
	// Buttons
	buttonPanel = new JPanel(new FlowLayout());
	buttonPanel.setOpaque(false);
	
	/**
	 * We are adding a button for each support that this film has. 
	 * Whether available or not.
	 */
	for(Support support : film.getSupportsType()) {
	    String supportType = support.getType();
	    buttonMap.computeIfAbsent(supportType, b -> createButton(supportType));

	    /**
	     * If the support is available, set the button available.
	     * We do it this way because: 
	     * if we have at least one type of support available: the button is enabled.
	     */
	    // if this support is available and the button is not already enabled
	    if(support.isAvailable() && !buttonMap.get(supportType).isEnabled()) {
		buttonMap.get(supportType).setEnabled(true);
	    }
	}
	
	this.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * 
     * @author MathysC
     *
     * @param supportType the type of the support.
     * @return A button with a concrete implementation
     */
    protected abstract FilmPanelButton createButton(String supportType);
    
    /**
     * 
     * @author MathysC
     *
     * @param percent the percent to scale the panel to.
     * @requires percent must be positive or will be reset to 100.
     */
    public void setScale(double percent) {
	int base = 100;
	percent = (percent < 0) ? base : percent;
	// Scale the poster
	poster.setIcon(new ImageIcon( 
		posterImage.getImage()
		.getScaledInstance(
			(int) (percent * dimPoster.getWidth() / base),
			(int) (percent * dimPoster.getHeight() / base),
			java.awt.Image.SCALE_SMOOTH)));
	
	// Scale the font
	Font derivedfont = font.deriveFont((float) (percent * font.getSize() / base));
	
	// Scale the buttons
	for(JButton button : buttonMap.values()) {
	    button.setSize(
		    (int) (percent * dimButton.getWidth() / base),
		    (int) (percent * dimButton.getHeight() / base));
	    button.setFont(derivedfont);
	}
    }

    @Override
    public void setLight() {
	for(FilmPanelButton button : buttonMap.values()) {
	    button.setLight();
	}
    }

    @Override
    public void setDark() {
	for(FilmPanelButton button : buttonMap.values()) {
	    button.setDark();
	}	
    }
}