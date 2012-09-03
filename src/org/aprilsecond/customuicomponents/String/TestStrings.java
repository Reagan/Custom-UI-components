package org.aprilsecond.customuicomponents.String;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class tests the type of strings that are 
 * created by the various class members in this package
 * @author Reagan
 */
public class TestStrings {
    public static void main (String[] args) {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame("Test Frame") ;
                frame.setSize(new Dimension(200, 300));
                
                JPanel p2 = new JPanel() ;
                p2.setSize(new Dimension(180, 200));
                p2.setBackground(Color.red);
                
                // create date string and create event listener
                DateString string = new DateString("Test") ;
                string.addDateStringSelectedListener(new DateStringSelectedListener() {

                    @Override
                    public void dateStringSelected(DateStringSelected evt) {
                        JOptionPane.showMessageDialog(null, "Date String component "
                                + "has been selected", "Date String selected", 
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                
                
                // add the string to the frame
                p2.add(string) ;
                
                frame.add(p2);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        }; 

    EventQueue.invokeLater(runnable) ;
    }
}
