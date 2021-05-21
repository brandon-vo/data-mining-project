package view;

import model.MyDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.data.category.*;
import util.Category;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CommuteVsShelterCostGUI extends Tool implements ActionListener {
    
    private JLabel titleLabel = new JLabel(new ImageIcon("img/titles/shelterCostVsCommonCommuteTypeTitle.png"));
    private JLabel currentDataDisplay = new JLabel("currentDisplayedDate");
    private JLabel buttonTitleLabel = new JLabel("Select City for Data Display");
    
    private JButton markhamButton = new JButton("Markham");
    private JButton vaughnButton = new JButton("Vaughn");
    private JButton richmondButton = new JButton("Richmond Hill");
    private JButton auroraButton = new JButton("Aurora");
    private JButton newMarketButton = new JButton("Newmarket");
    
    private DefaultCategoryDataset displayedData;
    private JFreeChart barChart;
    private ChartPanel chartPanel;
    private ArrayList<Category> dataGroup2;
    
    private String currentCity;
    
    public CommuteVsShelterCostGUI () {
        
        markhamButton.setFont(new Font("Tahoma",Font.PLAIN, 15));
        vaughnButton.setFont(new Font("Tahoma",Font.PLAIN, 15));
        richmondButton.setFont(new Font("Tahoma",Font.PLAIN, 15));
        auroraButton.setFont(new Font("Tahoma",Font.PLAIN, 15));
        newMarketButton.setFont(new Font("Tahoma",Font.PLAIN, 15));
        
        titleLabel.setBounds(0, 0, 1366, 768);
        add(titleLabel);
        
        add(getBackButton());
        
        buttonTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        Border border = BorderFactory.createLineBorder(Color.BLUE, 3);
        buttonTitleLabel.setBorder(border);
        buttonTitleLabel.setBounds(490, 555, 350, 35);
        add(buttonTitleLabel);
        
        markhamButton.setBounds(400, 600, 100, 40);
        vaughnButton.setBounds(530, 600, 100, 40);
        richmondButton.setBounds(660, 600, 150, 40);
        auroraButton.setBounds(840, 600, 100, 40);
        newMarketButton.setBounds(580, 650, 150, 40);
        
        add(markhamButton);
        add(vaughnButton);
        add(richmondButton);
        add(auroraButton);
        add(newMarketButton);
        
        
        createChart();
        setVisible(true);
        
    }
    
    private CategoryDataset createDataSet(String currentCity) {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        
        
        String firstRange = "0-600";
        String secondRange = "601-1000";
        String thirdRange = "1001-1400";
        String fourthRange = "1401-1800";
        String fifthRange = "1800+";
        
//         dataset.addValue(firstRangeMax, currentCity)
        
    
    
    
        return dataset;
    }
    
    @Override
    public void initializeDataToDisplay (MyDataset[] dataset) {
        
        // TODO since this requires two groups at once, the first
        //  you need another displayedData field
        String groupNameJourneyToWork = getValidGroups(0).get(0);
        String groupNameProfileOfHousing = getValidGroups(1).get(0);
        
        setDataGroup(dataset[0].getDataset().get(groupNameJourneyToWork));
        setDataGroup(dataset[1].getDataset().get(groupNameProfileOfHousing));
    
        createChart(groupNameProfileOfHousing, displayedData);
        chartPanel.setBounds(150, 100, 1000, 450);
        
    }
    
    public void createChart (String groupNameProfileOfHousing, DefaultCategoryDataset displayedData) {
        
        String chartTitle = " Commute Type V.S" + groupNameProfileOfHousing;
        String xAxisLabel = groupNameProfileOfHousing;
        String valueAxisLabel = "Number of People";
        
//        displayedData = createDataset();
//        barChart = ChartFactory.createBarChart(chartTitle, xAxisLabel, valueAxisLabel, displayedData, PlotOrientation.VERTICAL,
//                true, false, false);
        chartPanel = new ChartPanel(barChart);
        
        add(chartPanel);
        
    }
  
    
    @Override
    public void actionPerformed (ActionEvent e) {
    
    }
    
}
