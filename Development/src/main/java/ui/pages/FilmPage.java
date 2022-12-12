package ui.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ResourceBundle;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import fc.films.Film;
import ui.GUIComponent;
import ui.managers.GUIManager;
import ui.utils.Decorations;
import ui.utils.factory.filmpanel.factories.FilmCartPanel;
import ui.utils.factory.filmpanel.factories.FilmPanel;
import ui.utils.observer.colortheme.ColorThemes;
import ui.utils.observer.colortheme.IColorThemeObserver;
import ui.utils.observer.colortheme.palettes.Dark;
import ui.utils.observer.colortheme.palettes.Light;
import ui.utils.observer.multilingual.IMultilingualObserver;


/**
 * The FilmPage graphically presents the information of a film
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class FilmPage extends JPanel implements GUIComponent, IMultilingualObserver, IColorThemeObserver{

    /* Components */
    private JPanel infoPanel; // Info on right : all the text
    private JPanel posterPanel;
    private JLabel titleLabel;
    private JLabel actorsLabel;
    private JTextArea actorsText;
    private JLabel synopsisLabel;
    private JTextArea synopsisText;
    private JLabel directorLabel;
    private JTextArea directorsText;

    /**
     * Default constructor of {@code FilmPage}
     * @author MathysC
     *
     */
    public FilmPage() {
	// Initialisaiton des attributs d'instances 
	infoPanel= new JPanel();
	posterPanel = new JPanel();
	synopsisText = new JTextArea(1,5);
	actorsText = new JTextArea(1,2);
	directorsText = new JTextArea(1,1);
	createGUI();

    }

    @Override
    public void createGUI() {
	this.setLayout(new BorderLayout());
	this.setOpaque(false);
	infoPanel.setOpaque(false);
	posterPanel.setOpaque(false);
	synopsisText.setOpaque(false);
	actorsText .setOpaque(false);
	directorsText.setOpaque(false);
	infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
	titleLabel = new JLabel("TITRE");
	titleLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));
	infoPanel.add(titleLabel);

	actorsLabel = new JLabel("Acteurs");
	actorsLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 16));
	infoPanel.add(actorsLabel);

	actorsText.setMaximumSize(new Dimension(Short.MAX_VALUE,20));	
	actorsText.setFocusable(false);
	actorsText.setEditable(false);
	actorsText.setFont(Decorations.FONT_BASIC.getFont(Font.PLAIN, 12));
	actorsText.setOpaque(false);
	infoPanel.add(actorsText);
	
	synopsisLabel = new JLabel("Synopsis");
	synopsisLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 16));
	infoPanel.add(synopsisLabel);

	synopsisText.setMaximumSize(new Dimension(Short.MAX_VALUE,100));
	synopsisText.setEditable(false);
	synopsisText.setFocusable(false);
	synopsisText.setLineWrap(true);
	synopsisText.setWrapStyleWord(true);
	synopsisText.setFont(Decorations.FONT_BASIC.getFont(Font.PLAIN, 12));
	synopsisText.setOpaque(false);
	infoPanel.add(synopsisText);
	
	directorLabel = new JLabel("Directeur");
	directorLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 16));
	infoPanel.add(directorLabel);
	
	directorsText.setMaximumSize(new Dimension(Short.MAX_VALUE,20));	
	directorsText.setEditable(false);
	directorsText.setFocusable(false);
	directorsText.setFont(Decorations.FONT_BASIC.getFont(Font.PLAIN, 12));
	directorsText.setOpaque(false);
	infoPanel.add(directorsText);
	infoPanel.add(Box.createVerticalGlue());
	
	

	this.add(infoPanel, BorderLayout.CENTER);
	
	posterPanel.setLayout(new BoxLayout(posterPanel,BoxLayout.Y_AXIS));


	this.add(posterPanel, BorderLayout.WEST);
	
	
	}

    public void showFilm(FilmPanel filmCart) {
	Film film = filmCart.getFilm();

	titleLabel.setText(film.getTitle());

	// Informations for the synopsis
	synopsisText.setText(film.getSynopsis());


	// Informations for actors
	actorsText.setText(film.toStringActors());


	// Information for the director
	directorsText.setText(film.getNamesDirector());
	
	posterPanel.removeAll();
	FilmCartPanel filmCartPanel = new FilmCartPanel(film);
	filmCartPanel.setScale(200);
	posterPanel.add(Box.createVerticalGlue());
	posterPanel.add(filmCartPanel);
	posterPanel.add(Box.createVerticalGlue());

	GUIManager.getInstance().refreshColorTheme();
}

    @Override
    public void setColorTheme(ColorThemes colorTheme) {
	switch(colorTheme) {
	case LIGHT_THEME:
	    titleLabel.setForeground(Light.BLACK.getColor());
	    actorsLabel.setForeground(Light.BLACK.getColor());
	    actorsText.setForeground(Light.BLACK.getColor());
	    synopsisLabel.setForeground(Light.BLACK.getColor());
	    synopsisText.setForeground(Light.BLACK.getColor());
	    directorLabel.setForeground(Light.BLACK.getColor());
	    directorsText.setForeground(Light.BLACK.getColor());
	    
	    break;
	    
	case DARK_THEME:
	    titleLabel.setForeground(Dark.FOREGROUND.getColor());
	    actorsLabel.setForeground(Dark.FOREGROUND.getColor());
	    actorsText.setForeground(Dark.FOREGROUND.getColor());
	    synopsisLabel.setForeground(Dark.FOREGROUND.getColor());
	    synopsisText.setForeground(Dark.FOREGROUND.getColor());
	    directorLabel.setForeground(Dark.FOREGROUND.getColor());
	    directorsText.setForeground(Dark.FOREGROUND.getColor());
	    
	    break;
	    
	default:
	    break;
	}
	
    }

    @Override
    public void setLanguage(ResourceBundle rb) {
	this.actorsLabel.setText(rb.getString("film_actors"));
	this.synopsisLabel.setText(rb.getString("film_synopsis"));
	this.directorLabel.setText(rb.getString("film_director"));
	
    }

}
