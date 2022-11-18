package ui.bundles;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Multilingual is an interface that implements a method {@code setLanguage} used to change the language of interface labels.
 *
 * @author MathysC
 *
 */
public interface Multilingual {

    // List of supported Languages : English and French
    Locale[] supportedLocales = {
        Locale.ENGLISH,
        Locale.FRENCH
    };

    // Path to Bundles
    String PREFIX_BUNDLE = "ui/Bundles/",
        FR_BUNDLE = PREFIX_BUNDLE + "bundle_fr",
        EN_BUNDLE = PREFIX_BUNDLE + "bundle_en";

    /**
     * Getter of the English Bundle.
     * @author MathysC
     *
     * @return the English ResourceBundle.
     */
    default ResourceBundle getRbEN() {
        return ResourceBundle.getBundle(EN_BUNDLE, supportedLocales[0]);
    }

    /**
     * Getter of the French Bundle.
     * @author MathysC
     *
     * @return the French ResourceBundle.
     */
    default ResourceBundle getRbFR() {
        return ResourceBundle.getBundle(FR_BUNDLE, supportedLocales[1]);
    }

    /**
     * Set all text from the class.
     * @author MathysC
     *
     * @param rb the chosen language bundle.
     */
    void setLanguage(ResourceBundle rb);

}