package org.aprilsecond.customuicomponents.ScrollPane;

import java.awt.*;
import javax.swing.JPanel;

/**
 * This class forms the base for all the 
 * list elements that are displayed
 * on the component
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class ScrollPaneBase extends JPanel  {
    
    /**
     * stores the color for the background
     */
    private Color bgColor = new Color(67, 70, 75) ;
    
    /**
     * null constructor initializes the 
     * JPanel
     */
    public ScrollPaneBase(Dimension panelDimensions) {
        // set the bounds for the component
        setBounds(0, 0, 
                panelDimensions.width, panelDimensions.height) ;
        
        // set the panel as transparent
        setOpaque(true);
    }

   /**
    * draws the base panel for the component
    */
    @Override
    public void paintComponent(Graphics g) {
        // clone the Graphics object
        Graphics2D graphics = (Graphics2D) g ;
        
        // fill the background for the component
        graphics.setColor(bgColor);
        graphics.fillRect(0, 0, getWidth(), 
                getHeight());
    }
}
