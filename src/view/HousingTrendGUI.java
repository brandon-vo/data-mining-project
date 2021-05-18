package view;

import model.MyDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class HousingTrendGUI extends Tool {
    
    private JButton location = new JButton("LOCATION");
    private JButton variable = new JButton("VARIABLE");
    private ChartPanel chPanel;
    private JFreeChart scatterPlot; // TODO not initialized
    private XYSeriesCollection plot;
    private XYSeries series;
    
    public HousingTrendGUI () {
    
        setLayout(null);
        
        add(getBackButton());
        
        location.setBounds(200,100,100,100);
        add(location);
        
        variable.setBounds(200,400,100,100);
        add(variable);
        
        plot = new XYSeriesCollection();
        series = new XYSeries("House");
        
        plot.addSeries(series);
        
        chPanel = new ChartPanel(scatterPlot);
        chPanel.setPreferredSize(new Dimension(800,500));
        add(chPanel);
        
        setVisible(true);
        
    }
    
    @Override
    public void setDataToDisplay (MyDataset dataset, String groupName) {
        
        setDisplayedData(dataset.getDataset().get(groupName));
        for (int i = 0; i<getDisplayedData().size(); i++) {
            series.add(i+1, getDisplayedData().get(i).getCities().entrySet().iterator().next().getValue());
        }
    
        scatterPlot = ChartFactory.createScatterPlot(
                "Scatter Plot",
                "placeholder",
                "Number Of People",
                plot
        );
        
    }
    
}
