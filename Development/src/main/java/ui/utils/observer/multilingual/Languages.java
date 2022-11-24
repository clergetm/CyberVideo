package ui.utils.observer.multilingual;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Enumeration of all languages supported by the GUI.
 * @author MathysC
 */
public enum Languages {
    ENGLISH(Locale.ENGLISH, "bundle_en"),
    FRENCH(Locale.FRENCH, "bundle_fr");
    
    private Locale locale;
    private String bundleName;
    private static final String PREFIXBUNDLE = "bundles/";
    
    /**
     * Default constructor of {@code Languages}
     * @author MathysC
     *
     * @param locale The locale language
     * @param bundleName The associated bundleName 
     * 
     * @see resources/bundles.
     */
    Languages(Locale locale, String bundleName) {
	this.locale = locale;
	this.bundleName = PREFIXBUNDLE+bundleName;
    }

    /**
     * 
     * @author MathysC
     *
     * @return the bundle.
     */
    public String getBundleName() {
	return this.bundleName;
    }
    
    /**
     * 
     * @author MathysC
     *
     * @return the Locale.
     */
    public Locale getLocale() {
	return this.locale;
    }
    
    /**
     * 
     * @author MathysC
     *
     * @return {@code ResourceBundle} associated to the Language.
     */
    public ResourceBundle getResourceBundle() {
	return ResourceBundle.getBundle(this.getBundleName(), this.getLocale());
    }
}
