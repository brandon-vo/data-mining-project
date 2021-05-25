package view;

import controller.LineChartController;

import javax.swing.*;
import java.awt.*;

public class LineChartInteractor extends JPanel {
    
    private static final String CIRCLE_FILE = "./img/circle.png";
    public static final ImageIcon CIRCLE = new ImageIcon(CIRCLE_FILE);
    public static final Color BACKGROUND_COLOUR = new Color(32, 39, 41);
    
    private final JLabel[] circles;
    private final JPanel[] lines;
    
    private final int[] chosenCategories;
    
    public LineChartInteractor () {
    
        circles = new JLabel[LineChartController.MAX_CITIES];
        for (int i = 0; i<circles.length; ++i) {
            circles[i] = new JLabel(CIRCLE);
        }
        
        lines = new JPanel[2];
        for (int i = 0; i<lines.length; ++i) {
            lines[i] = new JPanel();
            lines[i].setBackground(BACKGROUND_COLOUR);
        }
        
        chosenCategories = new int[2];
        
    }
    
    public void setCirclePosition (int i, int x, int y) {
        circles[i].setBounds(x-CIRCLE.getIconWidth()/2, y-CIRCLE.getIconHeight()/2, CIRCLE.getIconWidth(), CIRCLE.getIconHeight());
    }
    
    public JLabel getCircle (int i) {
        return circles[i];
    }
    
    public void setLinePosition (int i, int x) {
        lines[i].setBounds(x-4/2, 35, 4, 446);
    }
    
    public JPanel getLine (int i) {
        return lines[i];
    }
    
    public int getChosenCategory (int i) {
        return chosenCategories[i];
    }
    
    public void setChosenCategory (int i, int chosenCategory) {
        chosenCategories[i] = chosenCategory;
    }
    
    public void clear () {
        
        for (JLabel circle : circles) {
            circle.setBounds(0, 0, 0, 0);
        }
        for (JPanel line : lines) {
            line.setBounds(0, 0, 0, 0);
        }
        
    }
    
}
