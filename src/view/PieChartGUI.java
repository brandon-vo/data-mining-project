package view;

import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;

public class PieChartGUI extends Tool {

    private TransportationPanel transportationPanel = new TransportationPanel();
    private HousingPanel housingPanel = new HousingPanel();

    public PieChartGUI () {

//        setTitle("TYPES OF TRANSPORTATION AND HOUSING - DANIEL SINGH");
        setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
        setLayout(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);

        transportationPanel.setBounds(0, 0, 1600, 450);
        add(transportationPanel);

        housingPanel.setBounds(0, 100, 1600, 450);
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
    
    @Override
    public void addChangeListener (DatasetChangeListener datasetChangeListener) {
    
    }
    
    @Override
    public void removeChangeListener (DatasetChangeListener datasetChangeListener) {
    
    }
    
    @Override
    public DatasetGroup getGroup () {
        return null;
    }
    
    @Override
    public void setGroup (DatasetGroup datasetGroup) {
    
    }
    
}
