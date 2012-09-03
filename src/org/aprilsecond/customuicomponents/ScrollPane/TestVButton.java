package org.aprilsecond.customuicomponents.ScrollPane;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;

/** 
 * Tests the 
 * @author Reagan
 */
public class TestVButton extends PanelComponent {
    public TestVButton() {
            setPreferredSize(new Dimension(50, 50));
            setMaximumSize(new Dimension(50, 50));
            setMinimumSize(new Dimension(50, 50));
            setBackground(Color.YELLOW);
            setBorder(new BevelBorder(BevelBorder.RAISED));
        }
        
        @Override
        public void setHoveredState(boolean state) {
            super.setHoveredState(state);
            
            if (true == state) {
                setBackground(Color.GREEN); 
            } else {
                setBackground(Color.YELLOW);
            }
        }

    @Override
    public void setSelectedState(boolean state) {}
}
