package org.aprilsecond.customuicomponents.ScrollPane;

/**
 * This interface determines the characteristics for the 
 * panel components displayed
 * 
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public interface IPanelComponent {
   /**
    * states if a component is selectable
    */ 
    void isSelectable(boolean state) ;
    
    /**
     * shows the selected state of the component
     */
    boolean selectedState() ;
    
    /**
     * sets the component as selected
     */
    void setSelectedState(boolean state) ;
    
    /**
     * sets the selected state
     */
    void setHoveredState(boolean state);    
}
