package view;

import model.MyDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import util.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CommuteVsShelterCostGUI extends Tool implements ActionListener {

    private JLabel titleLabel = new JLabel(new ImageIcon("img/shelterCostVsCommonCommuteTypeLabel.png"));
    private JLabel currentDataDisplay = new JLabel("currentDisplayedDate");
    private JLabel buttonTitleLabel = new JLabel("Select City for Data Display");

    // TODO call MyDataset.getCities() to get the names of all the cities instead
    private JButton markhamButton = new JButton("Markham");
    private JButton vaughnButton = new JButton("Vaughn");
    private JButton richmondButton = new JButton("Richmond Hill");
    private JButton auroraButton = new JButton("Aurora");
    private JButton newMarketButton = new JButton("Newmarket");

    private DefaultCategoryDataset displayedData = new DefaultCategoryDataset();
    private JFreeChart barChart;
    private ChartPanel chartPanel;

    public CommuteVsShelterCostGUI () {

        markhamButton.setFont(new Font("Tahoma",Font.PLAIN, 15));
        vaughnButton.setFont(new Font("Tahoma",Font.PLAIN, 15));
        richmondButton.setFont(new Font("Tahoma",Font.PLAIN, 15));
        auroraButton.setFont(new Font("Tahoma",Font.PLAIN, 15));
        newMarketButton.setFont(new Font("Tahoma",Font.PLAIN, 15));

        titleLabel.setBounds(0, 0, 1366, 768);
        add(titleLabel);

        add(getBackButton());

        buttonTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        buttonTitleLabel.setBounds(455, 500, 150, 35);
        add(buttonTitleLabel);

        markhamButton.setBounds(200, 550, 100, 20);
        vaughnButton.setBounds(320, 550, 100, 20);
        richmondButton.setBounds(440, 550, 100, 20);
        auroraButton.setBounds(560, 550, 100, 20);
        newMarketButton.setBounds(380, 600, 100, 20);

        add(markhamButton);
        add(vaughnButton);
        add(richmondButton);
        add(auroraButton);
        add(newMarketButton);

        setVisible(true);

    }

    @Override
    public void initializeDataToDisplay (MyDataset[] dataset) {

        // TODO since this requires two groups at once, the first
        //  you need another displayedData field
        String groupNameJourneyToWork = getValidGroups(0).get(0);
        String groupNameProfileOfHousing = getValidGroups(1).get(0);

        setDataGroup(dataset[0].getDataset().get(groupNameJourneyToWork));
        setDataGroup(dataset[1].getDataset().get(groupNameProfileOfHousing));

        createChart(groupNameProfileOfHousing);

    }

    public void createChart (String groupNameProfileOfHousing) {

        String chartTitle = " Commute Type V.S" + groupNameProfileOfHousing;
        String xAxisLabel = groupNameProfileOfHousing;
        String valueAxisLabel = "Number of People";

        barChart = ChartFactory.createBarChart(chartTitle, xAxisLabel, valueAxisLabel, displayedData, PlotOrientation.VERTICAL,
                true, false, false);
        chartPanel = new ChartPanel(barChart);

        chartPanel.setBounds(,)


    }

    public void createDisplayedData(){

    }

    @Override
    public void actionPerformed (ActionEvent e) {

    }

}
