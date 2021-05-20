package controller;

import model.DataType;
import model.MyDataset;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static model.DataType.*;

public class ApplicationController {
	
	// GUI
	public MainFrame mainFrame;
	private final Tool[] tools = new Tool[DataType.values().length+1];
	
	private final MyDataset[] datasets;
	
	public ApplicationController() {
		
		mainFrame = new MainFrame();
		
		// Initialize the tools (must be before importing)
//		tools[PIE_CHART.getValue()] = new PieChartUtil();
//		tools[PIE_CHART.getValue()+1] = new PieChartUtil();
		tools[DENSITY_MAP.getValue()] = new DensityMapGUI();
		tools[LINE_CHART.getValue()] = new LineChartGUI();
		tools[SCATTER_CHART.getValue()] = new HousingTrendGUI();
		tools[BAR_CHART.getValue()] = new CommuteVsShelterCostGUI();
		
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
		
		for (int i = 0; i<DataType.values().length; ++i) {
			
			// Add functionality to the switch frames buttons
			final int j = i;
			mainFrame.getMenuPanel().getButton(i).addActionListener(e->{
				switchFrame(tools[j]);
<<<<<<< HEAD
				tools[j].initializeDataToDisplay(datasets);
=======
				if (!tools[j].getValidGroups(0).isEmpty()) {
					tools[j].initializeDataToDisplay(datasets[0],
							tools[j].getValidGroups(0).get(0));
				} else {
					tools[j].initializeDataToDisplay(datasets[1],
							tools[j].getValidGroups(1).get(0));
				}
>>>>>>> 2eaa5ba31b8d0c0c038e1a6a5e8949e00e246c83
			});
			
			// Add functionality to the back buttons
			tools[i].getBackButton().addActionListener(e->{
				switchFrame(mainFrame.getMenuPanel());
			});
			
		}
		
		// TODO everything else
	
	}
	
	private void switchFrame (JPanel newPanel) {
		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().repaint();
		mainFrame.add(newPanel);
	}
	
}
