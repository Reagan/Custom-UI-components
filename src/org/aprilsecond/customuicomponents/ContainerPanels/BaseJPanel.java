package org.aprilsecond.customuicomponents.ContainerPanels;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * This class is responsible for the display of the base panel that 
 * forms the background of the main application
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class BaseJPanel extends JPanel  {

    /**
     * width of the panel
     */
    private final int WIDTH = 552 ;
    
    /** 
     * height of the panel
     */
    private final int HEIGHT = 335 ;        
    
    /**
     * stores state on whether or not the component
     * is draggable
     */
    private boolean isDraggable = false ;
    
    /**
     * sets the location of the component
     */
    public Point currentDragLocation = new Point() ;    
    
    /**
     * null constructor initializes component
     */
    public BaseJPanel() {
        // set the dimensions for the JPanel
        setSize(WIDTH, HEIGHT);
        
        // set layout for the container frame
        setLayout(new BorderLayout());
               
        // create the border for the chrome panel
        setBorder(new EmptyBorder(7, 7, 7, 7));
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
        
        // ####################
        // 1. ADD THE FIRST BASE 
        //#####################
        createFirstBaseLayer(graphics);
        
        //########################
        // 2. ADD THE SECOND BASE
        // #######################
        createSecondBaseLayer(graphics) ;    
    }
    
    /**
     * This method creates the first layer for the Base Panel
     * UI component
     * @param g 
     */
    private void createFirstBaseLayer(Graphics2D graphics) {
     
        // set the color for the base panel and apply it
        Color basePanelColor = new Color(12, 12, 12) ;
        graphics.setColor(basePanelColor);
        graphics.fillRect(0, 0, (int) getWidth(), 
                (int) getHeight());           
    }
    
    /**
     * This method creates the second layer for the Base Panel
     * UI component
     * @param graphics
     * @param baseRect 
     */
    private void createSecondBaseLayer(Graphics2D graphics) {
        // create required variables
        LinearGradientPaint secondLayerGradient ;
        
        
        // create the linear gradient
        secondLayerGradient = new LinearGradientPaint(0.0f, 0.0f, 0.0f, (float) getHeight(),
                new float[] { 0.0f, 0.13f, 0.36f,
                              0.53f, 0.69f, 0.84f, 1.0f }, 
                new Color[] { new Color(194, 194, 194),
                              new Color(255, 255, 255),
                              new Color(255, 254, 255),
                              new Color(18,17,22),
                              new Color(112, 110, 111),
                              new Color(112, 110, 111), 
                              new Color(61, 61, 61)}, 
                MultipleGradientPaint.CycleMethod.NO_CYCLE);
        
        // apply linear gradient to created rectangle
        graphics.setPaint(secondLayerGradient);
        graphics.fillRect(2, 2, (int) getWidth() - 4,
                (int) getHeight() - 4);
        
       
    }
}
