package ui.utils.observer.multilingual;

import java.util.ResourceBundle;

/**
 * Common Interface for Multilingual Observer object.
 * Each class implementing this interface support languages
 * enumerated in {@code Languages}.
 * @author MathysC
 * @see ui.utils.observer.multilingual.Languages
 * @see ui.utils.observer.multilingual.IMultilingualObservable
 */
public interface IMultilingualObserver {


    /**
     * Set all text in one language
     * @author MathysC
     *
     * @param rb the {@code ResourceBundle} of the language.
     */
    void setLanguage(ResourceBundle rb);
}
