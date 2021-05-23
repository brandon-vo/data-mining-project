package view;

import model.MyDataset;

public class PieChartGUI extends Tool {

    private PieChartUtil[] pieCharts;

    public PieChartGUI () {
        
        pieCharts = new PieChartUtil[2];
        for (int i = 0; i<pieCharts.length; ++i) {
            pieCharts[i] = new PieChartUtil();
            pieCharts[i].setBounds(0, i*MainFrame.HEIGHT/2, MainFrame.WIDTH, MainFrame.HEIGHT/2);
            add(pieCharts[i]);
        }
        
    }
    
    public PieChartUtil getPieCharts (int pieChart) {
        return pieCharts[pieChart];
    }
    
}
