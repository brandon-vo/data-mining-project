package view;

import model.MyDataset;
import util.Category;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Tool extends JPanel {
    
    private static final String BACK_BUTTON_IMAGE_FILE = "img/backButton.png";
    
    private final ArrayList<String>[] validGroups;
    private final ArrayList<Category> displayedData;
    
    private final JButton backButton;
    private final Color backgroundColour;
    
    public Tool () {
        
        setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        validGroups = new ArrayList[2];
        validGroups[0] = new ArrayList<>();
        validGroups[1] = new ArrayList<>();
        displayedData = new ArrayList<>();
    
        backButton = new JButton(new ImageIcon(BACK_BUTTON_IMAGE_FILE));
        backgroundColour = new Color(243, 243, 243);
        backButton.setBounds(1315, 17, 20, 20);
        
    }
    
    public JButton getBackButton () {
        return backButton;
    }
    
    public Color getBackgroundColour () {
        return backgroundColour;
    }
    
    public ArrayList<String> getValidGroups (int dataset) {
        return validGroups[dataset];
    }
    
    public ArrayList<Category> getDisplayedData () {
        return displayedData;
    }
    
    public void setDisplayedData (ArrayList<Category> displayedData) {
        this.displayedData.clear();
        this.displayedData.addAll(displayedData);
    }
    
    /**
     * Function to set the data that will be displayed on the screen
     * @param dataset = where the data will be taken from
     */
    public abstract void setDataToDisplay (MyDataset dataset, String groupName);
    
}
