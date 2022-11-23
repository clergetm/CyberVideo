package ui.utils.observer.multilingual;

import java.util.ArrayList;

/**
 * Concrete implementation of IMultilingualObservable.
 * @author MathysC
 * @see ui.utils.observer.multilingual.IMultilingualObservable
 * @see ui.utils.observer.multilingual.IMultilingualObserver
 * @see ui.utils.observer.multilingual.Languages
 */
public class MultilingualManager implements IMultilingualObservable {
    private ArrayList<IMultilingualObserver> observers;
    private Languages currentLang;
    
    private static MultilingualManager instance = null;
    /**
     * Default constructor of {@code MultilingualManager}.
     * Set the default language to ENGLISH.
     * @author MathysC
     *
     */
    private MultilingualManager() {
	this.observers = new ArrayList<>();
	
	// English is the default language.
	this.currentLang = Languages.ENGLISH;
	
    }
    
    public static MultilingualManager getInstance() {
	if(instance == null) {
	    instance = new MultilingualManager();
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
    public void refresh() {
	this.notifyObservers(this.getCurrentLang());
	
    }
    
    @Override
    public void register(IMultilingualObserver multilingualObserver) {
	this.observers.add(multilingualObserver);

    }

    @Override
    public void unregister(IMultilingualObserver multilingualObserver) {
	this.observers.remove(multilingualObserver);

    }

    @Override
    public void notifyObservers(Languages language) {
	this.setCurrentLang(language);
	this.observers.forEach(o -> o.setLanguage(language.getResourceBundle()));

    }

}
