package org.aprilsecond.customuicomponents.transparentbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 * This class implements a close button that closes an 
 * open application panel
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class CloseButton extends  AbstractTransparentButton {
    
    /**
     * null constructor
     */
    public CloseButton(JFrame frame) {
        
        // initialize the close button letter
        super(AbstractTransparentButton.CLOSE_BUTTON,
                frame) ;
    }

    /**
     * defines the behaviour for the button
     * @return 
     */
    @Override
    public ActionListener performAction() {
        ActionListener closeListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // close the frame
                appFrame.dispose();
                appFrame = null ;
                
                // close the application
                System.exit(0);
            }
        } ;
        
        return closeListener ;
    }    
}
