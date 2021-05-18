package controller;

import model.DataType;
import model.JourneyToWork;
import model.ProfileOfHousing;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static model.DataType.*;

public class ApplicationController implements ActionListener, MouseListener {

	// GUI
	public MainFrame mainFrame;
	private final Tool[] tools = new Tool[DataType.values().length];
	
	private final JourneyToWork journeyToWork;
	private final ProfileOfHousing profileOfHousing;
	
	public ApplicationController() {
		
		mainFrame = new MainFrame();
		
		// Initialize the tools (must be before importing)
		tools[PIE_CHART.getValue()] = new PieChartGUI();
		tools[DENSITY_MAP.getValue()] = new DensityMapGUI();
		tools[LINE_CHART.getValue()] = new LineChartGUI();
		tools[SCATTER_CHART.getValue()] = new HousingTrendGUI();
		tools[BAR_CHART.getValue()] = new CommuteVsShelterCostGUI();
		
		// Assign the data to the fields
		FileImportController files = new FileImportController();
		files.importFiles();
		journeyToWork = files.getJourneyToWork();
		profileOfHousing = files.getProfileOfHousing();
		
		// Provide the valid groups
		journeyToWork.assignValidGroupCharts(tools);
		profileOfHousing.assignValidGroupCharts(tools);
		
		for (int i = 0; i<tools.length; ++i) {
			
			// Default to JourneyToWork
			tools[i].setDataToDisplay(journeyToWork, tools[i].getValidGroups().get(0));
			
			final int j = i;
			mainFrame.getMenuPanel().getButton(i).addActionListener(e->{
				
				mainFrame.getContentPane().removeAll();
				mainFrame.getContentPane().repaint();
				mainFrame.add(tools[j]);
				tools[j].setVisible(true);
				
			});
			
		}
		
		mainFrame.setVisible(true);
		
	}
	
	public void setUpListeners() {
	
	
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
	
	}
	
	@Override
	public void mouseClicked (MouseEvent e) {
	
	}
	
	@Override
	public void mousePressed (MouseEvent e) {
	
	}
	
	@Override
	public void mouseReleased (MouseEvent e) {
	
	}
	
	@Override
	public void mouseEntered (MouseEvent e) {
	
	}
	
	@Override
	public void mouseExited (MouseEvent e) {
	
	}
	
}
