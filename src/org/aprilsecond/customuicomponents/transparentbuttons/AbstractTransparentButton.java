package org.aprilsecond.customuicomponents.transparentbuttons;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This creates a default implementation for a transparent button
 * @author Reagan Mbitiru
 */
public abstract class AbstractTransparentButton
    extends  JButton implements MouseListener {        
    
    /**
     * stores the letter that is displayed and which is activated
     * when the button is selected
     */
    public String displayLetter ;
    
    /**
     * stores the close button
     */
    public static final String CLOSE_BUTTON = "X" ;
    
    
    /**
     * stores the minimize button
     */
    public static final String MINIMIZE_BUTTON = "-" ;
    
    /**
     * stores the frame to receive the objects
     */
    public JFrame appFrame ;
    
    /**
     * stores the button width
     */
    public final int BUTTON_WIDTH = 20 ;
    
    /**
     * stores the button height
     */
    public final int BUTTON_HEIGHT = 20 ; 
    
    /**
     * stores state on whether the mouse is hovered
     */
    private boolean hovered = false ;
    
    /**
     * stores the color of text when mouse has not hovered
     */
    private final Color HOVER_COLOR = Color.WHITE ;
    
    /**
     * stores the color of the text when the mouse is 
     * not hovered on
     */
    private final Color UNHOVER_COLOR = Color.GRAY ;
    
    /**
     * constructor initializes variables
     * @param displayedLetter 
     */
    public AbstractTransparentButton(String displayedLetter,
            JFrame frame ){
        
        // initialise the displayed letter
        displayLetter = displayedLetter ;
        
        // initialise the frame to receive the events
        appFrame = frame ;
        
        // initializes the action listener
        addActionListener(performAction());
        
        // sets the mouselistener
        addMouseListener(this);
    
        // set the dimensions for the button
        setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT)) ;
        
        // remove the drawn border
        setBorder(null);
        
        // set the background as transparent
        setOpaque(false);
    }
    
    /**
     * overrides the way the button is drawn to display 
     * the button as desired
     */
    @Override
    public void paintComponent(Graphics g) {
        // clone the Graphics Object
        Graphics2D graphics = (Graphics2D) g ;
        
        // draw the set letter to the panel
        Rectangle boundingRect = graphics.getClipBounds() ;
        
        // cater for insets/border
        int width = getWidth() - getInsets().left - getInsets().right ;
        int height = getHeight() - getInsets().top - getInsets().bottom ;
        
        // draw the text on the JButton
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                 RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 
                15));
        Color fontColor = (hovered ? UNHOVER_COLOR : HOVER_COLOR ) ;
        graphics.setColor(fontColor);
        graphics.drawString(displayLetter, width/2 - 7, 
                height/2 + 5);
    }
        
    /**
     * sets the behavior of the button
     */
    public abstract ActionListener performAction() ;
    
    /**
     * sets the behavior when the cursor is on top 
     * of the icon
     */
    @Override
    public void mouseEntered(MouseEvent event) {
        hovered = true;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent event) {
        hovered = false;
        repaint();
    }
            
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}
