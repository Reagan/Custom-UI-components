package org.aprilsecond.customuicomponents.clock;

import java.util.EventListener;

/**
 * This class creates a TimeChangedListener when the time displayed on
 * the clock hand changes
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public interface TimeChangedListener extends EventListener {
    
    public void timeChanged(TimeChanged evt) ;
}
