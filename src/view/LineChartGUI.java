package view;

import model.MyDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import util.Category;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class LineChartGUI extends Tool {
    
    private static final int MAX_CITIES = 5;
    private static final String LINE_CHART_TITLE_FILE = "./img/titles/lineChartTitle.png";
    
    private static final Font FONT = new Font("Tahoma", Font.PLAIN, 17);
    private static final int PADDING = 30;
    private static final int USER_INPUT_X = MainFrame.WIDTH*3/4;
    private static final int USER_INPUT_WIDTH = MainFrame.WIDTH/4-PADDING*2;
    private static final int USER_INPUT_HEIGHT = PADDING*2;
    
    private JLabel lineChartTitleLabel;
    private JLabel datasetLabel;
    private JLabel[] boundLabels;
    
    // User input
    private JComboBox<String> selectDataGroupBox;
    private JComboBox<String>[] chartBounds;     // bounds[0] = start bound, bounds[1] = end bound
    
    private JButton selectVisibleCities;
    
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
    
        chartBounds = new JComboBox[2];
        currentHeight += USER_INPUT_HEIGHT;
        for (int i = 0; i<chartBounds.length; ++i) {
    
            chartBounds[i] = new JComboBox<>();
            chartBounds[i].setBounds(USER_INPUT_X+i*USER_INPUT_WIDTH*3/5, currentHeight,
                    USER_INPUT_WIDTH*2/5, USER_INPUT_HEIGHT);
            chartBounds[i].setFont(FONT);
            chartBounds[i].setBackground(COMBO_BOX_COLOUR);
            add(chartBounds[i]);
            
        }
        
        selectVisibleCities = new JButton("Select Visible cities");
        currentHeight += USER_INPUT_HEIGHT+PADDING;
        selectVisibleCities.setBounds(USER_INPUT_X, currentHeight, USER_INPUT_WIDTH, USER_INPUT_HEIGHT);
        selectVisibleCities.setBorder(null);
        selectVisibleCities.setFont(FONT);
        selectVisibleCities.setBackground(BUTTON_COLOUR);
        add(selectVisibleCities);
        
        displayedData = new DefaultCategoryDataset();
        
    }
    
    public JComboBox<String> getSelectDataGroupBox () {
        return selectDataGroupBox;
    }
    
    public JComboBox<String> getChartBounds (int index) {
        return chartBounds[index];
    }
    
    public JButton getSelectVisibleCities () {
        return selectVisibleCities;
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
    
    @Override
    public void initializeDataToDisplay (MyDataset[] dataset) {
        
        String groupName = getValidGroupNames(0).get(0);
        setDataToDisplay(groupName);
        
        // Initialize the dataGroup JComboBox
        ArrayList<String> items = new ArrayList<>();
        items.add(groupName);
        items.addAll(getValidGroupNames(0));
        items.addAll(getValidGroupNames(1));
        items.remove(items.lastIndexOf(groupName));
        
        String[] middleMan = new String[items.size()];
        items.toArray(middleMan);
        selectDataGroupBox.setModel(new DefaultComboBoxModel<>(middleMan));
        
    }
    
    public void setDataToDisplay (String groupName) {
    
        setDataGroup(groupName);
    
        // Initialize the bounds JComboBoxes
        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i<getDataGroup().size(); ++i) {
            items.add(getDataGroup().get(i).getCategoryName());
        }
        String[] middleMan = new String[items.size()];
        items.toArray(middleMan);
        chartBounds[0].setModel(new DefaultComboBoxModel<>(middleMan));
    
        // Reverse the array to display in descending order for the end bound
        for (int i = 0; i<middleMan.length/2; ++i) {
            String temp = middleMan[i];
            middleMan[i] = middleMan[middleMan.length-1-i];
            middleMan[middleMan.length-1-i] = temp;
        }
        items.toArray(middleMan);
        chartBounds[1].setModel(new DefaultComboBoxModel<>(middleMan));
    
        // Add some lines for cities to the line chart
        items.clear();
        middleMan = MyDataset.getCities();
        items.addAll(Arrays.asList(middleMan).subList(0, MAX_CITIES));
        createDisplayedData(items);
    
        createChart(groupName);
    
    }
    
    public void createDisplayedData (ArrayList<String> displayableCities) {
    
        displayedData.clear();
        
        // Create the city lines
        for (Category category: getDataGroup()) {
            for (String city: displayableCities) {
                displayedData.addValue(category.getCities().get(city), city, category.getCategoryName());
            }
        }
    
    }
    
    public void createChart (String groupName) {
    
        String chartTitle = "Number of People vs "+groupName;
        String valueAxisLabel = "Number of People";
    
        lineChart = ChartFactory.createLineChart(chartTitle, groupName, valueAxisLabel, displayedData,
                PlotOrientation.VERTICAL, true, false, false);
        lineChart.setBackgroundPaint(BACKGROUND_COLOUR);
    
        chartPanel = new ChartPanel(lineChart);
        chartPanel.setBounds(PADDING, PADDING*4, USER_INPUT_X-PADDING*2, MainFrame.HEIGHT-PADDING*7);
        chartPanel.setRangeZoomable(false);
        add(chartPanel);
    
    }
    
}
