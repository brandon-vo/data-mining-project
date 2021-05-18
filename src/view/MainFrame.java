package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	
	public static final int WIDTH = 1366;
	public static final int HEIGHT = 768;
	
	private JLabel titleLabel = new JLabel(new ImageIcon("img/mainFrameTitleLabel.png"));
	
	private final JButton[] buttons;
	private Tool[] tools;
	
	public MainFrame () {
		
		setTitle("DATA MINING PROJECT - FELIX DANIEL BRANDON SEAN STEVEN");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // To position window in the middle of the screen
		setLayout(null);

		titleLabel.setBounds(0, 0, 1366, 768);
		add(titleLabel);
		
		tools = new Tool[] {
				new PieChartGUI(),
				new DensityMapGUI(),
				new LineChartGUI(),
				new HousingTrendGUI(),
				new CommuteVsShelterCostGUI()
		};
		
		buttons = new JButton[5];
		buttons[0] = new JButton("TRANSPORTATION AND HOUSING");
		buttons[1] = new JButton("DENSITY MAP");
		buttons[2] = new JButton("LINE CHART");
		buttons[3] = new JButton("SCATTER PLOT");
		buttons[4] = new JButton("DOUBLE BAR GRAPH");
		
		for (int i = 0; i<buttons.length; ++i) {
			
			buttons[i].setBounds(450, 220+i*80, 470, 40);
			buttons[i].setBorder(null);
			buttons[i].setFont(new Font("Tahoma", Font.PLAIN, 15));
			buttons[i].setBackground(new Color(145, 172, 219));
			add(buttons[i]);
			
			final int j = i;
			buttons[i].addActionListener(e->{
				
				getContentPane().removeAll();
				getContentPane().repaint();
				add(tools[j]);
				tools[j].setVisible(true);
				
			});
			
		}
		
		setVisible(true);
		
	}
	
	public JButton getButton (int index) {
		return buttons[index];
	}
	
}
