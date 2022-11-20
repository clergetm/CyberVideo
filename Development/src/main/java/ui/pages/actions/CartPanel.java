package ui.pages.actions;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import ui.utils.Decorations;
import ui.utils.bundles.Multilingual;
import ui.utils.colors.ColorTheme;
import ui.utils.colors.Dark;
import ui.utils.colors.Light;
import ui.utils.mediator.cart.CartMediator;
import ui.utils.mediator.cart.components.CartComponent;

/**
 * This class implements the whole GUI cart and the button to rent films in the cart.
 * @author MathysC
 * @see ui.pages.actions.CartItemPanel
 * @see ui.utils.mediator.cart.CartMediator
 * @see ui.bundles.Multilingual
 * @see ui.colors.ColorTheme
 */
@SuppressWarnings("serial")
public class CartPanel extends JPanel implements Multilingual, ColorTheme {
    private JPanel itemPanel = new JPanel();
    private JButton checkoutButton = new JButton(); // TODO Action of checkoutButton
    
    /**
     * Default Constructor of {@code CheckoutPanel}.
     * TODO Remove this constructor when implementation with client is done
     * @author MathysC
     *
     */
    public CartPanel() {
	this.createGUI();
    }
    
    /**
     * Create and connect all used {@code swing JComponents.
     * @author MathysC
     *
     */
    private void createGUI() {
	this.setLayout(new BorderLayout());
	
	itemPanel.setLayout(new GridLayout(3,0));
	this.add(itemPanel, BorderLayout.NORTH);
	
	JPanel tempPanel = new JPanel();
	tempPanel.setOpaque(false);
	checkoutButton.setPreferredSize(new Dimension(200, 75));
	tempPanel.add(checkoutButton);
	this.add(tempPanel, BorderLayout.SOUTH);
    }
    
    public void addToCart(CartItemPanel item) {
	this.itemPanel.add(item);
    }
    
    public void removeFromCart(CartItemPanel item) {
	itemPanel.remove(item);
	this.revalidate();
	this.repaint();
    }

    public void clearCart() {
	this.itemPanel.removeAll();
	this.revalidate();
	this.repaint();
    }
    
    @Override
    public void setLight() {
	// This Panel
	this.setBackground(Light.BG.getColor());
	this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Light.BLACK.getColor()));
	// Checkout Manager
	this.itemPanel.setBackground(this.itemPanel.getParent().getBackground());
	// cartItems from cart
	for(Component fp : this.itemPanel.getComponents()) {
	    ((CartItemPanel)fp).setLight();
	}
	// Checkout Button
	this.checkoutButton.setBackground(Light.BLUE.getColor());
	this.checkoutButton.setForeground(Light.WHITE.getColor());
	this.checkoutButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));		
    }

    @Override
    public void setDark() {
	// This Panel
	this.setBackground(Dark.BG.getColor());
	this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Dark.FOREGROUND.getColor()));
	// Checkout Manager
	this.itemPanel.setBackground(this.itemPanel.getParent().getBackground());
	// CheckedOutFilmPanel from cart
	for(Component fp : this.itemPanel.getComponents()) {
	    ((CartItemPanel)fp).setLight();
	}
	
	// Checkout Button
	this.checkoutButton.setBackground(Dark.BLUE.getColor());
	this.checkoutButton.setForeground(Dark.FOREGROUND.getColor());
	this.checkoutButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));	
    }

    @Override
    public void setLanguage(ResourceBundle rb) {
	checkoutButton.setText(rb.getString("checkout_button"));
	for(Component fp : this.itemPanel.getComponents()) {
	    ((CartItemPanel)fp).setLight();
	}
    }
}