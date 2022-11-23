package ui.pages.welcome;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Welcome page that show the login panel and suggestions panel.
 * We keep this 
 * @author MathysC
 * @see ui.pages.welcome.SuggestionsPanel
 * @see ui.pages.welcome.LoginPanel
 */
@SuppressWarnings("serial")
public class WelcomePage extends JPanel {

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

}