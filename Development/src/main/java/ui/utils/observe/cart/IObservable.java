package ui.utils.observe.cart;

import fc.films.Film;
import ui.managers.FilmEvents;

/**
 * Common Interface for Observable cart related object.
 * @author MathysC
 */
public interface IObservable {

    /**
     * Add an observer.
     * @author MathysC
     *
     * @param observer The observer to register.
     */
    void register(IObserver observer);
    
    /**
     * Remove an observer.
     * @author MathysC
     *
     * @param observer The observer to unregister.
     */
    void unregister(IObserver observer);
    
    /**
     * Notify observers of any change depending on the Event
     * @author MathysC
     *
     * @param event The type of event (ADD or REMOVE).
     * @param film The film related to this change.
     * @param supportType The supportType related to this Film.
     */
    void notifyObservers(FilmEvents event, Film film, String supportType);
}
