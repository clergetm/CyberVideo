package ui.mainframe;

import ui.utils.KeyboardDialog;
import ui.utils.Resources;
import ui.utils.observer.colortheme.ColorThemes;
import ui.utils.observer.colortheme.IColorThemeObserver;
import ui.utils.observer.colortheme.palettes.Dark;
import ui.utils.observer.colortheme.palettes.Light;
import ui.GUIComponent;
import ui.managers.FilmManager;
import ui.managers.GUIManager;
import ui.pages.actions.ActionPanel;
import ui.pages.welcome.WelcomePage;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
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
public class MainFrame extends JFrame implements GUIComponent, IColorThemeObserver {

	/* Icon */
	public static final String ICO_APP = "App";
	/* Banners */
	public static final String IMG_BANNER_IT = "BannerIT";
	public static final String IMG_BANNER_POLICE = "BannerPOLICE";
	public static final String IMG_BANNER_DESERT = "BannerDESERT";
	/* Pages */
	public static final int ID_WELCOME_PAGE = 0;
	public static final int ID_RESULT_PAGE = 1;
	public static final int ID_FILM_PAGE = 2;
	
	/* Components */
	private TopBarPanel topBarPanel;
	private JLabel banner;
	private WelcomePage welcomePage;
	private ActionPanel actionPanel;
	/* Pages */
	private HashMap<Integer, JPanel> pages;
	private int currentPage;
	/* FC */
	private AL2000 fc;
	/* Films */
	private FilmManager filmManager; 
	/* Languages and Color Themes */
	private GUIManager guiManager;
	
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
	   
	    this.filmManager.getCartManager().registerCart(this.actionPanel.getCartPanel());
//	    TODO register client in CartManager
	    
	    
	    /* Initialize GUI */
	    this.createGUI();
	       
	    /* Languages */
	    this.guiManager = GUIManager.getInstance();
	    this.guiManager.registerMultilingual(this.welcomePage.getSuggestionsPanel());
	    this.guiManager.registerMultilingual(this.welcomePage.getLoginPanel());
	    this.guiManager.registerMultilingual(this.actionPanel);
	    this.guiManager.registerMultilingual(this.actionPanel.getCartPanel());
	    this.guiManager.registerMultilingual(this.actionPanel.getSearchPage());
	    this.guiManager.registerMultilingual(this.actionPanel.getFilmPage());
	    this.guiManager.registerMultilingual(KeyboardDialog.getInstance());

	    /*Color Themes*/
	    this.guiManager.registerColorTheme(this);
	    this.guiManager.registerColorTheme(topBarPanel);
	    this.guiManager.registerColorTheme(this.welcomePage.getSuggestionsPanel());
	    this.guiManager.registerColorTheme(this.welcomePage.getSuggestionsPanel());
	    this.guiManager.registerColorTheme(this.welcomePage.getLoginPanel());
	    this.guiManager.registerColorTheme(this.actionPanel);
	    this.guiManager.registerColorTheme(this.actionPanel.getCartPanel());
	    this.guiManager.registerColorTheme(this.actionPanel.getSearchPage());
	    this.guiManager.registerColorTheme(this.actionPanel.getFilmPage());
	    this.guiManager.registerColorTheme(KeyboardDialog.getInstance());
	    
	    
	    /* Interaction */
	    this.guiManager.refreshUI();
	    new Interaction(this);    
	    
	    
	    
	    /* Create Film attributes */
	    final String title = "The Matrix";
	    final String synopsis = "Thomas A. Anderson is a man living two lives. "
						+ "By day he is an average computer programmer and by night "
						+ "a hacker known as Neo. Neo has always questioned his reality,"
						+ " but the truth is far beyond his imagination...";
	    final String director_lname = "Wachowski";
	    final String director_fname = "Lana";
	    final ArrayList<String> actors = new ArrayList<>(Arrays.asList("Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss", "Hugo Weaving"));
	    final AgeRestriction restriction = AgeRestriction.EVERYONE;
	    final ArrayList<Categories> categories = new ArrayList<>(Arrays.asList(Categories.ACTION, Categories.DRAMAS));
	    final Year year = Year.of(1999);
	    final ArrayList<Support> supports = new ArrayList<>(Arrays.asList(new BluRay(22.5, StatesBluRay.RENTED), new QRCode(""), new BluRay(22.50, StatesBluRay.AVAILABLE)));
		
	    /* Create an instance of a Film for test Film class */
	    Film film = new Film(title, synopsis, actors, director_fname, director_lname, year, categories, restriction, supports);
		
	    
	    this.filmManager.addFilm(film);
	    this.actionPanel.getSearchPage().addResult(film);
	}
	
	/**
	 * @author MathysC
	 * @return the topBarPanel
	 */
	public TopBarPanel getTopBarPanel() {
	    return topBarPanel;
	}

	/**
	 * @author MathysC
	 * @return the welcomePage
	 */
	public WelcomePage getWelcomePage() {
	    return welcomePage;
	}

	/**
	 * @author MathysC
	 * @return the actionPanel
	 */
	public ActionPanel getActionPanel() {
	    return actionPanel;
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
	
	/**
	 * Start the MainFrame
	 * @author MathysC
	 * 
	 */
	public void start() {
	    this.setVisible(true);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void createGUI() {
	    
	    // Set Icon
	    this.setIconImage(Resources.getIco(ICO_APP));
		
	    // Put the Frame on Full Screen
	    this.setUndecorated(true);
	    this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
	    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	    this.setResizable(false);

	    this.setLayout(new BorderLayout());

	    // Add the Top Bar.
	    this.add(this.topBarPanel, BorderLayout.NORTH);

	    // Add a banner.
	    banner = new JLabel(Resources.getImg(IMG_BANNER_DESERT));
	    banner.setOpaque(false);
	    this.add(banner, BorderLayout.WEST);

	    // Add the welcome page.
	    this.add(welcomePage, BorderLayout.CENTER);
	    this.currentPage = ID_WELCOME_PAGE;

	    this.pack();    
	}
	
	@Override
	public void setColorTheme(ColorThemes colorTheme) {
	    switch(colorTheme) {
	    case LIGHT_THEME:
		// This JFrame
		this.setBackground(Light.BG.getColor());

		// Banner
		banner.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Light.BG.getColor()));

		break;
	    case DARK_THEME:
		// This JFrame
		this.setBackground(Dark.BG.getColor());
		// Banner
		banner.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Dark.BG.getColor()));

		break;
	    default:
		break;
	    }
	    
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

}