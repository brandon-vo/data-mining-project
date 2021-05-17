package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    
    public static final int WIDTH = 1366;
    public static final int HEIGHT = 768;
    
    private JButton transportationAndHousingButton = new JButton("TRANSPORTATION AND HOUSING");
    private JButton densityMapButton = new JButton("DENSITY");
    private JButton lineChartButton = new JButton("LINE CHART");
    private JButton scatterPlotButton = new JButton("SCATTER PLOT");
    private JButton doubleBarButton = new JButton("DOUBLE BAR GRAPH");
    
    private DensityMapGUI densityMap = new DensityMapGUI(0, 0, WIDTH, HEIGHT);
    
    public MainFrame () {
        
        setTitle("DATA MINING PROJECT - FELIX DANIEL BRANDON SEAN STEVEN");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // To position window in the middle of the screen
        setLayout(null);
        
        transportationAndHousingButton.setBounds(450, 180, 500, 40);
        add(transportationAndHousingButton);
        
        densityMapButton.setBounds(450, 260, 500, 40);
        add(densityMapButton);
        densityMapButton.addActionListener(e->{
            getContentPane().removeAll();
            getContentPane().repaint();
            densityMap.setVisible(true);
        });
        
        lineChartButton.setBounds(450, 340, 500, 40);
        add(lineChartButton);
        
        scatterPlotButton.setBounds(450, 420, 500, 40);
        add(scatterPlotButton);
        
        doubleBarButton.setBounds(450, 500, 500, 40);
        add(doubleBarButton);
        
        setVisible(true);
        
        add(densityMap);
        densityMap.setVisible(false);
        
    }
    
    public JButton getTransportationAndHousingButton () {
        return transportationAndHousingButton;
    }
    
    public JButton getDensityMapButton () {
        return densityMapButton;
    }
    
    public JButton getLineChartButton () {
        return lineChartButton;
    }
    
    public JButton getScatterPlotButton () {
        return scatterPlotButton;
    }
    
    public JButton getDoubleBarButton () {
        return doubleBarButton;
    }
    
}
