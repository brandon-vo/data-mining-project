package controller;

import model.MyDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeries;
import util.Category;
import view.HousingTrendGUI;
import view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import static view.HousingTrendGUI.*;

public class ScatterPlotController extends ToolController implements ActionListener {

    //transfer string to integer using hashmap
    private static final HashMap<String, Integer> STRING_TO_INTEGER = new HashMap<>() {{
        put("One", 1);
        put("Two", 2);
        put("Three", 3);
        put("Four", 4);
        put("Five", 5);
        put("Six", 6);
        put("Seven", 7);
        put("Eight", 8);
        put("Nine", 9);
        put("Zero", 0);
        put("1 4", 4);
        put("5", 5);
        put("6", 6);
        put("7", 7);
        put("8 Plus", 8);
        put("1",1);
        put("2",2);
        put("3 Plus",3);
    }};
    
    private HousingTrendGUI gui;
    //constructor
    public ScatterPlotController (HousingTrendGUI gui) {
        this.gui = gui;
        
        setUpListeners();
    }
    //set up action listener
    public void setUpListeners () {
        gui.getLocation1().addActionListener(this);
        gui.getVariable().addActionListener(this);
        
    }
    //set up the initial data
    @Override
    public void initializeDataToDisplay (MyDataset[] dataset) {
        
        String groupName = gui.getValidGroupNames(1).get(1);
        
        setDataToDisplay(groupName);
        
    }
    //get group name which is the variable and change it accordingly
    private void setDataToDisplay (String groupName) {
        
        gui.setDataGroup(groupName);
        //initial city
        createDisplayedData("Vaughan");

        //create chart
        gui.setScatterPlotChart(ChartFactory.createScatterPlot(
                "Number of House vs "+groupName,
                groupName, "Number Of House",
                gui.getDisplayedData()
        ));
        gui.getScatterPlotChart().setBackgroundPaint(BACKGROUND_COLOUR);
    
        // Remove if the chart panel exists
        if (gui.getChartPanel()!=null) {
            gui.remove(gui.getChartPanel());
        }

        //add the chart to the panel
        gui.setChartPanel(new ChartPanel(gui.getScatterPlotChart()));
        gui.getChartPanel().setBounds(400, 150, MainFrame.WIDTH/2, MainFrame.HEIGHT/2);
        gui.add(gui.getChartPanel());
        System.out.println(groupName);
        
    }
    //input data to the series
    public void createDisplayedData (String cityName) {
        //clear data after using combo box
        gui.getDisplayedData().removeAllSeries();
        XYSeries city = new XYSeries(cityName);
        //input data
        for (Category category : gui.getDataGroup()) {
            
            for (Map.Entry<String, Integer> identifiers : STRING_TO_INTEGER.entrySet()) {
                if (category.getCategoryName().contains(identifiers.getKey())) {
                    city.add(identifiers.getValue(), category.getCities().get(cityName));
                    System.out.println(category.getCities().get(cityName));
                    break;
                }
            }
            
        }
        gui.getDisplayedData().addSeries(city);
        
    }
    //2 cases when combo box is selected
    @Override
    public void actionPerformed (ActionEvent e) {
        
        if (e.getSource()==gui.getLocation1()) {
            updateDisplayedCities();
        } else if (e.getSource()==gui.getVariable()) {
            updateVariable();
        }
        gui.repaint();
        
    }
    //update my variable combo box and the data accordingly
    private void updateVariable () {
        switch ((String) gui.getVariable().getSelectedItem()) {
            case "room":
                setDataToDisplay(gui.getValidGroupNames(1).get(1));
                break;
            case "bed":
                setDataToDisplay(gui.getValidGroupNames(1).get(0));
                break;
            case "maintainer":
                setDataToDisplay(gui.getValidGroupNames(1).get(2));
                break;
        }
        gui.repaint();
    }
    //update my location combo box and the data accordingly
    private void updateDisplayedCities () {
        String location = (String) gui.getLocation1().getSelectedItem();
        createDisplayedData(location);
    }
    
}
