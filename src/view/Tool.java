package view;

import model.MyDataset;
import util.Category;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Tool extends JPanel {
    
    private ArrayList<String> validGroups;
    private ArrayList<Category> displayedData;
    
    private static final String BACK_BUTTON_IMAGE_FILE = "img/backButton.png";
    private final JLabel backButton;
    private final Color backgroundColour;
    
    public Tool () {
        
        setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        validGroups = new ArrayList<>();
        displayedData = new ArrayList<>();
    
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
    
    public ArrayList<String> getValidGroups () {
        return validGroups;
    }
    
    public ArrayList<Category> getDisplayedData () {
        return displayedData;
    }
    
    public void setDisplayedData (ArrayList<Category> displayedData) {
        this.displayedData = displayedData;
    }
    
    /**
     * Function to set the data that will be displayed on the screen
     * @param dataset = where the data will be taken from
     */
    public abstract void setDataToDisplay (MyDataset dataset, String groupName);
    
}
