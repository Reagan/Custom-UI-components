package org.aprilsecond.customuicomponents.ScrollPane;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

/**
 * This class is used to create a scroll pane that is used to display the any
 * JComponent that is added to it. The panel added must extend ViewPort and 
 * each of the components added to the panel must extend PanelComponent
 *
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class ScrollPane extends JPanel
        implements ComponentListener, MouseListener,
        MouseMotionListener, MouseWheelListener, KeyListener {

    /**
     * stores the width of the component. sets the default width
     */
    private int width = 100;
    /**
     * stores the height of the component. sets the default height
     */
    private int height = 100;
    /**
     * stores the layered pane for the component
     */
    private ScrollBarLayeredPane scrollBarLayeredPane;
    /**
     * stores the component making the viewport for the component
     */
    private ViewPort viewport;
    /**
     * stores the panel that contains all the list items
     */
    private ScrollPaneBase basePanel;
    /**
     * stores the vertical scroll pane
     */
    private ScrollBar verticalBar;
    /**
     * stores the horizontal scroll pane
     */
    private ScrollBar horizontalBar;
    /**
     * stores whether the mouse is on top of the component
     */
    private boolean isHovered = false;
    /**
     * stores the layer for the vertical scrollbar
     */
    private final int VIEWPORT_LAYER = 60;
    /**
     * stores the layer for the horizontal scrollbar
     */
    private final int HORIZONTAL_SCROLLBAR_LAYER = 70;
    /**
     * stores the layer for the vertical scrollbar
     */
    private final int VERTICAL_SCROLLBAR_LAYER = 80;
    /**
     * stores the base panel layer
     */
    private final int BASE_PANEL_LAYER = 50;
    /**
     * stores the current viewable top left coordinates
     */
    private Point2D.Double topLeftViewPortCoords = new Point2D.Double(0, 0);
    /**
     * stores the proportion by which the y scroll bar has moved
     */
    private double yScrolledPercentage = 0;
    /**
     * stores the percentage by which the x scroll bar has moved
     */
    private double xScrolledPercentage = 0;
    
    /**
     * stores initial vertical bar top Y location before 
     * mouse dragging begins
     */
    private int initialTopY = 0 ;
    
    /**
     * stores the initial horizontal bar y location before
     * mouse dragging starts
     */
    private int initialTopX = 0 ;
    
    /**
     * stores the event listener list
     */
    private EventListenerList listenerList = new EventListenerList() ;
    
    /**
     * null constructor initializes the scroll pane
     */
    public ScrollPane() {
        // initialises the component
        initialize();
    }

    /**
     * constructor initializes the dimensions for the scroll pane
     */
    public ScrollPane(int swidth, int sheight) {
        // initialize the dimensions for the component
        width = swidth - getInsets().left - getInsets().right ;
        height = sheight - getInsets().top - getInsets().bottom ;

        // finalise initialisation
        initialize();
    }

    /**
     * completes initialization
     */
    private void initialize() {
        // set the layout type
        setLayout(new BorderLayout());

        // set the dimensions for the panel
        Dimension panelDimensions = new Dimension(width, height);
        setPreferredSize(panelDimensions);

        // create the base JlayeredPane
        scrollBarLayeredPane = new ScrollBarLayeredPane(panelDimensions);

        // create the base panel for the component
        basePanel = new ScrollPaneBase(panelDimensions);

        // add the base panel to the component
        scrollBarLayeredPane.add(basePanel,
                (Integer) (JLayeredPane.DEFAULT_LAYER + BASE_PANEL_LAYER));

        // initializes the scroll pane
        // add the horizontal scroll pane
        verticalBar = new ScrollBar(ScrollBar.VERTICAL);

        // add the horizontal bar to the scroll pane
        horizontalBar = new ScrollBar(ScrollBar.HORIZONTAL);

        // attach the scroll panes to the component
        // add the layered pane to the frame
        add(scrollBarLayeredPane);

        // add a mouse listener
        addMouseListener(this);

        // add a component listener
        addComponentListener(this);

        // add the mouse moved listener
        addMouseMotionListener(this);

        // add the mouse wheel listener
        addMouseWheelListener(this);

        // add the key listener
        addKeyListener(this);
    }

    /**
     * sets the viewport for the scroll pane. The added component must extend
     * ViewPort
     */
    public void setViewport(ViewPort component) {
        if (component instanceof ViewPort) {
            viewport = component;

            // add the viewport component to the 
            // JLayeredPane
            scrollBarLayeredPane.add(viewport,
                    (Integer) (JLayeredPane.DEFAULT_LAYER
                    + VIEWPORT_LAYER));

            // set the bounds for thr viewport
            viewport.setBounds(0, 0, viewport.getWidth(), viewport.getHeight());

            // redraw the scrollbar lyered pane
            scrollBarLayeredPane.repaint();

        } else {
            throw new RuntimeException("Added viewport must extend "
                    + "interface ViewPort");
        }
    }

    /**
     * gets the viewport for the component
     */
    public ViewPort getViewport() {
        return viewport;
    }

    /**
     * displays or hides the scroll bars. Ensures that the scroll bars are only
     * displayed if the dimensions of the pane are less than the minimum values
     */
    public void displayScrollPane(boolean state) {

        if (true == state && null != viewport) {
            if (height < viewport.getHeight()) {
                // add the vertical bar to the scroll pane
                scrollBarLayeredPane.add(verticalBar,
                        (Integer) (JLayeredPane.DEFAULT_LAYER
                        + VERTICAL_SCROLLBAR_LAYER));
            }

            if (width < viewport.getWidth() && null != viewport) {
                // add the horizontal bar to the scroll pane
                scrollBarLayeredPane.add(horizontalBar,
                        (Integer) (JLayeredPane.DEFAULT_LAYER
                        + HORIZONTAL_SCROLLBAR_LAYER));
            }           
        } else if (false == state) {

            // hide the scroll panes
            scrollBarLayeredPane.remove(verticalBar);
            scrollBarLayeredPane.remove(horizontalBar);
        }
        
        // repaint the scrollpane
        scrollBarLayeredPane.repaint();
    }

    /**
     * gets the selected Panel component and creates an Item Selected event
     */
    private ItemSelected createSelectedItemObject(MouseEvent e) {
        // stores returned item selected object
        ItemSelected obj = null ; 
        
        // get the selected component
        // make sure that there is a viewport
        if (null == viewport) {
            return null;
        }

        // get all the components in the viewport
        ArrayList<PanelComponent> components = viewport.getViewPortComponents();

        // get the mouse locations while considering how
        // far the viewport has moved 
        int mouseX = e.getX() + (int) topLeftViewPortCoords.x ;
        int mouseY = e.getY() + (int) topLeftViewPortCoords.y ;
        
        // find out which of the child components is highlighted
        for (int componentCounter = 0; componentCounter < viewport.getViewPortSize(); componentCounter++) {

            // get the current hovered on component                       
            PanelComponent currComponent = components.get(componentCounter);

            // get the dimension location for the hovered on component
            int topX = currComponent.getX();
            int topY = currComponent.getY();
            int bottomX = currComponent.getX() + currComponent.getWidth();
            int bottomY = currComponent.getY() + currComponent.getHeight();

            // find out if the cursor is on the component
            if (mouseX >= topX && mouseX <= bottomX
                    && mouseY >= topY && mouseY <= bottomY) {

                // create the Item Selected object
                obj = new ItemSelected(currComponent, currComponent) ;
                break ; // no need to continue...
            } 
        }
        
        // return...
        return obj ;
    }
    
    /**
     * loops through the panel component and fires the 
     * selected one
     */
    public void fireItemSelected(ItemSelected evt) {
        Object[] listeners = listenerList.getListenerList();
        
        for (int i = 0; i < listeners.length; i = i + 2) {
          if (listeners[i] == ItemSelectedListener.class) {
                ((ItemSelectedListener) listeners[i + 1]).itemSelected(evt);     
                break ;//  no point going on...
            }
        }
    }
    
    /**
     * highlights an existing component
     */
    public void highlightComponent(MouseEvent e) {
        // make sure that there is a viewport
        if (null == viewport) {
            return;
        }

        // get all the components in the viewport
        ArrayList<PanelComponent> components = viewport.getViewPortComponents();

        // get the mouse locations while considering how
        // far the viewport has moved 
        int mouseX = e.getX() + (int) topLeftViewPortCoords.x ;
        int mouseY = e.getY() + (int) topLeftViewPortCoords.y ;
        
        // find out which of the child components is highlighted
        for (int componentCounter = 0; componentCounter < viewport.getViewPortSize(); componentCounter++) {

            // get the current hovered on component                       
            PanelComponent currComponent = components.get(componentCounter);

            // get the dimension location for the hovered on component
            int topX = currComponent.getX();
            int topY = currComponent.getY();
            int bottomX = currComponent.getX() + currComponent.getWidth();
            int bottomY = currComponent.getY() + currComponent.getHeight();

            // find out if the cursor is on the component
            if (mouseX >= topX && mouseX <= bottomX
                    && mouseY >= topY && mouseY <= bottomY) {

                // change the components state 
                currComponent.setHoveredState(true);

            } else {
                // deselect all other components where the 
                // mouse does not hover on
                currComponent.setHoveredState(false);
            }
        }
    }

    /**
     * this method updates the dimensions for the layered pane as the component
     * is resized
     */
    private void setDimensions(ComponentEvent ev) {
        // clone instance of JLayeredPane
        ScrollPane pane = (ScrollPane) ev.getSource();

        // set the dimensions for the Layered Pane
        width = pane.getSize().width;
        height = pane.getSize().height;

        // get the width & height of the vertical & horizontal scroll bars
        int resizedWidth;
        int resizedHeight;

        if (null != viewport) {
            resizedWidth = getScaledLength(width, viewport.getWidth());
            resizedHeight = getScaledLength(height, viewport.getHeight());
        } else {
            // use the dimensions of the component 
            // as the default dimensions
            resizedWidth = getScaledLength(width, width);
            resizedHeight = getScaledLength(height, height);
        }

        // repaint the ListBase
        basePanel.setBounds(0, 0, width, height);

        // set/repaint the vertical scroll bar to the right edge of
        // the component
        verticalBar.setBounds(width - 15, 0, 4, height);
        verticalBar.setYDimensions(yScrolledPercentage * height, resizedHeight);

        // repaint the horizontal scroll bar to the 
        // bottom section of the component
        horizontalBar.setBounds(0, height - 15, width, 4);
        horizontalBar.setXDimensions(xScrolledPercentage * width, resizedWidth);

        // repaint
        repaint();
    }

    /**
     * This gets the scaled value for the scrollbars that appear if the
     * dimensions for the displayed panel with the list items are too small
     */
    private int getScaledLength(int scrollPaneLength, int viewportLength) {

        // initialize the value of the scaled length
        double scaledLength = (double) scrollPaneLength / viewportLength * scrollPaneLength;

        // return the scaled length
        return (int) scaledLength;
    }

    /**
     * This function determines if any of the scrollbars are being hovered on
     * and changes the cursor and color of the scrollbar
     */
    private boolean isScrollBarHovered(MouseEvent mouseEvent, ScrollBar verticalBar,
            ScrollBar horizontalBar) {

        boolean isScrollBarHovered = false;
        boolean isTrackBarHovered = false ;
        
        // create an array list and add the scrollbars
        ArrayList<ScrollBar> scrollBars = new ArrayList<ScrollBar>();
        scrollBars.add(verticalBar);
        scrollBars.add(horizontalBar);

        // lopp through to determine if a scrollbar is hovered
        for (int scrollBarsCounter = 0; scrollBarsCounter < scrollBars.size();
                scrollBarsCounter++) {
            ScrollBar currBar = scrollBars.get(scrollBarsCounter);

            if (mouseEvent.getX() >= currBar.getBounds().x 
                    && mouseEvent.getX() <= (currBar.getBounds().x + currBar.getWidth())
                    && mouseEvent.getY() >= currBar.getBounds().y
                    && mouseEvent.getY() <= (currBar.getBounds().y + currBar.getHeight())) {
                
                 // set the mouse cursor
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                
                // set that the scroll bar has been hovered on
                isTrackBarHovered = true ;
                
                // set the hovered state
                currBar.setTrackbarHovered(isTrackBarHovered);

                // find out if the scroll bar is hovered
                if (currBar == verticalBar && mouseEvent.getY() >= currBar.getTopY()
                        && mouseEvent.getY() <= currBar.getTopY() 
                        + currBar.getBarLength()) {
                    
                    // note that the scroll bar is hovered
                    isScrollBarHovered = true ;
                    
                    // note that the scroll bar is hovered
                    currBar.setScrollBarHovered(isScrollBarHovered);
                
                } else if (currBar == horizontalBar && mouseEvent.getX() >= currBar.getBounds().x
                        && mouseEvent.getX() <= currBar.getBounds().x 
                        + currBar.getBarLength()) {
                    
                    // set the scrolled bar state
                    isScrollBarHovered = true ;
                    
                    // note that the scroll bar is hovered
                    currBar.setScrollBarHovered(isScrollBarHovered);
                    
                } else {
                    // the mouse is not on top of any of the scroll bars
                    isScrollBarHovered = false ;
                    
                    // set this to the curr bar
                    currBar.setScrollBarHovered(isScrollBarHovered);
                }   
                
                // *no need to continue with processing
                break ;

            } else {

                // set that the scroll bar has been hovered on
                isTrackBarHovered = false ;
                isScrollBarHovered = false ; 
                
                // set the hovered state for the track bar
                currBar.setTrackbarHovered(isTrackBarHovered) ;

                // set the hovered state for the scroll bar
                currBar.setScrollBarHovered(isScrollBarHovered);
                
                // set the cursor
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                
                // initialise the topX and topY locations
                initialTopX = 0 ;
                initialTopY = 0;
            }            
        }

        return isScrollBarHovered;
    }

    /**
     ************************************
     * Overrides the MouseMoved methods
     ************************************
     */
    /**
     * This method updates the location of the viewport
     * as the scroll bar is dragged down
     * @param e 
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        
        // check if any of the scroll panes is being dragged
        if (verticalBar.isTrackBarHovered()) {
            
            // find the current y location for the mouse
            int currentYLocation = e.getY() ;
            
            // assign to the top Y location
            if (initialTopY == 0 
                    && verticalBar.isScrollbarHovered() == true) {
                
                initialTopY = currentYLocation ;
                return ;
            }
                        
            // scroll the vertical scroll bar to the location
            verticalUpdatePane(currentYLocation - initialTopY);
            
            // update the location of the initial topY to store the coords
            // for the last Y location
            initialTopY = currentYLocation ;
            
        } else if (horizontalBar.isTrackBarHovered()) {
            
            // find the current x location 
            int currentXLocation = e.getX() ;
            
            // assign the top X Location
            if (initialTopX == 0                    
                        && horizontalBar.isScrollbarHovered() == true) {
                initialTopX = currentXLocation ;
                return ;
            }
            
            // scroll the horizontal scroll bar to the location
            horizontalUpdatePane(currentXLocation - initialTopX) ;
            
            // update the location for the ccords for the last
            // X location for the horizontal bar
            initialTopX = currentXLocation ;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // highlight the selected component
        highlightComponent(e);
        
        // find out if any of the scroll bars should be 
        // selected
        isScrollBarHovered(e, verticalBar, horizontalBar);
    }

    /**
     * ****************************************
     * overrides the Component Resized methods
     * ****************************************
     */
    // **********************************
    // Implements mouse listener events *
    // to ensure that the coordinates   *
    // for the scroll bars are correctly*
    // calibrated                       *
    // **********************************
    @Override
    public void componentResized(ComponentEvent e) {
        setDimensions(e);
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        setDimensions(e);
    }

    @Override
    public void componentShown(ComponentEvent e) {
        setDimensions(e);
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        setDimensions(e);
    }

    // **********************************
    // Implements mouse listener events *
    // to ensure that the scroll bars   *
    // appear correctly                 *
    // **********************************
    @Override
    public void mouseClicked(MouseEvent e) {        
        requestFocusInWindow();       
        
        // create an Item Selected Event
        ItemSelected itemSelected = createSelectedItemObject(e) ;
        
        // fire event
        fireItemSelected(itemSelected) ;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // show that the component has a mouse hovered action         
        isHovered = true;
        displayScrollPane(isHovered);

        // highlight the component
        highlightComponent(e);

        // make sure that the component acquires 
        // focus
        requestFocusInWindow();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // show that the component does not have a hovered action
        isHovered = false;
        displayScrollPane(isHovered);
    }

    /**
     * this method either scrolls down or scrolls up the area viewable in the
     * scroll pane
     *
     * @param e
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // get the number of notches moved
        int notches = e.getWheelRotation();

        // update the location of the panel
        verticalUpdatePane(notches);
    }

    /**
     * vertically updates the scroll pane
     * @param notches the number of notches with which 
     * to update the component
     */
    private void verticalUpdatePane(int notches) {

        if (isWithinRange((int) topLeftViewPortCoords.y + notches,
                0, viewport.getHeight() - height)) {

            // increment the location of the topLeft Y Coords
            topLeftViewPortCoords.y += notches;

            // scroll the viewport to the desired location
            viewport.setBounds((int) -topLeftViewPortCoords.x, (int) - topLeftViewPortCoords.y,
                    viewport.getWidth(), viewport.getHeight());
            
            // update the vertical scroll bar location 
            // ensure that the scrolling is done based on the 
            // scrolled direction
            double scrollIncrement = (double) height / viewport.getHeight()
                    * notches;

            double yLocationVerticalScrollBar;

            if (notches < 0) {
                yLocationVerticalScrollBar = verticalBar.getTopY() + scrollIncrement;
            } else {
                yLocationVerticalScrollBar = verticalBar.getTopY() + scrollIncrement;
            }

            // update the location for the vertical scrollbar
            verticalBar.setYDimensions(yLocationVerticalScrollBar,
                    verticalBar.getBarLength());

            // calculate the percentage of scrolled done
            yScrolledPercentage = yLocationVerticalScrollBar / height;

        }
    }

    /**
     * Horizontally updates the scroll pane
     * @param notches the number of points to the left
     * or right that the scroll pane moves
     */
    private void horizontalUpdatePane(int notches) {
        
        if (isWithinRange((int) topLeftViewPortCoords.x + notches,
                0, viewport.getWidth() - width)) {

            // increment the location of the topLeft Y Coords
            topLeftViewPortCoords.x += notches;

            // scroll the viewport to the desired location
            viewport.setBounds((int) -topLeftViewPortCoords.x, (int) -topLeftViewPortCoords.y,
                    viewport.getWidth(), viewport.getHeight());
            
            // update the vertical scroll bar location 
            // ensure that the scrolling is done based on the 
            // scrolled direction
            double scrollIncrement = (double) width / viewport.getWidth()
                    * notches;

            double xLocationVerticalScrollBar;

            if (notches < 0) {
                xLocationVerticalScrollBar = horizontalBar.getTopX() + scrollIncrement;
            } else {
                xLocationVerticalScrollBar = horizontalBar.getTopX() + scrollIncrement;
            }

            // update the location for the vertical scrollbar
            horizontalBar.setXDimensions(xLocationVerticalScrollBar,
                    horizontalBar.getBarLength());

            // calculate the percentage of scrolled done
            xScrolledPercentage = xLocationVerticalScrollBar / width ;
        }
    }
    
    /**
     * this method checks to make sure that the value of the dimensions for the
     * topLeftCoords to be displayed are within range
     */
    public boolean isWithinRange(int valueTobeChecked, int minValue,
            int maxValue) {
        // finds out if a value is within range
        boolean isValueWithinRange = false;

        if (valueTobeChecked >= minValue
                && valueTobeChecked <= maxValue) {
            isValueWithinRange = true;
        }

        return isValueWithinRange;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
     * allows a user to navigate the scroll pane using the arrow keys
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // check for the keys pressed
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            // make the panel go up by 1px
            verticalUpdatePane(-1);
            
        } else if (keyCode == KeyEvent.VK_DOWN) {
            // make the panel go doen by 1px
            verticalUpdatePane(1);
            
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            // move the panel to the right
            horizontalUpdatePane(1);
            
        } else if (keyCode == KeyEvent.VK_LEFT) {
            // move the panel to the left
            horizontalUpdatePane(-1);
            
        }
    }
    
    /**
     * adds the itemSelectedListener
     */
    public void addItemSelectedListener(ItemSelectedListener listener) {
        listenerList.add(ItemSelectedListener.class, listener);
    }
    
    /**
     * removes the itemSelected Listener
     */
    public void removeItemSelectedListener(ItemSelectedListener listener) {
        listenerList.remove(ItemSelectedListener.class, listener);
    }
}
