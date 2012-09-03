package org.aprilsecond.customuicomponents.clock;

/**
 * Stores a time object
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class Time {
    
    /**
     * stores the current hour
     */
    private int hour ;
    
    /**
     * stores the current minute
     */
    private int minute ;
    
    /**
     * stores the current second
     */
    private int second ;
    
    /**
     * null constructor
     */
    public Time() {}
    
    /**
     * constructor initializes all the time components
     */
    public Time (int _hour, int min, int sec) {
        hour = _hour ;
        minute = min ;
        second = sec ;
    }
    
    /**
     * sets a time
     */
    public void setTime(int _hour, int min, int sec) {
        hour = _hour ;
        minute = min ;
        second = sec ;
    }
    
    /**
     * sets the hour
     */
    public void setHour(int _hour) {
        hour = _hour ;
    }
    
    /**
     * gets the hour
     */
    public int getHour() {
        return hour ;
    }
    
    /**
     * sets the minute
     */
    public void setMinute(int min) {
        minute = min ;
    }
    
    /**
     * gets the minute
     */
    public int getMinute() {
        return minute ;
    }
    
    /**
     * sets the second
     */
    public void setSecond(int sec) {
        second = sec ;
    }
    
    /**
     * gets the second
     */
    public int getSecond() {
        return second ;
    }
}
