package ui.Pages.Welcome;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.Decorations;
import ui.FilmManager;
import ui.Bundles.Multilingual;

/**
 * Instantiate a list of suggested films displayed on Welcome Page.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
class SuggestionsPanel extends JPanel implements Multilingual {
	
	private JLabel sugLabel = new JLabel();
	private JScrollPane filmsPane;
	private FilmManager manager;
	
    /**
     * Constructor of {@code SuggestionsPanel} 
     * Set JPanel options and add Components.
     * @author MathysC
     *
     */
	public SuggestionsPanel(){
		// JPanel Options.
		this.setLayout(new BorderLayout());
		
		// Label
		JPanel tempPanelForLabel = new JPanel(new FlowLayout());
		tempPanelForLabel.add(sugLabel);
		sugLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));
		this.add(tempPanelForLabel, BorderLayout.NORTH);
		
		// JScrollPane
		manager = new FilmManager(new GridLayout(0,4));
		filmsPane = new JScrollPane(manager, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(filmsPane, BorderLayout.CENTER);
	}
	
	@Override
	public void setLanguage(ResourceBundle rb) {
		this.sugLabel.setText(rb.getString("sug_label"));
		
	}

}
