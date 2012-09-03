package org.aprilsecond.customuicomponents.List.ListItem;

import java.awt.*;
import javax.swing.JLabel;

/**
 * This class implements a simple list item with the displayed
 * list item as the default text in a label
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class SimpleListItem extends AbstractListItem { 
    
    /*
     * stores the width for the component
     */
    public static int width = 300 ;
    
    /**
     * stores the height for the component
     */
    public static int height = 37 ;
    
     /**
     * stores the calendar Item to be displayed
     */
    private SimpleItem sItem ;
    
    /**
     * stores the list item displayed
     */
    private String listItemName ; 
    
    /**
     * stores the alias for the list item
     */
    private Object aliasListItemName ;
        
    /**
     * stores state if the component is highlighted
     */
    private boolean isHighlighted = false;
    
     /**
     * stores color of bg when unselected
     */
    private Color bgUnhighlightedColor = new Color(67, 70, 75);
    
    /**
     * stores color of the bg when selected
     */
    private Color bgHighlightedColor = new Color(159, 196, 14);

    /**
     * stores the background color for the component 
     * when it is selected
     */
    private Color selectedColor = new Color(0, 0, 0) ;

    /**
     * stores the font for the list items
     */
    private final Font LIST_ITEM_FONT 
            = new Font(Font.SANS_SERIF, Font.PLAIN, 15) ;
    
    /**
     * stores the color for the font of the list items
     */
    private final Color LIST_ITEM_FONT_COLOR 
            = new Color(255, 255, 255) ;
    
    /**
     * null constructor initializes component
     */
    public SimpleListItem() {
        super() ;
        
        // initializes the component
        initialize() ;        
    }
    
    /**
     * null constructor initializes displayed item
     */
    public SimpleListItem(String iItem) {
        super() ;
        
        // initialise the item to be displayed
        listItemName = iItem ;
        
        // initialize the component
        initialize() ;
    }    
    
    /**
     * constructor initializes displayed item and dimensions
     */
    public SimpleListItem(String item, int width, int height) {
        
        // initialize dimensions for component
        super(width, height) ;
        
        // initialise the item to be displayed
        listItemName = item ;
        
        // finalise initialization
        initialize();
    }
    
    /**
     * this method initializes the component
     */
    private void initialize() {
       
        sItem = new SimpleItem() ;
        
         // add the JLabel to the item
        addComponent(sItem) ;
    }
           
    /**
     * sets the list item
     */
    public void setListItem(String lItem) {
        listItemName = lItem ;
    }
    
    /**
     * gets the list item created
     */
    public String getListItem() {
        return listItemName ;
    }
    
    /**
     * overrides to set a component ID
     */
    public void setComponentIDAlias(Object aliasComponentID) {
        aliasListItemName = aliasComponentID ;
    }
    
    /**
     * gets the alias for the component
     */
    public Object getComponentIDAlias() {
        return aliasListItemName ;
    }
    
    /**
     * overrides the getComponentID() to return
     * a the string displayed for the list item
     */
    @Override
    public String getComponentID() {
        return getListItem() ;
    }
            
    @Override
    public void setHoveredState(boolean state) { 
       super.isSelectable = state ;

        // set the calendar item to selectable state
        isHighlighted = state ;
        repaint() ;
    }
    
    private class SimpleItem extends JLabel {
        
        public SimpleItem() {
            // set dimensions
            setPreferredSize(new Dimension(width, height));
            setMinimumSize(new Dimension(width, height)) ;
            setMaximumSize(new Dimension(width, height));

            // set transparency
            setOpaque(true);            
        }
        
        /**
         * stores whether the component is selected or not        
         * @param state selected state
         */
        public void setHighlightedState(boolean state) {
            isHighlighted = state;
            repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            // Clone the grahphics object
            Graphics2D graphics = (Graphics2D) g ;

            // set to anti alias
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (!isHighlighted) {
                if (isSelected()) graphics.setColor(selectedColor);
                else graphics.setColor(bgUnhighlightedColor);
            } else {
                graphics.setColor(bgHighlightedColor);
            }
            
            graphics.fillRect(0, 0, getWidth(), getHeight());
            
            graphics.setColor(LIST_ITEM_FONT_COLOR);
            graphics.setFont(LIST_ITEM_FONT);            
            graphics.drawString(listItemName, 10, getHeight()/2 + 5);
        }
    }
}