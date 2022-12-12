package ui.utils.factory.filmpanel.factories;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fc.films.Film;
import fc.films.Support;
import ui.GUIComponent;
import ui.mainframe.MainFrame;
import ui.managers.GUIManager;
import ui.utils.Decorations;
import ui.utils.GoToFilmPageAdapter;
import ui.utils.Resources;
import ui.utils.factory.filmpanel.products.FilmPanelButton;

/**
 * Abstract Factory to create Filmpanel.
 * Graphic Implementation of a Film.
 * A Film Panel is composed of:
 *  - A poster.
 *  - Buttons related to film supports.
 * @author MathysC
 */
@SuppressWarnings("serial")
public abstract class FilmPanel extends JPanel implements GUIComponent {

    private static final Dimension DIM_BUTTON = Decorations.sizeConverter(new Dimension(85, 25));
    private static final Dimension DIM_POSTER = Decorations.sizeConverter(new Dimension(100, 150));
    
    private Film film;
    private Map<String, FilmPanelButton> buttonMap;

    /* Components */
    private JPanel mainPanel;
    protected JPanel buttonPanel;
    private JLabel poster;
    private ImageIcon posterImage;
    
    /**
     * Constructor of FilmPanel
     * @author MathysC
     *
     * @param film Film to display graphically.
     */
    protected FilmPanel(Film film) {
	this.film = film;
	this.buttonMap = new HashMap<>();
	
	mainPanel = new JPanel(new BorderLayout());
	poster = new JLabel();
	poster.addMouseListener(new GoToFilmPageAdapter(this));
	buttonPanel = new JPanel(new FlowLayout());

	// TODO #8 Find a way to put the right poster for each movie
	posterImage = Resources.getImg(Resources.IMG_FILM.toString()); 
	this.createGUI();
	
	this.setScale(100);
    }
    
    /** 
     * @author MathysC
     *
     * @param supportType the type of the support.
     * @return A button with a concrete implementation
     */
    protected abstract FilmPanelButton createButton(String supportType);
    
    /**
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
			(int) (percent * DIM_POSTER.getWidth() / base),
			(int) (percent * DIM_POSTER.getHeight() / base),
			java.awt.Image.SCALE_SMOOTH)));
	
	// Scale the buttons
	for(JButton button : buttonMap.values()) {
	    // Scale the font
	    // FIXME use Enum font
	    Font tempFont = ((FilmPanelButton) button).getButtonFont();
	    Font derivedfont = tempFont.deriveFont((float) (percent * tempFont.getSize() / base));
	    button.setSize(
		    (int) (percent * DIM_BUTTON.getWidth() / base),
		    (int) (percent * DIM_BUTTON.getHeight() / base));
	    button.setFont(derivedfont);
	}
    }

    /**
     * @author MathysC
     * @return the film
     */
    public Film getFilm() {
	return film;
    }

    /**
     * @author MathysC
     * @return the buttonMap
     */
    public Map<String, FilmPanelButton> getButtonMap() {
	return buttonMap;
    }
    
    @Override
    public void createGUI() {
	this.setLayout(new FlowLayout());
	this.setOpaque(false);
	
	mainPanel.setOpaque(false);
	
	poster.setOpaque(false);
	poster.setHorizontalAlignment(SwingConstants.CENTER);
	mainPanel.add(poster, BorderLayout.CENTER);
	
	// Buttons
	buttonPanel.setOpaque(false);
	
	/**
	 * We are adding a button for each support that this film has. 
	 * Whether available or not.
	 */
	for(Support support : film.getSupports()) {
	    String supportType = support.getType();
	    
	    // If successfully added, add the button to the GUIManager
	    if(!buttonMap.containsKey(supportType)) {
		FilmPanelButton button = createButton(supportType);
		buttonMap.put(supportType, button);
		GUIManager.getInstance().registerColorTheme(button);
	    }
	    /**
	     * If the support is available, set the button available.
	     * We do it this way because: 
	     * if we have at least one type of support available: the button is enabled.
	     */
	    // if this support is available and the button is not already enabled
	    if(support.isAvailable() && !(buttonMap.get(supportType)).isEnabled()) {
		buttonMap.get(supportType).setEnabled(true);
	    }
	}
	
	mainPanel.add(buttonPanel, BorderLayout.SOUTH);
	this.add(mainPanel);
    }
}