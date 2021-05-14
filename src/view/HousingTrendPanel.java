package view;
import model.HousingTrend;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.util.ArrayList;

public class HousingTrendPanel extends JPanel{
    JButton location = new JButton("LOCATION");
    JButton variable = new JButton("VARIABLE");
    JFreeChart scatterPlot;
    public HousingTrendPanel(ArrayList<HousingTrend> data){
        setLayout(null);
        setBackground();
        setBounds();
        
        
        location.setBounds();
        variable.setBounds();
    }
}
