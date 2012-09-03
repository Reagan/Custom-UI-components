package org.aprilsecond.customuicomponents.clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.EventListenerList;
import org.aprilsecond.customuicomponents.clock.clockhand.CenterCone;
import org.aprilsecond.customuicomponents.clock.clockhand.HoursHand;
import org.aprilsecond.customuicomponents.clock.clockhand.MinutesHand;
import org.aprilsecond.customuicomponents.clock.clockhand.SecondsHand;

/**
 * This class implements a custom clock component that 
 * displays the current time as specified or 
 * obtained from a UI component.
 * 
 * Wav file for the clock click sound downloaded from
 * http://www.freesound.org/people/TicTacShutUp/sounds/407
 * 
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public final class Clock extends JPanel
    implements MouseListener {
    /**
     * stores the width of the panel
     */
    public static final int PANEL_WIDTH = 168 ;
    
    /**
     * stores the height of the panel
     */
    public static final int PANEL_HEIGHT = 193 ;
    
    /** 
     * stores the path to the file with the clock image
     */
    private String clockFilePath 
            = "resources" 
            + File.separator             
            + "images"
            + File.separator
            + "clock.png";
    
    /**
     * stores the URL to the clock sound
     */
     private String clockTickSoundPath 
             = "resources" 
             + File.separator 
             + "audio"
             + File.separator
             + "clock.wav";
    /**
     * stores the state on whether or not 
     * the clock sound should be displayed
     */
    private boolean playClockSound = false;

    /**
     * stores the clock image
     */
    private Image clockImage ;
    
    /**
     * stores the hours clock hand
     */
    private HoursHand hoursHand ;
        
    /**
     * stores the minutes clock hand
     */
    private MinutesHand minutesHand ;
    
    /**
     * stores the second clock hand
     */
    private SecondsHand secondsHand ;

    /**
     * stores the clock Model
     */
    private ClockModel clockModel ;
    
    /**
     * stores center covering cone
     */
    private CenterCone centerCone ;
    
    /**
     * stores EventListenerList for component
     */
    private EventListenerList listenerList = new EventListenerList() ;
    
    /**
     * stores the clock timer that is responsible for animating the clock
     * hands
     */
    private Timer clockTimer; 
    
    /**
     * delay before the timer is activated
     */
    private final int DELAY = 1000 ;
    
    /**
     * stores the clip with the sound
     */
    private Clip clip ;
    
    /**
     * stores the buffered image with the clock
     */
    private BufferedImage _clockImage = null ;
    
    /**
     * Null constructor, uses default clock path
     */
    public Clock() {
        initialise() ;
    }
    
    /** 
     * constructor initializes file icon for the 
     * clock image
     */
    public Clock(String pathToClock) {
        setClockIconPath(pathToClock);        
        initialise() ;
    }
    
    /**
     * completes the initialization process
     */
    private void initialise() {
        // set the dimensions of the panel
        setSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        
        // set the visibility of the panel
        setOpaque(false);
        
        // set the layout type
        setLayout(new StackLayout());
      
        // get the clock image
        try {
            // fetch the image for the clock file icon
            File clockImageFile = new File(clockFilePath) ;
            clockImage = ImageIO.read(clockImageFile);
        } catch (IOException ex) {
            System.out.println("Unable to access clock image [ " + ex.toString()
                    + " ]");
        }  
         
        // initialise the clock hands
        hoursHand = new HoursHand() ;
        minutesHand = new MinutesHand() ;
        secondsHand = new SecondsHand() ;
        
        // add the event listener list to the clock
        // hands. This EventListenerList is updated when the clock
        // hands move
        hoursHand.addEventListenerList(listenerList);
        minutesHand.addEventListenerList(listenerList);
        secondsHand.addEventListenerList(listenerList);
    
        // initialise the center cone
        centerCone = new CenterCone() ;
        
        // add the hours, minute and second hands        
        add(hoursHand, StackLayout.TOP) ;
        add(minutesHand, StackLayout.TOP) ;
        add(secondsHand, StackLayout.TOP) ;        
        add(centerCone, StackLayout.TOP) ;
        
        // initialise the clock model
        clockModel = new ClockModel() ;
        
        // initialise the mouse listener
        addMouseListener(this);
        
        // initialize the timer
        clockTimer = new Timer(DELAY, new UpdateClockHands()) ;        
    }
    
    /**
     * This method adds the clock hands depending on the time 
     * to be displayed on the clock hands
     */
    public void drawClockHands(Time time) {
        
        // sets the new time for the model
        clockModel.setTime(time);
        
        // draw the Hours hand
        hoursHand.drawHand(time.getHour());
        
        // draw the minutes hand
        minutesHand.drawHand(time.getMinute());
        
        // draw the seconds hand
        secondsHand.drawHand(time.getSecond());
    }
    
    /**
     * gets the clock model
     */
    public ClockModel getClockModel() {
        return clockModel ;
    }
    
    /** 
     * sets the clock model
     */
    public void setClockModel(ClockModel cModel) {
        clockModel = cModel ;
    }
    
    /**
     * sets the path to the clock file icon
     */
    public void setClockIconPath(String pathToClockIcon) {
        clockFilePath = pathToClockIcon ;
    }
    
    /**
     * gets the path to the clock file icon
     */
    public String getClockIconPath() {
        return clockFilePath ;
    }
    
    /**
     * add Clock Selected Listener
     */
    public void addClockSelectedListener(ClockSelectedListener listener) {
        listenerList.add(ClockSelectedListener.class, listener) ;
    }
    
    /**
     * removes the clock selected listener
     */
    public void removeClockSelectedListener(ClockSelectedListener listener) {
        listenerList.remove(ClockSelectedListener.class, listener);
    }
    
    /**
     * adds a time changed listener
     */
    public void addTimeChangedListener(TimeChangedListener listener) {
        listenerList.add(TimeChangedListener.class, listener) ;
    }
    
    /**
     * removes a time changed listener
     */
    public void removeTimeChangedListener(TimeChangedListener listener) {
        listenerList.remove(TimeChangedListener.class, listener) ;
    }
    
    /**
     * displays the file icon as the base of the panel
     */
    @Override
    public void paintComponent(Graphics g) {
        
        Graphics2D graphics = (Graphics2D) g ;
        
        if (_clockImage == null 
                || _clockImage.getWidth() != getWidth()
                || _clockImage.getHeight() != getHeight()) {
            
            // determine the type of transparency supported by the screen
            int transparency = Transparency.OPAQUE ;
            
            // find out if the screen supports transparency
            if(hasAlpha(clockImage)) {
                transparency = Transparency.TRANSLUCENT ;
            }
            // create a buffered image with  a format that is compatible 
            // with the screen
            GraphicsConfiguration gc = getGraphicsConfiguration() ;
            _clockImage = gc.createCompatibleImage(getWidth(), getHeight(),
                    transparency) ;
            
            Graphics gImg = _clockImage.getGraphics() ;
            
            // set to Bilinear to ger the better quality scaling
            ((Graphics2D) gImg).setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            
            // draw the image
            gImg.drawImage(clockImage, 0, 0, getWidth(), getHeight(), null);
            
           gImg.dispose();
        }
        
        // apply the image at the base of the panel
        graphics.drawImage(_clockImage, 0, 0, null);
    }

    /**
     * Determines if the image has transparent pixels.
     *
     * @param image The image to check for transparent pixel.s
     * @return
     * <code>true</code> of
     * <code>false</code>, according to the result
     * code from http://www.java2s.com/Tutorial/Java/0261__2D-Graphics/Iftheimagehastransparentpixels.htm
     * @author Rafael Steil
     * @version $Id: ImageUtils.java,v 1.23 2007/09/09 01:05:22 rafaelsteil Exp $
     */
    public static boolean hasAlpha(Image image) {
        try {
            PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
            pg.grabPixels();

            return pg.getColorModel().hasAlpha();
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * Fires a Clock selected event
     */
    public void fireClockSelected(ClockSelected evt) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i = i+2) 
        {
            if (listeners[i] == ClockSelectedListener.class) 
            {
                ((ClockSelectedListener) listeners[i+1]).clockSelected(evt);
            }
        }
    }
    
    /**
     * animates the movement of the clock hands
     */
    public void tick() {
        if (SwingUtilities.isEventDispatchThread()) {
            clockTimer.start();            
        }
    }
    
    /**
     * stops the movement of the clock hands
     */
    public void stop() {
        if (SwingUtilities.isEventDispatchThread()) {
            clockTimer.stop();
            
            // stop playing the stop
            stoptickSound();
        } 
    }
    
    /**
     * determine if the clock has been double clicked and 
     * generate a clock selected event
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            ClockSelected c = new ClockSelected(this, this) ;
            fireClockSelected(c) ;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    /**
     * creates action listener that updates the clock hands
     */
    class UpdateClockHands implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            // get the system clock time
            int hour = Calendar.getInstance().get(Calendar.HOUR) ;
            int minute = Calendar.getInstance().get(Calendar.MINUTE) ;
            int second = Calendar.getInstance().get(Calendar.SECOND) ;
            
            // create a time object
            Time time = new Time(hour, minute, second) ;
            
            // update the clock hands
            drawClockHands(time);
                        
            // play the sound
            playtickSound();
        }        
    }
    
    /**
     * plays the tick sound. Code from StackOverflow
     * http://stackoverflow.com/tags/javasound/info
     */
    private void playtickSound() {
        if (true == playClockSound) {
            try {           
                clip = AudioSystem.getClip();

                // getAudioInputStream() also accepts a File or InputStream
                AudioInputStream ais = AudioSystem.getAudioInputStream(new File(clockTickSoundPath));

                // open and play the clip
                clip.open(ais);
                clip.loop(1);

            } catch (MalformedURLException ex) {
                System.out.println(ex);
            } catch (LineUnavailableException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
                System.out.println(ex);
            } catch (UnsupportedAudioFileException ex) {
                System.out.println(ex);
            }      
        }
    }
    
    /**
     * stops the playing of the tick sound
     */
     private void stoptickSound() {
         if(null != clip) {
             clip.stop();
         }
     }   
     
     /**
      * toggles the play of the tick sound 
      * by the clock
      */
     public void toggleClockSound() {
         playClockSound = (playClockSound) ? false: true ; 
     }
}
