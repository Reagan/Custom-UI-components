package org.aprilsecond.customuicomponents.List.ListItem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Calendar;
import javax.swing.JFrame;

/**
 * Tests the calendar list item
 * @author Reagan
 */
public class TestCalendarListItem {
    public static void main(String[] args) {
        Runnable window = new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame("Test Calendar component") ;
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(new Dimension(375, 104));
                frame.setLayout(new BorderLayout());
                
                Calendar today = Calendar.getInstance() ;
                String message = "Visit to Auntie Macharia's House..." ;
                CalendarListItem cItem = new CalendarListItem(today, message, 
                        375, 64) ;
                
                frame.add(cItem, BorderLayout.CENTER) ;
                frame.setVisible(true);
            }
        };

        EventQueue.invokeLater(window);
    }
}
