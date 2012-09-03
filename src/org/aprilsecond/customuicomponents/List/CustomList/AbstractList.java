package org.aprilsecond.customuicomponents.List.CustomList;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import org.aprilsecond.customuicomponents.ScrollPane.PanelComponent;
import org.aprilsecond.customuicomponents.ScrollPane.ViewPort;

/**
 * This is the abstract class that creates a
 * default list that is used to display the various 
 * list items (the list items may be simple or 
 * compound)
 * 
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class AbstractList extends ViewPort {
    
    /**
     * stores the minimum width for the panel
     */
    private int listWidth = 300 ;
    
    /**
     * stores the minimum height for the panel
     */
    private int listHeight = 300 ;
    
    /**
     * stores the model for the component
     */
    private CustomListModel listModel ;    
    
    /**
     * stores the highlighted component
     */
    private int highlightedComponentIndex = -1 ;
    
    /**
     * null constructor initializes default settings
     */
    public AbstractList() {}
    
    /**
     * null constructor
     */
    public AbstractList(int width, int height) {
        // initialize the dimensions
        listWidth = width ;
        listHeight = height ;        
    }
    
    /** 
     * null constructor initializes the model
     */
    public AbstractList(CustomListModel model) {
        // initialize the model
        listModel = model ;
    }
    
    /**
     * constructor initializes all required variables
     */
    public AbstractList(int width, int height, CustomListModel model){ 
        // initialize dimensions
        listWidth = width ;
        listHeight = height ;
        
        // initialize the model
        listModel = model ;
    }        
    
    /**
     * sets the dimensions for the component
     */
    public void setListDimensions(Dimension listDimensions) {
        listWidth = listDimensions.width ;
        listHeight = listDimensions.height ;
    }    
    
    /**
     * finalizes initialization of the model
     */
    public void drawComponent(int heightForEachListItem) {
        
        clearDisplayedListItems();
        
        // set the layout for the component
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // set the size of the component
        setSize(listWidth, (getViewPortSize() * heightForEachListItem)) ;
        
        // lay out the components
        if (listModel != null) {
            // add the components to the model
            addComponentsToList() ;
        }
        
        validate() ;
    }
           
    private void clearDisplayedListItems() {  
        repaint();
        removeAll();
    }
    
    /** 
     * This method adds the components to the list
     */
    private void addComponentsToList() {
        
        // loop through adding the components to the model         
        for (int componentCounter = 0 ; componentCounter < listModel.getSize() ;
                componentCounter ++) { 
            
            if (componentCounter == highlightedComponentIndex) {
                
                boolean selectComponent = true ;
               listModel.getComponent(componentCounter)
                       .setSelectedState(selectComponent);
            }            
            
            add(listModel.getComponent(componentCounter)) ;
        }
    }
    
    /**
     * add a model to the list
     */
    protected void setListModel(CustomListModel model, int heightForEachComponent) {
        listModel = model ;    
        drawComponent(heightForEachComponent);
    }
    
    public void setSelectedItem(int componentindex) {
        highlightedComponentIndex = componentindex ;
        revalidate();
    }
    
    public void setSelectedItem(PanelComponent component) {
        ArrayList<PanelComponent> components = getViewPortComponents() ;
        for (int componentsCounter = 0 ; componentsCounter < components.size();
                componentsCounter++) {
            PanelComponent currComp 
                    = components.get(componentsCounter) ;
            
            if (currComp == component) {
                currComp.setSelectedState(true);
            } else {
                currComp.setSelectedState(false);
            }
        }
        validate();
    }
    
    public int getSelectedItem() {
        return highlightedComponentIndex ;
    }
    
    @Override
    public int getViewPortSize() {
        return listModel.getSize() ;
    }

    @Override
    public ArrayList<PanelComponent> getViewPortComponents() {
        return listModel.getComponents() ;
    }

    @Override
    public PanelComponent getViewPortComponent(int componentIndex) {
        return listModel.getComponent(componentIndex) ;
    }
}
