package ui.managers;

import java.awt.LayoutManager;

import fc.films.Film;
import ui.utils.factory.filmpanel.factories.FilmRentPanel;

@SuppressWarnings("serial")
public class RentFilmManager extends FilmManager {

    public RentFilmManager(LayoutManager layout, double percent) {
	super(layout, percent);
    }

    @Override
    public void addFilm(Film film) {
	FilmRentPanel filmPanel = new FilmRentPanel(film);
	filmPanel.setScale(percent);
	this.films.add(filmPanel);
	this.add(filmPanel);
    }

}
