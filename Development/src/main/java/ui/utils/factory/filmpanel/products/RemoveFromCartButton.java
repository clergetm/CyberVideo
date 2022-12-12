package ui.utils.factory.filmpanel.products;

import fc.films.Film;
import ui.managers.FilmEvents;
import ui.managers.FilmManager;

/**
 * Concrete FilmPanelButton implementation to remove a film from the cart.
 * @author MathysC
 */
@SuppressWarnings("serial")
public class RemoveFromCartButton extends FilmPanelButton {

    /**
     * Default constructor for concrete implementation of a button to remove a film from the cart.
     * @author MathysC
     *
     * @param film The Film.
     * @param supportType The chosen support Type.
     */
    public RemoveFromCartButton(Film film, String supportType) {
	super(film, supportType);
	this.setActionCommand(FilmEvents.REMOVEFROMCART.toString());
	this.addActionListener(FilmManager.getInstance().getCartManager());
	
	this.setEnabled(true);
	
    }

}
