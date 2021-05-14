package view;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PieChartFrame extends JFrame {
	
	private JButton backButton = new JButton("Back");
	private TransportationPanel transportationPanel = new TransportationPanel();
	private HousingPanel housingPanel = new HousingPanel();
	
	public PieChartFrame () {
		
		setTitle("TYPES OF TRANSPORTATION AND HOUSING - DANIEL SINGH");
		setSize(1600, 900);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		transportationPanel.setBounds(0, 0, 1600, 450);
		add(transportationPanel);
		
		housingPanel.setBounds(0, 100, 1600, 450);
		add(housingPanel);
		
		
		
		setVisible(true);
		
		
			}
	
	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	public TransportationPanel getTransportationPanel() {
		return transportationPanel;
	}

	public void setTransportationPanel(TransportationPanel transportationPanel) {
		this.transportationPanel = transportationPanel;
	}
	
	public HousingPanel getHousingPanel() {
		return housingPanel;
	}
	
	public void setHousingPanel(HousingPanel housingPanel) {
		this.housingPanel = housingPanel;
	}
}
