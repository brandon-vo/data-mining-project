/**
 * Handles the necessary interactions between every class
 * - Initializes datasets
 * - Initializes controllers
 * - Initializes tools
 * - Switches the panels based on user input
 * @author Felix, Brandon, Daniel, Steven, Sean
 */

package controller;

import model.DataType;
import model.MyDataset;
import view.*;

import javax.swing.*;

import static model.DataType.*;

public class ApplicationController {
	
	// GUI
	private MainFrame mainFrame;
	private final Tool[] tools = new Tool[DataType.values().length];
	
	// Controllers
	private final ToolController[] controllers = new ToolController[DataType.values().length];
	
	private final MyDataset[] datasets;
	
	public ApplicationController() {
		
		mainFrame = new MainFrame();
		
		// Initialize the tools (must be before importing)
		tools[PIE_CHART.getValue()] = new PieChartGUI();
		tools[DENSITY_MAP.getValue()] = new DensityMapGUI();
		tools[LINE_CHART.getValue()] = new LineChartGUI();
		tools[SCATTER_CHART.getValue()] = new HousingTrendGUI();
		tools[BAR_CHART.getValue()] = new CommuteVsShelterCostGUI();
		
		controllers[PIE_CHART.getValue()] = new PieChartController((PieChartGUI) tools[PIE_CHART.getValue()]);
		controllers[DENSITY_MAP.getValue()] = new  DensityMapController((DensityMapGUI) tools[DENSITY_MAP.getValue()]);
		controllers[LINE_CHART.getValue()] = new LineChartController((LineChartGUI) tools[LINE_CHART.getValue()]);
		controllers[SCATTER_CHART.getValue()] = new ScatterPlotController((HousingTrendGUI) tools[SCATTER_CHART.getValue()]);
		controllers[BAR_CHART.getValue()] = new BarChartController((CommuteVsShelterCostGUI) tools[BAR_CHART.getValue()]);
		
		// Assign the data to the fields
		FileImportController files = new FileImportController();
		files.importFiles();
		datasets = new MyDataset[2];
		datasets[0] = files.getJourneyToWork();
		datasets[1] = files.getProfileOfHousing();
		
		// Provide the valid groups
		datasets[0].assignValidGroupCharts(tools);
		datasets[1].assignValidGroupCharts(tools);
		
		setUpListeners();
		mainFrame.setVisible(true);
		
	}
	
	public void setUpListeners () {
		
		// Set up the tool listeners
		for (int i = 0; i<DataType.values().length; ++i) {
			
			// Add functionality to the switch frames buttons
			final int j = i;
			mainFrame.getMenuPanel().getButton(i).addActionListener(e->{
				switchFrame(tools[j]);
				controllers[j].initializeDataToDisplay(datasets);
			});
			
			// Add functionality to the back buttons
			tools[i].getBackButton().addActionListener(e->
					switchFrame(mainFrame.getMenuPanel())
			);
			
		}
	
	}
	
	private void switchFrame (JPanel newPanel) {
		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().repaint();
		mainFrame.add(newPanel);
	}
	
}
