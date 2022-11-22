package ui.utils.observe.cart;

import fc.films.Film;
import ui.managers.FilmEvents;

/**
 * Common Interface for Observable cart related object.
 * @author MathysC
 */
public interface ICartObservable {

    /**
     * Add an observer.
     * @author MathysC
     *
     * @param cartObserver The observer to register.
     */
    void register(ICartObserver cartObserver);
    
    /**
     * Remove an observer.
     * @author MathysC
     *
     * @param cartObserver The observer to unregister.
     */
    void unregister(ICartObserver cartObserver);
    
    /**
     * Notify observers of any change depending on the Event
     * @author MathysC
     *
     * @param event The type of event (e.g. ADDTOCART).
     * @param film The film related to this change.
     * @param supportType The supportType related to this Film.
     */
    void notifyObservers(FilmEvents event, Film film, String supportType);
}
