package ui.MainFrame;

import ui.Decorations;
import ui.Bundles.Multilingual;
import ui.Colors.ColorTheme;
import ui.Colors.Dark;
import ui.Colors.Light;
import ui.Pages.ActionPanel;
import ui.Pages.FilmPage;
import ui.Pages.Welcome.WelcomePage;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import fc.AL2000;
import fc.Films.Film;

/**
 * @author MathysC
 * Main Class of AL2000. Initialize the machine Interface.
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Multilingual, ColorTheme {

	/* Icon */
	public static final String ICO_APP = "App";

	/* Banners */
	public static final String IMG_BANNER_IT = "BannerIT";
	public static final String IMG_BANNER_POLICE = "BannerPOLICE";
	public static final String IMG_BANNER_DESERT = "BannerDESERT";
	
	/* Components */
	protected TopBarPanel topBarPanel = new TopBarPanel();
	protected JLabel banner = new JLabel();
	protected WelcomePage welcomePage = new WelcomePage();
	protected ActionPanel actionPanel = new ActionPanel();
	protected FilmPage filmPage = new FilmPage();
	
	/* Pages */
	protected HashMap<Integer, JPanel> pages = new HashMap<>();
	public static final int ID_WELCOME_PAGE = 0;
	public static final int ID_RESULT_PAGE = 1;
	public static final int ID_FILM_PAGE = 2;
	private int current_page;
	
	/* FC */
	private AL2000 fc;
	
	/**
	 * Constructor of Main.
	 * Initialize the AL2000 Functional Core.
	 * Initialize the AL2000 Main Window.
	 * @author MathysC
	 *
	 */
	public MainFrame() {
		super("AL2000");

		// Initialize FC before the GUI (atleast this.setLanguage and this.setLight functions)
		fc = new AL2000();
		// TODO #38 Change TestHydrate function
		for(int i = 0; i <1; i++)
			for(Film f : this.fc.TESTHYDRATE())
				this.welcomePage.suggestions().getFilmManager().addFilm(f);
		
		// Set Icon
		this.setIconImage(Decorations.getIco(ICO_APP));
		
		// Put the Frame on Full Screen
		this.setUndecorated(true);
		this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setResizable(false);
		
		this.setLayout(new BorderLayout());
		new Interaction(this);
		
		// Add the Top Bar.
		this.add(this.topBarPanel, BorderLayout.NORTH);

		// Add a banner.
		banner = new JLabel(Decorations.getImg(IMG_BANNER_DESERT));
		this.add(banner, BorderLayout.WEST);

		// Add the welcome page.
		this.add(welcomePage, BorderLayout.CENTER);
		this.current_page = ID_WELCOME_PAGE;
		

		this.setLanguage(this.getRbEN());
		this.setLight();

		this.pack();

		/*Initialize pages map*/
		this.pages.put(ID_WELCOME_PAGE, welcomePage);
		this.pages.put(ID_RESULT_PAGE, actionPanel);
		this.pages.put(ID_FILM_PAGE,filmPage);
	}
	
	public void changeCurrentPage(int id) {
		

		this.remove(this.pages.get(current_page));
		this.revalidate();
		this.repaint();
		
		this.add(this.pages.get(id), BorderLayout.CENTER);
		this.current_page = id;

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
		banner.setBackground(Light.BG.getColor());
		banner.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Light.BG.getColor()));
		
		// Top Bar
		this.topBarPanel.setLight();
		
		// Welcome Page
		this.welcomePage.setLight();
		
		// Action Panel
		this.actionPanel.setLight();

		this.filmPage.setLight();
	}

	@Override
	public void setDark() {
		// This JFrame
		this.setBackground(Dark.BG.getColor());
		
		// Banner
		banner.setBackground(Dark.BG.getColor());
		banner.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Dark.BG.getColor()));
		
		// Top Bar
		this.topBarPanel.setDark();
		
		// Welcome Page
		this.welcomePage.setDark();
		
		// Action Panel
		this.actionPanel.setDark();

		this.filmPage.setDark();
	}
	
	/**
	 * Change all Main and Main’s Child text.
	 * @author MathysC
	 * @param rb the choosen language bundle.
	 */
	@Override
	public void setLanguage(ResourceBundle rb) {
		// Set all multilingual child.
		this.welcomePage.setLanguage(rb);
		
		// Action Panel
		this.actionPanel.setLanguage(rb);
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
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().start();
			}
		});
	}
}