package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import util.Category;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class HousingTrendGUI extends Tool {
    
    private JButton location = new JButton("LOCATION");
    private JButton variable = new JButton("VARIABLE");
    private ChartPanel chPanel;
    private JFreeChart scatterPlot;
    private XYSeriesCollection plot;
    private XYSeries series;
    
    public HousingTrendGUI () {
    
        setLayout(null);
        
        setBackground(getBackgroundColour());
        add(getBackButton());
        
        location.setBounds(200,100,100,100);
        add(location);
        
        variable.setBounds(200,400,100,100);
        add(variable);
        
        plot = new XYSeriesCollection();
        series = new XYSeries("House");
        
        // TODO dataset defaults to nothing (unneeded code)
        for (int i = 0; i<getDataset().size(); i++) {
            series.add(i+1, getDataset().get(i).getCities().entrySet().iterator().next().getValue());
        }
        plot.addSeries(series);
        
        scatterPlot = ChartFactory.createScatterPlot(
                "Scatter Plot",
                "placeholder",
                "number",
                plot
        );
        
        chPanel = new ChartPanel(scatterPlot);
        chPanel.setPreferredSize(new Dimension(800,500));
        add(chPanel);
        
        setVisible(true);
        
    }
    
}
