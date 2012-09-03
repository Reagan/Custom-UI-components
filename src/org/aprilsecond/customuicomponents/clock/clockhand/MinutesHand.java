package org.aprilsecond.customuicomponents.clock.clockhand;

/**
 * This class draws the Minutes Hand
 * @author Reagan
 */
public class MinutesHand extends AbstractClockHand {
    
    /**
     * constructor sets the Minute Hand
     */
    public MinutesHand() {
        super(ClockHand.MINUTE) ;
    }
    
     /**
     * translate the time to be displayed from 
     * minutes to degrees
     */
    @Override
    public void drawHand(int time) {
        // stores the translation time
        int translationTime ;
        
        // make sure that the hand is bound
        if( time < 0 || time > 60 ) {
            throw new RuntimeException("Clock time for minute hand incorrect") ;
        }
        
        // translate the time from hours 
        // to degrees 
        translationTime = (int) (( time/60.0 ) * 360) ;
        
        // set the translation time
        setTranslationAngle(translationTime);
        
        // draw the clock hand with the translated
        // value
        super.drawHand(translationTime);
    }
}
