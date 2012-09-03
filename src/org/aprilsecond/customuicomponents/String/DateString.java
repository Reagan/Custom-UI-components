package org.aprilsecond.customuicomponents.String;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.event.EventListenerList;

/**
 * This class implements a date string that is displayed on the home
 * page of the application
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class DateString extends JLabel
    implements MouseListener {
    
    /**
     * stores the font type for the string 
     */
    private final Font FONT_STRING = new Font(Font.SANS_SERIF, 
            Font.BOLD, 15) ;
    
    /**
     * stores the font color for the string
     */
    private final Color FONT_COLOR = new Color(157, 195, 12) ;
    
    /**
     * stores the Event Listener list that manages event
     * generated when the string is selected
     */
    private EventListenerList listenerList = new EventListenerList();    
    
    /**
     * stores the displayed string
     */
    private String displayedString ;
    
    /**
     * null constructor initializes a date string 
     */
    public DateString(String displayedDateString) {
        super(displayedDateString) ;
        
        // assign the date string
        displayedString = displayedDateString ;
        
        // set the font type for the displayed string
        setFont(FONT_STRING);
        
        // set the font color for the String
        setBackground(FONT_COLOR);
        setForeground(FONT_COLOR);
        
        // ensure that the component is transparent
        setOpaque(false);
        
        // add mouse listener
        addMouseListener(this);
    }
    
    /**
     * method responsible for adding adding events to listener
     */
    public void addDateStringSelectedListener (DateStringSelectedListener listener) {
        listenerList.add(DateStringSelectedListener.class, listener) ;
    }
    
    /**
     * removes events from listener
     */
    public void removeDateStringSelectedListener(DateStringSelectedListener listener) {
        listenerList.remove(DateStringSelectedListener.class, listener);
    }
    
    /**
     * fires the DateStringSelected Event
     */
    public void fireDateStringSelected(DateStringSelected  evt) {
        
        Object[] listeners = listenerList.getListenerList();
            for (int i = 0; i < listeners.length; i = i+2) 
            {
                    if (listeners[i] == DateStringSelectedListener.class) 
                    {
                            ((DateStringSelectedListener) listeners[i+1]).dateStringSelected(evt);
                    }
            }
    }

    /**
     * finds out if the user has double clicked the component 
     * and fires an event indicating that the DateString 
     * has been selected
     * @param e 
     */
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        if (e.getClickCount() == 2) {
             
                // fire custom event
                DateStringSelected n = new DateStringSelected(this, 
                        displayedString) ;
                fireDateStringSelected(n);  
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {}

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {}

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {}

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {}
    
}
