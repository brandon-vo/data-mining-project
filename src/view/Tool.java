package view;

import model.MyDataset;
import util.Category;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Tool extends JPanel {
    
    public static final Color BACKGROUND_COLOUR = new Color(243, 243, 243);
    public static final Color BUTTON_COLOUR = new Color(145, 172, 219);
    public static final Color COMBO_BOX_COLOUR = new Color(183, 183, 183);
    
    private static final String BACK_BUTTON_IMAGE_FILE = "img/backButton.png";
    
    private final ArrayList<String>[] validGroups;
    private final ArrayList<Category> dataGroup;
    
    private final JButton backButton;
    
    public Tool () {
    
        setLayout(null);
        setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        setBackground(BACKGROUND_COLOUR);
        validGroups = new ArrayList[2];
        validGroups[0] = new ArrayList<>();
        validGroups[1] = new ArrayList<>();
        dataGroup = new ArrayList<>();
    
        backButton = new JButton(new ImageIcon(BACK_BUTTON_IMAGE_FILE));
        backButton.setBounds(1315, 17, 20, 20);
        add(backButton);
        
    }
    
    public JButton getBackButton () {
        return backButton;
    }
    
    public ArrayList<String> getValidGroups (int dataset) {
        return validGroups[dataset];
    }
    
    public ArrayList<Category> getDataGroup () {
        return dataGroup;
    }
    
    public void setDataGroup (ArrayList<Category> dataGroup) {
        this.dataGroup.clear();
        this.dataGroup.addAll(dataGroup);
    }
    
    /**
     * Function to set the data that will be displayed on the screen
     * @param dataset = where the data will be taken from
     */
    public abstract void initializeDataToDisplay (MyDataset[] dataset);
    
}
