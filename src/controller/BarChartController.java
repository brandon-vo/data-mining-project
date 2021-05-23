package controller;

import model.MyDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import view.CommuteVsShelterCostGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarChartController extends ToolController implements ActionListener {
    
    CommuteVsShelterCostGUI barChartGui;
    
    public BarChartController(CommuteVsShelterCostGUI barChartGui){
        this.barChartGui = barChartGui;
        setUpListeners();
    }
    
    public void setUpListeners(){
        
        barChartGui.markhamButton.addActionListener(this);
        barChartGui.auroraButton.addActionListener(this);
        barChartGui.newMarketButton.addActionListener(this);
        barChartGui.vaughnButton.addActionListener(this);
        barChartGui.richmondButton.addActionListener(this);
    
    }
    
    @Override
    public void initializeDataToDisplay (MyDataset[] dataset) {
        
        String groupNameJourneyToWork = barChartGui.getValidGroupNames(0).get(0);
        String groupNameProfileOfHousing = barChartGui.getValidGroupNames(1).get(0);
    
        barChartGui.setDataGroup(groupNameJourneyToWork);
        barChartGui.setDataGroup2(dataset[1].getDataset().get(groupNameProfileOfHousing));
    
        barChartGui.setDisplayedData(createDataSet(barChartGui.getCurrentCity()));
        createChart(groupNameProfileOfHousing, barChartGui.getDisplayedData());
        barChartGui.getChartPanel().setBounds(150, 100, 1000, 450);
        
    }
    
    private DefaultCategoryDataset createDataSet(String currentCity) {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        int range1MaxValue;
        int range2MaxValue;
        int range3MaxValue;
        int range4MaxValue;
        int range5MaxValue;
        
        for(int i = 0; i < barChartGui.getDataGroup().size(); i++ ){
            
            //            getRawData()
            
        }
        
        return dataset;
    }
    
    public void createChart (String groupNameProfileOfHousing, DefaultCategoryDataset displayedData) {
        
        String chartTitle = " Commute Type V.S" + groupNameProfileOfHousing;
        String xAxisLabel = groupNameProfileOfHousing;
        String valueAxisLabel = "Number of People";
    
        barChartGui.setBarChart(ChartFactory.createBarChart(
                chartTitle, xAxisLabel, valueAxisLabel,
                displayedData, PlotOrientation.VERTICAL,
                true, false, false));
        barChartGui.setChartPanel(new ChartPanel(barChartGui.getBarChart()));
    
        barChartGui.add(barChartGui.getChartPanel());
        
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
    
    }
    
}
