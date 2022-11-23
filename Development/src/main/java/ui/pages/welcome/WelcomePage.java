package ui.pages.welcome;

import java.awt.BorderLayout;

import java.util.ResourceBundle;

import javax.swing.JPanel;

import ui.utils.bundles.Multilingual;
import ui.utils.colors.ColorTheme;

/**
 * Welcome page that show the login panel and suggestions panel.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class WelcomePage extends JPanel implements ColorTheme {

    /* Components */
    protected SuggestionsPanel suggestionsPanel = new SuggestionsPanel();
    protected LoginPanel loginPanel = new LoginPanel();

    /**
     * Constructor of WelcomePage
     * Set JPanel options and add the two main Panels.
     * @author MathysC
     *
     */
    public WelcomePage() {
        this.setLayout(new BorderLayout());
        this.add(suggestionsPanel, BorderLayout.CENTER);
        this.add(loginPanel, BorderLayout.EAST);
    }

    /**
     * Getter of suggestionsPanel
     * @author MathysC
     *
     * @return SuggestionsPanel
     */
    public SuggestionsPanel getSuggestionsPanel() { return this.suggestionsPanel; }
    
    /** 
     * Getter of loginPanel
     * @author MathysC
     *
     * @return LoginPanel
     */
    public LoginPanel getLoginPanel() { return this.loginPanel; }

    @Override
    public void setLight() {
	this.suggestionsPanel.setLight();
	this.loginPanel.setLight();
    }

    @Override
    public void setDark() {
	this.suggestionsPanel.setDark();
	this.loginPanel.setDark();
    }
}