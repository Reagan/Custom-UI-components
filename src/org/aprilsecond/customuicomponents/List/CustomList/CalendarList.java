package org.aprilsecond.customuicomponents.List.CustomList;

import java.util.ArrayList;
import java.util.Calendar;
import org.aprilsecond.customuicomponents.List.ListItem.CalendarListItem;

/**
 * This class implements a Calendar list component which extends a 
 * list component and displays calendar entries and their details
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class CalendarList extends AbstractList {
    
    /**
     * stores the list model for the list
     */
    private CalendarListModel calendarListModel ;        
     
    /**
     * constructor initializes messages and times displayed 
     * for the messages.
     * @param times for messages displayed
     * @param messages information to be displayed for the various times
     */
    public CalendarList(ArrayList<Calendar> times, 
            ArrayList<String> messages, int width, int height) {
        
        // initialize abstract list dimensions
        super(width, height) ;
        
        // initialize model
        calendarListModel = new CalendarListModel(messages, times, 
                width, height) ;
        
         // add the model
        setCalendarListModel(calendarListModel);
    }
    
    public CalendarList() {
        super() ;
        calendarListModel = new CalendarListModel(null, null) ;
        setCalendarListModel(calendarListModel);
    }
    
    /**
     * initializes with model instance
     */
    public final void setCalendarListModel(CalendarListModel model) {
        calendarListModel = model ;
        setListModel(calendarListModel, CalendarListItem.height);
    } 
}
