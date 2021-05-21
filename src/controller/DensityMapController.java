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
import java.util.List;

public class DensityMapController implements ActionListener, MouseListener {

    public static DensityMapGUI densityGUI;

    DensityMapController(Tool densityGUI) {

        this.densityGUI = (DensityMapGUI) densityGUI;
        setUpListeners();

        for (int cityIndex = 0; cityIndex < 9; cityIndex++) {
            ((DensityMapGUI) densityGUI).changeMapColour(cityIndex);
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
        } else if (densityGUI.getDataList().getSelectedIndex() == 1) {
            enableComponents(true);
            updateSelectedData(1);
            updateQuestionBox(1);
        } else if (densityGUI.getDataList().getSelectedIndex() == 2) {
            enableComponents(true);
            updateSelectedData(2);
            updateQuestionBox(2);
        } else if (densityGUI.getDataList().getSelectedIndex() == 3) {
            enableComponents(true);
            updateSelectedData(3);
            updateQuestionBox(3);
        } else if (densityGUI.getDataList().getSelectedIndex() == 4) {
            enableComponents(true);
            updateSelectedData(4);
            updateQuestionBox(4);
        } else if (densityGUI.getDataList().getSelectedIndex() == 5) {
            enableComponents(true);
            updateSelectedData(5);
            updateQuestionBox(5);
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
            for (int x = 0; x < list.length; x++)
                questions.add(list[x]);
        } else if (index == 2) {
            String[] list = new String[]{"- Select average monthly shelter cost -",
                    "0-500", "500-1,000", "1,000-1,500", "1,500-2,000", "2,000-2,500",
                    "2,500-3,000", "3,000-3,500", "3,500-4,000"};
            for (int x = 0; x < list.length; x++)
                questions.add(list[x]);
        } else if (index == 3) {
            String[] list = new String[]{"- Select average monthly shelter cost -",
                    "0-500", "500-1,000", "1,000-1,500", "1,500-2,000", "2,000-2,500",
                    "2,500-3,000"};
            for (int x = 0; x < list.length; x++)
                questions.add(list[x]);
        } else if (index == 4) {
            String[] list = new String[]{"- Are you a household owner? -",
                    "Yes", "No"};
            for (int x = 0; x < list.length; x++)
                questions.add(list[x]);
        } else if (index == 5) {
            String[] list = new String[]{"- Are you a household renter? -",
                    "Yes", "No"};
            for (int x = 0; x < list.length; x++)
                questions.add(list[x]);
        }

        // Set question list based on selected index
        densityGUI.getQuestionList().setModel(new DefaultComboBoxModel<>(questions.toArray(new String[0])));

    }

    // Change map colour
    public static class ColourFilter extends RGBImageFilter {
        int lv;

        public int filterRGB(int x, int y, int rgb) {
            if (densityGUI.getDataList().getSelectedIndex() == 1) {
                lv = 0xFF90CAF9; // test if colour changes. will be changed to the data information later
            } else if (densityGUI.getDataList().getSelectedIndex() == 2) {
                lv = 0xFF90CAF9; // test colour change
            } else if (densityGUI.getDataList().getSelectedIndex() == 3) {
                lv = 0xFF64B5F6; // test colour change
            } else if (densityGUI.getDataList().getSelectedIndex() == 4) {
                lv = 0xFF1E88E5; // test colour change
            } else if (densityGUI.getDataList().getSelectedIndex() == 5) {
                lv = 0xFF1565C0; // test colour change
            } else {
                lv = 0xFFE5E7E9; // Default grey colour
            }
            return (rgb & lv);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == densityGUI.getSubmitButton()) {
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

