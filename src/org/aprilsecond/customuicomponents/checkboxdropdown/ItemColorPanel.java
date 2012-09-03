package org.aprilsecond.customuicomponents.checkboxdropdown;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * This class displays a color rectangle at the center 
 * of the panel 
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class ItemColorPanel extends JPanel {
    
    /**
     * stores the displayed color for the component
     */
    private Color panelColor = new Color(0, 0, 0) ;
    
    /**
     * stores the buffer X value
     */
    private final int X_BUFFER_WIDTH = 5 ;
    
    /**
     * stores the buffer Y value
     */
    private final int Y_BUFFER_WIDTH = 5 ;
    
    /**
     * constructor initializes panel
     */
    public ItemColorPanel(Color color) {
        panelColor = color ;
    }
    
    /**
     * draw rect with the color at the center
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(panelColor);

        graphics.fillRect(X_BUFFER_WIDTH, Y_BUFFER_WIDTH,
                getWidth() - (2 * X_BUFFER_WIDTH),
                getHeight() - (2 * Y_BUFFER_WIDTH));
    }
}
