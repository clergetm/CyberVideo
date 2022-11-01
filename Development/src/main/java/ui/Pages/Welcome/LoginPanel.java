package ui.Pages.Welcome;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
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
import ui.Bundles.Multilingual;

/**
 * LoginPanel is part of the WelcomePage.
 * Instantiate all JComponent to allow the user to connect.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
class LoginPanel extends JPanel implements Multilingual {

    JLabel idLabel = new JLabel();
    JTextField idField = new JTextField();
    JPasswordField pwField = new JPasswordField();
    JButton connection = new JButton(), createAccount = new JButton(), continueWithoutConn = new JButton();
    String idPlaceHolder = "", pwPlaceHolder = "";
    private final int maxW = 400, W = (int)(maxW * 0.9),
        HTextField = 50, HButton = 75;

    // Size of rigid area placed between JComponent to create space.
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
        this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK));
        this.setBackground(Color.WHITE);

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
                    setPlaceHolder(idField, idPlaceHolder);

            }

            @Override
            public void focusGained(FocusEvent e) {
                if (idField.getText().equals(idPlaceHolder))
                    unsetPlaceHolder(idField);
            }
        });
        this.add(idField);

        this.add(Box.createRigidArea(SMALLRIGID)); // Space

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
                if (Arrays.equals(pwField.getPassword(), "".toCharArray())) {
                    setPlaceHolder(pwField, pwPlaceHolder);
                    pwField.setEchoChar((char) 0);
                }

            }

            @Override
            public void focusGained(FocusEvent e) {
                System.out.println("Gain: " + Arrays.equals(pwField.getPassword(), pwPlaceHolder.toCharArray()));
                if (Arrays.equals(pwField.getPassword(), pwPlaceHolder.toCharArray())) {
                    unsetPlaceHolder(pwField);
                    pwField.setEchoChar('*');
                }
            }
        });
        this.add(pwField);

        this.add(Box.createRigidArea(SMALLRIGID)); // Space

        // Set connection JButton.
        setComponent(connection, W / 2, HButton / 2, Component.LEFT_ALIGNMENT); // Component.LEFT_ALIGNMENT but a bit centered.
        this.add(connection);

        this.add(Box.createRigidArea(BIGRIGID)); // Space

        // Set create account JButton.
        setComponent(createAccount, W, HButton, Component.CENTER_ALIGNMENT);
        this.add(createAccount);

        this.add(Box.createRigidArea(BIGRIGID)); // Space

        // set continue without connection JButton
        setComponent(continueWithoutConn, W, HButton, Component.CENTER_ALIGNMENT);
        this.add(continueWithoutConn);

        this.setMinimumSize(new Dimension(400, 100));
        this.setPreferredSize(new Dimension(400, 100));
        this.setMaximumSize(new Dimension(400, 100));
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
        if (idField.getText().equals(idPlaceHolder))
            setPlaceHolder(idField, rb.getString("login_id_placeholder"));

        // And change the String value for condition in FocusListener.
        idPlaceHolder = rb.getString("login_id_placeholder");

        // Password JPasswordField
        // If the field wasn’t change. Change the placeholder
        if (Arrays.equals(pwField.getPassword(), pwPlaceHolder.toCharArray()))
            setPlaceHolder(pwField, rb.getString("login_pw_placeholder"));
        // And change the String value for condition in FocusListener.
        pwPlaceHolder = rb.getString("login_pw_placeholder");

        // connection JButton
        connection.setText(rb.getString("login_connection"));

        // createAccount JButton
        createAccount.setText(rb.getString("login_createAccount"));

        // continue Without an Account JButton
        continueWithoutConn.setText(rb.getString("login_continueWithoutConnection"));
    }
}