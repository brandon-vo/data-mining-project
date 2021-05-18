package view;

import model.DataType;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    
    private final JLabel titleLabel = new JLabel(new ImageIcon("img/menuPanelTitleLabel.png"));
    private final JButton[] buttons = new JButton[DataType.values().length];
    
    public MenuPanel () {
        
        titleLabel.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        add(titleLabel);
    
        DataType[] values = DataType.values();
    
        for (int i = 0; i<buttons.length; ++i) {
        
            buttons[i] = new JButton(values[i].getChartDescription());
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
