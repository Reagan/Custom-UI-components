package org.aprilsecond.customuicomponents.TimeTable;

import java.awt.Dimension;
import java.awt.Point;

/**
 * This class associates Activity Items with their dimensions (width & 
 * height) as well as their location on a track. The class does not define
 * a visual component
 * 
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class ActivityItemProperties {
    
    /**
     * stores the activity Item
     */
    private ActivityItem activityItem ;
    
    /**
     * stores the dimensions for the component
     */
    private Dimension activityItemDimensions ;
    
    /**
     * stores the location for the activity Item
     * on a track. uses the top left position as the
     * datum
     */
    private Point activityItemLocation ;
    
    /**
     * constructor initializes all 3 components for class
     */
    public ActivityItemProperties(ActivityItem acItem, Dimension acDimens, 
            Point acLocation) {
        activityItem = acItem ;
        activityItemDimensions = acDimens ;
        activityItemLocation = acLocation ;                
    }
    
    /**
     * constructor initializes just the activity item and the dimensions. 
     * The location for the component is added later on
     */
    public ActivityItemProperties(ActivityItem acItem, Dimension acDimens) {
        activityItem = acItem ;
        activityItemDimensions = acDimens ;
    }
    
    /**
     * getter for the activityItem
     */
    public ActivityItem getActivityItem() {
        return activityItem ;
    }
    
    /**
     * setter for the activityItem
     */
    public void setActivityItem(ActivityItem acItem) {
        activityItem = acItem ;
    }
    
    /**
     * getter for the activityItemDimensions
     */
    public Dimension getActivityItemDimensions() {
        return activityItemDimensions ;
    }
    
    /**
     * setter for the activityItemDimensions
     */
    public void setActivityItemDimensions(Dimension acItemDimens) {
        activityItemDimensions = acItemDimens ;
    }
    
    /**
     * getter for the activityItem Location
     */
    public Point getActivityItemLocation() {
        return activityItemLocation ;
    }
    
    /**
     * setter for the activityItem location
     */
    public void setActivityItemLocation(Point acItemLocation) {
        activityItemLocation = acItemLocation ;
    }
}
