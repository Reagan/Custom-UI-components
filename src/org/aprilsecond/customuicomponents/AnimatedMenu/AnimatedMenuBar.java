package org.aprilsecond.customuicomponents.AnimatedMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.EventListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import org.aprilsecond.customuicomponents.transparentbuttons.CloseButton;
import org.aprilsecond.customuicomponents.transparentbuttons.MinimiseButton;

/**
 * This class creates the Animated Menubar that contains the
 * Animated menu items. The menu bar adds a close and minimize 
 * button to the end of the menu bar
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class AnimatedMenuBar extends JPanel {
    
    /**
     * stores the height for the component
     */
    private int menuBarHeight = 40 ;
    
    /**
     * stores the width for the component
     */
    private int menuBarWidth = 540 ;
    
    /**
     * stores the close button 
     */
    private CloseButton closeButton ;
    
    /**
     * stores the minimize button
     */
    private MinimiseButton minimiseButton ;
    
    /**
     * stores the background for the component
     */
    private final Color BG_COLOR = new Color(67, 70, 75) ;
    
    /** 
     * create the panel that stores the buttons
     */
    private JPanel buttonContainer ; 
    
    /**
     * stores the window with the chrome window buttons
     */
    private JPanel chromeWindowContainer ;
    
    /**
     * stores the containing frame for the application 
     */
    private JFrame appFrame ;
    
    /**
     * stores the EventListener object that stores the Event Objects
     */
    public EventListenerList listenerList = new EventListenerList(); 
    
    /**
     * constructor initializes the component
     */
    public AnimatedMenuBar(JFrame frame) {
        super() ;
                 
        // store the application frame
        appFrame = frame ;
        
        // set dimensions
        setPreferredSize(new Dimension(menuBarWidth, menuBarHeight));        
        
        // sets the layout time
        setLayout(new BorderLayout());
        
        // initializes the panel that stores the buttons
        buttonContainer = new JPanel() ;
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));
        buttonContainer.setBackground(BG_COLOR);
        
        // initialize the panel with the buttons that 
        // minimise and maximise the buttons
        chromeWindowContainer = new JPanel() ;
        chromeWindowContainer.setBackground(BG_COLOR);
        
        // initialize the close button
        closeButton = new CloseButton(appFrame) ;
        
        // initialise the minimize button
        minimiseButton = new MinimiseButton(appFrame) ;
        
        // add the buttons to the main menu
        chromeWindowContainer.add(minimiseButton) ;
        chromeWindowContainer.add(closeButton) ;
        
        // add the main panel
        add(buttonContainer) ;
        add(chromeWindowContainer, BorderLayout.LINE_END) ;
    }
    
    /**
     * adds an animated menu item to the component by adding them to 
     * the component with the menu button
     */
    public void addMenuItem(AnimatedMenuItem menuItem) {
        buttonContainer.add(menuItem) ;
        menuItem.addEventListenerList(listenerList);
    }
    
    /**
     * adds a selected menu item listener
     */
    public void addMenuItemSelectedListener(MenuItemSelectedListener listener) {
        listenerList.add(MenuItemSelectedListener.class, listener);
    }
    
    /**
     * removes a selected menu item listener
     */
    public void removeMenuItemSelectedListener(MenuItemSelectedListener listener) {
        listenerList.remove(MenuItemSelectedListener.class, listener);
    }
}
