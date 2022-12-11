package ui.mainframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.GUIComponent;
import ui.utils.Decorations;
import ui.utils.Resources;
import ui.utils.observer.colortheme.ColorThemes;
import ui.utils.observer.colortheme.IColorThemeObserver;
import ui.utils.observer.colortheme.palettes.Dark;
import ui.utils.observer.colortheme.palettes.Light;

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
public class TopBarPanel extends JPanel implements GUIComponent, IColorThemeObserver {
    
    /* Actions */
    public static final String ACTION_EN = "Switch_to_fr";
    public static final String ACTION_FR = "Switch_to_en";
    public static final String ACTION_HELP = "Ask_for_help";
    public static final String ACTION_DARK = "Switch_to_Light";
    public static final String ACTION_LIGHT = "Switch_to_Dark";
    public static final String ACTION_SEARCH = "Go_to_Search_page";
    public static final String ACTION_WELCOME = "Go_to_Welcome_page";
    /* Images */
    public static final String IMG_LOGO = "Light_Logo";
    public static final String IMG_FR = "CurrentFR";
    public static final String IMG_EN = "CurrentEN";
    public static final String IMG_LIGHT = "CurrentLight" ;
    public static final String IMG_DARK = "CurrentDark";
    public static final String IMG_QUESTION_LIGHT = "QuestionLight";
    public static final String IMG_QUESTION_DARK = "QuestionDark";
    public static final String IMG_SEARCH_LIGHT = "searchLight";
    public static final String IMG_SEARCH_DARK = "searchDark";

    /* Components */
    private JLabel logo;
    private JPanel options;
    private JButton colorSwitch;
    private JButton languageSwitch;
    private JButton askForHelpButton;
    private JButton searchButton;   
    /* Options */
    private static final Dimension dimLogo = Decorations.sizeConverter(new Dimension(200, 150));

    /**
     * Constructor of TopBar.
     * Set JPanel options and add components.
     * @author MathysC
     *
     */
    public TopBarPanel() {
	logo = new JLabel();
	options = new JPanel();
	colorSwitch = new JButton();
	languageSwitch = new JButton();
	askForHelpButton = new JButton();
	searchButton = new JButton();   
	this.createGUI();
	
    }

    /**
     * @author MathysC
     * @return the options
     */
    public JPanel getOptions() {
        return options;
    }

    /**
     * @author MathysC
     * @return the colorSwitch
     */
    public JButton getColorSwitch() {
        return colorSwitch;
    }

    /**
     * @author MathysC
     * @return the languageSwitch
     */
    public JButton getLanguageSwitch() {
        return languageSwitch;
    }

    /**
     * @author MathysC
     * @return the askForHelpButton
     */
    public JButton getAskForHelpButton() {
        return askForHelpButton;
    }

    /**
     * @author MathysC
     * @return the searchButton
     */
    public JButton getSearchButton() {
        return searchButton;
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
    public void createGUI() {
        // Set JPanel’s options.
        this.setLayout(new BorderLayout());

        // Logo.
        logo.setPreferredSize(dimLogo);
        logo.setIcon(Resources.getImg(IMG_LOGO));
        logo.setBorder(Decorations.getDefaultBorder());
        this.add(logo, BorderLayout.WEST);

        // Buttons’ JPanel.
        options.setLayout(new FlowLayout(FlowLayout.TRAILING));
        options.setBorder(Decorations.getDefaultBorder());
        this.add(options, BorderLayout.EAST);
        
        // Search Button.
        setButton(searchButton, Decorations.getDefaultButtonDimension(), Resources.getImg(IMG_SEARCH_LIGHT), ACTION_SEARCH);
        
        // Color switch button.
        setButton(colorSwitch, Decorations.getDefaultButtonDimension(), Resources.getImg(IMG_LIGHT), ACTION_LIGHT);

        // Language switch button.
        setButton(languageSwitch, Decorations.getDefaultButtonDimension(), Resources.getImg(IMG_EN), ACTION_EN);

        // Ask for help button.
        setButton(askForHelpButton, Decorations.getDefaultButtonDimension(), Resources.getImg(IMG_QUESTION_LIGHT), ACTION_HELP);
	
    }
    
    @Override
    public void setColorTheme(ColorThemes colorTheme) {
	switch(colorTheme) {
	case LIGHT_THEME:
	    // This Panel
	    this.setBackground(Light.BG.getColor());
	    this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Light.BLACK.getColor()));

	    // Options Panel
	    this.options.setBackground(Light.BG.getColor());

	    // Buttons
	    colorSwitch.setIcon(Resources.getImg(IMG_LIGHT));
	    colorSwitch.setActionCommand(ACTION_LIGHT);
	    askForHelpButton.setIcon(Resources.getImg(IMG_QUESTION_LIGHT));
	    searchButton.setIcon(Resources.getImg(IMG_SEARCH_LIGHT));
	    break;
		
	case DARK_THEME:
	    // This Panel
	    this.setBackground(Dark.BG.getColor());
	    this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Dark.FOREGROUND.getColor()));
	    // Options Panel
	    this.options.setBackground(Dark.BG.getColor());

	    // Buttons
	    colorSwitch.setIcon(Resources.getImg(IMG_DARK));
	    colorSwitch.setActionCommand(ACTION_DARK);
	    askForHelpButton.setIcon(Resources.getImg(IMG_QUESTION_DARK));
	    searchButton.setIcon(Resources.getImg(IMG_SEARCH_DARK));
	    break;
	    
	default:
	    break;
	}
	
    }
    
}