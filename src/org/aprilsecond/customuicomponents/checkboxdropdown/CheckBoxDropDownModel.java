package org.aprilsecond.customuicomponents.checkboxdropdown;

import java.awt.Color;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This implements a model for the drop down menu 
 * model that contains the items that are displayed when 
 * the component drop down is displayed
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class CheckBoxDropDownModel {
    
    /**
     * stores the string items and their colors
     */
    private ConcurrentHashMap<String, Color> items;
    
    /**
     * stores the title for the component
     */
    private String compTitle ;
    
    /**
     * constructor initializes null model
     */
    public CheckBoxDropDownModel() {
        items = new ConcurrentHashMap<String, Color>()  ;
        compTitle = "" ;
    }
    
    /**
     * constructor initializes model with list of items
     */
    public CheckBoxDropDownModel(String title, 
            ConcurrentHashMap<String, Color> compItems) {
        if (null != items) items.clear();
            items = compItems ;
        compTitle = title ;
    }
    
    /**
     * gets the title for the component
     */
    public String getTitle() {
        return compTitle ;
    }
    
    /** 
     * sets the title for the component
     */
    public void setTitle(String title) {
        compTitle = title ;
    }
    
    /**
     * adds components to the model
     */
    public void addItem(String item, Color itemColor) {
        if(null == items) items = new ConcurrentHashMap<String, Color>() ;
        items.put(item, itemColor) ;
    }
    
    /** 
     * removes a component based on the item name
     */
    public void removeItem (String item) {
        if (null != items) {
            // get the index of the item
            items.remove(item);
        }
    }
    
    /**
     * adds the details for the model
     */
    public void addItems(ConcurrentHashMap<String,Color> compItems) {
        // clear the model for the items
        if(null  == items) items = new ConcurrentHashMap<String, Color> () ;
        if(items.size() > 0) {
            compItems.clear();
        }
               
        // add the items for the model
       items.putAll(compItems);
    }
    
    /**
     * gets all the model items excluding the title
     */
    public ConcurrentHashMap<String, Color> getItems() {
        return items ;
    }
    
    public void clear() {
        items.clear();
    }
}
