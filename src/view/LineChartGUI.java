package view;

import model.MyDataset;
import util.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LineChartGUI extends Tool implements MouseListener {
    
    private static final String LINE_CHART_TITLE_FILE = "./img/titles/lineChartTitle.png";
    
    private JLabel lineChartTitleLabel;
    private JLabel datasetLabel;
    private JLabel[] boundLabels;
    
    // User input
    private JComboBox<String> selectDatasetBox;
    private JComboBox<String>[] bounds;     // bounds[0] = start bound, bounds[1] = end bound
    
    private JButton selectVisibleCities;
    
    public LineChartGUI () {
    
        lineChartTitleLabel = new JLabel(new ImageIcon(LINE_CHART_TITLE_FILE));
        lineChartTitleLabel.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        add(lineChartTitleLabel);
        
        final Font FONT = new Font("Tahoma", Font.PLAIN, 17);
        final int PADDING = 30;
        final int USER_INPUT_X = MainFrame.WIDTH*3/4;
        final int USER_INPUT_WIDTH = MainFrame.WIDTH/4-PADDING*2;
        final int USER_INPUT_HEIGHT = PADDING*2;
        
        int currentHeight = PADDING*4;
        
        datasetLabel = new JLabel("Select the Dataset");
        datasetLabel.setBounds(USER_INPUT_X, currentHeight, USER_INPUT_WIDTH, USER_INPUT_HEIGHT);
        datasetLabel.setFont(FONT);
        add(datasetLabel);
        
        selectDatasetBox = new JComboBox<>();
        currentHeight += USER_INPUT_HEIGHT;
        selectDatasetBox.setBounds(USER_INPUT_X, currentHeight, USER_INPUT_WIDTH, USER_INPUT_HEIGHT);
        selectDatasetBox.setBorder(null);
        selectDatasetBox.setFont(FONT);
        selectDatasetBox.setBackground(COMBO_BOX_COLOUR);
        add(selectDatasetBox);
        
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
    
        bounds = new JComboBox[2];
        currentHeight += USER_INPUT_HEIGHT;
        for (int i = 0; i<bounds.length; ++i) {
    
            bounds[i] = new JComboBox<>();
            bounds[i].setBounds(USER_INPUT_X+i*USER_INPUT_WIDTH*3/5, currentHeight,
                    USER_INPUT_WIDTH*2/5, USER_INPUT_HEIGHT);
            bounds[i].setFont(FONT);
            bounds[i].setBackground(COMBO_BOX_COLOUR);
            add(bounds[i]);
            
        }
        
        selectVisibleCities = new JButton("Select Visible cities");
        currentHeight += USER_INPUT_HEIGHT+PADDING;
        selectVisibleCities.setBounds(USER_INPUT_X, currentHeight, USER_INPUT_WIDTH, USER_INPUT_HEIGHT);
        selectVisibleCities.setBorder(null);
        selectVisibleCities.setFont(FONT);
        selectVisibleCities.setBackground(BUTTON_COLOUR);
        add(selectVisibleCities);
        
    }
    
    @Override
    public void setDataToDisplay (MyDataset dataset, String groupName) {
        
        setDisplayedData(dataset.getDataset().get(groupName));
    
        // Initialize the dataset JComboBox
        ArrayList<String> items = new ArrayList<>();
        items.add(groupName);
        items.addAll(getValidGroups(0));
        items.addAll(getValidGroups(1));
        items.remove(items.lastIndexOf(groupName));
        
        String[] middleMan = new String[items.size()];
        items.toArray(middleMan);
        selectDatasetBox.setModel(new DefaultComboBoxModel<>(middleMan));
        
        // Initialize the bounds JComboBoxes
        items.clear();
        for (int i = 0; i<getDisplayedData().size(); ++i) {
            items.add(getDisplayedData().get(i).getCategoryName());
        }
        middleMan = new String[items.size()];
        items.toArray(middleMan);
        bounds[0].setModel(new DefaultComboBoxModel<>(middleMan));
        
        // Reverse the array
        for (int i = 0; i<middleMan.length/2; ++i) {
            String temp = middleMan[i];
            middleMan[i] = middleMan[middleMan.length-1-i];
            middleMan[middleMan.length-1-i] = temp;
        }
        items.toArray(middleMan);
        bounds[1].setModel(new DefaultComboBoxModel<>(middleMan));
        
    }
    
    @Override
    public void mouseClicked (MouseEvent e) {
        // TODO
    }
    
    @Override
    public void mousePressed (MouseEvent e) {
        // TODO
    }
    
    @Override
    public void mouseReleased (MouseEvent e) {
        // TODO
    }
    
    @Override
    public void mouseEntered (MouseEvent e) {
        // TODO
    }
    
    @Override
    public void mouseExited (MouseEvent e) {
        // TODO
    }
    
}
