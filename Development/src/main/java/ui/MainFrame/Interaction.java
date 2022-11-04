package ui.MainFrame;

import ui.Decorations;

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

    private Main mainFrame;
    
    /* Sounds */
    private final String PREFIX_SOUND = "resources/sounds/", FORMAT_SOUND = ".wav",
        BELL = PREFIX_SOUND + "Bell" + FORMAT_SOUND,
        DING = PREFIX_SOUND + "Ding" + FORMAT_SOUND,
        STORERING = PREFIX_SOUND + "StoreRing" + FORMAT_SOUND;
    /**
     * Constructor of Interaction.
     * @author MathysC
     *
     * @param m The main Frame of the machine.
     */
    public Interaction(Main m) {
        this.mainFrame = m;

        /* Listeners */
        mainFrame.topBarPanel.languageSwitch.addActionListener(this);
        mainFrame.topBarPanel.askForHelp.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            // Handle Language Switch Button from the TopBarPanel.
        case TopBarPanel.SWITCHFR:
            mainFrame.setLanguage(mainFrame.getRbFR());
            mainFrame.topBarPanel.languageSwitch.setIcon(Decorations.getImg(TopBarPanel.IMG_FR));
            mainFrame.topBarPanel.languageSwitch.setActionCommand(TopBarPanel.SWITCHEN);
            break;
        case TopBarPanel.SWITCHEN:
            mainFrame.setLanguage(mainFrame.getRbEN());
            mainFrame.topBarPanel.languageSwitch.setIcon(Decorations.getImg(TopBarPanel.IMG_EN));
            mainFrame.topBarPanel.languageSwitch.setActionCommand(TopBarPanel.SWITCHFR);
            break;
            
            // Handle askForHelp button from the TopBarPanel.
        case TopBarPanel.HELP:
            playSound(STORERING);
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
        new Thread(new Runnable() {

            @Override
            public void run() {
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

            }
        }).start();
    }

}