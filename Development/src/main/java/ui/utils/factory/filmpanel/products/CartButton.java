package ui.utils.factory.filmpanel.products;

import java.awt.event.ActionEvent;

import fc.films.Film;
import ui.utils.mediator.cart.CartMediator;
import ui.utils.mediator.cart.components.CartComponent;

/**
 * Concrete FilmPanelButton implementation to add a film to the cart.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class CartButton extends FilmPanelButton implements CartComponent {
    private CartMediator cartMediator;
    private Film film;
    private String supportType;
    
    /**
     * Constructor of CartButton
     * @author MathysC
     *
     * @param film The film to add to the cart.
     * @param supportType the way film is supported. (e.g. Blu-Ray, QR Code)
     */
    public CartButton(Film film, String supportType) {
	this.film = film;
	this.supportType = supportType;
    }
    
    @Override
    public void setMediator(CartMediator cartMediator) {
	this.cartMediator = cartMediator;
    }

    /**
     * When this button is clicked, The mediator is used in order to add a film to the cart.
     * @author MathysC
     *
     */
    @Override
    protected void fireActionPerformed(ActionEvent actionEvent) {
	this.cartMediator.addToCart(this.film, this.supportType);
    }

}
