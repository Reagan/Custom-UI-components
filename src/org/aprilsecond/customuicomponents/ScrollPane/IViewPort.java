package org.aprilsecond.customuicomponents.ScrollPane;

import java.util.ArrayList;

/**
 * This class defines methods to help define the 
 * behaviors and properties of the the viewport
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public interface IViewPort {
    /**
     * gets the number of components
     */
    int getViewPortSize() ;
    
    /**
     * returns the stored components
     */
    ArrayList<PanelComponent> getViewPortComponents() ;
    
    /**
     * returns a specific component as identified by the index
     */
    PanelComponent getViewPortComponent(int componentIndex) ;
    
}
