/**
 * Class that
 * @Author Felix
 */

package view;

import util.Category;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Tool extends JPanel {
    
    // GUI constants
    public static final Color BACKGROUND_COLOUR = new Color(243, 243, 243);
    public static final Color BUTTON_COLOUR = new Color(145, 172, 219);
    public static final Color COMBO_BOX_COLOUR = new Color(183, 183, 183);
    
    public static final Font FONT = new Font("Tahoma", Font.PLAIN, 17);
    public static final int PADDING = 60;
    public static final int USER_INPUT_X = MainFrame.WIDTH*3/4;
    public static final int USER_INPUT_WIDTH = MainFrame.WIDTH/4-PADDING;
    public static final int USER_INPUT_HEIGHT = PADDING;
    
    private static final String BACK_BUTTON_IMAGE_FILE = "./img/backButton.png";
    
    // Button to go back to MenuPanel
    private final JButton backButton;
    
    // Data trackers
    private final HashMap<String, ArrayList<Category>>[] validGroups;
    private ArrayList<Category> dataGroup;
    
    public Tool () {
    
        setLayout(null);
        setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        setBackground(BACKGROUND_COLOUR);
        
        // Initialize validGroups
        validGroups = new HashMap[2];
        validGroups[0] = new HashMap<>();
        validGroups[1] = new HashMap<>();
        dataGroup = new ArrayList<>();
    
        // Initialize the backButton with an image
        ImageIcon image = new ImageIcon(BACK_BUTTON_IMAGE_FILE);
        backButton = new JButton(image);
        backButton.setBounds(
                MainFrame.WIDTH-image.getIconWidth()-PADDING,
                PADDING/2, image.getIconWidth(), image.getIconHeight()
        );
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        add(backButton);
        
    }
    
    // Getters
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
    
    /**
     * Sets the current dataGroup to groupName
     * @param groupName
     */
    public void setDataGroup (String groupName) {
        
        // Find the dataset that groupName belongs to and set
        // the current dataGroup to it
        this.dataGroup = validGroups[0].get(groupName)!=null
                ? validGroups[0].get(groupName)
                : validGroups[1].get(groupName);
        
    }
    
}
