package ui.Pages.Welcome;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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

import ui.Decorations;
import ui.KeyboardDialog;
import ui.Bundles.Multilingual;
import ui.Colors.ColorTheme;
import ui.Colors.Dark;
import ui.Colors.Light;

/**
 * LoginPanel is part of the WelcomePage.
 * Instantiate all JComponent to allow the user to connect.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
class LoginPanel extends JPanel implements Multilingual, ColorTheme {

    private String placeholderID = "", placeholderPassword = "", keyboardTitleID = "", keyboardTitlePassword = "";

    /* Components */
    protected JLabel idLabel = new JLabel();
    protected JTextField idField = new JTextField();
    protected JPasswordField pwField = new JPasswordField();
    protected JButton connection = new JButton(), createAccount = new JButton(), continueWithoutConn = new JButton();
    protected KeyboardDialog keyboard = new KeyboardDialog();

    /*Options*/
    private final int maxW = 400, W = (int)(maxW * 0.9),
        HTextField = 50, HButton = 75;

    // Size of rigid areas placed between JComponent to create gaps.
    private final Dimension SMALLRIGID = new Dimension(25, 25);
    private final Dimension BIGRIGID = new Dimension(75, 75);

    /**
     * Constructor of {@code LoginPanel} 
     * Set JPanel options and add Components.
     * @author MathysC
     *
     */
    public LoginPanel() {
        // JPanel options
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Set Identification JLabel.
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        idLabel.setBorder(new EmptyBorder(10, 20, 50, 20));
        idLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));
        this.add(idLabel);

        this.add(Box.createRigidArea(SMALLRIGID));

        // Set Id JTextField.
        setComponent(idField, W, HTextField, Component.CENTER_ALIGNMENT);
        idField.setColumns(25);

        /**
         * FocusListener to handle the placeholder of the JTextField. 
         */
        idField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if (idField.getText().equals(""))
                    setPlaceHolder(idField, placeholderID);
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (idField.getText().equals(placeholderID))
                    unsetPlaceHolder(idField);
                idField.getRootPane().requestFocus(); // Change the focus to avoid looping              
                String prompt = keyboard.showKeyboardDialog(keyboardTitleID, idField);
                if (prompt.equals(""))
                    prompt = prompt;
                idField.setText(prompt);
            }
        });
        this.add(idField);

        this.add(Box.createRigidArea(SMALLRIGID)); // Gap.

        // Set Password JPasswordField.
        setComponent(pwField, W, HTextField, Component.CENTER_ALIGNMENT);
        pwField.setColumns(25);
        pwField.setEchoChar((char) 0);

        /**
         * FocusListener to handle the placeholder of the JPasswordField. 
         */
        pwField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                // If nothing has been written. Show the PlaceHolder.
                if (Arrays.equals(pwField.getPassword(), "".toCharArray())) {
                    pwField.setEchoChar((char) 0);
                    setPlaceHolder(pwField, placeholderPassword);
                }

            }

            @Override
            public void focusGained(FocusEvent e) {
                // If nothing has been written. Remove the PlaceHolder.
                if (Arrays.equals(pwField.getPassword(), placeholderPassword.toCharArray())) {
                    unsetPlaceHolder(pwField);
                }

                pwField.getRootPane().requestFocus(); // Change the focus to avoid looping
                pwField.setEchoChar((char) 0); // Show what written.
                // Type the entry with the Keyboard.
                String prompt = keyboard.showKeyboardDialog(keyboardTitlePassword, pwField);
                if (prompt.equals(""))
                    prompt = placeholderPassword;
                else
                    pwField.setEchoChar('*'); // Hide what’s written.

                pwField.setText(prompt);

                /* We hide the field here because of the requestFocus() (l: 130) call.
                 * If we set setEchoChar('*') in  focusLost Method, the entry will be displayed and immediately hidden.  
                 */
            }
        });
        this.add(pwField);

        this.add(Box.createRigidArea(SMALLRIGID)); // Gap.

        // Set connection JButton.
        setComponent(connection, W / 2, HButton / 2, Component.LEFT_ALIGNMENT); // Component.LEFT_ALIGNMENT but a bit centered.
        this.add(connection);

        this.add(Box.createRigidArea(BIGRIGID)); // Gap.

        // Set create account JButton.
        setComponent(createAccount, W, HButton, Component.CENTER_ALIGNMENT);
        this.add(createAccount);

        this.add(Box.createRigidArea(BIGRIGID)); // Gap.

        // set continue without connection JButton
        setComponent(continueWithoutConn, W, HButton, Component.CENTER_ALIGNMENT);
        this.add(continueWithoutConn);

        this.setMinimumSize(new Dimension(maxW, 100));
        this.setPreferredSize(new Dimension(maxW, 100));
        this.setMaximumSize(new Dimension(maxW, 100));
    }

    /**
     * Private method to better reading of the constructor.
     * Set options of the given {@code component}.
     * @author MathysC
     *
     * @param component The component to set.
     * @param W	The Width of the component.
     * @param H The Height of the component.
     * @param alignment The alignment of the component.
     */
    private void setComponent(JComponent component, int W, int H, float alignment) {
        component.setAlignmentX(alignment);
        component.setMinimumSize(new Dimension(W, H));
        component.setPreferredSize(new Dimension(W, H));
        component.setMaximumSize(new Dimension(W, H));
    }

    /**
     * Set the placeholder of a JTextField.
     * @author MathysC
     *
     * @param tf The JTextField component to set.
     * @param prompt The text to put as a placeholder.
     */
    private void setPlaceHolder(JTextField tf, String prompt) {
        tf.setFont(Decorations.FONT_PLACEHOLDER.getFont(Font.PLAIN, 16));
        tf.setText(prompt);
    }

    /**
     * Remove the placeholde of a JTextField.
     * @author MathysC
     *
     * @param tf The JTextField to unset.
     */
    private void unsetPlaceHolder(JTextField tf) {
        tf.setFont(Decorations.FONT_BASIC.getFont(Font.PLAIN, 16));
        tf.setText("");
    }

    /**
     * {@inheritDoc}
     * @author MathysC
     *
     */
    @Override
    public void setLanguage(ResourceBundle rb) {

        // Label
        idLabel.setText(rb.getString("login_label"));

        // ID JTextField
        // If the field wasn’t change. Change the placeholder
        if (idField.getText().equals(placeholderID))
            setPlaceHolder(idField, rb.getString("login_id_placeholder"));

        // And change the String value for condition in FocusListener.
        placeholderID = rb.getString("login_id_placeholder");

        // Password JPasswordField
        // If the field wasn’t change. Change the placeholder
        if (Arrays.equals(pwField.getPassword(), placeholderPassword.toCharArray()))
            setPlaceHolder(pwField, rb.getString("login_pw_placeholder"));
        // And change the String value for condition in FocusListener.
        placeholderPassword = rb.getString("login_pw_placeholder");

        // connection JButton
        connection.setText(rb.getString("login_connection"));

        // createAccount JButton
        createAccount.setText(rb.getString("login_createAccount"));

        // continue Without an Account JButton
        continueWithoutConn.setText(rb.getString("login_continueWithoutConnection"));

        // KeyboardDialog
        keyboardTitleID = rb.getString("login_id_vk_frameName");
        keyboardTitlePassword = rb.getString("login_pw_vk_frameName");
        keyboard.setLanguage(rb);
    }

	@Override
	public void setLight() {
		// This JPanel
        this.setBackground(Light.WHITE.getColor());
        this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Light.BLACK.getColor()));
      
        // ID Label
		this.idLabel.setForeground(Light.BLACK.getColor());
		
		// ID TextField
		this.idField.setBackground(Light.REVERSE_BG.getColor());
		this.idField.setBorder(BorderFactory.createLineBorder(Light.BLACK.getColor(), 1));
		this.idField.setForeground(Light.BLACK.getColor());
		
		// Password TextField
		this.pwField.setBackground(Light.REVERSE_BG.getColor());
		this.pwField.setBorder(BorderFactory.createLineBorder(Light.BLACK.getColor(), 1));
		this.pwField.setForeground(Light.BLACK.getColor());
		
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
		
		// KeyboardDialog
		this.keyboard.setLight();
	}

	@Override
	public void setDark() {
		// This JPanel
		this.setBackground(Dark.BG.getColor());
        this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Dark.FOREGROUNG.getColor()));
		
		// ID Label
        this.idLabel.setForeground(Dark.FOREGROUNG.getColor());
        
		// Id TextField
        this.idField.setBackground(Dark.PURPLE.getColor());
        this.idField.setForeground(Dark.FOREGROUNG.getColor());
		this.idField.setBorder(BorderFactory.createLineBorder(Dark.PINK.getColor(), 1));

		// Password TextField
        this.pwField.setBackground(Dark.PURPLE.getColor());
        this.pwField.setForeground(Dark.FOREGROUNG.getColor());
		this.pwField.setBorder(BorderFactory.createLineBorder(Dark.PINK.getColor(), 1));
        
		// Connection Button
		this.connection.setBackground(Dark.BLUE.getColor());
		this.connection.setForeground(Dark.FOREGROUNG.getColor());
		this.connection.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));
		
		// CreateAccount Button
		this.createAccount.setBackground(Dark.BLUE.getColor());
		this.createAccount.setForeground(Dark.FOREGROUNG.getColor());
		this.createAccount.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 18));
		
		// ContinueWithoutConn Button
		this.continueWithoutConn.setBackground(Dark.PURPLE.getColor());
		this.continueWithoutConn.setForeground(Dark.FOREGROUNG.getColor());
		this.continueWithoutConn.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 18));	
		this.continueWithoutConn.setBorder(BorderFactory.createLineBorder(Dark.PINK.getColor(), 1));
		
		// KeyboardDialog
		this.keyboard.setDark();
	}
}