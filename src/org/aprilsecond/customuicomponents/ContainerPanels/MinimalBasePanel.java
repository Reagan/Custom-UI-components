package org.aprilsecond.customuicomponents.ContainerPanels;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.aprilsecond.customuicomponents.BasePanels.MinimalContentPanel;
import org.aprilsecond.customuicomponents.BasePanels.TitlePanel;

/**
 * This class encompases the Minimal Base Panel component
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class MinimalBasePanel extends JFrame 
    implements MouseListener, MouseMotionListener {
    
    /** 
     * stores the main panel container
     */
    private MinimalBaseJPanel panel ;
    
    /**
     * stores state on whether or not the component
     * is draggable
     */
    private boolean isDraggable = false ;
    
    /**
     * sets the location of the component
     */
    public Point currentDragLocation = new Point() ;  
    
    /**
     * stores the title pane
     */
    private TitlePanel titlePane ;
    
    /**
     * stores the main content pane
     */
    private MinimalContentPanel contentPane ;    
    
    /**
     * stores the location at which the panel is 
     * displayed
     */
    private Point displayLocation = new Point(0, 0) ;
    
    /**
     * stores the application icon path
     */
    private String reminderAppIconPath = "resources" 
            + File.separator 
            + "images" 
            + File.separator
            + "reminderAppIcon.png";
    
     /**
     * constructor initializes component
     */
    public MinimalBasePanel() {
        
        // initialize the panel
        initializePanel();   
    }
    
    /**
     * constructor initializes the title and content
     * for the displayed window
     */
    public MinimalBasePanel(String title, String message)  {
        // initialize  the panel
        initializePanel() ;
        
        // add the title pane
        titlePane.setTitle(title);
        
        // add the message panel
        contentPane.setContentString(message);
    }
    
    /**
     * constructor initializes the title and content
     * for the displayed window and specifies the location
     * for the displayed panel
     */
    public MinimalBasePanel(String title, String message,
            Point location)  {
        // initialize  the panel
        initializePanel() ;
        
        // set the display location
        displayLocation = location ;
        
        // set the display location
        setLocation(displayLocation);
        
        // add the title pane
        titlePane.setTitle(title);
        
        // add the message panel
        contentPane.setContentString(message);
    }
    
    /**
     * sets whether or not the panel is draggable
     */
    private void setDraggable(boolean state) {
        isDraggable = state ;
    }
    
    /**
     * finds out if the component is draggable or not
     */
    private boolean isDraggable() {
        return isDraggable ;
    }       
    
    /**
     * displays the panel
     */
    public void displayPanel() {
        // display the frame if it in the event dispatch thread
        if(SwingUtilities.isEventDispatchThread()) {
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
    
    /**
     * sets the title
     */
    public void setPanelTitle(String title) {
        
    }
    
    /**
     * sets the content message
     */
    private void setPanelContent(String content) {
        
    }
    
    /**
     * this method initializes the panel
     */
    private void initializePanel() {
         // makes it draggable
        makeDraggable() ;
        
        // initializes base container panel
        panel = new MinimalBaseJPanel() ;
        
        // set the layout for the container panel
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // set the layout for the frame
        setLayout(new BorderLayout());
        
        // add the panel to the frame
        add(panel, BorderLayout.CENTER) ;
        
        // add the properties for the panel
        setUndecorated(true);
        setSize(256, 102);
        
        // add the title pane
        titlePane = new TitlePanel() ;
        addTitlePane(titlePane);
        
        // add the main content pane
        contentPane = new MinimalContentPanel(); 
        addContentPane(contentPane);
        
        // set the application icon
        Image appIcon = null;
        try {
            appIcon = ImageIO.read(new File(reminderAppIconPath));
            setIconImage(appIcon);
        } catch (IOException ex) {
            System.out.println("Error accessing reminder application icon image "
                    + reminderAppIconPath);
        }   
    }
    
    /**
     * makes the panel draggable
     */
    private void makeDraggable() {        
        // set the mouse listener and motion listeners
        addMouseListener(this);
        addMouseMotionListener(this);
    }  
    
    /**
     * sets the title pane
     */
    private void addTitlePane(TitlePanel titlePane) {
        panel.add(titlePane) ;
    }
    
    /** 
     * sets the content pane
     */
    private void addContentPane(MinimalContentPanel contentPane) {
        panel.add(contentPane) ;
    }
    
    /**
     * updates the location of the Base Panel based on the
     * mouse dragged coordinates
     * @param e 
     */
    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
       if (isDraggable()) {
            setLocation(e.getLocationOnScreen().x 
                    - currentDragLocation.x, e.getLocationOnScreen().y 
                    - currentDragLocation.y);
            currentDragLocation.x = e.getLocationOnScreen().x - getX();
            currentDragLocation.y = e.getLocationOnScreen().y - getY();
        }
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {}

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) { }

    /**
     * gets the initial coordinates for the panel 
     * before it is dragged 
     * @param e 
     */
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        if (contains(e.getPoint())) {
            currentDragLocation.x 
                    = e.getLocationOnScreen().x - getX();
            currentDragLocation.y 
                    = e.getLocationOnScreen().y - getY();
            setDraggable(true);
        }
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {}

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {}

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {}
}
