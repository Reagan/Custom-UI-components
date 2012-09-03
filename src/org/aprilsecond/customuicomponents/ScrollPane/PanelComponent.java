package org.aprilsecond.customuicomponents.ScrollPane;

import javax.swing.JPanel;

/**
 * This class should be extended for all components 
 * that are selectable and that are placed on a ViewPort
 * 
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public abstract class PanelComponent extends JPanel
    implements IPanelComponent {
    
    /**
     * stores state on whether the component is 
     * selectable
     */
    public boolean isSelectable = false ;
    
    /**
     * stores selected state
     */
    private boolean isSelected = false ;

    /**
     * null constructor defaults the initialization state
     */
    public PanelComponent() {
        setHoveredState(false);
    }
    
    /**
     * null constructor initializes the selected state of the component
     */
    public PanelComponent(boolean state) {
        setHoveredState(state);
    }
    
    /**
     * sets if the component is selectable or not
     * @param state 
     */
    @Override
    public final void isSelectable(boolean state) {
        isSelectable = state ;
    }

    /**
     * shows if the component is selected or not
     * @return 
     */
    @Override
    public final boolean selectedState() {
        return isSelected ;
    }
    
    /**
     * sets the selected state
     */
    @Override
    public void setHoveredState(boolean state) {
        isSelected = state ;
    }
    
    /**
     * get the panel ID
     */
    public String getComponentID() {
        return this.toString() ;
    }
}
