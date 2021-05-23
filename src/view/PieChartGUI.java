package view;

import model.MyDataset;

public class PieChartGUI extends Tool {

    private PieChartUtil[] pieCharts;

    public PieChartGUI () {
        
        pieCharts = new PieChartUtil[2];
        for (int i = 0; i<pieCharts.length; ++i) {
            pieCharts[i] = new PieChartUtil(i);
            pieCharts[i].setBounds(0, i*MainFrame.HEIGHT/2, MainFrame.WIDTH, MainFrame.HEIGHT/2);
            add(pieCharts[i]);
        }
        
    }
    
    @Override
    public void initializeDataToDisplay (MyDataset[] dataset) {
        pieCharts[0].initializeDataToDisplay(dataset[0], getValidGroupNames(0).get(0));
        pieCharts[1].initializeDataToDisplay(dataset[1], getValidGroupNames(1).get(0));
    }
    
}
