package ui.Pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.Flow;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fc.Films.Film;
import ui.Decorations;
import ui.Bundles.Multilingual;
import ui.Colors.ColorTheme;
import ui.Colors.Dark;
import ui.Colors.Light;

@SuppressWarnings("serial")
class CheckedOutFilmPanel extends JPanel implements Multilingual, ColorTheme {
		
		protected JLabel poster = new JLabel();
		protected JPanel filmOptions = new JPanel();
		protected JLabel filmTitle = new JLabel();
		protected JLabel rentType = new JLabel();
		protected JButton informationButton = new JButton();
		protected JButton removeButton = new JButton();
		
	public CheckedOutFilmPanel(Film film, String rent) {
		this.setLayout(new FlowLayout());
		filmOptions.setLayout(new BoxLayout(filmOptions, BoxLayout.Y_AXIS));
			
		/* Poster */
		// poster.setIcon(film.getIcon()); // TODO: getter of the film Icon
		poster.setIcon(Decorations.getImg(Decorations.IMG_FILM.toString()));
		this.add(poster);
		
		/*Options */
		// title
		filmTitle.setText(film.getTitle());
		filmOptions.add(filmTitle);
		
		// rent type
		rentType.setText(rent);
		filmOptions.add(rentType);
		
		// information Button
		informationButton.setText("Information");
		filmOptions.add(informationButton);
		
		// remove Button
		removeButton.setText("remove");
		filmOptions.add(removeButton);
		
		this.add(filmOptions);
	}
	
	@Override
	public void setLight() {
		// This JPanel
        this.setBackground(this.getParent().getBackground());
      
        // poster
        this.poster.setBackground(this.poster.getParent().getBackground());

        // Options
        this.filmOptions.setBackground(this.filmOptions.getParent().getBackground());
        
        // Film title
		this.filmTitle.setForeground(Light.BLACK.getColor());

        // Rent type
		this.rentType.setForeground(Light.BLACK.getColor());
		
		// Information Button
		this.informationButton.setBackground(Light.BLUE.getColor());
		this.informationButton.setForeground(Light.WHITE.getColor());
		this.informationButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));
		
		// Remove Button
		this.removeButton.setBackground(Light.BLUE.getColor());
		this.removeButton.setForeground(Light.WHITE.getColor());
		this.removeButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));		
	}

	@Override
	public void setDark() {
		// This JPanel
        this.setBackground(this.getParent().getBackground());
      
        // poster Label
        this.poster.setBackground(this.poster.getParent().getBackground());

        // Options JPanel
        this.filmOptions.setBackground(this.filmOptions.getParent().getBackground());
        
        // Film title Label
		this.filmTitle.setForeground(Dark.FOREGROUNG.getColor());

        // Rent type Label
		this.rentType.setForeground(Dark.FOREGROUNG.getColor());
		
		// Information Button
		this.informationButton.setBackground(Dark.BLUE.getColor());
		this.informationButton.setForeground(Dark.FOREGROUNG.getColor());
		this.informationButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));
		
		// Remove Button
		this.removeButton.setBackground(Dark.BLUE.getColor());
		this.removeButton.setForeground(Dark.FOREGROUNG.getColor());
		this.removeButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));	
		
	}

	@Override
	public void setLanguage(ResourceBundle rb) {
		this.informationButton.setText(rb.getString("checkout_info"));
		this.removeButton.setText(rb.getString("checkout_remove"));
	}

}


@SuppressWarnings("serial")
class CheckoutPanel extends JPanel implements Multilingual, ColorTheme {

	/* Checkout Part */
	protected JPanel checkoutManager = new JPanel(new GridLayout(0,3));
	protected JButton checkoutButton = new JButton();

	private HashMap<Film, CheckedOutFilmPanel> cart = new HashMap<>();
	
	public CheckoutPanel() {
		this.setLayout(new BorderLayout());
		this.add(checkoutManager, BorderLayout.NORTH);
		
		JPanel panelforCheckoutButton = new JPanel();
		panelforCheckoutButton.setOpaque(false);
		checkoutButton.setPreferredSize(new Dimension(200, 75));
		panelforCheckoutButton.add(checkoutButton);
		this.add(panelforCheckoutButton, BorderLayout.SOUTH);
	}
	
	/**
	 * Create a JPanel with data from the Rented film.
	 * @author MathysC
	 *
	 * @param film the rented film.
	 * @param rent how the film is rented.
	 * @return The complete JPanel of this film.
	 */
	private void addToCart(Film film, String rent) {
		CheckedOutFilmPanel filmCheckedOut = new CheckedOutFilmPanel(film, rent); 
//		this.checkoutManager.addFilm(filmCheckedOut); // TODO: addFilm to manager
		this.cart.put(film, filmCheckedOut);
	}
	
	@Override
	public void setLight() {
		// This Panel
		this.setBackground(Light.BG.getColor());
        this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Light.BLACK.getColor()));

		// Checkout Manager
		this.checkoutManager.setBackground(this.checkoutManager.getParent().getBackground());
		// CheckedOutFilmPanel from cart
		for(CheckedOutFilmPanel fp : cart.values())
			fp.setLight();
		
		// Checkout Button
		this.checkoutButton.setBackground(Light.BLUE.getColor());
		this.checkoutButton.setForeground(Light.WHITE.getColor());
		this.checkoutButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));		
	}

	@Override
	public void setDark() {
		// This Panel
		this.setBackground(Dark.BG.getColor());
        this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Dark.FOREGROUNG.getColor()));

		// Checkout Manager
		this.checkoutManager.setBackground(this.checkoutManager.getParent().getBackground());
		
		// CheckedOutFilmPanel from cart
		for(CheckedOutFilmPanel fp : cart.values())
			fp.setDark();		
		
		// Checkout Button
		this.checkoutButton.setBackground(Dark.BLUE.getColor());
		this.checkoutButton.setForeground(Dark.FOREGROUNG.getColor());
		this.checkoutButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));	
	}

	@Override
	public void setLanguage(ResourceBundle rb) {
		
		checkoutButton.setText(rb.getString("checkout_button"));
		for(CheckedOutFilmPanel fp : cart.values())
			fp.setLanguage(rb);	
		
	}
}

@SuppressWarnings("serial")
public class ActionPanel extends JPanel implements Multilingual, ColorTheme {
	
	/* Search Items */
	public static final String[] boxItems = {"Title", "Actor", "Director"};
	
	
	/* Action Center Panel Part*/
	protected JPanel actionCenterPanel = new JPanel(new BorderLayout());
	protected JPanel topPanel = new JPanel(new BorderLayout());
	
	/* Top Panel Components */
	protected JPanel searchPanel = new JPanel(new FlowLayout());
	protected JTextField tfSearch = new JTextField();
	protected JComboBox<String> filterCBox = new JComboBox<>(boxItems);
	protected JPanel commandPanel = new JPanel(new FlowLayout());
	protected JButton undoButton = new JButton();
	protected JButton redoButton = new JButton();
	protected JButton connectionButton = new JButton();
	
	public JButton getUndoButton() {
		return undoButton;
	}

	public JButton getRedoButton() {
		return redoButton;
	}

	public JButton getConnectionButton() {
		return connectionButton;
	}
	
	/*Action pages */
	public static final int RESULT_PAGE = 0;
	public static final int FILM_PAGE = 1;
	
	/*Action actions*/
	public static final String ACTION_UNDO = "Undo";
	public static final String ACTION_REDO = "Redo";

	/* Images */
	public static final String IMG_UNDO_LIGHT = "undoLight";
	public static final String IMG_UNDO_DARK = "undoDark";
	public static final String IMG_REDO_LIGHT = "redoLight";
	public static final String IMG_REDO_DARK = "redoDark";
	
	/* Checkout Part */
	protected CheckoutPanel checkoutPanel = new CheckoutPanel();
	
	
	/**
	 * Constructor of ActionPanel, represent the Cart, and several Command buttons.
	 * @author MathysC
	 *
	 */
	public ActionPanel() {
		this.setLayout(new BorderLayout());
		actionCenterPanel.setOpaque(false);
		topPanel.setOpaque(false);
		searchPanel.setOpaque(false);
		commandPanel.setOpaque(false);
		
		/* Search Panel */
		// Search TextField
		// TODO: Add MouseListener
		// TODO: Add KeyboardDialog
		tfSearch.setColumns(30);
		searchPanel.add(tfSearch);
		
		// Filter ComboxBox
		// TODO: Correct Size and Color Combobox
		searchPanel.add(filterCBox);
		
		topPanel.add(searchPanel,BorderLayout.WEST);
		
		/* Command Panel */
		// Undo Button
		undoButton.setActionCommand(ACTION_UNDO);
		undoButton.setContentAreaFilled(false); // transparent
		undoButton.setBorderPainted(false); // without border
		commandPanel.add(undoButton);

		// Redo Button
		redoButton.setActionCommand(ACTION_REDO);
		redoButton.setContentAreaFilled(false); // transparent
		redoButton.setBorderPainted(false); // without border
		commandPanel.add(redoButton);

		// Connection Button
//		connectionButton.setActionCommand(ACTION_UNDO); // TODO
		connectionButton.setPreferredSize(Decorations.getDefaultButtonDimension());
		commandPanel.add(connectionButton);

		topPanel.add(commandPanel, BorderLayout.EAST);
		
		actionCenterPanel.add(topPanel, BorderLayout.NORTH);
		
		this.add(actionCenterPanel, BorderLayout.CENTER);
		this.add(checkoutPanel,BorderLayout.EAST);
		
	}

	@Override
	public void setLight() {
		// Panels
		this.setBackground(Light.BG.getColor());

		// Checkout
		this.checkoutPanel.setLight();
		
		// Buttons
		undoButton.setIcon(Decorations.getImg(IMG_UNDO_LIGHT));
		redoButton.setIcon(Decorations.getImg(IMG_REDO_LIGHT));
		connectionButton.setBackground(Light.BLUE.getColor());
		connectionButton.setForeground(Light.WHITE.getColor());
		connectionButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));		
	}

	@Override
	public void setDark() {
		//Panels
		this.setBackground(Dark.BG.getColor());

		// Checkout
		this.checkoutPanel.setDark();
		// Buttons
		undoButton.setIcon(Decorations.getImg(IMG_UNDO_DARK));
		redoButton.setIcon(Decorations.getImg(IMG_REDO_DARK));
		connectionButton.setBackground(Dark.BLUE.getColor());
		connectionButton.setForeground(Dark.FOREGROUNG.getColor());
		connectionButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));	
	}

	@Override
	public void setLanguage(ResourceBundle rb) {
		this.checkoutPanel.setLanguage(rb);
		
		connectionButton.setText(rb.getString("login_in"));
	
	}
}
