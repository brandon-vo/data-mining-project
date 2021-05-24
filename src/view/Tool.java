package view;

import model.MyDataset;
import util.Category;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    private final HashMap<String, ArrayList<Category>>[] validGroups;
    private String groupName;
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
    
    public JButton getBackButton () {
        return backButton;
    }
    
    public ArrayList<String> getValidGroupNames (int dataset) {
        return new ArrayList<>(validGroups[dataset].keySet());
    }
    
    public HashMap<String, ArrayList<Category>> getValidGroups (int dataset) {
        return validGroups[dataset];
    }
    
    public String getGroupName () {
        return groupName;
    }
    
    public ArrayList<Category> getDataGroup () {
        return dataGroup;
    }
    
    public void setDataGroup (String groupName) {
        
        this.groupName = groupName;
        this.dataGroup = validGroups[0].get(groupName)!=null
                ? validGroups[0].get(groupName)
                : validGroups[1].get(groupName);
        
    }
    
}
