package org.aprilsecond.customuicomponents.ScrollPane;

import java.util.EventObject;

/**
 * This class creates an ItemSelected Event that is triggered
 * if any PanelComponent is selected
 * @author Reagan Mbitiru  <reaganmbitiru@gmail.com>
 */
public class ItemSelected extends EventObject {
    
    /**
     * stores the selected Panel Component
     */
    private PanelComponent component ;
    
    /**
     * constructor initializes source for the component
     */
    public ItemSelected(Object source, PanelComponent component) {
        super(source) ;
        setComponentSelected(component) ;
    }
    
    /**
     * sets the selected panel component
     */
    public final void setComponentSelected(PanelComponent comp) {
        component = comp ;
    }
    
    /**
     * gets the selected panel component
     */
    public PanelComponent getComponentSelected() {
        return component ;
    }
}
