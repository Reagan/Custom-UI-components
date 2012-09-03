package org.aprilsecond.customuicomponents.ScrollPane;

import java.util.EventListener;

/**
 * This creates a listener interface that is triggered when 
 * a panelComponent is selected
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public interface ItemSelectedListener extends EventListener {
    
    public void itemSelected(ItemSelected evt) ;
}
