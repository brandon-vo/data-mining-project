package view;

import model.MyDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class HousingTrendGUI extends Tool implements ItemListener {
    
    private JLabel selectLocation = new JLabel("Select Location");
    private JLabel selectVariable = new JLabel("Select Variable");
    private JComboBox location;
    private JComboBox variable;
    private ChartPanel chPanel;
    private JFreeChart scatterPlot;
    private XYSeriesCollection plot;
    private XYSeries series;
    
    public HousingTrendGUI () {
    
        setLayout(null);
        add(getBackButton());

        selectLocation.setBounds(200,100,100,50);
        add(selectLocation);
        String locationName[]={"Vaughan","Richmond Hill","Markham","East Gwillimbury","Newmarket","Georgina","King"};
        location= new JComboBox(locationName);
        location.addItemListener(this::itemStateChanged);
        location.setBounds(200,200,100,100);
        add(location);

        selectVariable.setBounds(200,400,100,50);
        add(selectVariable);
        String variableName[]={"room","bed","maintainer"};
        variable= new JComboBox(variableName);
        variable.addItemListener(this::itemStateChanged);
        variable.setBounds(200,500,100,100);
        add(variable);
        
        plot = new XYSeriesCollection();
        series = new XYSeries("House");
        
        plot.addSeries(series);

        chPanel = new ChartPanel(scatterPlot);
        chPanel.setBounds(450,100,800,500);
        add(chPanel);
        
        setVisible(true);
        
    }
    
    @Override
    public void setDataToDisplay (MyDataset dataset, String groupName) {
        
        setDataGroup(dataset.getDataset().get(groupName));
        for (int i = 0; i<getDataGroup().size(); i++) {
            series.add(i+1, getDataGroup().get(i).getCities().entrySet().iterator().next().getValue());
        }
    
        scatterPlot = ChartFactory.createScatterPlot(
                "Scatter Plot",
                "placeholder",
                "Number Of People",
                plot
        );
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
