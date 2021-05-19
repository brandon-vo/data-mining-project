package view;

import java.awt.Color;

import model.MyDataset;
import model.ProfileOfHousing;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import util.Category;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PieChartUtil extends JPanel {
    
    private JButton cityButton = new JButton("SELECT CITY");
    
    private DefaultPieDataset<String> displayedData;
    
    public PieChartUtil (int x, int y, int width, int height) {
        
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        cityButton.setBounds();
        add(cityButton);
        
    }
    
    @Override
    public void initializeDataToDisplay (MyDataset dataset, String groupName) {
    
        displayedData = createDataset(MyDataset.getCities()[0]);
        JFreeChart chart = ChartFactory.createPieChart(groupName, displayedData,
                true, false, false);
        chart.setBackgroundPaint(BACKGROUND_COLOUR);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(500, 5, 500, 340);
        add(chartPanel);
        
    }
    
    private DefaultPieDataset<String> createDataset (String city) {
        
        // Get the total people
        double totalPeople = 0;
        for (Category category: getDataGroup()) {
            totalPeople += category.getCities().get(city);
        }
    
        displayedData = new DefaultPieDataset<>();
        for (Category category: getDataGroup()) {
            displayedData.setValue(category.getCategoryName(),
                    category.getCities().get(city)/totalPeople);
        }
        
        return displayedData;
        
    }
    
    public JButton getCityButton() {
        return cityButton;
    }
    
    public void setCityButton(JButton cityButton) {
        this.cityButton = cityButton;
    }
    
}
