package ui.MainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * The Top bar always appears on the Main Frame. 
 * With a logo and the buttons that allows the client to change 
 * the theme (light/dark) and the language (en\fr).
 * 
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class TopBar extends JPanel {

	/**
	 * Constructor of TopBar.
	 * Set JPanel options and add components.
	 * @author MathysC
	 *
	 */
	public TopBar() {
		// Set JPanel’s options.
		setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.BLACK));
		
		// Logo.
		JLabel logo = new JLabel("LOGO");
		logo.setPreferredSize(Decorations.DIM_LOGO.getDimension());
		logo.setIcon(Decorations.ICON_LOGO.getIcon());
		logo.setBorder(Decorations.BORDER_DEFAULT.getBorder());
		this.add(logo,BorderLayout.WEST);

		// Buttons’ JPanel.
		JPanel options = new JPanel();
		options.setLayout(new FlowLayout(FlowLayout.TRAILING));
		options.setBorder(Decorations.BORDER_DEFAULT.getBorder());
		this.add(options,BorderLayout.EAST);

		// color switch button.
		JButton colorTheme = new JButton();
		colorTheme.setPreferredSize(Decorations.DIM_BUTTON.getDimension());
		colorTheme.setIcon(Decorations.ICON_BUTTON.getIcon());
		options.add(colorTheme);
		
		// language switch button.
		JButton language = new JButton();
		language.setPreferredSize(Decorations.DIM_BUTTON.getDimension());
		language.setIcon(Decorations.ICON_BUTTON.getIcon());
		options.add(language);
		
		
	}

}
