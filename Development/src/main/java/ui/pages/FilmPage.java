package ui.pages;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.time.Year;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import fc.films.AgeRestriction;
import fc.films.BluRay;
import fc.films.Categories;
import fc.films.Film;
import fc.films.QRCode;
import fc.films.StatesBluRay;
import fc.films.Support;
import ui.utils.Decorations;
import ui.utils.colors.ColorTheme;
import ui.utils.factory.filmpanel.factories.FilmCartPanel;

@SuppressWarnings("serial")
public class FilmPage extends JPanel implements ColorTheme {

	private JPanel infoPanel; // Info on right : all the text
	private JPanel posterPanel;

	public FilmPage() {
		// Initialisaiton des attributs d'instances 
		infoPanel= new JPanel();
		posterPanel = new JPanel();

		setLayout(new BorderLayout());

		
		String title = "The Matrix",
				synopsis = "Thomas A. Anderson is a man living two lives. "
						+ "By day he is an average computer programmer and by night "
						+ "a hacker known as Neo. Neo has always questioned his reality,"
						+ " but the truth is far beyond his imagination...",
				director_lname = "Wachowski",
				director_fname = "Lana";
		String[] actors = {"Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss", "Hugo Weaving"};
		AgeRestriction restriction = AgeRestriction.EVERYONE;
		Categories[] categories = {Categories.ACTION, Categories.DRAMAS};
		Year year = Year.of(1999);
		Support[] supports = {new BluRay(22.5, StatesBluRay.RENTED), new QRCode(), new BluRay(22.50, StatesBluRay.AVAILABLE)};
		
		/* Create an instance of a Film for test Film class */
		Film film = new Film(title, synopsis, actors, director_fname, director_lname, year, categories, restriction, supports);
		
		FilmCartPanel filmCart = new FilmCartPanel(film);
		
		showFilm(filmCart);

	}

	private void createGUI() {

		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));


		JLabel titleLabel = new JLabel("TITRE");
		titleLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));
		infoPanel.add(titleLabel);

		JLabel acteursLabel = new JLabel("Acteurs");
		acteursLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 16));
		infoPanel.add(acteursLabel);

		JLabel synopsisLabel = new JLabel("Synopsis");
		synopsisLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 16));
		infoPanel.add(synopsisLabel);

		JLabel directeurLabel = new JLabel("Directeur");
		directeurLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 16));
		infoPanel.add(directeurLabel);

		this.add(infoPanel, BorderLayout.CENTER);	
	}

	private void showFilm(FilmCartPanel filmCart) {
		Film film = filmCart.getFilm();

		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));


		JLabel titleLabel = new JLabel(film.getTitle());
		titleLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 20));
		infoPanel.add(titleLabel);

		// Informations for actors
		JLabel actorsLabel = new JLabel("Actors");
		actorsLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 16));
		infoPanel.add(actorsLabel);
		JTextArea actorsText = new JTextArea(25,25);
		actorsText.setText(film.getActors().toString());
		actorsText.setFont(Decorations.FONT_BASIC.getFont(Font.PLAIN, 12));
		actorsText.setOpaque(false);
		infoPanel.add(actorsText);

		// Informations for the synopsis
		JLabel synopsisLabel = new JLabel("Synopsis");
		synopsisLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 16));
		infoPanel.add(synopsisLabel);
		JTextArea synopsisText = new JTextArea(50,50);
		synopsisText.setText(film.getSynopsis());
		synopsisText.setFont(Decorations.FONT_BASIC.getFont(Font.PLAIN, 12));
		synopsisText.setOpaque(false);
		infoPanel.add(synopsisText);

		// Information for the director
		JLabel directorLabel = new JLabel("Director");
		directorLabel.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 16));
		infoPanel.add(directorLabel);
		JTextArea directorsText = new JTextArea(50,50);
		directorsText.setText(film.getNamesDirector());
		directorsText.setFont(Decorations.FONT_BASIC.getFont(Font.PLAIN, 12));
		directorsText.setOpaque(false);
		infoPanel.add(directorsText);
		
		
		

		this.add(infoPanel, BorderLayout.CENTER);	
	}





	@Override
	public void setLight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDark() {
		// TODO Auto-generated method stub

	}


}


//package ui.pages;
//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.GridLayout;
//import java.awt.Component;
//import java.awt.Color;
//
//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextArea;
//
//import fc.films.Film;
//import ui.utils.colors.ColorTheme;
//import ui.utils.colors.Dark;
//import ui.utils.colors.Light;
//import ui.utils.factory.filmpanel.factories.FilmCartPanel;
//import ui.utils.factory.filmpanel.factories.FilmPanel;
//import ui.utils.factory.filmpanel.factories.FilmRentPanel;
//
//
//@SuppressWarnings("serial")
//public class FilmPage extends JPanel implements ColorTheme {
//	private JPanel infoPanel = new JPanel(); // Info on right : all the text
//	
//	
////	private FilmCartPanel posterFilmCartPanel; // Infos on left : poster and the 2 buttons
//
//	private JPanel filmInfoContainer = new JPanel(); // ?
//	private Film film;
//	private String actorsTest ;
//
//	JLabel filmName;// = new JLabel();
//	JLabel synopsis; //= new JLabel();
//	JLabel directors; //= new JLabel();
//	JLabel actors; //= new JLabel();
//	JTextArea synopsisText = new JTextArea(50,50);
//	JTextArea directorsText = new JTextArea(50,50);
//	JTextArea actorsText = new JTextArea(50,50);
//
//
//	//FIXME
//	public FilmPage(){
//				
//		// Add the option of the Film Page
//		this.setLayout(new BorderLayout());
////		this.setBackground(Color.CYAN);
//		
//		
//		
//		// Add the option for the panel ?
////		filmInfoContainer.setOpaque(false); // ?
//		
//		
//		
//		// Add the option for the panel info
//		infoPanel.setOpaque(false);
//		infoPanel.setPreferredSize(new Dimension(500, 500));
//		infoPanel.setMaximumSize(new Dimension(500, 500));
//		
//		createGUI();
//		
//		//Creating JLabel and textArea for the synopsis
////		setTitle(this.synopsis,"Synopsis",infoPanel,20);
////		setTextArea(synopsisText,infoPanel,this.film.getSynopsis());
//		
//		//Creating JLabel and textArea for the directors
////		setTitle(this.directors,"Directors",infoPanel,20);
////		setTextArea(directorsText,infoPanel,this.film.getNamesDirector());
//
//		//Creating JLabel and textArea for the actors
////		this.actorsTest = String.join(", ",this.film.getActors()); //Converts array of String to a single String
////		setTitle(this.actors,"Actors",infoPanel,20);
////		setTextArea(actorsText,infoPanel,actorsTest);
//		
//		
//		
//		
//		// Add the panel at FilmPage and set the layout
////		this.add(posterFilmCartPanel, BorderLayout.WEST);
////		this.add(infoPanel, BorderLayout.CENTER);
//		
//		
//		
//		
//// 		PAS COMPRIS
////		JPanel tempFilmPoster = new JPanel();
////		tempFilmPoster.setLayout(new BoxLayout(tempFilmPoster,BoxLayout.Y_AXIS));
////		tempFilmPoster.setOpaque(false);
//
//		
////		PAS COMPRIS
////		tempFilmPoster.add( Box.createVerticalGlue());
////		tempFilmPoster.add(posterFilmCartPanel);
////		tempFilmPoster.add( Box.createVerticalGlue());
//		
//
////		//Creating a container to place the actual JPanel filmInfo
////		filmInfoContainer.setLayout(new FlowLayout(FlowLayout.LEFT,0,30));
////		filmInfoContainer.setOpaque(false);
////		infoPanel.setOpaque(false);
//
//		//Creating the filmInfo JPanel
//		//Setting Layout and size 
////		infoPanel.setLayout(new GridLayout( 0,1 ));
////		infoPanel.setPreferredSize(new Dimension(500, 500));
////		infoPanel.setMaximumSize(new Dimension(500, 500));
//
////		//creating JLabel MovieName
////		setTitle(this.filmName,(this.film.getTitle()),infoPanel,26);
//
////		//Adding elements to Jpanels
////		filmInfoContainer.add(infoPanel);
////		this.add(filmInfoContainer, BorderLayout.CENTER);
////		this.add(tempFilmPoster, BorderLayout.WEST);
//
//	} 
//	
//	
//	public void createGUI() {
//		this.actors = new JLabel("",(int)LEFT_ALIGNMENT);
//		infoPanel.add(actors);
//		
////		this.setTitle(this.directors,"Directors",infoPanel,20);
////		this.setTitle(this.synopsis,"Synopsis",infoPanel,20);
//	}
//	
//	public void showFilm(FilmRentPanel filmRent) {
//		Film film = filmRent.getFilm();
////		this.setTitle(this.synopsis,"Synopsis",infoPanel,20);
//		setTextArea(synopsisText,infoPanel,this.film.getSynopsis());
//	}
//
//
//	//Function to set up the different text areas of the JPanel
//	public void setTextArea(JTextArea a,JPanel j,String s){
//		// a = new JTextArea(s,50,50);
//		a.setText(s);
//		a.setSize(100,100);
//		a.setEditable(false);
//		a.setOpaque(false);
//		a.setCursor(null);
//		a.setFocusable(false);
//		a.setLineWrap(true);
//		a.setMaximumSize( a.getPreferredSize());
//		a.setFont(new Font("Verdana", Font.PLAIN, 14));
//		j.add(a);
//	}
//
//
////	//Function to set up the different JLabels of the JPanel
////	public void setTitle(JLabel b, String title,JPanel j,int fontSize){
////		b.setText(title);
////		b.setAlignmentX(Component.LEFT_ALIGNMENT);
////		b.setFont(new Font("Verdana", Font.PLAIN, fontSize));
////		j.add(b);
////	}
//
//
//	@Override
//	public void setLight() {
//		//	    this.filmPoster.setLight();
//		//        // This JPanel
//		//        this.setBackground(Light.BG.getColor());
//		//        //JLabel movieName, synopsis, directors, actors;
//		//        this.movieName.setForeground(Light.BLACK.getColor());
//		//        this.synopsis.setForeground(Light.BLACK.getColor());
//		//        this.directors.setForeground(Light.BLACK.getColor());
//		//        this.actors.setForeground(Light.BLACK.getColor());
//		//
//		//        //JTextAreas
//		//        this.synopsisText.setForeground(Light.BLACK.getColor());
//		//        this.directorsText.setForeground(Light.BLACK.getColor());
//		//        this.actorsText.setForeground(Light.BLACK.getColor());
//
//	}
//
//
//	@Override
//	public void setDark() {
//		//	    this.filmPoster.setDark();
//		//        // This JPanel
//		//        this.setBackground(Dark.BG.getColor());
//		//        //JLabel movieName, synopsis, directors, actors;
//		//        this.movieName.setForeground(Dark.FOREGROUND.getColor());
//		//        this.synopsis.setForeground(Dark.FOREGROUND.getColor());
//		//        this.directors.setForeground(Dark.FOREGROUND.getColor());
//		//        this.actors.setForeground(Dark.FOREGROUND.getColor());
//		//
//		//        //JTextAreas
//		//        this.synopsisText.setForeground(Dark.FOREGROUND.getColor());
//		//        this.directorsText.setForeground(Dark.FOREGROUND.getColor());
//		//        this.actorsText.setForeground(Dark.FOREGROUND.getColor());
//		//	
//	}
//
//}
