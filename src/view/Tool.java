package view;

import model.MyDataset;
import util.Category;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class Tool extends JPanel {
    
    public static final Color BACKGROUND_COLOUR = new Color(243, 243, 243);
    public static final Color BUTTON_COLOUR = new Color(145, 172, 219);
    public static final Color COMBO_BOX_COLOUR = new Color(183, 183, 183);
    
    private static final String BACK_BUTTON_IMAGE_FILE = "img/backButton.png";
    
    private final HashMap<String, ArrayList<Category>>[] validGroups;
    private ArrayList<Category> dataGroup;
    
    private final JButton backButton;
    
    public Tool () {
    
        setLayout(null);
        setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        setBackground(BACKGROUND_COLOUR);
        validGroups = new HashMap[2];
        validGroups[0] = new HashMap<>();
        validGroups[1] = new HashMap<>();
        dataGroup = new ArrayList<>();
    
        backButton = new JButton(new ImageIcon(BACK_BUTTON_IMAGE_FILE));
        backButton.setBounds(1315, 17, 20, 20);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        add(backButton);
        
    }
    
    public JButton getBackButton () {
        return backButton;
    }
    
    public ArrayList<String> getValidGroupNames (int dataset) {
        return new ArrayList<>(validGroups[dataset].keySet());
    }
    
    public HashMap<String, ArrayList<Category>> getValidGroups (int dataset) {
        return validGroups[dataset];
    }
    
    public ArrayList<Category> getDataGroup () {
        return dataGroup;
    }
    
    public void setDataGroup (String groupName) {
        this.dataGroup = validGroups[0].get(groupName)!=null
                ? validGroups[0].get(groupName)
                : validGroups[1].get(groupName);
    }
    
    /**
     * Function to set the data that will be displayed on the screen
     * @param dataset = where the data will be taken from
     */
    public abstract void initializeDataToDisplay (MyDataset[] dataset);
    
}
