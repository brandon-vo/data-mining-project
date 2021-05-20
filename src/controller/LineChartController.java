package controller;

import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetChangeListener;
import view.LineChartGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LineChartController implements ActionListener, MouseListener, DatasetChangeListener {
    
    private LineChartGUI gui;
    
    public LineChartController (LineChartGUI gui) {
    
        this.gui = gui;
        setUpListeners();
    
    }
    
    public void setUpListeners () {
        
        gui.getSelectDatasetBox().addActionListener(this);
        gui.getChartBounds(0).addActionListener(this);
        gui.getChartBounds(1).addActionListener(this);
        gui.getSelectVisibleCities().addActionListener(this);
        gui.getDisplayedData().addChangeListener(this);
        
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
    
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
    
    @Override
    public void mouseEntered (MouseEvent e) {
    
    }
    
    @Override
    public void mouseExited (MouseEvent e) {
    
    }
    
    @Override
    public void datasetChanged (DatasetChangeEvent datasetChangeEvent) {
    
    }
    
}
