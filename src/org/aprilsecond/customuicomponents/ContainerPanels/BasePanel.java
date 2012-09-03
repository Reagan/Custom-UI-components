package org.aprilsecond.customuicomponents.ContainerPanels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.aprilsecond.customuicomponents.BasePanels.BaseContentPanel;
import org.aprilsecond.customuicomponents.BasePanels.MenuPanel;

/**
 * This class encompases the BaseJPanel JPanel
 * component
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class BasePanel extends JFrame 
    implements MouseListener, MouseMotionListener {
    
    /**
     * stores the BaseJPanel component
     */
    private BaseJPanel panel ;
    
     /**
     * stores state on whether or not the component
     * is draggable
     */
    private boolean isDraggable = false ;
    
    /**
     * stores the width of the application
     */
    public static final int PANEL_WIDTH = 538 ;
    
    /**
     * stores the height of the application
     */
    public static final int PANEL_HEIGHT = 340 ;
        
    /**
     * sets the location of the component
     */
    public Point currentDragLocation = new Point() ;    
    
    public BasePanel() {
        
        // makes it draggable
        makeDraggable() ;
        
        // initialize the panel
        panel = new BaseJPanel() ;    
        
        // set the layout for the container panel
        panel.setLayout(new BorderLayout());
        
        // add the panel to the component
        add(panel) ;
        
        // add the properties for the panel
        setUndecorated(true);
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
        
        // display the frame if it in the event dispatch thread
        if(SwingUtilities.isEventDispatchThread()) {
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
    
    /**
     * adds the menu bar to the frame
     */
    public void addMenuBar(MenuPanel menuPanel) {
        panel.add(menuPanel, BorderLayout.NORTH);
    }
        
    /**
     * adds the content pane to the menu bar section
     */
    public void addContentPane(BaseContentPanel contentPanel) {
        panel.add(contentPanel, BorderLayout.CENTER) ;
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
