package ui.pages;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ResourceBundle;
import java.awt.Component;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fc.films.Film;
import ui.utils.factory.filmpanel.factories.FilmRentPanel;
import ui.utils.observer.colortheme.ColorThemes;
import ui.utils.observer.colortheme.IColorThemeObserver;
import ui.utils.observer.colortheme.palettes.Dark;
import ui.utils.observer.colortheme.palettes.Light;
import ui.utils.observer.multilingual.IMultilingualObserver;

public class PageProfil extends JPanel implements IMultilingualObserver, IColorThemeObserver {
    private JPanel returnButtonContainer = new JPanel();
    private JPanel profilPictureContainer = new JPanel();
    private JPanel profilInfoContainer = new JPanel();
    private JPanel profilInfo = new JPanel();
    private JLabel photoProfil = new JLabel();

    public PageProfil(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.RED);
        this.returnButtonContainer.setMaximumSize(new Dimension(Short.MAX_VALUE,100));
        this.returnButtonContainer.setPreferredSize(new Dimension(Short.MAX_VALUE,100));
        this.returnButtonContainer.setBackground(Color.CYAN);
        this.returnButtonContainer.add(new JLabel("LALALALALLALALALALALALAL"));

        this.profilInfoContainer.setLayout(new GridLayout(0,1));
        this.profilInfoContainer.setPreferredSize(new Dimension(500,500));
        this.profilInfoContainer.setMaximumSize(new Dimension(500,500));
        this.profilInfoContainer.setBackground(Color.BLACK);
        this.add(returnButtonContainer);
        this.add(profilPictureContainer);
        this.add(profilInfoContainer);
        this.add(profilInfo);
    }

    @Override
    public void setColorTheme(ColorThemes colorTheme) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setLanguage(ResourceBundle rb) {
        // TODO Auto-generated method stub
        
    }
}
