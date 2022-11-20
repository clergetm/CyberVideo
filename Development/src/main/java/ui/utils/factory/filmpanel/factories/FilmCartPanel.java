package ui.utils.factory.filmpanel.factories;

import fc.films.Film;
import ui.utils.factory.filmpanel.products.CartButton;
import ui.utils.factory.filmpanel.products.FilmPanelButton;
import ui.utils.mediator.cart.CartMediator;
import ui.utils.mediator.cart.components.CartComponent;

/**
 * Factory to create FilmCartPanel.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class FilmCartPanel extends FilmPanel implements CartComponent {
    private CartMediator cartMediator;

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

    @Override
    public void setMediator(CartMediator cartMediator) {
	this.cartMediator = cartMediator;
	for(FilmPanelButton button : buttonMap.values()) {
	    ((CartButton)button).setMediator(cartMediator);
	}
    }

}
