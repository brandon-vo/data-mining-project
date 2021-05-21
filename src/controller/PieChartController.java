package controller;

import view.PieChartGUI;
import view.PieChartUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PieChartController implements ActionListener {
    
    private PieChartGUI gui;
    
    public PieChartController (PieChartGUI gui) {
        
        this.gui = gui;
//        gui.getCityButton().addActionListener(this);
        
    }
    
   public void selectCity () {
        String[] cityList = {"Aurora", "East Gwillimbury", "Georgina", "King", "Markham", "Newmarket", "Richmond Hill","Stouffville", "Vaughan", };
    
        String city = (String) JOptionPane.showInputDialog(null, "Which city do you want to see?", "City", JOptionPane.PLAIN_MESSAGE, null, cityList, cityList[0]);
      
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
//        if (e.getSource()==gui.getCityButton()) ;
//        selectCity();
    }
    
}
