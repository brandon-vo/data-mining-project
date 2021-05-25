package controller;

import model.MyDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import util.Category;
import view.MainFrame;
import view.PieChartGUI;
import view.Tool;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PieChartController extends ToolController implements ActionListener {

    private final PieChartGUI gui;
    private String[] cityList = {"Aurora", "East Gwillimbury", "Georgina", "King", "Markham", "Newmarket", "Richmond Hill","Stouffville", "Vaughan", };
    private ArrayList<Category> dataGroup;
    private DefaultPieDataset<String> displayedData;
    private ChartPanel chartPanel;

    public PieChartController (PieChartGUI gui) {

        this.gui = gui;
        setUpListeners();
    }

    //used when a user wants to select a specific city to view
    public void selectCity () {

        //Displays a pop up with a list of cities to choose from
        String city = (String) JOptionPane.showInputDialog(null, "Which city do you want to see?", "City", JOptionPane.PLAIN_MESSAGE, null, cityList, cityList[0]);

        //switches the data displayed based on what city a user chooses
        gui.getPieCharts(0).createChart(gui.getPieCharts(0).getGroupName(), city);

    }



    // same as select city but for a different dataset
    public void selectCity2() {

        String city = (String) JOptionPane.showInputDialog(null, "Which city do you want to see?", "City", JOptionPane.PLAIN_MESSAGE, null, cityList, cityList[0]);
        gui.getPieCharts(1).createChart(gui.getPieCharts(1).getGroupName() , city);
        
    }


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
