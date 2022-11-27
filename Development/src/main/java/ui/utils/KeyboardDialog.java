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
public class KeyboardDialog extends JDialog implements ActionListener, IMultilingualObserver, IColorThemeObserver {

    private String entry = ""; // text of the TextField.

    /* Characters */
    // Alphabet in lower and upper cases.
    private static final int UPPERCASE_A = 65;
    private static final int UPPERCASE_Z = 91;
    private static final int LOWERCASE_A = 97;
    private static final int LOWERCASE_Z = 123;
    private final char[] specialChars = {
        '!', '@', '#', '$', '%',
        '&', '*', '(', ')', '-',
        '_', '=', '+', '[', ']',
        '{', '}', '\\', '|', ',',
        '.', '<', '>', '/', '?',
        ';', ':' };
    
    /* Components */
    private JTextField textField = new JTextField(100);
    private JPanel keysPanel = new JPanel();
    private JPanel specialPanel = new JPanel();
    private JPanel actionsPanel = new JPanel();
    private JPanel lowerCasePanel = new JPanel();
    private JPanel upperCasePanel = new JPanel();
    private JButton valButton = new JButton();
    private JButton bspaceButton = new JButton();
    private JButton spaceButton = new JButton();
    private JButton[] actionButtons = {
        valButton,
        bspaceButton,
        spaceButton
    };
    
    /* Actions */
    public static final String VAL_ACT = "Validate";
    public static final String BSPACE_ACT = "Back_space";
    public static final String SPACE_ACT = "Space";
    
    /* Icons */
    public static final String ICO_KEYBOARD = "Keyboard";
	
    private static KeyboardDialog instance;
    
    /**
     * Constructor of {@code VirtualKeyboard} 
     * Set JDialog options and add Components.
     * @author MathysC
     *
     */
    private KeyboardDialog() {
        // Set Options.
        super(null, Dialog.ModalityType.APPLICATION_MODAL);
        this.createGUI();
    }
    private void createGUI() {
	this.setIconImage(Resources.getIco(ICO_KEYBOARD));
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocation(10, 400);

        // Add Components.
        keysPanel.setLayout(new BoxLayout(keysPanel, BoxLayout.Y_AXIS));
        
        lowerCasePanel = getLowerCaseKeys();
        upperCasePanel = getUpperCaseKeys();
        specialPanel = getSpecialKeys();
        keysPanel.add(lowerCasePanel);
        keysPanel.add(upperCasePanel);
        keysPanel.add(specialPanel);
        this.add(keysPanel, BorderLayout.CENTER);

        actionsPanel = getActionKeys();
        this.add(actionsPanel, BorderLayout.EAST);
       
        this.pack();
    }

    public static KeyboardDialog getInstance() {
	if(instance == null) {
	    instance = new KeyboardDialog();
	}
	
	return instance;
    }
    /**
     * Implements the lowercase alphabet.
     * Method implemented for better readability.
     * @author MathysC
     * 
     * @return A JPanel of lowercased letters
     */
    private JPanel getLowerCaseKeys() {
        JPanel lowerKeys = new JPanel();
        for (int letter = LOWERCASE_A; letter < LOWERCASE_Z; letter++) {
            JButton button = new JButton("" + ((char) letter));
            button.addActionListener(this);
            lowerKeys.add(button);
        }
        return lowerKeys;
    }

    /**
     * Implements the uppercase alphabet.
     * Method implemented for better readability.
     * @author MathysC
     * 
     * @return A JPanel of uppercased letters
     */
    private JPanel getUpperCaseKeys() {
        JPanel upperKeys = new JPanel();
        for (int letter = UPPERCASE_A; letter < UPPERCASE_Z; letter++) {
            JButton button = new JButton("" + ((char) letter));
            button.addActionListener(this);
            upperKeys.add(button);
        }
        return upperKeys;
    }

    /**
     * Implements the special characters.
     * Method implemented for better readability
     * @author MathysC
     *
     * @return A JPanel of special characters.
     */
    private JPanel getSpecialKeys() {
        JPanel specials = new JPanel();
        for (char c: specialChars) {
            JButton button = new JButton("" + c);
            button.addActionListener(this);
            specials.add(button);
        }
        return specials;
    }

    /**
     * Implements the actions buttons.
     * Method implemented for better readability
     * @author MathysC
     *
     * @return A JPanel of action buttons.
     */
    private JPanel getActionKeys() {
        final String[] actionCommands = {
            VAL_ACT,
            BSPACE_ACT,
            SPACE_ACT,
        };

        JPanel actions = new JPanel(new GridLayout(3, 0));
        actions.setBorder(Decorations.getDefaultBorder());
        for (int index = 0; index < actionCommands.length; index++) {
            this.actionButtons[index].setActionCommand(actionCommands[index]);
            this.actionButtons[index].addActionListener(this);
            actions.add(this.actionButtons[index]);
        }
        return actions;
    }

    /**
     * Getter of the entry of the JTextField.
     * @author MathysC
     *
     * @return The entry String.
     */
    public String getEntry() {
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