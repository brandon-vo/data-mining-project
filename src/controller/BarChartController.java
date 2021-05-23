package controller;

import view.CommuteVsShelterCostGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarChartController implements ActionListener {
    
    CommuteVsShelterCostGUI barChartGui;
    
    public BarChartController(CommuteVsShelterCostGUI barChartGui){
        this.barChartGui = barChartGui;
        setUpListeners();
    }
    
    public void setUpListeners(){
        
        barChartGui.markhamButton.addActionListener(this);
        barChartGui.auroraButton.addActionListener(this);
        barChartGui.newMarketButton.addActionListener(this);
        barChartGui.vaughnButton.addActionListener(this);
        barChartGui.richmondButton.addActionListener(this);
    
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
    
    }
    
}
