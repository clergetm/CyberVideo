package ui.utils.mediator.cart.components;

import ui.utils.mediator.cart.Mediator;

/**
 * Component interface to represent a cart related object.
 * @author MathysC
 * @see ui.utils.mediator.cart.Mediator
 */
public interface Component {

    /**
     * 
     * @author MathysC
     *
     * @param mediator The mediator to set
     */
    void setMediator(Mediator mediator);
    
    /**
     * 
     * @author MathysC
     *
     * @return the name of the component.
     */
    String getComponentName();
}