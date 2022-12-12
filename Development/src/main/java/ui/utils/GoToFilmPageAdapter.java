package ui.utils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.mainframe.MainFrame;
import ui.utils.factory.filmpanel.factories.FilmPanel;

/**
 * Mouse Adapter implemented by poster on {@code FilmPanel}
 * @author MathysC
 *
 */
public class GoToFilmPageAdapter extends MouseAdapter {
    private FilmPanel panel;
    
    /**
     * Default constructor for {@code GoToFilmPageAdapter}
     * @author MathysC
     *
     * @param fp The FilmPanel.
     */
    public GoToFilmPageAdapter(FilmPanel fp) {
	this.panel = fp;
    }
    
    /**
     * When clicked, display the FilmPage and all data related to the Film.
     * @author MathysC
     *
     */
    @Override
    public void mouseClicked(MouseEvent e) {
	MainFrame.getInstance().changeCurrentPage(MainFrame.ID_FILM_PAGE);
	MainFrame.getInstance().getActionPanel().getFilmPage().showFilm(this.panel);
    }
}
