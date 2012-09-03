package org.aprilsecond.customuicomponents.clock;

import java.util.EventObject;
import org.aprilsecond.customuicomponents.clock.clockhand.AbstractClockHand;
import org.aprilsecond.customuicomponents.clock.clockhand.HoursHand;
import org.aprilsecond.customuicomponents.clock.clockhand.MinutesHand;
import org.aprilsecond.customuicomponents.clock.clockhand.SecondsHand;

/**
 * This class represents the Event generated when the hour, minute or second
 * hand is changed.
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class TimeChanged extends EventObject {
    
    /**
     * stores the type of time change
     */
    private int timeChangeType ;
    
    /**
     * stores the clock hand selected 
     */
    private AbstractClockHand clockHand ;
    
    /**
     * null constructor initializes the object source
     */
    public TimeChanged(Object source, AbstractClockHand hand) {
        super(source) ;
        
        // set the clock hand that changed
        setClockHandSelected(hand) ;
        
        // sets the type of time change type
        if(hand instanceof HoursHand) {
            setTimeChangeType(TimeChangeType.HOUR);
        } else if (hand instanceof MinutesHand) {
            setTimeChangeType(TimeChangeType.MINUTE);
        } else if (hand instanceof SecondsHand ) {
            setTimeChangeType(TimeChangeType.SECOND);
        }
    }
    
    /**
     * sets the selected clock hand
     */
    public final void setClockHandSelected(AbstractClockHand hand) {
        clockHand = hand ;
    }
    
    /**
     * gets the selected clock hand
     */
    public AbstractClockHand getClockHandSelected() {
        return clockHand ;
    }
    
    /**
     * sets the type of changed hand type
     */
    public final void setTimeChangeType(int tChangeType) {
        timeChangeType = tChangeType ;
    }
    
    /**
     * gets the type of time change
     */
    public int getTimeChangetype() {
        return timeChangeType ;
    }
}
