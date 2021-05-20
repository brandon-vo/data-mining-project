package controller;

import model.MyDataset;
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

    private DensityMapGUI gui;

    DensityMapController(Tool gui) {

        this.gui = (DensityMapGUI) gui;
        setUpListeners();

    }

    // Setup listeners
    public void setUpListeners() {
        gui.getSubmitButton().addActionListener(this);
        gui.getDataList().addActionListener(this);
        gui.getAuroraLabel().addMouseListener(this);
        gui.getEastGwillimburyLabel().addMouseListener(this);
        gui.getGeorginaLabel().addMouseListener(this);
        gui.getKingLabel().addMouseListener(this);
        gui.getMarkhamLabel().addMouseListener(this);
        gui.getNewmarketLabel().addMouseListener(this);
        gui.getRichmondHillLabel().addMouseListener(this);
        gui.getStouffvilleLabel().addMouseListener(this);
        gui.getVaughanLabel().addMouseListener(this);
    }

    public void enableComponents(boolean enable) {
        gui.getCityList().setEnabled(enable);
        gui.getSubmitButton().setEnabled(enable);
        gui.getUserResults().setEnabled(enable);
        gui.getQuestionList().setEnabled(enable);
    }

    public void getSelectedData() {
        if (gui.getDataList().getSelectedIndex() == 0) {
            System.out.println("set the map to full grey");
            enableComponents(false);
            updateSelectedData(0);
            updateQuestionBox(0);
            gui.getUserResults().setText("Please select a dataset!");
        } else if (gui.getDataList().getSelectedIndex() == 1) {
            enableComponents(true);
            updateSelectedData(1);
            updateQuestionBox(1);
            gui.getUserResults().setText("Average Dwelling Value");
        } else if (gui.getDataList().getSelectedIndex() == 2) {
            enableComponents(true);
            updateSelectedData(2);
            updateQuestionBox(2);
            gui.getUserResults().setText("Average Owner Monthly Cost");
        } else if (gui.getDataList().getSelectedIndex() == 3) {
            enableComponents(true);
            updateSelectedData(3);
            updateQuestionBox(3);
            gui.getUserResults().setText("Average Renter Monthly Cost");
        } else if (gui.getDataList().getSelectedIndex() == 4) {
            enableComponents(true);
            updateSelectedData(4);
            updateQuestionBox(4);
            gui.getUserResults().setText("Household Owners");
        } else if (gui.getDataList().getSelectedIndex() == 5) {
            enableComponents(true);
            updateSelectedData(5);
            updateQuestionBox(5);
            gui.getUserResults().setText("Household Renters");
        }
    }

    public void updateSelectedData(int index) {
        // index 0 = clear
        // index 1 = average dwelling
        // index 2 = average monthly cost owners
        // index 3 = average monthly cost renters
        // index 4 = household owners
        // index 5 = household renters
    }

    // Set question combo box
    public void updateQuestionBox(int index) {
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

        gui.getQuestionList().setModel(new DefaultComboBoxModel<>(questions.toArray(new String[0])));
    }

    // Change map colour
    public static class RedBlueSwapFilter extends RGBImageFilter {
        public int filterRGB(int x, int y, int rgb) {
            return (rgb & 0xFFE5E7E9);
            // 0xFFE5E7E9 grey
            // 0xFFE2F1FC
            // 0xFFBBDEFB
            // 0xFF90CAF9
            // 0xFF64B5F6
            // 0xFF1E88E5
            // 0xFF1565C0

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == gui.getSubmitButton()) {
            JOptionPane.showMessageDialog(null, "not done", "not done", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == gui.getDataList()) {
            getSelectedData();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == gui.getAuroraLabel()) {
            JOptionPane.showMessageDialog(null, "Aurora", "Aurora", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == gui.getEastGwillimburyLabel()) {
            JOptionPane.showMessageDialog(null, "east", "east", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == gui.getGeorginaLabel()) {
            JOptionPane.showMessageDialog(null, "g", "g", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == gui.getKingLabel()) {
            JOptionPane.showMessageDialog(null, "k", "k", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == gui.getMarkhamLabel()) {
            JOptionPane.showMessageDialog(null, "m", "m", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == gui.getNewmarketLabel()) {
            JOptionPane.showMessageDialog(null, "n", "n", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == gui.getRichmondHillLabel()) {
            JOptionPane.showMessageDialog(null, "r", "r", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == gui.getStouffvilleLabel()) {
            JOptionPane.showMessageDialog(null, "st", "st", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == gui.getVaughanLabel()) {
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

}

