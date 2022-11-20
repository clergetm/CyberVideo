package ui.pages.actions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import fc.clients.Client;
import fc.films.Film;
import ui.utils.Decorations;
import ui.utils.bundles.Multilingual;
import ui.utils.colors.ColorTheme;
import ui.utils.colors.Dark;
import ui.utils.colors.Light;
import ui.utils.mediator.cart.CartMediator;
import ui.utils.mediator.cart.components.CartComponent;
import ui.utils.mediator.cart.components.RemoveButton;

/**
 * This class implements the whole GUI cart and the button to rent films in the cart.
 * @author MathysC
 * @see ui.pages.actions.CartItemPanel
 * @see ui.utils.mediator.cart.CartMediator
 * @see ui.bundles.Multilingual
 * @see ui.colors.ColorTheme
 */
@SuppressWarnings("serial")
public class CheckoutPanel extends JPanel implements CartMediator, Multilingual, ColorTheme {
    private Client client;
    private HashMap<Film, CartItemPanel> cart = new HashMap<>();
   
    private JPanel cartPanel = new JPanel();
    private JButton checkoutButton = new JButton(); // TODO Action of checkoutButton
    
    /**
     * FIXME finish implementation with client
     * @author MathysC
     *
     * @param client the current connected client
     */
    public CheckoutPanel(Client client) {
	this.client = client;
	this.createGUI();
    }
    
    /**
     * Default Constructor of {@code CheckoutPanel}.
     * TODO Remove this constructor when implementation with client is done
     * @author MathysC
     *
     */
    public CheckoutPanel() {
	this.createGUI();
    }
    
    /**
     * Create and connect all used {@code swing JComponents.
     * @author MathysC
     *
     */
    private void createGUI() {
	this.setLayout(new BorderLayout());
	
	cartPanel.setLayout(new GridLayout(3,0));
	this.add(cartPanel, BorderLayout.NORTH);
	
	JPanel tempPanel = new JPanel();
	tempPanel.setOpaque(false);
	checkoutButton.setPreferredSize(new Dimension(200, 75));
	tempPanel.add(checkoutButton);
	this.add(tempPanel, BorderLayout.SOUTH);
    }
    
    /**
     * 
     * @author MathysC
     *
     * @return the client
     */
    public Client getClient() {
	return client;
    }

    /**
     * 
     * @author MathysC
     *
     * @param client the client to set
     */
    public void setClient(Client client) {
	this.client = client;
    }

    @Override
    public boolean addToCart(Film film, String supportType) {
	// TODO Check Client cart
//	if (this.cart.size() >= this.client.getSizeCart()) return false;
	// TODO Add to Client cart

	// Add to GUI cart
	CartItemPanel item = new CartItemPanel(film, supportType);
	item.getRemoveButton().setMediator(this);
	this.cartPanel.add(item); // TODO#29 addFilm to manager
	this.cart.put(film, item);
	return true;
    }

    /**
     * {@inheritDoc}
     * 
     * This implementation of this method uses only {@code RemoveButton}.
     * So we don’t need to check for the right Component using {@code getComponentName()}
     * @author MathysC
     * @see io.utils.mediator.cart.components.RemoveButton
     */
    @Override
    public void removeFromCart(CartComponent from) {
	// TODO Remove from client cart
	// Remove from GUI cart
	RemoveButton button = (RemoveButton) from;
	for(Entry<Film, CartItemPanel> entry : cart.entrySet()) {
	    if((entry.getValue().getRemoveButton()).equals(button)) {
		cartPanel.remove(entry.getValue());
		cart.remove(entry.getKey());
		this.revalidate();
		this.repaint();
		return;
	    }
	}
    }

    @Override
    public void clearCart() {
	// TODO clear client cart
	for(Entry<Film, CartItemPanel> entry : cart.entrySet()) {
	    cartPanel.remove(entry.getValue());
	    cart.remove(entry.getKey());
	}
	
	this.revalidate();
	this.repaint();
    }
    
    @Override
    public void setLight() {
	// This Panel
	this.setBackground(Light.BG.getColor());
	this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Light.BLACK.getColor()));
	// Checkout Manager
	this.cartPanel.setBackground(this.cartPanel.getParent().getBackground());
	// cartItems from cart
	for(CartItemPanel fp : cart.values()) {
	    fp.setLight();
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
	this.cartPanel.setBackground(this.cartPanel.getParent().getBackground());
	// CheckedOutFilmPanel from cart
	for(CartItemPanel fp : cart.values()) {
	    fp.setDark();		
	}
	
	// Checkout Button
	this.checkoutButton.setBackground(Dark.BLUE.getColor());
	this.checkoutButton.setForeground(Dark.FOREGROUND.getColor());
	this.checkoutButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));	
    }

    @Override
    public void setLanguage(ResourceBundle rb) {
	checkoutButton.setText(rb.getString("checkout_button"));
	for(CartItemPanel fp : cart.values()) {
	    fp.setLanguage(rb);	
	}
    }
}