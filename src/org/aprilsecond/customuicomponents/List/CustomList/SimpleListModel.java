package org.aprilsecond.customuicomponents.List.CustomList;

import java.util.ArrayList;
import org.aprilsecond.customuicomponents.List.ListItem.SimpleListItem;

/**
 * This class specifies a model for use by the SimpleList 
 * UI component
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class SimpleListModel extends CustomListModel {
    
    private ArrayList<String> items ;
    private ArrayList<Object> itemAliases ;
    private int listItemWidth = SimpleListItem.width ;
    private int listItemHeight =  SimpleListItem.height ;
    
    public SimpleListModel() {
        items = new ArrayList<String>() ;
        itemAliases = new ArrayList<Object>() ;
        
        populateModel();
    }
    
    public SimpleListModel(ArrayList<String> compItems) {
        if (null != compItems) {
            items = compItems ;
            itemAliases = new ArrayList<Object>() ;
            
            for (int itemsCounter = 0 ; itemsCounter < compItems.size() ;
                    itemsCounter++) {
               itemAliases.add((String) compItems.get(itemsCounter));
            }
        }
        else {
            items = new ArrayList<String>() ;
            itemAliases = new ArrayList<Object>() ;
        }
        
        populateModel();
    }
    
    public SimpleListModel(ArrayList<String> compItems, 
            ArrayList<Object> compItemAliases)  {
        items = compItems ;
        itemAliases = compItemAliases ;
        
        populateModel();
    }
    
    public void setListItemAliases(ArrayList<Object> aliases) {
        if ( null != aliases) itemAliases = aliases ;
        else itemAliases = new ArrayList<Object>() ;
        
        populateModel();
    }
    
    public ArrayList<Object> getAliases() {
        return itemAliases ;
    }
    
    public ArrayList<String> getItems() {
       return items ;
    }
    
    private void populateModel() {
        
        // set default dimensions for each of the list items
        int defaultListItemWidth = listItemWidth ;
        int defaultListItemHeight = listItemHeight ;
        
        // create list items and add them to list
        for (int itemsCounter = 0 ; itemsCounter < items.size() ;
                itemsCounter ++) {
            
            // get the current list item
            SimpleListItem item = new SimpleListItem(items.get(itemsCounter),
                    defaultListItemWidth, defaultListItemHeight) ;
            
            // add an alias 
            if (null != itemAliases) {
                item.setComponentIDAlias(itemAliases.get(itemsCounter));               
            }
            
            // add it to the model
            addItem(item);
        }
        
        setModelItems(getComponents());
    }
}
