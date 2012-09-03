package org.aprilsecond.customuicomponents.checkboxdropdown;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.EventListenerList;

/**
 * This is a custom component that displays a custom 
 * pop up menu that is displayed when a drop down menu
 * is displayed. Events are triggered when a check box 
 * is selected. Similarly an event is triggered when the arrow is 
 * selected
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class CheckBoxDropDown extends JPanel
   implements MouseListener {
    
    /**
     * stores the model for the component
     */
    private CheckBoxDropDownModel compModel ;
    
    /**
     * stores the checkbox popup
     */
    private CheckBoxDropDownPopup popup ;
    
    /**
     * stores the default status for the component
     * status
     */
    private CheckBoxPopupDisplayStatus popupDisplayStatus
            = CheckBoxPopupDisplayStatus.MINIMIZED ;
    
    /**
     * stores the background for the component
     */
    private final Color BASE_COLOR = new Color(51, 51, 51) ;
    
    /**
     * stores the font for title
     */
    private final Font TITLE_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 15) ;
    
    /**
     * stores the color for title text
     */
    private final Color TITLE_COLOR = new Color(255, 255, 255) ;
    
    /**
     * stores the color for the arrow
     */
    private final Color ARROW_COLOR = new Color(253, 253, 253) ;
    
    /**
     * stores the component minimum width
     */
    private final int COMP_WIDTH = 147 ;
    
    /**
     * set the component minimum height
     */
    private final int COMP_HEIGHT = 25 ;
    
    /**
     * stores the Event Listener list that manages event
     * generated when the component is selected
     */
    private EventListenerList listenerList = new EventListenerList();    
    
    /**
     * null constructor initializes component
     */
    public CheckBoxDropDown() {
        // initializes the model
        compModel = new CheckBoxDropDownModel() ;
        setCompModel(compModel);
        initializeListeners();
        setComponentDimensions() ;
    }
    
    /**
     * constructor initializes model
     */
    public CheckBoxDropDown(CheckBoxDropDownModel model) {
        setCompModel(model) ;
        initializeListeners() ;
        setComponentDimensions() ;
    }
    
    /**
     * constructor initializes the component with an 
     * array list of components
     */
    public CheckBoxDropDown(String title, 
            ConcurrentHashMap<String, Color> compItemDetails) {
        compModel = new CheckBoxDropDownModel(title, 
                compItemDetails) ;
        setCompModel(compModel);
        initializeListeners() ;
        setComponentDimensions() ;
    }
    
    /**
     * this sets the model for the component
     */
    public final void setCompModel(CheckBoxDropDownModel model) {
        compModel = model ;
        boolean[] checkBoxSelectedStatuses = {true, true, true} ;
        popup = new CheckBoxDropDownPopup(compModel.getItems(),
                listenerList, checkBoxSelectedStatuses) ;
    }
    
    /**
     * this method gets the model for the component
     */
    public CheckBoxDropDownModel getCompModel() {
        return compModel ;
    }
    
    /**
     * sets the status for drawn component
     */
    private void setPopupDisplayStatus(CheckBoxPopupDisplayStatus status) {
        popupDisplayStatus = status ;
        repaint();
    }
    
    /**
     * this initializes the component, sets the color 
     * for the fore and back ground of the component
     */
    private void initializeListeners() {
        addMouseListener(this);
    }

    /** 
     * sets the minimum and preferred dimensions 
     * for the component
     */
    private void setComponentDimensions() {
        setMinimumSize(new Dimension(COMP_WIDTH, COMP_HEIGHT));
        setPreferredSize(new Dimension(COMP_WIDTH, COMP_HEIGHT));
    }
    
    /**
     * draw the component    
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g; 
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        Insets insets = getInsets() ;
        
        // draw the  background
        graphics.setColor(BASE_COLOR);
        graphics.fillRect(insets.top, insets.left, 
                getWidth() - (insets.left + insets.right), 
                getHeight() - (insets.top + insets.bottom));
        
        // draw the title
        int stringX  = 6 ; 
        int stringY = 16 ;
        graphics.setColor(TITLE_COLOR);
        graphics.setFont(TITLE_FONT);
        graphics.drawString(compModel.getTitle(), stringX, stringY);
        
        // draw the arrow
        int noOfPoints = 3 ;
        int rightDisplacement = 25 ;
        int[] arrowXCoords = new int[noOfPoints] ;
        int[] arrowYCoords = new int[noOfPoints] ;
        int arrowWidth = 12 ;
        int arrowTop = 6 ;
        int arrowBottom = 15 ;
        
        if (popupDisplayStatus == CheckBoxPopupDisplayStatus.MINIMIZED) {
            arrowXCoords[0] = getWidth() - (insets.left + insets.right) 
                    - rightDisplacement  ;
            arrowXCoords[1] = getWidth() - (insets.left + insets.right) 
                    - rightDisplacement + arrowWidth ;
            arrowXCoords[2] = getWidth() - (insets.left + insets.right) 
                    - rightDisplacement + arrowWidth/2 ;
            
            arrowYCoords[0] = arrowTop ;
            arrowYCoords[1] =  arrowTop ;
            arrowYCoords[2] = arrowBottom ;
        } else if (popupDisplayStatus == CheckBoxPopupDisplayStatus.VISIBLE) {
            arrowXCoords[0] = getWidth() - (insets.left + insets.right) 
                    - rightDisplacement + arrowWidth/2 ;
            arrowXCoords[1] = getWidth() - (insets.left + insets.right) 
                    - rightDisplacement  ;
            arrowXCoords[2] =  getWidth() - (insets.left + insets.right) 
                    - rightDisplacement + arrowWidth  ;
            
            arrowYCoords[0] = arrowTop ;
            arrowYCoords[1] =  arrowBottom ;
            arrowYCoords[2] =  arrowBottom ;
        }
        
        graphics.setColor(ARROW_COLOR);
        graphics.fillPolygon(arrowXCoords, arrowYCoords, noOfPoints);
    }
    
    private void displayPopup(CheckBoxPopupDisplayStatus status) {
        Point compLocation = getLocation() ;
        int YSpaceBetweenTitleAndPopup = 1 ;
        
        SwingUtilities.convertPointToScreen(compLocation, this);
        if (CheckBoxPopupDisplayStatus.VISIBLE == status) {
            popup.setLocation( compLocation.x + getWidth() 
                    - CheckBoxDropDownPopup.DIALOG_WIDTH, 
                    compLocation.y + getHeight() + YSpaceBetweenTitleAndPopup);
            popup.setVisible(true);
        } else if (CheckBoxPopupDisplayStatus.MINIMIZED == status) {
            popup.setVisible(false);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        popupDisplayStatus = (CheckBoxPopupDisplayStatus.MINIMIZED == popupDisplayStatus) ?
                CheckBoxPopupDisplayStatus.VISIBLE : CheckBoxPopupDisplayStatus.MINIMIZED ;
        setPopupDisplayStatus(popupDisplayStatus);
        displayPopup(popupDisplayStatus);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    /**
     * adds events to listener
     */
    public void addCheckBoxPopupItemSelectedListener (CheckBoxPopupItemSelectedListener listener) {
        listenerList.add(CheckBoxPopupItemSelectedListener.class, listener) ;
    }
    
    /**
     * removes events from listener
     */
    public void removeCheckBoxPopupItemSelectedListener(CheckBoxPopupItemSelectedListener listener) {
        listenerList.remove(CheckBoxPopupItemSelectedListener.class, listener);
    }       
}
