/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aprilsecond.customuicomponents.transparentbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * This class implements a JButton that minimises an open
 * frame when activated
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class MinimiseButton extends AbstractTransparentButton {
    
    /**
     * initializes the minimize letter
     */
    public MinimiseButton(JFrame frame) {
        super(AbstractTransparentButton.MINIMIZE_BUTTON, 
                frame) ;
    }

    /**
     * implements the behavior of the JButton
     * @return 
     */
    @Override
    public ActionListener performAction() {
        ActionListener actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // close the frame
                appFrame.setExtendedState(JFrame.ICONIFIED);
            }
        } ;
        
        return actionListener ;
    }
}
