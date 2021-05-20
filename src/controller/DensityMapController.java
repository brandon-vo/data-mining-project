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
    
    DensityMapController (Tool gui) {

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
    
    // Change map colour
    public static class RedBlueSwapFilter extends RGBImageFilter {
        public int filterRGB (int x, int y, int rgb) {
            return (rgb&0xFFE5E7E9);
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
    public void actionPerformed (ActionEvent e) {
        
        if (e.getSource() == gui.getSubmitButton()) {
            System.out.println("test");
        }
        
    }
    
    @Override
    public void mouseClicked (MouseEvent e) {
    
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

