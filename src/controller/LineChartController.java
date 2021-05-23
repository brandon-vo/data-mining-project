package controller;

import model.MyDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetChangeListener;
import util.Category;
import view.LineChartGUI;
import view.MainFrame;
import view.Tool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import static view.LineChartGUI.*;

public class LineChartController extends ToolController implements ActionListener, MouseListener, DatasetChangeListener {
    
    private final LineChartGUI gui;
    
    public LineChartController (LineChartGUI gui) {
        this.gui = gui;
        setUpListeners();
    }
    
    public void setUpListeners () {
        
        gui.getSelectDataGroupBox().addActionListener(this);
        gui.getChartBounds(0).addActionListener(this);
        gui.getChartBounds(1).addActionListener(this);
        gui.getSelectVisibleCities().addActionListener(this);
        gui.getDisplayedData().addChangeListener(this);
        
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
    
        if (e.getSource()==gui.getSelectDataGroupBox()) {
            updateData();
        } else if (e.getSource()==gui.getChartBounds(0)) {
        
        } else if (e.getSource()==gui.getChartBounds(1)) {
        
        } else if (e.getSource()==gui.getSelectVisibleCities()) {
        
        }
        
    }
    
    @Override
    public void initializeDataToDisplay (MyDataset[] dataset) {
        
        String groupName = gui.getValidGroupNames(0).get(0);
        setDataToDisplay(groupName);
        
        // Initialize the dataGroup JComboBox
        ArrayList<String> items = new ArrayList<>();
        items.add(groupName);
        items.addAll(gui.getValidGroupNames(0));
        items.addAll(gui.getValidGroupNames(1));
        items.remove(items.lastIndexOf(groupName));
        
        String[] middleMan = new String[items.size()];
        items.toArray(middleMan);
        gui.getSelectDataGroupBox().setModel(new DefaultComboBoxModel<>(middleMan));
        
    }
    
    private void setDataToDisplay (String groupName) {
    
        gui.setDataGroup(groupName);
        
        // Initialize the bounds JComboBoxes
        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i<gui.getDataGroup().size(); ++i) {
            items.add(gui.getDataGroup().get(i).getCategoryName());
        }
        String[] middleMan = new String[items.size()];
        items.toArray(middleMan);
        gui.getChartBounds(0).setModel(new DefaultComboBoxModel<>(middleMan));
        
        // Reverse the array to display in descending order for the end bound
        for (int i = 0; i<middleMan.length/2; ++i) {
            String temp = middleMan[i];
            middleMan[i] = middleMan[middleMan.length-1-i];
            middleMan[middleMan.length-1-i] = temp;
        }
        items.toArray(middleMan);
        gui.getChartBounds(1).setModel(new DefaultComboBoxModel<>(middleMan));
        
        // Add some lines for cities to the line chart
        items.clear();
        middleMan = MyDataset.getCities();
        items.addAll(Arrays.asList(middleMan).subList(0, MAX_CITIES));
        createDisplayedData(items);
        
        createChart(groupName);
        
    }
    
    private void createDisplayedData (ArrayList<String> displayableCities) {
        
        gui.getDisplayedData().clear();
        
        // Create the city lines
        for (Category category: gui.getDataGroup()) {
            for (String city: displayableCities) {
                gui.getDisplayedData().addValue(
                        category.getCities().get(city), city,
                        category.getCategoryName()
                );
            }
        }
        
    }
    
    private void createChart (String groupName) {
        
        String chartTitle = "Number of People vs "+groupName;
        String valueAxisLabel = "Number of People";
        
        gui.setLineChart(ChartFactory.createLineChart(
                chartTitle, groupName, valueAxisLabel,
                gui.getDisplayedData(), PlotOrientation.VERTICAL,
                true, false, false
        ));
        gui.getLineChart().setBackgroundPaint(BACKGROUND_COLOUR);
    
        // Remove any previous chart on the gui
        gui.remove(gui.getChartPanel());
        
        gui.setChartPanel(new ChartPanel(gui.getLineChart()));
        gui.getChartPanel().setBounds(PADDING, PADDING*4, USER_INPUT_X-PADDING*2, MainFrame.HEIGHT-PADDING*7);
        gui.getChartPanel().setRangeZoomable(false);
        gui.getChartPanel().addMouseListener(this);
        gui.add(gui.getChartPanel());
        
    }
    
    private void updateData () {
        String groupName = (String) gui.getSelectDataGroupBox().getSelectedItem();
        setDataToDisplay(groupName);
        gui.repaint();
    }
    
    @Override
    public void datasetChanged (DatasetChangeEvent datasetChangeEvent) {
        
        if (datasetChangeEvent.getSource()==gui.getDisplayedData()) {
        
        }
        
    }
    
    @Override
    public void mouseClicked (MouseEvent e) {
    
    }
    
    @Override
    public void mousePressed (MouseEvent e) {
    
    }
    
    @Override
    public void mouseReleased (MouseEvent e) {
    
    }
    
    // Unneeded methods
    @Override
    public void mouseEntered (MouseEvent e) {
    }
    
    @Override
    public void mouseExited (MouseEvent e) {
    }
    
}
