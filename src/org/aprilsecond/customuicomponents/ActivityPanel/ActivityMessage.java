package org.aprilsecond.customuicomponents.ActivityPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Calendar;
import javax.swing.JPanel;
import org.aprilsecond.customuicomponents.Utils.CalendarTime;

/**
 * This class implements an activity message that is displayed on 
 * an activity panel
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class ActivityMessage extends JPanel 
    implements MouseListener {
   
    /**
     * stores the width of the panel
     */
    private final int ACTIVITY_PANEL_WIDTH = 225 ;
    
    /**
     * stores the height of a title panel
     */
    private final int ACTIVITY_TITLE_PANEL_HEIGHT = 27 ;
       
    /**
     * stores the height of a body pane
     */
    private final int ACTIVITY_PANEL_HEIGHT = 58 ;
    /**
     * stores the activities for the panel
     */
    private ActivityMessageModel activityPanelModel ;        
    
    /**
     * stores the font for the time of the messages
     */
    private final Font TIME_FONT = new Font (Font.SANS_SERIF, 
            Font.BOLD, 12);
    
    /**
     * stores the color for the time font
     */
    private final Color TIME_COLOR = new Color(255, 255, 255) ;
    
    /**
     * stores the font for the messages content
     */
    private final Font MESSAGES_FONT = new Font (Font.SANS_SERIF, 
            Font.PLAIN, 12);
    
    /**
     * stores the Font for the TITLED messages
     */
    private final Font TITLED_MESSAGES_FONT = new Font (Font.SANS_SERIF,
            Font.PLAIN, 19) ;
    
    /**
     * stores the color for the messages font
     */
    private final Color MESSAGES_COLOR = new Color(0, 0, 0) ;
    
    /**
     * stores the color for the titled messages
     */
    private final Color TITLED_MESSAGES_COLOR 
            = new Color(157, 195, 12) ;
    
    /**
     * stores the color for the titles of Activity.TITLE
     */
    private final Color TITLE_COLOR = new Color(67, 70, 75) ;
    
    /**
     * stores the font for titles of Activity.TITLE
     */
    private final Font TITLE_FONT = new Font(Font.SANS_SERIF, 
            Font.BOLD, 12) ;
    
    /**
     * stores state on whether or not the activity message 
     * has a mouse over or not
     */
    private boolean highlighted = false ;
    
    /**
     * sets whether or not the border should be drawn
     */
    private boolean drawLowerBorder = true ;

    /**
     * stores the color for the line
     */
    private final Color LOWER_BORDER_COLOR = new Color(204, 204, 204) ;
    
    /**
     * stores the starting off x point for the activity
     */
    private final int X_LOWER_LINE_STARTING_POINT = 42 ;
    
    /**
     * stores the starting off X location for the horizontal line
     * drawn below a title message
     */
    private final int TITLE_LINE_STARTING_POINT = 25 ;
    
    /**
     * stores the border color that is drawn 
     * when activity message is hovered on
     */
    private final Color HIGHLIGHTED_BORDER_COLOR
            = new Color(204, 204, 204) ;
    
    /**
     * stores the title for the component for the case 
     * of an Activity.TITLED
     */
    private String activityTitle = "Title" ;
    
    /**
     * stores the color for the font used 
     * in displaying the date on the top right 
     * of the component
     */
    private Color DAY_MONTH_FONT_COLOR = new Color(255, 255, 255) ;
    
    /**
     * stores the font for the font used
     * in displaying the date on the top
     * right of the component
     */
    private Font DAY_MONTH_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12) ;
    
    /**
     * stores the background color for the component 
     * displayed on the top right section of the component
     */
    private Color DAY_MONTH_BGCOLOR = new Color(157, 195, 12) ;
    
    /**
     * constructor initializes the 
     * activities to be displayed on the panel
     * using an activities message model
     * @param activities 
     */
    public ActivityMessage(ActivityMessageModel aPanelModel ) {                
        // initialise the activities for the panel
        activityPanelModel = aPanelModel ;
        
        // complete initialization
        initialize() ;
    }
    
    /**
     * constructor for use when Activities are passed to it
     */
    public ActivityMessage(Activity activity) {
        // initialise the model
        activityPanelModel = new ActivityMessageModel(activity) ;
        
        // complete initialization
        initialize();
    }
    
    /**
     * completes initialization
     */
    public final void initialize() {
        if (Activity.TITLE_ACTIVITY == 
                activityPanelModel.getActivityType()) {
            // sets the  preferred dimensions for a title pane
            setPreferredSize(new Dimension(ACTIVITY_PANEL_WIDTH,
                    ACTIVITY_TITLE_PANEL_HEIGHT + 15));
            
            setMinimumSize(new Dimension(ACTIVITY_PANEL_WIDTH,
                    ACTIVITY_TITLE_PANEL_HEIGHT + 15));
        } else {
            // set the preferred dimensions 
            setPreferredSize(new Dimension(ACTIVITY_PANEL_WIDTH,
                    ACTIVITY_TITLE_PANEL_HEIGHT));
            
            // sets the dimensions of the panel
            setMinimumSize(new Dimension(ACTIVITY_PANEL_WIDTH,
                    ACTIVITY_PANEL_HEIGHT));
        }
        
        // ensure that the panel is transparent
        setOpaque(false);
        
        // set the mouse listener
        addMouseListener(this);
    }
    
    /**
     * draws the activity messages panel
     */
    @Override
    public void paintComponent(Graphics g) {
        
        // clone the graphics object
        Graphics2D graphics = (Graphics2D) g ;
        
        // get the insets for the messages section of the panel
        Insets insets = getInsets();
        
        // enable antialiasing
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);        
        
        // draw a bounding rectangle for each of the 
        // messages drawn if selected/hovered
        if (true == highlighted) {
            graphics.setColor(HIGHLIGHTED_BORDER_COLOR);
            graphics.drawRect(X_LOWER_LINE_STARTING_POINT + 1 - insets.left, 
                    1, getWidth()- 3 - X_LOWER_LINE_STARTING_POINT - insets.left - insets.right , 
                    getHeight() - 4 - insets.top - insets.bottom);
            
        } else {
            // erase any white border that may have been drawn
            // when the component was selected
            graphics.setColor(Color.WHITE);
            graphics.drawRect(X_LOWER_LINE_STARTING_POINT + 1 - insets.left, 
                    1, getWidth() - 3 - X_LOWER_LINE_STARTING_POINT - insets.right - insets.left, 
                    getHeight() - 4 - insets.top - insets.bottom);
        }
        
        // paint the time for the activity message
        graphics.setColor(TIME_COLOR);
        graphics.setFont(TIME_FONT);
        
        // draw the start time
        graphics.drawString(String.valueOf(activityPanelModel.getActivity()
                .getStartTime().get(Calendar.HOUR))
                + ":"
                + CalendarTime.convertTo2Digits(activityPanelModel.getActivity()
                .getStartTime().get(Calendar.MINUTE))
                , 7, getHeight()/2 - 10) ;
        
        // draw the separating hyphen
        graphics.drawString("-", 17 , getHeight()/2 );
        
        // drawing the stop time
        graphics.drawString(String.valueOf(activityPanelModel.getActivity()
                .getStopTime().get(Calendar.HOUR))
                + ":"
                + CalendarTime.convertTo2Digits(activityPanelModel.getActivity()
                .getStopTime().get(Calendar.MINUTE))
                , 7, getHeight()/2 + 12) ;
        
        // paint the activity message DATE bg
        graphics.setColor(DAY_MONTH_BGCOLOR);
        graphics.fillRect(getWidth() - 33, 0, 33, 14);
        
        // paint the activity message DATE text
        graphics.setColor(DAY_MONTH_FONT_COLOR);
        graphics.setFont(DAY_MONTH_FONT);
        graphics.drawString(activityPanelModel.getActivity()
                .getStartTime().get(Calendar.DATE) 
                + "." 
                + (activityPanelModel.getActivity()
                .getStartTime().get(Calendar.MONTH)+1), getWidth() - 31, 11);
        
        // if the activity type is TITLED, 
        // draw the title for the component
        if (Activity.TITLE_ACTIVITY == 
                activityPanelModel.getActivityType()) {
            
            // draw the title
            graphics.setColor(TITLE_COLOR);
            graphics.setFont(TITLE_FONT);
            graphics.drawString(activityTitle, 49, 16);
            
            // draw the separating line below the title
            graphics.setColor(LOWER_BORDER_COLOR) ;
            graphics.drawLine(X_LOWER_LINE_STARTING_POINT + 1, 
                    TITLE_LINE_STARTING_POINT, getWidth() - 2, 
                     TITLE_LINE_STARTING_POINT);
            
            // set the message for titled activity message
            graphics.setColor(TITLED_MESSAGES_COLOR) ;            
        } else {
        
            // set the message for the activity message
            // set the messages color
            graphics.setColor(MESSAGES_COLOR) ;        
        }
        
        // get the activity details 
        String activityMessage = activityPanelModel.getActivity().getActivityMessage() ;
        
        // add the message so that it dynamically adapts to the space 
        // available for it to be drawn on
        // create a LineBreak Measurer instance 
        AttributedString as = new AttributedString(activityMessage);
        int topBuffer = 0 ;
        
        // set the font type depending on the activity type
        if (Activity.TITLE_ACTIVITY
                == activityPanelModel.getActivityType()) {
            as.addAttribute(TextAttribute.FONT, TITLED_MESSAGES_FONT);
            topBuffer = 5 ;
        } else {
            as.addAttribute(TextAttribute.FONT, MESSAGES_FONT);
            topBuffer = 14 ;
        }
        
        AttributedCharacterIterator aci = as.getIterator();
        FontRenderContext frc = graphics.getFontRenderContext();
        LineBreakMeasurer lbm = new LineBreakMeasurer(aci, frc);
        
        // get the width for the component
        float wrappingWidth = getWidth() - 49 - insets.left - insets.right;
        float x = 49 + insets.left;
        
        // create the top most y dimensions for the string while regarding 
        // whether or not the activity is a titled activity or  a 
        // highlighted one
        float y = (Activity.TITLE_ACTIVITY == 
                activityPanelModel.getActivityType()) ? TITLE_LINE_STARTING_POINT 
                + topBuffer + insets.top
                : topBuffer + insets.top ;
        
        // ensure that the text displayed does not get too 
        // close to the bottom of the activity message
        while (lbm.getPosition() < aci.getEndIndex()) {
            if (y < getHeight() - 20) {
                TextLayout textLayout = lbm.nextLayout(wrappingWidth);
                y += textLayout.getAscent();
                textLayout.draw(graphics, x, y);
                y += textLayout.getDescent() + textLayout.getLeading();
                x = 49 + insets.left;
            } else {
                break ;
            }
        }

        // draw the lower border
        if (true == drawLowerBorder) {
            graphics.setColor(LOWER_BORDER_COLOR) ;
            graphics.drawLine(X_LOWER_LINE_STARTING_POINT, 
                    getHeight() - 1, getWidth(), 
                    getHeight() - 1);
        }
    }
    
    /**
     * gets the title used for Activity.TITLE activities
     */
    public String getActivityTitle() {
        return activityTitle ;
    }
    
    /**
     * sets the title for Activity.TITLED messages
     */
    public void setActivityTitle(String title) {
        activityTitle = title ;
    }
    
    /**
     * sets the drawing lower border code
     */
    public void drawLowerBorder(boolean status) {
        drawLowerBorder = status ;
    }
    
    /**
     * gets the draw lower border status (whether the line is to be drawn
     * or not
     */
    public boolean getLowerBorderDrawStatus() {
        return drawLowerBorder ;
    }
    
    /**
     * sets the highlighted mode of the activity message
     */
    public void setSelected(boolean selectedState) {
        highlighted = selectedState ;
        repaint();
    }
    
    /**
     * returns the selected state
     */
    public boolean getSelectedState() {
        return highlighted ;
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * display a mouse hovered state
     * @param e 
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        setSelected(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setSelected(false) ;
    }
}

