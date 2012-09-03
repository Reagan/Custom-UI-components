package org.aprilsecond.customuicomponents.BasePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * This is the panel that holds the application menu
 * for the Base Window panel
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class MenuPanel extends JPanel {
     /**
     * width of the panel
     */
    private final int PANEL_WIDTH = 552 ;
    
    /** 
     * height of the panel
     */
    private final int PANEL_HEIGHT = 40 ;        
    
    /**
     * stores the background color for the panel
     */
    private final Color BG_COLOR = new Color(67, 70, 75) ;
    
    public MenuPanel() {
        // set the dimensions for the JPanel
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setMaximumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT)) ;
        setMinimumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT)) ;
    }
    
    /**
     * customizes the look of the panel
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {       
        
        // clone the graphics object
        Graphics2D graphics = (Graphics2D) g ;
               
        //###################
        // 1. DRAW THE PANEL
        // ##################
        createBaseLayer(graphics) ;                
    }    
   
    /**
     * This method creates the third layer of the Base Panel 
     * UI component
     * @param graphics
     * @param baseRect 
     */
    private void createBaseLayer(Graphics2D graphics) {
        // create required vars
        Color layer3Bg ;
        
        // initialise the variables;
        layer3Bg = BG_COLOR ;
        
        // apply the layer to th graphics object
        graphics.setColor(layer3Bg);
        graphics.fillRect(0, 0, (int) getWidth() ,
               (int) getHeight());
    }               
}