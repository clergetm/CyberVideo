package ui.Pages.Welcome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.Decorations;
import ui.FilmManager;
import ui.Bundles.Multilingual;
import ui.Palettes.DarkPalette;
import ui.Palettes.LightPalette;
import ui.Themes.ColorTheme;

/**
 * Instantiate a list of suggested films displayed on Welcome Page.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
class SuggestionsPanel extends JPanel implements Multilingual, ColorTheme {

	/* Components */
    protected JLabel sugLabel = new JLabel();
    private JPanel panelForLabel = new JPanel(new FlowLayout());
    protected JScrollPane filmsPane;
    private FilmManager manager;

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
        panelForLabel.add(sugLabel);
        sugLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));
        this.add(panelForLabel, BorderLayout.NORTH);

        // JScrollPane
        manager = new FilmManager(new GridLayout(0, 4));
        filmsPane = new JScrollPane(manager, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(filmsPane, BorderLayout.CENTER);
    }

    @Override
    public void setLanguage(ResourceBundle rb) {
        this.sugLabel.setText(rb.getString("sug_label"));

    }

	@Override
	public void setLight() {
		// This JPanel.
        this.setBackground(LightPalette.BG.getColor());
        
        // Suggestion Label.
		this.sugLabel.setForeground(Color.BLACK);
		this.panelForLabel.setBackground(LightPalette.BG.getColor());
		
		// Film ScrollPane.
		this.filmsPane.setBackground(LightPalette.BG.getColor());
		this.filmsPane.getVerticalScrollBar().setBackground(LightPalette.BG.getColor());
		this.filmsPane.setBorder(BorderFactory.createEmptyBorder());
		
		// Films manager.
		this.manager.setLight();
	}

	@Override
	public void setDark() {
		// This JPanel.
		this.setBackground(DarkPalette.BG.getColor());
		
        // Suggestion Label.
		this.sugLabel.setForeground(DarkPalette.FOREGROUNG.getColor());
		this.panelForLabel.setBackground(DarkPalette.BG.getColor());
		
		// Film ScrollPane.
		this.filmsPane.setBackground(DarkPalette.BG.getColor());
		this.filmsPane.getVerticalScrollBar().setBackground(DarkPalette.BG.getColor());
		
		// Films manager.
		this.manager.setDark();
	}

}