package controller;

import model.MyDataset;
import view.DensityMapGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static model.DataType.DENSITY_MAP;

public class DensityMapController extends ToolController implements ActionListener, MouseListener {
    
    private final DensityMapGUI densityGUI; // Access DensityMapGUI
    
    // Constructor
    public DensityMapController (DensityMapGUI densityGUI) {
        this.densityGUI = densityGUI; // Density GUI
        setUpListeners(); // Setup Listeners
        densityGUI.changeMapColour(); // Initial map colour set to grey
    }
    
    // Setup listeners
    public void setUpListeners () {
        densityGUI.getSubmitButton().addActionListener(this);
        densityGUI.getDataList().addActionListener(this);
        for (int index = 0; index<densityGUI.getCityLabels().length; index++)
            densityGUI.getCityLabels()[index].addMouseListener(this);
    }
    
    // Enable components
    public void enableComponents (boolean enable) {
        densityGUI.getCityList().setEnabled(enable);
        densityGUI.getSubmitButton().setEnabled(enable);
        densityGUI.getUserResults().setEnabled(enable);
        densityGUI.getQuestionList().setEnabled(enable);
    }
    
    // Get user selected dataset
    public void getSelectedData () {
        if (densityGUI.getDataList().getSelectedIndex()==0) {
            enableComponents(false);
            updateSelectedData();
            updateQuestionBox(0);
            densityGUI.getUserResults().setText("Please select a dataset!");
            return;
        }
        enableComponents(true);
        densityGUI.setDataGroup((String) densityGUI.getDataList().getSelectedItem());
        updateSelectedData();
        updateQuestionBox(densityGUI.getDataList().getSelectedIndex());
    }
    
    // Update map colour and user results text
    public void updateSelectedData () {
        densityGUI.getUserResults().setText("Please input your information!");
        densityGUI.changeMapColour();
        
    }
    
    // Set question combo box
    public void updateQuestionBox (int index) {
        // Store list of options for the question
        List<String> questions = new ArrayList<>();
        
        if (index==1) {
            String[] optionList = new String[] {
                    "- Select value of your dwelling -",
                    "0-500,000", "500,000-1,000,000", "1,000,000-1,500,000", "1,500,000-2,000,000",
                    "2,000,000-2,500,000", "2,500,000-3,000,000", "3,000,000-3,500,000" };
            questions.addAll(Arrays.asList(optionList));
        } else if (index==2) {
            String[] optionList = new String[] {
                    "- Select average monthly shelter cost -",
                    "0-500", "500-1,000", "1,000-1,500", "1,500-2,000",
                    "2,000-2,500", "2,500-3,000", "3,000-3,500", "3,500-4,000" };
            questions.addAll(Arrays.asList(optionList));
        } else if (index==3) {
            String[] optionList = new String[] {
                    "- Select average monthly shelter cost -",
                    "0-500", "500-1,000", "1,000-1,500",
                    "1,500-2,000", "2,000-2,500", "2,500-3,000" };
            questions.addAll(Arrays.asList(optionList));
        } else if (index==4) {
            String[] optionList = new String[] {
                    "- Are you a household owner? -",
                    "Yes", "No" };
            questions.addAll(Arrays.asList(optionList));
        } else if (index==5) {
            String[] optionList = new String[] {
                    "- Are you a household renter? -",
                    "Yes", "No" };
            questions.addAll(Arrays.asList(optionList));
        }
        
        // Set question list based on selected index
        densityGUI.getQuestionList().setModel(new DefaultComboBoxModel<>(questions.toArray(new String[0])));
        
    }
    
    @Override
    public void initializeDataToDisplay (MyDataset[] dataset) {
        
        String profileOfHousing = densityGUI.getValidGroupNames(1).get(0);
        densityGUI.setDataGroup(profileOfHousing);
        
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        // User clicked submit button
        if (e.getSource()==densityGUI.getSubmitButton()) {
            
            // User did not fill out all questions
            if (densityGUI.getCityList().getSelectedIndex()==0 || densityGUI.getQuestionList().getSelectedIndex()==0) {
                JOptionPane.showMessageDialog(null, "Please answer all questions!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(null, "Your results have been submtited!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            // TODO compare user results to data
            densityGUI.getUserResults().setText("TODO results");
        } else if (e.getSource()==densityGUI.getDataList()) {
            getSelectedData();
        }
        
    }
    
    @Override
    public void mouseClicked (MouseEvent e) {
        
        // Check all city labels
        for (int index = 0; index<densityGUI.getCityLabels().length; index++) {
            
            // User clicked a city label
            if (e.getSource()==densityGUI.getCityLabels()[index]) {
                
                int selectedCategory = densityGUI.getDataList().getSelectedIndex(); // Access selected data
                
                // Users selected category
                if (selectedCategory!=0) {
                    
                    int chosen = 0; // For accessing owners and renters
                    String suffix = ""; // Text format for owners and renters
                    
                    // Get selected category and display data
                    if (selectedCategory==1 || selectedCategory==2 || selectedCategory==3) {
                        JOptionPane.showMessageDialog(null,
                                densityGUI.getDataOptions()[selectedCategory]+" : \n"+
                                        "$"+Math.round(densityGUI.getDataGroup().get(0)
                                        .getCities().get(densityGUI.getCityName(index))),
                                densityGUI.getMapNames()[index].toUpperCase()+" DATA",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else if (selectedCategory==4 || selectedCategory==5) {
                        if (selectedCategory==4) {
                            chosen = 0;
                            suffix = " owners";
                        } // Household Owners
                        else if (selectedCategory==5) {
                            chosen = 1;
                            suffix = " renters";
                        } // Household Renters
                        JOptionPane.showMessageDialog(null,
                                densityGUI.getDataOptions()[selectedCategory]+" : \n"+
                                        Math.round(densityGUI.getDataGroup().get(chosen).
                                                getOGCityData((densityGUI.getCityName(index))))+suffix,
                                densityGUI.getMapNames()[index].toUpperCase()+" DATA",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    return;
                } else { // User did not select a dataset
                    JOptionPane.showMessageDialog(null,
                            "You have not selected a dataset!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        }
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
    
}
