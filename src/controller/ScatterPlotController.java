package controller;

import view.HousingTrendGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScatterPlotController implements ActionListener {
    
    private HousingTrendGUI gui;
    
    public ScatterPlotController(HousingTrendGUI gui){
        this.gui = gui;
        setUpListeners();
    }

    public void setUpListeners () {
        gui.getLocation1().addActionListener(this);
        gui.getVariable().addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
