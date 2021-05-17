package view;

import org.jfree.data.general.Dataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public abstract class Tool extends JPanel /*implements Dataset*/ {
    
    private static final String BACK_BUTTON_IMAGE_FILE = "img/backButton.png";
    private final JLabel backButton;
    private final Color backgroundColour;
    
    private ArrayList<TreeMap<String, HashMap<String, Double>>> dataGroups;
    
    public Tool () {
        
        backButton = new JLabel(new ImageIcon(BACK_BUTTON_IMAGE_FILE));
        backgroundColour = new Color(243, 243, 243);
        dataGroups = new ArrayList<>();
        
    }
    
    public JLabel getBackButton () {
        return backButton;
    }
    
    public Color getBackgroundColour () {
        return backgroundColour;
    }
    
    public ArrayList<TreeMap<String, HashMap<String, Double>>> getDataGroups () {
        return dataGroups;
    }
    
}
