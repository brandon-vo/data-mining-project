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

    }
    
    private DefaultCategoryDataset createDataSet(String currentCity) {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<ArrayList<String>> rawData1 = FileImportController.rawData[1];
        ArrayList<ArrayList<String>> rawData2 = FileImportController.rawData[0];

        //2d array holding ranges as rows and totals of each commute type as columns
        int commuteTypeCount[][] = new int[5][6];

        for(int i = 1; i < barChartGui.getDataGroup().size(); i++ ){
            int parsedData = (int)(Double.parseDouble(rawData1.get(i).get(70)));
            if(rawData1.get(i).get(3).equals(currentCity) && (parsedData > 0 &&
                    parsedData <= 600)){

                commuteTypeCount[0][0] += (int)(Double.parseDouble(rawData2.get(i).get(10)));
                commuteTypeCount[0][1] += (int)(Double.parseDouble(rawData2.get(i).get(11)));
                commuteTypeCount[0][2] += (int)(Double.parseDouble(rawData2.get(i).get(12)));
                commuteTypeCount[0][3] += (int)(Double.parseDouble(rawData2.get(i).get(13)));
                commuteTypeCount[0][4] += (int)(Double.parseDouble(rawData2.get(i).get(14)));
                commuteTypeCount[0][5] += (int)(Double.parseDouble(rawData2.get(i).get(15)));


            }else if(rawData1.get(i).get(4).equals(currentCity) && (parsedData >= 601 &&
                    parsedData <= 1000)){

                commuteTypeCount[1][0] += (int)(Double.parseDouble(rawData2.get(i).get(10)));
                commuteTypeCount[1][1] += (int)(Double.parseDouble(rawData2.get(i).get(11)));
                commuteTypeCount[1][2] += (int)(Double.parseDouble(rawData2.get(i).get(12)));
                commuteTypeCount[1][3] += (int)(Double.parseDouble(rawData2.get(i).get(13)));
                commuteTypeCount[1][4] += (int)(Double.parseDouble(rawData2.get(i).get(14)));
                commuteTypeCount[1][5] += (int)(Double.parseDouble(rawData2.get(i).get(15)));

            }else if(rawData1.get(i).get(4).equals(currentCity) && (parsedData >= 1001 &&
                    parsedData <= 1400)){

                commuteTypeCount[2][0] += (int)(Double.parseDouble(rawData2.get(i).get(10)));
                commuteTypeCount[2][1] += (int)(Double.parseDouble(rawData2.get(i).get(11)));
                commuteTypeCount[2][2] += (int)(Double.parseDouble(rawData2.get(i).get(12)));
                commuteTypeCount[2][3] += (int)(Double.parseDouble(rawData2.get(i).get(13)));
                commuteTypeCount[2][4] += (int)(Double.parseDouble(rawData2.get(i).get(14)));
                commuteTypeCount[2][5] += (int)(Double.parseDouble(rawData2.get(i).get(15)));

            }else if (rawData1.get(i).get(4).equals(currentCity) && (parsedData >= 1401 &&
                    parsedData <= 1800)){

                commuteTypeCount[3][0] += (int)(Double.parseDouble(rawData2.get(i).get(10)));
                commuteTypeCount[3][1] += (int)(Double.parseDouble(rawData2.get(i).get(11)));
                commuteTypeCount[3][2] += (int)(Double.parseDouble(rawData2.get(i).get(12)));
                commuteTypeCount[3][3] += (int)(Double.parseDouble(rawData2.get(i).get(13)));
                commuteTypeCount[3][4] += (int)(Double.parseDouble(rawData2.get(i).get(14)));
                commuteTypeCount[3][5] += (int)(Double.parseDouble(rawData2.get(i).get(15)));

            }else if(rawData1.get(i).get(4).equals(currentCity) && (parsedData > 1800)){

                commuteTypeCount[4][0] += (int)(Double.parseDouble(rawData2.get(i).get(10)));
                commuteTypeCount[4][1] += (int)(Double.parseDouble(rawData2.get(i).get(11)));
                commuteTypeCount[4][2] += (int)(Double.parseDouble(rawData2.get(i).get(12)));
                commuteTypeCount[4][3] += (int)(Double.parseDouble(rawData2.get(i).get(13)));
                commuteTypeCount[4][4] += (int)(Double.parseDouble(rawData2.get(i).get(14)));
                commuteTypeCount[4][5] += (int)(Double.parseDouble(rawData2.get(i).get(15)));

            }else if(rawData1.get(i).get(4).equals(currentCity) && (Integer.parseInt(rawData1.get(i).get(70)) == 0)){
                continue;
            }
        }
        int rangeMax[] = new int[5];

        largestValue(commuteTypeCount, rangeMax);

        dataset.addValue(rangeMax[0], currentCity, "0-600");
        dataset.addValue(rangeMax[1], currentCity, "601-1000");
        dataset.addValue(rangeMax[2], currentCity, "1001-1400");
        dataset.addValue(rangeMax[3], currentCity, "1401-1800");
        dataset.addValue(rangeMax[4], currentCity, "1800+");


        return dataset;
    }
    
    public void createChart (String groupNameProfileOfHousing, DefaultCategoryDataset displayedData) {
        
        String chartTitle = " Commute Type V.S" + groupNameProfileOfHousing;
        String xAxisLabel = groupNameProfileOfHousing + "($)";
        String valueAxisLabel = "Number of People";
    
        barChartGui.setBarChart(ChartFactory.createBarChart(
                chartTitle, xAxisLabel, valueAxisLabel,
                displayedData, PlotOrientation.VERTICAL,
                true, false, false));

        if (barChartGui.getChartPanel()!=null) {
            barChartGui.remove(barChartGui.getChartPanel());
        }
        barChartGui.setChartPanel(new ChartPanel(barChartGui.getBarChart()));
        barChartGui.getChartPanel().setBounds(150, 100, 1000, 450);
        barChartGui.add(barChartGui.getChartPanel());
        
    }

    private void largestValue(int commuteTypeCount[][], int rangeMax[]) {

        for(int i = 0; i < commuteTypeCount.length; i++){

            rangeMax[i] = commuteTypeCount[i][0];

            for(int j = 0; j < commuteTypeCount[i].length; j++)
                if(rangeMax[i] < commuteTypeCount[i][j])
                    rangeMax[i] = commuteTypeCount[i][j];

        }

    }
    
    @Override
    public void actionPerformed (ActionEvent e) {

        if(e.getSource() == barChartGui.markhamButton){
            System.out.println("woo");
            barChartGui.setCurrentCity("Markham");
            barChartGui.setDisplayedData(createDataSet(barChartGui.getCurrentCity()));
            createChart(barChartGui.getValidGroupNames(1).get(0), barChartGui.getDisplayedData());
            barChartGui.getChartPanel().repaint();


        }else if(e.getSource() == barChartGui.richmondButton){
            barChartGui.setCurrentCity("Richmond Hill");
            barChartGui.setDisplayedData(createDataSet(barChartGui.getCurrentCity()));
            createChart(barChartGui.getValidGroupNames(1).get(0), barChartGui.getDisplayedData());
            barChartGui.getChartPanel().repaint();


        }else if(e.getSource() == barChartGui.auroraButton){
            barChartGui.setCurrentCity("Aurora");
            barChartGui.setDisplayedData(createDataSet(barChartGui.getCurrentCity()));
            createChart(barChartGui.getValidGroupNames(1).get(0), barChartGui.getDisplayedData());
            barChartGui.getChartPanel().repaint();


        }else if(e.getSource() == barChartGui.newMarketButton){
            barChartGui.setCurrentCity("Newmarket");
            barChartGui.setDisplayedData(createDataSet(barChartGui.getCurrentCity()));
            createChart(barChartGui.getValidGroupNames(1).get(0), barChartGui.getDisplayedData());
            barChartGui.getChartPanel().repaint();


        }else if(e.getSource() == barChartGui.vaughnButton){
            barChartGui.setCurrentCity("Vaughan");
            barChartGui.setDisplayedData(createDataSet(barChartGui.getCurrentCity()));
            createChart(barChartGui.getValidGroupNames(1).get(0), barChartGui.getDisplayedData());
            barChartGui.getChartPanel().repaint();

        }
    
    }
    
}
