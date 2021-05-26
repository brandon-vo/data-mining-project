/**
 * The panel that provides GUI to display information about the
 * data whenever the user clicks on the graph
 * @author Felix
 */

package view;

import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

import static view.LineChartGUI.NULL_BOUNDS;

public class LineChartInfoPanel extends JPanel {
    
    // GUI constants
    public static final int PADDING = 10;
    public static final int WIDTH = PADDING*20;
    public static final int TEXT_HEIGHT = 15;
    public static final Color BACKGROUND_COLOUR = new Color(32, 39, 41);
    public static final Font FONT = new Font("Tahoma", Font.PLAIN, 14);
    
    // Line to divide the title and data
    private final JPanel divider;
    
    // Labels
    private final JLabel titleLabel;
    private final JLabel cityNamesLabel;
    private final JLabel cityDataLabel;
    
    // The user's selected categories
    private final int[] chosenCategories;
    
    public LineChartInfoPanel () {
        
        setLayout(null);
        setBackground(BACKGROUND_COLOUR);
        
        // Initialize the divider
        divider = new JPanel();
        divider.setBackground(Tool.BACKGROUND_COLOUR);
        add(divider);
        
        // Initialize the lavels
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
     * Generate the panel to display the selected information
     * @param displayedData = the displayed data
     * @param categoryX     = the category's x coordinates
     * @param mouseY        = the mouse's y coordinates
     */
    public void generatePanel (DefaultCategoryDataset displayedData, int categoryX, int mouseY) {
        
        // Reset the panel
        clear();
        
        // If the panel will go out the chartPanel, display it in the opposite direction
        if (MainFrame.HEIGHT-PADDING*7/2<categoryX+PADDING*2+WIDTH) {
            setBounds(categoryX-PADDING*2-WIDTH, mouseY, WIDTH, TEXT_HEIGHT*(4+displayedData.getRowCount()));
        } else {
            setBounds(categoryX+PADDING*2, mouseY, WIDTH, TEXT_HEIGHT*(4+displayedData.getRowCount()));
        }
        
        // Draw a line to divide the title with the information
        divider.setBounds(PADDING, PADDING+TEXT_HEIGHT*3/2, WIDTH-PADDING*2, 2);
        
        // Create titleLabel
        titleLabel.setText((String) displayedData.getColumnKey(chosenCategories[0]));
        titleLabel.setBounds(PADDING, PADDING, WIDTH-PADDING*2, TEXT_HEIGHT);
        
        // Create cityNamesLabel and cityDataLabel
        cityNamesLabel.setText("<html>");
        cityNamesLabel.setBounds(PADDING, PADDING+TEXT_HEIGHT*2, WIDTH-PADDING*2, TEXT_HEIGHT*displayedData.getRowCount()+10);
        cityDataLabel.setText("<html>");
        cityDataLabel.setBounds(cityNamesLabel.getBounds());
        
        // Add information to the labels
        for (int i = 0; i<displayedData.getRowCount()-1; ++i) {
            cityNamesLabel.setText(cityNamesLabel.getText()+displayedData.getRowKey(i)+"<br/>");
            cityDataLabel.setText(cityDataLabel.getText()+(int) ((double) displayedData.getValue(i, chosenCategories[0]))+"<br/>");
        }
        cityNamesLabel.setText(cityNamesLabel.getText()+displayedData.getRowKey(displayedData.getRowCount()-1)+"</html>");
        cityDataLabel.setText(cityDataLabel.getText()+(int) ((double) displayedData.getValue(displayedData.getRowCount()-1, chosenCategories[0]))+"</html>");
        
    }
    
    /**
     * Generate this panel to display the difference
     * between two pieces of information
     * @param displayedData = the displayed data
     * @param middleX       = the middle of the interval
     */
    public void generatePanel (DefaultCategoryDataset displayedData, int middleX) {
        
        // Reset the panel
        clear();
    
        // Put the necessary information into titleLabel
        int x1Value = (int) ((double) displayedData.getValue(0, chosenCategories[0]));
        int x2Value = (int) ((double) displayedData.getValue(0, chosenCategories[1]));
        titleLabel.setText(
                (x2Value-x1Value)+" | "
                +displayedData.getColumnKey(chosenCategories[0])
                +" to "+displayedData.getColumnKey(chosenCategories[1])
        );
    
        // Display the panel
        setBounds(middleX-PADDING*(titleLabel.getText().length()+2)*5/14, PADDING*4, PADDING*5/7*(titleLabel.getText().length()+2), TEXT_HEIGHT+PADDING*2);
        titleLabel.setBounds(PADDING, PADDING, PADDING*(titleLabel.getText().length())*5/7, TEXT_HEIGHT);
    
    }
    
    /**
     * Clear this panel from the screen
     */
    public void clear () {
    
        setBounds(NULL_BOUNDS);
        divider.setBounds(NULL_BOUNDS);
        titleLabel.setBounds(NULL_BOUNDS);
        titleLabel.setText("");
        cityNamesLabel.setBounds(NULL_BOUNDS);
        titleLabel.setText("");
        cityDataLabel.setBounds(NULL_BOUNDS);
        titleLabel.setText("");
        
    }
    
}
