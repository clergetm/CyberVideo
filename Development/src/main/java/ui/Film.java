package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Graphic Implementation of a Film.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class Film extends JPanel {

    /* Components */
    protected JLabel poster = new JLabel();
    protected JPanel availability = new JPanel(new FlowLayout());
    protected JButton qrcode = new JButton(), bluray = new JButton();

    /* Actions */
    public static final String RENTBR = "RENT_BLU-RAY";
    public static final String RENTQR = "RENT_QR-CODE";

    /**
     * Constructor of {@code ui.Film} 
     * Set JPanel options and add Components.
     * @author MathysC
     *
     */
    public Film() {
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

        JPanel tempforPoster = new JPanel(new FlowLayout());
        tempforPoster.add(poster);
        poster.setIcon(Decorations.IMG_FILM.getImg());

        this.add(tempforPoster, BorderLayout.CENTER);

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
}