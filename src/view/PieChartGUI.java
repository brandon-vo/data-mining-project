package view;

import model.MyDataset;

import javax.swing.*;

public class PieChartGUI extends Tool {

    private PieChartUtil[] pieCharts;

    public PieChartGUI () {
        
        pieCharts = new PieChartUtil[2];
        
        pieCharts[0] = new PieChartUtil();
        pieCharts[0].setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT/2);
        add(pieCharts[0]);
    
        pieCharts[1] = new PieChartUtil();
        pieCharts[1].setBounds(0, MainFrame.HEIGHT/2, MainFrame.WIDTH,MainFrame.HEIGHT/2);
        add(pieCharts[1]);
        
    }
    
}
