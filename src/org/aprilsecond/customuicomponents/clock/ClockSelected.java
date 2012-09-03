package org.aprilsecond.customuicomponents.clock;

import java.util.EventObject;

/**
 * This class represents the event object created when the clock 
 * is selected
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>s
 */
public class ClockSelected extends EventObject {
    
    /**
     * stores an instance of the selected clock
     */
    private Clock clock ;
    
    /**
     * constructor initializes object source
     */
    public ClockSelected(Object source, Clock clock) {
        super(source) ;
        setSelectedClock(clock) ;
    }
    
    /**
     * sets the selected clock
     */
    public final void setSelectedClock(Clock c) {
        clock = c ;
    }
    
    /**
     * gets the selected clock
     */
    public Clock getSelectedClock() {
        return clock ;
    }    
}
