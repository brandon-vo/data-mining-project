package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
	
	public static final int WIDTH = 1366;
	public static final int HEIGHT = 768;
	
	private JButton transportationAndHousingButton = new JButton("TRANSPORTATION AND HOUSING");
	private JButton densityMapButton = new JButton("DENSITY MAP");
	private JButton lineChartButton = new JButton("LINE CHART");
	private JButton scatterPlotButton = new JButton("SCATTER PLOT");
	private JButton doubleBarButton = new JButton("DOUBLE BAR GRAPH");
	private JLabel titleLabel = new JLabel(new ImageIcon("img/mainFrameTitleLabel.png"));
	
	private DensityMapGUI densityMap = new DensityMapGUI(0, 0, WIDTH, HEIGHT);
	
	public MainFrame () {
		
		setTitle("DATA MINING PROJECT - FELIX DANIEL BRANDON SEAN STEVEN");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // To position window in the middle of the screen
		setLayout(null);

		titleLabel.setBounds(0, 0, 1366, 768);
		add(titleLabel);

		transportationAndHousingButton.setBounds(450, 200, 500, 40);
		transportationAndHousingButton.setBorder(null);
		transportationAndHousingButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		transportationAndHousingButton.setBackground(new Color(145, 172, 219));
		add(transportationAndHousingButton);

		densityMapButton.setBounds(450, 280, 500, 40);
		densityMapButton.setBorder(null);
		densityMapButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		densityMapButton.setBackground(new Color(145, 172, 219));
		add(densityMapButton);
		densityMapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().repaint();
				add(densityMap);
				densityMap.setVisible(true);
			}
		});

		lineChartButton.setBounds(450, 360, 500, 40);
		lineChartButton.setBorder(null);
		lineChartButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lineChartButton.setBackground(new Color(145, 172, 219));
		add(lineChartButton);

		scatterPlotButton.setBounds(450, 440, 500, 40);
		scatterPlotButton.setBorder(null);
		scatterPlotButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scatterPlotButton.setBackground(new Color(145, 172, 219));
		add(scatterPlotButton);

		doubleBarButton.setBounds(450, 520, 500, 40);
		doubleBarButton.setBorder(null);
		doubleBarButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		doubleBarButton.setBackground(new Color(145, 172, 219));
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
