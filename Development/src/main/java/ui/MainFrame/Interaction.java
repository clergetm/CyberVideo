package ui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interaction class that implements all listener of the machine.
 * @author MathysC
 *
 */

/*
 *				+-------------+
 *				| Interaction |
 *				+-------------+
 *					   |
 *				       |
 *				+-------------+
 *				| 	 Main	  |
 *				+-------------+
 *					   |
 *					   |
 *		+---------------------------+
 *		| All others sub Components |
 * 		+---------------------------+ 
 */
public class Interaction implements ActionListener {

    private Main mainFrame;

    /**
     * Constructor of Interaction.
     * @author MathysC
     *
     * @param m The main Frame of the machine.
     */
    public Interaction(Main m) {
        this.mainFrame = m;

        /*Listeners*/
        mainFrame.topBar.languageSwitch.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            // Handle Language Switch Button from the TopBar.
        case Main.SWITCHFR:
            mainFrame.setLanguage(mainFrame.getRbFR());
            mainFrame.topBar.languageSwitch.setActionCommand(Main.SWITCHEN);
            break;
        case Main.SWITCHEN:
            mainFrame.setLanguage(mainFrame.getRbEN());
            mainFrame.topBar.languageSwitch.setActionCommand(Main.SWITCHFR);
            break;
        default:
            throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
        }
    }

}