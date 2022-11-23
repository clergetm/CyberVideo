package ui.utils.observer.multilingual;


/**
 * Common Interface for Multilingual Observable.
 * @author MathysC
 * @see ui.utils.observer.multilingual.Languages
 * @see ui.utils.observer.multilingual.IMultilingualObserver
 */
public interface IMultilingualObservable {

    /**
     * Add an observer.
     * @author MathysC
     *
     * @param multilingualObserver the observer.
     */
    void registerMultilingual(IMultilingualObserver multilingualObserver);
    
    /**
     * Remove an observer.
     * @author MathysC
     *
     * @param multilingualObserver the observer.
     */
    void unregisterMultilingual(IMultilingualObserver multilingualObserver);
    
    /**
     * Change languages of all observers.
     * @author MathysC
     *
     * @param language the language to set.
     */
    void notifyMultilingualObservers(Languages language);
}
