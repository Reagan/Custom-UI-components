package org.aprilsecond.customuicomponents.clock;

import java.util.Calendar;

/**
 * This class implements a clock model that contains the 
 * clock time values
 * @author Reagan Mbitiru 
 */
public class ClockModel {
    
    /** 
     * stores the time for the clock
     */
    private Time currTime ;
    
    /**
     * null constructor, sets the current time as the 
     * initial set time
     */
    public ClockModel() {
        // create the time object
        Time cTime = new Time() ;
        cTime.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        cTime.setMinute(Calendar.getInstance().get(Calendar.MINUTE));
        cTime.setSecond(Calendar.getInstance().get(Calendar.SECOND));
        
        // append the time object to the Clock Model
        currTime = cTime ;
    }
    
    /**
     * gets the time
     */
    public Time getTime() {
        return currTime ;
    }
    
    /**
     * sets the time
     */
    public void setTime(Time cTime) {
        currTime = cTime ;
    }    
}
