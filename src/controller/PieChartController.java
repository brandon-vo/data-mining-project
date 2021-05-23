package controller;

import model.MyDataset;
import view.PieChartGUI;
import view.PieChartUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PieChartController extends ToolController implements ActionListener {
    
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
    public void initializeDataToDisplay (MyDataset[] dataset) {
        gui.getPieCharts(0).initializeDataToDisplay(dataset[0], gui.getValidGroupNames(0).get(0));
        gui.getPieCharts(1).initializeDataToDisplay(dataset[1], gui.getValidGroupNames(1).get(0));
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
//        if (e.getSource()==gui.getCityButton()) ;
//        selectCity();
    }
    
}
