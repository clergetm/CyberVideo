package ui.Pages.Welcome;

import java.awt.BorderLayout;

import java.util.ResourceBundle;

import javax.swing.JPanel;

import ui.Bundles.Multilingual;
import ui.Themes.ColorTheme;

/**
 * Welcome page that show the login panel and suggestions panel.
 * @author MathysC
 *
 */
@SuppressWarnings("serial")
public class WelcomePage extends JPanel implements Multilingual, ColorTheme {

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
     * Set the language of the two panels.
     * @author MathysC
     *
     */
    @Override
    public void setLanguage(ResourceBundle rb) {
        this.suggestionsPanel.setLanguage(rb);
        this.loginPanel.setLanguage(rb);
    }

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