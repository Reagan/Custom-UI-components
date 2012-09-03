package org.aprilsecond.customuicomponents.checkboxdropdown;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.*;
import javax.swing.event.EventListenerList;

/**
 * This class displays a popup that is displayed when the 
 * user selects the drop down menu of the JComboBox
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class CheckBoxDropDownPopup extends JDialog {
    
    /**
     * stores the displayed check box items and the
     * colors for the items
     */
    private ConcurrentHashMap<String, Color> displayedItems ;
    
    /**
     * stores the status for each of the selected 
     * items
     */
    private boolean[] itemSelectedStatus ; 
    
    /**
     * stores the panel with the check boxes 
     */
    private JPanel basePanel  ;
    
    /**
     * stores the layout for the base panel
     */
    private BoxLayout layout ;
    
    /**
     * base panel background panel
     */
    private final Color BASE_COLOR =  new Color (51, 51, 51) ;
    
    /**
     * stores the width for the component
     */
    public static final int DIALOG_WIDTH = 207 ;
    
    /**
     * stores the height of the component
     */
    public static int dialogHeight = 100 ;
    
    /**
     * stores the width for the check box panel
     */
    private final int CHECKBOX_PANEL_WIDTH  = 23 ;
    
    /**
     * stores the height for the check box panel
     */
    private final int CHECKBOX_PANEL_HEIGHT = 30 ;
    
    /**
     * stores the width for the calendar color icons panel 
     */
    private final int CALENDAR_ICON_PANEL_WIDTH  = 23 ; 
    
    /**
     * stores the height for the calendar icons panel
     */
    private final int CALENDAR_ICON_PANEL_HEIGHT = 30 ;    
    
    /**
     * stores the width for the section with the calendar string 
     * name
     */
    private final int CALENDAR_NAME_PANEL_WIDTH = 162 ;
    
    /**
     * stores the width for the section with the calendar string 
     * name
     */
    private final int CALENDAR_NAME_PANEL_HEIGHT = 30 ;    
    
    /**
     * stores the listener list for the component
     */
    private EventListenerList listenerList ;
    
    /**
     * stores the check boxes that display the 
     * displayed items in the check boxes
     */
    public CheckBoxDropDownPopup(ConcurrentHashMap<String, Color> items,
            EventListenerList compEvtList, boolean[] selectedStatus) {
        
        // initialize the displayed items and item colors
        displayedItems = items ;             
        listenerList = compEvtList ;
        itemSelectedStatus = selectedStatus ;
        
        // initialize the base panel and components
        initializeBasePanel() ;
        initializePanelComponents() ;
        createAndAddESCListener() ;
    }
    
    /**
     * initializes the base panel for the component
     * and sets the correct layout that should be used
     */
    private void initializeBasePanel() {
        
        // determine the dialog height
        dialogHeight = displayedItems.size() * CHECKBOX_PANEL_HEIGHT ;
        
        // set the dimensions for the component
        setSize(DIALOG_WIDTH, dialogHeight);
        
        // make sure chrome is not displayed
        setUndecorated(true);
        
        basePanel = new JPanel() ;
        basePanel.setBackground(BASE_COLOR);
        basePanel.setSize(DIALOG_WIDTH, dialogHeight);
        
        // set the layout         
        layout = new BoxLayout(basePanel, BoxLayout.Y_AXIS);
        basePanel.setLayout(layout);
    }
    
    /**
     * this method initializes the components that 
     * are displayed on the component. Each of the items 
     * contains a displayed checkbox, color icon and 
     * string name
     */
    private void initializePanelComponents() {
        if (displayedItems != null) {
            
            // get the set of items
            Set itemsSet = displayedItems.entrySet() ;
            
            // create an iterator
            Iterator itemsIterator = itemsSet.iterator() ;
            
            // stores the counter to monitor selected state 
            // for the checkbox
            int checkBoxCounter = 0 ;
            
            // loop through and lay out the components horizontally 
            while (itemsIterator.hasNext()) {
                
                // get the current item and it's color
                Map.Entry<String, Color> currentItem 
                        = (Map.Entry<String, Color>) itemsIterator.next() ;
                
                String currDisplayedItem = currentItem.getKey() ;
                Color currColor = currentItem.getValue() ;
                
                // create item panel 
                JPanel itemPanel 
                        = createItemPanel(currDisplayedItem, currColor,
                        itemSelectedStatus[checkBoxCounter]) ;
                
                // add the main panel to the base panel
                addPanelToBasePanel(itemPanel) ;
            }
        }
        
        // add base panel to dialog
        addBasePanelToDialog() ;
    }     
    
    /**
     * this method creates each of the individual panels 
     * that are added to the main panel
     */
    private JPanel createItemPanel(String itemName, Color itemColor,
            boolean checkBoxSelectedStatus) {
        
        JPanel itemPanel = createItemPanel(); // the container panel
        
        // the component panels
        JPanel checkBoxPanel = createAndPopulateCheckBoxPanel(itemName,
                checkBoxSelectedStatus);
        JPanel itemColorPanel = createAndPopulateItemColorPanel(itemColor);
        JPanel itemNamePanel = createAndPopulateItemNamePanel(itemName);

        // add panels to base
        setLayoutAndAddItemsToItemPanel(itemPanel, checkBoxPanel,
                itemColorPanel, itemNamePanel);

        return itemPanel;                
    }
    
    /**
     * creates the item panel that forms the base 
     * for the attached components
     */
    private JPanel createItemPanel() {
        
        // set the dimensions for the panel
        int panelWidth = CHECKBOX_PANEL_WIDTH
                + CALENDAR_ICON_PANEL_WIDTH
                + CALENDAR_NAME_PANEL_WIDTH;
        int panelHeight = CHECKBOX_PANEL_HEIGHT;
            
        // initialise the panel
        JPanel itemPanel = new JPanel();
        itemPanel.setMinimumSize(new Dimension(panelWidth, panelHeight));
        itemPanel.setMaximumSize(new Dimension(panelWidth, panelHeight));
        
        itemPanel.setBackground(BASE_COLOR);
        
        return itemPanel ;
    }
    
    private JPanel createAndPopulateCheckBoxPanel(final String currItem,
            boolean selectedStatus) {
        
        JPanel itemCheckBoxPanel = new JPanel();
        JCheckBox itemCheckBox = new JCheckBox() ;
        itemCheckBox.setSelected(selectedStatus);
         
        itemCheckBox.setBackground(BASE_COLOR);
        itemCheckBoxPanel.setMaximumSize(
                new Dimension(CHECKBOX_PANEL_WIDTH, CHECKBOX_PANEL_HEIGHT));
        itemCheckBoxPanel.setMinimumSize(
                new Dimension(CHECKBOX_PANEL_WIDTH, CHECKBOX_PANEL_HEIGHT));
        
        itemCheckBoxPanel.add(itemCheckBox) ;        
        itemCheckBoxPanel.setBackground(BASE_COLOR);
        
        itemCheckBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange() ;
                CheckBoxPopupItemSelected selectedEvt = null ;
                
                if(state == ItemEvent.SELECTED) {
                    selectedEvt = new CheckBoxPopupItemSelected(this, 
                        currItem, CheckBoxPopupItemSelected.SELECTED) ;
                } else if (state == ItemEvent.DESELECTED) {
                    selectedEvt = new CheckBoxPopupItemSelected(this, 
                        currItem, CheckBoxPopupItemSelected.DESELECTED) ;                    
                }
                
                fireCheckBoxPopupItemSelected(selectedEvt);
            }
        });
        
        return itemCheckBoxPanel ;
    }
    
    private JPanel createAndPopulateItemColorPanel(Color itemColor) {
        
        ItemColorPanel itemColorIconPanel = new ItemColorPanel(itemColor);           
        itemColorIconPanel.setMinimumSize(
                new Dimension(CALENDAR_ICON_PANEL_WIDTH, CALENDAR_ICON_PANEL_HEIGHT));
        itemColorIconPanel.setMaximumSize(
                new Dimension(CALENDAR_ICON_PANEL_WIDTH, CALENDAR_ICON_PANEL_HEIGHT));        
        itemColorIconPanel.setBackground(BASE_COLOR);
        
        return itemColorIconPanel ;
    }
    
    private JPanel createAndPopulateItemNamePanel(String name) {
        String itemName = shortenName(name) ;
        
        Font itemNameFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12 ) ;
        Color itemNameFontColor = new Color (223, 223, 223) ;
        
        JPanel itemNamePanel = new JPanel();        
        JLabel itemNameLabel = new JLabel(itemName,SwingConstants.LEFT) ;
        itemNameLabel.setFont(itemNameFont);
        itemNameLabel.setForeground(itemNameFontColor);        
        itemNamePanel.setMaximumSize(
                new Dimension(CALENDAR_NAME_PANEL_WIDTH, CALENDAR_NAME_PANEL_HEIGHT));
        itemNamePanel.setMinimumSize(
                new Dimension(CALENDAR_NAME_PANEL_WIDTH, CALENDAR_NAME_PANEL_HEIGHT));        
        itemNamePanel.add(itemNameLabel) ;        
        itemNamePanel.setBackground(BASE_COLOR); 
        
        return itemNamePanel ;
    }
    
    private String shortenName(String name) {
        int MAX_NAME_LENGTH = 20 ;
        if (name.length()> MAX_NAME_LENGTH) {
           return name.substring(0, MAX_NAME_LENGTH)+ "..." ;            
        } else {
            return name ;
        }
    }
    
    private void setLayoutAndAddItemsToItemPanel(JPanel itemPanel,
            JPanel checkBoxPanel, JPanel itemColorIconPanel,
            JPanel itemNamePanel) {

        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
        itemPanel.add(checkBoxPanel);
        itemPanel.add(itemColorIconPanel);
        itemPanel.add(itemNamePanel);
    }
    
    private void addPanelToBasePanel(JPanel itemPanel) {
        basePanel.add(itemPanel) ;
    }
    
    private void addBasePanelToDialog() {
        add(basePanel) ;
    }
    
    private void createAndAddESCListener() {
        Action escListener = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };

        getRootPane().registerKeyboardAction(escListener,
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
    
     /**
     * fires the CheckBoxPopupItemSelected event
     */
    public void fireCheckBoxPopupItemSelected(CheckBoxPopupItemSelected  evt) {
        
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i = i + 2) {
            if (listeners[i] == CheckBoxPopupItemSelectedListener.class) {
                ((CheckBoxPopupItemSelectedListener) listeners[i + 1]).checkBoxPopupItemSelected(evt);
            }
        }
    }
}
