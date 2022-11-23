package ui.utils.observer.cart;

import fc.films.Film;
import ui.managers.FilmEvents;

/**
 * Common interface for an Observer, cart related, object.
 * @author MathysC
 * @see ui.utils.observer.cart.ICartObservable
 * @see ui.managers.FilmEvents
 */
public interface ICartObserver {
  
    /**
     * 
     * @author MathysC
     *
     * @param event The type of event (e.g. ADDTOCART).
     * @param film The film related to this change.
     * @param supportType The supportType related to the Film.
     */
    void update(FilmEvents event, Film film, String supportType);
}
