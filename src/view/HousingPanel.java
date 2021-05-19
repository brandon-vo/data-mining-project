package view;

import java.awt.Color;

import model.ProfileOfHousing;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HousingPanel extends JPanel {

	private JButton cityButton = new JButton("SELECT CITY");

	public HousingPanel() {

		setBorder(BorderFactory.createLineBorder(Color.black));

		cityButton.setBounds(50, 60, 200, 200);
		add(cityButton);

		PieDataset dataset = createDataset();
		JFreeChart chart = ChartFactory.createPieChart("Types of housing", dataset, true, true, false);
		ChartPanel chartPanel = new ChartPanel(chart);

		chartPanel.setBounds(500, 5, 500, 340);
		add(chartPanel);

		setVisible(true);

	}

	private static PieDataset createDataset(){
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Condo", 50 );
		dataset.setValue("Not Condo", 70);
		return dataset;
	}

	public JButton getCityButton() {
		return cityButton;
	}

	public void setCityButton(JButton cityButton) {
		this.cityButton = cityButton;
	}

}
