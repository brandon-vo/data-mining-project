package controller;

import view.HousingTrendGUI;
import view.Tool;


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
        String location = (String)gui.getLocation1().getSelectedItem();
        
        switch(location){
            case "Vaughan":

                break;
            case"Richmond Hill":
                break;
            case"Markham":
                break;
            case"East Gwillimbury":
                break;
            case"Newmarket":
                break;
            case"Georgina":
                break;
            case"King":
                break;
        }
        String variable = (String)gui.getVariable().getSelectedItem();
        switch(variable){
            case"room":
                break;
            case"bed":
                break;
            case"maintainer":
                break;
        }
    }
    
}
