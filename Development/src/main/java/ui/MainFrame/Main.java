package ui.MainFrame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author MathysC
 * Main Class of AL2000. Initialize the machine Interface.
 */
@SuppressWarnings("serial")
public class Main extends JFrame {

	/**
	 * Constructor of Main.
	 * Initialize the AL200 Main Window.
	 * @author MathysC
	 *
	 */
	public Main(){
		super("AL2000");
		// Set options.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Add a Top Bar.
        this.add(new TopBar(),BorderLayout.NORTH);
        

        this.pack();
        
        // Maximize the Window.
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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
