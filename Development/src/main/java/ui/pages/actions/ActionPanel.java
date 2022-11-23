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
import ui.utils.colors.ColorTheme;
import ui.utils.observer.colortheme.palettes.Dark;
import ui.utils.observer.colortheme.palettes.Light;
import ui.utils.observer.multilingual.IMultilingualObserver;
import ui.mainframe.MainFrame;
import ui.managers.UIManager;
import ui.pages.FilmPage;
import ui.pages.SearchPage;
import ui.pages.cart.CartPanel;

@SuppressWarnings("serial")
public class ActionPanel extends JPanel implements IMultilingualObserver, ColorTheme {

    /* Search Items */
    public static final String[] boxItems = {
        "Title",
        "Actor",
        "Director"
    };

    /* Action Center Panel Part*/
    protected JPanel actionCenterPanel = new JPanel(new BorderLayout());
    protected JPanel topPanel = new JPanel(new BorderLayout());

    /* Top Panel Components */
    protected JPanel searchPanel = new JPanel(new FlowLayout());
    protected JTextField tfSearch = new JTextField();
    private String placeholder = "";
    protected JComboBox <String> filterCBox = new JComboBox <> (boxItems);
    protected JPanel commandPanel = new JPanel(new FlowLayout());
    protected JButton undoButton = new JButton();
    protected JButton redoButton = new JButton();
    protected JButton connectionButton = new JButton();

    /*Action pages */
    private HashMap < Integer, JPanel > subActionsPanel = new HashMap < > ();
//  TODO #39 change JPanel when FilmPage added
    protected FilmPage filmPage = new FilmPage(); 
    protected SearchPage searchPage = new SearchPage();
    private int current_subAction;
    /*Action actions*/
    public static final String ACTION_UNDO = "Undo";
    public static final String ACTION_REDO = "Redo";

    /* Images */
    public static final String IMG_UNDO_LIGHT = "undoLight";
    public static final String IMG_UNDO_DARK = "undoDark";
    public static final String IMG_REDO_LIGHT = "redoLight";
    public static final String IMG_REDO_DARK = "redoDark";

    /* Cart Part */
    protected CartPanel cartPanel = new CartPanel();

    /**
     * Constructor of ActionPanel, represent the Cart, and several Command buttons.
     * @author MathysC
     *
     */
    public ActionPanel() {
//      TODO implement Client
//	public ActionPanel(Client client){
	this.createGUI();
        /* Initialize subPanel map */
        subActionsPanel.put(MainFrame.ID_RESULT_PAGE, searchPage);
        subActionsPanel.put(MainFrame.ID_FILM_PAGE, filmPage);
        current_subAction = MainFrame.ID_RESULT_PAGE;
   }

    private void createGUI() {
	this.setLayout(new BorderLayout());
        actionCenterPanel.setOpaque(false);
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

        actionCenterPanel.add(topPanel, BorderLayout.NORTH);

        this.add(actionCenterPanel, BorderLayout.CENTER);
        this.add(cartPanel, BorderLayout.EAST);
    }

    /**
     * Remove the current subAction Panel and add another one.
     * @author MathysC
     *
     * @param id The ID of the Page to show.
     */
    public void changeCurrentActionPage(int id) {
        this.actionCenterPanel.remove(this.subActionsPanel.get(current_subAction));
        this.actionCenterPanel.add(this.subActionsPanel.get(id), BorderLayout.CENTER);
        current_subAction = id;
    }

    public JButton getUndoButton() {
        return undoButton;
    }

    public JButton getRedoButton() {
        return redoButton;
    }

    public JButton getConnectionButton() {
        return connectionButton;
    }
    
    /**
     * @return the cartPanel
     */
    public CartPanel getCartPanel() {
	return cartPanel;
    }

    /**
     * @return the searchPage
     */
    public SearchPage getSearchPage() {
	return searchPage;
    }

    /**
     * @return the filmPage
     */
    public FilmPage getFilmPage() {
	return filmPage;
    }

    @Override
    public void setLight() {
        // Panels
        this.setBackground(Light.BG.getColor());
        
        tfSearch.setBackground(Light.REVERSE_BG.getColor());
        tfSearch.setBorder(BorderFactory.createLineBorder(Light.BLACK.getColor(), 1));
        tfSearch.setForeground(Light.BLACK.getColor());        
        
        filterCBox.setBackground(Light.BLUE.getColor());
        filterCBox.setForeground(Light.WHITE.getColor());

        // Buttons
        undoButton.setIcon(Decorations.getImg(IMG_UNDO_LIGHT));
        redoButton.setIcon(Decorations.getImg(IMG_REDO_LIGHT));
        connectionButton.setBackground(Light.BLUE.getColor());
        connectionButton.setForeground(Light.WHITE.getColor());
        
        cartPanel.setLight();
        searchPage.setLight();
    }

    @Override
    public void setDark() {
	//Panels
        this.setBackground(Dark.BG.getColor());
        
        tfSearch.setBackground(Dark.PURPLE.getColor());
        tfSearch.setForeground(Dark.FOREGROUND.getColor());
        tfSearch.setBorder(BorderFactory.createLineBorder(Dark.PINK.getColor(), 1));
        
        filterCBox.setBackground(Dark.BLUE.getColor());
        filterCBox.setForeground(Dark.FOREGROUND.getColor());
        
        // Buttons
        undoButton.setIcon(Decorations.getImg(IMG_UNDO_DARK));
        redoButton.setIcon(Decorations.getImg(IMG_REDO_DARK));
        connectionButton.setBackground(Dark.BLUE.getColor());
        connectionButton.setForeground(Dark.FOREGROUND.getColor());

        cartPanel.setDark();
        searchPage.setDark();
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
}