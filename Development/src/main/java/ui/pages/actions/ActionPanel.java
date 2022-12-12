package ui.pages.actions;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.utils.Decorations;
import ui.utils.KeyboardDialog;
import ui.utils.Resources;
import ui.utils.observer.colortheme.ColorThemes;
import ui.utils.observer.colortheme.IColorThemeObserver;
import ui.utils.observer.colortheme.palettes.Dark;
import ui.utils.observer.colortheme.palettes.Light;
import ui.utils.observer.multilingual.IMultilingualObserver;
import ui.GUIComponent;
import ui.mainframe.MainFrame;
import ui.pages.FilmPage;
import ui.pages.SearchPage;
import ui.pages.cart.CartPanel;

@SuppressWarnings("serial")
public class ActionPanel extends JPanel implements GUIComponent, IMultilingualObserver, IColorThemeObserver {

    /* Actions */
    public static final String ACTION_UNDO = "Undo";
    public static final String ACTION_REDO = "Redo";
    /* Images */
    public static final String IMG_UNDO_LIGHT = "undoLight";
    public static final String IMG_UNDO_DARK = "undoDark";
    public static final String IMG_REDO_LIGHT = "redoLight";
    public static final String IMG_REDO_DARK = "redoDark";
    /* Search Items */
    private static final String[] BOX_ITEMS = {
        "Title",
        "Actor",
        "Director"
    };

    /* Components */
    private JPanel topPanel;
    /* Top Part */
    private JPanel searchPanel;
    private JTextField tfSearch;
    private JComboBox <String> filterCBox;
    private JPanel commandPanel;
    private JButton undoButton;
    private JButton redoButton;
    private JButton connectionButton;
    /* Center Part */
    private JPanel centerPanel;
    private FilmPage filmPage; 
    private SearchPage searchPage;
    /* Cart Part */
    private CartPanel cartPanel;
    
    /*Action pages */
    private HashMap <Integer, JPanel> actionPanels;
    private int currentActionPanel;
  
    private String placeholder;

    /**
     * Constructor of {@code ActionPanel}, represent the Cart, and several Command buttons.
     * @author MathysC
     */
    public ActionPanel() {
	topPanel = new JPanel(new BorderLayout());
	searchPanel = new JPanel(new FlowLayout());
	tfSearch = new JTextField();
	filterCBox = new JComboBox <> (BOX_ITEMS);
	commandPanel = new JPanel(new FlowLayout());
	undoButton = new JButton();
	redoButton = new JButton();
	connectionButton = new JButton();
	centerPanel = new JPanel(new BorderLayout());
	filmPage = new FilmPage(); 
	searchPage = new SearchPage();
	cartPanel = new CartPanel();
	this.createGUI();
	
        /* Initialize subPanel map */
	actionPanels = new HashMap<>();
        actionPanels.put(MainFrame.ID_RESULT_PAGE, searchPage);
        actionPanels.put(MainFrame.ID_FILM_PAGE, filmPage);
        currentActionPanel = MainFrame.ID_RESULT_PAGE;
   }

    /**
     * Remove the current subAction Panel and add another one.
     * @author MathysC
     *
     * @param id The ID of the Page to show.
     */
    public void changeCurrentActionPage(int id) {
        this.centerPanel.remove(this.actionPanels.get(currentActionPanel));
        this.centerPanel.add(this.actionPanels.get(id), BorderLayout.CENTER);
        currentActionPanel = id;
    }

    /**
     * @author MathysC
     * @return the undo Button
     */
    public JButton getUndoButton() {
        return undoButton;
    }

    /**
     * @author MathysC
     * @return the redo Button
     */
    public JButton getRedoButton() {
        return redoButton;
    }

    /**
     * @author MathysC
     * @return the connection Button
     */
    public JButton getConnectionButton() {
        return connectionButton;
    }
    
    /**
     * @author MathysC
     * @return the cartPanel
     */
    public CartPanel getCartPanel() {
	return cartPanel;
    }

    /**
     * @author MathysC
     * @return the searchPage
     */
    public SearchPage getSearchPage() {
	return searchPage;
    }

    /**
     * @author MathysC
     * @return the filmPage
     */
    public FilmPage getFilmPage() {
	return filmPage;
    }

    @Override
    public void createGUI() {
	this.setLayout(new BorderLayout());
        centerPanel.setOpaque(false);
        topPanel.setOpaque(false);
        searchPanel.setOpaque(false);
        commandPanel.setOpaque(false);

        /* Search Panel */
        // Search TextField
        tfSearch.setColumns(25);
        tfSearch.setPreferredSize(Decorations.sizeConverter(new Dimension(500, 40)));
        tfSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
        	// if text is default value
        	if (tfSearch.getText().equals(placeholder)) {
        	    Decorations.resetDefaultPlaceholder(tfSearch);
        	}
        	// Get new text
        	String prompt = KeyboardDialog.showKeyboardDialog(placeholder, tfSearch);
 
        	// If empty prompt
        	if (prompt.equals("")) {
        	    Decorations.setDefaultPlaceholder(tfSearch, placeholder);
        	} else {
        	    tfSearch.setText(prompt);
        	}
            }
        });
        
        searchPanel.add(tfSearch);

        // Filter ComboxBox
        filterCBox.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 18));
        
        /**
         *  This itemListener is implemented in order to remove the focus
         * from the combobox. Because with the focus on the selected Item: 
         * the foreground is black and we need it to stay white at any time.
         * @author MathysC
         */
        filterCBox.addItemListener(e -> ((Component)e.getSource()).getParent().requestFocus());
        searchPanel.add(filterCBox);

        /**
         * This is not the best way to center both Jcomponents...
         * But i couldn’t find the right way to do it.
         */
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(searchPanel);
        leftPanel.add(Box.createVerticalGlue());
        topPanel.add(leftPanel, BorderLayout.WEST);

        /* Command Panel */
        // Undo Button
        undoButton.setActionCommand(ACTION_UNDO);
        undoButton.setContentAreaFilled(false); // Transparent
        undoButton.setBorderPainted(false); // without border
        commandPanel.add(undoButton);

        // Redo Button
        redoButton.setActionCommand(ACTION_REDO);
        redoButton.setContentAreaFilled(false); // Transparent
        redoButton.setBorderPainted(false); // without border
        commandPanel.add(redoButton);

        // Connection Button
//      TODO #39
//      connectionButton.setActionCommand(); 
        connectionButton.setPreferredSize(Decorations.getDefaultButtonDimension());
        connectionButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));
        commandPanel.add(connectionButton);

        topPanel.add(commandPanel, BorderLayout.EAST);

        centerPanel.add(topPanel, BorderLayout.NORTH);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(cartPanel, BorderLayout.EAST);
        
    }
    
    @Override
    public void setLanguage(ResourceBundle rb) {
        connectionButton.setText(rb.getString("login_in"));

        // If the field wasn’t change. Change the placeholder
        if (tfSearch.getText().equals(placeholder))
            Decorations.setDefaultPlaceholder(tfSearch, rb.getString("action_search"));

        // And change the String value for condition in FocusListener.        
        placeholder = rb.getString("action_search");
    }

    @Override
    public void setColorTheme(ColorThemes colorTheme) {
	switch(colorTheme) {
	    case LIGHT_THEME:
	        // Panels
	        this.setBackground(Light.BG.getColor());
	        
	        tfSearch.setBackground(Light.REVERSE_BG.getColor());
	        tfSearch.setBorder(BorderFactory.createLineBorder(Light.BLACK.getColor(), 1));
	        tfSearch.setForeground(Light.BLACK.getColor());        
	        
	        filterCBox.setBackground(Light.BLUE.getColor());
	        filterCBox.setForeground(Light.WHITE.getColor());

	        // Buttons
	        undoButton.setIcon(Resources.getImg(IMG_UNDO_LIGHT));
	        redoButton.setIcon(Resources.getImg(IMG_REDO_LIGHT));
	        connectionButton.setBackground(Light.BLUE.getColor());
	        connectionButton.setForeground(Light.WHITE.getColor());
	        
		break;
	    case DARK_THEME:
		//Panels
	        this.setBackground(Dark.BG.getColor());
	        
	        tfSearch.setBackground(Dark.PURPLE.getColor());
	        tfSearch.setForeground(Dark.FOREGROUND.getColor());
	        tfSearch.setBorder(BorderFactory.createLineBorder(Dark.PINK.getColor(), 1));
	        
	        filterCBox.setBackground(Dark.BLUE.getColor());
	        filterCBox.setForeground(Dark.FOREGROUND.getColor());
	        
	        // Buttons
	        undoButton.setIcon(Resources.getImg(IMG_UNDO_DARK));
	        redoButton.setIcon(Resources.getImg(IMG_REDO_DARK));
	        connectionButton.setBackground(Dark.BLUE.getColor());
	        connectionButton.setForeground(Dark.FOREGROUND.getColor());

		break;
	    default:
		break;
	    }	
    }
}