package ui.managers;

import java.util.ArrayList;

import ui.utils.observer.colortheme.ColorThemes;
import ui.utils.observer.colortheme.IColorThemeObservable;
import ui.utils.observer.colortheme.IColorThemeObserver;
import ui.utils.observer.multilingual.IMultilingualObservable;
import ui.utils.observer.multilingual.IMultilingualObserver;
import ui.utils.observer.multilingual.Languages;

/**
 * Concrete implementation of IMultilingualObservable.
 * @author MathysC
 * @see ui.utils.observer.multilingual.IMultilingualObservable
 * @see ui.utils.observer.multilingual.IMultilingualObserver
 * @see ui.utils.observer.multilingual.Languages
 */
public class GUIManager implements IMultilingualObservable, IColorThemeObservable {
    private ArrayList<IMultilingualObserver> multilinguals;
    private ArrayList<IColorThemeObserver> colorthemes;
    private Languages currentLang;
    private ColorThemes currentColorTheme;
    
    private static GUIManager instance = null;
    /**
     * Default constructor of {@code MultilingualManager}.
     * Set the default language to ENGLISH.
     * @author MathysC
     *
     */
    private GUIManager() {
	this.multilinguals = new ArrayList<>();
	this.colorthemes = new ArrayList<>();
	
	// English is the default language.
	this.currentLang = Languages.ENGLISH;
	
	// LightTheme is the default color Theme.
	this.currentColorTheme = ColorThemes.LIGHT_THEME;
    }
    
    public static GUIManager getInstance() {
	if(instance == null) {
	    instance = new GUIManager();
	}
	
	return instance;
	
    }
    /**
     * @author MathysC
     * @return the currentLang
     */
    public Languages getCurrentLang() {
	return currentLang;
	
    }
    
    /**
     * @author MathysC
     * @param currentLang the currentLang to set
     */
    public void setCurrentLang(Languages currentLang) {
	this.currentLang = currentLang;
	
    }
    
    /**
     * Refresh all observers with the current language.
     * @author MathysC
     */
    public void refreshMultilingual() {
	this.notifyMultilingualObservers(this.getCurrentLang());
	
    }
    
    @Override
    public void registerMultilingual(IMultilingualObserver multilingualObserver) {
	this.multilinguals.add(multilingualObserver);

    }

    @Override
    public void unregisterMultilingual(IMultilingualObserver multilingualObserver) {
	this.multilinguals.remove(multilingualObserver);

    }

    @Override
    public void notifyMultilingualObservers(Languages language) {
	this.setCurrentLang(language);
	this.multilinguals.forEach(o -> o.setLanguage(language.getResourceBundle()));

    }

        /*
    * @author MathysC
    * @return the currentColorTheme
    */
    public ColorThemes getCurrentColorTheme() {
	return this.currentColorTheme;

    }
       
       /**
    * @author MathysC
    * @param currentColorTheme the currentColorTheme to set
    */
    public void setCurrentColorTheme(ColorThemes currentColorTheme) {
    	this.currentColorTheme = currentColorTheme;
    	
    }
       
       /**
    * Refresh all observers with the current language.
    * @author MathysC
    */
    public void refreshColorTheme() {
    	this.notifyColorThemeObservers(this.getCurrentColorTheme());
    	
    }
        
    @Override
    public void registerColorTheme(IColorThemeObserver colorThemeObserver) {
	this.colorthemes.add(colorThemeObserver);
	
    }

    @Override
    public void unregisterColorTheme(IColorThemeObserver colorThemeObserver) {
	this.colorthemes.remove(colorThemeObserver);
	
    }

    @Override
    public void notifyColorThemeObservers(ColorThemes color) {
	this.setCurrentColorTheme(color);
	this.colorthemes.forEach(o -> o.setColorTheme(color));
	
    }

    
    public void refreshUI() {
	this.refreshMultilingual();
	this.refreshColorTheme();
    }
}
