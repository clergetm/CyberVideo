package ui.MainFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.Decorations;
import ui.Colors.ColorTheme;
import ui.Colors.Dark;
import ui.Colors.Light;

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
    protected JPanel options = new JPanel();
    protected JButton colorSwitch = new JButton();
    protected JButton languageSwitch = new JButton();
    protected JButton askForHelpButton = new JButton();
    protected JButton searchButton = new JButton();
    
    /* Options */
    private final Dimension DIM_BUTTON = new Dimension(100, 100);
    private final Dimension DIM_LOGO = new Dimension(200, 150);

    /* Actions */
    public static final String ACTION_EN = "Switch_to_fr", ACTION_FR = "Switch_to_en", ACTION_HELP = "Ask_for_help",
    		ACTION_DARK = "Switch_to_Light", ACTION_LIGHT = "Switch_to_Dark";
    public static final String ACTION_SEARCH = "Search";
    /* Icons */
    public static final String IMG_LOGO = "Light_Logo", IMG_FR = "CurrentFR", IMG_EN = "CurrentEN", 
    		IMG_LIGHT = "CurrentLight" , IMG_DARK = "CurrentDark", 
    		IMG_QUESTION_LIGHT = "QuestionLight", IMG_QUESTION_DARK = "QuestionDark";
    
    public static final String IMG_SEARCH_LIGHT = "searchLight";
    public static final String IMG_SEARCH_DARK = "searchDark";
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
        
        // Search Button
        setButton(searchButton, DIM_BUTTON, Decorations.getImg(IMG_SEARCH_LIGHT), ACTION_SEARCH);
        
        // Color switch button.
        setButton(colorSwitch, DIM_BUTTON, Decorations.getImg(IMG_LIGHT), ACTION_LIGHT);

        // Language switch button.
        setButton(languageSwitch, DIM_BUTTON, Decorations.getImg(IMG_EN), ACTION_EN);

        // Ask for help button.
        setButton(askForHelpButton, DIM_BUTTON, Decorations.getImg(IMG_QUESTION_LIGHT), ACTION_HELP);

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
		this.setBackground(Light.BG.getColor());
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Light.BLACK.getColor()));

		// Options Panel
		this.options.setBackground(Light.BG.getColor());

		// Buttons
    	colorSwitch.setIcon(Decorations.getImg(IMG_LIGHT));
        colorSwitch.setActionCommand(ACTION_LIGHT);
        askForHelpButton.setIcon(Decorations.getImg(IMG_QUESTION_LIGHT));
        searchButton.setIcon(Decorations.getImg(IMG_SEARCH_LIGHT));
	}

	@Override
	public void setDark() {
		// This Panel
		this.setBackground(Dark.BG.getColor());
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Dark.FOREGROUNG.getColor()));

		// Options Panel
		this.options.setBackground(Dark.BG.getColor());
		
		// Buttons
    	colorSwitch.setIcon(Decorations.getImg(IMG_DARK));
        colorSwitch.setActionCommand(ACTION_DARK);
    	askForHelpButton.setIcon(Decorations.getImg(IMG_QUESTION_DARK));
        searchButton.setIcon(Decorations.getImg(IMG_SEARCH_DARK));
	}
}