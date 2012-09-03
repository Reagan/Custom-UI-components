package org.aprilsecond.customuicomponents.List.CustomList;

import org.aprilsecond.customuicomponents.List.ListItem.SimpleListItem;

/**
 * This class implements a simple list that displays
 * simple list items and throws events when selected
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class SimpleList extends AbstractList {
    
    /**
     * stores the model for the simple list
     */
    private SimpleListModel simpleListModel  ;
    
    /**
     * null constructor initializes list using default settings
     */
    public SimpleList() {
        super() ;
        
        // initialise the model
        simpleListModel = new SimpleListModel() ;
        
        // add the model
        setListModel(simpleListModel, SimpleListItem.height);
    }
    
    /**
     * null constructor initializes dimensions
     */
    public SimpleList(int width, int height) {
        
        // initialise the dimensions
        super(width, height) ;
        
        // initialise the model
        simpleListModel = new SimpleListModel() ;
        
        // add the model
        setListModel(simpleListModel, SimpleListItem.height);
    }
    
    /**
     * null constructor only initializes model
     */
    public SimpleList(SimpleListModel model) {
        super() ;    
        
        // initialize the model
        simpleListModel = new SimpleListModel() ;
        
        // add the model
        setListModel(simpleListModel, SimpleListItem.height);
    }
    
    /**
     * initializes both dimensions and items
     */
    public SimpleList(SimpleListModel model, int width, int height) {
        super(width, height) ;
        
        // add the model
        setListModel(model, SimpleListItem.height);
    }
        
    /**
     * overrides behavior for adding the model
     */
    public void setSimpleListModel(SimpleListModel slModel) {
        simpleListModel = slModel ;
        setListModel(simpleListModel, SimpleListItem.height);
    }
}
