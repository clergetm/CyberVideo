package ui.utils.factory.filmpanel.factories;

import fc.films.Film;
import ui.utils.factory.filmpanel.products.CartButton;

/**
 * Factory to create {@code FilmCartPanel}.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class FilmCartPanel extends FilmPanel {

    /**
     * Default constructor of {@code FilmCartPanel}
     * @author MathysC
     *
     * @param film The Film.
     */
    public FilmCartPanel(Film film) {
	super(film);
    }

    @Override
    protected CartButton createButton(String supportType) {
	CartButton button = new CartButton(this.getFilm(), supportType);	
	this.buttonPanel.add(button); // Add to the panel 
	return button;
    }
    
}
