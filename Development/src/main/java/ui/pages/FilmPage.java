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
import ui.utils.Decorations;
import ui.utils.factory.filmpanel.factories.FilmCartPanel;
import ui.utils.factory.filmpanel.factories.FilmPanel;
import ui.utils.observer.colortheme.ColorThemes;
import ui.utils.observer.colortheme.IColorThemeObserver;
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
    private JLabel synopsisLabel;
    private JLabel directorLabel;

    /**
     * Default constructor of {@code FilmPage}
     * @author MathysC
     *
     */
    public FilmPage() {
	// Initialisaiton des attributs d'instances 
	infoPanel= new JPanel();
	posterPanel = new JPanel();
	createGUI();

    }

    @Override
    public void createGUI() {
	setLayout(new BorderLayout());
	infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
	titleLabel = new JLabel("TITRE");
	titleLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));
	infoPanel.add(titleLabel);

	actorsLabel = new JLabel("Acteurs");
	actorsLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 16));
	infoPanel.add(actorsLabel);

	synopsisLabel = new JLabel("Synopsis");
	synopsisLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 16));
	infoPanel.add(synopsisLabel);

	directorLabel = new JLabel("Directeur");
	directorLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 16));
	infoPanel.add(directorLabel);

	this.add(infoPanel, BorderLayout.CENTER);
	
	}

    public void showFilm(FilmPanel filmCart) {
	Film film = filmCart.getFilm();

	infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));


	titleLabel.setText(film.getTitle());
	titleLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));
	infoPanel.add(titleLabel);

	// Informations for the synopsis
	synopsisLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 16));
	infoPanel.add(synopsisLabel);
	JTextArea synopsisText = new JTextArea(1,5);
	synopsisText.setText(film.getSynopsis());
	synopsisText.setMaximumSize(new Dimension(Short.MAX_VALUE,100));
	synopsisText.setEditable(false);
	synopsisText.setFocusable(false);
	synopsisText.setLineWrap(true);
	synopsisText.setWrapStyleWord(true);
	synopsisText.setFont(Decorations.FONT_BASIC.getFont(Font.PLAIN, 12));
	synopsisText.setOpaque(false);
	infoPanel.add(synopsisText);

	// Informations for actors
	actorsLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 16));
	infoPanel.add(actorsLabel);
	JTextArea actorsText = new JTextArea(1,2);
	actorsText.setText(film.toStringActors());
	actorsText.setMaximumSize(new Dimension(Short.MAX_VALUE,20));	
	actorsText.setFocusable(false);
	actorsText.setEditable(false);
	actorsText.setFont(Decorations.FONT_BASIC.getFont(Font.PLAIN, 12));
	actorsText.setOpaque(false);
	infoPanel.add(actorsText);

	// Information for the director
	directorLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 16));
	infoPanel.add(directorLabel);
	JTextArea directorsText = new JTextArea(1,1);
	directorsText.setMaximumSize(new Dimension(Short.MAX_VALUE,20));	
	directorsText.setEditable(false);
	directorsText.setFocusable(false);
	directorsText.setText(film.getNamesDirector());
	directorsText.setFont(Decorations.FONT_BASIC.getFont(Font.PLAIN, 12));
	directorsText.setOpaque(false);
	infoPanel.add(directorsText);
	infoPanel.add(Box.createVerticalGlue());

	FilmCartPanel filmCartPanel = new FilmCartPanel(film);
	filmCartPanel.setScale(200);
	posterPanel.setLayout(new BoxLayout(posterPanel,BoxLayout.Y_AXIS));
	posterPanel.add(Box.createVerticalGlue());
	posterPanel.add(filmCartPanel);
	posterPanel.add(Box.createVerticalGlue());

	this.add(posterPanel, BorderLayout.WEST);
	this.add(infoPanel, BorderLayout.CENTER);	
	    
    }

    @Override
    public void setColorTheme(ColorThemes colorTheme) {
	switch(colorTheme) {
	case LIGHT_THEME:
	    break;
	    
	case DARK_THEME:
	    break;
	    
	default:
	    break;
	}
	
    }

    @Override
    public void setLanguage(ResourceBundle rb) {
	// TODO Auto-generated method stub
	
    }

}
