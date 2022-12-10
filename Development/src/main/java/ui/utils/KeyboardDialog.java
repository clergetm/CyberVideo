package ui.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.GUIComponent;
import ui.utils.observer.colortheme.ColorThemes;
import ui.utils.observer.colortheme.IColorThemeObserver;
import ui.utils.observer.colortheme.palettes.Dark;
import ui.utils.observer.colortheme.palettes.Light;
import ui.utils.observer.multilingual.IMultilingualObserver;

/**
 * Class that implement a Virtual Keyboard that the client can use to type in TextFields.
 * @author MathysC
 */
@SuppressWarnings("serial")
public class KeyboardDialog extends JDialog implements GUIComponent, ActionListener, IMultilingualObserver, IColorThemeObserver {

    /* Actions */
    public static final String VAL_ACT = "Validate";
    public static final String BSPACE_ACT = "Back_space";
    public static final String SPACE_ACT = "Space";
    /* Icons */
    public static final String ICO_KEYBOARD = "Keyboard";
    /* Characters */
    // Alphabet in lower and upper cases.
    private static final int UPPERCASE_A = 65;
    private static final int UPPERCASE_Z = 91;
    private static final int LOWERCASE_A = 97;
    private static final int LOWERCASE_Z = 123;
    private static final char[] SPECIAL_CHARS = {
        '!', '@', '#', '$', '%',
        '&', '*', '(', ')', '-',
        '_', '=', '+', '[', ']',
        '{', '}', '\\', '|', ',',
        '.', '<', '>', '/', '?',
        ';', ':' };
    
    /* Components */
    private JTextField textField;
    private JPanel keysPanel;
    private JPanel specialPanel;
    private JPanel actionsPanel;
    private JPanel lowerCasePanel;
    private JPanel upperCasePanel;
    private JButton valButton;
    private JButton bspaceButton;
    private JButton spaceButton;
    private JButton[] actionButtons;
       
    private String entry = ""; // text of the TextField.
    private static KeyboardDialog instance = null;
    
    /**
     * Constructor of {@code VirtualKeyboard} 
     * Set JDialog options and add Components.
     * @author MathysC
     *
     */
    private KeyboardDialog() {
        // Set Options.
        super(null, Dialog.ModalityType.APPLICATION_MODAL);
        this.setIconImage(Resources.getIco(ICO_KEYBOARD));
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocation(10, 400);
        
        textField = new JTextField(100);
       	keysPanel = new JPanel();
       	
       	lowerCasePanel = new JPanel();
       	upperCasePanel = new JPanel();
       	specialPanel = new JPanel();
       	actionsPanel = new JPanel();
       	
       	valButton = new JButton();
       	bspaceButton = new JButton();
       	spaceButton = new JButton();
       	actionButtons =  new JButton []{ valButton, bspaceButton, spaceButton };
       
       	this.createGUI();
    }
        
    /**
     * Lazy Singleton Pattern implementation.
     * @author MathysC
     *
     * @return the one and only instance of the {@code KeyBoardDialog}
     */
    public static KeyboardDialog getInstance() {
	if(instance == null) {
	    instance = new KeyboardDialog();
	}
	
	return instance;
    }

    /**
     * Getter of the entry of the JTextField.
     * @author MathysC
     *
     * @return The entry String.
     */
    private String getEntry() {
        return this.entry;
    }

    /**
     * Show the {@code KeyboardDialog} then when the client close the dialog (close it or validate it), the {@code entry} is returned
     * @author MathysC
     *
     * @param title The title of this KeyboardDialog.
     * @param linked The JTextField linked to this KeyboardDialog.
     * @return The written entry.
     */
    public static String showKeyboardDialog(String title, JTextField linked) {
        KeyboardDialog keyboard = getInstance();
        keyboard.setTitle(title);
        keyboard.textField = linked;
        keyboard.entry = linked.getText();
        keyboard.setVisible(true);
        return keyboard.getEntry();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            // Close the Dialog.
        case KeyboardDialog.VAL_ACT:
            this.setVisible(false);
            break;
            // Remove the last character.
        case KeyboardDialog.BSPACE_ACT:
            if(entry.length() > 0) this.entry = entry.substring(0, entry.length() - 1);
            this.textField.setText(entry);
            break;
            // Add a space.
        case KeyboardDialog.SPACE_ACT:
            this.entry += " ";
            this.textField.setText(entry);
            break;
            // Add a character.
        default:
            this.entry += ((JButton) e.getSource()).getText();
            this.textField.setText(entry);
            break;
        }
    }
    
    @Override
    public void createGUI() {
        // Add Components.
        keysPanel.setLayout(new BoxLayout(keysPanel, BoxLayout.Y_AXIS));
        
        /* Implements the lowercase alphabet. */
        for (int letter = LOWERCASE_A; letter < LOWERCASE_Z; letter++) {
            JButton button = new JButton("" + ((char) letter));
            button.addActionListener(this);
            lowerCasePanel.add(button);
        }
        
        /* Implements the uppercase alphabet. */
        for (int letter = UPPERCASE_A; letter < UPPERCASE_Z; letter++) {
            JButton button = new JButton("" + ((char) letter));
            button.addActionListener(this);
            upperCasePanel.add(button);
        }
        
        /* Implements the special characters. */
        for (char c: SPECIAL_CHARS) {
            JButton button = new JButton("" + c);
            button.addActionListener(this);
            specialPanel.add(button);
        }
        
        keysPanel.add(lowerCasePanel);
        keysPanel.add(upperCasePanel);
        keysPanel.add(specialPanel);
        this.add(keysPanel, BorderLayout.CENTER);
       
        /* Implements the actions buttons. */
        final String[] actionCommands = { VAL_ACT, BSPACE_ACT, SPACE_ACT };

        actionsPanel = new JPanel(new GridLayout(3, 0));
        actionsPanel.setBorder(Decorations.getDefaultBorder());
        for (int index = 0; index < actionCommands.length; index++) {
            this.actionButtons[index].setActionCommand(actionCommands[index]);
            this.actionButtons[index].addActionListener(this);
            actionsPanel.add(this.actionButtons[index]);
        }
        this.add(actionsPanel, BorderLayout.EAST);
       
        this.pack();
    }
    
    /**
     * Set the action button texts in chosen Language.
     * @author MathysC
     *
     */
    @Override
    public void setLanguage(ResourceBundle rb) {
	String[] multilingualLabels = rb.getString("VK_ActionsKeys").split(" ");
        for (int index = 0; index < multilingualLabels.length; index++) {
            this.actionButtons[index].setText(multilingualLabels[index]);
        }
    }

    @Override
    public void setColorTheme(ColorThemes colorTheme) {
	switch(colorTheme) {
	case LIGHTTHEME:
	    // This Dialog.
	    this.setBackground(Light.BG.getColor());

	    // Characters.
	    for(Component panel :this.keysPanel.getComponents()) {
		panel.setBackground(Light.BG.getColor());
		for(Component key : ((JPanel) panel).getComponents()) {
		    key.setBackground(Light.BLUE.getColor());
		    key.setForeground(Light.WHITE.getColor());
		}
	    }
		
	    // Actions Panel.
	    this.actionsPanel.setBackground(Light.BG.getColor());
		
	    // Validate Button.
	    this.valButton.setBackground(new Color(217, 221, 146));
	    this.valButton.setForeground(Light.BLACK.getColor());

	    // Back Space Button.
	    this.bspaceButton.setBackground(new Color(238, 99, 82));
	    this.bspaceButton.setForeground(Light.WHITE.getColor());
		
	    // Space Button.
	    this.spaceButton.setBackground(Light.WHITE.getColor());
	    this.spaceButton.setForeground(Light.REVERSE_FG.getColor());
	    break;
	case DARKTHEME:
	    // This Dialog.
	    this.setBackground(Dark.BG.getColor());
	    // Characters.
	    for(Component panel :this.keysPanel.getComponents()) {
		panel.setBackground(Dark.BG.getColor());
		for(Component key : ((JPanel) panel).getComponents()) {
		    key.setBackground(Dark.BLUE.getColor());
		    key.setForeground(Dark.FOREGROUND.getColor());
		}
	    }
		
	    // Actions Panel.
	    this.actionsPanel.setBackground(Dark.BG.getColor());
	    
	    // Validate Button.
	    this.valButton.setBackground(new Color(127, 176, 105));
	    this.valButton.setForeground(Dark.FOREGROUND.getColor());

	    // Back Space Button.
	    this.bspaceButton.setBackground(new Color(211, 97, 53));
	    this.bspaceButton.setForeground(Dark.FOREGROUND.getColor());
		
	    // Space Button.
	    this.spaceButton.setBackground(Dark.PURPLE.getColor());
	    this.spaceButton.setForeground(Dark.FOREGROUND.getColor());
		
	    break;
	    
	default:
	    break;
	}

    }

}