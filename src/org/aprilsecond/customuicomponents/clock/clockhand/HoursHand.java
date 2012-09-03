package org.aprilsecond.customuicomponents.clock.clockhand;

/**
 * This class draws the Hours Hand
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class HoursHand extends AbstractClockHand {
    
    /**
     * constructor sets the clock hand type
     */
    public HoursHand() {
        super(ClockHand.HOUR) ;
    }
    
    /**
     * translate the time to be displayed from 
     * hours to degrees
     */
    @Override
    public void drawHand(int time) {
        // stores the translation time
        int translationTime ;
        
        // make sure that the hand is bound
        if( time < 0 || time > 12 ) {
            throw new RuntimeException("Clock time for hour hand incorrect") ;
        }
        
        // translate the time from hours 
        // to degrees 
        translationTime = (int) (( time/12.0 ) * 360) ;
        
        // set the translation time
        setTranslationAngle(translationTime);
        
        // draw the clock hand with the translated
        // value
        super.drawHand(translationTime);
    }
}
