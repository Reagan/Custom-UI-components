package org.aprilsecond.customuicomponents.ActivityPanel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import javax.swing.JPanel;

/**
 * This panel draws the base activity panel used on the activities page
 * of the activities page of the application. It combines the TimePanel and 
 * the MessagesPanel
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class ActivityPanel extends JPanel {
    
    // ********************
    // * PANEL DIMENSIONS *
    // ********************
    /**
     * stores the width of the panel
     */
    private int PANEL_WIDTH = 227 ;
    
    /**
     * stores the height of the panel
     */
    private int PANEL_HEIGHT = 58 ;
    
    // ****** END MAIN PANEL DIMENSIONS **********
    
    // *********************************
    // * VARIABLES TIME SECTION PANEL  *
    // *********************************
    
    /**
     *
     * stores the min width of the JPanel
     */
    private int TIME_PANEL_WIDTH = 42 ;
    
    /**
     * stores the min height of the JPanel
     */
    private int TIME_PANEL_HEIGHT = 58 ;
    
    /**
     * stores the gradient for the JPanel
     */
    private RadialGradientPaint timeRadialGradientPaint ;
    
    /**
     * stores the start color for the  gradient
     */
    private Color timeRadialStartGradientColor 
            = new Color(153, 191, 7)  ;
    
    /**
     * stops the stop color for the gradient
     */
    private Color timeRadialStopGradientColor 
            = new Color(198, 231, 62) ;
   
    /**
     * stores the gradient located on the right
     * side of the panel
     */
    private LinearGradientPaint edgeGradient ;
    
    /**
     * stores the start color for the gradient on the 
     * left side of the panel
     */
    // private Color edgeStartColor = new Color(143, 167, 45) ;
    private Color edgeStartColor = new Color(183, 218, 43) ;
    
    /**
     * stores the end color for thee gradient on 
     * the right side of the panel
     */
    private Color edgeStopColor = new Color(190, 222, 60) ;

    // ***********END TIME PANEL DIMENSIONS ****************
    
    // ************************************
    // * MESSAGES PANEL SECTION VARIABLES *
    // ************************************
    
    /**
     * stores the width of the panel
     */
    private static final int MESSAGES_PANEL_WIDTH = 185 ;
    
    /**
     * stores the height for the panel
     */
    private static final int MESSAGES_PANEL_HEIGHT = 58 ;
    
    /**
     * stores the background color for the panel
     */
    private static final Color MESSAGES_BG_COLOR = Color.WHITE ; 
    
    // ***** END MESSAGES PANEL SECTION VARS ***************
    
    /** 
     * null constructor initializes the dimensions and 
     * opaque state of the component
     */
    public  ActivityPanel() {
        // set the preferred size for the panel
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT) ) ;            
        
        // set the panel to be transparent
        setOpaque(false);
        
        // set the layout type
        setLayout(new BorderLayout());
    }
    
    /**
     * adds a message to the panel
     */
    public void addMessage(ActivityMessage message) {
        add(message, BorderLayout.CENTER) ;
    }
    
    /**
     * draw the panel
     */
    @Override
    public void paintComponent(Graphics g) {
        // clone the Graphics object
        Graphics2D graphics = (Graphics2D) g ;
        
        // **************************************
        // * Draw the TIME SECTION of the panel *
        // **************************************
        
        // accomodate for the insets
        int height = getHeight() - getInsets().top - getInsets().bottom;
        int width = getWidth() - getInsets().left - getInsets().right ;
        
        // draw section with the linear gradient
         Rectangle timeSectionRectangle = new Rectangle(0, 0, 
                TIME_PANEL_WIDTH, height) ;         
          
        // draw the section with the linear gradient
        // get the current transform
        AffineTransform oldTransform = graphics.getTransform() ;
        
        // apply the gradient
        edgeGradient = new LinearGradientPaint(0.0f, 0.0f, 0.0f, 10,
                new float[] {0.0f, 1.0f},
                new Color[] {edgeStartColor, edgeStopColor}) ;
        
        // rotate the transformation 
        graphics.translate(TIME_PANEL_WIDTH, 0);
        graphics.rotate(Math.toRadians(90));                
                
        // create the gradient on the right side of the panel
        graphics.setPaint(edgeGradient);
        graphics.fillRect(0 , 0, (int) timeSectionRectangle.getHeight(), 
                4) ;
        
        // resume the earlier gradient & transform
        graphics.setTransform(oldTransform);        
        
        // draw the section with the Radial Gradient
        timeRadialGradientPaint = new RadialGradientPaint(new 
                Point2D.Double(timeSectionRectangle.width/2,
                timeSectionRectangle.height/2), 
                timeSectionRectangle.height/2,
                new float [] {0.0f,1.0f},
                new Color [] {timeRadialStartGradientColor, 
                    timeRadialStopGradientColor}) ;
        
        // set a good alpha composite
        AlphaComposite oldComposite = (AlphaComposite) graphics.getComposite() ;
        
        // create new Alpha Composite
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        
        // draw the gradient on the panel
        graphics.setPaint(timeRadialGradientPaint);
        graphics.fillRect(0, 0, timeSectionRectangle.width, 
                timeSectionRectangle.height);
      
        // resume old composite
        graphics.setComposite(oldComposite);
                
        // ******** END DRAWING TIME PANEL SECTION *********
        
        // ******************************************
        // * Draw the MESSAGES SECTION of the panel *
        // ******************************************
        
        Rectangle messagesSectionRectangle = new Rectangle(TIME_PANEL_WIDTH, 0, 
                (int) width - TIME_PANEL_WIDTH, 
                (int) height) ;
    
        graphics.setColor(MESSAGES_BG_COLOR);
        graphics.fillRect(TIME_PANEL_WIDTH, 0, messagesSectionRectangle.width, 
                messagesSectionRectangle.height);   
        
        // ****** END MESSAGES SECTION **************
    }
}

