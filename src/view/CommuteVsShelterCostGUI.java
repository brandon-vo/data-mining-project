package view;

import model.*;
import controller.FileImportController;
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

public class CommuteVsShelterCostGUI extends Tool{

    //components
    private JLabel titleLabel = new JLabel(new ImageIcon("img/titles/shelterCostVsCommonCommuteTypeTitle.png"));
    private JLabel buttonTitleLabel = new JLabel("Select City for Data Display");
    
    public JButton markhamButton = new JButton("Markham");
    public JButton vaughnButton = new JButton("Vaughan");
    public JButton richmondButton = new JButton("Richmond Hill");
    public JButton auroraButton = new JButton("Aurora");
    public JButton newMarketButton = new JButton("Newmarket");
    
    private DefaultCategoryDataset displayedData = new DefaultCategoryDataset();
    private JFreeChart barChart;
    private ChartPanel chartPanel;
    private ArrayList<Category> dataGroup2;

    private String currentCity;

    //constructor
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
        
    }
    //getters and setters
    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        
        this.currentCity = currentCity;
    }
    
    public DefaultCategoryDataset getDisplayedData () {
        return displayedData;
    }
    
    public void setDisplayedData (DefaultCategoryDataset displayedData) {
        this.displayedData = displayedData;
    }
    
    public JFreeChart getBarChart () {
        return barChart;
    }
    
    public void setBarChart (JFreeChart barChart) {
        this.barChart = barChart;
    }
    
    public ChartPanel getChartPanel () {
        return chartPanel;
    }
    
    public void setChartPanel (ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }
    
    public ArrayList<Category> getDataGroup2 () {
        return dataGroup2;
    }
    
    public void setDataGroup2 (ArrayList<Category> dataGroup2) {
        this.dataGroup2 = dataGroup2;
    }
    
    
    
}
