package ui.Pages;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fc.Films.Film;
import ui.FilmPanel;

public class FilmPage extends JPanel {
    private JPanel filmInfo = new JPanel();
    private JPanel filmInfoContainer = new JPanel();
    private String[] l = {"Acteur1","acteur2"};
    private Film film = new Film("HELLO", "LOREM IPSUM", l , "DIR1", "DIR2", null, null);
    private FilmPanel filmPoster; 
    private String actorsTest ;
  
    JLabel movieName, synopsis, directors, actors;
    JTextArea synopsisText, directorsText, actorsText;
    public FilmPage(){
        this.filmPoster = new FilmPanel(this.film, 200);
        this.setLayout(new BorderLayout());

       //Creating a container to place the actual JPanel filmInfo
       filmInfoContainer.setLayout(new FlowLayout(FlowLayout.LEFT,0,30));
        

       //Creating the filmInfo JPanel
       //Setting Layout and size 
        filmInfo.setLayout(new java.awt.GridLayout( 0,1 ));
        filmInfo.setPreferredSize(new Dimension(500, 500));
        filmInfo.setMaximumSize(new Dimension(500, 500));

        //creating JLabel MovieName
        setTitle(this.movieName,(this.film.getTitle()),filmInfo,26);
        

        //Creating JLabel and textArea for the synopsis
        setTitle(this.synopsis,"Synopsis",filmInfo,20);
        setTextArea(synopsisText,filmInfo,this.film.getSynopsis());

        //Creating JLabel and textArea for the directors
        setTitle(this.directors,"Directors",filmInfo,20);
        setTextArea(directorsText,filmInfo,this.film.getNamesDirector());

        //Creating JLabel and textArea for the actors
        this.actorsTest = String.join(", ",this.film.getActors()); //Converts array of String to a single String
        setTitle(this.actors,"Actors",filmInfo,20);
        setTextArea(actorsText,filmInfo,actorsTest);
        
        //Adding elements to Jpanels
        filmInfoContainer.add(filmInfo);
        this.add(filmInfoContainer, BorderLayout.CENTER);
        this.add(filmPoster, BorderLayout.WEST);
        
    }   
   
  
    //Function to set up the different text areas of the JPanel
    public void setTextArea(JTextArea a,JPanel j,String s){
        a = new JTextArea(s,50,50);
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
        b = new JLabel(title);
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        b.setFont(new Font("Verdana", Font.PLAIN, fontSize));
        j.add(b);
    }

}
