package org.aprilsecond.customuicomponents.TimeTable ;

import java.awt.*;
import java.util.ArrayList;
import org.aprilsecond.customuicomponents.ScrollPane.PanelComponent;
import org.aprilsecond.customuicomponents.ScrollPane.ViewPort;

/**
 * This creates a time table where various calendar items can be 
 * displayed. The class extends viewport and is thus scroll able. The calendar items
 * extends PaintComponent and are thus selectable. The activities are displayed on
 * no more than 3 columns and are added horizontally so that they do not
 * overlap as they are displayed on the TimeTable.
 * 
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class Timetable extends ViewPort {
 
    /**
     * stores the model for the timetable
     */
    private TimetableModel timeTablemodel ;
   
    /**
     * stores the tracks for the Activity items
     */
    private ArrayList<Track> activityItemsTracks ;
    
    /**
     * stores default number of tracks for component
     */
    private final int DEFAULT_NO_OF_TRACKS = 3 ;
    
    /**
     * stores the number of tracks the component has
     */
    private int numberOfTracks = 3 ;
    
    /**
     * stores the initial time displayed on the timetable 
     */
    private int initialDisplayTime = 0; 
    
    /**
     * stores the width for the component. has default length
     */
    public static int componentWidth = 314 ;
    
    /**
     * stores the height for the component. has default length
     */
    public static int componentHeight = 480 ;
    
    /**
     * stores the background color for the component
     */
    private Color bgColor = new Color(88, 105, 122) ;
    
    /**
     * stores the color for the rectangles with the time markings
     */
    private Color timeMarkingsRectbgColor = new Color(65, 75, 84) ;
    
    /**
     * stores the width for the space used by the component to display
     * the current time 
     */
    private final int TIME_MARKINGS_WIDTH = 35 ;
    
    /**
     * stores the color for the font with the time markings
     */
    private final Font TIME_MARKINGS_FONT = new Font(Font.SANS_SERIF, 
            Font.PLAIN, 12) ;
    
    /**
     * stores the color for the font with the time markings
     */
    private final Color TIME_MARKINGS_COLOR = new Color (255, 255, 255);
    
    /**
     * null constructor initializes default number of
     * tracks for component and model
     */
    public Timetable(TimetableModel model) {
        
        // set the number of tracks
        numberOfTracks = DEFAULT_NO_OF_TRACKS ;
        
         // set the model
        setTimetableModel(model) ;
    }
    
    /**
     * constructor initializes the model and number of 
     * tracks for the component
     */
    public Timetable(TimetableModel model, int nTracks) {
        
        // set the number of tracks
        numberOfTracks = nTracks ;        
        
         // set the model
        setTimetableModel(model) ;
    }
    
    /**
     * constructor initializes the initial time to be displayed
     */
    public Timetable(TimetableModel model, int nTracks
            , int initialDisplayHour) {
        
        // check the initial display time
        if (initialDisplayHour < 0 || initialDisplayHour > 23) {
            throw new RuntimeException("Start hour for timetable should be between 0 and 23");
        } else {
            initialDisplayTime = initialDisplayHour;
        } 
        
        // set the number of tracks
        numberOfTracks = nTracks ;
        
         // set the model
        setTimetableModel(model) ;
    }
    
    /**
     * completes the initialization process
     */
    public final void drawTable() {        
        
        // calculate and set the dimensions for the component
        componentWidth = numberOfTracks * Track.TRACK_WIDTH + 
                TIME_MARKINGS_WIDTH ;
     
        // set the dimensions for the component
        setSize(componentWidth, componentHeight) ;
        
        // set the layout for the component
        setLayout(null);
        
        // display the components on their tracks
        if (null != timeTablemodel) {
            addTracksAndComponents() ;
        }
    }
    
   /**
     * Overrides the methods for the ViewPort class defining the details 
     * about the calendar items included within the timetable
     * @return number of paint components in timetable
     */
    @Override
    public int getViewPortSize() {
        return timeTablemodel.getActivitiesSize() ;
    }
 
    /**
     * returns the ActivityItems for the timetable
     * @return 
     */
    @Override
    public ArrayList<PanelComponent> getViewPortComponents() {
        
        ArrayList<PanelComponent> activityItems 
                = new ArrayList<PanelComponent>() ;
        
        // loop through the tracks and get all the 
        // components to load up
        for (int tracksCounter = 0 ; tracksCounter < activityItemsTracks.size() ;
                tracksCounter ++) {
            
            // loop through the activityItems in each of the tracks
            // and add them 
            Track currTrack = activityItemsTracks.get(tracksCounter) ;
            
            for (int activityitemsCounter = 0 ; 
                    activityitemsCounter < currTrack.getActivitiesSize();
                    activityitemsCounter ++) {
                // get the current activity item
                ActivityItem currActivityItem = currTrack.getActivityItem(activityitemsCounter) ;
                activityItems.add(currActivityItem);
            }
        }
        
        // return the activity items
        return activityItems ;
    }

    /**
     * adds/replaces the model for the component
     */
    public final void setTimetableModel(TimetableModel model) {
        timeTablemodel = model ;
        drawTable();
    }
    
    /**
     * gets a specific view port component for the various paint components 
     * for a component
     * @param componentIndex
     * @return 
     */
    @Override
    public PanelComponent getViewPortComponent(int componentIndex) {
        int counter = 0 ;
        ActivityItem selectedItem = null ;
        
        // loop througn each of the tracks
        for (int tracksCounter = 0 ; tracksCounter < activityItemsTracks.size() ;
                tracksCounter ++) {
            
            // loop through the tracks looking for the indexed component
            for (int activityItemsCounter = 0 ; 
                    activityItemsCounter < activityItemsTracks
                        .get(tracksCounter).getActivitiesSize();
                    activityItemsCounter ++) {
                
                // find out if the components match
                if (counter == componentIndex) {
                    selectedItem = activityItemsTracks.get(tracksCounter)
                            .getActivityItem(activityItemsCounter) ;
                }
                
                // increment the counter from within the loop
                counter ++ ;
            }
        }
        
        // return the found activity item
        return selectedItem ;
    }
    
    /**
     * adds the tracks with the various components for the component
     */
    public void addTracksAndComponents() {
        // make sure there are no tracks
        clearTracksAndActivityItems() ;
        
        // creates the tracks
        createTracks() ;
        
        // add activities to tracks
        addActivitiesToTracks(activityItemsTracks) ;
        
        // display Activity Items
        displayActivityItems(activityItemsTracks) ;
    }
    
    /**
     * this method ensures that there are no 
     * tracks and track components on the timetable components
     */
    private void clearTracksAndActivityItems() {
        removeAll();
        repaint();
    }
    
    /**
     * this method creates the tracks that hold the activity item components
     */
    private void createTracks() {
      
        // initialize store for item tracks
        activityItemsTracks = new ArrayList<Track>(numberOfTracks) ;
        
        // create each of the tracks 
        for (int tracksCounter = 0 ; tracksCounter < numberOfTracks ;
                tracksCounter ++) {
            
            // create a new track with the location for the component
            Track newTrack = new Track(new Rectangle((TIME_MARKINGS_WIDTH + 
                    tracksCounter * Track.TRACK_WIDTH), 
                    0, Track.TRACK_WIDTH, componentHeight)) ;
            
            // add the track to the list of tracks
            activityItemsTracks.add(newTrack);
        }  
    }
    
    /**
     * adds the available activities as stored in the model
     * to the tracks
     */
    private void addActivitiesToTracks(ArrayList<Track> tracks) {
        
        // add the components to the tracks
        // check while adding that the components do not overlap        
        for (int activityItemsCounter = 0 ; 
                activityItemsCounter < timeTablemodel.getActivitiesSize();
                activityItemsCounter ++) {
            
            // get the current activity
            Activity act = timeTablemodel.getActivity(activityItemsCounter) ;
            
            // create an activity item from the component
            ActivityItem actItem = new ActivityItem(act) ;
            
            // add the activity Item to the track
            addActivityItemToTrack(actItem,
                    tracks) ;            
        }
    }
    
    /**
     * This method adds a specific activity to whichever of the
     * tracks that might be free and creates an activityItemProperty
     * object from the activity.
     * 
     * @param actProperties  property object for a specific activity
     * @param tracks total number of tracks available for the component
     */
    private void addActivityItemToTrack(ActivityItem actItem, 
            ArrayList<Track> tracks) {
                
        for (int tracksCounter = 0 ; tracksCounter < tracks.size() ;
                tracksCounter ++) {
            // get the track
            Track currTrack = tracks.get(tracksCounter) ;
            
            // try to add the component to the track
            if (null != currTrack.addActivityItem(actItem)) {
                // means that the item has been added to a track
                //  no need to continue...check in another track!
                break ;
            }
        }
    }
    
    /**
     * This method actually displays the activity items as stored 
     * in the activities Items Tracks components by looping through
     * these tracks
     */
    private void displayActivityItems(ArrayList<Track> activityItemsTracks) {
        
        for (int tracksCounter = 0 ; tracksCounter < activityItemsTracks.size() ;
                tracksCounter ++ ) {
            
            // get the current track
            Track currTrack = activityItemsTracks.get(tracksCounter) ;
            
            // for each track, get the activity items contained within
            for  (int activityItemsCounter = 0 ; activityItemsCounter < currTrack.getActivitiesSize();
                    activityItemsCounter ++) {
                
                // get the activity item
                ActivityItem currItem = currTrack.getActivityItem(activityItemsCounter) ;
                
                // get the location of the activity item
                currItem.setBounds(currItem.getTrackLocation().x + 
                            currTrack.getBoundingRect().x, 
                        currItem.getTrackLocation().y + 
                            currTrack.getBoundingRect().y, 
                        currItem.getActivityItemDimensions().width, 
                        currItem.getActivityItemDimensions().height);
                
                // add it to the timetable
                add(currItem) ;
            }
        }
        
        validate();
    }
    
    /**
     * overrides the paint component method to draw the stripped 
     * background
     */
    @Override
    public void paintComponent(Graphics g) {
        // clone the graphics object
        Graphics2D graphics = (Graphics2D) g ;
        
        // make everything antialiased
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        // cater for insets/border
        int height = getHeight() - getInsets().top - getInsets().bottom ;
        int width = getWidth() - getInsets().left - getInsets().right ;
        
        // fill the background rectangle
        graphics.setColor(bgColor);
        graphics.fillRect(0, 0, width, height);
        
        // fill the rectangles to show the time markings
        Point offset = new Point(TIME_MARKINGS_WIDTH, 0) ;
        int currY = 0 ; // stores the location for the currently drawn rectangle
        int currTime = 0 ; // stores the currently displayed time
        int heightOfTimeRects = 10 ;
        
        // loop through drawing the rectangles
        for ( ; currY < componentHeight; ) {
            // draw the time string
            graphics.setFont(TIME_MARKINGS_FONT);
            graphics.setColor(TIME_MARKINGS_COLOR);
            graphics.drawString(String.valueOf(currTime) + ":00", 0, currY) ;
            
            // draw the rect           
            graphics.setColor(timeMarkingsRectbgColor);
            graphics.fillRect(offset.x, currY, componentWidth - offset.x, 
                    heightOfTimeRects);
            
            // increment
            currY += 20 ;
            currTime ++ ; 
        }
    }
}
