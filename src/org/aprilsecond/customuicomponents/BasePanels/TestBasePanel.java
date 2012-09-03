package org.aprilsecond.customuicomponents.BasePanels;

import java.awt.EventQueue;
import org.aprilsecond.customuicomponents.ContainerPanels.BasePanel;

/**
 * This class draws the main panel and the various components
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class TestBasePanel {
    
    // Test
    public static void main(String[] args) {
        Runnable windowDisplay = new Runnable() {
            
            @Override
            public void run() {
                
                // create the custom panel
                BasePanel panel = new BasePanel() ;                
                
                MenuPanel p1 = new MenuPanel() ;                
                panel.addMenuBar(p1) ;
                
                BaseContentPanel p2 = new BaseContentPanel() ;
                panel.addContentPane(p2) ;
            }
        };
        
        // run the window display
        EventQueue.invokeLater(windowDisplay);
    }
}
