package org.aprilsecond.customuicomponents.String;

import java.util.EventObject;

/**
 * This class creates an event that is triggered when the DateString
 * is double clicked
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class DateStringSelected extends EventObject{
    /**
     * stores the date string that is returned
     * when the string is selected
     */
    private String dateStringDisplayed ; 
    
    public DateStringSelected(Object source, String displayedString) {
        super(source) ;
        setDateStringDisplayed(displayedString) ;
    }
    
    /**
     * sets the string to be displayed
     */
    public void setDateStringDisplayed(String dateString) {
        dateStringDisplayed = dateString ;
    }
    
    /**
     * gets the date string displayed
     */
    public String getDateStringDisplayed() {
        return dateStringDisplayed ;
    }
}
