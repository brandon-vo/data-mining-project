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

public class DensityMapController implements ActionListener, MouseListener {

    // get selected value for data type
    // get data of selected value
    // change map colours based on data

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
    }

    public void getSelectedData() {
        if (gui.getDataList().getSelectedIndex() == 0) {
            System.out.println("set the map to full grey");
            enableComponents(false);
            updateSelectedData(0);
        } else if (gui.getDataList().getSelectedIndex() == 1) {
            System.out.println("dwell");
            enableComponents(true);
            updateSelectedData(1);
        } else if (gui.getDataList().getSelectedIndex() == 2) {
            System.out.println("avg monthly cost owner");
            enableComponents(true);
            updateSelectedData(2);
        } else if (gui.getDataList().getSelectedIndex() == 3) {
            System.out.println("avg monthly cost rent");
            enableComponents(true);
            updateSelectedData(3);
        } else if (gui.getDataList().getSelectedIndex() == 4) {
            System.out.println("owners");
            enableComponents(true);
            updateSelectedData(4);
        } else if (gui.getDataList().getSelectedIndex() == 5) {
            System.out.println("renters");
            enableComponents(true);
            updateSelectedData(5);
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
            System.out.println("submit button");
        } else if (e.getSource() == gui.getDataList()) {
            System.out.println(gui.getDataList().getSelectedItem());
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

