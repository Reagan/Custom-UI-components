package org.aprilsecond.customuicomponents.List.ListItem;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class tests the various lists created 
 * by components in the class
 * @author Reagan
 */
public class TestSimpleListItem {
    public static void main (String [] args) {
        Runnable run = new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame("Test Lists") ;
                frame.setSize(new Dimension(185, 290));
                
                ArrayList<String>items = new ArrayList<String>() ;
                items.add("Monday");
                items.add("Tuesday");
                items.add("Wednesday");
                items.add("Thursday");
                items.add("Friday");
                items.add("Saturday");
                items.add("Sunday");
                
                
                JPanel p1 = new JPanel() ;
                p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
                
                // test the simple list item
                for (int i = 0 ; i < items.size() ; i++) {
                    SimpleListItem simpleListItem = new SimpleListItem(items.get(i), 185, 27) ;
                    p1.add(simpleListItem) ;
                }
                // add the panel to the frame
                frame.add(p1) ;
                
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        }; 
        
        EventQueue.invokeLater(run);
    }
}
