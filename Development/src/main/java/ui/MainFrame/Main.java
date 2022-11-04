package ui.MainFrame;

import ui.Decorations;
import ui.Bundles.Multilingual;
import ui.Pages.Welcome.WelcomePage;

import java.awt.BorderLayout;

import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * @author MathysC
 * Main Class of AL2000. Initialize the machine Interface.
 */
@SuppressWarnings("serial")
public class Main extends JFrame implements Multilingual {

	/* Icon */
	public static final String ICO_APP = "App";

    /* Components */
    protected TopBarPanel topBarPanel = new TopBarPanel();
    protected JLabel banner = new JLabel();
    protected WelcomePage welcomePage = new WelcomePage();

    /**
     * Constructor of Main.
     * Initialize the AL200 Main Window.
     * @author MathysC
     *
     */
    public Main() {
        super("AL2000");
        this.setIconImage(Decorations.getIco(ICO_APP));
        new Interaction(this);
        // Set options.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Add the Top Bar.
        this.add(this.topBarPanel, BorderLayout.NORTH);

        // Add a banner.
        banner = new JLabel(Decorations.IMG_BANNERDESERT.getImg());
        banner.setBorder(Decorations.BORDER_DEFAULT.getBorder());
        this.add(banner, BorderLayout.WEST);

        // Add the welcome page.
        this.add(welcomePage, BorderLayout.CENTER);
        this.setLanguage(this.getRbEN());
        this.pack();

        // Maximize the Window.
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Change all Main and Main’s Child text.
     * @author MathysC
     * @param rb the choosen language bundle.
     */
    @Override
    public void setLanguage(ResourceBundle rb) {
        // Set MainFrame Components text.

        // Set all multilingual child.
        this.welcomePage.setLanguage(rb);
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
                new Main().setVisible(true);
            }
        });
    }
}