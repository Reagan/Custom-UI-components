package org.aprilsecond.customuicomponents.TimeTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.aprilsecond.customuicomponents.ScrollPane.ItemSelected;
import org.aprilsecond.customuicomponents.ScrollPane.ItemSelectedListener;
import org.aprilsecond.customuicomponents.ScrollPane.ScrollPane;

/**
 * This class tests the display of a timetable component
 * @author Reagan
 */
public class TestTimetable {
    public static void main(String [] args) {
        Runnable run = new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame("Timetable Test") ;
                frame.getContentPane().setPreferredSize(new Dimension(350, 520));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
                
                final ArrayList<Activity> components = new ArrayList<Activity> () ;
                // 10:45 AM 12th May 2012
                Calendar startTime1 = new GregorianCalendar(2012, 5, 12, 10, 45) ;
                
                // 12:45PM 12th May 2012 
                Calendar stopTime1 = new GregorianCalendar(2012, 5, 12, 12, 45) ;
                
                // 1:55PM 12th May 2012
                Calendar startTime2 = new GregorianCalendar(2012, 5, 12, 13, 55) ;
                
                // 2:45PM 12th may 2012
                Calendar stopTime2 = new GregorianCalendar(2012, 5, 12, 14, 45) ;
                
                // 11:00 AM 12th May 2012
                Calendar startTime3 = new GregorianCalendar(2012, 5, 12, 11, 10) ;
                
                // 11:45 AM 12th May 2012
                Calendar stopTime3 = new GregorianCalendar(2012, 5, 12, 15, 55) ;
                
                // 8:20 AM 12th May 2012
                Calendar startTime4 = new GregorianCalendar(2012, 5, 12, 8, 20) ;
                
                // 12:30 PM 12th May 2012
                Calendar stopTime4 = new GregorianCalendar(2012, 5, 12, 12, 30) ;
                
                // create the activities
                final Activity a1 = new Activity("Cooking", 
                        startTime4, stopTime4) ;
                Activity a2 = new Activity("Swimming", 
                        startTime1, stopTime1) ;
                Activity a3 = new Activity("Golf", 
                        startTime3, stopTime3) ;
                final Activity a4 = new Activity("Meeting", 
                        startTime2, stopTime2) ;
                
                // add them to the arraylist
                components.add(a1) ;
                components.add(a2) ;
                components.add(a3);
                components.add(a4);
                
                // create the model
                final TimetableModel model = new TimetableModel(components) ;                
                final Timetable table = new Timetable(model, 3, 12) ;
                
                // create the scroll pane
                ScrollPane scrollPane = new ScrollPane() ;
                scrollPane.setViewport(table);
                
                // create event listener for the selected activity item 
                // and display the item
                
                // create and add an event listener to the
                // scroll pane
                scrollPane.addItemSelectedListener(new ItemSelectedListener() {

                    @Override
                    public void itemSelected(ItemSelected evt) {
                        // get the selected panel component
                        // and display message on it
                        ActivityItem p = (ActivityItem) evt.getSource() ;
                        JOptionPane.showMessageDialog(null, "Activity Item selected : "
                                + p.getComponentID(), "Panel Component selected",
                                JOptionPane.PLAIN_MESSAGE);
                        
                        // edit model
                        components.remove(p.getActivity());
                        table.setTimetableModel(model);
                    }
                });
                
                // add the frame result
                frame.setLayout(new BorderLayout());
                frame.add(scrollPane, BorderLayout.CENTER) ;
                frame.pack();
                frame.setVisible(true);
            }
        };
        
        EventQueue.invokeLater(run);
    }
}
