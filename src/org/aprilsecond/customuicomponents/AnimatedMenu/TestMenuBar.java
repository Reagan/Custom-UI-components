package org.aprilsecond.customuicomponents.AnimatedMenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This tests the Menubar
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class TestMenuBar {
    public static void main(String [] args) {
        Runnable run = new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame("Test Animated Menu bar") ;
                frame.getContentPane().setPreferredSize(new Dimension(540, 300));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                
                AnimatedMenuBar bar = new AnimatedMenuBar(frame) ;
                frame.add(bar, BorderLayout.NORTH);
                
                AnimatedMenuItem menuItem = new AnimatedMenuItem("Activities") ;
                bar.addMenuItem(menuItem);
                AnimatedMenuItem menuItem1 = new AnimatedMenuItem("Schedule") ;
                bar.addMenuItem(menuItem1);
                
                // add an event listener to the component
                bar.addMenuItemSelectedListener(new MenuItemSelectedListener() {

                    @Override
                    public void menuItemSelected(MenuItemSelected evt) {
                        // get the selected menu item
                        AnimatedMenuItem menuItem = (AnimatedMenuItem) evt.getSource() ;
                        
                        // show a Joprion pane with the content for the 
                        // selected menu item
                        JOptionPane.showMessageDialog(null, "Selected Animated Menu Item : "
                                + menuItem.getActionName(), "Menu Item selected", 
                                JOptionPane.PLAIN_MESSAGE);
                    }
                });
                
                JPanel panel = new JPanel() ;                
                panel.setPreferredSize(new Dimension(540, 260));
                frame.add(panel, BorderLayout.CENTER) ;
                
                frame.pack();
                frame.setVisible(true);                
            }
        }; 
        
        EventQueue.invokeLater(run);
    }
}
