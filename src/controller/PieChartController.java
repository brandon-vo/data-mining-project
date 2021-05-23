package controller;

import model.MyDataset;
import view.PieChartGUI;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PieChartController extends ToolController implements ActionListener {

    private final PieChartGUI gui;
    private String[] cityList = {"Aurora", "East Gwillimbury", "Georgina", "King", "Markham", "Newmarket", "Richmond Hill","Stouffville", "Vaughan", };

    public PieChartController (PieChartGUI gui) {

        this.gui = gui;
        setUpListeners();
    }

    //used when a user wants to select a specific city to view
    public void selectCity () {

        //Displays a pop up with a list of cities to choose from
        String city = (String) JOptionPane.showInputDialog(null, "Which city do you want to see?", "City", JOptionPane.PLAIN_MESSAGE, null, cityList, cityList[0]);

        //switches the data displayed based on what city a user chooses
        switch(city){

            case"Aurora":
               // gui.getPieCharts(0).createChart(gui., "Aurora" );

                break;
            case"East Gwillimbury":

                break;
            case"Georgina":

                break;
            case"King":

                break;
            case"Markham":

                break;
            case"Newmarket":

                break;
            case"Richmond Hill":

                break;
            case"Stouffville":

                break;
            case"Vaughan":

                break;
        }

    }

    // same as select city but for a different dataset
    public void selectCity2() {

        String city = (String) JOptionPane.showInputDialog(null, "Which city do you want to see?", "City", JOptionPane.PLAIN_MESSAGE, null, cityList, cityList[0]);

        switch(city){

            case"Aurora":
                gui.getPieCharts(1);

                break;
            case"East Gwillimbury":

                break;
            case"Georgina":

                break;
            case"King":

                break;
            case"Markham":

                break;
            case"Newmarket":

                break;
            case"Richmond Hill":

                break;
            case"Stouffville":

                break;
            case"Vaughan":

                break;
        }

    }

    //


    @Override
    public void initializeDataToDisplay (MyDataset[] dataset) {
        gui.getPieCharts(0).initializeDataToDisplay(dataset[0], gui.getValidGroupNames(0).get(0));
        gui.getPieCharts(1).initializeDataToDisplay(dataset[1], gui.getValidGroupNames(1).get(0));
    }

    public void enableComponents(boolean enabled){
        gui.getPieCharts(0).getCityButton().setEnabled(enabled);
        gui.getPieCharts(1).getCityButton().setEnabled(enabled);


    }

    public void setUpListeners(){

        gui.getPieCharts(0).getCityButton().addActionListener(this);
        gui.getPieCharts(1).getCityButton().addActionListener(this);
    }

    @Override
    public void actionPerformed (ActionEvent e) {

        if(e.getSource()==gui.getPieCharts(0).getCityButton())
            selectCity();

        if(e.getSource()==gui.getPieCharts(1).getCityButton())
            selectCity2();
    }



}
