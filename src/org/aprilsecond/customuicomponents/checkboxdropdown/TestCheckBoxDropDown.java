package org.aprilsecond.customuicomponents.checkboxdropdown;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JFrame;

/**
 * This class tests the CheckBoxDropDown Component 
 * display
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class TestCheckBoxDropDown {
    
    public static void main(String[] args) {
        final ConcurrentHashMap<String,Color> items 
                =  new ConcurrentHashMap<String, Color>() ;
        items.put("Personal Calendar", new Color(255, 102, 0));
        items.put("Master's for that the Class calendar", new Color(255, 0, 0));
        items.put("Reagan's Calendar", new Color(0, 102, 255));
        
        final String title = "All" ;
        
        Runnable run = new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame("CheckBoxDropDown Test");
                frame.setSize(new Dimension(147, 25));
                frame.setLocation(200, 200);
                frame.setUndecorated(true);
                
                CheckBoxDropDownModel model = new CheckBoxDropDownModel(title, items) ;
                CheckBoxDropDown comp = new CheckBoxDropDown();
                comp.setCompModel(model);
                
                comp.addCheckBoxPopupItemSelectedListener(new CheckBoxPopupItemSelectedListener() {

                    @Override
                    public void checkBoxPopupItemSelected(CheckBoxPopupItemSelected evt) {
                        if (evt.getSelectedStatus() 
                                == CheckBoxPopupItemSelected.SELECTED) {
                            System.out.println(evt.getItemSelected() + " SELECTED") ;
                        } else if (evt.getSelectedStatus() 
                                == CheckBoxPopupItemSelected.DESELECTED) {
                            System.out.println(evt.getItemSelected() + " DESELECTED") ;                            
                        }
                    }                   
                });
                
                frame.add(comp) ;
                
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        } ;
        
        EventQueue.invokeLater(run);
    }
}
