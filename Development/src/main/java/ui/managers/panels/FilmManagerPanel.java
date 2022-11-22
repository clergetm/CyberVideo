package ui.managers.panels;

import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JPanel;

import fc.films.Film;
import ui.utils.colors.ColorTheme;
import ui.utils.colors.Dark;
import ui.utils.colors.Light;
import ui.utils.factory.filmpanel.factories.FilmPanel;

/**
 *
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public abstract class FilmManagerPanel extends JPanel implements ColorTheme{
    
    protected ArrayList<FilmPanel> films = new ArrayList<>();
    protected double percent; // Scale of FilmPanels
    
    /**
     * Constructor of FilmManager
     * @author MathysC
     *
     * @param layout the layout to set.
     * @param percent the scale used for FilmPanels.
     */
    protected FilmManagerPanel(LayoutManager layout, double percent) {
	this.setLayout(layout);
	this.percent = percent;
    }

    /**
     * Add FilmPanel to the Manager.
     * @author MathysC
     * @param film the film to add
     */
    public abstract void addFilm(Film film);
    
    /**
     * Clear the manager. Empty the list, remove FilmPanels.
     * @author MathysC
     *
     */
    public void clear() {
	this.films.forEach(this::remove);
	this.films.clear();
	this.revalidate();
	this.repaint();
    }
    
    @Override
    public void setLight() {
	this.setBackground(Light.BG.getColor());
	this.films.forEach(FilmPanel::setLight);
    }

    @Override
    public void setDark() {
	this.setBackground(Dark.BG.getColor());
	this.films.forEach(FilmPanel::setDark);
    }
    
}