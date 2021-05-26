/**
 * The menu panel that contains all the buttons to the tools
 * @author Daniel, Brandon
 */

package view;

import model.DataType;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    
    private final JLabel titleLabel = new JLabel(new ImageIcon("img/menuPanelTitleLabel.png"));
    private final JButton[] buttons = new JButton[DataType.values().length];
    
    public MenuPanel () {
    
        // Set up the panel
        setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        setLayout(null);
        
        // Create the title
        titleLabel.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        add(titleLabel);
    
        DataType[] values = DataType.values();
    
        // Create equally spaced out buttons
        for (int i = 0; i<buttons.length; ++i) {
        
            buttons[i] = new JButton(values[i].getChartDescription());
            buttons[i].setBounds(450, 220+i*80, 470, 40);
            buttons[i].setBorder(null);
            buttons[i].setFont(new Font("Tahoma", Font.PLAIN, 15));
            buttons[i].setBackground(new Color(145, 172, 219));
            add(buttons[i]);
        
        }
        
    }
    
    // Getter
    public JButton getButton (int index) {
        return buttons[index];
    }
    
}
