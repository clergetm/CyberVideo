package ui.utils.mediator.cart.components;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import ui.utils.mediator.cart.Mediator;

/**
 * Concrete component implementation 
 * for removing a film from the cart by a click of this button.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class RemoveButton extends JButton implements Component{
    private Mediator mediator;
    
    @Override
    public void setMediator(Mediator mediator) {
	this.mediator = mediator;
    }
    
    /**
     * 
     * @author MathysC
     *
     */
    @Override
    protected void fireActionPerformed(ActionEvent actionEvent) {
	mediator.removeFromCart(this);
    }

    @Override
    public String getComponentName() {
	return "RemoveButton";
    }

}