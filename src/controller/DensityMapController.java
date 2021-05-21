package controller;

import view.DensityMapGUI;
import view.Tool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.RGBImageFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DensityMapController implements ActionListener, MouseListener {

    private DensityMapGUI densityGUI;

    public DensityMapController(DensityMapGUI densityGUI) {

        this.densityGUI = densityGUI;
        setUpListeners();

        for (int cityIndex = 0; cityIndex < 9; cityIndex++) {
            (densityGUI).changeMapColour(cityIndex);
        }
    }

    // Setup listeners
    public void setUpListeners() {
        densityGUI.getSubmitButton().addActionListener(this);
        densityGUI.getDataList().addActionListener(this);
        densityGUI.getAuroraLabel().addMouseListener(this);
        densityGUI.getEastGwillimburyLabel().addMouseListener(this);
        densityGUI.getGeorginaLabel().addMouseListener(this);
        densityGUI.getKingLabel().addMouseListener(this);
        densityGUI.getMarkhamLabel().addMouseListener(this);
        densityGUI.getNewmarketLabel().addMouseListener(this);
        densityGUI.getRichmondHillLabel().addMouseListener(this);
        densityGUI.getStouffvilleLabel().addMouseListener(this);
        densityGUI.getVaughanLabel().addMouseListener(this);
    }

    // Enable components
    public void enableComponents(boolean enable) {
        densityGUI.getCityList().setEnabled(enable);
        densityGUI.getSubmitButton().setEnabled(enable);
        densityGUI.getUserResults().setEnabled(enable);
        densityGUI.getQuestionList().setEnabled(enable);
    }

    // Get user selected dataset
    public void getSelectedData() {

        if (densityGUI.getDataList().getSelectedIndex() == 0) {
            System.out.println("set the map to full grey");
            enableComponents(false);
            updateSelectedData(0);
            updateQuestionBox(0);
            densityGUI.getUserResults().setText("Please select a dataset!");
        }
        for (int i = 1; i<=5; ++i) {
            if (densityGUI.getDataList().getSelectedIndex()==i) {
                enableComponents(true);
                updateSelectedData(i);
                updateQuestionBox(i);
                return;
            }
        }
        
    }

    public void updateSelectedData(int index) {
        densityGUI.getUserResults().setText("Please input your information!");
        for (int city = 0; city < densityGUI.getMaps().length; city++) {
            if (index == 0) {
                // index 0 = clear
                densityGUI.changeMapColour(city);
            } else if (index == 1) {
                // index 1 = average dwelling
                densityGUI.changeMapColour(city);
            } else if (index == 2) {
                // index 2 = average monthly cost owners
                densityGUI.changeMapColour(city);
            } else if (index == 3) {
                // index 3 = average monthly cost renters
                densityGUI.changeMapColour(city);
            } else if (index == 4) {
                // index 4 = household owners
                densityGUI.changeMapColour(city);
            } else if (index == 5) {
                // index 5 = household renters
                densityGUI.changeMapColour(city);
            }
        }
    }

    // Set question combo box
    public void updateQuestionBox(int index) {
        // Store list of options for the question
        List<String> questions = new ArrayList<>();

        if (index == 1) {
            String[] list = new String[]{"- Select value of your dwelling -",
                    "0-500,000", "500,000-1,000,000", "1,000,000-1,500,000", "1,500,000-2,000,000",
                    "2,000,000-2,500,000", "2,500,000-3,000,000", "3,000,000-3,500,000"};
            questions.addAll(Arrays.asList(list));
        } else if (index == 2) {
            String[] list = new String[]{"- Select average monthly shelter cost -",
                    "0-500", "500-1,000", "1,000-1,500", "1,500-2,000", "2,000-2,500",
                    "2,500-3,000", "3,000-3,500", "3,500-4,000"};
            questions.addAll(Arrays.asList(list));
        } else if (index == 3) {
            String[] list = new String[]{"- Select average monthly shelter cost -",
                    "0-500", "500-1,000", "1,000-1,500", "1,500-2,000", "2,000-2,500",
                    "2,500-3,000"};
            questions.addAll(Arrays.asList(list));
        } else if (index == 4) {
            String[] list = new String[]{"- Are you a household owner? -",
                    "Yes", "No"};
            questions.addAll(Arrays.asList(list));
        } else if (index == 5) {
            String[] list = new String[]{"- Are you a household renter? -",
                    "Yes", "No"};
            questions.addAll(Arrays.asList(list));
        }

        // Set question list based on selected index
        densityGUI.getQuestionList().setModel(new DefaultComboBoxModel<>(questions.toArray(new String[0])));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        if (e.getSource() == densityGUI.getSubmitButton()) {
        
            // User did not fill out all questions
            if (densityGUI.getCityList().getSelectedIndex() == 0 || densityGUI.getQuestionList().getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Please answer all questions!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(null, "not done", "not done", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == densityGUI.getDataList()) {
            getSelectedData();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == densityGUI.getAuroraLabel()) {
            JOptionPane.showMessageDialog(null, "Aurora", "Aurora", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == densityGUI.getEastGwillimburyLabel()) {
            JOptionPane.showMessageDialog(null, "east", "east", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == densityGUI.getGeorginaLabel()) {
            JOptionPane.showMessageDialog(null, "g", "g", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == densityGUI.getKingLabel()) {
            JOptionPane.showMessageDialog(null, "k", "k", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == densityGUI.getMarkhamLabel()) {
            JOptionPane.showMessageDialog(null, "m", "m", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == densityGUI.getNewmarketLabel()) {
            JOptionPane.showMessageDialog(null, "n", "n", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == densityGUI.getRichmondHillLabel()) {
            JOptionPane.showMessageDialog(null, "r", "r", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == densityGUI.getStouffvilleLabel()) {
            JOptionPane.showMessageDialog(null, "st", "st", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == densityGUI.getVaughanLabel()) {
            JOptionPane.showMessageDialog(null, "Vaughan", "Vaughan", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public DensityMapGUI getGui() {
        return densityGUI;
    }

    public void setGui(DensityMapGUI densityGUI) {
        this.densityGUI = densityGUI;
    }
}

