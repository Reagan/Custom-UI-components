package org.aprilsecond.customuicomponents.ScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 * Creates a test instance of the ViewPort
 * @author Reagan
 */
public class TestViewer extends ViewPort {
    
        /**
         * stores first JButton
         */
        TestVButton b1 = new TestVButton() ;
        
        /**
         * stores second JButton
         */
        TestVButton b2 = new TestVButton() ;
        
        /**
         * stores the array list with the components
         */
         ArrayList<PanelComponent> cs = new ArrayList<PanelComponent>() ;
         
        public TestViewer() {
            setSize(200, 600);
            setBackground(Color.red);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            
            // package the items            
            cs.add(b1);
            cs.add(b2);
            
            // add the buttons
            add(b1) ;
            add(b2) ;
        }
        
        @Override
        public int getViewPortSize() {
           // return the number of components
            return 2 ;
        }

        @Override
        public ArrayList<PanelComponent> getViewPortComponents() {
            
            
            // return the arraylist
            return cs ;
        }

        @Override
        public PanelComponent getViewPortComponent(int componentIndex) {
            return cs.get(componentIndex) ;
        }   
        
        public static void main(String[] args) {
            Runnable runn = new Runnable() {

            @Override
            public void run() {
               JFrame f = new JFrame("Test Link") ;
               f.setSize(200, 200);
               f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               f.setLayout(new BorderLayout());
               
               // create and add the viewer
               TestViewer test = new TestViewer();
               f.add(test) ;
               
               // display frame
               f.setVisible(true);
            }
        };
            
            EventQueue.invokeLater(runn);
        }
}
