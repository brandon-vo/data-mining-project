package view;

import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

import static view.LineChartGUI.NULL_BOUNDS;
import static view.Tool.PADDING;

public class LineChartInfoPanel extends JPanel {
    
    public static final int PADDING = 10;
    public static final int WIDTH = PADDING*20;
    public static final int TEXT_HEIGHT = 15;
    public static final Color BACKGROUND_COLOUR = new Color(32, 39, 41);
    public static final Font FONT = new Font("Tahoma", Font.PLAIN, 14);
    
    private final JLabel titleLabel;
    private final JLabel cityNamesLabel;
    private final JLabel cityDataLabel;
    
    private final int[] chosenCategories;
    
    public LineChartInfoPanel () {
        
        setLayout(null);
        setBackground(BACKGROUND_COLOUR);
        
        titleLabel = new JLabel();
        titleLabel.setForeground(Tool.BACKGROUND_COLOUR);
        titleLabel.setFont(FONT);
        add(titleLabel);
        
        cityNamesLabel = new JLabel();
        cityNamesLabel.setForeground(Tool.BACKGROUND_COLOUR);
        cityNamesLabel.setFont(FONT);
        add(cityNamesLabel);
        
        cityDataLabel = new JLabel();
        cityDataLabel.setForeground(Tool.BACKGROUND_COLOUR);
        cityDataLabel.setFont(FONT);
        cityDataLabel.setHorizontalAlignment(JLabel.RIGHT);
        add(cityDataLabel);
        
        chosenCategories = new int[2];
        
    }
    
    public int getChosenCategory (int i) {
        return chosenCategories[i];
    }
    
    public void setChosenCategory (int i, int chosenCategory) {
        chosenCategories[i] = chosenCategory;
    }
    
    /**
     * Generate the this panel to display the selected information
     * @param displayedData = the displayed data
     * @param categoryX     = the category's x coordinates
     * @param mouseY        = the mouse's y coordinates
     */
    public void generatePanel (DefaultCategoryDataset displayedData, int categoryX, int mouseY) {
        
        clear();
        
        // If the panel will go out the chartPanel, display it in the opposite direction
        if (MainFrame.HEIGHT-PADDING*7/2<categoryX+PADDING*2+WIDTH) {
            setBounds(categoryX-PADDING*2-WIDTH, mouseY, WIDTH, TEXT_HEIGHT*(4+displayedData.getRowCount()));
        } else {
            setBounds(categoryX+PADDING*2, mouseY, WIDTH, TEXT_HEIGHT*(4+displayedData.getRowCount()));
        }
        
        // Create titleLabel
        titleLabel.setText((String) displayedData.getColumnKey(chosenCategories[0]));
        titleLabel.setBounds(PADDING, PADDING, WIDTH-PADDING*2, TEXT_HEIGHT);
        
        // Create cityNamesLabel and cityDataLabel
        cityNamesLabel.setText("<html>");
        cityNamesLabel.setBounds(PADDING, PADDING+TEXT_HEIGHT*2, WIDTH-PADDING*2, TEXT_HEIGHT*displayedData.getRowCount()+10);
        cityDataLabel.setText("<html>");
        cityDataLabel.setBounds(cityNamesLabel.getBounds());
        
        for (int i = 0; i<displayedData.getRowCount()-1; ++i) {
            cityNamesLabel.setText(cityNamesLabel.getText()+displayedData.getRowKey(i)+"<br/>");
            cityDataLabel.setText(cityDataLabel.getText()+(int) ((double) displayedData.getValue(i, chosenCategories[0]))+"<br/>");
        }
        cityNamesLabel.setText(cityNamesLabel.getText()+displayedData.getRowKey(displayedData.getRowCount()-1)+"</html>");
        cityDataLabel.setText(cityDataLabel.getText()+(int) ((double) displayedData.getValue(displayedData.getRowCount()-1, chosenCategories[0]))+"</html>");
        
    }
    
    public void generatePanel (DefaultCategoryDataset displayedData) {
        
        clear();
        
    }
    
    public void clear () {
        
        titleLabel.setBounds(NULL_BOUNDS);
        titleLabel.setText("");
        cityNamesLabel.setBounds(NULL_BOUNDS);
        titleLabel.setText("");
        cityDataLabel.setBounds(NULL_BOUNDS);
        titleLabel.setText("");
        setBounds(NULL_BOUNDS);
        
    }
    
    // Draw a line to divide the infoPanel
    @Override
    protected void paintComponent (Graphics g) {
        
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(2));
        g2D.setColor(Tool.BACKGROUND_COLOUR);
        g2D.drawLine(PADDING, PADDING+TEXT_HEIGHT*3/2, WIDTH-PADDING, PADDING+TEXT_HEIGHT*3/2);
        
    }
    
}
