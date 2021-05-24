package controller;

import model.MyDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import util.Category;
import view.LineChartGUI;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import static view.LineChartGUI.*;

public class LineChartController extends ToolController
        implements ActionListener, MouseListener {
    
    public static final int MAX_CITIES = 5;
    
    private final LineChartGUI gui;
    private final HashSet<String> displayedCities;
    
    public LineChartController (LineChartGUI gui) {
        this.gui = gui;
        displayedCities = new HashSet<>();
        setUpListeners();
    }
    
    public void setUpListeners () {
        
        gui.getSelectDataGroupBox().addActionListener(this);
        gui.getChartBoundBox(0).addActionListener(this);
        gui.getChartBoundBox(1).addActionListener(this);
        gui.getSelectVisibleCitiesButton().addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
    
        if (e.getSource()==gui.getSelectDataGroupBox()) {
            updateData();
        } else if (e.getSource()==gui.getChartBoundBox(0)) {
            updateTimeInterval(0);
        } else if (e.getSource()==gui.getChartBoundBox(1)) {
            updateTimeInterval(1);
        } else if (e.getSource()==gui.getSelectVisibleCitiesButton()) {
            updateDisplayedCities();
        }
        gui.repaint();
        
    }
    
    /**
     * Initialize the dataGroup
     * @param dataset = where the data will be taken from
     */
    @Override
    public void initializeDataToDisplay (MyDataset[] dataset) {
        
        String groupName = gui.getValidGroupNames(0).get(0);
        setDataToDisplay(groupName);
        
        // Initialize the dataGroup JComboBox
        ArrayList<String> items = new ArrayList<>();
        items.addAll(gui.getValidGroupNames(0));
        items.addAll(gui.getValidGroupNames(1));
        Collections.swap(items, 0, items.lastIndexOf(groupName));
        
        String[] middleMan = new String[items.size()];
        items.toArray(middleMan);
        gui.getSelectDataGroupBox().setModel(new DefaultComboBoxModel<>(middleMan));
        
    }
    
    /**
     * Change the group of the data being displayed
     * @param groupName = the new group to display
     */
    private void setDataToDisplay (String groupName) {
    
        gui.setDataGroup(groupName);
        
        // Initialize the bounds JComboBoxes
        String[] items = new String[gui.getDataGroup().size()];
        for (int i = 0; i<gui.getDataGroup().size(); ++i) {
            items[i] = gui.getDataGroup().get(i).getCategoryName();
        }
        
        gui.getChartBoundBox(0).setModel(new DefaultComboBoxModel<>(items));
        
        // Reverse the array to display in descending order for the end bound
        for (int i = 0; i<items.length/2; ++i) {
            String temp = items[i];
            items[i] = items[items.length-1-i];
            items[items.length-1-i] = temp;
        }
        gui.getChartBoundBox(1).setModel(new DefaultComboBoxModel<>(items));
        
        // Add some lines for cities to the line chart
        items = MyDataset.getCities();
        displayedCities.clear();
        displayedCities.addAll(Arrays.asList(items).subList(0, MAX_CITIES));
        
        createDisplayedData(0, gui.getDataGroup().size()-1);
        createChart(groupName);
        
    }
    
    private void createDisplayedData (int startBound, int endBound) {
        
        gui.getDisplayedData().clear();
        
        // Create the city lines
        for (int i = startBound; i<=endBound; ++i) {
            
            Category category = gui.getDataGroup().get(i);
            for (String city: displayedCities) {
    
                gui.getDisplayedData().addValue(
                        category.getCities().get(city),
                        city,
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
        if (gui.getChartPanel()!=null) {
            gui.remove(gui.getChartPanel());
        }
        
        // Create and add the chart to the gui
        gui.setChartPanel(new ChartPanel(gui.getLineChart()));
        gui.getChartPanel().setBounds(PADDING, PADDING*4, USER_INPUT_X-PADDING*2, MainFrame.HEIGHT-PADDING*7);
        gui.getChartPanel().setRangeZoomable(false);
        gui.getChartPanel().addMouseListener(this);
        gui.add(gui.getChartPanel());
        
    }
    
    private void updateData () {
        String groupName = (String) gui.getSelectDataGroupBox().getSelectedItem();
        setDataToDisplay(groupName);
    }
    
    /**
     * Update time interval on the graph
     */
    private void updateTimeInterval (int bound) {
        
        int startBound = gui.getChartBoundBox(0).getSelectedIndex();
        int endBound = gui.getChartBoundBox(1).getItemCount()
                -gui.getChartBoundBox(1).getSelectedIndex()-1;
        
        // Alert the user that the range is invalid
        if (startBound>=endBound) {
            JOptionPane.showMessageDialog(
                    gui,
                    "The starting bound is >= than the ending bound\n("
                            +gui.getChartBoundBox(0).getSelectedItem()+" >= "
                            +gui.getChartBoundBox(1).getSelectedItem()
                            +")\nTry again.",
                    "Alert",
                    JOptionPane.ERROR_MESSAGE
            );
            
            // Get either the first or last item from the graph depending
            // on whether the bound is the start or end respectively
            JComboBox<String> currentBound = gui.getChartBoundBox(bound);
            String chartBound = (String) (bound==0
                    ? gui.getDisplayedData().getColumnKey(0)
                    : gui.getDisplayedData().getColumnKey(gui.getDisplayedData().getColumnCount()-1)
            );
            
            for (int i = 0; i<currentBound.getItemCount(); ++i) {
                if (chartBound.equals(currentBound.getItemAt(i))) {
                    currentBound.setSelectedIndex(i);
                    return;
                }
            }
        }
        
        // Create a new chart with the new range
        createDisplayedData(startBound, endBound);
        
    }
    
    /**
     * Display to the user a JOptionPane with checkboxes to
     * select/unselect the displayed cities.
     */
    public void updateDisplayedCities () {
    
        String[] cities = MyDataset.getCities();
        JCheckBox[] checkBoxes = new JCheckBox[cities.length];
        
        // Create checkBoxes
        for (int i = 0; i<checkBoxes.length; ++i) {
            
            checkBoxes[i] = new JCheckBox(cities[i]);
            
            // Set the current box to selected if it is
            // already displayed
            if (displayedCities.contains(cities[i])) {
                checkBoxes[i].setSelected(true);
            }
            
        }
        Object[] message = {
                "Select the cities you want to display.",
                checkBoxes
        };
        
        // Prompt the user until they give valid input
        do {
            
            JOptionPane.showMessageDialog(gui, message, "Prompt", JOptionPane.INFORMATION_MESSAGE);
            
            // Find all the selected cities
            displayedCities.clear();
            for (JCheckBox checkBox : checkBoxes) {
                if (checkBox.isSelected()) {
                    displayedCities.add(checkBox.getText());
                }
            }
            
            // Input is invalid if the number
            // of selected cities exceeds MAX_CITIES
            if (displayedCities.size()>MAX_CITIES) {
                JOptionPane.showMessageDialog(gui,
                        "Too many cities were selected.\n" +
                        "Select at most "+MAX_CITIES,
                        "Alert",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            
        } while (displayedCities.size()>MAX_CITIES);
        
        // Update the chart
        updateTimeInterval(0);
    
    }
    
    /**
     * Display the chart's value at the clicked location
     * @param e = event
     */
    @Override
    public void mouseClicked (MouseEvent e) {
    
    
    
    }
    
    /**
     * Display the difference between 2 separate values by dragging the mouse
     * @param e = event
     */
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
