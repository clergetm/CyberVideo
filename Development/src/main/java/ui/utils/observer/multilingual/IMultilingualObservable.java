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
    void register(IMultilingualObserver multilingualObserver);
    
    /**
     * Remove an observer.
     * @author MathysC
     *
     * @param multilingualObserver the observer.
     */
    void unregister(IMultilingualObserver multilingualObserver);
    
    /**
     * Change languages of all observers.
     * @author MathysC
     *
     * @param language the language to set.
     */
    void notifyObservers(Languages language);
}
