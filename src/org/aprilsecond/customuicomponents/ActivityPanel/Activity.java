package org.aprilsecond.customuicomponents.ActivityPanel;

import java.util.Calendar;

/**
 * Stores an activity object 
 * @author reagan
 */
public class Activity {
    /**
     * stores the type of activity. It may be highlighted or
     * normal
     */
    private int activityType ; 
    
    /**
     * stores the highlighted activity type
     */
    public final static int HIGHLIGHTED_ACTIVITY = 1 ;
    
    /**
     * stores a normal activity type
     */
    public final static int NORMAL_ACTIVITY = 2 ;
    
    /**
     * stores a title activity type
     */
    public final static int TITLE_ACTIVITY = 3 ;
    
    /**
     * stores start time
     */
    private Calendar startTime ;
    
    /**
     * stores stop time
     */
    private Calendar stopTime ;
    
    /**
     * stores the details of the activity
     */
    private String activityMessage ;
    
    /**
     * constructor initializes the variables to the activity type
     * @param activityType
     * @param normalOrHighlighted
     * @param startTime
     * @param stopTime
     * @param activityMessage 
     */
    public Activity(int aType, Calendar staTime, 
            Calendar stoTime, String aMessage) {
        
        // sets the activity time
        activityType = aType ;
        
        // sets the start time
        startTime = staTime ;
        
        // sets the stop time
        stopTime = stoTime ;
        
        // sets the activity message
        activityMessage = aMessage ;                
    }
    
    /**
     * initializes the activity message type
     * @param activityMessage 
     */
    private Activity(String aMessage) {
        activityMessage = aMessage ;
    }
    
    /**
     * sets the activity type
     */
    public void setActivityType(int aType) {
        activityType = aType ;
    }
    
    /**
     * gets the activity type
     */
    public int activityType() {
        return activityType ;
    }
    
    /**
     * sets the activity start time
     */
    public void setActivityStartTime(Calendar staTime) {
        startTime = staTime ;
    }
    
    /**
     * gets the activity start time
     */
    public Calendar getStartTime() {
        return startTime ;
    }
    
    /**
     * sets the activity stop time
     */
    public void setActivityStopTime(Calendar stoTime) {
        stopTime = stoTime ;
    }
    
    /**
     * gets the activity stop time
     */
    public Calendar getStopTime() {
        return stopTime ;
    }
    
    /**
     * sets the activity message
     */
    public void setActivityMessage(String aMessage) {
        activityMessage = aMessage ;
    }
    /**
     * gets the activity message
     */
    public String getActivityMessage() {
        return activityMessage ;
    }
}
