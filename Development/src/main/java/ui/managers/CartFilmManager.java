package ui.managers;

import java.awt.LayoutManager;

import fc.films.Film;
import ui.utils.factory.filmpanel.factories.FilmCartPanel;
import ui.utils.mediator.cart.CartMediator;
import ui.utils.mediator.cart.components.CartComponent;

@SuppressWarnings("serial")
public class CartFilmManager extends FilmManager implements CartComponent {
    private CartMediator cartMediator;
    
    public CartFilmManager(LayoutManager layout, double percent) {
	super(layout, percent);
    }
    
    public void addFilm(Film film) {
	FilmCartPanel filmPanel = new FilmCartPanel(film);
	filmPanel.setMediator(cartMediator);
	filmPanel.setScale(percent);
	this.films.add(filmPanel);
	this.add(filmPanel);
    }
    
    @Override
    public void setMediator(CartMediator cartMediator) {
	this.cartMediator = cartMediator;
    }

}
