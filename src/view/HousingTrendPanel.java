package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import util.Category;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class HousingTrendPanel extends JPanel {
    JButton location = new JButton("LOCATION");
    JButton variable = new JButton("VARIABLE");
    ArrayList<Category> dataset;
    JFreeChart scatterPlot;
    XYSeriesCollection plot;
    XYSeries series;
    
    public HousingTrendPanel (ArrayList<Category> dataset) {
        
        setLayout(null);
        this.dataset = dataset;
        //        setBackground();
        //        setBounds();
        //
        //        location.setBounds();
        //        variable.setBoundgit pull
        //        s();
        plot = new XYSeriesCollection();
        
        series = new XYSeries("House");
        for (int i = 0; i<dataset.size(); i++) {
            series.add(i+1, dataset.get(i).getCities().entrySet().iterator().next().getValue());
        }
        plot.addSeries(series);
        
        scatterPlot = ChartFactory.createScatterPlot(
                "Scatter Plot",
                "placeholder",
                "number",
                plot
        );
        
    }
}
