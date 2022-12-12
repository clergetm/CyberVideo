package ui.utils.factory.filmpanel.products;

import fc.films.Film;
import ui.managers.FilmEvents;

/**
 * Concrete FilmPanelButton implementation to directly rent a Film.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class RentButton extends FilmPanelButton {

    public RentButton(Film film, String supportType) {
	super(film, supportType);
	this.setActionCommand(FilmEvents.RENT.toString());
//	    TODO create rentManager
//	    entries.getRentPanel().getButtonMap().forEach((k,b) -> b.addActionListener(rentManager));
	   
    }

}
