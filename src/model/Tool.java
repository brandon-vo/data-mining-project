package model;

import javax.swing.*;
import java.awt.*;

public abstract class Tool extends JPanel {

    private final JLabel backButton;
    private final Color backgroundColour;
    
    public Tool () {
        backButton = new JLabel();
        backgroundColour = new Color(243, 243, 243);
    }
    
    public JLabel getBackButton () {
        return backButton;
    }
    
    public Color getBackgroundColour () {
        return backgroundColour;
    }

}
