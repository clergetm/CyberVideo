package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fc.Films.Film;
import ui.Colors.ColorTheme;
import ui.Colors.Dark;
import ui.Colors.Light;

/**
 * Graphic Implementation of a Film.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class FilmPanel extends JPanel implements ColorTheme {

    /* Components */
    protected JLabel poster = new JLabel();
    private JPanel panelforPoster = new JPanel(new FlowLayout());
    protected JPanel availability = new JPanel(new FlowLayout());
    protected JButton qrcode = new JButton(), bluray = new JButton();

    /* Actions */
    public static final String RENTBR = "RENT_BLU-RAY";
    public static final String RENTQR = "RENT_QR-CODE";

    /* FC */
    private String IMG_FILM; // TODO
    private Film film;
    
    /**
     * Constructor of {@code FilmPanel} 
     * Set JPanel options and add Components.
     * @author MathysC
     */
    public FilmPanel(Film f) {
    	// Film
    	this.film = f;
    	
        // JPanel Options
        this.setLayout(new BorderLayout());

        // Set Poster.
    	panelforPoster.add(poster);
        this.add(panelforPoster, BorderLayout.CENTER);

        IMG_FILM = "Test_Film";// TODO 
        this.setPosterLabel(Decorations.getImg(IMG_FILM)); // TODO

        // Set Buttons.
        qrcode = new JButton("QRCode");
        qrcode.setActionCommand(RENTQR);
        availability.add(qrcode);

        bluray = new JButton("BluRay");
        bluray.setActionCommand(RENTBR);
        availability.add(bluray);

        this.add(availability, BorderLayout.SOUTH);
    }

    /**
     * TODO: Add action from click on the poster to the Information of the Film
     * @author MathysC
     * @param img The image of the film to set.
     */
    private void setPosterLabel(ImageIcon img) {
        poster.setIcon(img);
    }

    /**
     * @author MathysC
     * @param availability The BluRay availability to set
     */
    public void setBluRayAvailable(boolean availability) {
        this.bluray.setEnabled(availability);
    }
   
    /**
     * @author MathysC
     * @param availability The QR Code availability to set
     */
    public void setQRCodeAvailable(boolean availability) {
        this.qrcode.setEnabled(availability);
    }
    
    /**
     * TODO: Update Poster
     * Update Panel with {@code this.film} data.
     * @author MathysC
     */
    public void update() {
       this.setQRCodeAvailable(this.film.isQRAvailable());
       this.setBluRayAvailable(this.film.isBRAvailable());
    }

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