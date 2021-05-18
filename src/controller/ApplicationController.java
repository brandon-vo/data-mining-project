package controller;

import model.JourneyToWork;
import model.ProfileOfHousing;
import view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ApplicationController implements ActionListener, MouseListener {

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
	
	public void setupListeners() {
	
	
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
