package org.aprilsecond.customuicomponents.AnimatedMenu;

import java.util.EventListener;

/**
 * This class implements a listener for the menu items that 
 * have been selected on the Animated Menu.
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public interface MenuItemSelectedListener extends EventListener {
    
    public void menuItemSelected(MenuItemSelected evt) ;
}
