package ui.managers;

import java.util.HashMap;
import java.util.Map;

import fc.films.Film;
import ui.utils.factory.filmpanel.factories.FilmCartPanel;
import ui.utils.factory.filmpanel.factories.FilmRentPanel;

/**
 * Represent the link between {@code Film} and UI.
 * The film is linked to the different Panel saved in {@code PanelsEntry}
 * @author MathysC
 * @see fc.films.Film
 * @see ui.managers.PanelsEntry
 * @see ui.managers.CartManager
 * @see ui.FilmEvents.FilmsEvent
 */
public class FilmManager {

    private Map<Film, PanelsEntry> films;
    private CartManager cartManager;
    
    /**
     * Default constructor of {@code FilmManager}
     * @author MathysC
     *
     */
    public FilmManager() {
	this.films = new HashMap<>();
	this.cartManager = new CartManager();
    }
    
    /**
     * Add a Film in UI.
     * @author MathysC
     *
     * @param film The {@code Film} to be graphed.
     * @return true if the film successfully added to the Map (so as everyPanel needed). Else false.
     */
    public boolean addFilm(Film film) {
	boolean successfullyAdded;
	PanelsEntry entries = new PanelsEntry(film);
	
	if(this.films.putIfAbsent(film, entries) != null) {
	    successfullyAdded = false;
	} else {
	    entries.getCartPanel().getButtonMap().forEach((k,b) -> b.addActionListener(cartManager));
//	    TODO create rentManager
//	    entries.getRentPanel().getButtonMap().forEach((k,b) -> b.addActionListener(rentManager));
	    successfullyAdded = true;
	}
	
	
	return successfullyAdded;
    }
    
    /**
     * Remove a Film from UI.
     * @author MathysC
     *
     * @param film the film to remove.
     * @return True if the film is successfully removed. Else false.
     */
    public boolean removeFilm(Film film) {
	boolean successfullyRemoved;
	
	/* Because we’re adding film using addFilm(Film film) function. 
	 * There is no way to obtain null other than the film does not exists in the Map. 
	 */
	if(this.films.remove(film) == null) {
	    successfullyRemoved = false;
	} else {
	    successfullyRemoved = true;
	}
	return successfullyRemoved;
   }

    /**
     * Get the {@code FilmCartPanel} corresponding to the film.
     * @author MathysC
     *
     * @param film The film to find as {@code FilmCartPanel}.
     * @return the {@code FilmCartPanel} if found. Else false.
     */
    public FilmCartPanel getFilmCartPanel(Film film) {
	PanelsEntry entry = this.films.get(film);
	return entry == null ? null : entry.getCartPanel();
    }
    
    /**
     * Get the {@code FilmRentPanel} corresponding to the film.
     * @author MathysC
     *
     * @param film The film to find as {@code FilmRentPanel}.
     * @return the {@code FilmRentPanel} if found. Else false.
     */
    public FilmRentPanel getFilmRentPanel(Film film) {
	PanelsEntry entry = this.films.get(film);
	return entry == null ? null : entry.getRentPanel();    
    }

    /**
     * @author MathysC
     * @return the cartManager
     */
    public CartManager getCartManager() {
	return cartManager;
    }
    
}
