package org.aprilsecond.customuicomponents.checkboxdropdown;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JFrame;

/**
 * This class tests the display of the check box drop 
 * down menu component item
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class TestCheckBoxDropDownPopup {
    
    public static void main(String[] args) {
        final ConcurrentHashMap<String,Color> items 
                =  new ConcurrentHashMap<String, Color>() ;
        items.put("Personal Calendar", new Color(255, 102, 0));
        items.put("Master's Class calendar", new Color(255, 0, 0));
        items.put("Mr. Njuguna's Calendar", new Color(0, 102, 255));
        
        Runnable displayCheckBoxDialog = new Runnable() {

            @Override
            public void run() {
                JFrame baseFrame = new JFrame() ;                
                baseFrame.setSize(400, 200);
                baseFrame.setLayout(new BorderLayout());
                baseFrame.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        boolean [] checkBoxesSelectedStatus 
                                = {true, true, true} ;
                        CheckBoxDropDownPopup popup  
                                    = new CheckBoxDropDownPopup(items, null,
                                checkBoxesSelectedStatus) ;
                        popup.setLocation(0, 0);
                        popup.setVisible(true);
                    }                   
                });
                
                                
                baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                baseFrame.setVisible(true);
            }
        };
        
        EventQueue.invokeLater(displayCheckBoxDialog);
    }
}
