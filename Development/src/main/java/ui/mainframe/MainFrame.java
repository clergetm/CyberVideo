package ui.mainframe;

import ui.utils.Decorations;
import ui.utils.KeyboardDialog;
import ui.utils.colors.ColorTheme;
import ui.utils.observer.colortheme.palettes.Dark;
import ui.utils.observer.colortheme.palettes.Light;
import ui.managers.FilmManager;
import ui.managers.UIManager;
import ui.pages.actions.ActionPanel;
import ui.pages.welcome.WelcomePage;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.time.Year;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import fc.al2000.AL2000;
import fc.films.AgeRestriction;
import fc.films.BluRay;
import fc.films.Categories;
import fc.films.Film;
import fc.films.QRCode;
import fc.films.StatesBluRay;
import fc.films.Support;

/**
 * @author MathysC
 * Main Class of AL2000. Initialize the machine Interface.
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ColorTheme {

	/* Icon */
	public static final String ICO_APP = "App";

	/* Banners */
	public static final String IMG_BANNER_IT = "BannerIT";
	public static final String IMG_BANNER_POLICE = "BannerPOLICE";
	public static final String IMG_BANNER_DESERT = "BannerDESERT";
	
	/* Components */
	private TopBarPanel topBarPanel;
	private JLabel banner;
	private WelcomePage welcomePage;
	private ActionPanel actionPanel;
	
	/* Pages */
	private HashMap<Integer, JPanel> pages;
	private int currentPage;
	public static final int ID_WELCOME_PAGE = 0;
	public static final int ID_RESULT_PAGE = 1;
	public static final int ID_FILM_PAGE = 2;

	/* FC */
	private AL2000 fc;
	
	/* Films */
	private FilmManager filmManager; 
	
	/* Languages */
	private UIManager langManager;
	
	/**
	 * Constructor of Main.
	 * Initialize the AL2000 Functional Core.
	 * Initialize the AL2000 Main Window.
	 * @author MathysC
	 *
	 */
	public MainFrame() {
	    super("AL2000");
	    
	    /*Initialize Components */
	    topBarPanel = new TopBarPanel();
	    banner = new JLabel();
	    welcomePage = new WelcomePage();
	    actionPanel = new ActionPanel();
	    
	    /* Initialize pages map */
	    this.pages = new HashMap<>();
	    this.pages.put(ID_WELCOME_PAGE, welcomePage);
	    this.pages.put(ID_RESULT_PAGE, actionPanel);
	    
	    // ! Initialize FC before the GUI (atleast this.setLanguage and this.setLight functions)
	    this.fc = new AL2000();
	    
	    this.filmManager = FilmManager.getInstance();   

//	    TODO add Film from fc in filmManager
	   
	    this.filmManager.getCartManager().register(this.actionPanel.getCartPanel());
//	    TODO register client in CartManager
	    
	    
	    /* Initialize GUI */
	    this.createGUI();
	    this.setLight();
	       
	    /* Languages */
	    this.langManager = UIManager.getInstance();
	    this.langManager.register(this.welcomePage.getSuggestionsPanel());
	    this.langManager.register(this.welcomePage.getLoginPanel());
	    this.langManager.register(this.actionPanel);
	    this.langManager.register(this.actionPanel.getCartPanel());
	    this.langManager.register(this.actionPanel.getSearchPage());
	    this.langManager.register(this.actionPanel.getFilmPage());
	    this.langManager.register(KeyboardDialog.getInstance());
	    this.langManager.refreshMultilingual();
	    /* Interaction */
	    new Interaction(this);    
	    
	    
	    
		/* Create Film attributes */
	    final String title = "The Matrix",
		    synopsis = "Thomas A. Anderson is a man living two lives. "
						+ "By day he is an average computer programmer and by night "
						+ "a hacker known as Neo. Neo has always questioned his reality,"
						+ " but the truth is far beyond his imagination...",
				director_lname = "Wachowski",
				director_fname = "Lana";
	    final String[] actors = {"Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss", "Hugo Weaving"};
	    final AgeRestriction restriction = AgeRestriction.EVERYONE;
	    final Categories[] categories = {Categories.ACTION, Categories.DRAMAS};
	    final Year year = Year.of(1999);
	    final Support[] supports = {new BluRay(22.5, StatesBluRay.RENTED), new QRCode(), new BluRay(22.50, StatesBluRay.AVAILABLE)};
		
	    /* Create an instance of a Film for test Film class */
	    Film film = new Film(title, synopsis, actors, director_fname, director_lname, year, categories, restriction, supports);
		
	    
	    this.filmManager.addFilm(film);
	    this.actionPanel.getSearchPage().addResult(film);
	}
	
	/**
	 * Create and set all GUI Components.
	 * @author MathysC
	 *
	 */
	private void createGUI() {
	    
	    // Set Icon
	    this.setIconImage(Decorations.getIco(ICO_APP));
		
	    // Put the Frame on Full Screen
	    this.setUndecorated(true);
	    this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
	    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	    this.setResizable(false);

	    this.setLayout(new BorderLayout());

	    // Add the Top Bar.
	    this.add(this.topBarPanel, BorderLayout.NORTH);

	    // Add a banner.
	    banner = new JLabel(Decorations.getImg(IMG_BANNER_DESERT));
	    banner.setOpaque(false);
	    this.add(banner, BorderLayout.WEST);

	    // Add the welcome page.
	    this.add(welcomePage, BorderLayout.CENTER);
	    this.currentPage = ID_WELCOME_PAGE;

	    this.pack();    
	}
	
	/**
	 * Change the current displayed Page
	 * @author MathysC
	 *
	 * @param id The id number of the page to display.
	 */
	public void changeCurrentPage(int id) {
	    this.remove(this.pages.get(currentPage));
	    this.revalidate();
	    this.repaint();

	    this.add(this.pages.get(id), BorderLayout.CENTER);
	    this.currentPage = id;

	    if(id != ID_WELCOME_PAGE) {
		this.actionPanel.changeCurrentActionPage(id);
	    }

	    this.pack();
	}
	
	@Override
	public void setLight() {
	    // This JFrame
	    this.setBackground(Light.BG.getColor());

	    // Banner
	    banner.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Light.BG.getColor()));

	    // Top Bar
	    this.topBarPanel.setLight();

	    // Welcome Page
	    this.welcomePage.setLight();

	    // Action Panel
	    this.actionPanel.setLight();

	}

	@Override
	public void setDark() {
	    // This JFrame
	    this.setBackground(Dark.BG.getColor());

	    // Banner
	    banner.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Dark.BG.getColor()));

	    // Top Bar
	    this.topBarPanel.setDark();

	    // Welcome Page
	    this.welcomePage.setDark();

	    // Action Panel
	    this.actionPanel.setDark();
	    
	}
	
	/**
	 * Start the MainFrame
	 * @author MathysC
	 * 
	 */
	public void start() {
	    this.setVisible(true);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Run the Main Window of AL2000.
	 * @author MathysC
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(() -> new MainFrame().start());    
	}

	/**
	 * @return the topBarPanel
	 */
	public TopBarPanel getTopBarPanel() {
	    return topBarPanel;
	}

	/**
	 * @return the welcomePage
	 */
	public WelcomePage getWelcomePage() {
	    return welcomePage;
	}

	/**
	 * @return the actionPanel
	 */
	public ActionPanel getActionPanel() {
	    return actionPanel;
	}
	
}