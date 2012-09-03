package org.aprilsecond.customuicomponents.clock.clockhand;

import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import org.aprilsecond.customuicomponents.clock.Clock;
import org.aprilsecond.customuicomponents.clock.Time;
import org.aprilsecond.customuicomponents.clock.TimeChanged;
import org.aprilsecond.customuicomponents.clock.TimeChangedListener;

/**
 * This class implements a clock hand
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public abstract class AbstractClockHand extends JPanel
    implements ClockHand {

    /**
     * stores the translation angle for the hand
     */
    private int translationAngle = 0 ; 
    
    /**
     * stores the type of hand currently being implemented
     */
    private int clockHandType ;
    
    /**
     * stores the dimensions for the clock hand drawn
     */
    private Dimension handDimensions ;
    
    /**
     * stores the color of the clock hand drawn
     */
    private Color handColor ;
    
    /**
     * stores the EventListener list
     */
    private EventListenerList listenerList ;    
    
    
    /**
     * constructor initializes the clock hand type
     * @param _clockHandType 
     */
    public AbstractClockHand(int _clockHandType) {
        // set the size of each of the panels
        setSize(new Dimension(Clock.PANEL_WIDTH, Clock.PANEL_HEIGHT));
        
        // ensure that the panel is transparent
        setOpaque(false);
        
        // set the clock hand type
        clockHandType = _clockHandType ;
        
        // set the dimensions and color of the hand drawn
        switch(clockHandType) {
            case(ClockHand.HOUR) :
                handDimensions = new Dimension(33, 2) ;
                handColor = new Color(124, 123, 128) ;
                break;
                
            case (ClockHand.MINUTE) :
                handDimensions = new Dimension(45, 2) ;
                handColor = new Color(166, 167, 171) ;
                break ;
                
            case (ClockHand.SECOND) :
                handDimensions = new Dimension(48, 1) ;
                handColor = new Color(179, 6, 29) ;
                break ;
                
            default:
                throw new RuntimeException("Error with the "
                        + "supplied clock hand type") ;
        }
    }
    
    /**
     * implements the drawing of the hand depending on the
     * type of hand that is drawn. The panel is drawn translated depending 
     * on the time of the clock
     * @param time 
     */
    @Override
    public void drawHand(int time) {          
        // repaint the component
        repaint();
        
        // stores the time 
        // create time changed event 
        TimeChanged evt = new TimeChanged(this, this) ;
        
        // fire event changed object
        fireTimeChanged(evt) ;
    }   
    
    /**
     * fires an event that the time for the clock hand has 
     * changed
     */
    private void fireTimeChanged(TimeChanged evt) {
         Object[] listeners = listenerList.getListenerList();
            for (int i = 0; i < listeners.length; i = i+2) 
            {
                    if (listeners[i] == TimeChangedListener.class) 
                    {
                            ((TimeChangedListener) listeners[i+1]).timeChanged(evt);
                    }
            }
    }
    
    /**
     * adds an EventListenerList to the component
     */
    public void addEventListenerList(EventListenerList list) {
        listenerList = list ;
    }
    
    /**
     * sets the translation angle
     */
    public void setTranslationAngle(int _translationAngle) {
        translationAngle = _translationAngle ;
    }
    
    /**
     * gets the translation angle
     */
    public int getTranslationAngle() {
        return translationAngle  ;
    }
    
    /**
     * Draws the hand depending on the type of hand to be 
     * displayed
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        
        // clone the graphics object
        Graphics2D graphics = (Graphics2D) g ;       
        
        // cater for the insets/border
        int width = getWidth() - getInsets().left - getInsets().right ;
        int height = getHeight() - getInsets().top - getInsets().bottom ;
        
        // draw the rectangle with the dimensions 
        // for the clock hand
        if (handDimensions != null) {
            
            // capture the initial transform
            AffineTransform initialTransform = graphics.getTransform() ;
            
            // translate the graphics object
            // to the center of the clock object that is drawn
            graphics.translate(width/2 - 1 ,
                    height/2 + 7) ;
            
            // 90 degrees are subtracted to compensate for the starting off 
            // point for radian measurements for Java2D            
            graphics.rotate(Math.toRadians(getTranslationAngle()-90), 
                    handDimensions.height, 0) ;
            
            // set antialiasing on
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            // get the color of the hand
            graphics.setColor(handColor);
            
            // fill the shape
            graphics.fillRect(0, 0, 
                    (int) handDimensions.getWidth(), 
                    (int) handDimensions.getHeight());
            
            // set the transform to initial values
            graphics.setTransform(initialTransform);
        }        
    }
}
