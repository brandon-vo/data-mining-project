package view;
/**
created by Daniel Singh
sets up the frame that holds all the charts and
reads in datasets
replaces the charts when a city is selected
 */

import java.awt.Color;
import java.util.ArrayList;

import model.MyDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultPieDataset;
import util.Category;

import javax.swing.*;

public class PieChartPanel extends JPanel {
    
    private final JButton cityButton = new JButton("SELECT CITY");
    private static final String PIE_CHART_TITLE_FINAL = "./img/titles/typesOfHousingAndTransportationTitle.png";
    private final JLabel titleLabel;

    private String groupName;
    private ArrayList<Category> dataGroup;
    private DefaultPieDataset<String> displayedData;
    private ChartPanel chartPanel;
    
    public PieChartPanel() {
        
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.black));
        cityButton.setBounds(50, MainFrame.HEIGHT/4-25, 200, 50);
        add(cityButton);

        titleLabel = new JLabel(new ImageIcon(PIE_CHART_TITLE_FINAL));
        titleLabel.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        add(titleLabel);
        
    }
    
    public void initializeDataToDisplay (MyDataset dataset, String groupName) {
        
        // Copy the given group into the dataGroup field
        this.groupName = groupName;
        dataGroup = dataset.getDataset().get(groupName);
        createChart(groupName, MyDataset.getCities()[0]);
        
    }

    //create a dataset based on user city choice
    public DefaultPieDataset<String> createDataset (String city) {
        
        displayedData = new DefaultPieDataset<>();
        for (Category category : dataGroup) {
            displayedData.setValue(category.getCategoryName(), category.getOGCityData(city));
        }
        
        return displayedData;
        
    }

    //Create a new pie chart to replace the old one
    public void createChart (String groupName, String city) {
        
        displayedData = createDataset(city);
        JFreeChart chart = ChartFactory.createPieChart(groupName + "" + city, displayedData,
                true, false, false);
        chart.setBackgroundPaint(Tool.BACKGROUND_COLOUR);

        //check if the chart is null
        if (chartPanel!=null)
            remove(chartPanel);
        chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(500, 70, 500, MainFrame.HEIGHT/2-100);
        add(chartPanel);
        //change the actual visual chart
        chartPanel.repaint();
        
    }
    
    public JButton getCityButton () {
        return cityButton;
    }
    
    public String getGroupName () {
        return groupName;
    }
    
    public ArrayList<Category> getDataGroup () {
        return dataGroup;
    }

}
