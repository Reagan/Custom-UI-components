package org.aprilsecond.customuicomponents.clock;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class ensures that the clock is displayed correctly
 * @author Reagan
 */
public class TestClock {
    public static void main (String[] args ) {
        Runnable windowDisplay = new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame("Clock Test") ;
                frame.setSize(new Dimension(200, 230));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                // add the clock panel
                final Clock clock = new Clock() ;
                clock.drawClockHands(new Time(Calendar.getInstance().get(Calendar.HOUR), 
                        Calendar.getInstance().get(Calendar.MINUTE), 
                        Calendar.getInstance().get(Calendar.SECOND)));
                
                // add a clock event listener 
                clock.addClockSelectedListener(new ClockSelectedListener() {

                    @Override
                    public void clockSelected(ClockSelected evt) {
                        Clock c = (Clock) evt.getSource() ;
                        
                        JOptionPane.showMessageDialog(null, "Clock selected "
                                + c.getClockIconPath(), 
                                "Clock selected", 
                                JOptionPane.INFORMATION_MESSAGE);
                        c.toggleClockSound();
                    }
                });                
                
                // add a time changed listener
                clock.addTimeChangedListener(new TimeChangedListener() {

                    @Override
                    public void timeChanged(TimeChanged evt) {
                        // get the change type
                        int timeChangeType = evt.getTimeChangetype() ;
                        
                        // output
                        String output = "" ;
                        if (timeChangeType == TimeChangeType.HOUR) {
                            output = "Hour hand changed to " 
                                    + clock.getClockModel().getTime().getHour() ;
                        }  else if (timeChangeType == TimeChangeType.MINUTE) {
                            output = "Minute hand changed to " 
                                    + clock.getClockModel().getTime().getMinute() ;
                        } else  if (timeChangeType == TimeChangeType.SECOND) {
                            output = "Second hand changed to " 
                                    + clock.getClockModel().getTime().getSecond() ;
                        }
                        
                        // display 
                        //System.out.println(output) ;
                    }
                });
                
                // add it to frame
                frame.add(clock) ;
                
                // display the frame
                frame.setVisible(true);                
                
                // start the clock
                clock.tick();
            }
            
        };
        
        // run the class
        EventQueue.invokeLater(windowDisplay);
    }
}
