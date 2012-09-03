package org.aprilsecond.customuicomponents.clock;

import java.util.EventListener;

/**
 * This interface creates a Clock selected listener that is
 * selected when the clock is clicked
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public interface ClockSelectedListener extends EventListener {
    
    public void clockSelected(ClockSelected evt) ;
}
