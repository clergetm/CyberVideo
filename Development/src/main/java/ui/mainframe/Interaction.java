package ui.mainframe;

import ui.managers.GUIManager;
import ui.utils.Decorations;
import ui.utils.observer.colortheme.ColorThemes;
import ui.utils.observer.multilingual.Languages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

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

    private MainFrame mainFrame;
    /* Sounds */
    public static final String SND_DING = "Ding";
    public static final String SND_BELL = "Bell";
    public static final String SND_STORERING = "StoreRing";

    /**
     * Constructor of Interaction.
     * @author MathysC
     *
     * @param m The main Frame of the machine.
     */
    public Interaction(MainFrame m) {
        this.mainFrame = m;

        /* Listeners */
        mainFrame.getTopBarPanel().getLanguageSwitch().addActionListener(this);
        mainFrame.getTopBarPanel().getAskForHelpButton().addActionListener(this);
        mainFrame.getTopBarPanel().getColorSwitch().addActionListener(this);
        mainFrame.getTopBarPanel().getSearchButton().addActionListener(this);
        mainFrame.getActionPanel().getUndoButton().addActionListener(this);
        mainFrame.getActionPanel().getRedoButton().addActionListener(this);
        mainFrame.getActionPanel().getConnectionButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            // Handle Search Button from the TopBarPanel.
        case TopBarPanel.ACTION_SEARCH:
            // Go to Search Page
            mainFrame.changeCurrentPage(MainFrame.ID_RESULT_PAGE);
            mainFrame.getTopBarPanel().getSearchButton().setActionCommand(TopBarPanel.ACTION_WELCOME);
            break;

        case TopBarPanel.ACTION_WELCOME:
            // Go to Welcome Page
            mainFrame.changeCurrentPage(MainFrame.ID_WELCOME_PAGE);
            mainFrame.getTopBarPanel().getSearchButton().setActionCommand(TopBarPanel.ACTION_SEARCH);
            break;
            
            // Handle Language Switch Button from the TopBarPanel.
        case TopBarPanel.ACTION_EN:
            // Change from English to French
            GUIManager.getInstance().notifyMultilingualObservers(Languages.FRENCH);
            mainFrame.getTopBarPanel().getLanguageSwitch().setIcon(Decorations.getImg(TopBarPanel.IMG_FR));
            mainFrame.getTopBarPanel().getLanguageSwitch().setActionCommand(TopBarPanel.ACTION_FR);
            break;
            
        case TopBarPanel.ACTION_FR:
            // Change from French to English
            GUIManager.getInstance().notifyMultilingualObservers(Languages.ENGLISH);
            mainFrame.getTopBarPanel().getLanguageSwitch().setIcon(Decorations.getImg(TopBarPanel.IMG_EN));
            mainFrame.getTopBarPanel().getLanguageSwitch().setActionCommand(TopBarPanel.ACTION_EN);
            break;

            // Handle askForHelp button from the TopBarPanel.
        case TopBarPanel.ACTION_HELP:
            playSound(Decorations.getSndPath(SND_STORERING));
            break;

            // Handle Color Switch Button from the TopBarPanel.
        case TopBarPanel.ACTION_LIGHT:
            // Change from Light to Dark theme.
            GUIManager.getInstance().notifyColorThemeObservers(ColorThemes.DARKTHEME);
            break;
            
        case TopBarPanel.ACTION_DARK:
            // Change from Dark to Light theme.
            GUIManager.getInstance().notifyColorThemeObservers(ColorThemes.LIGHTTHEME);
            break;
        default:
            throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
        }
    }

    /**
     * Play a sound in a thread.
     * @author MathysC
     *
     * @param path The path from {@code Development/}  to a sound in a .wav format only.
     * 
     * @see https://stackoverflow.com/questions/23255162/looping-audio-on-separate-thread-in-java
     * @see https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
     */
    private void playSound(String path) {
        // Create a thread and immediately start it.
        new Thread(() -> {
            try {
                // Get audio file and format.
                AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path));
                AudioFormat audioFormat = audio.getFormat();
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
                // Play the sound until it’s done.
                SourceDataLine sourceDataline = (SourceDataLine) AudioSystem.getLine(info);
                sourceDataline.open(audioFormat);
                sourceDataline.start();
                int nBytesRead = 0;
                byte[] abData = new byte[128000];
                while (nBytesRead != -1) {
                    nBytesRead = audio.read(abData, 0, abData.length);
                    if (nBytesRead >= 0)
                        sourceDataline.write(abData, 0, nBytesRead);
                }
                // Stop the sound when it’s done.
                sourceDataline.drain();
                sourceDataline.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

}