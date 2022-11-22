package ui.managers;

import fc.films.Film;
import ui.utils.factory.filmpanel.factories.FilmCartPanel;
import ui.utils.factory.filmpanel.factories.FilmRentPanel;

/**
 * Represents a tuple of value for film Map.
 * @author MathysC
 * @see ui.managers.FilmManager
 */
public class PanelsEntry {

    private FilmCartPanel cartPanel;
    private FilmRentPanel rentPanel;
    
    /**
     * Constructor of PanelsEntry.
     * Create both panels 
     * @author MathysC
     *
     * @param film The film to display in a panel.
     */
    public PanelsEntry(Film film) {
	this.cartPanel = new FilmCartPanel(film);
	this.rentPanel = new FilmRentPanel(film);
    }

    /**
     * @return the cartPanel
     */
    public FilmCartPanel getCartPanel() {
	return cartPanel;
    }

    /**
     * @return the rentPanel
     */
    public FilmRentPanel getRentPanel() {
	return rentPanel;
    }
}
