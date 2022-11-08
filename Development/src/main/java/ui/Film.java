package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.Colors.ColorTheme;
import ui.Colors.Dark;
import ui.Colors.Light;

/**
 * Graphic Implementation of a Film.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class Film extends JPanel implements ColorTheme{

    /* Components */
    protected JLabel poster = new JLabel();
    private JPanel panelforPoster = new JPanel(new FlowLayout());
    protected JPanel availability = new JPanel(new FlowLayout());
    protected JButton qrcode = new JButton(), bluray = new JButton();

    /* Actions */
    public static final String RENTBR = "RENT_BLU-RAY";
    public static final String RENTQR = "RENT_QR-CODE";

    
    private String IMG_FILM; 
    /**
     * Constructor of {@code ui.Film} 
     * Set JPanel options and add Components.
     * @author MathysC
     *
     */
    public Film() {
    	IMG_FILM = "Test_Film";// TODO 
    	
        // JPanel Options
        this.setLayout(new BorderLayout());

        // Set Poster.
        this.setPosterLabel(); // TODO

        // Set Buttons.
        this.setAvailabilityPanel(); // TODO
        
        
    }

    /**
     * TODO: Find a way to implement the right image for the poster.
     * TODO: Add action from click on the poster to the Information of the Film
     * Set the icon of the poster Label.
     * @author MathysC
     *
     */
    private void setPosterLabel() {

    	panelforPoster.add(poster);
        poster.setIcon(Decorations.getImg(IMG_FILM));

        this.add(panelforPoster, BorderLayout.CENTER);

    }

    /**
     * TODO
     * Set actions of buttons
     * @author MathysC
     *
     */
    private void setAvailabilityPanel() {
        qrcode = new JButton("QRCode");
        qrcode.setActionCommand(RENTQR);
        availability.add(qrcode);

        bluray = new JButton("BluRay");
        bluray.setActionCommand(RENTBR);
        availability.add(bluray);

        this.add(availability, BorderLayout.SOUTH);

    }

    /**
     * Set the BluRay Available.
     * @author MathysC
     *
     */
    public void setBluRayAvailable() {
        this.bluray.setEnabled(true);
    }

    /**
     * Set the BluRay Unavailable.
     * @author MathysC
     *
     */
    public void setBluRayUnavailable() {
        this.bluray.setEnabled(false);
    }

    /**
     * Set the QR Code Available.
     * @author MathysC
     *
     */
    public void setQRCodeAvailable() {
        this.qrcode.setEnabled(true);
    }

    /**
     * Set the QR Code Unavailable.
     * @author MathysC
     *
     */
    public void setQRCodeUnavailable() {
        this.qrcode.setEnabled(false);
    }

    /**
     * TODO: update the {@code ui.Film} with data from {@code fc.Film}
     * @author MathysC
     *
     */
    public void update() {
        /* TODO */ }

	@Override
	public void setLight() {
		// Poster
		this.panelforPoster.setBackground(Light.BG.getColor());
		
		// Buttons Panel
		this.availability.setBackground(Light.BG.getColor());
		
		// QR Code Button
		this.qrcode.setBackground(Light.BLUE.getColor());
		this.qrcode.setForeground(Light.WHITE.getColor());
		this.qrcode.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));
		
		// Blu Ray Button
		this.bluray.setBackground(Light.BLUE.getColor());
		this.bluray.setForeground(Light.WHITE.getColor());
		this.bluray.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));
	}

	@Override
	public void setDark() {
		// Poster
		this.panelforPoster.setBackground(Dark.BG.getColor());
		
		// Buttons Panel
		this.availability.setBackground(Dark.BG.getColor());		
		
		// QR Code Button
		this.qrcode.setBackground(Dark.BLUE.getColor());
		this.qrcode.setForeground(Dark.FOREGROUNG.getColor());
		this.qrcode.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));
		
		// Blu Ray Button
		this.bluray.setBackground(Dark.BLUE.getColor());
		this.bluray.setForeground(Dark.FOREGROUNG.getColor());
		this.bluray.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));	
	}
}