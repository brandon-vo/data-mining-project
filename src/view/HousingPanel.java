package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HousingPanel extends JPanel {

	private JLabel citiesLabel = new JLabel("CITIES");
	private JButton markhamButton = new JButton("MARKHAM");
	private JButton vaughanButton = new JButton("VAUGHAN");
	private JButton richmondHillButton = new JButton("RICHMOND HILL");
	
	public HousingPanel() {
	
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		citiesLabel.setBounds(105, 10, 200, 40);
		add(citiesLabel);
		
		markhamButton.setBounds(20, 70, 200, 40);
		add(markhamButton);
		
		vaughanButton.setBounds(20, 130, 200, 40);
		add(vaughanButton);
		
		richmondHillButton.setBounds(20, 190, 200, 40);
		add(richmondHillButton);
		
		setVisible(true);
		
	}

	public JLabel getCitiesLabel() {
		return citiesLabel;
	}

	public void setCitiesLabel(JLabel citiesLabel) {
		this.citiesLabel = citiesLabel;
	}

	public JButton getMarkhamButton() {
		return markhamButton;
	}

	public void setMarkhamButton(JButton markhamButton) {
		this.markhamButton = markhamButton;
	}

	public JButton getVaughanButton() {
		return vaughanButton;
	}

	public void setVaughanButton(JButton vaughanButton) {
		this.vaughanButton = vaughanButton;
	}

	public JButton getRichmondHillButton() {
		return richmondHillButton;
	}

	public void setRichmondHillButton(JButton richmondHillButton) {
		this.richmondHillButton = richmondHillButton;
	}
	
}
