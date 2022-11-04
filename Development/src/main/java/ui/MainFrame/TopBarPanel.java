package ui.MainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.Decorations;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * The Top bar always appears on the Main Frame. 
 * With a logo and the buttons that allows the client to change 
 * the theme (light/dark) and the language (en\fr).
 * 
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class TopBarPanel extends JPanel {

    protected JPanel topBar = new JPanel(), options = new JPanel();
    protected JLabel logo = new JLabel(), test = new JLabel();
    protected JButton colorSwitch = new JButton(), languageSwitch = new JButton(), askForHelp = new JButton();

    /*Actions*/
    static final String SWITCHFR = "Switch_to_fr";
    static final String SWITCHEN = "Switch_to_en";
    static final String HELP = "Ask_for_help";
    /**
     * Constructor of TopBar.
     * Set JPanel options and add components.
     * @author MathysC
     *
     */
    public TopBarPanel() {
        // Set JPanel’s options.
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

        // Logo.
        logo.setPreferredSize(Decorations.DIM_LOGO.getDimension());
        logo.setIcon(Decorations.IMG_LOGO.getImg());
        logo.setBorder(Decorations.BORDER_DEFAULT.getBorder());
        this.add(logo, BorderLayout.WEST);

        // Buttons’ JPanel.
        options.setLayout(new FlowLayout(FlowLayout.TRAILING));
        options.setBorder(Decorations.BORDER_DEFAULT.getBorder());
        this.add(options, BorderLayout.EAST);

        // Color switch button.
        colorSwitch.setPreferredSize(Decorations.DIM_BUTTON.getDimension());
        colorSwitch.setIcon(Decorations.IMG_CURRENTLIGHT.getImg());
        colorSwitch.setContentAreaFilled(false); // transparent
        colorSwitch.setBorderPainted(false); // without border
        options.add(colorSwitch);

        // Language switch button.
        languageSwitch.setPreferredSize(Decorations.DIM_BUTTON.getDimension());
        languageSwitch.setIcon(Decorations.IMG_CURRENTEN.getImg());
        languageSwitch.setActionCommand(SWITCHFR);
        languageSwitch.setContentAreaFilled(false); // transparent
        languageSwitch.setBorderPainted(false); // without border
        options.add(languageSwitch);

        // ask for help button.
        askForHelp.setPreferredSize(Decorations.DIM_BUTTON.getDimension());
        askForHelp.setIcon(Decorations.IMG_QUESTION.getImg());
        askForHelp.setActionCommand(HELP);
        askForHelp.setContentAreaFilled(false); // transparent
        askForHelp.setBorderPainted(false); // without border
        options.add(askForHelp);

    }
}