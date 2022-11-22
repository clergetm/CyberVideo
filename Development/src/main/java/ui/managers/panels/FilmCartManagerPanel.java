package ui.managers.panels;

import java.awt.LayoutManager;

import fc.films.Film;
import ui.utils.factory.filmpanel.factories.FilmCartPanel;

@SuppressWarnings("serial")
public class FilmCartManagerPanel extends FilmManagerPanel {
    
    public FilmCartManagerPanel(LayoutManager layout, double percent) {
	super(layout, percent);
    }
    
    public void addFilm(Film film) {
	FilmCartPanel filmPanel = new FilmCartPanel(film);
	filmPanel.setScale(percent);
	this.films.add(filmPanel);
	this.add(filmPanel);
    }
}
