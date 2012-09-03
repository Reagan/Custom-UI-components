package org.aprilsecond.customuicomponents.TimeTable;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Stores the details for an activity. Many activities form the 
 * model for the Timetable model. Each of these activities captures
 * the 
 * <ol>
 * <li>Activity</li>
 * <li>Start time</li>
 * <li>End time</li>
 * </ol>
 * 
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class Activity {
    
    /**
     * stores the activity string
     */
    private String activityName ;
    
    /**
     * stores the start time for the activity
     */
    private Calendar startTime ;
    
    /**
     * stores the end time for the activity
     */
    private Calendar stopTime ;
    
    /**
     * null constructor initializes ActivityItem
     */
    public Activity() {
        // initialize the various components
        activityName = new String() ;
        startTime = new GregorianCalendar() ;
        stopTime = new GregorianCalendar() ;
    }
    
    /**
     * constructor initializes all components of an activity
     */
    public Activity(String aName, Calendar staTime, 
            Calendar stoTime) {
        activityName = aName ;
        startTime = staTime ;
        stopTime = stoTime ;
    }
    
    /**
     * getter for the activity string
     */
    public String getActivity() {
        return activityName ;
    }
    
    /**
     * setter for the activity string
     */
    public void setString(String aName) {
        activityName = aName ;
    }
    
    
    /**
     * getter for the start time
     */
    public Calendar getStartTime() {
        return startTime ;
    }
    
    /**
     * setter for the start time
     */
    public void setStartTime(Calendar staTime) {
        startTime = staTime ;
    }    
    
    /**
     * getter for the stop time
     */
    public Calendar getStopTime() {
        return stopTime ;
    }
    
    /**
     * setter for the stop time
     */
    public void setStopTime(Calendar stoTime) {
        stopTime =  stoTime ;
    }
}
