package ui.Pages;
import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fc.Films.Film;
import ui.FilmPanel;

public class FilmPage extends JPanel {
    private Film film;
    private FilmPanel filmPoster; 
    private FilmInfo info;
   
    public FilmPage(Film f){
        this.filmPoster = new FilmPanel(f, 200);
        this.info = new FilmInfo(f);
        this.setLayout(new BorderLayout());
        this.add(info, BorderLayout.CENTER);
        this.add(filmPoster, BorderLayout.WEST);

    }   
    
    public class FilmInfo extends JPanel {
        JLabel movieName, synopsis, Directors, Actors;
        JTextArea synopsisText, DirectorsText, ActorsText;
        public FilmInfo(Film f){
            this.movieName = new JLabel(f.getTitle());
            this.synopsis = new JLabel("Synopsis");
            this.Directors = new JLabel("Directors");
            this.Actors = new JLabel("Actors");

        }
    }
   
    public void setTextArea(JTextArea a){
        a = new JTextArea();
        a.setEditable(false);
        a.setOpaque(false);
       

    }

}
