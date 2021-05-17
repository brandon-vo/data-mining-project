package controller;

import model.JourneyToWork;
import model.ProfileOfHousing;
import view.MainFrame;

public class ApplicationController {

	public MainFrame mainFrame;
	
	private JourneyToWork journeyToWork;
	private ProfileOfHousing profileOfHousing;
	
	public ApplicationController() {
		
		mainFrame = new MainFrame();
		
		// Assign the data to the fields
		FileImportController files = new FileImportController();
		files.importFiles();
		journeyToWork = files.getJourneyToWork();
		profileOfHousing = files.getProfileOfHousing();
		
		mainFrame.setVisible(true);
		
	}
	
}
