package ui.utils.factory.filmpanel.factories;

import fc.films.Film;
import ui.utils.factory.filmpanel.products.CartButton;
import ui.utils.factory.filmpanel.products.FilmPanelButton;

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
    protected FilmPanelButton createButton(String supportType) {
	CartButton button = new CartButton();
	button.setText(supportType);
	button.setName(supportType);
	button.setFont(font);
	button.setEnabled(false); // Disabled by default.
	
	this.buttonPanel.add(button); // Add to the panel 
	return button;
    }

}
