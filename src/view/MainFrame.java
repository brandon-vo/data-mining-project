package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	
	public static final int WIDTH = 1366;
	public static final int HEIGHT = 768;
	
	private final MenuPanel menuPanel;
	
	public MainFrame () {
		
		setTitle("DATA MINING PROJECT - FELIX DANIEL BRANDON SEAN STEVEN");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // To position window in the middle of the screen
		setLayout(null);
		
		menuPanel = new MenuPanel();
		add(menuPanel);
		
	}
	
	public MenuPanel getMenuPanel () {
		return menuPanel;
	}
	
}
