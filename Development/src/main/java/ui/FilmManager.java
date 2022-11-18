package ui;

import java.awt.LayoutManager;
import java.util.HashMap;

import javax.swing.JPanel;

import fc.Films.Film;
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

    private HashMap < Film,FilmPanel > films = new HashMap<> (); // List of managed Films. 
    private double percent; // scale of dimension of Films
    /**
     * Constructor of FilmManager
     * @author MathysC
     * @param layout
     */
    public FilmManager(LayoutManager layout, double p) {
        this.setLayout(layout);
        this.percent = p;
    }

    /**
     * Add a Film in the list of films and a JComponent of this film.
     * @author MathysC
     * @param film The {@code fc.Film} to add.
     */
    public void addFilm(Film film) {
    	FilmPanel panel = new FilmPanel(film, this.percent);
        this.films.put(film, panel);
        this.add(panel);
    }


    /**
     * Remove one Film from the Manager. 
     * @author MathysC
     * @param film The {@code fc.Film} to remove.
     * @return true if the film is removed, else false.
     */
    public boolean removeFilm(Film film) {
        if (!this.films.containsKey(film))
            return false;
        
        this.remove(this.films.get(film));
        this.films.remove(film);
        
        //IMPORTANT: We have to revalidate and repaint when we remove a graphic component.
        this.revalidate();
        this.repaint();
        return true;
    }

    /**
     * Update one film from the Manager.
     * @author MathysC
     * @param film The {@code fc.Film} to update.
     * @return true if the film was updated, else false.
     */
    public boolean updateFilm(Film film) {
        if (!this.films.containsKey(film))
            return false;
       
//        this.films.get(film).update();
        return true;
    }

	@Override
	public void setLight() {
		// This JPanel
		this.setBackground(Light.BG.getColor());
				
		// Films
		for(FilmPanel filmPanel: this.films.values())
			filmPanel.setLight();		
	}

	@Override
	public void setDark() {
		// This JPanel
		this.setBackground(Dark.BG.getColor());
		
		// Films
		for(FilmPanel filmPanel: this.films.values())
			filmPanel.setDark();			
	}
}