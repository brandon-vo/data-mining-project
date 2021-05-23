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
//        String location = (String)gui.getLocation1().getSelectedItem();
//        gui.createDisplayedData(location);
//        gui.repaint();
        
//        switch(location){
//            case "Vaughan":
//                cityIndex=1;
//                break;
//            case"Richmond Hill":
//                cityIndex=2;
//                break;
//            case"Markham":
//                cityIndex=3;
//                break;
//            case"East Gwillimbury":
//                cityIndex=4;
//                break;
//            case"Newmarket":
//                cityIndex=5;
//                break;
//            case"Georgina":
//                cityIndex=6;
//                break;
//            case"King":
//                cityIndex=7;
//                break;
//        }
       
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
