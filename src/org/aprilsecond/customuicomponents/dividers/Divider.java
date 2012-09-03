package org.aprilsecond.customuicomponents.dividers;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 * This class implements a divider that can be used to 
 * separate the various sections of the panel
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class Divider extends JPanel {

    /**
     * stores the vertical orientation of the 
     * Divider
     */
    public static final int VERTICAL = JSeparator.VERTICAL ;
    
    /**
     * stores the horizontal orientation of the
     * Divider
     */
    public static final int HORIZONTAL = JSeparator.HORIZONTAL ;
    
    /**
     * stores the color of the dots
     */
    private Color dotsColors = Color.WHITE ;
    
    /**
     * stores the orientation of the Divider
     */
    private int orientation ; 
    
    /**
     * initializes the orientation & length for the Divider
     * @param orientation
     * @param dimension The dimensions for the JSeparator Panel
     */
    public Divider(int _orientation, Dimension dimension) {
        // initialise the orientation
        setSize(dimension);  
        setPreferredSize(dimension) ;
        setMinimumSize(dimension);
        
        // set the orientation
        orientation = _orientation ;
        
        // make sure that the component is opaque
        setOpaque(false);
    }
    
    /**
     * draws the JSeparator using dotted lines rather than 
     * a straight line
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g ) {
        
        // create the Graphics2D object
        Graphics2D graphics = (Graphics2D) g ;
        
        // cater for insets/border
        int width = getWidth() - getInsets().left - getInsets().right ;
        int height = getHeight() - getInsets().top - getInsets().bottom ;
        
        // set antiAias
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON) ;
        
        // draw the dots for the component
        graphics.setColor(dotsColors);
        
        // draw the ovals vertically
        if ( orientation == Divider.VERTICAL ) {
            // initialise the counter
            int widthCounter = 0 ;
            
            // get the height location 
            int drawHeight = height / 2 ;
            
            while ( widthCounter < width) {
                // draw the dot at the desired point
                graphics.fillOval(widthCounter, drawHeight, 2, 2);
                
                // increment the location for the drawn oval
                widthCounter += 5 ;
            }            
        }
        
        // draw the ovals horizontally
        if ( orientation == Divider.HORIZONTAL ) {
            // initialise the counter
            int heightCounter = 0 ;
            
            // get the height location 
            int drawWidth = width / 2 ;
            
            while (heightCounter < height) {
                // draw the dot at the desired spot
                graphics.fillOval(drawWidth, heightCounter, 2, 2);
                
                // increment the location for the drawn oval
                heightCounter += 5 ;
            }            
        }
    }
    
    // Test
    public static void main (String[] args) {
        Runnable windowDisplay = new Runnable () {

            @Override
            public void run() {
                JFrame f = new JFrame("Test Divider") ;
                f.setSize(300, 400);                
                
                JPanel mainPanel = new JPanel() ;
                mainPanel.setPreferredSize(new Dimension(300, 400));
                mainPanel.setBackground(Color.BLUE);
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                
                JPanel topPanel = new JPanel() ;
                topPanel.setPreferredSize(new Dimension(300, 100));
                topPanel.setBackground(Color.RED);
                
                Divider d = new Divider(Divider.VERTICAL, new Dimension(300, 2)) ;
                
                JPanel bottomPanel = new JPanel() ;
                bottomPanel.setPreferredSize(new Dimension(300, 300));
                bottomPanel.setBackground(Color.YELLOW);
                
                mainPanel.add(topPanel) ;
                mainPanel.add(d) ;
                mainPanel.add(bottomPanel) ;
                                
                f.add(mainPanel) ;
                
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }            
        }; 
        
        EventQueue.invokeLater(windowDisplay);
    }
}
