package view;

import controller.LineChartController;

import javax.swing.*;
import java.awt.*;

public class LineChartInteractor extends JPanel {
    
    private static final String CIRCLE_FILE = "./img/circle.png";
    public static final ImageIcon CIRCLE = new ImageIcon(CIRCLE_FILE);
    public static final Color BACKGROUND_COLOUR = new Color(32, 39, 41);
    
    private JLabel[] circles;
    
    public LineChartInteractor () {
    
        circles = new JLabel[LineChartController.MAX_CITIES];
        for (int i = 0; i<circles.length; ++i) {
            circles[i] = new JLabel(CIRCLE);
        }
        
    }
    
    public void setCircle (int i, int x, int y) {
        circles[i].setBounds(x-CIRCLE.getIconWidth()/2, y-CIRCLE.getIconHeight()/2, CIRCLE.getIconWidth(), CIRCLE.getIconHeight());
    }
    
    public JLabel getCircle (int i) {
        return circles[i];
    }

}
