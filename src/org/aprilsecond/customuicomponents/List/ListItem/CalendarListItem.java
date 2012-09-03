package org.aprilsecond.customuicomponents.List.ListItem;

import java.awt.*;
import java.util.Calendar;
import javax.swing.JPanel;
import org.aprilsecond.customuicomponents.Utils.CalendarTime;

/**
 * This class creates a custom list component that shows list
 * items next to a calendar date entry.
 *
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class CalendarListItem
    extends AbstractListItem {

    /**
     * stores the default width for the component
     */
    public static int width = 375 ;

    /**
     * stores the default height for the component
     */
    public static int height = 66 ;

    /**
     * stores the current date for the item
     */
    private Calendar date ;

    /**
     * stores the message for the item
     */
    private String cItemString ;

    /**
     * stores the calendar Item to be displayed
     */
    private CalendarItem cItem ;

     /**
      * stores state if the component is highlighted
      */
    private boolean isHighlighted = false ;
    
    /**
     * stores color of bg when unselected
     */
    private Color bgUnhighlightedColor = new Color(121, 137, 101);
    
    /**
     * stores color of the bg when selected
     */
    private Color bgHighlightedColor = new Color(101, 117, 131);

    /**
     * stores the background color for the component 
     * when it is selected
     */
    private Color selectedColor = new Color(0, 0, 0) ;
    
    /**
     * null constructor
     */
    public CalendarListItem() {
        // initializes the component
        initialize() ;
    }

    /**
     * constructor initializes time
     */
    public CalendarListItem(Calendar currDate) {
        date = currDate ;

        // initializes the component
        initialize() ;
    }

    /**
     * constructor initializes message
     */
    public CalendarListItem(String message) {
        cItemString = message ;

        // initializes the component
        initialize() ;
    }

    /**
     * constructor initializes time and message
     */
    public CalendarListItem(Calendar currDate, String message,
            int width, int height) {

        // initialize dimensions for the component
        super(width, height) ;

        // initialize the time and date
        date = currDate ;
        cItemString = message ;

        // initializes the component
        initialize() ;
    }

    /**
     * overrides the getCompoent ID method to obtain a unique date & time &
     * message concatenated string rather than the object toString() output
     */
    @Override
    public String getComponentID() {
        String AM_or_PM = (getDate().get(Calendar.AM_PM) == 0) ? "AM" : "PM";

        String timeString = "[" + getDate().get(Calendar.HOUR)
                + ":" + getDate().get(Calendar.MINUTE)
                + " " + AM_or_PM + "] ";

        // add the activity message
        timeString += cItemString;

        // return the time string
        return timeString;
    }

    /**
     * method adds the time for component
     */
    public void setDate(Calendar currDate) {
        date = currDate ;
    }

    /**
     * gets time for component
     */
    public Calendar getDate() {
        return date ;
    }

    /**
     * sets message for component
     */
    public void setMessage(String message) {
        cItemString = message ;
    }

    /**
     * gets message for component
     */
    public String getMessage() {
        return cItemString ;
    }

    /**
     * this method completes initialization of the component
     */
    private void initialize() {
        // finalise initialization of CalendarItem
        cItem = new CalendarItem() ;

        // add the calendar item to the component
        addComponent(cItem);
    }

    /**
     * overrides setHovered() to transfer responsibility of changing
     * set hovered state to CalendarItem
     */
    @Override
    public void setHoveredState(boolean state) {
        super.isSelectable = state ;

        // set the calendar item to selectable state
        isHighlighted = state ;
        repaint() ;
    }

    /**
     * sets the unselected background Color for component
     */
    public void setBgColor(Color bgColor) {
        bgUnhighlightedColor = bgColor;
    }

    /**
     * sets the selected background color for component
     */
    public void setSelectedBgColor(Color bgColor) {
        bgHighlightedColor = bgColor;
    }

    private class CalendarItem extends JPanel {        

        /**
         * stores the calendar date font
         */
        private final Font CALENDAR_FONT = new Font(Font.SANS_SERIF,
                Font.BOLD, 19) ;

        /**
         * stores the color for the calendar font
         */
        private final Color CALENDAR_FONT_COLOR = new Color(67, 70, 75) ;

        /**
         * stores the time font
         */
        private final Font TIME_FONT = new Font(Font.SANS_SERIF,
                Font.PLAIN, 12 ) ;

        /**
         * stores the color for the time font
         */
        private final Color TIME_FONT_COLOR = new Color(204, 204, 204) ;

        /**
         * stores the message font
         */
        private final Font MESSAGE_FONT = new Font(Font.SANS_SERIF,
                Font.PLAIN, 17) ;

        /**
         * stores the message font color
         */
        private final Color MESSAGE_FONT_COLOR = new Color(255, 255, 255) ;

        /**
         * null constructor initializes dimensions
         */
        public CalendarItem() {

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
            isHighlighted = state ;
            repaint() ;
        }
        
        /**
         * overrides paint component to draw custom component
         */
        @Override
        public void paintComponent(Graphics g) {
            // Clone the grahphics object
            Graphics2D graphics = (Graphics2D) g ;

            // set to anti alias
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // set & fill it with the base color
            // depending on the selection state
            if (!isHighlighted) {
                if (isSelected()) graphics.setColor(selectedColor);
                else graphics.setColor(bgUnhighlightedColor);
            } else {
                graphics.setColor(bgHighlightedColor);
            }

            graphics.fillRect(0, 0, getWidth(), getHeight());

            // draw the calendar graphic component
            // draw the radial gradient top component
            String[] days = {"Sunday","Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday"} ;

            // draw the day of the week
            graphics.setColor(new Color(183, 218, 44));
            graphics.setFont(new Font(Font.SANS_SERIF,
                    Font.PLAIN, 10));
            graphics.fillRect(16, 8, 54, 22) ;
            graphics.setColor(Color.WHITE);
            graphics.drawString(days[getDate()
                    .get(Calendar.DAY_OF_WEEK) - 1], 19, 18) ; // -1 because days are indexed from 1

            // draw the bottom rectangle
            graphics.setColor(Color.WHITE);
            graphics.fillRect(16, 22, 54, 30);

            // draw the date and month for component
            graphics.setFont(CALENDAR_FONT);
            graphics.setColor(CALENDAR_FONT_COLOR);
            graphics.drawString(
                    getDate().get(Calendar.DATE) + "." 
                    + (getDate().get(Calendar.MONTH)+1) + ""
                    , 20, 42);

            // add the time component
            graphics.setColor(TIME_FONT_COLOR);
            graphics.setFont(TIME_FONT) ;

            String AM_or_PM = (getDate().get(Calendar.AM_PM) == 0) ? "AM" : "PM" ;

            graphics.drawString(getDate().get(Calendar.HOUR)
                    + ":" + CalendarTime.convertTo2Digits(getDate().get(Calendar.MINUTE))
                    + " " + AM_or_PM
                    , 81, 20);

            // add the message component trimmed to fit
            graphics.setColor(MESSAGE_FONT_COLOR);
            graphics.setFont(MESSAGE_FONT);
            graphics.drawString(cItemString, 81, 39);
        }
    }
}
