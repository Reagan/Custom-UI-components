package org.aprilsecond.customuicomponents.ActivityPanel;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 * Tests the display of the messages panel
 * @author reagan mbitiru <reaganmbitiru@gmail.com>
 */
public class TestActivitiesPanel {
    public static void main(String[] args) {
        Runnable displayWindow = new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame() ;
                frame.setSize(new Dimension (200, 158));
                
                // add the characteristics for Messages
                // set start & stop time
                Calendar startTime = new GregorianCalendar(2012, 1, 12, 11, 25) ;
                
                Calendar stopTime = new GregorianCalendar(2012, 1, 12, 12, 25);
                
                Calendar startTime1 = new GregorianCalendar(2012, 1, 12, 12, 30) ;
                
                Calendar stopTime1 = new GregorianCalendar(2012, 1, 12, 12, 45);
                
                // set title
                String title = "Currently" ;
                
                // create an activity
                Activity a1 = new Activity(Activity.HIGHLIGHTED_ACTIVITY, 
                        startTime, stopTime, 
                        "First Activity is the best kind to have and "
                        + "here are some of the type of messages that can be delivered") ;
                
                Activity a2 = new Activity(Activity.TITLE_ACTIVITY, 
                        startTime1, stopTime1, 
                        "Second Activity") ;
                
                // add the activity to the model
                ActivityMessage message = new ActivityMessage(a1) ;
                ActivityMessage message2 = new ActivityMessage(a2) ;
                message2.setActivityTitle(title);
                
                // create the activity Panel
                ActivityPanel panel = new ActivityPanel() ;
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                
                // add the model to the  
                panel.addMessage(message) ;
                panel.addMessage(message2) ;
                
                // add the activity to the panel
                frame.add(panel) ;
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                // display the panel
                frame.setVisible(true);
            }
        };

        EventQueue.invokeLater(displayWindow) ;
    }
}
