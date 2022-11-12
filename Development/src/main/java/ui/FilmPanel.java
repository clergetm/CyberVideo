package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

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
    private JPanel mainPanel = new JPanel(new BorderLayout()), panelforPoster = new JPanel(new FlowLayout());
    protected JPanel availability = new JPanel(new FlowLayout());
    protected JButton qrcode = new JButton(), bluray = new JButton();

    /* Actions */
    public static final String RENTBR = "RENT_BLU-RAY";
    public static final String RENTQR = "RENT_QR-CODE";

	/* Options */
    private int witdhPanel = 200, heightPanel = 200,
    		witdhButton = 85, heightButton = 25,
    		witdhPoster = 100, heightPoster = 150;
    
    /* FC */
    private ImageIcon posterImg = Decorations.getImg("Test_Film"); // TODO
    private Film film;
    
    /**
     * Constructor of {@code FilmPanel} 
     * Set JPanel options and add Components.
     * @author MathysC
     */
    public FilmPanel(Film f, double percent) {
    	// Film
    	this.film = f;
    	
        // JPanel Options
        this.setLayout(new FlowLayout());
    
        // Set Poster.
        // TODO: Add action from click on the poster to the Information of the Film
    	panelforPoster.add(poster);
    	mainPanel.add(panelforPoster, BorderLayout.CENTER);
        poster.setIcon(posterImg);


        // Set Buttons.
        qrcode = new JButton("QRCode");
        qrcode.setActionCommand(RENTQR);
        availability.add(qrcode);

        bluray = new JButton("BluRay");
        bluray.setActionCommand(RENTBR);
        availability.add(bluray);

        mainPanel.add(availability, BorderLayout.SOUTH);
            
        this.add(mainPanel);
        this.setPanelScale(percent);
        this.update();
        
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

    
    /**
     * 
     * @author MathysC
     *
     * @param percent the percent to scale the panel to.
     * @requires percent must be positive or will be reset to 100.
     */
    public void setPanelScale(double percent) {
    	percent = (percent < 0) ? 100 : percent;
    	// Change size of main Panel.
        mainPanel.setPreferredSize(
        		new Dimension(
        				(int)(percent * witdhPanel / 100),
        				(int)(percent * heightPanel / 100)));
        
        // Change size of QR Code button.
        qrcode.setPreferredSize(
        		new Dimension(
        				(int)(percent * witdhButton / 100),
        				(int)(percent * heightButton / 100)));
        
        // Change size of Blu Ray button.
        bluray.setPreferredSize(
        		new Dimension(
        				(int)(percent * witdhButton / 100),
        				(int)(percent * heightButton / 100)));
        
        // Change size of Poster
        poster.setIcon(
        		new ImageIcon(
        				posterImg.getImage().getScaledInstance(
        						(int)(percent * witdhPoster / 100),
        						(int)(percent * heightPoster / 100), 
        						java.awt.Image.SCALE_SMOOTH)));
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