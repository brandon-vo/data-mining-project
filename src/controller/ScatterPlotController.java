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
        put("No 1 4", 4);
        put("No 5", 5);
        put("No 6", 6);
        put("No 7", 7);
        put("No 8 Plus", 8);
    }};
    
    private HousingTrendGUI gui;
    
    public ScatterPlotController (HousingTrendGUI gui) {
        this.gui = gui;
        
        setUpListeners();
    }
    
    public void setUpListeners () {
        gui.getLocation1().addActionListener(this);
        gui.getVariable().addActionListener(this);
        
    }
    
    @Override
    public void initializeDataToDisplay (MyDataset[] dataset) {
        
        String groupName = gui.getValidGroupNames(1).get(2);
        
        setDataToDisplay(groupName);
        
    }
    
    private void setDataToDisplay (String groupName) {
        
        gui.setDataGroup(groupName);
        
        createDisplayedData("Vaughan");
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
        
        gui.setChartPanel(new ChartPanel(gui.getScatterPlotChart()));
        gui.getChartPanel().setBounds(400, 150, MainFrame.WIDTH/2, MainFrame.HEIGHT/2);
        gui.add(gui.getChartPanel());
        System.out.println(groupName);
        
    }
    
    public void createDisplayedData (String cityName) {
        
        gui.getDisplayedData().removeAllSeries();
        XYSeries city = new XYSeries(cityName);
        
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
    
    @Override
    public void actionPerformed (ActionEvent e) {
        
        if (e.getSource()==gui.getLocation1()) {
            updateDisplayedCities();
        } else if (e.getSource()==gui.getVariable()) {
            updateVariable();
        }
        gui.repaint();
        
    }
    
    private void updateVariable () {
        switch ((String) gui.getVariable().getSelectedItem()) {
            case "room":
                setDataToDisplay(gui.getValidGroupNames(1).get(2));
                break;
            case "bed":
                setDataToDisplay(gui.getValidGroupNames(1).get(0));
                break;
            case "maintainer":
                setDataToDisplay(gui.getValidGroupNames(1).get(1));
                break;
        }
        gui.repaint();
    }
    
    private void updateDisplayedCities () {
        String location = (String) gui.getLocation1().getSelectedItem();
        createDisplayedData(location);
    }
    
}
