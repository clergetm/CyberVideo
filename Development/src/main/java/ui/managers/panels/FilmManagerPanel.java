package ui.managers.panels;

import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JPanel;

import ui.managers.GUIManager;
import ui.utils.factory.filmpanel.factories.FilmPanel;
import ui.utils.observer.colortheme.ColorThemes;
import ui.utils.observer.colortheme.IColorThemeObserver;
import ui.utils.observer.colortheme.palettes.Dark;
import ui.utils.observer.colortheme.palettes.Light;

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
public class FilmManagerPanel extends JPanel implements IColorThemeObserver{
    
    protected ArrayList<FilmPanel> films;
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
	this.films = new ArrayList<>();
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
	this.films.forEach(f -> {
	    f.getButtonMap().forEach((k,v) -> GUIManager.getInstance().unregisterColorTheme(v));
	    this.remove(f);
	});
	this.films.clear();
	this.revalidate();
	this.repaint();
    }
    
    @Override
    public void setColorTheme(ColorThemes colorTheme) {
	switch(colorTheme) {
	    case LIGHT_THEME:
		this.setBackground(Light.BG.getColor());
		break;
	    case DARK_THEME:
		this.setBackground(Dark.BG.getColor());
		break;
	    default:
		break;
	    }
	    
	
    }
    
}