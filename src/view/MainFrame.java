package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
	
	public static final int WIDTH = 1366;
	public static final int HEIGHT = 768;
	
	private JButton transportationAndHousingButton = new JButton("TRANSPORTATION AND HOUSING");
	private JButton densityMapButton = new JButton("DENSITY");
	private JButton lineChartButton = new JButton("LINE CHART");
	private JButton scatterPlotButton = new JButton("SCATTER PLOT");
	private JButton doubleBarButton = new JButton("DOUBLE BAR GRAPH");
	
	private DensityMapGUI densityMap = new DensityMapGUI(0, 0, WIDTH, HEIGHT);
	
	public MainFrame () {
		
		setTitle("DATA MINING PROJECT - FELIX DANIEL BRANDON SEAN STEVEN");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // To position window in the middle of the screen
		setLayout(null);
		
		transportationAndHousingButton.setBounds(450, 180, 500, 40);
		add(transportationAndHousingButton);

		densityMapButton.setBounds(450, 260, 500, 40);
		add(densityMapButton);
		densityMapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				repaint();
				densityMap.setVisible(true);
			}
		});

		lineChartButton.setBounds(450, 340, 500, 40);
		add(lineChartButton);

		scatterPlotButton.setBounds(450, 420, 500, 40);
		add(scatterPlotButton);

		doubleBarButton.setBounds(450, 500, 500, 40);
		add(doubleBarButton);
		
		setVisible(true);

		add(densityMap);
		densityMap.setVisible(false);
		
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
