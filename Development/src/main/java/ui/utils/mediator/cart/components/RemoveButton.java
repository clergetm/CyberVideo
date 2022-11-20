package ui.utils.mediator.cart.components;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import ui.utils.mediator.cart.CartMediator;

/**
 * Concrete component implementation 
 * for removing a film from the cart by a click of this button.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class RemoveButton extends JButton implements CartComponent{
    private CartMediator cartMediator;
    
    @Override
    public void setMediator(CartMediator cartMediator) {
	this.cartMediator = cartMediator;
    }
    
    /**
     * 
     * @author MathysC
     *
     */
    @Override
    protected void fireActionPerformed(ActionEvent actionEvent) {
	cartMediator.removeFromCart(this);
    }
}
