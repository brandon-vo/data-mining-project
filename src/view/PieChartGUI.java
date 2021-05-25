package view;

import javax.swing.*;

public class PieChartGUI extends Tool {

    private PieChartPanel[] pieCharts;

    public PieChartGUI () {

        //create the pie charts on the frame
        pieCharts = new PieChartPanel[2];
        for (int i = 0; i<pieCharts.length; ++i) {
            pieCharts[i] = new PieChartPanel();
            pieCharts[i].setBounds(0, i*MainFrame.HEIGHT/2, MainFrame.WIDTH, MainFrame.HEIGHT/2);
            add(pieCharts[i]);
        }

    }
    
    public PieChartPanel getPieCharts (int pieChart) {
        return pieCharts[pieChart];
    }
    
}
