package controller;

import view.MainFrame;

public class ApplicationController {

	static MainFrame mainFrame;
	
	public ApplicationController() {
		
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}
}
