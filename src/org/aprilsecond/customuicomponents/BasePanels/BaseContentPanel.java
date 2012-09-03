package org.aprilsecond.customuicomponents.BasePanels;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * This is the panel that stores the content displayed
 * in each of the panes in the Base Panel
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class BaseContentPanel extends JPanel {

    /**
     * width of the panel
     */
    private final int PANE_WIDTH = 552 ;
    
    /** 
     * height of the panel
     */
    private final int PANE_HEIGHT = 280 ;        
    
    public BaseContentPanel() {
        // set the dimensions for the JPanel
        setMinimumSize(new Dimension(PANE_WIDTH, PANE_HEIGHT));
        setMaximumSize(new Dimension(PANE_WIDTH, PANE_HEIGHT));
    }
    
    /**
     * customizes the look of the panel
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {       
        
        // clone the graphics object
        Graphics2D graphics = (Graphics2D) g ;
        
        
        // fill the rectangle with the panel 
        // with desired dimensions
        Rectangle2D baseRect = graphics.getClipBounds() ;
                
        //####################
        // 1. DRAW THE PANEL
        // ###################
        createBaseLayer(graphics, baseRect) ;        
    }
    
    /**
     * Creates the fourth layer of the Base Panel 
     * component UI
     * @param graphics
     * @param baseRect 
     */
    private void createBaseLayer(Graphics2D graphics, 
            Rectangle2D baseRect) {
        
        // cater for insets/border
        int width = getWidth() - getInsets().left - getInsets().right ;
        int height = getHeight() - getInsets().top - getInsets().bottom ;
         
        // create the required variables
        Rectangle2D.Double layer ;
        GradientPaint layerGradient ;
        Color startColor ;
        Color stopColor ;
        
        // initialise the variables
        layer = new Rectangle2D.Double(0, 0, width, 
                height) ;
        startColor = new Color(119, 142, 164);
        stopColor = new Color(87, 101, 112);
        layerGradient = new GradientPaint(0, 0, startColor, 
                0, height, stopColor) ;
        
        // apply the gradient to the rectangle and add to
        // the base Rect
        graphics.setPaint(layerGradient);
        graphics.fillRect(0, 0, width, height);
        
        // add the layer to the main rect
        baseRect.add(layer);
    }        
}
