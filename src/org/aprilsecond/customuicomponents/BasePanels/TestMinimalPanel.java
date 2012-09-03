package org.aprilsecond.customuicomponents.BasePanels;

import java.awt.EventQueue;
import java.awt.Point;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import org.aprilsecond.customuicomponents.ContainerPanels.MinimalBaseJPanel;
import org.aprilsecond.customuicomponents.ContainerPanels.MinimalBasePanel;

/**
 * Tests the display of the minimal panel
 * @author Reagan
 */
public class TestMinimalPanel {
            
    // Test
    public static void main(String[] args) {
        Runnable windowDisplay = new Runnable() {
            
            @Override
            public void run() {
                
                // create the custom panel
                /**
                // initial test
                MinimalBasePanel panel = new MinimalBasePanel() ;
                                
                TitlePanel p1 = new TitlePanel() ;
                panel.addTitlePane(p1) ;
                
                MinimalContentPanel p2 = new MinimalContentPanel() ;
                panel.addContentPane(p2) ;
                **/
                
                /**
                // second test
                MinimalBasePanel panel = new MinimalBasePanel("!Reminder", 
                        "Review of materials for the development of the "
                        + "SMS system") ;
                **/
                
                MinimalBasePanel panel = new MinimalBasePanel("!Reminder", 
                        "Review of materials for the development of the "
                        + "SMS system", new Point(100, 100)) ;
                panel.displayPanel() ;
            }
        };
        
        // run the window display
        EventQueue.invokeLater(windowDisplay);
    }
}
