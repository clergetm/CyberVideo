package ui.managers.panels;

import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JPanel;

import fc.films.Film;
import ui.utils.colors.ColorTheme;
import ui.utils.colors.Dark;
import ui.utils.colors.Light;
import ui.utils.factory.filmpanel.factories.FilmCartPanel;
import ui.utils.factory.filmpanel.factories.FilmPanel;

/**
 * {@code JPanel} added to {@code JScrollPane} where {@code FilmPanel} need to be displayed.
 * @author MathysC
 * @see ui.utils.factory.filmpanel.factories.FilmPanel
 * @see ui.utils.factory.filmpanel.factories.FilmCartPanel
 * @see ui.utils.factory.filmpanel.factories.FilmRentPanel
 * @see ui.utils.factory.filmpanel.products.FilmPanelButton
 * @see ui.utils.factory.filmpanel.products.CartButton
 * @see ui.utils.factory.filmpanel.products.RentButton
 */
@SuppressWarnings("serial")
public class FilmManagerPanel extends JPanel implements ColorTheme{
    
    protected ArrayList<FilmPanel> films = new ArrayList<>();
    protected double percent; // Scale of FilmPanels
    
    /**
     * Constructor of FilmManager
     * @author MathysC
     *
     * @param layout the layout to set.
     * @param percent the scale used for FilmPanels.
     */
    public FilmManagerPanel(LayoutManager layout, double percent) {
	this.setLayout(layout);
	this.percent = percent;
    }

    /**
     * Add FilmPanel to the Manager.
     * @author MathysC
     * @param filmPanel the {@code FilmPanel} to add
     */
    public void addPanel(FilmPanel filmPanel) {
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