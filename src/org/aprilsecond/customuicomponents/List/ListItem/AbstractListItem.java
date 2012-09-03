package org.aprilsecond.customuicomponents.List.ListItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComponent;
import org.aprilsecond.customuicomponents.ScrollPane.PanelComponent;

/**
 * This class is used to display each of the list components
 * that makes a custom Items List
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public abstract class AbstractListItem 
    extends PanelComponent {
    
    /*
     * stores the minimum width for the component
     */
    public int minWidth = 66 ;
    
    /**
     * stores the minimum height for the component
     */
    public int minHeight = 27 ;
    
    /**
     * stores the highlight color when the component is
     * selected
     */
    private Color panelHighlightColor = new Color(0,0,0);    
    
    /**
     * stores if the item has been selected
     */
    private boolean selected = false ;
    
    /**
     * null constructor initializes default dimensions for component
     */
    public AbstractListItem() {        
        // complete initialization
        initialize() ;
    }
    
    /**
     * null constructor initializes dimensions for component
     */
    public AbstractListItem(int width, int height) {
        // get the dimensions for the component
        minWidth = width - getInsets().left - getInsets().right ;
        minHeight = height - getInsets().top - getInsets().bottom ;

        // complete initialization
        initialize() ;
    }
    
    /**
     * completes initialization of the Abstract List Item
     */
    private void initialize() {            
        
        // set the layout
        setLayout(new BorderLayout());
        
        // set the dimensions as preferred
        setPreferredSize(new Dimension(minWidth, minHeight));
        setMinimumSize(new Dimension(minWidth, minHeight)) ;
        setMaximumSize(new Dimension(minWidth, minHeight));
        
        // set the state of the transparency for the component
        setOpaque(true);        
    }
      
    /**
     * this method adds a component to
     * the list item      
     */
    public void addComponent(JComponent component) {
        add(component, BorderLayout.CENTER) ;
    }     
        
    @Override
    public void setSelectedState(boolean state) {
        selected = state ;
    }
    
    public boolean isSelected() {
        return selected ;
    }    
    
    /**
     * this method sets the color of the panel when
     * selected
     */
    public void setHighlightedColor(Color highlightedColor) {
        panelHighlightColor = highlightedColor ;
    }
    
    /**
     * this method gets the color of the panel when selected
     */
    public Color getHighlightColor() {
        return panelHighlightColor ;
    }
}
