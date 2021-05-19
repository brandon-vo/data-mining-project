package view;

import model.MyDataset;
import util.Category;

import java.util.ArrayList;

public class PieChartGUI extends Tool {

    private TransportationPanel transportationPanel = new TransportationPanel();
    private HousingPanel housingPanel = new HousingPanel();

    public PieChartGUI () {
        
        transportationPanel.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT/2);
        add(transportationPanel);

        housingPanel.setBounds(0, MainFrame.HEIGHT/2, MainFrame.WIDTH,MainFrame.HEIGHT/2);
        add(housingPanel);

        setVisible(true);

    }
    
    @Override
    public void setDataToDisplay (MyDataset dataset, String groupName) {
    
    }
    
    public TransportationPanel getTransportationPanel () {
        return transportationPanel;
    }

    public void setTransportationPanel (TransportationPanel transportationPanel) {
        this.transportationPanel = transportationPanel;
    }

    public HousingPanel getHousingPanel () {
        return housingPanel;
    }

    public void setHousingPanel (HousingPanel housingPanel) {
        this.housingPanel = housingPanel;
    }
    
}
