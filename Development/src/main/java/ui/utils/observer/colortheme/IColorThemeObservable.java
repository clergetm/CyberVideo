package ui.utils.observer.colortheme;

/**
 * Common interface for Color Theme observable objects.
 * @author MathysC
 * @see ui.utils.observer.colortheme.IColorThemeObserver
 * @see ui.utils.observer.colortheme.ColorThemes
 * @see ui.utils.observer.colortheme.palettes.Dark
 * @see ui.utils.observer.colortheme.palettes.Light
 */
public interface IColorThemeObservable {

    /**
     * Add an observer.
     * @author MathysC
     *
     * @param colorThemeObserver the observer.
     */
    void registerColorTheme(IColorThemeObserver colorThemeObserver);
    
    /**
     * Remove an observer.
     * @author MathysC
     *
     * @param colorThemeObserver the observer.
     */
    void unregisterColorTheme(IColorThemeObserver colorThemeObserver);
    
    /**
     * Change color theme of all observers.
     * @author MathysC
     *
     * @param color the color to set.
     */
    void notifyColorThemeObservers(ColorThemes color);
}
