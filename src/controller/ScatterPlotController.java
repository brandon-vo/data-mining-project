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
        put("ONE", 1);
        put("TWO", 2);
        put("THREE", 3);
        put("FOUR", 4);
        put("FIVE", 5);
        put("SIX", 6);
        put("SEVEN", 7);
        put("EIGHT", 8);
        put("NINE", 9);
        put("ZERO", 0);
        put("NO", 0);
    }};
    
    // TODO never initialized
    private static int cityIndex;
    private HousingTrendGUI gui;
    
    public ScatterPlotController (HousingTrendGUI gui) {
        this.gui = gui;
        
        setUpListeners();
    }
    
    public void setUpListeners () {
        gui.getLocation1().addActionListener(this);
        gui.getVariable().addActionListener(this);
        
    }
    
    public static int getCityIndex () {
        return cityIndex;
    }
    
    @Override
    public void initializeDataToDisplay (MyDataset[] dataset) {
        
        String groupName = gui.getValidGroupNames(1).get(0);
        gui.setDataGroup(groupName);
        createDisplayedData(MyDataset.getCities()[cityIndex]);
        
        gui.setScatterPlotChart(ChartFactory.createScatterPlot(
                "Number of House vs "+groupName,
                groupName, "Number Of House",
                gui.getDisplayedData()
        ));
        gui.getScatterPlotChart().setBackgroundPaint(BACKGROUND_COLOUR);
        gui.setChartPanel(new ChartPanel(gui.getScatterPlotChart()));
        gui.getChartPanel().setBounds(400, 150, MainFrame.WIDTH/2, MainFrame.HEIGHT/2);
        gui.add(gui.getChartPanel());
        
    }
    
    public void createDisplayedData (String cityName) {
        
        gui.getDisplayedData().removeAllSeries();
        XYSeries city = new XYSeries(cityName);
        
        for (Category category : gui.getDataGroup()) {
            
            // TODO resolve 1-4 since there are 2 numbers there
            for (Map.Entry<String, Integer> identifiers : STRING_TO_INTEGER.entrySet()) {
                if (category.getCategoryName().contains(identifiers.getKey())) {
                    city.add(identifiers.getValue(), category.getCities().get(cityName));
                    break;
                }
            }
            
        }
        gui.getDisplayedData().addSeries(city);
        
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        String location = (String) gui.getLocation1().getSelectedItem();
        createDisplayedData(location);
        
        //        switch(location){
        //            case "Vaughan":
        //                cityIndex=1;
        //                break;
        //            case"Richmond Hill":
        //                cityIndex=2;
        //                break;
        //            case"Markham":
        //                cityIndex=3;
        //                break;
        //            case"East Gwillimbury":
        //                cityIndex=4;
        //                break;
        //            case"Newmarket":
        //                cityIndex=5;
        //                break;
        //            case"Georgina":
        //                cityIndex=6;
        //                break;
        //            case"King":
        //                cityIndex=7;
        //                break;
        //        }
        
        switch ((String) gui.getVariable().getSelectedItem()) {
            case "room":
                
                break;
            case "bed":
                break;
            case "maintainer":
                break;
            default:
            
        }
    }
    
}
