package ui.pages;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import fc.films.Categories;
import fc.films.Film;
import ui.GUIComponent;
import ui.managers.FilmManager;
import ui.managers.GUIManager;
import ui.managers.panels.FilmManagerPanel;
import ui.utils.Decorations;
import ui.utils.factory.filmpanel.factories.FilmCartPanel;
import ui.utils.observer.colortheme.ColorThemes;
import ui.utils.observer.colortheme.IColorThemeObserver;
import ui.utils.observer.colortheme.palettes.Dark;
import ui.utils.observer.colortheme.palettes.Light;
import ui.utils.observer.multilingual.IMultilingualObserver;

@SuppressWarnings("serial")
public class SearchPage extends JPanel implements GUIComponent, IMultilingualObserver, IColorThemeObserver {
    	
    /* Components */
    private JPanel mainPanel;
    /* Results */
    private JPanel resultsPanel;
    private JLabel resultsLabel;
    private JScrollPane resultsPane;
    private FilmManagerPanel resultsManager;
    /* Most rented films */
    private JPanel mostRentedPanel;
    private JLabel mostRentedLabel;
    private JScrollPane mostRentedPane;
    private FilmManagerPanel mostRentedManager;
    /* Categories */
    private JScrollPane categoriesPane;
    private JPanel categoriesManager;
    /* Film Manager */
    private FilmManager filmManager;
    
    private int countResults;

    /**
     * Default Constructor of {@code SearchPage}
     * @author MathysC
     *
     */
    public SearchPage() {
	mainPanel = new JPanel(new BorderLayout());
	resultsPanel = new JPanel(new BorderLayout());
	resultsLabel = new JLabel();
	mostRentedPanel = new JPanel(new BorderLayout());
	mostRentedLabel = new JLabel();
	countResults = 0;
	this.createGUI();
	
    }
	
    /**
     * Add a film to the ResultPanel
     * @author MathysC
     *
     * @param film The film to add.
     */
    public void addResult(Film film) { 
	FilmCartPanel panel = this.filmManager.getFilmCartPanel(film);
	if(panel != null) {
	    this.resultsManager.addPanel(panel);
	    countResults++;
	    this.updateCountResults();
	}
	    
    }
	
    /**
     * Update the JLabel of results Found
     * @author MathysC
     */
    private void updateCountResults() {
	String text = resultsLabel.getText();
	// Remove the previous count and update it
	text = text.split(" ")[0] + " (" + countResults + ")";
	resultsLabel.setText(text);
    }
	
    @Override
    public void createGUI() {
	this.setLayout(new BorderLayout());
	resultsPanel.setOpaque(false);
	mostRentedPanel.setOpaque(false);
	resultsLabel.setOpaque(false);
	mostRentedLabel.setOpaque(false);
	// Categories
	categoriesManager = new JPanel(new GridLayout(0, 1));
	categoriesPane = new JScrollPane(categoriesManager, 
		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	categoriesPane.setBorder(BorderFactory.createEmptyBorder());
	for(Categories c: Categories.values()) {
	    JButton button = new JButton(c.toString());
	    button.setPreferredSize(new Dimension(200, 50));
	    button.setSize(new Dimension(200, 25));
	    categoriesManager.add(button);
	}
	    
	this.add(categoriesPane, BorderLayout.WEST);
	    
	/* Center */
	JPanel centerPanel = new JPanel(new GridLayout(2,0));
	centerPanel.setOpaque(false);
	// Results
	resultsManager = new FilmManagerPanel(new FlowLayout(FlowLayout.LEFT), 100);	    
	resultsPane = new JScrollPane(resultsManager, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, 
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	resultsPane.setBorder(BorderFactory.createEmptyBorder());
	resultsLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));
	resultsPanel.add(resultsLabel, BorderLayout.NORTH);
	resultsPanel.add(resultsPane, BorderLayout.CENTER);
	centerPanel.add(resultsPanel);
	    
	// Most rented films
	mostRentedManager = new FilmManagerPanel(new FlowLayout(FlowLayout.LEFT), 100);
	mostRentedPane = new JScrollPane(mostRentedManager, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, 
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	mostRentedPane.setBorder(BorderFactory.createEmptyBorder());
	mostRentedLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));
	mostRentedPanel.add(mostRentedLabel, BorderLayout.NORTH);
	mostRentedPanel.add(mostRentedPane, BorderLayout.CENTER);
	centerPanel.add(mostRentedPanel);

	mainPanel.add(centerPanel, BorderLayout.CENTER);
	this.add(mainPanel,BorderLayout.CENTER);
	    
	this.filmManager = FilmManager.getInstance();

	GUIManager.getInstance().registerColorTheme(mostRentedManager);
	GUIManager.getInstance().registerColorTheme(resultsManager);
	    
    }
	
    @Override
    public void setLanguage(ResourceBundle rb) {
	resultsLabel.setText(rb.getString("result_title"));
	this.updateCountResults();
	mostRentedLabel.setText(rb.getString("result_most_rented"));
    }

    @Override
    public void setColorTheme(ColorThemes colorTheme) {
	switch(colorTheme) {
	    case LIGHT_THEME:
		mainPanel.setBackground(Light.BG.getColor());
		resultsLabel.setForeground(Light.REVERSE_FG.getColor());
		mostRentedLabel.setForeground(Light.REVERSE_FG.getColor());

		for(Component c: categoriesManager.getComponents()) {
		    JButton button = (JButton)c;
		    button.setBackground(Light.BLUE.getColor());
		    button.setForeground(Light.WHITE.getColor());
		    button.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));
		}
		break;
	    case DARK_THEME:
		mainPanel.setBackground(Dark.BG.getColor());
		resultsLabel.setForeground(Dark.FOREGROUND.getColor());
		mostRentedLabel.setForeground(Dark.FOREGROUND.getColor());	

		for(Component c: categoriesManager.getComponents()) {
		    JButton button = (JButton)c;
		    button.setBackground(Dark.BLUE.getColor());
		    button.setForeground(Dark.FOREGROUND.getColor());
		    button.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));
		}
		break;
	    default:
		break;
	}
	    
    }

}
