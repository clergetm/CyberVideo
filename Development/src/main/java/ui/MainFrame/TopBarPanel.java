package ui.MainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.Decorations;
import ui.Palettes.DarkPalette;
import ui.Palettes.LightPalette;
import ui.Themes.ColorTheme;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
public class TopBarPanel extends JPanel implements ColorTheme {

    /* Components */
    protected JLabel logo = new JLabel();
    protected JPanel topBar = new JPanel(), options = new JPanel();
    protected JButton colorSwitch = new JButton(), languageSwitch = new JButton(), askForHelp = new JButton();

    /* Options */
    private Dimension DIM_BUTTON = new Dimension(100, 100), DIM_LOGO = new Dimension(200, 150);

    /* Actions */
    public static final String ACTION_EN = "Switch_to_fr", ACTION_FR = "Switch_to_en", ACTION_HELP = "Ask_for_help",
    		ACTION_DARK = "Switch_to_Light", ACTION_LIGHT = "Switch_to_Dark";

    /* Icons */
    public static final String IMG_LOGO = "Light_Logo", IMG_FR = "CurrentFR", IMG_EN = "CurrentEN", 
    		IMG_LIGHT = "CurrentLight" , IMG_DARK = "CurrentDark", 
    		IMG_QUESTION_LIGHT = "QuestionLight", IMG_QUESTION_DARK = "QuestionDark";
    /**
     * Constructor of TopBar.
     * Set JPanel options and add components.
     * @author MathysC
     *
     */
    public TopBarPanel() {
        // Set JPanel’s options.
        this.setLayout(new BorderLayout());

        // Logo.
        logo.setPreferredSize(DIM_LOGO);
        logo.setIcon(Decorations.getImg(IMG_LOGO));
        logo.setBorder(Decorations.getDefaultBorder());
        this.add(logo, BorderLayout.WEST);

        // Buttons’ JPanel.
        options.setLayout(new FlowLayout(FlowLayout.TRAILING));
        options.setBorder(Decorations.getDefaultBorder());
        this.add(options, BorderLayout.EAST);

        // Color switch button.
        setButton(colorSwitch, DIM_BUTTON, Decorations.getImg(IMG_LIGHT), ACTION_LIGHT);

        // Language switch button.
        setButton(languageSwitch, DIM_BUTTON, Decorations.getImg(IMG_EN), ACTION_EN);

        // Ask for help button.
        setButton(askForHelp, DIM_BUTTON, Decorations.getImg(IMG_QUESTION_LIGHT), ACTION_HELP);

    }

    /**
     * Set options of the given {@code component}.
     * Private method to better reading of the constructor.
     * @author MathysC
     *
     * @param button The JButton to set.
     * @param dim The dimension of the button.
     * @param icon The icon of the button.
     * @param command The actionCommand of the button.
     */
    private void setButton(JButton button, Dimension dim, ImageIcon icon, String command) {
        button.setMinimumSize(dim);
        button.setPreferredSize(dim);
        button.setMaximumSize(dim);
        button.setIcon(icon);
        button.setActionCommand(command);
        button.setContentAreaFilled(false); // transparent
        button.setBorderPainted(false); // without border
        this.options.add(button);
    }

	@Override
	public void setLight() {
		// This Panel
		this.setBackground(LightPalette.BG.getColor());
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

		// Options Panel
		this.options.setBackground(LightPalette.BG.getColor());
		
	}

	@Override
	public void setDark() {
		// This Panel
		this.setBackground(DarkPalette.BG.getColor());
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, DarkPalette.FOREGROUNG.getColor()));

		// Options Panel
		this.options.setBackground(DarkPalette.BG.getColor());
		
	}
}