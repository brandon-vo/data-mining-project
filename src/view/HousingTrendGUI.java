package view;

import controller.ScatterPlotController;
import model.MyDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import util.Category;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class HousingTrendGUI extends Tool {
    
    private JLabel housingTrendTitle = new JLabel(new ImageIcon("img/titles/housingTrendTitle.png"));
    private JLabel selectLocation = new JLabel("Select Location");
    private JLabel selectVariable = new JLabel("Select Variable");
    private JComboBox<String> location;
    private JComboBox<String> variable;
    private ChartPanel chartPanel;
    private JFreeChart scatterPlotChart;
    private XYSeriesCollection displayedData;
    
    public HousingTrendGUI () {
        //set up labels and combo box
        housingTrendTitle.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        add(housingTrendTitle);
        
        selectLocation.setBounds(200, 100, 100, 50);
        add(selectLocation);
        String[] locationName = { "Vaughan", "Richmond Hill", "Markham", "East Gwillimbury", "Newmarket", "Georgina", "King" };
        
        location = new JComboBox<>(locationName);
        location.setBounds(200, 160, 100, 50);
        add(location);
    
        selectVariable.setBounds(200, 400, 100, 50);
        add(selectVariable);
        String[] variableName = { "room", "bed", "maintainer" };
        variable = new JComboBox<>(variableName);
        
        variable.setBounds(200, 460, 100, 50);
        add(variable);
        //set up series collector to collect xy coordinates
        displayedData = new XYSeriesCollection();
        
        setVisible(true);
        
    }
    //getters and setters

    public JComboBox<String> getLocation1() {
        return location;
    }

    public JComboBox<String> getVariable() {
        return variable;
    }
    
    public ChartPanel getChartPanel () {
        return chartPanel;
    }
    
    public void setChartPanel (ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }
    
    public JFreeChart getScatterPlotChart () {
        return scatterPlotChart;
    }
    
    public void setScatterPlotChart (JFreeChart scatterPlotChart) {
        this.scatterPlotChart = scatterPlotChart;
    }
    
    public XYSeriesCollection getDisplayedData () {
        return displayedData;
    }
    
    public void setDisplayedData (XYSeriesCollection displayedData) {
        this.displayedData = displayedData;
    }
    
}
