package ui.pages.actions;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fc.films.AgeRestriction;
import fc.films.Categories;
import fc.films.Film;
import ui.utils.Decorations;
import ui.utils.bundles.Multilingual;
import ui.utils.colors.ColorTheme;
import ui.utils.colors.Dark;
import ui.utils.colors.Light;
import ui.mainframe.MainFrame;
import ui.pages.SearchPage;

@SuppressWarnings("serial")
public class ActionPanel extends JPanel implements Multilingual, ColorTheme {

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
    protected JComboBox < String > filterCBox = new JComboBox < > (boxItems);
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
    private HashMap < Integer, JPanel > subActionsPanel = new HashMap < > ();
//  TODO #39 change JPanel when FilmPage added
    protected JPanel filmPage = new JPanel(); 
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

    /* Checkout Part */
    protected CheckoutPanel checkoutPanel = new CheckoutPanel();
    
    /**
     * Constructor of ActionPanel, represent the Cart, and several Command buttons.
     * @author MathysC
     *
     */
    public ActionPanel() {
	this.createGUI();
        /*Initialize subPanel map*/
        subActionsPanel.put(MainFrame.ID_RESULT_PAGE, searchPage);
        subActionsPanel.put(MainFrame.ID_FILM_PAGE, filmPage);
        current_subAction = MainFrame.ID_RESULT_PAGE;

//         TODO #29 REMOVE AFTER TEST
//         Film film = new Film("toto", "toto tutu tata", new String[]{"JM COCO","PE SOSO"}, "DIDI", "DODO", AgeRestriction.MINUS12, new Categories[] {Categories.DRAMAS, Categories.COMEDIES});
//         this.checkoutPanel.addToCart(new Film("toto", "toto tutu tata", new String[]{"JM COCO","PE SOSO"}, "DIDI", "DODO", AgeRestriction.MINUS12, new Categories[] {Categories.DRAMAS, Categories.COMEDIES}), "1");

    }

    private void createGUI() {
	this.setLayout(new BorderLayout());
        actionCenterPanel.setOpaque(false);
        topPanel.setOpaque(false);
        searchPanel.setOpaque(false);
        commandPanel.setOpaque(false);

        /* Search Panel */
        // Search TextField
//         TODO #29 Add MouseListener
//         TODO #29 Add KeyboardDialog
        tfSearch.setColumns(30);
        searchPanel.add(tfSearch);

        // Filter ComboxBox
//         TODO #29 Correct Size and Color Combobox
        filterCBox.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 18));
        
        /* This itemListener is implemented in order to remove the focus
         * from the combobox. Because with the focus on the selected Item: 
         * the foreground is black and we need it to stay white at any time.
         */
        filterCBox.addItemListener(e -> ((Component)e.getSource()).getParent().requestFocus());
        searchPanel.add(filterCBox);

        topPanel.add(searchPanel, BorderLayout.WEST);

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
        this.add(checkoutPanel, BorderLayout.EAST);
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

    @Override
    public void setLight() {
        // Panels
        this.setBackground(Light.BG.getColor());
        
        filterCBox.setBackground(Light.BLUE.getColor());
        filterCBox.setForeground(Light.WHITE.getColor());

        // Buttons
        undoButton.setIcon(Decorations.getImg(IMG_UNDO_LIGHT));
        redoButton.setIcon(Decorations.getImg(IMG_REDO_LIGHT));
        connectionButton.setBackground(Light.BLUE.getColor());
        connectionButton.setForeground(Light.WHITE.getColor());

        checkoutPanel.setLight();
        searchPage.setLight();
    }

    @Override
    public void setDark() {
	//Panels
        this.setBackground(Dark.BG.getColor());
        
        filterCBox.setBackground(Dark.BLUE.getColor());
        filterCBox.setForeground(Dark.FOREGROUND.getColor());
        // Buttons
        undoButton.setIcon(Decorations.getImg(IMG_UNDO_DARK));
        redoButton.setIcon(Decorations.getImg(IMG_REDO_DARK));
        connectionButton.setBackground(Dark.BLUE.getColor());
        connectionButton.setForeground(Dark.FOREGROUND.getColor());

        checkoutPanel.setDark();
        searchPage.setDark();
    }

    @Override
    public void setLanguage(ResourceBundle rb) {
        connectionButton.setText(rb.getString("login_in"));
        checkoutPanel.setLanguage(rb);
        searchPage.setLanguage(rb);
    }
}