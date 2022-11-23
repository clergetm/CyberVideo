package ui.pages.cart;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import fc.films.Film;
import ui.managers.FilmEvents;
import ui.managers.GUIManager;
import ui.utils.Decorations;
import ui.utils.colors.ColorTheme;
import ui.utils.observer.cart.ICartObserver;
import ui.utils.observer.colortheme.palettes.Dark;
import ui.utils.observer.colortheme.palettes.Light;
import ui.utils.observer.multilingual.IMultilingualObserver;

/**
 * This class implements the whole GUI cart and the button to rent films in the cart.
 * @author MathysC
 * @see ui.pages.cart.CartItemPanel
 * @see ui.utils.observer.cart.ICartObserver
 * @see ui.bundles.Multilingual
 * @see ui.colors.ColorTheme
 */
@SuppressWarnings("serial")
public class CartPanel extends JPanel implements IMultilingualObserver, ColorTheme, ICartObserver{
    private JPanel itemPanel = new JPanel();
//  TODO Action of checkoutButton
    private JButton checkoutButton = new JButton();
    
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
     * Create CartPanel Interface.
     * @author MathysC
     */
    private void createGUI() {
	this.setLayout(new BorderLayout());
	
	itemPanel.setLayout(new GridLayout(3,0));
	this.add(itemPanel, BorderLayout.NORTH);
	
	checkoutButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));	
	checkoutButton.setPreferredSize(new Dimension(200, 75));
	this.add(checkoutButton, BorderLayout.SOUTH);
    }
    
    /**
     * Add a CartItemPanel to the Panel.
     * @author MathysC
     *
     * @param item the CartItemPanel to add.
     */
    public void addToCart(CartItemPanel item) {
	this.itemPanel.add(item);
	this.revalidate();
	this.repaint();
	GUIManager.getInstance().registerMultilingual(item);
	GUIManager.getInstance().refreshMultilingual();
    }
    
    /**
     * Remove a CartItemPanel from the Panel.
     * @author MathysC
     *
     * @param item the CartItemPanel to remove.
     */
    public void removeFromCart(CartItemPanel item) {
	this.itemPanel.remove(item);
	this.revalidate();
	this.repaint();
	GUIManager.getInstance().unregisterMultilingual(item);

    }
    
    /**
     * Clear the items panel.
     * @author MathysC
     */
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
	    ((ColorTheme)fp).setLight();
	}
	// Checkout Button
	this.checkoutButton.setBackground(Light.BLUE.getColor());
	this.checkoutButton.setForeground(Light.WHITE.getColor());
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
	    ((ColorTheme)fp).setLight();
	}
	
	// Checkout Button
	this.checkoutButton.setBackground(Dark.BLUE.getColor());
	this.checkoutButton.setForeground(Dark.FOREGROUND.getColor());
    }

    @Override
    public void setLanguage(ResourceBundle rb) {
	checkoutButton.setText(rb.getString("checkout_button"));
    }

    @Override
    public void update(FilmEvents event, Film film, String supportType) {
	switch(event) {
	case ADDTOCART:
	    this.addToCart(new CartItemPanel(film, supportType));
	    break;
	case REMOVEFROMCART:
	    // TODO Retrouver le bon cartItemPanel
	    break;
	default:
	    break;
	}
	
    }
    
}