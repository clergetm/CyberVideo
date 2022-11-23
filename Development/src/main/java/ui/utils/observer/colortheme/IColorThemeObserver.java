package ui.utils.observer.colortheme;

/**
 * Common Interface for colorTheme observer object.
 * @author MathysC
 * @see ui.utils.observer.colortheme.IColorThemeObservable
 * @see ui.utils.observer.colortheme.ColorThemes
 * @see ui.utils.observer.colortheme.palettes.Dark
 * @see ui.utils.observer.colortheme.palettes.Light
 */
public interface IColorThemeObserver {

    /**
     * Set the colorTheme of the observer.
     * @author MathysC
     *
     * @param colorTheme The color theme to set.
     */
    void setColorTheme(ColorThemes colorTheme);
}
