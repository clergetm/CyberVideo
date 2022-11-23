package ui.pages;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ResourceBundle;
import java.awt.Component;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fc.films.Film;
import ui.utils.colors.ColorTheme;
import ui.utils.colors.Dark;
import ui.utils.colors.Light;
import ui.utils.factory.filmpanel.factories.FilmRentPanel;
import ui.utils.observer.multilingual.IMultilingualObserver;


@SuppressWarnings("serial")
public class FilmPage extends JPanel implements IMultilingualObserver, ColorTheme{
    private JPanel filmInfo = new JPanel();
    private JPanel filmInfoContainer = new JPanel();
    private Film film;
    private FilmRentPanel filmPoster; 
    private String actorsTest ;
  
    JLabel movieName = new JLabel();
    JLabel synopsis = new JLabel();
    JLabel directors = new JLabel();
    JLabel actors = new JLabel();
    JTextArea synopsisText = new JTextArea(50,50);
    JTextArea directorsText = new JTextArea(50,50);
    JTextArea actorsText = new JTextArea(50,50);


//FIXME
    public FilmPage(){
//
//	JPanel tempFilmPoster = new JPanel();
//	tempFilmPoster.setLayout(new BoxLayout(tempFilmPoster,BoxLayout.Y_AXIS));
//	tempFilmPoster.setOpaque(false);
//
//        this.filmPoster = new FilmPanel(this.film, 200, false);
//        
//        tempFilmPoster.add( Box.createVerticalGlue());
//        tempFilmPoster.add(filmPoster);
//        tempFilmPoster.add( Box.createVerticalGlue());
//        this.setBackground(Color.CYAN);
//        this.setLayout(new BorderLayout());
//
//       //Creating a container to place the actual JPanel filmInfo
//       filmInfoContainer.setLayout(new FlowLayout(FlowLayout.LEFT,0,30));
//       filmInfoContainer.setOpaque(false);
//       filmInfo.setOpaque(false);
//
//       //Creating the filmInfo JPanel
//       //Setting Layout and size 
//        filmInfo.setLayout(new GridLayout( 0,1 ));
//        filmInfo.setPreferredSize(new Dimension(500, 500));
//        filmInfo.setMaximumSize(new Dimension(500, 500));
//
//        //creating JLabel MovieName
//        setTitle(this.movieName,(this.film.getTitle()),filmInfo,26);
//        
//
//        //Creating JLabel and textArea for the synopsis
//        setTitle(this.synopsis,"Synopsis",filmInfo,20);
//        setTextArea(synopsisText,filmInfo,this.film.getSynopsis());
//
//        //Creating JLabel and textArea for the directors
//        setTitle(this.directors,"Directors",filmInfo,20);
//        setTextArea(directorsText,filmInfo,this.film.getNamesDirector());
//
//        //Creating JLabel and textArea for the actors
//        this.actorsTest = String.join(", ",this.film.getActors()); //Converts array of String to a single String
//        setTitle(this.actors,"Actors",filmInfo,20);
//        setTextArea(actorsText,filmInfo,actorsTest);
//        
//        //Adding elements to Jpanels
//        filmInfoContainer.add(filmInfo);
//        this.add(filmInfoContainer, BorderLayout.CENTER);
//        this.add(tempFilmPoster, BorderLayout.WEST);
        
    }   
   
  
    //Function to set up the different text areas of the JPanel
    public void setTextArea(JTextArea a,JPanel j,String s){
       // a = new JTextArea(s,50,50);
        a.setText(s);
        a.setSize(100,100);
        a.setEditable(false);
        a.setOpaque(false);
        a.setCursor(null);
        a.setFocusable(false);
        a.setLineWrap(true);
        a.setMaximumSize( a.getPreferredSize());
        a.setFont(new Font("Verdana", Font.PLAIN, 14));
        j.add(a);
    }
    

    //Function to set up the different JLabels of the JPanel
    public void setTitle(JLabel b, String title,JPanel j,int fontSize){
        b.setText(title);
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        b.setFont(new Font("Verdana", Font.PLAIN, fontSize));
        j.add(b);
    }


    @Override
    public void setLight() {
//	    this.filmPoster.setLight();
//        // This JPanel
//        this.setBackground(Light.BG.getColor());
//        //JLabel movieName, synopsis, directors, actors;
//        this.movieName.setForeground(Light.BLACK.getColor());
//        this.synopsis.setForeground(Light.BLACK.getColor());
//        this.directors.setForeground(Light.BLACK.getColor());
//        this.actors.setForeground(Light.BLACK.getColor());
//
//        //JTextAreas
//        this.synopsisText.setForeground(Light.BLACK.getColor());
//        this.directorsText.setForeground(Light.BLACK.getColor());
//        this.actorsText.setForeground(Light.BLACK.getColor());

    }


    @Override
    public void setDark() {
//	    this.filmPoster.setDark();
//        // This JPanel
//        this.setBackground(Dark.BG.getColor());
//        //JLabel movieName, synopsis, directors, actors;
//        this.movieName.setForeground(Dark.FOREGROUND.getColor());
//        this.synopsis.setForeground(Dark.FOREGROUND.getColor());
//        this.directors.setForeground(Dark.FOREGROUND.getColor());
//        this.actors.setForeground(Dark.FOREGROUND.getColor());
//
//        //JTextAreas
//        this.synopsisText.setForeground(Dark.FOREGROUND.getColor());
//        this.directorsText.setForeground(Dark.FOREGROUND.getColor());
//        this.actorsText.setForeground(Dark.FOREGROUND.getColor());
//	
    }


    @Override
    public void setLanguage(ResourceBundle rb) {
	// TODO Auto-generated method stub
	
    }

}
