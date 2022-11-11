package ui;

import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ui.Colors.ColorTheme;
import ui.Colors.Dark;
import ui.Colors.Light;

/**
 * TODO: Implement use of Film class from fc package.
 *
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class FilmManager extends JPanel implements ColorTheme {

    private List < FilmPanel > filmPanels = new ArrayList < FilmPanel > (); // List of managed Films. 

    /**
     * Constructor of FilmManager
     * @author MathysC
     *
     * @param layout
     */
    public FilmManager(LayoutManager layout) {
        this.setLayout(layout);
    }

    /**
     * TODO: Use Film class from fc.
     * Add a Film in the list of films and a JComponent of this film.
     * @author MathysC
     *
     * @param filmPanel
     */
    public void addFilm(FilmPanel filmPanel) {
        this.filmPanels.add(filmPanel);
        this.add(filmPanel);
    }

    /**
     * TODO: Use Film class from fc.
     * Remove one Film from the list.
     * @author MathysC
     *
     * @param filmPanel
     * @return
     */
    public boolean removeFilm(FilmPanel filmPanel) {
        if (!this.filmPanels.contains(filmPanel))
            return false;

        this.filmPanels.remove(filmPanel);
        this.remove(filmPanel);

        //IMPORTANT: We have to revalidate and repaint when we remove a graphic component.
        this.revalidate();
        this.repaint();
        return true;
    }

    /**
     * TODO: Use Film class from fc.
     * Update one film from the list.
     * @author MathysC
     *
     * @param filmPanel
     * @return
     */
    public boolean updateFilm(FilmPanel filmPanel) {
        /*TODO*/
        return false;
    }

	@Override
	public void setLight() {
		// This JPanel
		this.setBackground(Light.BG.getColor());
				
		// Films
		for(FilmPanel filmPanel: this.filmPanels)
			filmPanel.setLight();		
	}

	@Override
	public void setDark() {
		// This JPanel
		this.setBackground(Dark.BG.getColor());
		
		// Films
		for(FilmPanel filmPanel: this.filmPanels)
			filmPanel.setDark();			
	}
}