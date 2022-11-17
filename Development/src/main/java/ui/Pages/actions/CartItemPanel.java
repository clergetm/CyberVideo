package ui.Pages.actions;

import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fc.Films.Film;
import ui.Decorations;
import ui.Bundles.Multilingual;
import ui.Colors.ColorTheme;
import ui.Colors.Dark;
import ui.Colors.Light;

@SuppressWarnings("serial")
public class CartItemPanel extends JPanel implements Multilingual, ColorTheme {

    protected JLabel poster = new JLabel();
    protected JPanel filmOptions = new JPanel();
    protected JLabel filmTitle = new JLabel();
    protected JLabel rentType = new JLabel();
    protected JButton informationButton = new JButton();
    protected JButton removeButton = new JButton();

    public CartItemPanel(Film film, String rent) {
        this.setLayout(new FlowLayout());
        filmOptions.setLayout(new BoxLayout(filmOptions, BoxLayout.Y_AXIS));

        /* Poster */
        // poster.setIcon(film.getIcon()); // TODO #8 getter of the film Icon
        poster.setIcon(Decorations.getImg(Decorations.IMG_FILM.toString()));
        this.add(poster);

        /*Options */
        // title
        filmTitle.setText(film.getTitle());
        filmOptions.add(filmTitle);

        // rent type
        rentType.setText(rent);
        filmOptions.add(rentType);

        // information Button
        informationButton.setText("Information");
        filmOptions.add(informationButton);

        // remove Button
        removeButton.setText("remove");
        filmOptions.add(removeButton);

        this.add(filmOptions);
    }

    @Override
    public void setLight() {
	// This JPanel
        this.setBackground(this.getParent().getBackground());

        // poster
        this.poster.setBackground(this.poster.getParent().getBackground());

        // Options
        this.filmOptions.setBackground(this.filmOptions.getParent().getBackground());

        // Film title
        this.filmTitle.setForeground(Light.BLACK.getColor());

        // Rent type
        this.rentType.setForeground(Light.BLACK.getColor());

        // Information Button
        this.informationButton.setBackground(Light.BLUE.getColor());
        this.informationButton.setForeground(Light.WHITE.getColor());
        this.informationButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));

        // Remove Button
        this.removeButton.setBackground(Light.BLUE.getColor());
        this.removeButton.setForeground(Light.WHITE.getColor());
        this.removeButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));
    }

    @Override
    public void setDark() {
        // This JPanel
        this.setBackground(this.getParent().getBackground());

        // poster Label
        this.poster.setBackground(this.poster.getParent().getBackground());

        // Options JPanel
        this.filmOptions.setBackground(this.filmOptions.getParent().getBackground());

        // Film title Label
        this.filmTitle.setForeground(Dark.FOREGROUND.getColor());

        // Rent type Label
        this.rentType.setForeground(Dark.FOREGROUND.getColor());

        // Information Button
        this.informationButton.setBackground(Dark.BLUE.getColor());
        this.informationButton.setForeground(Dark.FOREGROUND.getColor());
        this.informationButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));

        // Remove Button
        this.removeButton.setBackground(Dark.BLUE.getColor());
        this.removeButton.setForeground(Dark.FOREGROUND.getColor());
        this.removeButton.setFont(Decorations.FONT_BASIC.getFont(Font.BOLD, 12));
    }

    @Override
    public void setLanguage(ResourceBundle rb) {
        this.informationButton.setText(rb.getString("checkout_info"));
        this.removeButton.setText(rb.getString("checkout_remove"));
    }

}