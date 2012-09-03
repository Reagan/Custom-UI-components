package org.aprilsecond.customuicomponents.List.CustomList;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import org.aprilsecond.customuicomponents.List.ListItem.CalendarListItem;

/**
 * This creates a model for the items to be displayed in a 
 * CalendarList component
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class CalendarListModel extends CustomListModel {
    
    /**
     * stores the item messages to be displayed
     */
    private ArrayList<String> items ;
    
    /**
     * stores the times for the display of items
     */
    private ArrayList<Calendar> times ;
        
    /**
     * stores width of each calendar list component
     */
    private int calendarItemWidth = CalendarListItem.width ;
    
    /**
     * stores height of each calendar list component
     */
    private int calendarItemHeight =  CalendarListItem.height ;
    
    /**
     * stores the array with the alternating background 
     * colors displayed
     */
    private Color [] bgColors = {new Color(88, 105, 122), 
        new Color(65, 75, 84)} ;
   
    public CalendarListModel(ArrayList<String> compItems, 
            ArrayList<Calendar> compItemsTimes, int width, 
            int height) {
        
        calendarItemWidth = width ;
        calendarItemHeight = height ;  
       
        initialize(compItems, compItemsTimes) ;
    }    
    
    public CalendarListModel(ArrayList<String> compItems, 
            ArrayList<Calendar> compItemsTimes) {
        initialize(compItems, compItemsTimes);
    }
        
    private void initialize(ArrayList<String> compItems, 
            ArrayList<Calendar> compItemsTimes) {
        if (null != compItems) items = compItems ;
        else items = new ArrayList<String>() ;
        
        if (null != compItemsTimes) times = compItemsTimes ;
        else times = new ArrayList<Calendar>() ;
                        
        populateModel();        
    }
    
    private void populateModel() {
        
        if (times.size() != items.size())  {
            throw new RuntimeException("There is an error with messages"
                    + " and times for those messages passed to "
                    + " the calendar list") ;
        }
        
        // loop through creating the components and adding them 
        // to the components
        for (int itemsCounter = 0 ; itemsCounter < items.size() ;
                itemsCounter ++) {
            CalendarListItem currItem = new CalendarListItem(times.get(itemsCounter), 
                    items.get(itemsCounter), calendarItemWidth, 
                    calendarItemHeight) ;
            
            // add the correct bgColor to the calendar item
            int currBgCounter = itemsCounter % 2 ;            
            currItem.setBgColor(bgColors[currBgCounter]);
            
            // add this to the model
            addItem(currItem);
        }
        
        setModelItems(getComponents());
    }
    
    public ArrayList<String> getCalendarItems() {
        return items ;
    }
    
    public ArrayList<Calendar> getCalendarTimes() {
        return times ;
    }    
}
