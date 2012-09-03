package org.aprilsecond.customuicomponents.BasePanels;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import javax.swing.JPanel;
import org.aprilsecond.customuicomponents.ActivityPanel.Activity;

/**
 * This class creates the panel that displays the actual details 
 * of the activity being tracked/reminded on the Minimal Tracker
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class MinimalContentPanel extends JPanel {
    
    /**
     * stores the width of the panel
     */
    private final int PANEL_WIDTH = 256 ; 
    
    /** 
     * stores the height of the panel
     */
    private final int PANEL_HEIGHT = 74 ;
    
    /**
     * stores the content for the reminder
     */
    private String contentString = "" ;
    
    /**
     * sets the font for the string
     */
    private final Font CONTENT_STRING_FONT = new Font(Font.SANS_SERIF, 
            Font.PLAIN, 16);
    
    /**
     * sets the color for the string
     */
    private final Color CONTENT_STRING_COLOR 
            = new Color (255, 255, 255);
    
    /**
     * initializes the Minimal Panel
     */
    public MinimalContentPanel() {
        initializePanel();
    }
    
    /**
     * initializes the panel
     */
    public MinimalContentPanel(String content) {
        initializePanel() ;
    }
    
    /**
     * this method initializes the message panel
     */
    private void initializePanel(){
        // sets the dimensions for the JPanel
        setMinimumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setMaximumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));        
    }
    
    /**
     * sets the content string 
     */
    public void setContentString(String content) {
        contentString = content ;
    }
    
    /**
     * gets the content string
     */
    public String getContentString() {
        return contentString ;
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
        // 1. DRAW THE PANEL
        //########################
        createBaseLayer(graphics, baseRect) ;
    }
    
    /**
     * Creates the lower section of the panel with the 
     * time scheduled for the activity and the details of the 
     * tracked/reminded activity
     * @param graphics
     * @param baseRect 
     */
    private void createBaseLayer(Graphics2D graphics, 
            Rectangle2D baseRect) {
        
         // get the insets for the messages section of the panel
        Insets insets = getInsets();        
         
        // create the required variables for the panel
        Rectangle2D.Double bottomMinimalLayer ;
        Color bottomMinimalPanelColor ;
        
        // initialise the variables 
        bottomMinimalLayer = new Rectangle2D.Double(0, 0, getWidth(), 
                getHeight()) ;
        bottomMinimalPanelColor =  new Color(67, 70, 75) ;
        
        // paint the rectangle
        graphics.setColor(bottomMinimalPanelColor);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        
        // add the rectangle to the baseRect
        baseRect.add(bottomMinimalLayer);  
        
        // draw the string 
        if (!"".equals(contentString)) {
            graphics.setColor(CONTENT_STRING_COLOR);
            
            // add the message so that it dynamically adapts to the space 
            // available for it to be drawn on
            // create a LineBreak Measurer instance 
            AttributedString as = new AttributedString(contentString);

            // set the font type             
            as.addAttribute(TextAttribute.FONT, CONTENT_STRING_FONT);
            
            // create required vars
            AttributedCharacterIterator aci = as.getIterator();
            FontRenderContext frc = graphics.getFontRenderContext();
            LineBreakMeasurer lbm = new LineBreakMeasurer(aci, frc);

            // get the width for the component
            float wrappingWidth = getWidth() - 17 - insets.left - insets.right;
            
            // get the left most position for the component
            float x = 17 + insets.left;

            // get the top most position for displayed text
            float y =  12 + insets.top;

            // ensure that the text displayed does not get too 
            // close to the bottom of the activity message
            while (lbm.getPosition() < aci.getEndIndex()) {
                if (y < getHeight() - 20) {
                    TextLayout textLayout = lbm.nextLayout(wrappingWidth);
                    y += textLayout.getAscent();
                    textLayout.draw(graphics, x, y);
                    y += textLayout.getDescent() + textLayout.getLeading();
                    x = 17 + insets.left;
                } else {
                    break;
                }
            }
        }
    }
}
