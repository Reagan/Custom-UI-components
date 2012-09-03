package org.aprilsecond.customuicomponents.TimeTable;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Activity Items are placed on Tracks on the Timetable component. 
 * A Track object tracks the location for an ActivityItem and 
 * the number of activityItems within it. The Object does not have a 
 * visual representation
 * 
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class Track {
    
    /**
     * stores the dimensions and location for the activities
     * on the Track
     */
    private ArrayList<ActivityItemProperties> activityProperties ;
    
    /**
     * stores the width of a track
     */
    public final static int TRACK_WIDTH = 157 ;
    
    /**
     * stores the horizontal buffer for each activityItem
     * added to the track
     */
    public final static int ACTIVITY_ITEM_H_BUFFER = 10 ;
    
    /**
     * stores the rectangle within which the track is contained
     */
    public Rectangle boundingRect ; 
    
    /**
     * stores the vertical buffer for all activity item components added 
     * after the first one on the track
     */
    public final static int ACTIVITY_ITEM_V_BUFFER = 10 ;
    
    /**
     * constructor initializes ActivityItemProperties 
     */
    public Track(Rectangle rect) {
        // initialize the properties
        activityProperties = new ArrayList<ActivityItemProperties>() ;
        
        // initialize the bounding rect
        boundingRect = rect ;
    }
    
    /**
     * constructor initializes with the activityProperties to be added
     */
    public Track(ArrayList<ActivityItem> acts, Rectangle rect) {
        
        // initialize the bounding rect
        boundingRect = rect ;
        
        // initialise the properties object
        activityProperties = new ArrayList<ActivityItemProperties>(acts.size()) ;
        
        // convert to ActivitiesProperties and add them to storage collection
        for (int actsCounter = 0 ; actsCounter < acts.size() ;
                actsCounter ++ ) {
            
            // add the activity Items
            addActivityItem(acts.get(actsCounter));
        }
    }
    
    /**
     * constructor initializes with the number of ActivityItems to add
     */
    public Track(int numberOfActivityItems) {
        activityProperties = new ArrayList<ActivityItemProperties>(numberOfActivityItems) ;
    }
    
    /**
     * adds activity Items to itself
     */
    public ActivityItemProperties addActivityItem(ActivityItem actItem) {
        
        // get the dimensions for the component
        Dimension activityItemDimens = new Dimension(actItem.getActivityItemDimensions().width, 
                actItem.getActivityItemDimensions().height) ;
        
        // calculate the location for the component
        Point greatestTracklocation = new Point(0, 0) ;
        
        for (int activityItemsCounter = 0 ; activityItemsCounter < activityProperties.size() ;
                activityItemsCounter ++) {
            
            // get the currActivity
            ActivityItem currItem = activityProperties
                    .get(activityItemsCounter).getActivityItem() ;
            
            // get its location
            Point currItemLocation = currItem.getTrackLocation() ;
            
            // stores whether or not there exists a component 
            // at the desired location for the component
            
            // find out if there is an overlapping component 
            // at that location and return false 
            // this checks if any of the left coordinates of
            // the left coords are within the item proposed
            // to be added
            // !+ (TRICKY SECTION :- CHANGE AT YOUR OWN PERIL !+
            if ((currItem.getTrackLocation().y >= actItem.getTrackLocation().y
                    && currItem.getTrackLocation().y <= (actItem.getTrackLocation().y 
                    + actItem.getActivityItemDimensions().height)) 
                    || ( (currItem.getTrackLocation().y + currItem.getActivityItemDimensions().height)
                        >= actItem.getTrackLocation().y
                        && (currItem.getTrackLocation().y + currItem.getActivityItemDimensions().height)
                        <= (actItem.getTrackLocation().y + actItem.getActivityItemDimensions().height)
                    )) {
                
                // do not allow the component to be added to this track
                return null ;
                
            } else {
                // increment the coordinates for the lowermost location 
                // for the component
                greatestTracklocation.x = currItemLocation.x ;
                greatestTracklocation.y = currItemLocation.y 
                        + currItem.getActivityItemDimensions().height ;
            }                        
        }
        
        // create the ActivityItemProperties object
        ActivityItemProperties acProperties  = new 
                ActivityItemProperties(actItem, activityItemDimens, 
                greatestTracklocation);
        
        // add it to the main container
        activityProperties.add(acProperties);    
        
        // return the activities item properties
        return acProperties ;
    }
    
    /**
     * gets an activity item based on an index
     */
    public ActivityItem getActivityItem(int index) {
        ActivityItem currActivity = null ;
        
        if (activityProperties.size() < index) {
            return null ;
        } else {
            // get the current activity Property item
            ActivityItemProperties currProperty = activityProperties.get(index) ;
            
            // get the activity item for the component
            currActivity = currProperty.getActivityItem() ;
        }
        
        // return the activity
        return currActivity ;
    }
    
    /**
     * removes an activity item from track
     */
    public void removeActivityItem(ActivityItem act) {
        for (int actsCounter = 0 ; actsCounter < activityProperties.size() ;
                actsCounter ++ ) {
            
            // check the current activity property obejct for the 
            // property
            if (activityProperties.get(actsCounter).getActivityItem() 
                    == act) {
                
                // and remove it
                activityProperties.remove(act);
                break ;
            }
        }
    }
    
    /**
     * gets the number of activityItems on the track
     */
    public int getActivitiesSize() {
        return activityProperties.size() ;
    }
    
    /**
     * returns the activity item properties objects for all
     * activities present on the track 
     */
    public ArrayList<ActivityItemProperties> getActivityItemProperties() {
        return activityProperties ;
    }
    
    /**
     * returns the activity items on the track
     */
    public ArrayList<ActivityItem> getActivityItems() {
        // create the activity items array list
        ArrayList<ActivityItem> actsItems = new 
                ArrayList<ActivityItem>(activityProperties.size()) ;
        
        // populate the arraylist
        for (int actsCounter = 0 ; actsCounter < activityProperties.size() ;
                actsCounter ++ ){
            actsItems.add(activityProperties.get(actsCounter).getActivityItem()) ;
        }
        
        // return it!
        return actsItems ;
    }
    
    /**
     * returns the absolute location for the rect that 
     * encompases the track
     */
    public Rectangle getBoundingRect() {
        return boundingRect ;
    }
}
