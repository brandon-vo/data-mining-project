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
        int commuteTypeCount[][] = new int[5][6];

        for(int i = 1; i < barChartGui.getDataGroup().size(); i++ ){
            
            if(rawData1.get(i).get(4).equals(currentCity) && (Integer.parseInt(rawData1.get(i).get(70)) > 0 &&
                    Integer.parseInt(rawData1.get(i).get(70)) <= 600)){

                commuteTypeCount[0][0] = Integer.parseInt(rawData2.get(i).get(10));
                commuteTypeCount[0][1] = Integer.parseInt(rawData2.get(i).get(11));
                commuteTypeCount[0][2] = Integer.parseInt(rawData2.get(i).get(12));
                commuteTypeCount[0][3] = Integer.parseInt(rawData2.get(i).get(13));
                commuteTypeCount[0][4] = Integer.parseInt(rawData2.get(i).get(14));
                commuteTypeCount[0][5] = Integer.parseInt(rawData2.get(i).get(15));


            }else if(rawData1.get(i).get(4).equals(currentCity) && (Integer.parseInt(rawData1.get(i).get(70)) >= 601 &&
                    Integer.parseInt(rawData1.get(i).get(70)) <= 1000)){

                commuteTypeCount[1][0] += Integer.parseInt(rawData2.get(i).get(10));
                commuteTypeCount[1][1] += Integer.parseInt(rawData2.get(i).get(11));
                commuteTypeCount[1][2] += Integer.parseInt(rawData2.get(i).get(12));
                commuteTypeCount[1][3] += Integer.parseInt(rawData2.get(i).get(13));
                commuteTypeCount[1][4] += Integer.parseInt(rawData2.get(i).get(14));
                commuteTypeCount[1][5] += Integer.parseInt(rawData2.get(i).get(15));

            }else if(rawData1.get(i).get(4).equals(currentCity) && (Integer.parseInt(rawData1.get(i).get(70)) >= 1001 &&
                    Integer.parseInt(rawData1.get(i).get(70)) <= 1400)){

                commuteTypeCount[2][0] += Integer.parseInt(rawData2.get(i).get(10));
                commuteTypeCount[2][1] += Integer.parseInt(rawData2.get(i).get(11));
                commuteTypeCount[2][2] += Integer.parseInt(rawData2.get(i).get(12));
                commuteTypeCount[2][3] += Integer.parseInt(rawData2.get(i).get(13));
                commuteTypeCount[2][4] += Integer.parseInt(rawData2.get(i).get(14));
                commuteTypeCount[2][5] += Integer.parseInt(rawData2.get(i).get(15));

            }else if (rawData1.get(i).get(4).equals(currentCity) && (Integer.parseInt(rawData1.get(i).get(70)) >= 1401 &&
                    Integer.parseInt(rawData1.get(i).get(70)) <= 1800)){

                commuteTypeCount[3][0] += Integer.parseInt(rawData2.get(i).get(10));
                commuteTypeCount[3][1] += Integer.parseInt(rawData2.get(i).get(11));
                commuteTypeCount[3][2] += Integer.parseInt(rawData2.get(i).get(12));
                commuteTypeCount[3][3] += Integer.parseInt(rawData2.get(i).get(13));
                commuteTypeCount[3][4] += Integer.parseInt(rawData2.get(i).get(14));
                commuteTypeCount[3][5] += Integer.parseInt(rawData2.get(i).get(15));

            }else if(rawData1.get(i).get(4).equals(currentCity) && (Integer.parseInt(rawData1.get(i).get(70)) > 1800)){

                commuteTypeCount[4][0] += Integer.parseInt(rawData2.get(i).get(10));
                commuteTypeCount[4][1] += Integer.parseInt(rawData2.get(i).get(11));
                commuteTypeCount[4][2] += Integer.parseInt(rawData2.get(i).get(12));
                commuteTypeCount[4][3] += Integer.parseInt(rawData2.get(i).get(13));
                commuteTypeCount[4][4] += Integer.parseInt(rawData2.get(i).get(14));
                commuteTypeCount[4][5] += Integer.parseInt(rawData2.get(i).get(15));

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
