package org.aprilsecond.customuicomponents.AnimatedMenu;

import java.util.EventObject;

/**
 * This class creates an Event Object that is triggered 
 * when a menu item is selected.
 * 
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class MenuItemSelected extends EventObject {
    
    /**
     * stores the selected menu item
     */
    private AnimatedMenuItem menuItem ;
    
    /**
     * constructor initializes menu component
     */
    public MenuItemSelected(Object source, AnimatedMenuItem menuItem) {
        super(source) ;
        setSelectedMenuItem(menuItem) ;
    }
    
    /**
     * sets the selected menu item
     */
    public final void setSelectedMenuItem(AnimatedMenuItem mItem) {
        menuItem = mItem ;
    }
    
    /**
     * gets the selected menu item
     */
    public AnimatedMenuItem getSelectedMenuItem() {
        return menuItem ;
    }
}
