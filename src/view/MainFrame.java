/**
 * Description:
 * Creates a menu panel object and a frame
 * @author: Daniel, Brandon
 */
package view;

import javax.swing.*;

public class MainFrame extends JFrame {
	
	//Constant width and height for the entire application
	public static final int WIDTH = 1366;
	public static final int HEIGHT = 768;
	
	private final MenuPanel menuPanel;

	public MainFrame () {
		
		// Initialize the frame
		setTitle("DATA MINING PROJECT - FELIX DANIEL BRANDON SEAN STEVEN");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // To position window in the middle of the screen
		setLayout(null);
		
		// Put the menu panel in it
		menuPanel = new MenuPanel();
		add(menuPanel);
		
	}
	
	// Getter for menu panel
	public MenuPanel getMenuPanel () {
		return menuPanel;
	}
	
}
