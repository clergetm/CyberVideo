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
public class SuggestionsPanel extends JPanel implements IMultilingualObserver, IColorThemeObserver {

	/* Components */
    protected JLabel sugLabel = new JLabel();
    protected JScrollPane filmsPane;
    private FilmManagerPanel manager;

    /**
     * Constructor of {@code SuggestionsPanel} 
     * Set JPanel options and add Components.
     * @author MathysC
     *
     */
    public SuggestionsPanel() {
        // JPanel Options.
        this.setLayout(new BorderLayout());

        // Label
        sugLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));
        sugLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(sugLabel, BorderLayout.NORTH);

        // JScrollPane
        manager = new FilmManagerPanel(new GridLayout(0, 4), 100);
        filmsPane = new JScrollPane(manager, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	filmsPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(filmsPane, BorderLayout.CENTER);
        
        GUIManager.getInstance().registerColorTheme(manager);
    }

    /**
     * Getter of filmManager of {@code SuggestionsPanel}
     * @author MathysC
     *
     * @return FilmManager
     */
    public FilmManagerPanel getFilmManager() { return this.manager; }
    
    @Override
    public void setLanguage(ResourceBundle rb) {
        this.sugLabel.setText(rb.getString("sug_label"));

    }

    @Override
    public void setColorTheme(ColorThemes colorTheme) {
	switch(colorTheme) {
	    case LIGHTTHEME:
		// This JPanel.
		this.setBackground(Light.BG.getColor());
	        
	        // Suggestion Label.
		this.sugLabel.setForeground(Light.BLACK.getColor());
		
		// Film ScrollPane.
		this.filmsPane.setBackground(Light.BG.getColor());
		this.filmsPane.getVerticalScrollBar().setBackground(Light.BG.getColor());
		
		break;
	    case DARKTHEME:
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