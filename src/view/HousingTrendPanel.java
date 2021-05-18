package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import util.Category;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class HousingTrendPanel extends Tool {
    
    private JButton location = new JButton("LOCATION");
    private JButton variable = new JButton("VARIABLE");
    private ChartPanel chPanel;
    private JFreeChart scatterPlot;
    private XYSeriesCollection plot;
    private XYSeries series;
    
    public HousingTrendPanel (int x, int y, int width, int height, ArrayList<Category> datasets) {
    
        super(x, y, width, height, datasets);
        setLayout(null);
        
        setBackground(getBackgroundColour());
        add(getBackButton());
        
        location.setBounds(200,100,100,100);
        add(location);
        
        variable.setBounds(200,400,100,100);
        add(variable);
        
        plot = new XYSeriesCollection();
        series = new XYSeries("House");
        for (int i = 0; i<datasets.size(); i++) {
            series.add(i+1, datasets.get(i).getCities().entrySet().iterator().next().getValue());
        }
        plot.addSeries(series);
        
        scatterPlot = ChartFactory.createScatterPlot(
                "Scatter Plot",
                "placeholder",
                "number",
                plot
        );
        
        chPanel = new ChartPanel(scatterPlot);
        chPanel.setPreferredSize(new );
        
    }
    
}
