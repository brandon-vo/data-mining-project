package view;

import javax.swing.JButton;

public class MainFrame extends JFrame {
	private JButton transportationAndHousingButton = new JButton("TRANSPORTATION AND HOUSING");
	private JButton densityMapButton = new JButton("DENSITY");
	private JButton lineChartButton = new JButton("LINE CHART");
	private JButton scatterPlotButton = new JButton("SCATTER PLOT");
	private JButton doubleBarButton = new JButton("DOUBLE BAR GRAPH");
	
	public MainFrame() {
		
		setTitle("DATA MINING PROJECT - FELIX DANIEL BRANDON SEAN STEVEN");
		setSize(1600, 900);
		setLayout(null);
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		transportationAndHousingButton.setBounds(650, 100, 500, 40);
		add(transportationAndHousingButton);
		
		densityMapButton.setBounds(650, 180, 500, 40);
		add(densityMapButton);
		
		lineChartButton.setBounds(650, 260, 500, 40);
		add(lineChartButton);
		
		scatterPlotButton.setBounds(650, 340, 500, 40);
		add(scatterPlotButton);
		
		doubleBarButton.setBounds(650, 420, 500, 40);
		add(doubleBarButton);
		
		setVisible(true);
		
	}

	public JButton getTransportationAndHousingButton() {
		return transportationAndHousingButton;
	}

	public void setTransportationAndHousingButton(JButton transportationAndHousingButton) {
		this.transportationAndHousingButton = transportationAndHousingButton;
	}

	public JButton getDensityMapButton() {
		return densityMapButton;
	}

	public void setDensityMapButton(JButton densityMapButton) {
		this.densityMapButton = densityMapButton;
	}

	public JButton getLineChartButton() {
		return lineChartButton;
	}

	public void setLineChartButton(JButton lineChartButton) {
		this.lineChartButton = lineChartButton;
	}

	public JButton getScatterPlotButton() {
		return scatterPlotButton;
	}

	public void setScatterPlotButton(JButton scatterPlotButton) {
		this.scatterPlotButton = scatterPlotButton;
	}

	public JButton getDoubleBarButton() {
		return doubleBarButton;
	}

	public void setDoubleBarButton(JButton doubleBarButton) {
		this.doubleBarButton = doubleBarButton;
	}
	
	
	
}
}
