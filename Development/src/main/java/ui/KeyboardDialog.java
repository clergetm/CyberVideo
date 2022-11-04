package ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.Bundles.Multilingual;

/**
 * Class that implement a Virtual Keyboard that the client can use to type in TextFields.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class KeyboardDialog extends JDialog implements ActionListener, Multilingual {

    private String entry = ""; // text of the TextField.
    private static String[] multilingualLabels = {}; //  labels of action depend on Multilingual.

    /* Characters */
    // Alphabet in lower and upper cases.
    private final int beginUpperCase = 65, endUpperCase = 91, beginLowerCase = 97, endLowerCase = 123;
    private final char[] specialChars = {
        '!', '@', '#', '$', '%',
        '&', '*', '(', ')', '-',
        '_', '=', '+', '[', ']',
        '{', '}', '\\', '|', ',',
        '.', '<', '>', '/', '?',
        ';', ':', '~' };
    
    /* Components */
    private JTextField textField = new JTextField(100);
    private JPanel keysPanel = new JPanel();
    private JButton valButton = new JButton(),
        bspaceButton = new JButton(),
        spaceButton = new JButton(),
        UPCButton = new JButton(),
        LOCButton = new JButton();
    private JButton[] actionButtons = {
        valButton,
        bspaceButton,
        spaceButton,
        UPCButton,
        LOCButton
    };

    /* Actions */
    private static final String VAL_ACT = "Validate", // Action Names.
        BSPACE_ACT = "Back_space",
        SPACE_ACT = "Space";

    /**
     * Constructor of {@code VirtualKeyboard} 
     * Set JDialog options and add Components.
     * @author MathysC
     *
     */
    public KeyboardDialog() {
        // Set Options.
        super(null, Dialog.ModalityType.APPLICATION_MODAL);
        this.setIconImage(Decorations.ICO_KEYBOARD.getIco());
        setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLanguage(this.getRbEN());
        this.setLocation(10, 400);

        // Add Components.
        keysPanel.setLayout(new BoxLayout(keysPanel, BoxLayout.Y_AXIS));
        keysPanel.add(getLowerCaseKeys());
        keysPanel.add(getUpperCaseKeys());
        keysPanel.add(getSpecialKeys());
        this.add(keysPanel, BorderLayout.CENTER);

        //        JPanel actionsPanel = new JPanel(); // TODO: Find a way to put the component Vertically.
        //        actionsPanel = getActionKeys();
        //        this.add(actionsPanel, BorderLayout.EAST);
        this.add(getActionKeys(), BorderLayout.EAST);
        this.pack();
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
        for (int letter = beginLowerCase; letter < endLowerCase; letter++) {
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
        for (int letter = beginUpperCase; letter < endUpperCase; letter++) {
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

        JPanel actions = new JPanel();
        for (int index = 0; index < actionCommands.length; index++) {
            this.actionButtons[index] = new JButton(multilingualLabels[index]);
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
    public String showKeyboardDialog(String title, JTextField linked) {
        this.setTitle(title);
        this.textField = linked;
        this.entry = linked.getText();
        this.setVisible(true);
        return getEntry();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            // Close the Dialog.
        case KeyboardDialog.VAL_ACT:
            this.setVisible(false);
            break;
            // Remove the last char.
        case KeyboardDialog.BSPACE_ACT:
            this.entry = entry.substring(0, entry.length() - 1);
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
        KeyboardDialog.multilingualLabels = rb.getString("VK_ActionsKeys").split(" ");
        for (int index = 0; index < multilingualLabels.length - 1; index++) {
            this.actionButtons[index].setText(multilingualLabels[index]);
        }
    }
}