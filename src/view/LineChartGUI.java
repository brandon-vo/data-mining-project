package view;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class LineChartGUI extends Tool {
    
    private static final String LINE_CHART_TITLE_FILE = "./img/titles/lineChartTitle.png";
    
    // GUI constants
    public static final Font FONT = new Font("Tahoma", Font.PLAIN, 17);
    public static final int PADDING = 30;
    public static final int USER_INPUT_X = MainFrame.WIDTH*3/4;
    public static final int USER_INPUT_WIDTH = MainFrame.WIDTH/4-PADDING*2;
    public static final int USER_INPUT_HEIGHT = PADDING*2;
    
    private final JLabel lineChartTitleLabel;
    private final JLabel datasetLabel;
    private final JLabel[] boundLabels;
    
    // User input
    private final JComboBox<String> selectDataGroupBox;
    private final JComboBox<String>[] chartBoundsBoxes;     // bounds[0] = start bound, bounds[1] = end bound
    
    private JButton selectVisibleCitiesButton;
    
    // JFreeChart
    private DefaultCategoryDataset displayedData;
    private JFreeChart lineChart;
    private ChartPanel chartPanel;
    
    public LineChartGUI () {
    
        lineChartTitleLabel = new JLabel(new ImageIcon(LINE_CHART_TITLE_FILE));
        lineChartTitleLabel.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        add(lineChartTitleLabel);
        
        int currentHeight = PADDING*4;
        
        datasetLabel = new JLabel("Select the Dataset");
        datasetLabel.setBounds(USER_INPUT_X, currentHeight, USER_INPUT_WIDTH, USER_INPUT_HEIGHT);
        datasetLabel.setFont(FONT);
        add(datasetLabel);
        
        selectDataGroupBox = new JComboBox<>();
        currentHeight += USER_INPUT_HEIGHT;
        selectDataGroupBox.setBounds(USER_INPUT_X, currentHeight, USER_INPUT_WIDTH, USER_INPUT_HEIGHT);
        selectDataGroupBox.setBorder(null);
        selectDataGroupBox.setFont(FONT);
        selectDataGroupBox.setBackground(COMBO_BOX_COLOUR);
        add(selectDataGroupBox);
        
        boundLabels = new JLabel[2];
        currentHeight += USER_INPUT_HEIGHT;
        for (int i = 0; i<boundLabels.length; ++i) {
    
            boundLabels[i] = new JLabel();
            boundLabels[i].setBounds(USER_INPUT_X+i*USER_INPUT_WIDTH*3/5, currentHeight,
                    USER_INPUT_WIDTH*2/5, USER_INPUT_HEIGHT);
            boundLabels[i].setFont(FONT);
            boundLabels[i].setBackground(COMBO_BOX_COLOUR);
            add(boundLabels[i]);
        
        }
        boundLabels[0].setText("Start");
        boundLabels[1].setText("End");
    
        chartBoundsBoxes = new JComboBox[2];
        currentHeight += USER_INPUT_HEIGHT;
        for (int i = 0; i<chartBoundsBoxes.length; ++i) {
    
            chartBoundsBoxes[i] = new JComboBox<>();
            chartBoundsBoxes[i].setBounds(USER_INPUT_X+USER_INPUT_WIDTH*3/5*i, currentHeight,
                    USER_INPUT_WIDTH*2/5, USER_INPUT_HEIGHT);
            chartBoundsBoxes[i].setFont(new Font("Tahoma", Font.PLAIN, 12));
            chartBoundsBoxes[i].setBackground(COMBO_BOX_COLOUR);
            add(chartBoundsBoxes[i]);
            
        }
        
        selectVisibleCitiesButton = new JButton("Select Visible cities");
        currentHeight += USER_INPUT_HEIGHT+PADDING;
        selectVisibleCitiesButton.setBounds(USER_INPUT_X, currentHeight, USER_INPUT_WIDTH, USER_INPUT_HEIGHT);
        selectVisibleCitiesButton.setBorder(null);
        selectVisibleCitiesButton.setFont(FONT);
        selectVisibleCitiesButton.setBackground(BUTTON_COLOUR);
        add(selectVisibleCitiesButton);
        
        displayedData = new DefaultCategoryDataset();
        
    }
    
    public JComboBox<String> getSelectDataGroupBox () {
        return selectDataGroupBox;
    }
    
    public JComboBox<String> getChartBoundBox (int index) {
        return chartBoundsBoxes[index];
    }
    
    public JButton getSelectVisibleCitiesButton () {
        return selectVisibleCitiesButton;
    }
    
    public DefaultCategoryDataset getDisplayedData () {
        return displayedData;
    }
    
    public JFreeChart getLineChart () {
        return lineChart;
    }
    
    public ChartPanel getChartPanel () {
        return chartPanel;
    }
    
    public void setLineChart (JFreeChart lineChart) {
        this.lineChart = lineChart;
    }
    
    public void setChartPanel (ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }
    
}
