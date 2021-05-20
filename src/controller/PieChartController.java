package controller;

import view.PieChartUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PieChartController implements ActionListener {
    
    private PieChartUtil gui;
    
    public PieChartController (PieChartUtil gui) {
        
        this.gui = gui;
        gui.getCityButton().addActionListener(this);
        
    }
    
//    public String selectCity () {
//
//        return city;
//    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        if (e.getSource()==gui.getCityButton()) ;
//        selectCity();
    }
    
}
