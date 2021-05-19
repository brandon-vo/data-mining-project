package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TransportationPanel extends JPanel {

	private JButton cityButton = new JButton("SELECT CITY");

	public TransportationPanel () {

		setBorder(BorderFactory.createLineBorder(Color.black));

		cityButton.setBounds(50, 60, 200, 200);
		add(cityButton);

		PieDataset dataset = createDataset();
		JFreeChart chart = ChartFactory.createPieChart("Types of transportation", dataset, true, true, false);
		ChartPanel chartPanel = new ChartPanel(chart);

		chartPanel.setBounds(500, 5, 500, 340);
		add(chartPanel);

		setVisible(true);
	}

	private static PieDataset createDataset(){
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("MARKHAM", 50 );
		dataset.setValue("VAUGHAN", 70);
		return dataset;
	}

	public JButton getCityButton() {
		return cityButton;
	}

	public void setCityButton(JButton cityButton) {
		this.cityButton = cityButton;
	}
}
