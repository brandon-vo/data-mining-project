package view;

import org.jfree.data.general.Dataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public abstract class Tool extends JPanel /*implements Dataset*/ {

    private final JLabel backButton = new JLabel(new ImageIcon("img/backButton.png"));
    private final Color backgroundColour;
    
    private ArrayList<TreeMap<String, HashMap<String, Double>>> dataGroups;
    
    public Tool (int x, int y, int width, int height) {
        
        setBounds(x, y, width, height);
        backgroundColour = new Color(243, 243, 243);
        dataGroups = new ArrayList<>();

        backButton.setBounds(1315, 17, 20, 20);

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
