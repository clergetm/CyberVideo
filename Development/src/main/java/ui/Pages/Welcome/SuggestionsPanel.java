package ui.Pages.Welcome;

import java.awt.BorderLayout;
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
import ui.Colors.ColorTheme;
import ui.Colors.Dark;
import ui.Colors.Light;

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
        this.setBackground(Light.BG.getColor());
        
        // Suggestion Label.
		this.sugLabel.setForeground(Light.BLACK.getColor());
		this.panelForLabel.setBackground(Light.BG.getColor());
		
		// Film ScrollPane.
		this.filmsPane.setBackground(Light.BG.getColor());
		this.filmsPane.getVerticalScrollBar().setBackground(Light.BG.getColor());
		this.filmsPane.setBorder(BorderFactory.createEmptyBorder());
		
		// Films manager.
		this.manager.setLight();
	}

	@Override
	public void setDark() {
		// This JPanel.
		this.setBackground(Dark.BG.getColor());
		
        // Suggestion Label.
		this.sugLabel.setForeground(Dark.FOREGROUNG.getColor());
		this.panelForLabel.setBackground(Dark.BG.getColor());
		
		// Film ScrollPane.
		this.filmsPane.setBackground(Dark.BG.getColor());
		this.filmsPane.getVerticalScrollBar().setBackground(Dark.BG.getColor());
		
		// Films manager.
		this.manager.setDark();
	}

}