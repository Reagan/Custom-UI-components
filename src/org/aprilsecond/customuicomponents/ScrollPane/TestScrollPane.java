package org.aprilsecond.customuicomponents.ScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class tests the Scroll pane class to ensure that 
 * any component is embedded within it and that correct 
 * scrolling behavior is demonstrated
 * @author Reagan
 */
public class TestScrollPane {
    
    public static void main(String[] args) {
        Runnable windowDisplay = new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Test scrollpane") ;
                frame.getContentPane().setPreferredSize(new Dimension(300, 300));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                
                // create the viewport & Component
                TestViewer panelViewer = new TestViewer() ;
                
                // create the scrollpane
                ScrollPane scrollPane = new ScrollPane() ;
                scrollPane.setViewport(panelViewer);
                
                // create and add an event listener to the
                // scroll pane
                scrollPane.addItemSelectedListener(new ItemSelectedListener() {

                    @Override
                    public void itemSelected(ItemSelected evt) {
                        // get the selected panel component
                        // and display message on it
                        PanelComponent p = (PanelComponent) evt.getSource() ;
                        JOptionPane.showMessageDialog(null, "Panel component selected : "
                                + p.getComponentID(), "Panel Component selected",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                });
                
                // add the scrollpane to the frame
                frame.add(scrollPane, BorderLayout.CENTER) ;
                frame.pack();
                frame.setVisible(true);
            }
        } ;
        
        EventQueue.invokeLater(windowDisplay);
    }
}
