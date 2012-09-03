package org.aprilsecond.customuicomponents.transparentbuttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Tests the behavior of the transparent buttons
 * @author reagan
 */
public class TestTransparentButtons {
    public static void main(String[] args) {
        Runnable run = new Runnable() {

            @Override
            public void run() {
                // initialise the frame
                JFrame frame = new JFrame("Test Transparent Buttons") ;
                frame.setSize(new Dimension(200, 100));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                // create the main panel
                JPanel p1 = new JPanel() ;
                p1.setPreferredSize(new Dimension(200, 100));
                p1.setBackground(Color.RED);
                
                // add the transparent button
                CloseButton closeButton = new CloseButton(frame) ;
                p1.add(closeButton) ;
                
                // add the minimise button
                MinimiseButton minimiseButton = new MinimiseButton(frame) ;
                p1.add(minimiseButton) ;
                
                // add the panel to the frame and display the frame
                frame.add(p1) ;
                frame.setVisible(true);
            }
            
        };
        
        EventQueue.invokeLater(run);
    }
}
