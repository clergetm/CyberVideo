package ui.managers;

import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JPanel;

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
public class FilmManager extends JPanel implements ColorTheme{
    
    private ArrayList<FilmPanel> films = new ArrayList<>();
    private double percent; // Scale of FilmPanels
    
    /**
     * Constructor of FilmManager
     * @author MathysC
     *
     * @param layout the layout to set.
     * @param percent the scale used for FilmPanels.
     */
    public FilmManager(LayoutManager layout, double percent) {
	this.setLayout(layout);
	this.percent = percent;
    }

    /**
     * Add FilmPanel to the Manager.
     * @author MathysC
     * @param filmPanel The FilmPanel to add.
     */
    public void addFilm(FilmPanel filmPanel) {
	filmPanel.setScale(percent);
	this.films.add(filmPanel);
	this.add(filmPanel);
    }
    
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