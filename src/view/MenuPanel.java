package view;

import model.DataType;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    
    private final JLabel titleLabel = new JLabel(new ImageIcon("img/menuPanelTitleLabel.png"));
    private final JButton[] buttons = new JButton[DataType.values().length];
    
    public MenuPanel () {
    
        // TODO option pane to choose a particular dataset (journey to work, housing profile)
        
        titleLabel.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        add(titleLabel);
    
        buttons[0] = new JButton("TRANSPORTATION AND HOUSING");
        buttons[1] = new JButton("DENSITY MAP");
        buttons[2] = new JButton("LINE CHART");
        buttons[3] = new JButton("SCATTER PLOT");
        buttons[4] = new JButton("DOUBLE BAR GRAPH");
    
        for (int i = 0; i<buttons.length; ++i) {
        
            buttons[i].setBounds(450, 220+i*80, 470, 40);
            buttons[i].setBorder(null);
            buttons[i].setFont(new Font("Tahoma", Font.PLAIN, 15));
            buttons[i].setBackground(new Color(145, 172, 219));
            add(buttons[i]);
        
        }
        
    }
    
    public JButton getButton (int index) {
        return buttons[index];
    }
    
}
