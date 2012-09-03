package org.aprilsecond.customuicomponents.ContainerPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * This class creates the panel for the minimal 
 * panel that displays the tracker messages or reminders. 
 * It consists of a top panel that displays what 
 * kind of a message is being displayed and a lower section
 * with the details of the activity
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class MinimalBaseJPanel extends JPanel {
    
    /**
     * stores the width of the panel
     */
    private final int WIDTH = 256 ; 
    
    /** 
     * stores the height of the panel
     */
    private final int HEIGHT = 102 ;
    
    /**
     * initializes the Minimal Panel
     */
    public MinimalBaseJPanel() {
        // sets the dimensions for the JPanel
        setSize(new Dimension(WIDTH, HEIGHT));
    }
    
    /**
     * Customizes the look of the panel
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        
        // create a Graphics2D obj
        Graphics2D graphics = (Graphics2D) g ;
        Rectangle2D baseRect = graphics.getClipBounds();
        
        //##########################
        // 1. CREATE THE BASE PANEL
        //##########################
        createBasePanel(graphics, baseRect) ;        
    }

    /**
     * Stores the main background panel for the MinimalPanel UI
     * @param graphics
     * @param baseRect 
     */
    private void createBasePanel(Graphics2D graphics, 
            Rectangle2D baseRect) {
        
        // create the required variables
        Rectangle2D.Double layer1 ;
        Color baseColor ;
        
        // initialise and apply the color to the rect
        layer1 = new Rectangle2D.Double(0, 0, baseRect.getWidth(), 
                baseRect.getHeight()) ;
        baseColor = new Color(0, 0, 0) ;
        
        // apply to the graphics object
        graphics.setColor(baseColor);
        graphics.fillRect(0, 0, (int) baseRect.getWidth(), 
                (int) baseRect.getHeight());
        
        // add to the main rectangle
        baseRect.add(layer1);
    }
}
