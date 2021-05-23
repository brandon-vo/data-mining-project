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
        
        gui.getSelectDataGroupBox().addActionListener(this);
        gui.getChartBounds(0).addActionListener(this);
        gui.getChartBounds(1).addActionListener(this);
        gui.getSelectVisibleCities().addActionListener(this);
        gui.getDisplayedData().addChangeListener(this);
        // TODO add the action listener to chart panel everytime it's created
        
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
    
    public void updateData () {
        String groupName = (String) gui.getSelectDataGroupBox().getSelectedItem();
        gui.setDataToDisplay(groupName);
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
