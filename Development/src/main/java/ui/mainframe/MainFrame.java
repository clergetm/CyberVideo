package ui.mainframe;

import ui.utils.Decorations;
import ui.utils.bundles.Multilingual;
import ui.utils.colors.ColorTheme;
import ui.utils.colors.Dark;
import ui.utils.colors.Light;
import ui.pages.FilmPage;
import ui.pages.actions.ActionPanel;
import ui.pages.welcome.WelcomePage;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import fc.al2000.AL2000;

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


	    this.createGUI();
	    this.setLanguage(this.getRbEN());
	    this.setLight();

	    /*Initialize pages map*/
	    pages = new HashMap<>();
	    this.pages.put(ID_WELCOME_PAGE, welcomePage);
	    this.pages.put(ID_RESULT_PAGE, actionPanel);
	}
	
	/**
	 * Create and set all GUI Components.
	 * @author MathysC
	 *
	 */
	private void createGUI() {
	    
	    /*Initialize Components */
	    topBarPanel = new TopBarPanel();
	    banner = new JLabel();
	    welcomePage = new WelcomePage();
	    actionPanel = new ActionPanel();
	    
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
	    new Interaction(this);
	    
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