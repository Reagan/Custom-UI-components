package org.aprilsecond.customuicomponents.ActivityPanel;

/**
 * This class stores the model for the activities(i.e. the
 * time and activity details for a message displayed on an 
 * activity message panel
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class ActivityMessageModel {
    
    /**
     * stores the panel activities
     */
    private Activity displayedActivity ;
    
    public ActivityMessageModel(Activity activity) {
        // initialise the activities displayed
        displayedActivity = activity ;
    }
    
    /**
     * gets the model
     */
    public ActivityMessageModel getModel() {
        return this ;
    }
    
    /**
     * sets the model
     */
    public void setActivityPanelModel(Activity activities) {
        displayedActivity = activities ;
    }
    
    /**
     * gets a specific activity
     */
    public Activity getActivity() {
        return displayedActivity ;
    }
    
    /**
     * gets the number of activities in the model
     */
    public int getActivitysLength() {
        return 1 ;
    }
    
    /**
     * gets the activity type
     */
    public int getActivityType() {
        return displayedActivity.activityType() ;
    }
}
