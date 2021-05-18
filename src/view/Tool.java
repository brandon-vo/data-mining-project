package view;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.Dataset;
import util.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public abstract class Tool extends JPanel {
    
    private ArrayList<Category> dataset;
    
    private static final String BACK_BUTTON_IMAGE_FILE = "img/backButton.png";
    private final JLabel backButton;
    private final Color backgroundColour;
    
    public Tool () {
        
        setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        this.dataset = new ArrayList<>();
    
        backButton = new JLabel(new ImageIcon(BACK_BUTTON_IMAGE_FILE));
        backgroundColour = new Color(243, 243, 243);
        backButton.setBounds(1315, 17, 20, 20);
        
    }
    
    public JLabel getBackButton () {
        return backButton;
    }
    
    public Color getBackgroundColour () {
        return backgroundColour;
    }
    
    public void setDataset (ArrayList<Category> dataset) {
        this.dataset = dataset;
    }
    
    public ArrayList<Category> getDataset () {
        return dataset;
    }
    
}
