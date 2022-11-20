package ui.utils.mediator.cart.components;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;

import ui.utils.Decorations;
import ui.utils.bundles.Multilingual;
import ui.utils.colors.ColorTheme;
import ui.utils.colors.Dark;
import ui.utils.colors.Light;
import ui.utils.mediator.cart.CartMediator;

/**
 * Concrete component implementation 
 * for removing a film from the cart by a click of this button.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class RemoveButton extends JButton implements CartComponent, ColorTheme, Multilingual{
    private CartMediator cartMediator;
    
    /**
     * Constructor of RemoveButton.
     * @author MathysC
     *
     */
    public RemoveButton() {
        this.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));
    }
    
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

    @Override
    public void setLanguage(ResourceBundle rb) {
        this.setText(rb.getString("checkout_remove"));
    }

    @Override
    public void setLight() {
        this.setBackground(Light.BLUE.getColor());
        this.setForeground(Light.WHITE.getColor());	
    }

    @Override
    public void setDark() {
        this.setBackground(Dark.BLUE.getColor());
        this.setForeground(Dark.FOREGROUND.getColor());	
    }
}
