package ui.pages.welcome;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ui.GUIComponent;
import ui.managers.GUIManager;
import ui.managers.panels.FilmManagerPanel;
import ui.utils.Decorations;
import ui.utils.observer.colortheme.ColorThemes;
import ui.utils.observer.colortheme.IColorThemeObserver;
import ui.utils.observer.colortheme.palettes.Dark;
import ui.utils.observer.colortheme.palettes.Light;
import ui.utils.observer.multilingual.IMultilingualObserver;

/**
 * Instantiate a list of suggested films displayed on Welcome Page.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class SuggestionsPanel extends JPanel implements GUIComponent, IMultilingualObserver, IColorThemeObserver {

    /* Components */
    private JLabel sugLabel;
    private JScrollPane filmsPane;
    private FilmManagerPanel suggestionManager;

    /**
     * Constructor of {@code SuggestionsPanel} 
     * Set JPanel options and add Components.
     * @author MathysC
     *
     */
    public SuggestionsPanel() {
        sugLabel = new JLabel();
        this.createGUI();
        
    }
    
    @Override
    public void createGUI() {
	// JPanel Options.
        this.setLayout(new BorderLayout());

        // Label
        sugLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));
        sugLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(sugLabel, BorderLayout.NORTH);

        // JScrollPane
        suggestionManager = new FilmManagerPanel(new GridLayout(0, 4), 100);
        filmsPane = new JScrollPane(suggestionManager, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	filmsPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(filmsPane, BorderLayout.CENTER);
        
        GUIManager.getInstance().registerColorTheme(suggestionManager);
	
    }
    
    @Override
    public void setLanguage(ResourceBundle rb) {
        this.sugLabel.setText(rb.getString("sug_label"));

    }

    @Override
    public void setColorTheme(ColorThemes colorTheme) {
	switch(colorTheme) {
	    case LIGHT_THEME:
		// This JPanel.
		this.setBackground(Light.BG.getColor());
	        
	        // Suggestion Label.
		this.sugLabel.setForeground(Light.BLACK.getColor());
		
		// Film ScrollPane.
		this.filmsPane.setBackground(Light.BG.getColor());
		this.filmsPane.getVerticalScrollBar().setBackground(Light.BG.getColor());
		
		break;
	    case DARK_THEME:
		// This JPanel.
		this.setBackground(Dark.BG.getColor());
		
		// Suggestion Label.
		this.sugLabel.setForeground(Dark.FOREGROUND.getColor());
		
		// Film ScrollPane.
		this.filmsPane.setBackground(Dark.BG.getColor());
		this.filmsPane.getVerticalScrollBar().setBackground(Dark.BG.getColor());

		break;
	    default:
		break;
	    }
	
    }

}