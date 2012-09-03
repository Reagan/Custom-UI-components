package org.aprilsecond.customuicomponents.TimeTable;

import java.util.ArrayList;

/**
 * This class stores the model for the timetable. It stores the time 
 * and activities for the activities. The model is composed of an 
 * array list with each entry holding an array with three elements i.e.
 * <ol>
 * <li>Activity</li>
 * <li>Start time</li>
 * <li>End time</li>
 * </ol>
 * 
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class TimetableModel {
    
    /**
     * stores the array list with the components 
     */
    private ArrayList<Activity> activities ;
    
    /**
     * constructor initializes null model 
     */
    public TimetableModel() {
        activities = new ArrayList<Activity>() ;
    }
    
    /**
     * constructor initializes model with activities
     */
    public TimetableModel(ArrayList<Activity> acts) {
        activities = acts ;
    }
    
    /**
     * adds item to model
     */
    public void addActivity(Activity act) {
        activities.add(act);
    }
    
    /**
     * removes an activity based on index
     */
    public void removeActivity(int index) {
        if (activities.get(index) != null) {
            activities.remove(index) ;
        }
    }
    
    /**
     * removes an activity based on prescence
     */
    public void removeActivity(Activity act) {
        activities.remove(act);
    }
    
    /**
     * adds an activity at a specific index
     */
    public void addActivity(Activity activity, int index) {
        activities.add(index, activity);
    }
    
    /**
     * gets the size of the components
     */
    public int getActivitiesSize() {
        return activities.size() ;
    }
    
    /**
     * gets the activities from the model
     */
    public ArrayList<Activity> getActivities() {
        return activities ;
    }
    
    /**
     * gets a specific activity
     */
    public Activity getActivity(int index) {
        return activities.get(index) ;
    }
    
    /**
     * clears the model
     */
    public void clear() {
        activities.clear();
    }
    
    /**
     * returns size of the model
     */
    public int size() {
        return activities.size() ;
    }
}
