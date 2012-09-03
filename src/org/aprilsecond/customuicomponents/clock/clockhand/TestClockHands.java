package org.aprilsecond.customuicomponents.clock.clockhand;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class test the drawing of the clock hands
 * @author Reagan
 */
public class TestClockHands {
    
    public static void main(String [] args) {
        
        Runnable windowDisplay = new Runnable() {
            
            @Override
            public void run() {
                JFrame frame = new JFrame("Test clock Hands") ;
                frame.setSize(new Dimension(400,400));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
                
                JPanel p1 = new JPanel() ;
                p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
                
                // Test for the Hours Hand
                AbstractClockHand hourHand = new HoursHand () ;
                AbstractClockHand minuteHand = new MinutesHand () ;
                AbstractClockHand secondHand = new SecondsHand () ;
                
                hourHand.drawHand(7);
                minuteHand.drawHand(45);
                secondHand.drawHand(33); // draw the clock at the 3
                                    // 'O'clock position
                
                // add the clock hands to the main panel                
                p1.add(hourHand) ;
                p1.add(minuteHand) ;
                p1.add(secondHand) ;
                
                // add the clock hands to the frame
                frame.add(p1);
                
                // display the frame
                frame.setVisible(true);
            }
        };
        
        EventQueue.invokeLater(windowDisplay);
    }
}
