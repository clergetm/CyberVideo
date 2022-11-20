package ui.utils.mediator.cart.components;

import ui.utils.mediator.cart.CartMediator;

/**
 * Component interface to represent a cart related object.
 * @author MathysC
 * @see ui.utils.mediator.cart.CartMediator
 */
public interface Component {

    /**
     * 
     * @author MathysC
     *
     * @param cartMediator The mediator to set
     */
    void setMediator(CartMediator cartMediator);
    
    /**
     * 
     * @author MathysC
     *
     * @return the name of the component.
     */
    String getComponentName();
}