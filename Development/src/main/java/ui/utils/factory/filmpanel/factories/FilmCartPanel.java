package ui.utils.factory.filmpanel.factories;

import fc.films.Film;
import ui.utils.factory.filmpanel.products.CartButton;

/**
 * Factory to create FilmCartPanel.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class FilmCartPanel extends FilmPanel {

    public FilmCartPanel(Film film) {
	super(film);
	// TODO Auto-generated constructor stub
    }

    @Override
    protected CartButton createButton(String supportType) {
	CartButton button = new CartButton(this.getFilm(), supportType);
	button.setText(supportType);
	button.setName(supportType);
	button.setFont(font);
	button.setEnabled(false); // Disabled by default.
	
	this.buttonPanel.add(button); // Add to the panel 
	return button;
    }
    
}
