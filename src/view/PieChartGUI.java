package view;

import util.Category;

import java.util.ArrayList;

public class PieChartGUI extends Tool {

    private TransportationPanel transportationPanel = new TransportationPanel();
    private HousingPanel housingPanel = new HousingPanel();

    public PieChartGUI (int x, int y, int width, int height, ArrayList<Category> dataset) {

        super(x, y, width, height, dataset);
        
//        setTitle("TYPES OF TRANSPORTATION AND HOUSING - DANIEL SINGH");
        setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
        setLayout(null);

        transportationPanel.setBounds(0, 0, 1600, 450);
        add(transportationPanel);

        housingPanel.setBounds(0, 450, 1360, 450);
        add(housingPanel);

        setVisible(true);

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
