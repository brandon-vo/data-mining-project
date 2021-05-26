package controller;

import model.MyDataset;
import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import util.Category;
import view.LineChartGUI;
import view.LineChartInfoPanel;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.*;

import static view.LineChartGUI.*;

public class LineChartController
        extends ToolController
        implements ActionListener {
    
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
    
        JOptionPane.showMessageDialog(gui, "1 displayed city allows for the\n"+
                        "comparison of 2 periods of time",
                "Info", JOptionPane.INFORMATION_MESSAGE);
        
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
            for (String city : displayedCities) {
                
                gui.getDisplayedData().addValue(
                        category.getOGCityData(city),
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
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) gui.getLineChart().getCategoryPlot().getRenderer();
        renderer.setAutoPopulateSeriesStroke(false);
        renderer.setDefaultStroke(new BasicStroke(3f));
        
        // Remove any previous chart on the gui
        if (gui.getChartPanel()!=null) {
            gui.remove(gui.getChartPanel());
        }
        
        // Create and add the chart to the gui
        gui.setChartPanel(new ChartPanel(gui.getLineChart()));
        ChartPanel chartPanel = gui.getChartPanel();
        chartPanel.setLayout(null);
        chartPanel.setBounds(PADDING, PADDING*2, USER_INPUT_X-PADDING*2, MainFrame.HEIGHT-PADDING*7/2);
        chartPanel.setRangeZoomable(false);
        chartPanel.addMouseListener(this);
        chartPanel.addMouseMotionListener(this);
    
        chartPanel.add(gui.getInfoPanel());
        
        // Add the circles and lines to the chartPanel
        for (int i = 0; i<MAX_CITIES; ++i) {
            chartPanel.add(gui.getCircle(i));
        }
        for (int i = 0; i<2; ++i) {
            chartPanel.add(gui.getLine(i));
        }
    
        gui.add(chartPanel);
        
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
            
            // Set the JComboBox back to the selected item before
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
                        "Too many cities were selected.\n"+
                                "Select at most "+MAX_CITIES,
                        "Alert",
                        JOptionPane.ERROR_MESSAGE
                );
                // Input invalid if the number
                // of selected cities is 0
            } else if (displayedCities.size()==0) {
                JOptionPane.showMessageDialog(gui,
                        "Too few cities were selected.\n"+
                                "Select at least "+1,
                        "Alert",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            
        } while (displayedCities.size()>MAX_CITIES);
        
        // Update the chart
        updateTimeInterval(0);
        
    }
    
    /**
     * @param mouseX = mouse's x coordinate
     * @return The nearest category given the mouse's x coordinate,
     * or the nearest category if it is out of bounds
     */
    private int getNearestCategory (int mouseX) {
        
        DefaultCategoryDataset displayedData = gui.getDisplayedData();
        
        // Iterate over the categories until mouseX<currentX
        int currentCategory;
        for (currentCategory = 0; currentCategory<displayedData.getColumnCount()
                && getCategoryX(currentCategory)<mouseX; ++currentCategory)
            ;
        
        // return the nearest category if it is out of bounds
        if (currentCategory==0) {
            return 0;
        } else if (currentCategory==displayedData.getColumnCount()) {
            return displayedData.getColumnCount()-1;
        }
        
        // If the distance of previousX and mouseX < distance of currentX and mouseX,
        // or currentCategory>=the number of categories,
        // make the currentCategory the previous category
        if (Math.abs(getCategoryX(currentCategory-1)-mouseX)<Math.abs(getCategoryX(currentCategory)-mouseX)
                || currentCategory>=displayedData.getColumnCount()) {
            --currentCategory;
        }
        
        return currentCategory;
        
    }
    
    /**
     * @param city     = the city to look at
     * @param category = the category to look at
     * @return the coordinates of the city at the category
     */
    private Point2D getCategoryPoint (int city, int category) {
        
        CategoryPlot plot = gui.getLineChart().getCategoryPlot();
        Rectangle2D dataArea = gui.getChartPanel().getChartRenderingInfo().getPlotInfo().getDataArea();
        
        // just for fun, lets convert the axis coordinates back to component coordinates...
        double x = plot.getDomainAxis().getCategoryMiddle(
                category, gui.getDisplayedData().getColumnCount(),
                dataArea, plot.getDomainAxisEdge());
        double y = plot.getRangeAxis().valueToJava2D(
                (double) gui.getDisplayedData().getValue(city, category),
                dataArea, plot.getRangeAxisEdge());
        
        return gui.getChartPanel().translateJava2DToScreen(new Point2D.Double(x, y));
        
    }
    
    private int getCategoryX (int category) {
        return (int) getCategoryPoint(0, category).getX();
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
     * Display the chart's value at the clicked location
     * @param e = event
     */
    @Override
    public void mouseClicked (MouseEvent e) {
        
        // Do not do anything if there is 1 city displayed
        if (displayedCities.size()==1) {
            return;
        }
        
        LineChartInfoPanel info = gui.getInfoPanel();
        
        info.setChosenCategory(0, getNearestCategory(e.getX()));
        
        int x = getCategoryX(info.getChosenCategory(0));
        gui.setLinePosition(0, x);
        
        // For each visible city
        for (int city = 0; city<gui.getDisplayedData().getRowCount(); ++city) {
            
            // Find the coordinate of the cityPoint and put a dot there
            Point2D cityPoint = getCategoryPoint(city, info.getChosenCategory(0));
            gui.setCirclePosition(city, (int) cityPoint.getX(), (int) cityPoint.getY());
            
        }
        
        // Display GUI to the left detailing the time and quantity at that point for each city
        gui.getInfoPanel().generatePanel(gui.getDisplayedData(), x, e.getY());
        
    }
    
    /**
     * Display the difference between 2 separate values by dragging the mouse
     * @param e = event
     */
    @Override
    public void mousePressed (MouseEvent e) {
        
        // Do not do anything if there is not 1 city displayed
        if (displayedCities.size()!=1) {
            return;
        }
        gui.clear();
        
        gui.getInfoPanel().setChosenCategory(0, getNearestCategory(e.getX()));
        gui.setAsSelected(0, getCategoryPoint(0, gui.getInfoPanel().getChosenCategory(0)));
        
    }
    
    /**
     * Display the difference between 2 time periods
     * @param e = event
     */
    @Override
    public void mouseReleased (MouseEvent e) {
        
        if (displayedCities.size()!=1) {
            return;
        }
        
        gui.getInfoPanel().setChosenCategory(1, getNearestCategory(e.getX()));
        gui.setAsSelected(1, getCategoryPoint(0, gui.getInfoPanel().getChosenCategory(1)));
        
        // Calculate the difference in time and period between the start point and endpoint
        int x1 = getCategoryX(gui.getInfoPanel().getChosenCategory(0));
        int x2 = getCategoryX(gui.getInfoPanel().getChosenCategory(1));
        
        gui.getInfoPanel().generatePanel(gui.getDisplayedData(), x1+(x2-x1)/2);
    
    }
    
    /**
     * Draw a line that follows the cursor
     * @param e = event
     */
    @Override
    public void mouseDragged (MouseEvent e) {
        
        // Do not do anything if there is not 1 city displayed
        if (displayedCities.size()!=1) {
            return;
        }
        
        // Draw a line at the user's mouse cursor
        gui.setLinePosition(1, e.getX());
        gui.getChartPanel().repaint();
        
    }
    
    /**
     * Clear the interactor from the graph when the mouse exits
     * @param e = event
     */
    @Override
    public void mouseExited (MouseEvent e) {
        gui.clear();
    }
    
}
