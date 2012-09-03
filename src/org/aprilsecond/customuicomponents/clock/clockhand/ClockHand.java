package org.aprilsecond.customuicomponents.clock.clockhand;

/**
 * This class defines the required methods 
 * required for the minutes, hours and 
 * seconds hands
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public interface ClockHand {
    
    /**
     * specifies the Hour hand
     */
    int HOUR = 0 ;
    
    /**
     * specifies the MINUTE hand
     */
    int MINUTE = 1 ;
    
    /**
     * specifies the SECOND hand
     */
    int SECOND = 2 ; 
    
    /**
     * specifies the time that the hand should draw
     * @param time 
     */
    void drawHand(int time) ;
}
