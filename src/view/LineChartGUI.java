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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static javax.swing.text.html.CSS.Attribute.PADDING;

public class LineChartGUI extends Tool implements MouseListener {
    
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
    private JComboBox<String> selectDatasetBox;
    private JComboBox<String>[] bounds;     // bounds[0] = start bound, bounds[1] = end bound
    
    private JButton selectVisibleCities;
    
    // JFreeChart
    private DefaultCategoryDataset displayedData;
    private JFreeChart chart;
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
        
        selectDatasetBox = new JComboBox<>();
        currentHeight += USER_INPUT_HEIGHT;
        selectDatasetBox.setBounds(USER_INPUT_X, currentHeight, USER_INPUT_WIDTH, USER_INPUT_HEIGHT);
        selectDatasetBox.setBorder(null);
        selectDatasetBox.setFont(FONT);
        selectDatasetBox.setBackground(COMBO_BOX_COLOUR);
        add(selectDatasetBox);
        
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
    
        bounds = new JComboBox[2];
        currentHeight += USER_INPUT_HEIGHT;
        for (int i = 0; i<bounds.length; ++i) {
    
            bounds[i] = new JComboBox<>();
            bounds[i].setBounds(USER_INPUT_X+i*USER_INPUT_WIDTH*3/5, currentHeight,
                    USER_INPUT_WIDTH*2/5, USER_INPUT_HEIGHT);
            bounds[i].setFont(FONT);
            bounds[i].setBackground(COMBO_BOX_COLOUR);
            add(bounds[i]);
            
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
    
    @Override
    public void setDataToDisplay (MyDataset dataset, String groupName) {
        
        setDataGroup(dataset.getDataset().get(groupName));
    
        // Initialize the dataset JComboBox
        ArrayList<String> items = new ArrayList<>();
        items.add(groupName);
        items.addAll(getValidGroups(0));
        items.addAll(getValidGroups(1));
        items.remove(items.lastIndexOf(groupName));
        
        String[] middleMan = new String[items.size()];
        items.toArray(middleMan);
        selectDatasetBox.setModel(new DefaultComboBoxModel<>(middleMan));
        
        // Initialize the bounds JComboBoxes
        items.clear();
        for (int i = 0; i<getDataGroup().size(); ++i) {
            items.add(getDataGroup().get(i).getCategoryName());
        }
        middleMan = new String[items.size()];
        items.toArray(middleMan);
        bounds[0].setModel(new DefaultComboBoxModel<>(middleMan));
        
        // Reverse the array
        for (int i = 0; i<middleMan.length/2; ++i) {
            String temp = middleMan[i];
            middleMan[i] = middleMan[middleMan.length-1-i];
            middleMan[middleMan.length-1-i] = temp;
        }
        items.toArray(middleMan);
        bounds[1].setModel(new DefaultComboBoxModel<>(middleMan));
        
        items.clear();
        middleMan = MyDataset.getCities();
        items.addAll(Arrays.asList(middleMan).subList(0, MAX_CITIES));
        createDisplayedData(items);
        
        createChart(groupName);
        chartPanel.setBounds(PADDING, PADDING*4, USER_INPUT_X-PADDING*2, MainFrame.HEIGHT-PADDING*7);
        
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
        String categoryAxisLabel = groupName;
        String valueAxisLabel = "Number of People";
    
        chart = ChartFactory.createLineChart(chartTitle, categoryAxisLabel, valueAxisLabel, displayedData,
                PlotOrientation.VERTICAL, true, false, false);
        chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(BACKGROUND_COLOUR);
        add(chartPanel);
    
    }
    
    public void updateChart () {
        
        // TODO
        
    }
    
    @Override
    public void mouseClicked (MouseEvent e) {
        // TODO
    }
    
    @Override
    public void mousePressed (MouseEvent e) {
        // TODO
    }
    
    @Override
    public void mouseReleased (MouseEvent e) {
        // TODO
    }
    
    @Override
    public void mouseEntered (MouseEvent e) {
        // TODO
    }
    
    @Override
    public void mouseExited (MouseEvent e) {
        // TODO
    }
    
}
