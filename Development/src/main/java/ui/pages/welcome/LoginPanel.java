package ui.pages.welcome;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ui.GUIComponent;
import ui.utils.Decorations;
import ui.utils.KeyboardDialog;
import ui.utils.observer.colortheme.ColorThemes;
import ui.utils.observer.colortheme.IColorThemeObserver;
import ui.utils.observer.colortheme.palettes.Dark;
import ui.utils.observer.colortheme.palettes.Light;
import ui.utils.observer.multilingual.IMultilingualObserver;

/**
 * LoginPanel is part of the WelcomePage.
 * Instantiate all JComponent to allow the user to connect.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
class LoginPanel extends JPanel implements GUIComponent, IMultilingualObserver, IColorThemeObserver {

    /*Options*/
    private static final int MAX_WIDTH = 350;
    private static final int WIDTH = (int)(MAX_WIDTH * 0.9);
    private static final int TEXTFIELD_HEIGHT = 50;
    private static final int BUTTON_HEIGHT = 75;
    // Size of rigid areas placed between JComponent to create gaps.
    private static final Dimension SMALL_RIGID = Decorations.sizeConverter(new Dimension(25, 25));
    private static final Dimension BIG_RIGID = Decorations.sizeConverter(new Dimension(75, 75));

    /* Components */
    private JLabel loginLabel;
    private JTextField tfID;
    private JPasswordField tfPassword;
    private JButton connection;
    private JButton createAccount;
    private JButton continueWithoutConn;
    /* Placeholders */
    private String phID = "";
    private String phPWD = "";
    /* Dialog titles */
    private String keyboardTitleID = "";
    private String keyboardTitlePWD = "";

    /**
     * Constructor of {@code LoginPanel} 
     * Set JPanel options and add Components.
     * @author MathysC
     *
     */
    public LoginPanel() {
	loginLabel = new JLabel();
	tfID = new JTextField();
	tfPassword = new JPasswordField();
	connection = new JButton();
	createAccount = new JButton();
	continueWithoutConn = new JButton();
	this.createGUI();
	
    }
    
    /**
     * Private method to better reading of the constructor.
     * Set options of the given {@code component}.
     * @author MathysC
     *
     * @param component The component to set.
     * @param width	The Width of the component.
     * @param height The Height of the component.
     * @param alignment The alignment of the component.
     */
    private void setComponent(JComponent component, int width, int height, float alignment) {
        component.setAlignmentX(alignment);
        component.setMinimumSize(Decorations.sizeConverter(new Dimension(width, height)));
        component.setPreferredSize(Decorations.sizeConverter(new Dimension(width, height)));
        component.setMaximumSize(Decorations.sizeConverter(new Dimension(width, height)));
        
    }
    
    @Override
    public void createGUI() {
        // JPanel options
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Set Login JLabel.
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginLabel.setBorder(new EmptyBorder(10, 20, 50, 20));
        loginLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));
        this.add(loginLabel);

        this.add(Box.createRigidArea(SMALL_RIGID));

        // Set ID JTextField.
        setComponent(tfID, WIDTH, TEXTFIELD_HEIGHT, Component.CENTER_ALIGNMENT);
        tfID.setColumns(25);

        tfID.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // if text is default value
                if (tfID.getText().equals(phID)) {
                    Decorations.resetDefaultPlaceholder(tfID);
                }
                // Get new text
                String prompt = KeyboardDialog.showKeyboardDialog(keyboardTitleID, tfID);
    
                // If empty prompt
                if (prompt.equals("")) {
                    Decorations.setDefaultPlaceholder(tfID, phID);
                } else {
                    tfID.setText(prompt);
                }
            }
        });

        this.add(tfID);

        this.add(Box.createRigidArea(SMALL_RIGID)); // Gap.

        // Set Password JPasswordField.
        setComponent(tfPassword, WIDTH, TEXTFIELD_HEIGHT, Component.CENTER_ALIGNMENT);
        tfPassword.setColumns(25);
        tfPassword.setEchoChar((char) 0);


        tfPassword.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // If nothing has been written. Remove the PlaceHolder.
                if (Arrays.equals(tfPassword.getPassword(), phPWD.toCharArray())) {
                    Decorations.resetDefaultPlaceholder(tfPassword);
                }

                // Show what’s written.
                tfPassword.setEchoChar((char) 0);

                // Get new prompt
                String prompt = KeyboardDialog.showKeyboardDialog(keyboardTitlePWD, tfPassword);

                // If empty prompt
                if (prompt.equals("")) {
                    // Set to default
                    Decorations.setDefaultPlaceholder(tfPassword, phPWD);
                } else {
                    // Hide what’s written.
                    tfPassword.setEchoChar('*');
                    tfPassword.setText(prompt);
                }
            }
        });

        this.add(tfPassword);

        this.add(Box.createRigidArea(SMALL_RIGID)); // Gap.

        // Set connection JButton.
        setComponent(connection, WIDTH / 2, BUTTON_HEIGHT / 2, Component.LEFT_ALIGNMENT); // Component.LEFT_ALIGNMENT but a bit centered.
        this.add(connection);

        this.add(Box.createRigidArea(BIG_RIGID)); // Gap.

        // Set create account JButton.
        setComponent(createAccount, WIDTH, BUTTON_HEIGHT, Component.CENTER_ALIGNMENT);
        this.add(createAccount);

        this.add(Box.createRigidArea(BIG_RIGID)); // Gap.

        // set continue without connection JButton
        setComponent(continueWithoutConn, WIDTH, BUTTON_HEIGHT, Component.CENTER_ALIGNMENT);
        this.add(continueWithoutConn);

        this.setPreferredSize(new Dimension(MAX_WIDTH, 100));
        
    }


    /**
     * {@inheritDoc}
     * @author MathysC
     *
     */
    @Override
    public void setLanguage(ResourceBundle rb) {

        // Label
        loginLabel.setText(rb.getString("login_label"));

        // ID JTextField
        // If the field wasn’t change. Change the placeholder
        if (tfID.getText().equals(phID))
            Decorations.setDefaultPlaceholder(tfID, rb.getString("login_id_placeholder"));

        // And change the String value for condition in FocusListener.
        phID = rb.getString("login_id_placeholder");

        // Password JPasswordField
        // If the field wasn’t change. Change the placeholder
        if (Arrays.equals(tfPassword.getPassword(), phPWD.toCharArray()))
            Decorations.setDefaultPlaceholder(tfPassword, rb.getString("login_pw_placeholder"));
        // And change the String value for condition in FocusListener.
        phPWD = rb.getString("login_pw_placeholder");

        // connection JButton
        connection.setText(rb.getString("login_in"));

        // createAccount JButton
        createAccount.setText(rb.getString("login_up"));

        // continue Without an Account JButton
        continueWithoutConn.setText(rb.getString("login_without"));

        // KeyboardDialog
        keyboardTitleID = rb.getString("login_id_vk_frameName");
        keyboardTitlePWD = rb.getString("login_pw_vk_frameName");
    }

    @Override
    public void setColorTheme(ColorThemes colorTheme) {
	switch(colorTheme) {
	    case LIGHT_THEME:
		// This JPanel
	        this.setBackground(Light.WHITE.getColor());
	        this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Light.BLACK.getColor()));

	        // Label
	        this.loginLabel.setForeground(Light.BLACK.getColor());
	        // ID TextField
	        this.tfID.setBackground(Light.REVERSE_BG.getColor());
	        this.tfID.setBorder(BorderFactory.createLineBorder(Light.BLACK.getColor(), 1));
	        this.tfID.setForeground(Light.BLACK.getColor());

	        // Password TextField
	        this.tfPassword.setBackground(Light.REVERSE_BG.getColor());
	        this.tfPassword.setBorder(BorderFactory.createLineBorder(Light.BLACK.getColor(), 1));
	        this.tfPassword.setForeground(Light.BLACK.getColor());

	        // Connection Button
	        this.connection.setBackground(Light.BLUE.getColor());
	        this.connection.setForeground(Light.WHITE.getColor());
	        this.connection.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));

	        // CreateAccount Button
	        this.createAccount.setBackground(Light.BLUE.getColor());
	        this.createAccount.setForeground(Light.WHITE.getColor());
	        this.createAccount.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 18));

	        // ContinueWithoutConn Button
	        this.continueWithoutConn.setBackground(Light.REVERSE_BG.getColor());
	        this.continueWithoutConn.setForeground(Light.REVERSE_FG.getColor());
	        this.continueWithoutConn.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 18));
	        this.continueWithoutConn.setBorder(BorderFactory.createLineBorder(Light.BLACK.getColor(), 1));

		break;
	    case DARK_THEME:
		// This JPanel
	        this.setBackground(Dark.BG.getColor());
	        this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Dark.FOREGROUND.getColor()));

	        // Label
	        this.loginLabel.setForeground(Dark.FOREGROUND.getColor());

	        // Id TextField
	        this.tfID.setBackground(Dark.PURPLE.getColor());
	        this.tfID.setForeground(Dark.FOREGROUND.getColor());
	        this.tfID.setBorder(BorderFactory.createLineBorder(Dark.PINK.getColor(), 1));

	        // Password TextField
	        this.tfPassword.setBackground(Dark.PURPLE.getColor());
	        this.tfPassword.setForeground(Dark.FOREGROUND.getColor());
	        this.tfPassword.setBorder(BorderFactory.createLineBorder(Dark.PINK.getColor(), 1));

	        // Connection Button
	        this.connection.setBackground(Dark.BLUE.getColor());
	        this.connection.setForeground(Dark.FOREGROUND.getColor());
	        this.connection.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));

	        // CreateAccount Button
	        this.createAccount.setBackground(Dark.BLUE.getColor());
	        this.createAccount.setForeground(Dark.FOREGROUND.getColor());
	        this.createAccount.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 18));

	        // ContinueWithoutConn Button
	        this.continueWithoutConn.setBackground(Dark.PURPLE.getColor());
	        this.continueWithoutConn.setForeground(Dark.FOREGROUND.getColor());
	        this.continueWithoutConn.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 18));
	        this.continueWithoutConn.setBorder(BorderFactory.createLineBorder(Dark.PINK.getColor(), 1));

		break;
	    default:
		break;
	    }
	
    }
}