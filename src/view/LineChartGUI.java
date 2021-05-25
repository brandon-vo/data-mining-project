package view;

import controller.LineChartController;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class LineChartGUI extends Tool {
    
    private static final String LINE_CHART_TITLE_FILE = "./img/titles/lineChartTitle.png";
    private static final String CIRCLE_FILE = "./img/circle.png";
    public static final ImageIcon CIRCLE = new ImageIcon(CIRCLE_FILE);
    public static final Rectangle NULL_BOUNDS = new Rectangle(0, 0, 0, 0);
    
    private final JLabel lineChartTitleLabel;
    private final JLabel datasetLabel;
    private final JLabel[] boundLabels;
    
    private final LineChartInfoPanel infoPanel;
    
    // User input
    private final JComboBox<String> selectDataGroupBox;
    private final JComboBox<String>[] chartBoundsBoxes;     // bounds[0] = start bound, bounds[1] = end bound
    private final JButton selectVisibleCitiesButton;
    
    // JFreeChart
    private final DefaultCategoryDataset displayedData;
    private JFreeChart lineChart;
    private ChartPanel chartPanel;
    
    private final JLabel[] circles;
    private final JPanel[] lines;
    
    public LineChartGUI () {
    
        lineChartTitleLabel = new JLabel(new ImageIcon(LINE_CHART_TITLE_FILE));
        lineChartTitleLabel.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        add(lineChartTitleLabel);
        
        int currentHeight = PADDING*2;
        
        datasetLabel = new JLabel("Select the Dataset");
        datasetLabel.setBounds(USER_INPUT_X, currentHeight, USER_INPUT_WIDTH, USER_INPUT_HEIGHT);
        datasetLabel.setFont(FONT);
        add(datasetLabel);
        
        selectDataGroupBox = new JComboBox<>();
        currentHeight += USER_INPUT_HEIGHT;
        selectDataGroupBox.setBounds(USER_INPUT_X, currentHeight, USER_INPUT_WIDTH, USER_INPUT_HEIGHT);
        selectDataGroupBox.setBorder(null);
        selectDataGroupBox.setFont(FONT);
        selectDataGroupBox.setBackground(COMBO_BOX_COLOUR);
        add(selectDataGroupBox);
        
        boundLabels = new JLabel[2];
        currentHeight += USER_INPUT_HEIGHT;
        for (int i = 0; i<boundLabels.length; ++i) {
    
            boundLabels[i] = new JLabel();
            boundLabels[i].setBounds(USER_INPUT_X+i*USER_INPUT_WIDTH*3/5, currentHeight,
                    USER_INPUT_WIDTH*2/5, USER_INPUT_HEIGHT);
            boundLabels[i].setFont(FONT);
            boundLabels[i].setBackground(COMBO_BOX_COLOUR);
            add(boundLabels[i]);
        
        }
        boundLabels[0].setText("Start");
        boundLabels[1].setText("End");
        
        infoPanel = new LineChartInfoPanel();
    
        chartBoundsBoxes = new JComboBox[2];
        currentHeight += USER_INPUT_HEIGHT;
        for (int i = 0; i<chartBoundsBoxes.length; ++i) {
    
            chartBoundsBoxes[i] = new JComboBox<>();
            chartBoundsBoxes[i].setBounds(USER_INPUT_X+USER_INPUT_WIDTH*3/5*i, currentHeight,
                    USER_INPUT_WIDTH*2/5, USER_INPUT_HEIGHT);
            chartBoundsBoxes[i].setFont(new Font("Tahoma", Font.PLAIN, 12));
            chartBoundsBoxes[i].setBackground(COMBO_BOX_COLOUR);
            add(chartBoundsBoxes[i]);
            
        }
        
        selectVisibleCitiesButton = new JButton("Select Visible cities");
        currentHeight += USER_INPUT_HEIGHT+PADDING/2;
        selectVisibleCitiesButton.setBounds(USER_INPUT_X, currentHeight, USER_INPUT_WIDTH, USER_INPUT_HEIGHT);
        selectVisibleCitiesButton.setBorder(null);
        selectVisibleCitiesButton.setFont(FONT);
        selectVisibleCitiesButton.setBackground(BUTTON_COLOUR);
        add(selectVisibleCitiesButton);
        
        displayedData = new DefaultCategoryDataset();
    
        circles = new JLabel[LineChartController.MAX_CITIES];
        for (int i = 0; i<circles.length; ++i) {
            circles[i] = new JLabel(CIRCLE);
        }
    
        lines = new JPanel[2];
        for (int i = 0; i<lines.length; ++i) {
            lines[i] = new JPanel();
            lines[i].setBackground(BACKGROUND_COLOUR);
        }
        
    }
    
    public LineChartInfoPanel getInfoPanel () {
        return infoPanel;
    }
    
    public JComboBox<String> getSelectDataGroupBox () {
        return selectDataGroupBox;
    }
    
    public JComboBox<String> getChartBoundBox (int index) {
        return chartBoundsBoxes[index];
    }
    
    public JButton getSelectVisibleCitiesButton () {
        return selectVisibleCitiesButton;
    }
    
    public DefaultCategoryDataset getDisplayedData () {
        return displayedData;
    }
    
    public JFreeChart getLineChart () {
        return lineChart;
    }
    
    public ChartPanel getChartPanel () {
        return chartPanel;
    }
    
    public void setLineChart (JFreeChart lineChart) {
        this.lineChart = lineChart;
    }
    
    public void setChartPanel (ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }
    
    public JPanel getLine (int i) {
        return lines[i];
    }
    
    public void setCirclePosition (int i, int x, int y) {
        circles[i].setBounds(x-CIRCLE.getIconWidth()/2, y-CIRCLE.getIconHeight()/2, CIRCLE.getIconWidth(), CIRCLE.getIconHeight());
    }
    
    public JLabel getCircle (int i) {
        return circles[i];
    }
    
    public void setLinePosition (int i, int x) {
        lines[i].setBounds(Math.max(x-4/2, 63), 35, 4, 447);
    }
    
    public void setAsSelected (int i, Point2D point) {
        setLinePosition(i, (int) point.getX());
        setCirclePosition(i, (int) point.getX(), (int) point.getY());
    }
    
    public void clear () {
        
        infoPanel.clear();
        for (JLabel circle : circles) {
            circle.setBounds(NULL_BOUNDS);
        }
        for (JPanel line : lines) {
            line.setBounds(NULL_BOUNDS);
        }
        
    }
    
}
