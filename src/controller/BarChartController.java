package controller;

import model.MyDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import view.CommuteVsShelterCostGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

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

        createChart(barChartGui.getValidGroupNames(1).get(0), barChartGui.getDisplayedData());

        barChartGui.getChartPanel().setBounds(150, 100, 1000, 450);
        
    }
    
    private DefaultCategoryDataset createDataSet(String currentCity) {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<ArrayList<String>> rawData1 = FileImportController.rawData[1];
        ArrayList<ArrayList<String>> rawData2 = FileImportController.rawData[0];
        int range1Max, range2Max, range3Max, range4Max, range5Max;
        //array list of ranges containing array list holding totals of each commute type
        ArrayList<ArrayList<Integer>> commuteTypeCount = new ArrayList<>();

        for(int i = 1; i < barChartGui.getDataGroup().size(); i++ ){
            
            if(rawData1.get(i).get(4).equals(currentCity) && (Integer.parseInt(rawData1.get(i).get(70)) > 0 &&
                    Integer.parseInt(rawData1.get(i).get(70)) <= 600)){

//                 = Integer.parseInt(rawData2.get(i).get(10));

            }else if(rawData1.get(i).get(4).equals(currentCity) && (Integer.parseInt(rawData1.get(i).get(70)) >= 601 &&
                    Integer.parseInt(rawData1.get(i).get(70)) <= 1000)){

            }else if(rawData1.get(i).get(4).equals(currentCity) && (Integer.parseInt(rawData1.get(i).get(70)) >= 1001 &&
                    Integer.parseInt(rawData1.get(i).get(70)) <= 1400)){

            }else if (rawData1.get(i).get(4).equals(currentCity) && (Integer.parseInt(rawData1.get(i).get(70)) >= 1401 &&
                    Integer.parseInt(rawData1.get(i).get(70)) <= 1800)){

            }else if(rawData1.get(i).get(4).equals(currentCity) && (Integer.parseInt(rawData1.get(i).get(70)) > 1800)){

            }else if(rawData1.get(i).get(4).equals(currentCity) && (Integer.parseInt(rawData1.get(i).get(70)) == 0)){
                continue;
            }
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

    private int largestValue(int commuteTypeCount[]) {

        int largestValue = commuteTypeCount[0];
        for (int index = 1; index < commuteTypeCount.length; index++) {

            if (commuteTypeCount[index] > largestValue) {
                largestValue = commuteTypeCount[index];
            }
        }
        return largestValue;
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {

        if(e.getSource() == barChartGui.markhamButton){
            barChartGui.setCurrentCity("Markham");
            barChartGui.setDisplayedData(createDataSet(barChartGui.getCurrentCity()));
            createChart(barChartGui.getValidGroupNames(1).get(0), barChartGui.getDisplayedData());
        }else if(e.getSource() == barChartGui.richmondButton){
            barChartGui.setCurrentCity("Richmond Hill");
            barChartGui.setDisplayedData(createDataSet(barChartGui.getCurrentCity()));
            createChart(barChartGui.getValidGroupNames(1).get(0), barChartGui.getDisplayedData());
        }else if(e.getSource() == barChartGui.auroraButton){
            barChartGui.setCurrentCity("Aurora");
            barChartGui.setDisplayedData(createDataSet(barChartGui.getCurrentCity()));
            createChart(barChartGui.getValidGroupNames(1).get(0), barChartGui.getDisplayedData());
        }else if(e.getSource() == barChartGui.newMarketButton){
            barChartGui.setCurrentCity("New Market");
            barChartGui.setDisplayedData(createDataSet(barChartGui.getCurrentCity()));
            createChart(barChartGui.getValidGroupNames(1).get(0), barChartGui.getDisplayedData());
        }else if(e.getSource() == barChartGui.vaughnButton){
            barChartGui.setCurrentCity("Vaughn");
            barChartGui.setDisplayedData(createDataSet(barChartGui.getCurrentCity()));
            createChart(barChartGui.getValidGroupNames(1).get(0), barChartGui.getDisplayedData());
        }
    
    }
    
}
