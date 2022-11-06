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

    private List < Film > films = new ArrayList < Film > (); // List of managed Films. 

    /**
     * Constructor of FilmManager
     * @author MathysC
     *
     * @param layout
     */
    public FilmManager(LayoutManager layout) {
        this.setLayout(layout);

        /*TODO: remove it after implementing Film from fc package*/
        for (int i = 0; i < 100; i++)
            this.addFilm(new Film());
    }

    /**
     * TODO: Use Film class from fc.
     * Add a Film in the list of films and a JComponent of this film.
     * @author MathysC
     *
     * @param film
     */
    public void addFilm(Film film) {
        this.films.add(film);
        this.add(film);
    }

    /**
     * TODO: Use Film class from fc.
     * Remove one Film from the list.
     * @author MathysC
     *
     * @param film
     * @return
     */
    public boolean removeFilm(Film film) {
        if (!this.films.contains(film))
            return false;

        this.films.remove(film);
        this.remove(film);

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
     * @param film
     * @return
     */
    public boolean updateFilm(Film film) {
        /*TODO*/
        return false;
    }

	@Override
	public void setLight() {
		// This JPanel
		this.setBackground(Light.BG.getColor());
				
		// Films
		for(Film film: this.films)
			film.setLight();		
	}

	@Override
	public void setDark() {
		// This JPanel
		this.setBackground(Dark.BG.getColor());
		
		// Films
		for(Film film: this.films)
			film.setDark();			
	}
}