package ui.utils.factory.filmpanel.products;

import fc.films.Film;

/**
 * Concrete FilmPanelButton implementation to directly rent a Film.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class RentButton extends FilmPanelButton {

    public RentButton(Film film, String supportType) {
	super(film, supportType);
	// TODO Specific action
    }

}
