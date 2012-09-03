package org.aprilsecond.customuicomponents.TimeTable;

import java.awt.*;
import java.util.Calendar;
import org.aprilsecond.customuicomponents.ScrollPane.PanelComponent;
import org.aprilsecond.customuicomponents.Utils.CalendarTime;

/**
 * This class implements an activity panel as displayed
 * on the timetable component. Since the entire component is enclosed 
 * in a scroll pane, it extends PanelComponent.
 * 
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class ActivityItem extends PanelComponent {
    
    /**
     * stores the activity details for the component
     */
    private Activity activity ;
    
    /**
     * stores dimensions for the component
     */
    private Dimension activityItemDimensions ;
    
    /**
     * stores the track location for the activity item
     */
    private Point trackLocation = new Point(0, 0) ;
    
    /**
     * stores the color for the left section for the component
     */
    private Color GREEN_SECTION_COLOR = new Color(153, 191, 7) ;
    
    /**
     * stores the white right section for the component
     */
    private Color WHITE_SECTION_COLOR = new Color(255, 255, 255) ;
    
    /**
     * stores the font for the start time for the activity
     */
    private Font timeFont = new Font(Font.SANS_SERIF, Font.PLAIN,
            12) ;
    
    /**
     * stores the font for the stop time for the activity
     */
    private Font messageFont = new Font(Font.SANS_SERIF, Font.PLAIN,
            12) ;
    
    /**
     * stores the color for the font for the start and stop time displayed
     */
    private Color timeStringColor = new Color(255, 255, 255) ;
    
    /**
     * stores the color for the font for the messages displayed
     */
    private Color messageStringColor = new Color(67, 70, 75) ;
    
    /**
     * null constructor initializes the activity 
     */
    public ActivityItem(Activity act) {
        // initialize the activity
        activity = act ;
        
        // initialize the dimensions for the component
        setActivityItemDimensions(activity)  ;
    }
    
    /**
     * sets the activity item dimensions for the component
     */
    private void setActivityItemDimensions(Activity act) {
           
        // set the width for an activity item
        int activityItemWidth = Track.TRACK_WIDTH 
                - 2*Track.ACTIVITY_ITEM_H_BUFFER ;
        
        // get the height for the component
        // get the difference in the start & stop times
        // for the component
        int timeDifference = (act.getStopTime().get(Calendar.HOUR_OF_DAY) 
                - act.getStartTime().get(Calendar.HOUR_OF_DAY)) * 60 
                + (act.getStopTime().get(Calendar.MINUTE) 
                - act.getStartTime().get(Calendar.MINUTE)) ; 
        
        // calculate the dimensions for the activity item based on 
        // total length for the component (PS: 1440 minutes = 1 day)
        int activityItemHeight = (int) ((double) Timetable.componentHeight/1440
                * timeDifference) ;
        
        // set them as the dimensions for the component
        activityItemDimensions = new Dimension(activityItemWidth, activityItemHeight) ;
        
        // set the track location
        // x location
        trackLocation.x = Track.ACTIVITY_ITEM_H_BUFFER ;
        
        // y location based on the start time for the component
        // PS: there are 1440 minutes in a day
        trackLocation.y = (int) ((double)Timetable.componentHeight / 1440 * 
                    (act.getStartTime().get(Calendar.HOUR_OF_DAY) * 60 
                    + act.getStartTime().get(Calendar.MINUTE))) ;
    }
    
    /**
     * add an activity to the item
     */
    public Activity getActivity() {
        return activity ;
    }
    
    /**
     * sets an activity
     */
    public void setActivity(Activity act) {
        activity = act ;
    }
    
    /**
     * gets the dimensions for the component 
     */
    public Dimension getActivityItemDimensions() {
        return activityItemDimensions ;
    }
    
    /**
     * sets the track location for the ActivityItem
     */
    public void setTrackLocation(Point tLocation) {
        trackLocation = tLocation ;
    }
    
    /**
     * gets the track location for an ActivityItem
     */
    public Point getTrackLocation() {
        return trackLocation ;
    }
    
    /**
     * overrides the getComponentID method to create custom
     * identifier for the component
     */
    @Override
    public String getComponentID() {
        String resString = activity.getStartTime().get(Calendar.HOUR_OF_DAY)
                + ":" + CalendarTime.convertTo2Digits(activity.getStartTime().get(Calendar.MINUTE)) + " ";        
        
        // indicate the message for the activity        
        resString += activity.getActivity() ;
        
        return resString ;
    }
    
    /**
     * draws an activity component. This method extracts details about the  
     * activity and draws the component based on the length of the timetable
     * and details about the activity
     */
    @Override
    public void paintComponent(Graphics g) {
        // clone the graphics object
        Graphics2D graphics = (Graphics2D) g ;
     
        
        // fill the right green section of the component
        graphics.setColor(GREEN_SECTION_COLOR) ;
        graphics.fillRect(0, 0, 42, activityItemDimensions.height) ;
        
        // fill the left white section of the component
        graphics.setColor(WHITE_SECTION_COLOR);
        graphics.fillRect(42, 0, activityItemDimensions.width - 42, 
                activityItemDimensions.height);
        
        // indicate the time for the activity
        graphics.setFont(timeFont);
        graphics.setColor(timeStringColor);
        graphics.drawString(activity.getStartTime().get(Calendar.HOUR_OF_DAY)
                + ":" + CalendarTime.convertTo2Digits(activity.getStartTime().get(Calendar.MINUTE)),
            5, 12);        
        
        // indicate the message for the activity
        graphics.setFont(messageFont);
        graphics.setColor(messageStringColor);
        graphics.drawString(activity.getActivity(), 50, 10);        
    }

    @Override
    public void setSelectedState(boolean state) {}
}
