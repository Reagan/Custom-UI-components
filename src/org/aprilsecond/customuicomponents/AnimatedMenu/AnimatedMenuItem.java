package org.aprilsecond.customuicomponents.AnimatedMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.EventListenerList;

/**
 * This class creates an animated menu item that creates an 
 * animated fold down animation when selected.
 * 
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class AnimatedMenuItem extends JPanel
    implements MouseListener  {
    
    /**
     * stores the width for the component
     */
    private int componentWidth = 121 ;
    
    /**
     * stores the height for the component
     */
    private int componentHeight = 32 ;
    
    /**
     * stores the string for the component
     */
    private String menuItemString ;
    
    /**
     * background color for component
     */
    private Color bgColor = new Color(67, 70, 75) ;
    
    /**
     * stores the highlighted color for the component
     */
    private Color highlightColor = new Color(198, 226, 89) ;
    
    /**
     * determines if the component is selected
     */
    private boolean isSelected = false ;
    
    /**
     * stores the selected height for the component
     */
    private int selectionHeight = 0 ;
    
    /**
     * stores the animation delay for the component
     * in milliseconds
     */
    private final int DELAY = 10 ; 
    
    /**
     * stores the timer that is run to show the component
     */
     private Timer showSelectedButton ;
     
    /**
     * stores the timer that is used to hide the component
     */
     private Timer hideSelectedButton ;
     
    /**
     * sets the font for the component
     */
    private Font stringFont = new Font(Font.SANS_SERIF, 
            Font.PLAIN, 18) ;
    /**
     * sets the color for the font
     */
    private Color stringFontColor = new Color(255, 255, 255) ;
    
    /**
     * stores the menu event listener list
     */
    private EventListenerList listenerList ;
   
    /**
     * null constructor initializes the component
     */    
    public AnimatedMenuItem(String mItemString) {
        menuItemString = mItemString ;
        setPreferredSize(new Dimension(componentWidth, componentHeight));
        setMinimumSize(new Dimension(componentWidth, componentHeight));
        setMaximumSize(new Dimension(componentWidth, componentHeight));
        setBorder(null);
        
        // add the mouse listener
        addMouseListener(this);
    }
    
    /**
     * sets the selected state for the component
     */
    public void setSelectedState(boolean state) {
        isSelected = state ;
    }
    
    /**
     * gets the selected state for the component
     */
    public boolean isSelectedState() {
        return isSelected ;
    }
    
    /**
     * paints the custom drawing of the component
     */
    @Override
    public void paintComponent(Graphics g) {
        // clone the graphics object
        Graphics2D graphics = (Graphics2D) g ;
        
        // cater for border/insets
        int width = getWidth() - getInsets().left - getInsets().right ;
        int height = getHeight() - getInsets().top - getInsets().bottom ;
        
        // fill the background for the component
        graphics.setColor(bgColor);
        graphics.fillRect(0, 0, width, height);
        
        // sets anti aliasing
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        // fill the rectangle depending on the selected state
        // for the component
        if (isSelectedState()) {
            graphics.setColor(highlightColor);
            graphics.fillRect(0, 0, getWidth(), selectionHeight);
        }
        
        // display the text for the component
        graphics.setFont(stringFont);
        graphics.setColor(stringFontColor);
        graphics.drawString(menuItemString, 24, 22);
    }

    /**
     * Fires off a method showing that the menu item has been selected
     */
    private void fireMenuItemSelected(MenuItemSelected evt) {
        
        //  loop through and find out if the selected menu
        // item is the current one and fire off an event
        Object[] listeners = listenerList.getListenerList();
        
        for (int i = 0; i < listeners.length; i = i + 2) {
          if (listeners[i] == MenuItemSelectedListener.class) {
                ((MenuItemSelectedListener) listeners[i + 1]).menuItemSelected(evt);     
                break ;//  no point going on...
            }
        }
    }
    
    /**
     * triggers a MenuItem selected event when the component is selected
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // create a menu selected event and fire custom menu item
        MenuItemSelected n = new MenuItemSelected(this, 
                        this) ;
        fireMenuItemSelected(n) ;
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        
        // stop all timers first
         // stop all timers first
        if ( null != showSelectedButton ) {
            showSelectedButton.stop() ;
        } else if (null != hideSelectedButton) {
            hideSelectedButton.stop();
        }        
        
        // set the selected state
        setSelectedState(true);
        
        // loop through animating the display of the
        // menu item
        showSelectedButton = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // increment the height for the selected section
                // of the component and repaint
                if (selectionHeight < componentHeight) {
                    selectionHeight ++ ;
                    repaint() ;
                } else {
                    // stop the timer
                    ((Timer) e.getSource()).stop();
                }
            }
        }) ;
       
        // start the timer
        showSelectedButton.start();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
        // stop all timers first
        if ( null != showSelectedButton ) {
            showSelectedButton.stop() ;
        } else if (null != hideSelectedButton) {
            hideSelectedButton.stop();
        }
        
        // loop through animating the display of the
        // menu item
        hideSelectedButton = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // increment the height for the selected section
                // of the component and repaint
                if (selectionHeight > 0) {
                    selectionHeight -- ;
                    repaint() ;
                } else {
                    setSelectedState(false);   
                    
                    // stop the timer
                    ((Timer) e.getSource()).stop();
                }
            }
        }) ;
        
        // start the timer
        hideSelectedButton.start();              
    }
    
    /**
     * adds a menu event list listener
     */
    public void addEventListenerList(EventListenerList eventList) {
        listenerList = eventList ;
    }
    
    /**
     * retrieves the action name for the component
     */
    public String getActionName() {
        return menuItemString ;
    }
}
