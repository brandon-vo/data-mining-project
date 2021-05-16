package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TransportationPanel extends JPanel {
	
	private JLabel citiesLabel = new JLabel("CITIES");
	private JButton markhamButton = new JButton("MARKHAM");
	private JButton vaughanButton = new JButton("VAUGHAN");
	private JButton richmondHillButton = new JButton("RICHMOND HILL");
	
	public TransportationPanel() {
		
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(Color.black));
		

		
		citiesLabel.setBounds(10, 20, 200, 40);
		add(citiesLabel);
		
		markhamButton.setBounds(10, 80, 200, 40);
		add(markhamButton);
		
		vaughanButton.setBounds(10, 140, 200, 40);
		add(vaughanButton);
		
		richmondHillButton.setBounds(10, 200, 200, 40);
		add(richmondHillButton);
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
