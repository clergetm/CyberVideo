package ui.MainFrame;

import ui.Decorations;
import ui.Bundles.Multilingual;
import ui.Colors.ColorTheme;
import ui.Colors.Dark;
import ui.Colors.Light;
import ui.Pages.Welcome.WelcomePage;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import fc.AL2000.AL2000;
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
	public static final String IMG_BANNER_IT = "BannerIT", 
			IMG_BANNER_POLICE = "BannerPOLICE",
			IMG_BANNER_DESERT = "BannerDESERT";
	
	/* Components */
    protected TopBarPanel topBarPanel = new TopBarPanel();
    protected JLabel banner = new JLabel();
    protected WelcomePage welcomePage = new WelcomePage();

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
        
        // Set options.
        this.setIconImage(Decorations.getIco(ICO_APP));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        new Interaction(this);
        
        // Add the Top Bar.
        this.add(this.topBarPanel, BorderLayout.NORTH);

        // Add a banner.
        banner = new JLabel(Decorations.getImg(IMG_BANNER_DESERT));
        this.add(banner, BorderLayout.WEST);

        // Add the welcome page.
        this.add(welcomePage, BorderLayout.CENTER);
        this.pack();
        

        this.setLanguage(this.getRbEN());
        this.setLight();

        // Maximize the Window.
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
    }

    /**
     * Getter of topBar
     * @author MathysC
     *
     * @return TopBarPanel
     */
    public TopBarPanel topBar() { return this.topBarPanel; }
    
    /**
     * Getter of welcomePage
     * @author MathysC
     *
     * @return WelcomePage
     */
    public WelcomePage welcome() { return this.welcomePage; }
        
    /**
     * Change all Main and Main’s Child text.
     * @author MathysC
     * @param rb the choosen language bundle.
     */
    @Override
    public void setLanguage(ResourceBundle rb) {
        // Set all multilingual child.
        this.welcomePage.setLanguage(rb);
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
                new MainFrame().setVisible(true);
            }
        });
    }
}