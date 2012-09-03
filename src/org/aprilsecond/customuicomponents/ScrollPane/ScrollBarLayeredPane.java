package org.aprilsecond.customuicomponents.ScrollPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLayeredPane;

/**
 * This class is the layered pane that displays the custom 
 * glass panes when the mouse hovers over the component
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class ScrollBarLayeredPane extends JLayeredPane {
    
    /**
     * null constructor initializes the panel
     */
    public ScrollBarLayeredPane(Dimension scrollPaneDimensions) {
        // set the dimensions for the panel
        setPreferredSize(scrollPaneDimensions);
        
        // set transparent
        setOpaque(false);
    }
    
    /**
     * display the JScrollPane
     */
    @Override
    public void paintComponent(Graphics g) {
        // clone the Graphics object
        Graphics2D graphics = (Graphics2D) g ;        
                
        // set the color
        graphics.setColor(new Color(10, 15, 45)) ;
        
        // fill the rectangle
        graphics.drawRect(0, 0, getWidth(), 
                getHeight());
    }
}
