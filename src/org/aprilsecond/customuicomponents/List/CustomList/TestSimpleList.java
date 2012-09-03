package org.aprilsecond.customuicomponents.List.CustomList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.aprilsecond.customuicomponents.List.ListItem.SimpleListItem;
import org.aprilsecond.customuicomponents.ScrollPane.ItemSelected;
import org.aprilsecond.customuicomponents.ScrollPane.ItemSelectedListener;
import org.aprilsecond.customuicomponents.ScrollPane.ScrollPane;

/**
 * Tests Simple List
 * @author Reagan 
 */
    public class TestSimpleList {
    public static void main(String[] args) {
        Runnable windowDisplay = new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame("Test Lists") ;
                frame.setSize(new Dimension(250, 300));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                
                // create the model
                ArrayList<String> items = new ArrayList<String>() ;
                items.add("Item 1") ;
                items.add("Item 2") ;                
                items.add("Item 3") ;  
                items.add("Item 4") ;  
                items.add("Item 5") ;  
                items.add("Item 6") ;  
                items.add("Item 7") ;  
                items.add("Item 8") ;                  
                
                // create and attach the simple list
                // SimpleList list = new SimpleList(items, 250, 500) ;
                                               
                final SimpleList list = new SimpleList();
                list.setListDimensions(new Dimension(250, (37*8)));                
                list.setSelectedItem(3);
                
                SimpleListModel model = new SimpleListModel(items); 
                list.setSimpleListModel(model);
                
                // initialise the scroll pane
                ScrollPane scrollPane = new ScrollPane() ;
                
                // add components to scroll pane
                scrollPane.setViewport(list);
        
                 // add event listener to ScrollPane
                scrollPane.addItemSelectedListener(new ItemSelectedListener() {

                    @Override
                    public void itemSelected(ItemSelected evt) {
                        
                        // get the selected calendar Item
                        SimpleListItem sListItem = (SimpleListItem) evt.getSource() ;
                        
                        // alert the selected list item
                        /**
                        JOptionPane.showMessageDialog(null, 
                                "Selected list item : "
                                + sListItem.getComponentID(),
                                "SimpleListItem selected", 
                                JOptionPane.INFORMATION_MESSAGE);
                        **/
                        // set it as the selected item
                        // sListItem.setSelectedState(true);
                        list.setSelectedItem(sListItem);
                    }
                });
                
                frame.add(scrollPane, BorderLayout.CENTER); 
                frame.setVisible(true);
            }
        };
        
        EventQueue.invokeLater(windowDisplay);
    }
}
