package ui.pages.welcome;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import ui.utils.Decorations;
import ui.utils.KeyboardDialog;
import ui.utils.colors.ColorTheme;
import ui.utils.colors.Dark;
import ui.utils.colors.Light;
import ui.utils.observer.multilingual.IMultilingualObserver;
import ui.utils.observer.multilingual.MultilingualManager;

/**
 * LoginPanel is part of the WelcomePage.
 * Instantiate all JComponent to allow the user to connect.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
class LoginPanel extends JPanel implements IMultilingualObserver, ColorTheme {

    private String placeholderID = "", placeholderPassword = "", keyboardTitleID = "", keyboardTitlePassword = "";

    /* Components */
    protected JLabel loginPanelLabel = new JLabel();
    protected JTextField tfID = new JTextField();
    protected JPasswordField tfPassword = new JPasswordField();
    protected JButton connection = new JButton(), createAccount = new JButton(), continueWithoutConn = new JButton();

    /*Options*/
    private final int maxW = 350, W = (int)(maxW * 0.9),
        HTextField = 50, HButton = 75;

    // Size of rigid areas placed between JComponent to create gaps.
    private final Dimension SMALLRIGID = Decorations.sizeConverter(new Dimension(25, 25));
    private final Dimension BIGRIGID = Decorations.sizeConverter(new Dimension(75, 75));

    /**
     * Constructor of {@code LoginPanel} 
     * Set JPanel options and add Components.
     * @author MathysC
     *
     */
    public LoginPanel() {
        // JPanel options
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Set Login JLabel.
        loginPanelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanelLabel.setBorder(new EmptyBorder(10, 20, 50, 20));
        loginPanelLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));
        this.add(loginPanelLabel);

        this.add(Box.createRigidArea(SMALLRIGID));

        // Set ID JTextField.
        setComponent(tfID, W, HTextField, Component.CENTER_ALIGNMENT);
        tfID.setColumns(25);

        tfID.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) { /* Unused. */ }

            @Override
            public void mousePressed(MouseEvent e) { /* Unused. */ }

            @Override
            public void mouseExited(MouseEvent e) { /* Unused. */ }

            @Override
            public void mouseEntered(MouseEvent e) { /* Unused. */ }

            @Override
            public void mouseClicked(MouseEvent e) {

                // if text is default value
                if (tfID.getText().equals(placeholderID)) {
                    Decorations.resetDefaultPlaceholder(tfID);
                }
                // Get new text
                String prompt = KeyboardDialog.showKeyboardDialog(keyboardTitleID, tfID);

                // If empty prompt
                if (prompt.equals("")) {
                    Decorations.setDefaultPlaceholder(tfID, placeholderID);
                } else {
                    tfID.setText(prompt);
                }
            }
        });

        this.add(tfID);

        this.add(Box.createRigidArea(SMALLRIGID)); // Gap.

        // Set Password JPasswordField.
        setComponent(tfPassword, W, HTextField, Component.CENTER_ALIGNMENT);
        tfPassword.setColumns(25);
        tfPassword.setEchoChar((char) 0);


        tfPassword.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) { /* Unused. */ }

            @Override
            public void mousePressed(MouseEvent e) { /* Unused. */ }

            @Override
            public void mouseExited(MouseEvent e) { /* Unused. */ }

            @Override
            public void mouseEntered(MouseEvent e) { /* Unused. */ }

            @Override
            public void mouseClicked(MouseEvent e) {
                // If nothing has been written. Remove the PlaceHolder.
                if (Arrays.equals(tfPassword.getPassword(), placeholderPassword.toCharArray())) {
                    Decorations.resetDefaultPlaceholder(tfPassword);
                }

                // Show what’s written.
                tfPassword.setEchoChar((char) 0);

                // Get new prompt
                String prompt = KeyboardDialog.showKeyboardDialog(keyboardTitlePassword, tfPassword);

                // If empty prompt
                if (prompt.equals("")) {
                    // Set to default
                    Decorations.setDefaultPlaceholder(tfPassword, placeholderPassword);
                } else {
                    // Hide what’s written.
                    tfPassword.setEchoChar('*');
                    tfPassword.setText(prompt);
                }
            }
        });

        this.add(tfPassword);

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
        component.setMinimumSize(Decorations.sizeConverter(new Dimension(W, H)));
        component.setPreferredSize(Decorations.sizeConverter(new Dimension(W, H)));
        component.setMaximumSize(Decorations.sizeConverter(new Dimension(W, H)));
    }

    /**
     * {@inheritDoc}
     * @author MathysC
     *
     */
    @Override
    public void setLanguage(ResourceBundle rb) {

        // Label
        loginPanelLabel.setText(rb.getString("login_label"));

        // ID JTextField
        // If the field wasn’t change. Change the placeholder
        if (tfID.getText().equals(placeholderID))
            Decorations.setDefaultPlaceholder(tfID, rb.getString("login_id_placeholder"));

        // And change the String value for condition in FocusListener.
        placeholderID = rb.getString("login_id_placeholder");

        // Password JPasswordField
        // If the field wasn’t change. Change the placeholder
        if (Arrays.equals(tfPassword.getPassword(), placeholderPassword.toCharArray()))
            Decorations.setDefaultPlaceholder(tfPassword, rb.getString("login_pw_placeholder"));
        // And change the String value for condition in FocusListener.
        placeholderPassword = rb.getString("login_pw_placeholder");

        // connection JButton
        connection.setText(rb.getString("login_in"));

        // createAccount JButton
        createAccount.setText(rb.getString("login_up"));

        // continue Without an Account JButton
        continueWithoutConn.setText(rb.getString("login_without"));

        // KeyboardDialog
        keyboardTitleID = rb.getString("login_id_vk_frameName");
        keyboardTitlePassword = rb.getString("login_pw_vk_frameName");
    }

    @Override
    public void setLight() {
        // This JPanel
        this.setBackground(Light.WHITE.getColor());
        this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Light.BLACK.getColor()));

        // Label
        this.loginPanelLabel.setForeground(Light.BLACK.getColor());
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

    }

    @Override
    public void setDark() {
	// This JPanel
        this.setBackground(Dark.BG.getColor());
        this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Dark.FOREGROUND.getColor()));

        // Label
        this.loginPanelLabel.setForeground(Dark.FOREGROUND.getColor());

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

    }
}