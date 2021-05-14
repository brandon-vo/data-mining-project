package model;

import javax.swing.*;
import java.awt.*;

public abstract class Tool extends JPanel {

    
    private static final String BACK_BUTTON_IMAGE_FILE = "img/backButton.png";
    private final JLabel backButton;
    private final Color backgroundColour;
    
    public Tool () {
        backButton = new JLabel(new ImageIcon(BACK_BUTTON_IMAGE_FILE));
        backgroundColour = new Color(243, 243, 243);
    }
    
    public JLabel getBackButton () {
        return backButton;
    }
    
    public Color getBackgroundColour () {
        return backgroundColour;
    }

}
