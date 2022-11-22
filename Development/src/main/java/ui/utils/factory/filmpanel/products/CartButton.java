package ui.utils.factory.filmpanel.products;

import fc.films.Film;

/**
 * Concrete FilmPanelButton implementation to add a film to the cart.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class CartButton extends FilmPanelButton {

    
    /**
     * Constructor of CartButton
     * @author MathysC
     *
     * @param film The film to add to the cart.
     * @param supportType the way film is supported. (e.g. Blu-Ray, QR Code)
     */
    public CartButton(Film film, String supportType) {
	super(film, supportType);
	// TODO Specific action
    }

}
