package org.aprilsecond.customuicomponents.clock.clockhand;

import java.awt.*;
import javax.swing.JPanel;
import org.aprilsecond.customuicomponents.clock.Clock;

/**
 * This class implements the covering center cone for the clock
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class CenterCone extends JPanel {    
   
    /**
     * stores the radius of the cone
     */
    private final int CONE_RADIUS  = 9 ;
    
    /**
     * stores the color of the cone
     */
    private final Color coneColor = new Color(0, 0, 0) ;
    
    /**
     * null constructor, initializes panel dimensions 
     * and draws the clock arms
     */
    public CenterCone() {
        // set the size of the panel
        setSize(new Dimension(Clock.PANEL_WIDTH, Clock.PANEL_HEIGHT));
        
        // set the panel as transparent
        setOpaque(false);
    }
    
    /**
     * draw the cone
     */
    @Override
    public void paintComponent( Graphics g ) {
        // clone the graphics object
        Graphics2D graphics = (Graphics2D) g ;
        
        // translate the drawing point
        graphics.translate(getWidth()/2 - CONE_RADIUS + 5, 
                getHeight()/2 -  CONE_RADIUS + 11);
        
        // set the  color 
        graphics.setColor(coneColor);
        
        // set the anti alias
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        // draw the component
        graphics.fillOval(0, 0, CONE_RADIUS, CONE_RADIUS);
        
    }
}
