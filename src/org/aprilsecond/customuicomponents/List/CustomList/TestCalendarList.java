package org.aprilsecond.customuicomponents.List.CustomList;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.aprilsecond.customuicomponents.List.ListItem.CalendarListItem;
import org.aprilsecond.customuicomponents.ScrollPane.ItemSelected;
import org.aprilsecond.customuicomponents.ScrollPane.ItemSelectedListener;
import org.aprilsecond.customuicomponents.ScrollPane.ScrollPane;

/**
 * This class tests the calendar list component
 * @author Reagan
 */
public class TestCalendarList {
    public static void main(String [] args) {
        Runnable window = new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame("Testing Calendar List Component") ;
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(425, 380);
                
                // create the messages
                final ArrayList<String> messages = new ArrayList<String>() ;
                messages.add("Visit to Aunt Macharia's House 1");
                messages.add("Visit to Aunt Macharia's House 2");
                messages.add("Visit to Aunt Macharia's House 3");
                // messages.add("Visit to Aunt Macharia's House 4");
                
                // create the calendar times
                final ArrayList<Calendar> times = new ArrayList<Calendar>() ;                
                times.add(new GregorianCalendar(2012, Calendar.JANUARY, 
                        12, 11, 13));
                times.add(new GregorianCalendar(2012, Calendar.FEBRUARY, 
                        13, 11, 13));
                times.add(new GregorianCalendar(2012, Calendar.OCTOBER, 
                        14, 11, 13));
                // times.add(new GregorianCalendar(2012, Calendar.JANUARY, 
                //         Calendar.FRIDAY, 11, 13));
                
                // create calendar list component
                // CalendarList cList = new CalendarList(times, messages, 375, 320) ;               
                final CalendarList cList = new CalendarList() ;
                cList.setListDimensions(new Dimension(375, 320)); 
                cList.setSelectedItem(1);
                
                // add the model
                CalendarListModel model = new CalendarListModel(messages, times) ;
                cList.setCalendarListModel(model);
                
                // add to frame and display frame
                final ScrollPane sPane = new ScrollPane() ;
                sPane.setViewport(cList);
                
                // add event listener to ScrollPane
                sPane.addItemSelectedListener(new ItemSelectedListener() {

                    @Override
                    public void itemSelected(ItemSelected evt) {
                        
                        // get the selected calendar Item
                        CalendarListItem listItem 
                                = (CalendarListItem) evt.getSource() ;
                                                
                        // alert details on the selected component
                        JOptionPane.showMessageDialog(null, 
                                "CalendarListItem Selected : " + 
                                listItem.getComponentID(), 
                                "Calendar List Item Selected", 
                                JOptionPane.INFORMATION_MESSAGE);
                                                
                        // clear the array lists
                        // @Test
                        //times.clear() ;
                        //messages.clear() ;
                   
                        // add a little more data to calendar list
                        times.add(new GregorianCalendar(2012, Calendar.JANUARY, 
                            Calendar.FRIDAY, 11, 13));
                        messages.add("Visit to Aunt Macharia's House 4");
                        
                         times.add(new GregorianCalendar(2012, Calendar.JANUARY, 
                            Calendar.FRIDAY, 11, 13));
                        messages.add("Visit to Aunt Macharia's House 5");
                        
                        // add items to model
                        CalendarListModel newModel = new CalendarListModel(messages, times) ;
                        cList.setCalendarListModel(newModel);
                        
                        // change the selected item
                        // listItem.setSelectedState(true);
                        // cList.setSelectedItem(listItem);
                    }
                });
                                
                // display the frame
                frame.add(sPane) ;
                frame.setVisible(true);
            }
        } ;
        
        EventQueue.invokeLater(window);
    }
}
