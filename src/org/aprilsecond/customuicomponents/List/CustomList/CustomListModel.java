package org.aprilsecond.customuicomponents.List.CustomList;

import java.util.ArrayList;
import org.aprilsecond.customuicomponents.ScrollPane.PanelComponent;

/**
 * This class creates a default model for the list
 * that is used to display the various list items
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class CustomListModel {
    
    /**
     * stores the arraylist of JPanels (PanelComponents)
     * that are used to display the various list items
     */
    private ArrayList<PanelComponent> listItems ;
        
    /**
     * null constructor
     */
    public CustomListModel() {
        // initialize the custom elements
        listItems = new ArrayList<PanelComponent>() ;
    }
    
    /**
     * null constructor initializes the model
     */
    public CustomListModel(ArrayList<PanelComponent> items) {
        // initializes the custom list model
        listItems = items ;
    }
    
    /**
     * adds a component to the list 
     */
    public void addItem(PanelComponent component) {
        listItems.add(component);
    }
    
    /**
     * removes a component from a list
     */
    public void removeItem(PanelComponent component) {
        listItems.remove(component);
    }
    
    /**
     * gets the number of items in the model
     */
    public int getSize() {
        return listItems.size() ;
    }
    
    /**
     * get the index for a specific component
     */
    public int getComponentIndex(PanelComponent component) {
        return listItems.indexOf(component) ;
    }
    
    /**
     * gets the component at the specified index
     */
    public PanelComponent getComponent(int componentIndex) {
        return listItems.get(componentIndex) ;
    }
    
    /**
     * get the array list of the panel components
     */
    public ArrayList<PanelComponent> getComponents() {
        return listItems ;
    }
    
    /**
     * adds components to model
     */
    public void setModelItems(ArrayList<PanelComponent> components) {
        listItems = components ;
    }
    
    /**
     * clears the model
     */
    public void clear() {
        listItems.clear();
    }
}
