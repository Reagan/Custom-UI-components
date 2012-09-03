package org.aprilsecond.customuicomponents.BasePanels;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * This class creates the top panel with information on the 
 * whether the displayed information is tracking/reminder 
 * information
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class TitlePanel extends JPanel {
    
    /**
     * stores the width of the panel
     */
    private final int WIDTH = 256 ; 
    
    /** 
     * stores the height of the panel
     */
    private final int HEIGHT = 28 ;
    
    /**
     * stores the displayed string
     */
    private String messageString = "" ;
    
    /**
     * stores the title font
     */
    private Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 
            16) ;
    
    /**
     * stores the font color 
     */
    private Color titleColor = new Color(51, 51, 51) ;
    
    /**
     * initializes the Minimal Panel
     */
    public TitlePanel() {
       // initializes the panel
        initializePanel();
    }
    
    /**
     * constructor initializes a message for display
     * @param title The title to be displayed
     */
    public TitlePanel(String title) {
        messageString = title ;
        
        // initialize the panel
        initializePanel();
    }
    
    /**
     * initializes the panel
     */
    private void initializePanel() {
         // sets the dimensions for the JPanel
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
    }
    
    /**
     * sets the message string for the title pane
     */
    public void setTitle(String title) {
        messageString = title ;
    }
    
    /**
     * gets the message string
     */
    public String getTitle() {
        return messageString ;
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
        
        // set antialiasing
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        //########################
        // 1. DRAWS THE PANEL
        //########################
        createBaseLayer(graphics, baseRect) ;
    }

   
    private void createBaseLayer(Graphics2D graphics, 
            Rectangle2D baseRect) {
        
        // create the required variables for the panel
        Rectangle2D.Double topMinimalLayer ;
        RadialGradientPaint topLayerGradient ;
        Color startColor ;
        Color stopColor ;
        
        // initialise the variables 
        topMinimalLayer = new Rectangle2D.Double(0, 0, getWidth(), 
                getHeight()) ;
        stopColor =  new Color(153, 191, 7) ;
        startColor = new Color(198, 231, 62) ;
        topLayerGradient = new RadialGradientPaint((int) getWidth()/2 , 
                (int) getHeight(),  // half of the height of the top panel
                (int) getWidth()/2, 
                new float[] {0.0f, 1.0f}, 
                new Color[] { startColor, stopColor} );
        
        // paint the rectangle
        graphics.setPaint(topLayerGradient);
        graphics.fillRect(0, 0, (int) getWidth(), 
                (int) getWidth());
        
        // add the rectangle to the baseRect
        baseRect.add(topMinimalLayer);     
        
        // paint the reminder string
        if (!"".equals(messageString)) {
            graphics.setColor(titleColor);
            graphics.setFont(titleFont);
            
            // draw the string
            graphics.drawString(messageString, 20, 21);
        }
    }
}
