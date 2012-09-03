package org.aprilsecond.customuicomponents.checkboxdropdown;

import java.util.EventObject;

/**
 * This stores the event that is generated when a checkbox 
 * item is generated from the CheckBoxDropDown component
 * @author Reagan mbitiru <reaganmbitiru@gmail.com>
 */
public class CheckBoxPopupItemSelected extends EventObject {
    
    /**
     * stores the selected state
     */
    public  static int SELECTED = 1 ;
    
    /**
     * stores the deselected status
     */
    public static int DESELECTED = 2 ;

    
    /**
     * stores the <String> name of the selected
     * checkbox item
     */
    private String selectedItem  ;
    
    /**
     * stores the selected status
     */
    private int selectedStatus ;
        
    /**
     * initializes the Event object
     * @param source item generating the event
     * @param selectedItem action name for the selected item
     * @param selectedStatus status whether the component is selected
     *  or not selected
     */
    public CheckBoxPopupItemSelected(Object source, String selectedItem,
            int selectedStatus) {
        super(source) ;
        setItemSelected(selectedItem) ;
        setSelectedStatus(selectedStatus ) ;
    }
    
    private void setItemSelected(String selectedStringItem) {
        selectedItem = selectedStringItem ;
    }
    
    public String getItemSelected() {
        return selectedItem ;
    }
    
    private void setSelectedStatus(int selectStatus) {
        selectedStatus = selectStatus ;
    }
    
    public int getSelectedStatus() {
        return selectedStatus ;
    }
}
