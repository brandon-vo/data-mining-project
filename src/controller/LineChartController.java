package controller;

import model.MyDataset;
import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import util.Category;
import view.LineChartGUI;
import view.LineChartInteractor;
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
    private final JPanel rectangle;
    
    public LineChartController (LineChartGUI gui) {
        this.gui = gui;
        displayedCities = new HashSet<>();
        rectangle = new JPanel();
        rectangle.setBackground(LineChartInteractor.BACKGROUND_COLOUR);
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
            for (String city : displayedCities) {
                
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
        chartPanel.add(rectangle);
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
     * Display the chart's value at the clicked location
     * @param e = event
     */
    @Override
    public void mouseClicked (MouseEvent e) {
        
        int nearestCategory = getNearestCategory(e.getX());
        
        // For each visible city
        for (int city = 0; city<gui.getDisplayedData().getRowCount(); ++city) {
            
            // Find the coordinate of the cityPoint and put a dot there
            Point2D cityPoint = getCategoryPoint(city, nearestCategory);
            gui.getInteractor().setCircle(city, (int) cityPoint.getX(), (int) cityPoint.getY());
            gui.getChartPanel().add(gui.getInteractor().getCircle(city));
            
        }
        
        // Display GUI to the left detailing the time and quantity at that point for each city
        
        
    }
    
    /**
     * Display the difference between 2 separate values by dragging the mouse
     * @param e = event
     */
    @Override
    public void mouseDragged (MouseEvent e) {
        
        System.out.println("oog booga");
        // If the mouse is not over the graph or is not pressed, do not do anything
        // Iterate over the visible cities
        // If a city is visible, add 1 to numCitiesVisible
        // If numCitiesVisible is not 1, do not do anything
        // Get the start display point (using the previous algorithm)
        // Display the point on the screen
        // While the mouse is still pressed
        // Clear any end points previously on the screen
        // Get the end display point
        // Display the point on the screen
        // Calculate the difference in time and period between the start point and endpoint
        // Display this information in the middle of the interval on a GUI
        // Highlight the area under the graph between the start and end points
        // Clear anything drawn on the graph
        
    }
    
    /**
     * Clear everything from the graph when the mouse exists
     * @param e = event
     */
    @Override
    public void mouseExited (MouseEvent e) {
        gui.repaint();
    }
    
    /**
     * @param mouseX = mouse's x coordinate
     * @return The nearest category given the mouse's x coordinate,
     *         or the nearest category if it is out of bounds
     */
    private int getNearestCategory (int mouseX) {
    
        DefaultCategoryDataset displayedData = gui.getDisplayedData();
        
        // Iterate over the categories until mouseX<currentX
        int currentCategory;
        for (currentCategory = 0; currentCategory<displayedData.getColumnCount()
                && getCategoryX(currentCategory)<mouseX; ++currentCategory);
        
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
    
}
