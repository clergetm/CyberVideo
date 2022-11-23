package ui.pages.cart;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fc.films.Film;
import ui.utils.Decorations;
import ui.utils.observer.colortheme.ColorThemes;
import ui.utils.observer.colortheme.IColorThemeObserver;
import ui.utils.observer.colortheme.palettes.Dark;
import ui.utils.observer.colortheme.palettes.Light;
import ui.utils.observer.multilingual.IMultilingualObserver;

/**
 * Class that represents a film in the Cart. 
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class CartItemPanel extends JPanel implements IMultilingualObserver, IColorThemeObserver {

    private JLabel poster = new JLabel(); // Poster on the left.
    private JPanel cartOptions = new JPanel(); // Options on the right
    private JLabel titleLabel = new JLabel(); // Title on the top right.
    private JPanel bottomPanel = new JPanel(); // Buttons on the bottom right.
    private JLabel supportLabel = new JLabel();
    private JButton moreButton = new JButton();
    private JButton removeButton = new JButton();

    /**
     * Constructor of {@code CartItemPanel}. Create the GUI
     * @author MathysC
     *
     * @param film the checked film.
     * @param supportType How the film is wanted. 
     */
    public CartItemPanel(Film film, String supportType) {
        this.setLayout(new GridLayout(0,2));
        this.setBorder(Decorations.getDefaultBorder());
        cartOptions.setLayout(new BorderLayout());
        
        /* Poster */
        // poster.setIcon(film.getIcon()); // TODO #8 getter of the film Icon
        poster.setIcon(Decorations.getImg(Decorations.IMG_FILM.toString()));
        this.add(poster);
        
        /* Cart Options */
        // Same size as the poster.
        cartOptions.setPreferredSize(poster.getPreferredSize());        
        
        titleLabel.setText(film.getTitle());
        cartOptions.add(titleLabel,BorderLayout.NORTH);
        
        bottomPanel.setLayout(new GridLayout(3,0));
        supportLabel.setText(supportType);
        bottomPanel.add(supportLabel);
        
        moreButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));
        bottomPanel.add(moreButton);
        bottomPanel.add(removeButton);
        
        cartOptions.add(bottomPanel, BorderLayout.SOUTH);
        this.add(cartOptions);
    }

    @Override
    public void setLanguage(ResourceBundle rb) {
        this.moreButton.setText(rb.getString("checkout_info"));
        this.removeButton.setText(rb.getString("checkout_remove"));
    }

    @Override
    public void setColorTheme(ColorThemes colorTheme) {
	switch(colorTheme) {
	    case LIGHTTHEME:
		// This JPanel
	        this.setBackground(this.getParent().getBackground());

	        // poster
	        this.poster.setBackground(this.poster.getParent().getBackground());

	        // Options
	        this.cartOptions.setBackground(this.cartOptions.getParent().getBackground());

	        // Film title
	        this.titleLabel.setForeground(Light.BLACK.getColor());
	        
	        // Bottom Panel
	        this.bottomPanel.setBackground(this.bottomPanel.getParent().getBackground());
	        
	        // Rent type
	        this.supportLabel.setForeground(Light.BLACK.getColor());

	        // Information Button
	        this.moreButton.setBackground(Light.BLUE.getColor());
	        this.moreButton.setForeground(Light.WHITE.getColor());

	        // Remove Button
	        this.removeButton.setBackground(Light.BLUE.getColor());
	        this.removeButton.setForeground(Light.WHITE.getColor());
		break;
	    case DARKTHEME:
		// This JPanel
	        this.setBackground(this.getParent().getBackground());

	        // poster Label
	        this.poster.setBackground(this.poster.getParent().getBackground());

	        // Options JPanel
	        this.cartOptions.setBackground(this.cartOptions.getParent().getBackground());

	        // Film title Label
	        this.titleLabel.setForeground(Dark.FOREGROUND.getColor());

	        // Bottom Panel
	        this.bottomPanel.setBackground(this.bottomPanel.getParent().getBackground());
	        // Rent type Label
	        this.supportLabel.setForeground(Dark.FOREGROUND.getColor());

	        // Information Button
	        this.moreButton.setBackground(Dark.BLUE.getColor());
	        this.moreButton.setForeground(Dark.FOREGROUND.getColor());

	        // Remove Button
	        this.removeButton.setBackground(Dark.BLUE.getColor());
	        this.removeButton.setForeground(Dark.FOREGROUND.getColor());
		break;
	    default:
		break;
	    }
	
    }

}